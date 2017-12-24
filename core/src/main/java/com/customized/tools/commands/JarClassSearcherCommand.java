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

import com.customized.tools.commands.Validator.DirectoryValidator;
import com.customized.tools.commands.Validator.InputNotNullValidator;
import com.customized.tools.jarClassSearcher.ClassSearcher;
import com.customized.tools.jarClassSearcher.JarClassSearcher;

@CommandDefinition(name="searcher", description = "[-p] ... [-n] ... \nSearch class from jar file")
public class JarClassSearcherCommand implements Command<CommandInvocation>{
	
	@Option(shortName = 'H', name = "help", hasValue = false,
            description = "display this help and exit")
    private boolean help;
	
	@Option(shortName = 'p',
			name = "folderPath", 
			description = "jarClassSearcher folder path", 
			defaultValue = {"/home/kylin/server/jboss-eap-6.3"},
			validator = DirectoryValidator.class)
	private String folderPath;
	
	@Option(shortName = 'n',
			name = "className", 
			description = "jarClassSearcher class name",
			defaultValue = {"org.jboss.modules.Main"},
			validator = InputNotNullValidator.class)
	private String className;
	
	@Override
	public CommandResult execute(CommandInvocation commandInvocation) throws CommandException, InterruptedException {
		
		if(help) {
            commandInvocation.getShell().out().println(commandInvocation.getHelpInfo("searcher"));
            return CommandResult.SUCCESS;
        }

		
		ClassSearcher entity = new ClassSearcher();
		entity.setFolderPath(folderPath);
		entity.setClassName(className);
				
		new JarClassSearcher(entity, commandInvocation.getShell().out(), commandInvocation.getShell().err()).execute();
		
		return CommandResult.SUCCESS;
	}

}
