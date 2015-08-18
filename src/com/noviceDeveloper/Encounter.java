package com.noviceDeveloper;

import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Richard Webb This will query , add,
 * update, and create custom encounters.
 */
public class Encounter implements DatabaseCommands {

    private int encounterID;
    private int minDie;
    private int maxDie;
    private String creature;
    private String description;
    private String TableName;
    
    // Default Constructor
    Encounter() {
        encounterID = 0;
        minDie = 0;
        maxDie = 0;
        creature = "";
        description = "";
    }

    //Database encounter add's, updates, queries, etc.
    @Override
    public void add() {
        
        // add a encounter to a custom table
        Statement statement = null;

        try {
     
            Connector conn = new Connector();
            conn.connectDatabase(conn.getPASSWORD());
            statement = conn.makeStatement();

            String sqlst = ("INSERT INTO " + this.getTableName() + " ("
                    + " minDie, maxDie, creature, description) " 
                    + " VALUES ('"
                    + this.getMinDie() + "','" 
                    + this.getMaxDie() + "','"
                    + this.getCreature() + "','" 
                    + this.getDescription() + "')");

            statement.execute(sqlst);

            // Close the Connections
            statement.close();
            conn.close();

        } catch (Exception e) {
            System.err.println("ERROR: Either cannot connect to the DB " + " or error with the SQL statement");
        }
    }

    @Override
    public void delete() {
        Statement statement = null;
        // Delete a single encounter from a Custom Table
        try {
            
            Table tableName = new Table();
            Connector conn = new Connector();
            conn.connectDatabase(conn.getPASSWORD());
            statement = conn.makeStatement();
            String sqlst = ("DELETE FROM " + tableName.getTableName() + " WHERE ID = " + this.getEncounterID() + " ");

            statement.executeUpdate(sqlst);

            //close the connections
            statement.close();
            conn.close();


        } catch (Exception e) {
            System.err.println("ERROR: Either cannot connect to the BD or error with the SQL statement");
        }
    }

    @Override
    public void update() {
        
        // Update a single encounter for a custom table
        Statement statement = null;

        try {
            
            Table tableName = new Table();
            Connector conn = new Connector();
            conn.connectDatabase(conn.getPASSWORD());
            statement = conn.makeStatement();
            String sqlst = "Update " + tableName.getTableName() + 
                    " SET ID=" + this.getEncounterID() + 
                    ", min=" + this.getMinDie() + 
                    ", max=" + this.getMaxDie() + 
                    ", creature=" + this.getCreature() +
                    ", notes=" + this.getDescription() + "";

            statement.executeUpdate(sqlst);

            // Close Connections
            statement.close();
            conn.close();

        } catch (Exception e) {
            System.err.println("ERROR: Either cannot connect to the DB " + " or error with the SQL statement");
        }

    }

    @Override
    public void query() {
        // Query a single encounter on the custom table.
        Statement statement = null;
        ResultSet rs = null;

        try {
            
            Table tableName = new Table();
            Connector conn = new Connector();
            conn.connectDatabase(conn.getPASSWORD());
            statement = conn.makeStatement();
            String sqlst = "Select * FROM " + tableName.getTableName() + " WHERE ID = " + this.getEncounterID();
            rs = statement.executeQuery(sqlst);

            //Parse the results
            while (rs.next()) {
                this.setEncounterID(rs.getInt("ID"));
                this.setMinDie(rs.getInt("min"));
                this.setMaxDie(rs.getInt("max"));
                this.setCreature(rs.getString("creature"));
                this.setDescription(rs.getString("description"));
            }

            rs.close();
            statement.close();
            conn.close();
            conn.close();


        } catch (Exception e) {
            System.err.println("ERROR: Either cannot connect to the DB " + " or error with the SQL statement");
        }
    }

  // Setters and Getters

    public Integer getEncounterID() {
        return encounterID;
    }

    public void setEncounterID(Integer encounterID) {
        this.encounterID = encounterID;
    }

    public Integer getMinDie() {
        return minDie;
    }

    public void setMinDie(Integer minDie) {
        this.minDie = minDie;
    }

    public Integer getMaxDie() {
        return maxDie;
    }

    public void setMaxDie(Integer maxDie) {
        this.maxDie = maxDie;
    }

    public String getCreature() {
        return creature;
    }

    public void setCreature(String creature) {
        this.creature = creature;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTableName() {
        return TableName;
    }

    public void setTableName(String TableName) {
        this.TableName = TableName;
    }


}
