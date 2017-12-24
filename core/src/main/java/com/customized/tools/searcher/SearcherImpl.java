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
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

public class SearcherImpl {
	
	private String searchName ;
	
	private String searchFolder;
	
	private PrintStream out;
	
	private PrintStream err;

	public SearcherImpl(String searchName, String searchFolder, PrintStream out, PrintStream err) {
		this.searchName = searchName;
		this.searchFolder = searchFolder;
		this.out = out;
		this.err = err;
	}

	public List<String> search() throws ZipException, IOException {
		
	    out.println("Searching " + searchName + " under " + searchFolder);
		
		List<String> result = new ArrayList<String>();
		
		search(result, new File(searchFolder), searchName);
		
		return result;
	}

	private void search(List<String> result, File file, String searchName) throws ZipException, IOException {
		
		for(File f : file.listFiles()) {
			
			if(f.getName().contains(searchName)) {
				String path = f.getAbsolutePath() ;
				result.add(path.substring(path.indexOf(searchFolder)));
			}
			
			if(f.isDirectory()) {
				search(result, f, searchName);
			}  else if(f.getName().endsWith(".zip") || f.getName().endsWith(".jar") || f.getName().endsWith(".war") || f.getName().endsWith(".ear") || f.getName().endsWith(".esb")) {
				
				ZipFile zipFile = null;
				
				try {
					zipFile = new ZipFile(f);
					String path = f.getAbsolutePath() ;
					path = path.substring(path.indexOf(searchFolder));
					traverseZipFile(result, path, zipFile, searchName);
				} catch (Exception e) {
					String prompt = "can not create zipFile via " + f ;
					err.println(prompt + ", ignored");
				}
				
			}
		}
	}

	private void traverseZipFile(List<String> result, String prefix, ZipFile zip, String searchName) {
		
		@SuppressWarnings("unchecked")
		Enumeration <ZipEntry>en = (Enumeration<ZipEntry>) zip.entries();
		
		while(en.hasMoreElements()) {
			
			ZipEntry entry = en.nextElement() ;
			
			if(entry.getName().contains(searchName)) {
				result.add(prefix + " $ " + entry.getName());
			}
		}
	}
	
}
