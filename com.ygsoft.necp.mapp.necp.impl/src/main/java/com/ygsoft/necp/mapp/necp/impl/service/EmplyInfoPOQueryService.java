/*
 * Copyright (C) 2005 - 2030 YGSoft.Inc All Rights Reserved.
 * YGSoft.Inc PROPRIETARY/CONFIDENTIAL.Use is subject to license terms.
 */
package com.ygsoft.necp.mapp.necp.impl.service;

import com.alibaba.fastjson.JSON;
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
import java.util.List;
import java.util.Map;

/**
 * EmplyInfoPOQueryService查询服务类.<br>
 *
 * @author zhangying14@ygsoft.com <br>
 * @version 1.0.0 2020-09-12 12:32:17<br>
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
		Page<EmplyInfoPO> page=this.getContext().queryByConditionList(example,pageRequest);
		return page;
	}



	@EcpPostMapping({"/saveOrUpdateEmpInfo"})
	public Map<String,Object> saveOrUpdateEmpInfo(@RequestBody EmplyInfoPO emplyInfoPO)  {
		Map<String,Object>map =new HashMap<>();
		//首先员工id是否重复
		Boolean flag = this.getContext().checkEmpInfo(emplyInfoPO);
		if(flag==true){
			//首先查询是否是新增还是修改
			try{
				String gid = this.getContext().saveOrUpdateEmpInfo(emplyInfoPO);
				map.put("flag",true);
				map.put("gid",gid);
			}catch (Exception e){
				map.put("flag",false);
				map.put("msg","接口出错");
			}
		}else{
			map.put("flag",false);
			map.put("msg","员工id不可重复");
		}

		return map;
	}

	@EcpPostMapping({"/findByEmpId"})
	public List<EmplyInfoPO> findById(@RequestBody EmplyInfoPO emplyInfoPO)  {
		//首先查询是否是新增还是修改
		List<EmplyInfoPO> list = this.getContext().findById(emplyInfoPO.getEmplyid());
		return list;
	}

	/**
	 * 删除员工信息及工作经历
	 * @param emplyInfoPO
	 * @return
	 */
	@EcpPostMapping({"/delete"})
	public void delete(@RequestBody EmplyInfoPO emplyInfoPO)  {
		this.getContext().deleteEmpInfo(emplyInfoPO);
	}

}
