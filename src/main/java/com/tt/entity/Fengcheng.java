package com.tt.entity;

import com.google.common.collect.Lists;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "FENGCHENG")
public class Fengcheng {
	private Long id;
	private String name;
	private Integer fcbl;
	private String bz;
	private Integer lx;
	 private List<Renyuan> renyuanList = Lists.newArrayList();
	 private List<User> userList = Lists.newArrayList();

	 
	 
	public Fengcheng() {
		super();
		// TODO Auto-generated constructor stub
	}
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "fengcheng")
	@OrderBy("id")
	public List<Renyuan> getRenyuanList() {
		return renyuanList;
	}
	public void setRenyuanList(List<Renyuan> renyuanList) {
		this.renyuanList = renyuanList;
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
	public Integer getFcbl() {
		return fcbl;
	}
	public void setFcbl(Integer fcbl) {
		this.fcbl = fcbl;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public Integer getLx() {
		return lx;
	}
	public void setLx(Integer lx) {
		this.lx = lx;
	}
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "fengcheng")
	@OrderBy("id")
	public List<User> getUserList() {
		return userList;
	}
	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
	
	
}
