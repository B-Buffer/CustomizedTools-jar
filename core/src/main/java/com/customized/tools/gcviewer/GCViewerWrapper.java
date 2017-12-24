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
package com.customized.tools.gcviewer;

import java.io.PrintStream;

import com.customized.tools.AbstractTools;
import com.customized.tools.gcviewer.GCViewerException;
import com.tagtraum.perf.gcviewer.GCViewer;

public class GCViewerWrapper extends AbstractTools {
    
    private GCViewerEntity gcViwer;
			
	private PrintStream out;
    
    private PrintStream err;

	public GCViewerWrapper(GCViewerEntity gcViwer, PrintStream out, PrintStream err) {
		this.gcViwer = gcViwer;
		this.out = out;
		this.err = err;
	}

		
	public void execute() {
		
		out.println("GCViewWrapper start GCViewer");
			
		try {
		    String[] args = new String[]{gcViwer.getPath(), gcViwer.getName()};
		    
		    GCViewer.main(args);
		} catch (Throwable e) {
			GCViewerException ex = new GCViewerException("", e);
			err.println("GCViewer Return a Error, " + ex.getMessage());
		}
	}

}
