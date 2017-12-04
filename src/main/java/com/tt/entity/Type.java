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

/**
 * 类型实体表
 * @author you
 * */
@Entity
@Table(name = "TYPE")
public class Type {
	private Long id;
	private String name;
	private String bz;
	private List<Shangpin> ShangpinList = Lists.newArrayList();

	
	
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
	
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "types")
	@OrderBy("id")
	public List<Shangpin> getShangpinList() {
		return ShangpinList;
	}
	public void setShangpinList(List<Shangpin> shangpinList) {
		ShangpinList = shangpinList;
	}
	
	
}
