package nl.Groep5.FullHouse.UI;

import nl.Groep5.FullHouse.Main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InlogScherm implements ActionListener {
    private JFrame frame;

    private JPanel mainPanel;
    private JTextField txtUser;
    private JPasswordField txtPassword;
    private JButton btnLogin;

    public InlogScherm() {
        frame = new JFrame("Login scherm");
        frame.add(mainPanel);
        frame.setSize(410, 130);
        frame.setVisible(true);

        txtPassword.addActionListener(this);
        btnLogin.addActionListener(this);

        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        PreparedStatement ps = Main.getMySQLConnection().prepareStatement("select user, pass from logins where user = ? and pass = ?");
        try {
            ps.setString(1, txtUser.getText());
            ps.setString(2, new String(txtPassword.getPassword()));

            ResultSet rs = Main.getMySQLConnection().query(ps);
            if(rs.next()){
                new MainScherm();
                frame.dispose();
            }else{
                JOptionPane.showMessageDialog(frame, "Gebruikersnaam of wachtwoord is incorrect!");
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }

    }
}
