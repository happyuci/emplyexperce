/*
 * Copyright (C) 2005 - 2030 YGSoft.Inc All Rights Reserved.
 * YGSoft.Inc PROPRIETARY/CONFIDENTIAL.Use is subject to license terms.
 */
package com.ygsoft.necp.mapp.necp.service.dao;

import com.ygsoft.necp.core.service.dataaccess.IGeneralGidCompleteDao;
import com.ygsoft.necp.mapp.necp.service.domain.model.EmplyInfoPO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * IEmplyInfoPODao数据访问类.<br>
 *
 * @author zhangying14@ygsoft.com <br>
 * @version 1.0.0 2020-09-12 12:32:17<br>
 * @since JDK 1.8.0_152
 */
@Repository
public interface IEmplyInfoPODao extends IGeneralGidCompleteDao<EmplyInfoPO, String> {

    List<EmplyInfoPO> findByEmplyid(String id);

}
