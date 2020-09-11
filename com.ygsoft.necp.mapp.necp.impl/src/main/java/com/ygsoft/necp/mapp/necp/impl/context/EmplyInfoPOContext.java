/*
 * Copyright (C) 2005 - 2030 YGSoft.Inc All Rights Reserved.
 * YGSoft.Inc PROPRIETARY/CONFIDENTIAL.Use is subject to license terms.
 */
package com.ygsoft.necp.mapp.necp.impl.context;

import com.hazelcast.util.UuidUtil;
import com.ygsoft.ecp.service.dataaccess.ISQLTemplate;
import com.ygsoft.necp.component.genentity.context.EntityContext;
import com.ygsoft.necp.core.service.dcispec.GeneralContext;
import com.ygsoft.necp.core.service.dcispec.annotation.EventSource;
import com.ygsoft.necp.mapp.necp.service.context.IEmplyInfoPOContext;
import com.ygsoft.necp.mapp.necp.service.dao.IEmplyInfoPODao;
import com.ygsoft.necp.mapp.necp.service.dao.IEmplyexpercePODao;
import com.ygsoft.necp.mapp.necp.service.domain.model.EmplyInfoPO;
import com.ygsoft.necp.mapp.necp.service.domain.model.EmplyexpercePO;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * EmplyInfoPO场景类.<br>
 *
 * @author zhangying14@ygsoft.com <br>
 * @version 1.0.0 2020-09-06 19:32:17<br>
 * @since JDK 1.8.0_152
 */
@Service
@Transactional
@EventSource
@EntityContext(classId = "empdetailinfo", typeId = "emplyinfo11po")
public class EmplyInfoPOContext extends GeneralContext<EmplyInfoPO, String, IEmplyInfoPODao>
		implements IEmplyInfoPOContext {
		
	/**
	 * 构造函数.
	 */
	public EmplyInfoPOContext() {
		super();
	}

	@Autowired
	ISQLTemplate sqlTemplate;

	@Autowired
	private IEmplyexpercePODao emplyexpercePODao;

	/**
	 * {@inheritDoc}
	 * 
	 * @see com.ygsoft.necp.core.service.dcispec.GeneralContext#newModel()
	 */
	@Override
	protected EmplyInfoPO newModel() {
		return new EmplyInfoPO();
	}


	@Override
	public Page<EmplyInfoPO> queryByConditionList(Map<String, Object> map, Pageable pageable) {
		Specification<EmplyInfoPO> sep = getSupplierPaySummarySpection(map);
		Page<EmplyInfoPO> list = this.getDao().findAll(sep, pageable);
		return list;
	}

	@Override
	public void saveOrUpdateEmpInfo(EmplyInfoPO emplyInfoPO) {
		List<EmplyInfoPO> list = getDao().findByEmplyid(emplyInfoPO.getEmplyid());
		if(!list.isEmpty()){  //已经存在的员工，为更新操作,删除关联工作经历
			emplyexpercePODao.deleteByEmplyid(emplyInfoPO.getEmplyid());
		}
		if(emplyInfoPO.getGid()==null||"".equals(emplyInfoPO.getGid())){
			emplyInfoPO.setGid(UuidUtil.buildRandomUUID().toString());
		}
		EmplyInfoPO v = this.getDao().save(emplyInfoPO);
		for(EmplyexpercePO emplyexpercePO:emplyInfoPO.getEmplyexperce()){
			emplyexpercePO.setGid(UuidUtil.buildRandomUUID().toString());
			emplyexpercePO.setEmplyid(emplyInfoPO.getEmplyid());
			emplyexpercePODao.save(emplyexpercePO);
		}
	}

	@Override
	public List<EmplyInfoPO> findById(String id) {
		List<EmplyInfoPO> list = this.getDao().findByEmplyid(id);
		return list;
	}


	@Override
	public void deleteEmpInfo(EmplyInfoPO emplyInfoPO) {
		//删除员工信息
		this.getDao().delete(emplyInfoPO.getGid());
		//删除工作经历
		emplyexpercePODao.deleteByEmplyid(emplyInfoPO.getEmplyid());

	}


	private Specification<EmplyInfoPO> getSupplierPaySummarySpection(Map<String, Object> example) {
		return (Specification<EmplyInfoPO>) (root, query, cb) -> {
			return cb.and(generateSpecWhere(example, root, cb));
		};
	}


	private Predicate[] generateSpecWhere(Map<String, Object> example, Root<EmplyInfoPO> root, CriteriaBuilder cb) {
		List<Predicate> list = new ArrayList<>();
		final Map<String, String> attributeMap = new HashMap<>();
		root.getModel().getAttributes().forEach(e -> attributeMap.put(e.getName(), ""));
		for (Map.Entry<String, Object> entry : example.entrySet()) {
			String name = entry.getKey();
			Object value = entry.getValue();
			if (StringUtils.equals("age", name) && !"".equals(value)) {
				list.add(cb.equal(root.get("age"), value));
			} else if (StringUtils.equals("emplyname", name) && !"".equals(value)) {
				list.add(cb.equal(root.get("emplyname"), value));
			} else if (StringUtils.equals("sex", name) && !"".equals(value)) {
				list.add(cb.equal(root.get("sex"), value));
			} else if (StringUtils.equals("emplyid", name) && !"".equals(value)) {
				list.add(cb.equal(root.get("sex"), value));
			} else {
				if (attributeMap.containsKey(name)) {
					//list.add(cb.like(root.get(name), "%" + value.toString().trim() + "%"));
				}
			}
		}
		Predicate[] p = new Predicate[list.size()];

		return list.toArray(p);
	};
}
