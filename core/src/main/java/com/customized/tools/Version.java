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

public class Version {

	public static final String V_1 = "1.0";
	
	public static final String V_2 = "2.0";
	
	public static final String V_3 = "3.0";
	
	public static final String V_CURRENT = V_3 ;
	
	public static final String NAME = "CustomizedTools";
	
	public static final String VERSION_STRING = NAME + " 'KylinSoong' " + V_CURRENT ;
	
	public static String version() {
		return V_CURRENT ;
	}
	
	public static String name() {
		return NAME ;
	}
	
	public static String versionString() {
		return VERSION_STRING ;
	}
}
