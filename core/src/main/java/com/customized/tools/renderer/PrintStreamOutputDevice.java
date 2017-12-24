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
package com.customized.tools.renderer;

import java.io.PrintStream;

public class PrintStreamOutputDevice implements OutputDevice {
	
	private final PrintStream out;

	public PrintStreamOutputDevice(PrintStream out) {
		super();
		this.out = out;
	}

	@Override
	public void flush() {
		out.flush();
	}

	@Override
	public void write(byte[] buf, int off, int len) {
		out.write(buf, off, len);
	}

	@Override
	public void print(String s) {
		out.print(s);
	}

	@Override
	public void println(String s) {
		out.println(s);
	}

	@Override
	public void println() {
		out.println();
	}

	@Override
	public void attributeBold() {
		
	}

	@Override
	public void attributeReset() {
		
	}

	@Override
	public void attributeGrey() {
		
	}

	@Override
	public void close() {
		out.close();
	}

	@Override
	public boolean isTerminal() {
		return false;
	}

}
