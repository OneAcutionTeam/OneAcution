package com.siims.auction.dao;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.Id;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;



/** 
 * 通用dao层实现类，使用Hibernate4实现。
 * @author	dosh
 * @version 2014年9月2日 下午4:10:24
 */
public class BaseDaoImpl<M, PK extends Serializable> implements IBaseDao<M, PK> {
	protected final Log logger = LogFactory.getLog(getClass());
	
    private final Class<M> entityClass;
    private final String HQL_COUNT_ALL;
    private final String HQL_LIST_ALL;
    private String pkName = null;

    @SuppressWarnings("unchecked")
    public BaseDaoImpl() {
        this.entityClass = (Class<M>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        Field[] fields = this.entityClass.getDeclaredFields();
        for(Field f : fields) {
            if(f.isAnnotationPresent(Id.class)) {
                this.pkName = f.getName();
            }
        }
        
        Assert.notNull(pkName);
        // @Entity name not null
        HQL_COUNT_ALL = " select count(*) from " + this.entityClass.getSimpleName();
        HQL_LIST_ALL = "from " + this.entityClass.getSimpleName() ;
    }
        
    @Autowired
    private SessionFactory sessionFactory;
    
    public Session getSession() {
    	
        return sessionFactory.getCurrentSession();
    }
 
	@SuppressWarnings("unchecked")
	@Override
	public M get(PK id) {
		return (M) getSession().get(this.entityClass, id);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public PK save(M model) {
		Session session = getSession();
		M m = (M) session.merge(model);
		Transaction t = session.beginTransaction();
		PK res = (PK)session.save(m);
		t.commit();
		session.flush();
		return res;
		
	}
	
	@Override
	public void saveOrUpdate(M model) {
		Transaction t = getSession().beginTransaction();
		getSession().saveOrUpdate(model);
		t.commit();
		getSession().flush();
	}
	
	@Override
	public void update(M model) {
		Transaction t = getSession().beginTransaction();
		getSession().update(model);
		t.commit();
	}

	@Override
	public void merge(M model) {
		getSession().merge(model);
	}
	
	@Override
	public void delete(PK id) {
		Transaction t = getSession().beginTransaction();
		getSession().delete(this.get(id));
		t.commit();
	}
	
	@Override
	public void delete(M model) {
		Transaction t = getSession().beginTransaction();
		getSession().delete(model);
		t.commit();
	}
	
	@Override
	public boolean exists(PK id) {
		return get(id) != null;
	}
	
	@Override
	public void flush() {
		getSession().flush();
	}
	
	@Override
	public void clear() {
		getSession().clear();
	}
	
	@Override
	public int countAll() {
        Long total = aggregate(HQL_COUNT_ALL);
        return total.intValue();
	}
	
   /**
     * 获取实体类型的全部对象
     */
    @SuppressWarnings("unchecked")
	public List<M> getAll() {
    	Session s = getSession();
    	Transaction t = s.beginTransaction();
    	Query q =  s.createQuery(HQL_LIST_ALL);
    	t.commit();
    	s.flush();
    	return q.list();
    }
    
    /**
     * 获取全部对象,带排序字段与升降序参数.
     */
    @SuppressWarnings("unchecked")
    public List<M> getAll(String protertyName, boolean isAsc) {
        Assert.hasText(protertyName);
        Criteria criteria = getSession().createCriteria(this.entityClass);
        if(isAsc){
        	criteria.addOrder(Order.asc(protertyName));
        }else{
        	criteria.addOrder(Order.desc(protertyName));
        }
        return criteria.list();
    } 
    
    /**
     * 根据属性名和属性值查询对象.
     * @return 符合条件的对象列表
     */
    @SuppressWarnings("unchecked")
    public List<M> findBy(String propertyName, Object value) {
        Assert.hasText(propertyName);
        return createCriteria(Restrictions.eq(propertyName, value)).list();
    }
    
    /**
     * 根据属性名和属性值查询对象,带排序参数.
     */
    @SuppressWarnings("unchecked")
    public List<M> findBy(String propertyName, Object value, String orderBy, boolean isAsc) {
        Assert.hasText(propertyName);
        Assert.hasText(orderBy);
        return createCriteria(orderBy, isAsc, Restrictions.eq(propertyName, value)).list();
    }
    
    /**
     * 根据属性名和属性值查询唯一对象.
     * @return 符合条件的唯一对象 or null if not found.
     */
    @SuppressWarnings("unchecked")
    public M findUniqueBy(String propertyName, Object value) {
        Assert.hasText(propertyName);
        return (M) createCriteria(Restrictions.eq(propertyName, value)).uniqueResult();
    } 
	
    @SuppressWarnings({ "unchecked", "hiding" })
	protected <M> M aggregate(final String hql, final Object... paramlist) {
        Query query = getSession().createQuery(hql);
        setParameters(query, paramlist);
        return (M) query.uniqueResult();
    }
    
    @SuppressWarnings({ "unchecked", "hiding" })
    protected <M> M aggregate(final String hql, final Map<String, Collection<?>> map, final Object... paramlist) {
        Query query = getSession().createQuery(hql);
        if (paramlist != null) {
            setParameters(query, paramlist);
            for (Entry<String, Collection<?>> e : map.entrySet()) {
                query.setParameterList(e.getKey(), e.getValue());
            }
        }
        return  (M) query.uniqueResult();
    }
    
    protected void setParameters(Query query, Object[] paramlist) {
        if (paramlist != null) {
            for (int i = 0; i < paramlist.length; i++) {
                if(paramlist[i] instanceof Date) {
                    //难道这是bug 使用setParameter不行？？
                    query.setTimestamp(i, (Date)paramlist[i]);
                } else {
                    query.setParameter(i, paramlist[i]);
                }
            }
        }
    }

    /**
     * 创建Criteria对象.
     * @param criterions 可变的Restrictions条件列表
     */
    public Criteria createCriteria(Criterion... criterions) {
        Criteria criteria = getSession().createCriteria(this.entityClass);
        for (Criterion c : criterions) {
            criteria.add(c);
        }
        return criteria;
    }
    /**
     * 创建Criteria对象，带排序字段与升降序字段.
     */
    public Criteria createCriteria(String orderBy, boolean isAsc, Criterion... criterions) {
        Assert.hasText(orderBy);
        Criteria criteria = createCriteria(criterions);
        if (isAsc)
            criteria.addOrder(Order.asc(orderBy));
        else
            criteria.addOrder(Order.desc(orderBy));
        return criteria;
    }

	@Override
	public M findEntityByHql(String hql, Object... values) {
		// TODO Auto-generated method stub
		Session session = getSession();
		Transaction t =null;
		 Query query = null;
		try{
			t= session.beginTransaction();
			query = session.createQuery(hql);
		}catch(Exception e){
			System.out.println("Entity worong  "+e.toString());
			return null;
		}
		try{
        for (int i = 0; i < values.length; i++) {
            query.setParameter(i, values[i]);
        }
      t.commit();
      session.flush();
		}catch(Exception e){
			return null;
		}
		if(query.list()==null){
			return null;
		}
		if(query.list().size()==0){
			return null;
		}
       return (M) query.list().get(0);
	}

	@Override
	public List<M> findByHql(String hql, Object... values) {
		// TODO Auto-generated method stub
		Session session = getSession();
		 Query query = session.createQuery(hql);
         for (int i = 0; i < values.length; i++) {
             query.setParameter(i, values[i]);
         }
       //  query.setMaxResults(count).setFirstResult(start);
         List<M> list = query.list();
         return list;
	} 
    
}
