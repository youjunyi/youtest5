package com.tt.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.google.common.collect.Lists;

@Entity
@Table(name = "ZHEKOU")
public class Zhekou {
	private Long id;
	private Double zkl;
	private String bz;
	private String name;
	private List<Liucheng> liuchengList = Lists.newArrayList();
	public Zhekou() {
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

	public Double getZkl() {
		return zkl;
	}

	public void setZkl(Double zkl) {
		this.zkl = zkl;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "zhekou")
	@OrderBy("id")
	public List<Liucheng> getLiuchengList() {
		return liuchengList;
	}

	public void setLiuchengList(List<Liucheng> liuchengList) {
		this.liuchengList = liuchengList;
	}

}
