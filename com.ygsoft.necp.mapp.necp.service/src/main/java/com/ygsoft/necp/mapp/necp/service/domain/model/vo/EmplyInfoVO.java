/*
 * Copyright (C) 2005 - 2030 YGSoft.Inc All Rights Reserved.
 * YGSoft.Inc PROPRIETARY/CONFIDENTIAL.Use is subject to license terms.
 */
package com.ygsoft.necp.mapp.necp.service.domain.model.vo;
import java.util.Date;

import com.ygsoft.necp.enumeration.component.types.Gender;

import com.ygsoft.necp.component.genentity.domain.model.GenObjectEntity;

/**
 * EmplyInfoVO的VO类.<br>
 * 
 * @author zhangying14@ygsoft.com <br>
 * @version 1.0.0 2020-09-06 19:32:17<br>
 * @since JDK 1.8.0_152
 */
public class EmplyInfoVO extends GenObjectEntity {

	/**
	 * 序列化版本.
	 */
	private static final long serialVersionUID = 1L;
												
	/**
	 * 员工id.
	 */
	private String emplyid;
														
	/**
	 * 员工名称.
	 */
	private String emplyname;
														
	/**
	 * 年龄.
	 */
	private int age;
														
	/**
	 * 性别.
	 */
	private Gender sex;
														
	/**
	 * 生日.
	 */
	private Date birth;
														
	/**
	 * 籍贯.
	 */
	private String nat;
														
	/**
	 * 民族.
	 */
	private String nation;
														
	/**
	 * 邮件.
	 */
	private String email;
				
	/**
     * 员工id的get方法
     * @return emplyid
     */
    public String getEmplyid() {
        return emplyid;
    }
		
    /**
     * 设置员工id.
     * @param emplyid
     */
    public void setEmplyid(final String newEmplyid) {
		this.emplyid = newEmplyid;
	}
		
	/**
     * 员工名称的get方法
     * @return emplyname
     */
    public String getEmplyname() {
        return emplyname;
    }
		
    /**
     * 设置员工名称.
     * @param emplyname
     */
    public void setEmplyname(final String newEmplyname) {
		this.emplyname = newEmplyname;
	}
		
	/**
     * 年龄的get方法
     * @return age
     */
    public int getAge() {
        return age;
    }
		
    /**
     * 设置年龄.
     * @param age
     */
    public void setAge(final int newAge) {
		this.age = newAge;
	}
		
	/**
     * 性别的get方法
     * @return sex
     */
    public Gender getSex() {
        return sex;
    }
		
    /**
     * 设置性别.
     * @param sex
     */
    public void setSex(final Gender newSex) {
		this.sex = newSex;
	}
		
	/**
     * 生日的get方法
     * @return birth
     */
    public Date getBirth() {
        return birth;
    }
		
    /**
     * 设置生日.
     * @param birth
     */
    public void setBirth(final Date newBirth) {
		this.birth = newBirth;
	}
		
	/**
     * 籍贯的get方法
     * @return nat
     */
    public String getNat() {
        return nat;
    }
		
    /**
     * 设置籍贯.
     * @param nat
     */
    public void setNat(final String newNat) {
		this.nat = newNat;
	}
		
	/**
     * 民族的get方法
     * @return nation
     */
    public String getNation() {
        return nation;
    }
		
    /**
     * 设置民族.
     * @param nation
     */
    public void setNation(final String newNation) {
		this.nation = newNation;
	}
		
	/**
     * 邮件的get方法
     * @return email
     */
    public String getEmail() {
        return email;
    }
		
    /**
     * 设置邮件.
     * @param email
     */
    public void setEmail(final String newEmail) {
		this.email = newEmail;
	}
	
}
