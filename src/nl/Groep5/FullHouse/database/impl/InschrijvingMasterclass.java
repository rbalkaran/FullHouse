package nl.Groep5.FullHouse.database.impl;

import nl.Groep5.FullHouse.database.DatabaseHelper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by DeStilleGast 5-6-2019
 */
public class InschrijvingMasterclass {

    private int spelerID, masterclassID;
    private boolean isBetaald;

    public InschrijvingMasterclass(int spelerID, int masterclassID, boolean isBetaald) {
        this.spelerID = spelerID;
        this.masterclassID = masterclassID;
        this.isBetaald = isBetaald;
    }

    public InschrijvingMasterclass(ResultSet resultSet) throws SQLException {
        this.spelerID = resultSet.getInt("spelerID");
        this.masterclassID = resultSet.getInt("masterclassID");
        this.isBetaald = resultSet.getBoolean("betaald");
    }

    public int getSpelerID() {
        return spelerID;
    }

    public int getMasterclassID() {
        return masterclassID;
    }

    public boolean isBetaald() {
        return isBetaald;
    }

    public void setBetaald(boolean betaald) {
        isBetaald = betaald;
    }

    /**
     * Verkrijg de {@link Speler} van deze inschrijving
     * @return {@link Speler} van deze inschrijving
     * @throws SQLException
     */
    public Speler getSpeler() throws SQLException{
        return DatabaseHelper.verkrijgSpelerBijId(spelerID);
    }

    /**
     * Verkrijg de {@link MasterClass} van deze inschrijving
     * @return {@link MasterClass} van deze inschrijving
     * @throws SQLException
     */
    public MasterClass getMasterClass() throws SQLException{
        return DatabaseHelper.verkrijgMasterClassById(masterclassID);
    }
}
