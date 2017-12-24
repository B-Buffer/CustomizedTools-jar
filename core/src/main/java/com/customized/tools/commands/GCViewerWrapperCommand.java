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
package com.customized.tools.commands;

import org.jboss.aesh.cl.CommandDefinition;
import org.jboss.aesh.cl.Option;
import org.jboss.aesh.console.command.Command;
import org.jboss.aesh.console.command.CommandException;
import org.jboss.aesh.console.command.CommandResult;
import org.jboss.aesh.console.command.invocation.CommandInvocation;

import com.customized.tools.commands.Validator.FileValidator;
import com.customized.tools.commands.Validator.InputNotNullValidator;
import com.customized.tools.gcviewer.GCViewerEntity;
import com.customized.tools.gcviewer.GCViewerWrapper;

@CommandDefinition(name="gcViewer", description = "JVM Garbage Collection Log Analyzer")
public class GCViewerWrapperCommand implements Command<CommandInvocation> {
	
	@Option(shortName = 'H', name = "help", hasValue = false,
            description = "display this help and exit")
    private boolean help;
	
	@Option(shortName = 'p',
			name = "filePath", 
			description = "GCViewer gc log file path", 
			defaultValue = {"gc.log"},
			validator = FileValidator.class)
	private String filePath;
	
	@Option(shortName = 'n',
			name = "resultFile", 
			description = "GCViewer result file name",
			defaultValue = {"export.csv"},
			validator = InputNotNullValidator.class)
	private String resultFile;
	
	@Override
	public CommandResult execute(CommandInvocation commandInvocation) throws CommandException, InterruptedException {
		
		if(help) {
            commandInvocation.getShell().out().println(commandInvocation.getHelpInfo("gcViewer"));
            return CommandResult.SUCCESS;
        }
		
		GCViewerEntity entity = new GCViewerEntity();
		entity.setPath(filePath);
		entity.setName(resultFile);
				
		new GCViewerWrapper(entity, commandInvocation.getShell().out(), commandInvocation.getShell().err()).execute();
		
		return CommandResult.SUCCESS;
	}

}
