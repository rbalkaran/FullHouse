package nl.Groep5.FullHouse.database;

import nl.Groep5.FullHouse.Main;
import nl.Groep5.FullHouse.database.impl.Locatie;
import nl.Groep5.FullHouse.database.impl.MasterClass;
import nl.Groep5.FullHouse.database.impl.Speler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by DeStilleGast 5-6-2019
 */
public class DatabaseHelper {

    /**
     * Verkrijg lijst met spelers
     * @return List met Speler objecten
     * @throws SQLException
     */
    public static List<Speler> verkrijgAlleSpelers() throws SQLException {
        return verkrijgAlleSpelers(null);
    }

    /**
     * Verkrijg lijst met spelers waarvan de voornaam, tussenvoegsel, achternaam gefilterd zijn
     * @param filter waarom de filter moet werken
     * @return List met Speler objecten die gefilted zijn
     * @throws SQLException
     */
    public static List<Speler> verkrijgAlleSpelers(String filter) throws SQLException {
        MySQLConnector mysql = Main.getMySQLConnection();
        PreparedStatement ps;
        if(filter == null || filter.isEmpty()){
            ps = mysql.prepareStatement("select * from speler");
        }else {
            ps = mysql.prepareStatement("select * from speler where voornaam like ? or tussenvoegsel like ? or achternaam like ?");
            ps.setString(1, filter);
            ps.setString(2, filter);
            ps.setString(3, filter);
        }
        ResultSet rs = mysql.query(ps);

        List<Speler> spelers = new ArrayList<>();

        while(rs.next()){
            spelers.add(new Speler(rs));
        }

        return spelers;
    }

    /**
     * Verkrijg lijst met locaties
     * @return Lijst met locatie objecten
     * @throws SQLException
     */
    public static List<Locatie> verkrijgLocatieLijst() throws SQLException {
        MySQLConnector mysql = Main.getMySQLConnection();
        PreparedStatement ps = mysql.prepareStatement("select * from locatie");
        ResultSet rs = mysql.query(ps);

        List<Locatie> locaties = new ArrayList<>();

        while(rs.next()){
            locaties.add(new Locatie(rs));
        }

        return locaties;
    }

    /**
     * Verkrijg lijst met MasterClassen
     * @return Lijst met MasterClass objecten
     * @throws SQLException
     */
    public static List<MasterClass> verkrijgMasterClasses() throws SQLException {
        MySQLConnector mysql = Main.getMySQLConnection();
        PreparedStatement ps = mysql.prepareStatement("select * from masterclass");
        ResultSet rs = mysql.query(ps);

        List<MasterClass> masterClasses = new ArrayList<>();

        while(rs.next()){
            masterClasses.add(new MasterClass(rs));
        }

        return masterClasses;
    }

    /**
     * Verkrijg MasterClass object met opgegeven ID, returned null als er niks gevonden is
     * @param id MasterClass ID
     * @return Gevonden MasterClass, null als er niks gevonden is
     * @throws SQLException
     */
    public static MasterClass verkrijgMasterClass(int id) throws SQLException {
        MySQLConnector mysql = Main.getMySQLConnection();
        PreparedStatement ps = mysql.prepareStatement("select * from masterclass");
        ResultSet rs = mysql.query(ps);

        List<MasterClass> masterClasses = new ArrayList<>();

        if(rs.next())
            return new MasterClass(rs);

        return null;
    }

}
