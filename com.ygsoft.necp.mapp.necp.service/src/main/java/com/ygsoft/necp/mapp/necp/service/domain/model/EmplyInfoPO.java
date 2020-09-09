/*
 * Copyright (C) 2005 - 2030 YGSoft.Inc All Rights Reserved.
 * YGSoft.Inc PROPRIETARY/CONFIDENTIAL.Use is subject to license terms.
 */
package com.ygsoft.necp.mapp.necp.service.domain.model;

import com.ygsoft.ecp.core.framework.annotations.Topic;
import com.ygsoft.necp.component.genentity.domain.model.GenObjectEntity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * EmplyInfoPO的POJO类.<br>
 * 
 * @author zhangying14@ygsoft.com <br>
 * @version 1.0.0 2020-09-06 19:32:17<br>
 * @since JDK 1.8.0_152
 */
@Entity
@Table(name = "emply_info")
@Topic(classId = "empdetailinfo", typeId = "emplyinfo11po")
public class EmplyInfoPO extends GenObjectEntity {
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
	private int sex;
											
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
	 * 工作经历.
	 */
	private List<EmplyexpercePO> emplyexperce;

	private String gid;

	public String getGid() {
		return this.gid;
	}

	public void setGid(String gid) {
		this.gid = gid;
	}
	
	/**
     * 员工id的get方法
     * @return emplyid
     */
	@Column(name = "emplyid", nullable = true ,length = 50)
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
	@Column(name = "emplyname", nullable = true, length = 50)
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
	@Column(name = "age", nullable = true)
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
	@Column(nullable = true, name = "SEX", columnDefinition = "int default 0")
    public Integer getSex() {
        return sex;
    }
		
    /**
     * 设置性别.
     * @param sex
     */
    public void setSex(final Integer newSex) {
		this.sex = newSex;
	}
	
	/**
     * 生日的get方法
     * @return birth
     */
	@Column(name = "BIRTH", nullable = true)
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
	@Column(name = "NAT", nullable = true, length = 50)
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
	@Column(name = "NATION", nullable = true, length = 50)
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
	@Column(name = "EMAIL", nullable = true, length = 50)
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
	
	/**
     * 工作经历的get方法
     * @return emplyexperce
     */
	@Transient
	public List<EmplyexpercePO> getEmplyexperce() {
		return emplyexperce;
	}
		
    /**
     * 设置工作经历.
     * @param emplyexperce
     */
	public void setEmplyexperce(final List<EmplyexpercePO> newEmplyexperce) {
		this.emplyexperce = newEmplyexperce;
	}
}
