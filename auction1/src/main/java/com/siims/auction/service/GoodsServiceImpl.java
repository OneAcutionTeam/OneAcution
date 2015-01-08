package com.siims.auction.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.siims.auction.dao.goods.IgoodsDao;
import com.siims.auction.domain.Goods;

@Service
public class GoodsServiceImpl implements GoodsService{
	@Autowired
	private IgoodsDao dao;
	@Override
	public List<Object> getAllGoods() {
		// TODO Auto-generated method stub
		List<Goods> goods = dao.getAll();
		
		List<Object> res = new ArrayList<Object>();
		for(Goods g : goods){
			JSONObject j = new JSONObject();
			j.put("gId", g.getGoodsId());
			j.put("gName", g.getgName());
			j.put("gPrePrice", g.getgOrigionPrice());
			j.put("gPrice", g.getgPrice());
			j.put("gPublished", g.getgPublished());
			j.put("gBrief", g.getgBriefDesc());
			j.put("gDetail", g.getgDetailDesc());
			j.put("gImages", g.getgImages());
			j.put("gContract", g.getgConTractId());
			j.put("gUserId", g.getgUserId());
			res.add(j);
		}
		return res;
	}

	@Override
	public List<Object> getUserPublishedGoodsByuId(String userId) {
		// TODO Auto-generated method stub
		String hql = "from Goods as g where g.gUserId = ? ";
		List<Goods> goods = null;
		try{
			goods = dao.findByHql(hql, userId);
		}catch(Exception e){
			System.out.println(e.toString());
		}
		List<Object> res = new ArrayList<Object>();
		for(Goods g : goods){
			JSONObject j = new JSONObject();
			j.put("gId", g.getGoodsId());
			j.put("gName", g.getgName());
			j.put("gPrePrice", g.getgOrigionPrice());
			j.put("gPrice", g.getgPrice());
			j.put("gPublished", g.getgPublished());
			j.put("gBrief", g.getgBriefDesc());
			j.put("gDetail", g.getgDetailDesc());
			j.put("gImages", g.getgImages());
			j.put("gContract", g.getgConTractId());
			j.put("gUserId", g.getgUserId());
			res.add(j);
		}
		return res;
	}

	@Override
	public Goods getGoodsById(String goodsId) {
		// TODO Auto-generated method stub
		//String hql = "select from Goods as g where g.goodsId = ?";
		//Goods g = dao.findEntityByHql(hql, goodsId);
		Goods g = dao.get(goodsId);
		return g;
	}

	@Override
	public boolean addGoods(Goods goods) {
		try{
			String key = dao.save(goods);
			if(key!=null){
				System.out.println(key);
				return true;
			}else{
				return false;
			}
			
		}catch(Exception e){
			System.out.println(e.toString());
		}
		return false;
	}

	@Override
	public boolean deleteGoods(Goods goods) {
		// TODO Auto-generated method stub
		try{
			dao.delete(goods);
			return true;
		}catch(Exception e){
			
		}
		return false;
	}

	@Override
	public boolean deleteGoods(String goodsId) {
		// TODO Auto-generated method stub
		String hql = "delete from Goods g where g.id = "+goodsId;
		try{
			dao.deleteByHql(hql);
			return true;
		}catch(Exception e){
			System.out.println(e.toString());
		}
		return false;
	}

	@Override
	public boolean deleteSeletedGoods(String ids) {
		// TODO Auto-generated method stub
		String hql ="delete from Goods g where g.id in ("+ids+")";
		try{
			dao.deleteByHql(hql);
			return true;
		}catch(Exception e){
			System.out.println(e.toString());
		}
		return false;
	}

	@Override
	public boolean publishGoods(String goodsId) {
		// TODO Auto-generated method stub
		String hql = "update Goods g set g.gPublished = 1 where g.id = "+goodsId;
		
		try{
			dao.executeHql(hql);
			System.out.println(hql);
			return true;
		}catch(Exception e){
			System.out.println(e.toString());
		}
		return false;
	}

	@Override
	public boolean publishSeletedGoods(String ids) {
		// TODO Auto-generated method stub
		System.out.println(ids);
	String hql = "update Goods g set g.gPublished = 1 where g.id in ( "+ids+")";
		
		try{
			dao.executeHql(hql);
			return true;
		}catch(Exception e){
			System.out.println(e.toString());
		}
		return false;
	}

}
