/*
 * Copyright (C) 2005 - 2030 YGSoft.Inc All Rights Reserved.
 * YGSoft.Inc PROPRIETARY/CONFIDENTIAL.Use is subject to license terms.
 */
package com.ygsoft.necp.mapp.necp.service.domain.model.vo;
import java.util.Date;


import com.ygsoft.necp.component.genentity.domain.model.GenObjectEntity;

/**
 * EmplyexperceVO的VO类.<br>
 * 
 * @author zhangying14@ygsoft.com <br>
 * @version 1.0.0 2020-09-06 19:32:16<br>
 * @since JDK 1.8.0_152
 */
public class EmplyexperceVO extends GenObjectEntity {

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
     * 入职日期的get方法
     * @return stime
     */
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
	
}
