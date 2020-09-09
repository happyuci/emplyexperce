/*
 * Copyright (C) 2005 - 2030 YGSoft.Inc All Rights Reserved.
 * YGSoft.Inc PROPRIETARY/CONFIDENTIAL.Use is subject to license terms.
 */
package com.ygsoft.necp.mapp.necp.boot;

import com.ygsoft.ecp.core.actuator.EnableEcpActuator;
import com.ygsoft.ecp.core.boot.Application;
import com.ygsoft.ecp.core.boot.datasource.DataSource;
import com.ygsoft.ecp.core.boot.run.ApplicationBuilder;
import com.ygsoft.ecp.core.mapp.MicroService;

/**
 * 项目启动类.<br>
 *
 * @author zhangying14@ygsoft.com <br>
 * @version 1.0.0 2020-09-06 19:32:25<br>
 * @since JDK 1.8.0_152
 */
@MicroService(serviceRegisterUrl = "http://127.0.0.1:8761", contextPath = "/", port = 8080, region = "necp", name = "ygsoft.necp", vipAddress = "/necp/mapp/emplyexperce")
@DataSource
@EnableEcpActuator
public class Main extends Application {
	public static void main(String[] args) {
		new ApplicationBuilder(Main.class).run(args);
	}
}
