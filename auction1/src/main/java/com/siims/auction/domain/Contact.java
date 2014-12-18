package com.siims.auction.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="m_contact")
public class Contact implements Serializable{
	@Id
	@Column(name="id")
	private String cId;
	@Column(name="name")
	private String cName;
	@Column(name="phone")
	private String cPhone;
	@Column(name="city")
	private String cCity;
	@Column(name="religion")
	private String cRegion;
	@Column(name="user_id")
	private String cUserId;
	public String getcId() {
		return cId;
	}
	public void setcId(String cId) {
		this.cId = cId;
	}
	public String getcName() {
		return cName;
	}
	public void setcName(String cName) {
		this.cName = cName;
	}
	public String getcPhone() {
		return cPhone;
	}
	public void setcPhone(String cPhone) {
		this.cPhone = cPhone;
	}
	public String getcCity() {
		return cCity;
	}
	public void setcCity(String cCity) {
		this.cCity = cCity;
	}
	public String getcRegion() {
		return cRegion;
	}
	public void setcRegion(String cRegion) {
		this.cRegion = cRegion;
	}
	public String getcUserId() {
		return cUserId;
	}
	public void setcUserId(String cUserId) {
		this.cUserId = cUserId;
	}
	
	
	

}
