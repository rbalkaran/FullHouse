package nl.Groep5.FullHouse.database.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by DeStilleGast 5-6-2019
 */
public class InschrijvingToernooi {

    private int spelerID, toernooiID;
    private boolean isBetaald;

    public InschrijvingToernooi(int spelerID, int toernooiID, boolean isBetaald) {
        this.spelerID = spelerID;
        this.toernooiID = toernooiID;
        this.isBetaald = isBetaald;
    }

    public InschrijvingToernooi(ResultSet resultSet) throws SQLException {
        this.spelerID = resultSet.getInt("spelerID");
        this.toernooiID = resultSet.getInt("toernooiID");
        this.isBetaald = resultSet.getBoolean("betaald");
    }

    public int getSpelerID() {
        return spelerID;
    }

    public int getToernooiID() {
        return toernooiID;
    }

    public boolean isBetaald() {
        return isBetaald;
    }

    public void setBetaald(boolean betaald) {
        isBetaald = betaald;
    }
}
