package nl.Groep5.FullHouse;

import nl.Groep5.FullHouse.UI.InlogScherm;
import nl.Groep5.FullHouse.UI.MainScherm;

import javax.swing.*;
import java.sql.ResultSet;

public class Main {

    // Gebruik dit object om met de database te communiceren
    // voor zelf gebruik moet je waarschijnlijk info aanpassen
    public final static MySQLConnector mysqlConnection = new MySQLConnector("localhost", "javaopdr", "root", "toor");


    public static void main(String[] args) {

        // Als de applicatie sluit, sluit dan ook de connectie met DB op een nette manier
        Runtime.getRuntime().addShutdownHook(new Thread(Main.mysqlConnection::close));


        // test database om logins te testen !
        ResultSet rs = mysqlConnection.query(mysqlConnection.prepareStatement("select 1 from logins limit 1"));
        if(rs == null){
            mysqlConnection.update(mysqlConnection.prepareStatement("CREATE TABLE logins ( `id` INT NOT NULL AUTO_INCREMENT , `user` VARCHAR(50) NOT NULL , `pass` VARCHAR(255) NOT NULL , PRIMARY KEY (`id`));"));
            mysqlConnection.update(mysqlConnection.prepareStatement("INSERT INTO logins(`user`, `pass`) values('test', 'test')"));
        }


//        MySQLConnector connector = new MySQLConnector("localhost", "javaopdr", "root", "toor");
//
//        if(connector.hasConnection()) {
//            PreparedStatement ps = connector.prepareStatement("select * from book");
//            ResultSet rs = connector.query(ps);
//
//            try {
//                while (rs.next()) {
//                    System.out.println(rs.getString("author") + " - " + rs.getString("title"));
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//
//
//            Scanner scanner = new Scanner(System.in);
//            System.out.println("Title van boek: ");
//            String newTitle = scanner.next();
//
//            System.out.println("Author: ");
//            String newAuthor = scanner.next();
//
//            PreparedStatement newPS = connector.prepareStatement("insert into book (author, title) VALUES (?, ?);");
//            try {
//                newPS.setString(1, newTitle);
//                newPS.setString(2, newAuthor);
//            } catch (SQLException e) {
//                e.printStackTrace();
//                return;
//            }
//            connector.update(newPS);
//
//
//            connector.close();
//        }


        try {
            // zet de style naar de operatie systeem style (voor meeste van ons, windows style)
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
            System.out.println("Unsupported Look and feel, defaulting to normal");
        }


        new InlogScherm();
    }
}
