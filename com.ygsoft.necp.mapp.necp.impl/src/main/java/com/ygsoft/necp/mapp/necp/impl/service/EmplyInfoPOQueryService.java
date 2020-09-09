/*
 * Copyright (C) 2005 - 2030 YGSoft.Inc All Rights Reserved.
 * YGSoft.Inc PROPRIETARY/CONFIDENTIAL.Use is subject to license terms.
 */
package com.ygsoft.necp.mapp.necp.impl.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ygsoft.necp.core.service.dcispec.EcpPostMapping;
import com.ygsoft.necp.core.service.dcispec.GeneralQueryRestService;
import com.ygsoft.necp.mapp.necp.impl.context.Condition;
import com.ygsoft.necp.mapp.necp.service.context.IEmplyInfoPOContext;
import com.ygsoft.necp.mapp.necp.service.domain.model.EmplyInfoPO;
import com.ygsoft.necp.mapp.necp.service.service.IEmplyInfoPOQueryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * EmplyInfoPOQueryService查询服务类.<br>
 *
 * @author zhangying14@ygsoft.com <br>
 * @version 1.0.0 2020-09-06 19:32:17<br>
 * @since JDK 1.8.0_152
 */
@RestController
@RequestMapping("/necp/mapp/emplyexperce/query/emplyInfoPO")
public class EmplyInfoPOQueryService extends GeneralQueryRestService<EmplyInfoPO, String, IEmplyInfoPOContext>
		implements IEmplyInfoPOQueryService {
	@PostMapping("/query")
	public Page<EmplyInfoPO> queryEmplyInfoPO(@RequestBody Condition requestMap) {
		final int pageSize = requestMap.getPageSize();
		final int pageNum =requestMap.getPageNum();
		Map<String, Object> example = JSON.parseObject(requestMap.getExample(), HashMap.class);
		Pageable pageRequest=new PageRequest(pageNum, pageSize);
		JSONObject resp=new JSONObject();
		Page<EmplyInfoPO> page=this.getContext().queryByConditionList(example,pageRequest);
		return page;
	}

	@EcpPostMapping({"/saveOrUpdateEmpInfo"})
	public String saveOrUpdateEmpInfo(@RequestBody EmplyInfoPO emplyInfoPO)  {
		//首先查询是否是新增还是修改
		this.getContext().saveOrUpdateEmpInfo(emplyInfoPO);
		return null;
	}
}
