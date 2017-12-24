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

import com.customized.tools.commands.Validator.BooleanValidator;
import com.customized.tools.commands.Validator.IntegerValidator;
import com.customized.tools.commands.Validator.JVMCollectorValidator;
import com.customized.tools.commands.Validator.JVMVendorValidator;
import com.customized.tools.model.JVMOptions;

@CommandDefinition(name="jvmConfig", description = "JVM Options Configuration Tool")
public class JVMConfigCommand implements Command<CommandInvocation>{
	
	@Option(shortName = 'H', name = "help", hasValue = false,
            description = "display this help and exit")
    private boolean help;
	
	@Option(name = "vendor",
			description = "JVM Vendor",
			required = true,
			defaultValue = {"Oracle", "OpenJDK", "IBM"},
			validator = JVMVendorValidator.class)
	private String vendor;
	
	@Option(name = "collector",
			description = "Collector",
			required = true,
			defaultValue = {"CMS", "Parallel", "G1"},
			validator = JVMCollectorValidator.class)
	private String collector;
	
	@Option(name = "heapSize",
			description = "Heap Size (M) ",
			required = true,
			defaultValue = {"1303", "2048"},
			validator = IntegerValidator.class)
	private int heapSize;
	
	@Option(name = "enableLargePage",
			description = "Enable Large Pages",
			defaultValue = {"true", "false"},
			validator = BooleanValidator.class)
	private boolean enableLargePage;
	
	@Option(name = "enableGCLogging",
			description = "Enable GC Logging",
			defaultValue = {"true", "false"},
			validator = BooleanValidator.class)
	private boolean enableGCLogging;
	
	@Option(name = "enableAggressiveOpts",
			description = "Enable Aggressive Opts",
			defaultValue = {"true", "false"},
			validator = BooleanValidator.class)
	private boolean enableAggressiveOpts;

	@Override
	public CommandResult execute(CommandInvocation commandInvocation) throws CommandException, InterruptedException {
		
		if(help) {
            commandInvocation.getShell().out().println(commandInvocation.getHelpInfo("jvmConfig"));
            return CommandResult.SUCCESS;
        }
	
		JVMOptions opt = new JVMOptions();
		
		opt.setVendor(vendor);
		opt.setCollector(collector);
		opt.setHeapSize(heapSize);
		opt.setEnableAggressiveOpts(enableAggressiveOpts);
		opt.setEnableGCLogging(enableGCLogging);
		opt.setEnableLargePage(enableLargePage);
		
		commandInvocation.println("Reference JVM Options:");
		String prompt = "\t" + opt;
		commandInvocation.println(prompt);
		commandInvocation.println("");
		
		return CommandResult.SUCCESS;
	}

}
