/*
 * Copyright (C) 2005 - 2030 YGSoft.Inc All Rights Reserved.
 * YGSoft.Inc PROPRIETARY/CONFIDENTIAL.Use is subject to license terms.
 */
package com.ygsoft.necp.mapp.necp.impl.service;

import com.alibaba.fastjson.JSON;
import com.ygsoft.necp.core.service.dcispec.GeneralQueryRestService;
import com.ygsoft.necp.mapp.necp.impl.context.Condition;
import com.ygsoft.necp.mapp.necp.service.context.IEmplyexpercePOContext;
import com.ygsoft.necp.mapp.necp.service.domain.model.EmplyexpercePO;
import com.ygsoft.necp.mapp.necp.service.service.IEmplyexpercePOQueryService;
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
 * EmplyexpercePOQueryService查询服务类.<br>
 *
 * @author zhangying14@ygsoft.com <br>
 * @version 1.0.0 2020-09-06 19:32:16<br>
 * @since JDK 1.8.0_152
 */
@RestController
@RequestMapping("/necp/mapp/emplyexperce/query/emplyexpercePO")
public class EmplyexpercePOQueryService extends GeneralQueryRestService<EmplyexpercePO, String, IEmplyexpercePOContext>
		implements IEmplyexpercePOQueryService {

	@PostMapping("/query")
	public Page<EmplyexpercePO> querySupplierDesc2(@RequestBody Condition requestMap) {
		final int pageSize = requestMap.getPageSize();
		final int pageNum =requestMap.getPageNum();
		Map<String, Object> example =JSON.parseObject(requestMap.getExample(), HashMap.class);
		Pageable pageRequest=new PageRequest(pageNum, pageSize);
		Page<EmplyexpercePO> page=this.getContext().queryByConditionList(example,pageRequest);
		return page;
	}


}
