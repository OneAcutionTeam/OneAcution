package com.siims.auction.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="m_goods")
public class Goods implements Serializable{
	@Id
	@Column(name="g_id")
	private String goodsId;
	
	@Column(name="g_brief_desc")
	private String gBriefDesc;
	
	@Column(name="g_detail_desc")
	private String gDetailDesc;
	
	@Column(name="g_price")
	private float gPrice;
	
	@Column(name="g_images")
	private String gImages;
	
	@Column(name="g_origion_price")
	private float gOrigionPrice;
	
	@Column(name="g_name")
	private String gName;
	
	@Column(name="g_contract")
	private String gConTractId;
	
	@Column(name="g_published")
	private int gPublished;
	
	@Column(name="g_user_id")
	private String gUserId;
	
	@Column(name="g_video")
	private String gVideo;
	@Column(name="g_video_cover")
	private String gVideoCover;
	
	
	

	


	public String getgVideoCover() {
		return gVideoCover;
	}

	public void setgVideoCover(String gVideoCover) {
		this.gVideoCover = gVideoCover;
	}

	public String getgVideo() {
		return gVideo;
	}

	public void setgVideo(String gVideo) {
		this.gVideo = gVideo;
	}

	public String getgUserId() {
		return gUserId;
	}

	public void setgUserId(String gUserId) {
		this.gUserId = gUserId;
	}

	public String getgConTractId() {
		return gConTractId;
	}

	public void setgConTractId(String gConTractId) {
		this.gConTractId = gConTractId;
	}

	public int getgPublished() {
		return gPublished;
	}

	public void setgPublished(int gPublished) {
		this.gPublished = gPublished;
	}

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public String getgBriefDesc() {
		return gBriefDesc;
	}

	public void setgBriefDesc(String gBriefDesc) {
		this.gBriefDesc = gBriefDesc;
	}

	public String getgDetailDesc() {
		return gDetailDesc;
	}

	public void setgDetailDesc(String gDetailDesc) {
		this.gDetailDesc = gDetailDesc;
	}

	public float getgPrice() {
		return gPrice;
	}

	public void setgPrice(float gPrice) {
		this.gPrice = gPrice;
	}

	public String getgImages() {
		return gImages;
	}

	public void setgImages(String gImages) {
		this.gImages = gImages;
	}

	public float getgOrigionPrice() {
		return gOrigionPrice;
	}

	public void setgOrigionPrice(float gOrigionPrice) {
		this.gOrigionPrice = gOrigionPrice;
	}

	public String getgName() {
		return gName;
	}

	public void setgName(String gName) {
		this.gName = gName;
	}
	
	
	
}
