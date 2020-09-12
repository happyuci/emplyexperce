/*
 * Copyright (C) 2005 - 2030 YGSoft.Inc All Rights Reserved.
 * YGSoft.Inc PROPRIETARY/CONFIDENTIAL.Use is subject to license terms.
 */
package com.ygsoft.necp.mapp.necp.impl.action;

import org.springframework.stereotype.Component;

import com.ygsoft.ecp.core.framework.domain.AbstractBusinessAction;
import com.ygsoft.necp.mapp.necp.service.action.IEmplyInfoPOAction;
import com.ygsoft.necp.mapp.necp.service.domain.model.EmplyInfoPO;

/**
 * EmplyInfoPO动作类.<br>
 *
 * @author zhangying14@ygsoft.com <br>
 * @version 1.0.0 2020-09-12 12:32:17<br>
 * @since JDK 1.8.0_152
 */
@Component
public class EmplyInfoPOAction extends AbstractBusinessAction<EmplyInfoPO> implements IEmplyInfoPOAction {
	
	/**
	 * EmplyInfoPOAction constructor.
	 * @param newBo newBo.
	 */
	public EmplyInfoPOAction(EmplyInfoPO newBo) {
		super(newBo);
	}
	
}
