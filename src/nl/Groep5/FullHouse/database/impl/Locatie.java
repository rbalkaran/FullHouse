package nl.Groep5.FullHouse.database.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by DeStilleGast 5-6-2019
 */
public class Locatie {

    private int ID;
    private String naam;

    public Locatie(String naam) {
        this.naam = naam;
    }

    public Locatie(ResultSet resultSet) throws SQLException {
        this.ID = resultSet.getInt("ID");
        this.naam = resultSet.getString("naam");
    }

    public int getID() {
        return ID;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }
}
