package com.tt.entity;

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

import com.google.common.collect.Lists;

@Entity
@Table(name = "RENYUAN")
public class Renyuan {
	
	private Long id;
	private String name;
	private String bz;
	private Integer type;
	private Fengcheng fengcheng;
	private List<Liucheng> liuchengList = Lists.newArrayList();
	
	public Renyuan() {
		super();
		// TODO Auto-generated constructor stub
	}
	
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
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FC_ID")
	public Fengcheng getFengcheng() {
		return fengcheng;
	}
	public void setFengcheng(Fengcheng fengcheng) {
		this.fengcheng = fengcheng;
	}
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "renyuan")
	@OrderBy("id")
	public List<Liucheng> getLiuchengList() {
		return liuchengList;
	}

	public void setLiuchengList(List<Liucheng> liuchengList) {
		this.liuchengList = liuchengList;
	}
	

}
