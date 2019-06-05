package nl.Groep5.FullHouse.database.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by DeStilleGast 5-6-2019
 */
public class BekendeSpeler {

    private int id;
    private String pseudonaam;

    public BekendeSpeler(String pseudonaam) {
        this.pseudonaam = pseudonaam;
    }

    public BekendeSpeler(ResultSet resultSet) throws SQLException {
        this.id = resultSet.getInt("id");
        this.pseudonaam = resultSet.getString("pseudonaam");
    }

    public int getId() {
        return id;
    }

    public String getPseudonaam() {
        return pseudonaam;
    }

    public void setPseudonaam(String pseudonaam) {
        this.pseudonaam = pseudonaam;
    }
}
