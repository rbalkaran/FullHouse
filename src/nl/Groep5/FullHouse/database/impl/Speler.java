package nl.Groep5.FullHouse.database.impl;

import nl.Groep5.FullHouse.database.DatabaseHelper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

/**
 * Created by DeStilleGast 5-6-2019
 */
public class Speler {

    private int ID;
    private String voornaam, tussenvoegsel, achternaam, adres, postcode, woonplaats, telefoonnummer, email;
    private char geslacht;
    private Date geboortedatum;
    private double rating;

    public Speler(String voornaam, String tussenvoegsel, String achternaam, String adres, String postcode, String woonplaats, String telefoonnummer, String email, char geslacht, Date geboortedatum, double rating) {
        this.voornaam = voornaam;
        this.tussenvoegsel = tussenvoegsel;
        this.achternaam = achternaam;
        this.adres = adres;
        this.postcode = postcode;
        this.woonplaats = woonplaats;
        this.telefoonnummer = telefoonnummer;
        this.email = email;
        this.geslacht = geslacht;
        this.geboortedatum = geboortedatum;
        this.rating = rating;
    }

    // Zonder tussenvoegsel
    public Speler(String voornaam, String achternaam, String adres, String postcode, String woonplaats, String telefoonnummer, String email, char geslacht, Date geboortedatum, double rating) {
        this.voornaam = voornaam;
        this.achternaam = achternaam;
        this.adres = adres;
        this.postcode = postcode;
        this.woonplaats = woonplaats;
        this.telefoonnummer = telefoonnummer;
        this.email = email;
        this.geslacht = geslacht;
        this.geboortedatum = geboortedatum;
        this.rating = rating;
    }

    public Speler(ResultSet resultSet) throws SQLException {
        this.ID = resultSet.getInt("ID");
        this.voornaam = resultSet.getString("voornaam");
        this.tussenvoegsel = resultSet.getString("tussenvoegsel");
        this.achternaam = resultSet.getString("achternaam");
        this.adres = resultSet.getString("adres");
        this.postcode = resultSet.getString("postcode");
        this.woonplaats = resultSet.getString("woonplaats");
        this.telefoonnummer = resultSet.getString("telefoonnummer");
        this.email = resultSet.getString("email");
        this.geslacht = resultSet.getString("geslacht").charAt(0);
        this.geboortedatum = resultSet.getDate("geboortedatum");
        this.rating = resultSet.getDouble("rating");
    }

    public int getID() {
        return ID;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public String getTussenvoegsel() {
        return tussenvoegsel;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public String getAdres() {
        return adres;
    }

    public String getPostcode() {
        return postcode;
    }

    public String getWoonplaats() {
        return woonplaats;
    }

    public String getTelefoonnummer() {
        return telefoonnummer;
    }

    public String getEmail() {
        return email;
    }

    public char getGeslacht() {
        return geslacht;
    }

    public Date getGeboortedatum() {
        return geboortedatum;
    }

    public double getRating() {
        return rating;
    }

    // https://howtodoinjava.com/java/calculate-age-from-date-of-birth/
    public int getLeeftijd(){
        Period p = Period.between(this.getGeboortedatum().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), LocalDate.now());
        return p.getYears();
    }

    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }

    public void setTussenvoegsel(String tussenvoegsel) {
        this.tussenvoegsel = tussenvoegsel;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public void setWoonplaats(String woonplaats) {
        this.woonplaats = woonplaats;
    }

    public void setTelefoonnummer(String telefoonnummer) {
        this.telefoonnummer = telefoonnummer;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setGeslacht(char geslacht) {
        this.geslacht = geslacht;
    }

    public void setGeboortedatum(Date geboortedatum) {
        this.geboortedatum = geboortedatum;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public boolean registreerVoorToernooi(Toernooi toernooi, Boolean heeftBetaald) throws SQLException{
        return DatabaseHelper.registreerSpelerVoorToernooi(toernooi, this, heeftBetaald);
    }

    public boolean registreerVoorMasterClass(MasterClass masterClass, Boolean heeftBetaald) throws SQLException{
        return DatabaseHelper.registreerSpelerVoorMasterclass(masterClass, this, heeftBetaald);
    }
}
