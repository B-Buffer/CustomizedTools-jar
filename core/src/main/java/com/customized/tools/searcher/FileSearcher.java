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

package com.customized.tools.searcher;

import java.io.File;
import java.io.PrintStream;
import java.util.List;

import com.customized.tools.AbstractTools;

public class FileSearcher extends AbstractTools {
		
	private Searcher fileSearcher ;
	
	private PrintStream out;
    
    private PrintStream err;
    
    public FileSearcher(Searcher fileSearcher, PrintStream out, PrintStream err) {
        this.fileSearcher = fileSearcher ;
        this.out = out ;
        this.err = err ;
    }
	

	public void execute() {
		

		try {
			String searchName = fileSearcher.getFileName();
			String searchFolder = fileSearcher.getFolderPath();
			
			File parentfile = new File(searchFolder);
			
			if(!parentfile.exists() || !parentfile.isDirectory() || searchName.length() == 0) {
			    err.println(new FileSearcherException("Search folder not exist.").getMessage());
				return;
			}
			
			if( searchName.length() == 0 || searchName.trim().length() == 0) {
			    err.println(new FileSearcherException("Error search file name format").getMessage());
				return;
			}
			
			out.println("FileSearcher start, searching file '" + searchName + "' under " + searchFolder + LN);
			
			List<String> result = new SearcherImpl(searchName, searchFolder, out, err).search();
			
			String propmtStr = "File '" + searchName + "' be found " + result.size() + " times:";
			out.println(propmtStr);
			
			for(int i = 0 ; i < result.size() ; i ++) {
			    out.println(TAB2 + result.get(i));
			}
			
			//TODO-- add dump propmtStr, result to file
		} catch (Throwable e) {
			FileSearcherException ex = new FileSearcherException("File Searcher return a Exception" ,e);
			err.println(ex.getMessage());
		}
	}

}
