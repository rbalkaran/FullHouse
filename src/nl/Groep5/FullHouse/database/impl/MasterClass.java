package nl.Groep5.FullHouse.database.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Period;
import java.util.Date;

/**
 * Created by DeStilleGast 5-6-2019
 */
public class MasterClass {

    private int ID, locatieId;
    private Date datum;
    private Timestamp beginTijd, eindTijd;
    private double kosten, minRating;
    private int maxAantalInschrijvingen;

    public MasterClass(int locatieId, Date datum, Timestamp beginTijd, Timestamp eindTijd, double kosten, double minRating, int maxAantalInschrijvingen) {
        this.locatieId = locatieId;
        this.datum = datum;
        this.beginTijd = beginTijd;
        this.eindTijd = eindTijd;
        this.kosten = kosten;
        this.minRating = minRating;
        this.maxAantalInschrijvingen = maxAantalInschrijvingen;
    }

    public MasterClass(ResultSet resultSet) throws SQLException {
        this.ID = resultSet.getInt("ID");
        this.datum = resultSet.getDate("datum");
        this.beginTijd = resultSet.getTimestamp("beginTijd");
        this.eindTijd = resultSet.getTimestamp("eindTijd");
        this.kosten = resultSet.getDouble("kosten");
        this.minRating = resultSet.getDouble("minRating");
        this.maxAantalInschrijvingen = resultSet.getInt("maxInschrijvingen");
        this.locatieId = resultSet.getInt("locatieID");
    }

    public int getID() {
        return ID;
    }

    public int getLocatieId() {
        return locatieId;
    }

    public void setLocatieId(int locatieId) {
        this.locatieId = locatieId;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
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

    public double getKosten() {
        return kosten;
    }

    public void setKosten(double kosten) {
        this.kosten = kosten;
    }

    public double getMinRating() {
        return minRating;
    }

    public void setMinRating(double minRating) {
        this.minRating = minRating;
    }

    public int getMaxAantalInschrijvingen() {
        return maxAantalInschrijvingen;
    }

    public void setMaxAantalInschrijvingen(int maxAantalInschrijvingen) {
        this.maxAantalInschrijvingen = maxAantalInschrijvingen;
    }
}
