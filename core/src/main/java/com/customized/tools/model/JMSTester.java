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

import javax.naming.Context;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.customized.tools.Entity;

@XmlRootElement(name = "jmsTester")
@XmlType(propOrder = { "factoryJNDIName", "factoryClassName", "url", "pkgs", "principle", "credentials"})
public class JMSTester extends Entity {

	private static final long serialVersionUID = 3652913632249760108L;

	private String factoryJNDIName;
	
	private String factoryClassName;
	
	private String url;
	
	private String pkgs;
	
	private String principle;
	
	private String credentials;
	
	@XmlElement(name = "factoryJNDIName")
	public String getFactoryJNDIName() {
		return factoryJNDIName;
	}

	public void setFactoryJNDIName(String factoryJNDIName) {
		this.factoryJNDIName = factoryJNDIName;
	}

	@XmlElement(name = "factoryClassName")
	public String getFactoryClassName() {
		return factoryClassName;
	}

	public void setFactoryClassName(String factoryClassName) {
		this.factoryClassName = factoryClassName;
	}

	@XmlElement(name = "url")
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@XmlElement(name = "pkgs")
	public String getPkgs() {
		return pkgs;
	}

	public void setPkgs(String pkgs) {
		this.pkgs = pkgs;
	}

	@XmlElement(name = "principle")
	public String getPrinciple() {
		return principle;
	}

	public void setPrinciple(String principle) {
		this.principle = principle;
	}

	@XmlElement(name = "credentials")
	public String getCredentials() {
		return credentials;
	}

	public void setCredentials(String credentials) {
		this.credentials = credentials;
	}

	@Override
	public String toString() {
		
		StringBuffer sb = new StringBuffer();
		sb.append("        " + Context.INITIAL_CONTEXT_FACTORY + ": " + factoryClassName + "\n");
		sb.append("        " + Context.PROVIDER_URL + ": " + url + "\n");
		sb.append("        " + Context.URL_PKG_PREFIXES + ": " + pkgs + "\n");
		sb.append("        " + Context.SECURITY_PRINCIPAL + ": " + principle + "\n");
		sb.append("        " + Context.SECURITY_CREDENTIALS + ": " + credentials);
		
		return sb.toString();
	}
}
