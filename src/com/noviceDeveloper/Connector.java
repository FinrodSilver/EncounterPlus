package com.noviceDeveloper;

import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Richard Webb Database Connector for the Student Registar
 *
 */
public class Connector {
    public Connector() throws SQLException {
    }
    private Connection conn;
    private String PASSWORD = "";
    
    public Connection connectDatabase(String password) throws SQLException {
        
        if (conn == null) {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/randomencounter",
                    "root", password);
        }
        return conn;
    }

    public Statement makeStatement() throws SQLException {
        Statement st = conn.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        return st;
    }

    public int close() throws SQLException {
        conn.close();
        return 1;
    }
    
       public ArrayList tableMetaData() throws SQLException {
        String catalog = null;
        String schemaPattern = null;
        String tableNamePattern = null;
        String[] types = null;
        ArrayList tableNames = new ArrayList();
        DatabaseMetaData databaseMetaData = conn.getMetaData();
        ResultSet result = databaseMetaData.getTables (catalog, schemaPattern, tableNamePattern, types );
        
        while(result.next()) {
            String table = result.getString(3);
            tableNames.add(table);
        }
        return tableNames;
        
        
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public void setPASSWORD(String PASSWORD) {
        this.PASSWORD = PASSWORD;
    }
}
