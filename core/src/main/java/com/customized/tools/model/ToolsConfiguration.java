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

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.customized.tools.dbtester.DBTester;
import com.customized.tools.filechangemonitor.Monitor;
import com.customized.tools.gcviewer.GCViewerEntity;
import com.customized.tools.jarClassSearcher.ClassSearcher;
import com.customized.tools.searcher.Searcher;

@XmlRootElement(name = "configuration")
public class ToolsConfiguration {

	private GCViewerEntity gcViewer;
	
	private TDAEntity tda;
	
	private DBTester dbTester;
	
	private ClassSearcher jarClassSearcher;
	
	private Searcher fileSearcher;
	
	private Monitor fileChangeMonitor;

	@XmlElement(name = "gcViewer")
	public GCViewerEntity getGcViewer() {
		return gcViewer;
	}

	public void setGcViewer(GCViewerEntity gcViewer) {
		this.gcViewer = gcViewer;
	}
	
	@XmlElement(name = "tda")
	public TDAEntity getTda() {
		return tda;
	}

	public void setTda(TDAEntity tda) {
		this.tda = tda;
	}

	@XmlElement(name = "dbTester")
	public DBTester getDbTester() {
		return dbTester;
	}

	public void setDbTester(DBTester dbTester) {
		this.dbTester = dbTester;
	}

	@XmlElement(name = "jarClassSearcher")
	public ClassSearcher getJarClassSearcher() {
		return jarClassSearcher;
	}

	public void setJarClassSearcher(ClassSearcher jarClassSearcher) {
		this.jarClassSearcher = jarClassSearcher;
	}

	@XmlElement(name = "fileSearcher")
	public Searcher getFileSearcher() {
		return fileSearcher;
	}

	public void setFileSearcher(Searcher fileSearcher) {
		this.fileSearcher = fileSearcher;
	}

	@XmlElement(name = "fileChangeMonitor")
	public Monitor getFileChangeMonitor() {
		return fileChangeMonitor;
	}

	public void setFileChangeMonitor(Monitor fileChangeMonitor) {
		this.fileChangeMonitor = fileChangeMonitor;
	}
}
