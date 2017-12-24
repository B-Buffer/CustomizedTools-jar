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
package com.customized.tools.dbtester;

import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.customized.tools.AbstractTools;

public class DBConnectionTester extends AbstractTools {
	
	private static final String TABLE_CREATE = "create table tool_dbconn_kylin_test(id int)";
	private static final String TABLE_INSERT = "insert into tool_dbconn_kylin_test values(100)";
	private static final String TABLE_DROP = "drop table tool_dbconn_kylin_test";
	
	private DBTester dbTester;
	
	private PrintStream out;
    
    private PrintStream err;
	
	public DBConnectionTester(DBTester dbTester, PrintStream out, PrintStream err) {
		this.dbTester = dbTester ;
		this.out = out ;
		this.err = err ;
	}

	public void execute() {

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {	
		    out.println("DBConnectionTester Properties: " +  dbTester);
						
			conn = JDBCUtil.getConnection(dbTester.getDriver(), dbTester.getUrl(), dbTester.getUsername(), dbTester.getPassword());
			
			promptConnectionResult(conn);
			
			stmt = conn.createStatement();
			stmt.executeUpdate(TABLE_CREATE);
			out.println("Create Table Success" + LN);
			
			stmt.execute(TABLE_INSERT);
			out.println("Insert Success" + LN);
			
			stmt.execute(TABLE_DROP);
			out.println("Drop Table Success" + LN);
			
		} catch (Throwable e) {
			DBConnectionTesterException ex = new DBConnectionTesterException("DBConnectionTester met exception", e);
			err.println("Test Failed, due to " + ex.getMessage());
		}finally {
		    JDBCUtil.close(rs, stmt, conn);
		}
	}

	private void promptConnectionResult(Connection conn) throws SQLException {

		DatabaseMetaData meta = conn.getMetaData();
		
		StringBuffer sb = new StringBuffer();
		sb.append("Create Databse Connection [" + dbTester.getUrl() + " - " + dbTester.getUsername() + "/******]" + LN2);
		sb.append(TAB + "Databse Connection Test Success" + LN2);
		sb.append(TAB + meta.getDatabaseProductVersion());
		
		out.println(sb.toString());
				
	}
	
}
