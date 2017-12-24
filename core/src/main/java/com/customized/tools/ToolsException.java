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

public class ToolsException extends RuntimeException {

	private static final long serialVersionUID = -5243883308591516193L;

	public String errorCode;

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public ToolsException(String errorCode) {
		super();
		this.errorCode = errorCode;
	}
	
	public ToolsException(String errorCode, String msg) {
		super(msg);
		this.errorCode = errorCode;
	}
	
	public ToolsException(String errorCode, String msg, Throwable t) {
		super(msg, t);
		this.errorCode = errorCode;
	}
	
	public String getMessage() {
		Throwable cause = getCause();
		String msg = "[" + getErrorCode() + "] - " + super.getMessage();
		if (null != cause && null != cause.getMessage()) {
			msg += " (" + cause.getMessage() + ")";
		}
		return msg;
	}

	
	public String getErrorCode() {
		return errorCode;
	}

}
