package com.noviceDeveloper;

import java.sql.*;

/**
 *
 * @author Richard Webb
 *
 * Creation of a custom random encounter table within the database
 */
public class Table implements DatabaseCommands {

    private String tableName;
    private String resetTableName;
    Encounter list = new Encounter();
    
    Table() {
        tableName = "";
    }
    //getters and setters

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public void setResetTablename(String resetTableName) {
        this.resetTableName = resetTableName;
    }

    public String getResetTableName() {
        return resetTableName;
    }

    @Override
    public void add() {

        // add a table into the database
        Statement statement = null;

        try {
            Connector conn = new Connector();
            conn.connectDatabase(conn.getPASSWORD());
            statement = conn.makeStatement();

            String sqlst = "CREATE TABLE IF NOT EXISTS " + this.getTableName() + " ("
                    + " encounterID int(10) NULL AUTO_INCREMENT, "
                    + " minDie int(10) NOT NULL, "
                    + " maxDie int(10) NOT NULL, "
                    + " creature varchar(100) NOT NULL, "
                    + " description varchar(200), "
                    + " PRIMARY KEY (encounterID) "
                    + " ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci";


            statement.execute(sqlst);

            // Close the Connections
            statement.close();
            conn.close();

        } catch (Exception e) {
            System.err.println("ERROR: Either cannot connect to the DB " + " or error with the SQL statement");
        }
    }

    @Override
    public void query() {

        // Query all records from a custom table and save them within the rs object
        // currently not using in this program, leaving previous code in

    }

    @Override
    public void update() {

        // update a table name within the database
        Statement statement = null;
        try {
            Connector conn = new Connector();
            conn.connectDatabase(conn.getPASSWORD());
            statement = conn.makeStatement();
            String sqlst = "RENAME TABLE " + this.getTableName() + ""
                    + " TO " + this.getResetTableName() + "";
            statement.executeQuery(sqlst);
            // Close the Connections
            statement.close();
            conn.close();

        } catch (Exception e) {
            System.err.println("ERROR: Either cannot connect to the DB " + " or error with the SQL statement");
        }
    }

    @Override
    public void delete() {

        // Delete a complete table
        Statement statement = null;
        try {
            Connector conn = new Connector();
            conn.connectDatabase(conn.getPASSWORD());
            statement = conn.makeStatement();
            String sqlst = "DROP TABLE " + this.getTableName();
            statement.executeUpdate(sqlst);
            // Close the Connections
            statement.close();
            conn.close();

        } catch (Exception e) {
            System.err.println("ERROR: Either cannot connect to the DB " + " or error with the SQL statement");
        }
    }
    

}
