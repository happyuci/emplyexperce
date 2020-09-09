/*
 * Copyright (C) 2005 - 2030 YGSoft.Inc All Rights Reserved.
 * YGSoft.Inc PROPRIETARY/CONFIDENTIAL.Use is subject to license terms.
 */
package com.ygsoft.necp.mapp.necp.impl.service;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ygsoft.necp.core.service.dcispec.GeneralRestService;

import com.ygsoft.necp.mapp.necp.service.domain.model.EmplyexpercePO;
import com.ygsoft.necp.mapp.necp.service.service.IEmplyexpercePOService;
import com.ygsoft.necp.mapp.necp.service.context.IEmplyexpercePOContext;

/**
 * EmplyexpercePOService服务类.<br>
 *
 * @author zhangying14@ygsoft.com <br>
 * @version 1.0.0 2020-09-06 19:32:16<br>
 * @since JDK 1.8.0_152
 */
@RestController
@RequestMapping("/necp/mapp/emplyexperce/service/emplyexpercePO")
public class EmplyexpercePOService extends GeneralRestService<EmplyexpercePO, String, IEmplyexpercePOContext>
		implements IEmplyexpercePOService {
}
