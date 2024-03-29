
package client.view;


import client.RmiConnector;

import javax.swing.*;
import java.awt.event.*;
import java.rmi.RemoteException;
import java.sql.SQLException;

public class AuthorisationDialog extends JDialog {
    private JPanel contentPanel;
    private JButton loginBtn;
    private JButton cancelBtn;
    private JButton checkinBtn;
    private JTextField loginField;
    private JLabel messageLabel;
    private JPasswordField passwordField;

    public void setMessageLabel(String message) {
        this.messageLabel.setText(message);
    }


    public AuthorisationDialog() {
        setSize(500,500);
        setModal(true);
        setContentPane(contentPanel);
        setLocationRelativeTo(null);
        getRootPane().setDefaultButton(loginBtn);
        initListener();
        setVisible(true);
        pack();
    }

    private void initListener(){
        loginBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    onOK();
                } catch (Exception e1) {}
            }
        });

        cancelBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        checkinBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new RegistrationWindow(thisWindow());
            }
        });
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });
    }


    private void onOK() throws RemoteException, SQLException {

        RmiConnector rmiConnection = new RmiConnector();
        try {
            if (loginField.getText().equals("") || passwordField.getText().equals("")) {
                throw new NumberFormatException("Проверьте введенные данные");
            }
            if ((rmiConnection.getUserInterface().authorisation(loginField.getText(), passwordField.getText()))!= -1) {
                setVisible(false);
                dispose();
                TaskManager taskManager = new TaskManager(thisWindow());
            }
            else messageLabel.setText("Ошибка");
        } catch (NumberFormatException e) {
            new ErrorWindow(e.getMessage());
        }
    }

    AuthorisationDialog thisWindow() {
        return this;
    }

    private void onCancel() {
        System.exit(0);
    }
}