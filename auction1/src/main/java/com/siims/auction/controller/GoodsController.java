package com.siims.auction.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.siims.auction.domain.Goods;
import com.siims.auction.service.GoodsService;
import com.siims.auction.utils.JsonSend;

@Controller
@RequestMapping("/goods")
public class GoodsController {
	@Autowired
	private GoodsService service;
	
	@RequestMapping(value="all_goods",method=RequestMethod.GET)
	public void getAllGoods(
			HttpServletRequest request,HttpServletResponse response
			){
		List<Object> goods = service.getAllGoods();
		JSONArray array = new JSONArray(goods);
		JsonSend.send(response, array.toJSONString());
	}
	
	@RequestMapping(value="user_goods",method=RequestMethod.GET)
	public void getUserGoods(
			@RequestParam(value="user_id") String userId,
			HttpServletRequest request,HttpServletResponse response
			){
		List<Object> goods = service.getUserPublishedGoodsByuId(userId);
		JSONArray array = new JSONArray(goods);
		
		
		JsonSend.send(response, array.toJSONString());
	}
	@RequestMapping(value="goods",method=RequestMethod.GET)
	public void getGoods(
			@RequestParam(value="goods_id") String goodsId,
			HttpServletRequest request,HttpServletResponse response
			){
		Goods g = service.getGoodsById(goodsId);
		if(g==null)return;
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

		
		JsonSend.send(response, j.toJSONString());
	}
	
	@RequestMapping(value="add_goods",method=RequestMethod.POST)
	public void addGoods(
			@RequestParam(value="goods_name",required=true) String name,
			@RequestParam(value="user_id",required=true)String userId,
			@RequestParam(value="origion_price",required=true) double origionPrice,
			@RequestParam(value="goods_price",required=true)double price,
			@RequestParam(value="good_desc",required=true)String desc,
			@RequestParam(value="goods_contract",required=true)String contactId,
			
			HttpServletRequest request,HttpServletResponse response
			){
		
	}
	

}
