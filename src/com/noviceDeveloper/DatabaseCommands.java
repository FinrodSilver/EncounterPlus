package com.noviceDeveloper;

/**
 *
 * @author Richard Webb
 * Interface for commands to the EncounterPlus Database System.
 */
public interface DatabaseCommands {

    public abstract void update();

    public abstract void add();

    public abstract void delete();

    public abstract void query();
}
