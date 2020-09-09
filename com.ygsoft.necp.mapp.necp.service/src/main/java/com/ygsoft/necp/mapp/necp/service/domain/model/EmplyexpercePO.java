/*
 * Copyright (C) 2005 - 2030 YGSoft.Inc All Rights Reserved.
 * YGSoft.Inc PROPRIETARY/CONFIDENTIAL.Use is subject to license terms.
 */
package com.ygsoft.necp.mapp.necp.service.domain.model;
import com.ygsoft.ecp.core.framework.annotations.Topic;
import com.ygsoft.necp.component.genentity.domain.model.GenObjectEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * EmplyexpercePO的POJO类.<br>
 * 
 * @author zhangying14@ygsoft.com <br>
 * @version 1.0.0 2020-09-06 19:32:16<br>
 * @since JDK 1.8.0_152
 */
@Entity
@Table(name = "emply_experce", indexes = {})
@Topic(classId = "empdetailinfo", typeId = "emplyexpercepo")
public class EmplyexpercePO extends GenObjectEntity {
	/**
	 * 序列化版本.
	 */
	private static final long serialVersionUID = 1L;
												
	/**
	 * 入职日期.
	 */
	private Date stime;
											
	/**
	 * 离职日期.
	 */
	private Date etime;
											
	/**
	 * 工作单位.
	 */
	private String company;
											
	/**
	 * 职务.
	 */
	private String composition;
											
	/**
	 * 备注.
	 */
	private String rmark;

	/**
	 * 员工id
	 */
	private String emplyid;

	private String gid;


	/**
     * 入职日期的get方法
     * @return stime
     */
	@Column(name = "STIME", nullable = true)
    public Date getStime() {
        return stime;
    }
		
    /**
     * 设置入职日期.
     * @param stime
     */
    public void setStime(final Date newStime) {
		this.stime = newStime;
	}
	
	/**
     * 离职日期的get方法
     * @return etime
     */
	@Column(name = "ETIME", nullable = true)
    public Date getEtime() {
        return etime;
    }
		
    /**
     * 设置离职日期.
     * @param etime
     */
    public void setEtime(final Date newEtime) {
		this.etime = newEtime;
	}
	
	/**
     * 工作单位的get方法
     * @return company
     */
	@Column(name = "COMPANY", nullable = true, length = 50)
    public String getCompany() {
        return company;
    }
		
    /**
     * 设置工作单位.
     * @param company
     */
    public void setCompany(final String newCompany) {
		this.company = newCompany;
	}
	
	/**
     * 职务的get方法
     * @return composition
     */
	@Column(name = "COMPOSITION", nullable = true, length = 50)
    public String getComposition() {
        return composition;
    }
		
    /**
     * 设置职务.
     * @param composition
     */
    public void setComposition(final String newComposition) {
		this.composition = newComposition;
	}
	
	/**
     * 备注的get方法
     * @return rmark
     */
	@Column(name = "RMARK", nullable = true, length = 50)
    public String getRmark() {
        return rmark;
    }
		
    /**
     * 设置备注.
     * @param rmark
     */
    public void setRmark(final String newRmark) {
		this.rmark = newRmark;
	}

	public String getGid() {
		return this.gid;
	}

	public void setGid(String gid) {
		this.gid = gid;
	}

	public String getEmplyid() {
		return emplyid;
	}

	public void setEmplyid(String emplyid) {
		this.emplyid = emplyid;
	}
}
