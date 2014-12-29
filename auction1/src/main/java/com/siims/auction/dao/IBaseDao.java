package com.siims.auction.dao;

import java.io.Serializable;
import java.util.List;

/** 
 * 通用dao层接口
 * @author	dosh
 * @version 2014年9月2日 下午4:05:28
 */
public interface IBaseDao<M, PK extends Serializable>{
	
	/**
	 * 根据主键ID获取对象
	 * @param id 主键
	 * @return 实体对象
	 */
	public M get(PK id);
	
	/**
	 * 保存实体对象
	 * @param model 实体对象
	 * @return 主键
	 */
    public PK save(M model);

    /**
     * 保存或更新数据库记录：
     * 根据主键判断数据库中是否有该对象，如果有，则更新；反之，则新增一条记录
     * @param model
     */
    public void saveOrUpdate(M model);
    
    /**
     * 更新实体对象（根据主键判断）
     * @param model
     */
    public void update(M model);
    
    public void merge(M model);

    /**
     * 删除数据库中对应记录
     * @param id 主键
     */
    public void delete(PK id);

    /**
     * 删除数据库中对应记录
     * @param model 实体对象（包含主键信息）
     */
    public void delete(M model);
    
    /**
     * 数据库中是否存在该记录
     * @param id 主键
     * @return
     */
    boolean exists(PK id);
    
    /**
     * 执行一些必须的sql语句把内存中的对象同步到jdbc的链接中
     */
    public void flush();
    
    /**
     * 清除所有对象缓存
     */
    public void clear();
    
    /**
     * 获取记录总条数
     * @return
     */
    public int countAll();
    
    /**
     * 获取实体类型的全部对象
     */
    public List<M> getAll();

    /**
     * 获取全部对象,带排序字段与升降序参数.
     * @param orderBy 查询条件
     * @param isAsc 是否升序
     * @return
     */
    public List<M> getAll(String orderBy, boolean isAsc);
    
    /**
     * 查找符合条件的记录
     * @param propertyName 属性名
     * @param value 属性值
     * @return
     */
    public List<M> findBy(String propertyName, Object value);
    
    /**
     * 根据属性名和属性值查询对象,带排序参数.
     * @param propertyName 属性名
     * @param value 属性值
     * @param orderBy 排序参数
     * @param isAsc 是否升序
     * @return
     */
    public List<M> findBy(String propertyName, Object value, String orderBy, boolean isAsc);
    
    /**
     * 根据属性名和属性值查询唯一对象.
     * @param propertyName 属性名
     * @param value 属性值
     * @return
     */
    public M findUniqueBy(String propertyName, Object value);
    /**
     * 根据hql取得实体
     * @param hql hql语句
     * @param objects 属性值
     * @return
     */
    public M findEntityByHql(String hql,Object... values );
    /**
     * 根据hql取得实体列表
     * @param hql
     * @param objects
     * @return
     */
    public List<M>findByHql(String hql,Object... values );
    /**
     * 
     */
    public boolean deleteByHql(String hql);
    /**
     * 
     * @param hql
     * @return
     */
    public boolean executeHql(String hql);
}
