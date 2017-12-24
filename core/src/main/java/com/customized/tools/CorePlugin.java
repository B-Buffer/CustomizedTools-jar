/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.customized.tools;

import java.util.ResourceBundle;

import com.customized.tools.util.BundleUtil;

public class CorePlugin {
	
	public static final String PLUGIN_ID = CorePlugin.class.getPackage().getName();
	
	public static final BundleUtil Util = new BundleUtil(PLUGIN_ID, PLUGIN_ID + ".i18n", ResourceBundle.getBundle(PLUGIN_ID + ".i18n"));
	
	public static enum Event implements BundleUtil.Event {
		CSTCORE1000,
		CSTCORE1001,
		CSTCORE1002,
		CSTCORE1003,
		CSTCORE1004,
		CSTCORE1005,
		CSTCORE1006,
		CSTCORE1007,
		CSTCORE1008,
		CSTCORE1009,
	}

}
