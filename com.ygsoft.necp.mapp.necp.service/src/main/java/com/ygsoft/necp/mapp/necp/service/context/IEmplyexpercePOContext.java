/*
 * Copyright (C) 2005 - 2030 YGSoft.Inc All Rights Reserved.
 * YGSoft.Inc PROPRIETARY/CONFIDENTIAL.Use is subject to license terms.
 */
package com.ygsoft.necp.mapp.necp.service.context;

import com.ygsoft.necp.mapp.necp.service.domain.model.EmplyexpercePO;
import com.ygsoft.necp.core.service.dcispec.IContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
 * IEmplyexpercePOContext场景接口类.<br>
 *
 * @author zhangying14@ygsoft.com <br>
 * @version 1.0.0 2020-09-06 19:32:16<br>
 * @since JDK 1.8.0_152
 */
public interface IEmplyexpercePOContext extends IContext<EmplyexpercePO, String> {

    public Page<EmplyexpercePO> queryByConditionList(Map<String,Object> map, Pageable pageable);
}
