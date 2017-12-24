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

import java.sql.Connection;
import java.sql.SQLException;

import org.jboss.aesh.cl.CommandDefinition;
import org.jboss.aesh.cl.Option;
import org.jboss.aesh.console.command.Command;
import org.jboss.aesh.console.command.CommandException;
import org.jboss.aesh.console.command.CommandResult;
import org.jboss.aesh.console.command.invocation.CommandInvocation;

import com.customized.tools.commands.Validator.DBDriverValidator;
import com.customized.tools.commands.Validator.DBURLValidator;
import com.customized.tools.dbtester.JDBCUtil;

@CommandDefinition(name="sqlPlus", description = "A SQLPlus for ")
public class SQLPlusCommand implements Command<CommandInvocation> {
	
	@Option(shortName = 'H', name = "help", hasValue = false,
            description = "display this help and exit")
    private boolean help;
	
	@Option(shortName = 'd',
			name = "driver",
			description = "DB Driver Class",
			required = true,
			defaultValue = {"org.h2.Driver", "com.mysql.jdbc.Driver", "org.postgresql.Driver"},
			validator = DBDriverValidator.class)
	private String driverClass;
	
	@Option(shortName = 'c',
			name = "url",
			description = "DB Connection URL",
			required = true,
			defaultValue = {"jdbc:mysql://localhost:3306/test", "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1"},
			validator = DBURLValidator.class)
	private String connURL;
	
	@Option(shortName = 'u',
			name = "user",
			description = "DB User",
			required = true,
			defaultValue = {"test_user", "sa"})
	private String user;
	
	@Option(shortName = 'p',
			name = "password",
			description = "DB Password",
			required = true,
			defaultValue = {"test_pass", "sa"})
	private String password;

	@Override
	public CommandResult execute(CommandInvocation commandInvocation) throws CommandException, InterruptedException{
		
		if(help) {
            commandInvocation.getShell().out().println(commandInvocation.getHelpInfo("sqlPlus"));
            return CommandResult.SUCCESS;
        }
		
		commandInvocation.getShell().out().print("SQLPlus>");
		commandInvocation.getShell().out().println(commandInvocation.getInputLine());
		
		try {
			Connection conn = JDBCUtil.getConnection(driverClass, connURL, user, password);
			while(true){
				commandInvocation.getShell().out().print("SQLPlus>");
				String input = commandInvocation.getInputLine();
				if(input.equals("exit")){
					break;
				}
				commandInvocation.getShell().out().println(input);
			}
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			commandInvocation.getShell().out().println(e);
		}
		return CommandResult.SUCCESS;
	}

}
