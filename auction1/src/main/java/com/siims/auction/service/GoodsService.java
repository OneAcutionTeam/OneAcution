package com.siims.auction.service;

import java.util.List;

import com.siims.auction.domain.Goods;

public interface GoodsService {
	public List<Object>getAllGoods();
	public List<Object>getUserPublishedGoodsByuId(String userId);
	public Goods getGoodsById(String goodsId);
	public boolean addGoods(Goods goods);
	public boolean deleteGoods(Goods goods);
	public boolean deleteGoods(String goodsId);
	
}
