/*
 * Copyright (C) 2005 - 2030 YGSoft.Inc All Rights Reserved.
 * YGSoft.Inc PROPRIETARY/CONFIDENTIAL.Use is subject to license terms.
 */
package com.ygsoft.necp.mapp.necp.impl.context;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.ygsoft.necp.core.service.dcispec.GeneralContext;

import com.ygsoft.necp.mapp.necp.service.domain.model.EmplyexpercePO;
import com.ygsoft.necp.mapp.necp.service.dao.IEmplyexpercePODao;
import com.ygsoft.necp.mapp.necp.service.context.IEmplyexpercePOContext;

import com.ygsoft.necp.component.genentity.context.EntityContext;
import com.ygsoft.necp.core.service.dcispec.annotation.EventSource;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * EmplyexpercePO场景类.<br>
 *
 * @author zhangying14@ygsoft.com <br>
 * @version 1.0.0 2020-09-06 19:32:16<br>
 * @since JDK 1.8.0_152
 */
@Service
@Transactional
@EventSource
@EntityContext(classId = "empdetailinfo", typeId = "emplyexpercepo")
public class EmplyexpercePOContext extends GeneralContext<EmplyexpercePO, String, IEmplyexpercePODao>
		implements IEmplyexpercePOContext {
		
	/**
	 * 构造函数.
	 */
	public EmplyexpercePOContext() {
		super();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see com.ygsoft.necp.core.service.dcispec.GeneralContext#newModel()
	 */
	@Override
	protected EmplyexpercePO newModel() {
		return new EmplyexpercePO();
	}


	@Override
	public Page<EmplyexpercePO> queryByConditionList(Map<String, Object> map, Pageable pageable) {
		Specification<EmplyexpercePO> sep = getSupplierPaySummarySpection(map);
		Page<EmplyexpercePO> list = this.getDao().findAll(sep, pageable);
		return list;
	}

	private Specification<EmplyexpercePO> getSupplierPaySummarySpection(Map<String, Object> example) {
		return (Specification<EmplyexpercePO>) (root, query, cb) -> {
			return cb.and(generateSpecWhere(example, root, cb));
		};
	}


	private Predicate[] generateSpecWhere(Map<String, Object> example, Root<EmplyexpercePO> root, CriteriaBuilder cb) {
		List<Predicate> list = new ArrayList<>();
		final Map<String, String> attributeMap = new HashMap<>();
		root.getModel().getAttributes().forEach(e -> attributeMap.put(e.getName(), ""));
		for (Map.Entry<String, Object> entry : example.entrySet()) {
			String name = entry.getKey();
			Object value = entry.getValue();
			if (StringUtils.equals("age", name) && !"".equals(value)) {
				list.add(cb.equal(root.get("age"), value));
			} else if (StringUtils.equals("name", name) && !"".equals(value)) {
				list.add(cb.equal(root.get("name"), value));
			} else if (StringUtils.equals("sex", name) && !"".equals(value)) {
				//list.add(cb.equal(root.get("sex"), value));
			} else {
                if (attributeMap.containsKey(name)) {
                    list.add(cb.like(root.get(name), "%" + value.toString().trim() + "%"));
                }
           }
		}
		Predicate[] p = new Predicate[list.size()];

		return list.toArray(p);
	};
}
