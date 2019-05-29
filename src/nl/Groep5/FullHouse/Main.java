package nl.Groep5.FullHouse;

import nl.Groep5.FullHouse.UI.InlogScherm;

import javax.swing.*;
import java.io.*;
import java.sql.ResultSet;
import java.util.Properties;

public class Main {

    private static MySQLConnector mysqlConnection;

    public static void main(String[] args) {
        Properties props = new Properties();
        try {
            props.load(new FileInputStream("config.config"));
        } catch (IOException e) {
//            e.printStackTrace();

            try {
                PrintWriter writer = new PrintWriter("config.config", "UTF-8");

                writer.println("host=");
                writer.println("database=");
                writer.println("username=");
                writer.println("password=");
                writer.close();
                System.out.println("config bestand gemaakt, bewerk deze voor gebruik");
                return;
            } catch (FileNotFoundException | UnsupportedEncodingException e1) {
                e1.printStackTrace();
            }
        }

        if(props.containsKey("host") && props.containsKey("database") && props.containsKey("username") && props.containsKey("password")) {
            mysqlConnection = new MySQLConnector(props.getProperty("host"), props.getProperty("database"), props.getProperty("username"), props.getProperty("password"));
        }else{
            System.out.println("Missing mysql info, exiting");
            return;
        }


        // Als de applicatie sluit, sluit dan ook de connectie met DB op een nette manier
        Runtime.getRuntime().addShutdownHook(new Thread(Main.mysqlConnection::close));


        // test database om logins te testen !
        ResultSet rs = mysqlConnection.query(mysqlConnection.prepareStatement("select 1 from logins limit 1"));
        if(rs == null){
            mysqlConnection.update(mysqlConnection.prepareStatement("CREATE TABLE logins ( `id` INT NOT NULL AUTO_INCREMENT , `user` VARCHAR(50) NOT NULL , `pass` VARCHAR(255) NOT NULL , PRIMARY KEY (`id`));"));
            mysqlConnection.update(mysqlConnection.prepareStatement("INSERT INTO logins(`user`, `pass`) values('test', 'test')"));
        }

        try {
            // zet de style naar de operatie systeem style (voor meeste van ons, windows style)
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
            System.out.println("Unsupported Look and feel, defaulting to normal");
        }

        new InlogScherm();
    }

    public static MySQLConnector getMySQLConnection() {
        return mysqlConnection;
    }
}
