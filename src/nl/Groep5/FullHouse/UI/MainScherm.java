package nl.Groep5.FullHouse.UI;

import com.mysql.jdbc.exceptions.MySQLDataException;
import nl.Groep5.FullHouse.database.DatabaseHelper;
import nl.Groep5.FullHouse.database.impl.Speler;
import nl.Groep5.FullHouse.database.impl.Toernooi;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class MainScherm {
    private JPanel mainPanel;
    private JTabbedPane tabbedPane1;
    private JPanel pnlSpelerOverzicht;
    private JTextField txtSpelerVoornaam;
    private JTextField txtSpelerTussenvoegsel;
    private JTextField txtSpelerAchternaam;
    private JTextField txtSpelerAdres;
    private JTextField txtSpelerPostcode;
    private JTextField txtSpelerWoonplaats;
    private JTextField txtSpelerTelefoonnummer;
    private JTextField txtSpelerGeboorteDatum;
    private JComboBox cbSpelerGeslacht;
    private TextFieldWithPlaceholder txtZoekSpelerInLijst;
    private JButton btnSpelerBewerk;

    private JTextField txtToernooiNaam;
    private JTextField txtToernooiDatum;
    private JTextField txtToernooiBeginTijd;
    private JTextField txtToernooiEindTijd;
    private JTextArea txtToernooiBeschrijving;
    private JTextField txtToernooiMaxInschrijvingen;
    private JTextField txtToernooiInleggeld;
    private JTextField txtToernooiSluitingInschrijving;
    private JTextArea txtToernooiCondities;
    private TextFieldWithPlaceholder txtToernooiZoeken;
    private JButton btnToernooiTafelIndeling;

    private JList listMasterClass;
    private JTextField txtMasterClassDatum;
    private JTextField txtMasterClassBeginTijd;
    private JTextField txtMasterClassEindDatum;
    private JTextField txtMasterClassKosten;
    private JTextField txtMasterClassMinRating;
    private JButton btnMasterClassGeregistreerdeSpelers;
    private TextFieldWithPlaceholder txtMasterClassZoeken;
    private JTable spelerTabel;
    private JScrollPane spelerScroll;
    private JScrollPane toernooiScroll;
    private JTable toernooiTabel;
    private JButton btnZoeken;
    private JComboBox comboBox1;

    public MainScherm() {
        JFrame frame = new JFrame("FullHouse");
        frame.add(mainPanel);
        frame.setSize(900, 500);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        spelerTabel.setModel(bouwSpelerTabel());

        tabbedPane1.addChangeListener(new ChangeListener(){
            @Override
            public void stateChanged(ChangeEvent e) {
                switch(tabbedPane1.getSelectedIndex()){ //TODO: voeg update logic per tabblad toe.
                    case 0:
                        spelerTabel.setModel(bouwSpelerTabel());
                        frame.setSize(900,500);
                        break;
                    case 1:
                        toernooiTabel.setModel(bouwToernooienTabel());
                        frame.setSize(1500,500);
                    default:
                        break;
                }
            }
        });


        spelerTabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                try{
                    int selectedIndex = (int) spelerTabel.getValueAt(spelerTabel.getSelectedRow(), 0);
                    Speler geselecteerdeSpeler = DatabaseHelper.verkrijgSpelerBijId(selectedIndex);
                    txtSpelerVoornaam.setText(geselecteerdeSpeler.getVoornaam());
                    txtSpelerTussenvoegsel.setText(geselecteerdeSpeler.getTussenvoegsel());
                    txtSpelerAchternaam.setText(geselecteerdeSpeler.getAchternaam());
                    txtSpelerAdres.setText(geselecteerdeSpeler.getAdres());
                    txtSpelerPostcode.setText(geselecteerdeSpeler.getPostcode());
                    txtSpelerWoonplaats.setText(geselecteerdeSpeler.getWoonplaats());
                    txtSpelerTelefoonnummer.setText(geselecteerdeSpeler.getTelefoonnummer());
                    txtSpelerGeboorteDatum.setText(geselecteerdeSpeler.getGeboortedatum().toString());
                    cbSpelerGeslacht.setSelectedItem((geselecteerdeSpeler.getGeslacht() == 'M')?"Man":"Vrouw");
                }catch(SQLException q){
                    q.printStackTrace();
                }
            }
        });
        toernooiTabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                try{
                    int selectedIndex = (int) toernooiTabel.getValueAt(toernooiTabel.getSelectedRow(), 0);
                    Toernooi geselecteerdToernooi = DatabaseHelper.verkrijgToernooiById(selectedIndex);
                    txtToernooiNaam.setText(geselecteerdToernooi.getNaam());
                    txtToernooiDatum.setText(String.valueOf(geselecteerdToernooi.getDatum()));
                    txtToernooiBeginTijd.setText(geselecteerdToernooi.getBeginTijd());
                    txtToernooiEindTijd.setText(geselecteerdToernooi.getEindTijd());
                    txtToernooiBeschrijving.setText(geselecteerdToernooi.getBeschrijving());
                    //txtToernooiCondities.setText(geselecteerdToernooi.getC();
                    txtToernooiMaxInschrijvingen.setText(String.valueOf(geselecteerdToernooi.getMaxAantalInschrijvingen()));
                    txtToernooiInleggeld.setText(String.valueOf(geselecteerdToernooi.getInleg()));
                    txtToernooiSluitingInschrijving.setText(String.valueOf(geselecteerdToernooi.getUitersteInschrijfDatum()));
                }catch(SQLException q){
                    q.printStackTrace();
                }
            }
        });
    }

    public static DefaultTableModel bouwSpelerTabel(){
        Vector<String> kollomNamen = new Vector<>();
        Vector<Vector<Object>> spelerData = new Vector<>();
        try {
            List<Speler> spelerLijst = DatabaseHelper.verkrijgAlleSpelers();
            kollomNamen.add("ID");
            kollomNamen.add("Voornaam");
            kollomNamen.add("Tussenvoegsel");
            kollomNamen.add("Achternaam");
            kollomNamen.add("Rating");
            for (Speler element : spelerLijst){
                Vector<Object> vector = new Vector<>();
                vector.add(element.getID());
                vector.add(element.getVoornaam());
                vector.add(element.getTussenvoegsel());
                vector.add(element.getAchternaam());
                vector.add(element.getRating());
                spelerData.add(vector);
            }
        }catch(SQLException e){
        e.printStackTrace();
        }
        return new DefaultTableModel(spelerData, kollomNamen){
            @Override
            public boolean isCellEditable(int row, int clumn){
                return false;
            }
        };
    }
    public static DefaultTableModel bouwToernooienTabel(){
        Vector<String> kollomNamen = new Vector<>();
        Vector<Vector<Object>> toernooiData = new Vector<>();
        try {
            List<Toernooi> toernooienLijst = DatabaseHelper.verkrijgToernooien();
            kollomNamen.add("ID");
            kollomNamen.add("Naam");
            kollomNamen.add("Datum");
            kollomNamen.add("Begintijd");
            kollomNamen.add("Eindtijd");
            kollomNamen.add("Inschrijvingslimiet");
            kollomNamen.add("Inleg");
            kollomNamen.add("Inschrijfdeadline");
            kollomNamen.add("Locatie");
            for (Toernooi element : toernooienLijst){
                Vector<Object> vector = new Vector<>();
                vector.add(element.getID());
                vector.add(element.getNaam());
                vector.add(element.getDatum());
                vector.add(element.getBeginTijd());
                vector.add(element.getEindTijd());
                vector.add(element.getMaxAantalInschrijvingen());
                vector.add(element.getInleg());
                vector.add(element.getUitersteInschrijfDatum());
                vector.add(element.getLocatie());
                toernooiData.add(vector);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return new DefaultTableModel(toernooiData, kollomNamen){
            @Override
            public boolean isCellEditable(int row, int clumn){
                return false;
            }
        };
    }
}


