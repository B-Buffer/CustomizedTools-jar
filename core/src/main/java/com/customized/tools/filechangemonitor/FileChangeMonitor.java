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
package com.customized.tools.filechangemonitor;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;

import com.customized.tools.AbstractTools;

public class FileChangeMonitor extends AbstractTools {
			
	private Monitor fileChangeMonitor;
	
	private PrintStream out;
    
    private PrintStream err;

	public FileChangeMonitor(Monitor fileChangeMonitor,  PrintStream out, PrintStream err) {
		this.fileChangeMonitor = fileChangeMonitor;
		this.out = out ;
        this.err = err ;
	}

	public void execute() {
	    
	    out.println(LN + "FileChangeMonitor will monitor on " + fileChangeMonitor.getFolderPath() + ", Monitor result will persist to " + fileChangeMonitor.getResultFile() + LN);
        
        try {
            reset();
            synchronized(this) {
                wait(1000);
            }
            
            final PrintWriter pw = new PrintWriter(new FileOutputStream(new File(fileChangeMonitor.getResultFile())), true);
            IFileChangeListener listener = new FileChangeListener(out, pw);
            IFileChangeHandler handler = new FileChangeHandler();
            while(true){
                listener.addListener(handler, new File(fileChangeMonitor.getFolderPath()));
                synchronized(this) {
                    wait(100);
                }
            }
        } catch (Exception e) {
            FichangeMonitorException ex = new FichangeMonitorException("FichangeMonitor Error", e);
            err.println(ex.getMessage());
        }
			
	}
	
	private void reset() throws IOException {

        if(new File(fileChangeMonitor.getResultFile()).exists()){
            new File(fileChangeMonitor.getResultFile()).delete();
        }
        
        new File(fileChangeMonitor.getResultFile()).createNewFile();
        
    }
	


}
