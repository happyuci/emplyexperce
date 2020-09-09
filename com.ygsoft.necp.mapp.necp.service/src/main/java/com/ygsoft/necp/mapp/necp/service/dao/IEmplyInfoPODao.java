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
 * @version 1.0.0 2020-09-06 19:32:17<br>
 * @since JDK 1.8.0_152
 */
@Repository
public interface IEmplyInfoPODao extends IGeneralGidCompleteDao<EmplyInfoPO, String> {

/*
    @Query(value = "SELECT * FROM empdetailinfo WHERE if(?1 !='',name=?1,1=1)  " +
            "and if(?2 !='',age=?2,1=1) and if(?2 !='',birth=?2,1=1) and if(?2 !='',sex=?2,1=1) ",
            countQuery = "SELECT count(*) dw_nb_fin_t_amso_dm_amso_evaluate_score WHERE if(?1 !='',sap_org_code=?1,1=1)  " +
                    "  and if(?2 !='',amso_code=?2,1=1) and  fyear=?3 and fmonth= ?4",
            nativeQuery = true)
    Page<EmplyexpercePO> findByCondition(String name, String age, String birth, String sex);*/

    List<EmplyInfoPO> findByEmplyid(String id);

}
