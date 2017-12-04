package com.tt.entity;








import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
/**
 * 流程表实体
 * @author you
 * **/
@Entity
@Table(name = "LIUCHENG")
public class Liucheng {
	private Long id;
//	private String userName;
	private String bz;
	private Date czsj;
	//类型1.出售   2.进货   3.退件
	private Integer type;
	private Double jyje;
	private Double lr;
	private Integer jysl;
	//状态 1.未对账2.以对帐
	private Integer zt;
	private Integer zkfs;
	private Shangpin shangpin;
	private Renyuan renyuan;
	private Zhekou zhekou;
	private User user;
	public Liucheng() {
		super();
		
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
	public Integer getZkfs() {
		return zkfs;
	}
	public void setZkfs(Integer zkfs) {
		this.zkfs = zkfs;
	}
	/**
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	*/
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public Date getCzsj() {
		return czsj;
	}
	public void setCzsj(Date czsj) {
		this.czsj = czsj;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Double getJyje() {
		return jyje;
	}
	public void setJyje(Double jyje) {
		this.jyje = jyje;
	}
	public Double getLr() {
		return lr;
	}
	public void setLr(Double lr) {
		this.lr = lr;
	}
	public Integer getJysl() {
		return jysl;
	}
	public void setJysl(Integer jysl) {
		this.jysl = jysl;
	}
	public Integer getZt() {
		return zt;
	}
	public void setZt(Integer zt) {
		this.zt = zt;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SID")
    @NotFound(action=NotFoundAction.IGNORE)
	public Shangpin getShangpin() {
		return shangpin;
	}
	public void setShangpin(Shangpin shangpin) {
		this.shangpin = shangpin;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "R_ID")
    @NotFound(action=NotFoundAction.IGNORE)
	public Renyuan getRenyuan() {
		return renyuan;
	}
	public void setRenyuan(Renyuan renyuan) {
		this.renyuan = renyuan;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ZID")
    @NotFound(action=NotFoundAction.IGNORE)
	public Zhekou getZhekou() {
		return zhekou;
	}
	public void setZhekou(Zhekou zhekou) {
		this.zhekou = zhekou;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID")
    @NotFound(action=NotFoundAction.IGNORE)
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
	
}
