package nl.Groep5.FullHouse.UI;

import nl.Groep5.FullHouse.database.DatabaseHelper;
import nl.Groep5.FullHouse.database.impl.Speler;

import javax.swing.*;
import java.sql.SQLException;
import java.util.List;

public class MainScherm {
    private JPanel mainPanel;
    private JTabbedPane tabbedPane1;
    private JList listSpelers;
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
    private JButton btnSpelerNieuw;
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
    private JTable table1;

    public MainScherm() {
        JFrame frame = new JFrame("FullHouse");
        frame.add(mainPanel);
        frame.setSize(500, 420);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        DefaultListModel<String> test = new DefaultListModel<>();

        listSpelers.setModel(test);

        try{
            DatabaseHelper DBhelper = new DatabaseHelper();
            List<Speler> list = DBhelper.verkrijgAlleSpelers();
            for(Speler element : list){
                test.addElement(element.getVoornaam());
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}


