package com.tt.entity;


import java.sql.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.google.common.collect.Lists;
/**
 * 商品实体
 * @author you
 * **/
@Entity
@Table(name = "SHANGPIN")
public class Shangpin {
	private Long id;
	private String name;
	private String wid;
	private String type;
	private String spxh;
	private String ghs;
	private Integer kcsl;
	private Date ghrq;
	private Double cb;
	private Double mtj;
	private Double zdj;
	private String path; 
	private List<Liucheng> liuchengList = Lists.newArrayList();
	private Type types;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getWid() {
		return wid;
	}
	public void setWid(String wid) {
		this.wid = wid;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSpxh() {
		return spxh;
	}
	public void setSpxh(String spxh) {
		this.spxh = spxh;
	}
	public String getGhs() {
		return ghs;
	}
	public void setGhs(String ghs) {
		this.ghs = ghs;
	}
	public Integer getKcsl() {
		return kcsl;
	}
	public void setKcsl(Integer kcsl) {
		this.kcsl = kcsl;
	}
	public Date getGhrq() {
		return ghrq;
	}
	public void setGhrq(Date ghrq) {
		this.ghrq = ghrq;
	}
	public Double getCb() {
		return cb;
	}
	public void setCb(Double cb) {
		this.cb = cb;
	}
	public Double getMtj() {
		return mtj;
	}
	public void setMtj(Double mtj) {
		this.mtj = mtj;
	}
	public Double getZdj() {
		return zdj;
	}
	public void setZdj(Double zdj) {
		this.zdj = zdj;
	}
	
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "shangpin")
	@OrderBy("id")
	public List<Liucheng> getLiuchengList() {
		return liuchengList;
	}
	public void setLiuchengList(List<Liucheng> liuchengList) {
		this.liuchengList = liuchengList;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TID")
	public Type getTypes() {
		return types;
	}
	public void setTypes(Type types) {
		this.types = types;
	}
	
	
	
	
	
}
