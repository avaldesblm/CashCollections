package com.blm.webportal.pins.models.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Bitacora")
public class Binnacle {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;	
	private String user_name;
	@Temporal(TemporalType.TIMESTAMP)
	private Date request_date;
	private String validation_pin;
	private String ceve;
	private String route;
	private String bimbo_code;
	private BigDecimal amount;
	private int status;
	
	@Column(length = 150)
	private String ip;
	@Column(length = 150)
	private String method;
	@Column(length = 150)
	private String url;
	@Column(length = 4000)
	private String rspDescription;
	
	public String getRspDescription() {
		return rspDescription;
	}
	public void setRspDescription(String rspDescription) {
		this.rspDescription = rspDescription;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public Date getRequest_date() {
		return request_date;
	}
	public void setRequest_date(Date request_date) {
		this.request_date = request_date;
	}
	public String getValidation_pin() {
		return validation_pin;
	}
	public void setValidation_pin(String validation_pin) {
		this.validation_pin = validation_pin;
	}
	public String getCeve() {
		return ceve;
	}
	public void setCeve(String ceve) {
		this.ceve = ceve;
	}
	public String getRoute() {
		return route;
	}
	public void setRoute(String route) {
		this.route = route;
	}
	public String getBimbo_code() {
		return bimbo_code;
	}
	public void setBimbo_code(String bimbo_code) {
		this.bimbo_code = bimbo_code;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
}
