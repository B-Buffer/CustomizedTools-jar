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
package com.customized.tools.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.customized.tools.Version;

@XmlRootElement/*(namespace = "urn:cst:1.0")*/
@XmlType(propOrder = { "configuration", "profile" })
public class CustomizedToolsContext {
	
	private String version = Version.version();
	
	private String name = Version.name();
	
	private ToolsConfiguration configuration ;
	
	private ToolsProfile profile ;
	
	
	@XmlAttribute(name = "version")
	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	@XmlAttribute(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlElement
	public ToolsConfiguration getConfiguration() {
		return configuration;
	}

	public void setConfiguration(ToolsConfiguration configuration) {
		this.configuration = configuration;
	}

	@XmlElement
	public ToolsProfile getProfile() {
		return profile;
	}

	public void setProfile(ToolsProfile profile) {
		this.profile = profile;
	}


}
