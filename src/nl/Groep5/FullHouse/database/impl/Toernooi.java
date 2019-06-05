package nl.Groep5.FullHouse.database.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by DeStilleGast 5-6-2019
 */
public class Toernooi {

    private int ID, locatieID;
    private String naam, beschrijving;
    private Date datum, uitersteInschrijfDatum;
    private Timestamp beginTijd, eindTijd;
    private int maxInschrijvingen;
    private double inleg;

    public Toernooi(int locatieID, String naam, String beschrijving, Date datum, Date uitersteInschrijfDatum, Timestamp beginTijd, Timestamp eindTijd, int maxInschrijvingen, double inleg) {
        this.locatieID = locatieID;
        this.naam = naam;
        this.beschrijving = beschrijving;
        this.datum = datum;
        this.uitersteInschrijfDatum = uitersteInschrijfDatum;
        this.beginTijd = beginTijd;
        this.eindTijd = eindTijd;
        this.maxInschrijvingen = maxInschrijvingen;
        this.inleg = inleg;
    }

    private Toernooi(ResultSet resultSet) throws SQLException {
        this.ID = resultSet.getInt("ID");
        this.naam = resultSet.getString("naam");
        this.beschrijving = resultSet.getString("beschrijving");
        this.datum = resultSet.getDate("datum");
        this.beginTijd = resultSet.getTimestamp("beginTijd");
        this.eindTijd = resultSet.getTimestamp("eindTijd");
        this.maxInschrijvingen = resultSet.getInt("maxInschrijvingen");
        this.inleg = resultSet.getDouble("inleg");
    }

    public int getID() {
        return ID;
    }

    public int getLocatieID() {
        return locatieID;
    }

    public void setLocatieID(int locatieID) {
        this.locatieID = locatieID;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getBeschrijving() {
        return beschrijving;
    }

    public void setBeschrijving(String beschrijving) {
        this.beschrijving = beschrijving;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public Date getUitersteInschrijfDatum() {
        return uitersteInschrijfDatum;
    }

    public void setUitersteInschrijfDatum(Date uitersteInschrijfDatum) {
        this.uitersteInschrijfDatum = uitersteInschrijfDatum;
    }

    public Timestamp getBeginTijd() {
        return beginTijd;
    }

    public void setBeginTijd(Timestamp beginTijd) {
        this.beginTijd = beginTijd;
    }

    public Timestamp getEindTijd() {
        return eindTijd;
    }

    public void setEindTijd(Timestamp eindTijd) {
        this.eindTijd = eindTijd;
    }

    public int getMaxInschrijvingen() {
        return maxInschrijvingen;
    }

    public void setMaxInschrijvingen(int maxInschrijvingen) {
        this.maxInschrijvingen = maxInschrijvingen;
    }

    public double getInleg() {
        return inleg;
    }

    public void setInleg(double inleg) {
        this.inleg = inleg;
    }
}
