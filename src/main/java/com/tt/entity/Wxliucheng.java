package com.tt.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "LCTAB")
public class Wxliucheng {
	private Long id;
	private Shouji  shouji;
	private Jiejuefanan jjfa;
	private String biaozhi;
	private Date kssj;
	private Date jssj;
	private String bz;
	private Long user_id;
	private String bh;
	private String fwfbh;
	private String sxrq;
	private String wcbz;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SHOUJI_ID")
    @NotFound(action=NotFoundAction.IGNORE)
	public Shouji getShouji() {
		return shouji;
	}
	public void setShouji(Shouji shouji) {
		this.shouji = shouji;
	}
	@OneToOne(targetEntity= Jiejuefanan.class,fetch = FetchType.LAZY)
	@JoinColumn(name = "JJFA_ID")
	public Jiejuefanan getJjfa() {
		return jjfa;
	}
	public void setJjfa(Jiejuefanan jjfa) {
		this.jjfa = jjfa;
	}
	public String getBiaozhi() {
		return biaozhi;
	}
	public void setBiaozhi(String biaozhi) {
		this.biaozhi = biaozhi;
	}
	public Date getKssj() {
		return kssj;
	}
	public void setKssj(Date kssj) {
		this.kssj = kssj;
	}
	public Date getJssj() {
		return jssj;
	}
	public void setJssj(Date jssj) {
		this.jssj = jssj;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public Long getUser_id() {
		return user_id;
	}
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	public String getBh() {
		return bh;
	}
	public void setBh(String bh) {
		this.bh = bh;
	}
	public String getFwfbh() {
		return fwfbh;
	}
	public void setFwfbh(String fwfbh) {
		this.fwfbh = fwfbh;
	}
	public String getSxrq() {
		return sxrq;
	}
	public void setSxrq(String sxrq) {
		this.sxrq = sxrq;
	}
	public String getWcbz() {
		return wcbz;
	}
	public void setWcbz(String wcbz) {
		this.wcbz = wcbz;
	}
	
	
}
