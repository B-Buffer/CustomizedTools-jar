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

import java.io.File;
import java.io.InputStream;

import org.jboss.aesh.console.AeshConsole;
import org.jboss.aesh.console.AeshConsoleBuilder;
import org.jboss.aesh.console.Prompt;
import org.jboss.aesh.console.command.registry.AeshCommandRegistryBuilder;
import org.jboss.aesh.console.command.registry.CommandRegistry;
import org.jboss.aesh.console.helper.ManProvider;
import org.jboss.aesh.console.settings.Settings;
import org.jboss.aesh.console.settings.SettingsBuilder;
import org.jboss.aesh.terminal.Color;
import org.jboss.aesh.terminal.TerminalColor;
import org.jboss.aesh.terminal.TerminalString;

import com.customized.tools.commands.AuthUtilCommand;
import com.customized.tools.commands.ConverterCommand;
import com.customized.tools.commands.DBTesterCommand;
import com.customized.tools.commands.ExitCommand;
import com.customized.tools.commands.FileChangeMonitorCommand;
import com.customized.tools.commands.FileSearcherCommand;
import com.customized.tools.commands.GCViewerWrapperCommand;
import com.customized.tools.commands.JVMConfigCommand;
import com.customized.tools.commands.JarClassSearcherCommand;
import com.customized.tools.commands.LSCommand;
import com.customized.tools.commands.SQLPlusCommand;

public class AeshContainer implements LifeCycle {
	
	
	private String rootNode = File.separator;
	
	private Status status = Status.STOP ;

	@Override
	public void doInit() {
		setStatus(Status.INIT);
	}

	@Override
	public void doStart() {
		
		if(status == Status.DESTORY || status == Status.STOP) {
			doInit();
		}
				
		AeshConsole console = buildAeshConsole();
		
//		console.getShell().println(Version.VERSION_STRING + " Started");
		
		setStatus(Status.START);
		
		console.start();
		
	}

	private AeshConsole buildAeshConsole() {
		
		Settings settings = new SettingsBuilder().logging(true)
				.enableMan(true)
				.readInputrc(false)
				.create();
		
		CommandRegistry registry = new AeshCommandRegistryBuilder()
//				.command(Exit.class)
//				.command(Ls.class)
//				.command(Matrix.class)
				.command(ExitCommand.class)
				.command(JarClassSearcherCommand.class)
				.command(FileSearcherCommand.class)
				.command(FileChangeMonitorCommand.class)
				.command(GCViewerWrapperCommand.class)
				.command(AuthUtilCommand.class)
				.command(DBTesterCommand.class)
				.command(JVMConfigCommand.class)
				.command(LSCommand.class)
				.command(SQLPlusCommand.class)
				.command(ConverterCommand.class)
				.create();
		
		AeshConsole aeshConsole = new AeshConsoleBuilder()
				.commandRegistry(registry)
				.manProvider(new ManProviderTools())
				.settings(settings)
				.prompt(buildRootPrompt(rootNode))
                .create();

		return aeshConsole;
	}
	
	public String getRootNode() {
		return rootNode;
	}

	public void setRootNode(String rootNode) {
		this.rootNode = rootNode;
	}

	protected Prompt buildRootPrompt(String name) {
		String head = "[CustomizedTools ";
		String tail = "]";
		String chars = head + name + tail;
		TerminalColor color = new TerminalColor(Color.GREEN, Color.DEFAULT, Color.Intensity.BRIGHT);
		TerminalString terminal = new TerminalString(chars, color);
		return new Prompt(terminal);
	}

	@Override
	public void doStop() {
		setStatus(Status.STOP);
	}

	@Override
	public void status() {
		
	}
	
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public void destory() {
		

		if(status == Status.START || status == Status.INIT) {
			doStop();
		}
		
	}
	
	private class ManProviderTools implements ManProvider {

		@Override
		public InputStream getManualDocument(String commandName) {
			return null;
		}
		
	}

}
