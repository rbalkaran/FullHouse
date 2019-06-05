package nl.Groep5.FullHouse.database.impl;

import nl.Groep5.FullHouse.database.DatabaseHelper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

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

    public Toernooi(ResultSet resultSet) throws SQLException {
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

    public int getMaxAantalInschrijvingen() {
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

    public List<InschrijvingToernooi> getInschrijvingen() throws SQLException {
        return DatabaseHelper.VerkrijgInschrijvingenVanToernooi(this);
    }

    public Locatie getLocatie() throws SQLException {
        return DatabaseHelper.verkrijgLocatieById(locatieID);
    }

    /**
     * Probeer speler in te schrijven voor dit toernooi
     * @param speler om te registreren
     * @return true als registratie gelukt is, false als het niet gelukt is (bijvoorbeeld omdat het vol is)
     */
    public boolean voegSpelerToe(Speler speler, Boolean heeftBetaald)throws SQLException{
        return DatabaseHelper.registreerSpelerVoorToernooi(this, speler, heeftBetaald);
    }

    /**
     * Kijk of de toernooi vol zit kwa inschrijvingen
     * @return true als de inschrijven de maximaleAantal overschrijft
     * <br>
     * <br>
     *     het returned ook true als er een SQL fout opgetreden is !!
     */
    public boolean isVol(){
        try {
            return this.getInschrijvingen().size() >= this.getMaxAantalInschrijvingen();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }
}
