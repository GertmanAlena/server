package server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class ClientGUI extends JFrame{

    JPanel panelTop;
    JPanel bottomTop;
    JTextArea log;
    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;
    JTextField jtfHost;
    JTextField jtfPort;
    JPasswordField jtfPassword;
    JTextField jtfLogin;
    JTextField jtfMessage;
    JButton btnLogin;
    JButton btnSend;

    ServerWindow serverWindow;


    ClientGUI(ServerWindow serverWindow){
        this.serverWindow = serverWindow;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setTitle("Chat client");
        setResizable(false);

        add(createPanelTop(), BorderLayout.NORTH);
        add(createPanelBottomTop(), BorderLayout.SOUTH);
        add(createPaneLog());

    }
    private Component createPanelTop(){
        panelTop = new JPanel(new GridLayout(2, 3));
        jtfHost = new JTextField();
        jtfPort = new JTextField();
        jtfPassword = new JPasswordField();
        jtfLogin = new JTextField();
        btnLogin = new JButton("login");
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelTop.setVisible(false);
            }
        });

        panelTop.add(jtfHost);
        panelTop.add(jtfPort);
        panelTop.add(jtfLogin);
        panelTop.add(jtfHost);
        panelTop.add(jtfPassword);
        panelTop.add(btnLogin);
        return panelTop;
    }
    private Component createPanelBottomTop(){
        bottomTop = new JPanel(new BorderLayout());
        btnSend = new JButton("send");
        jtfMessage = new JTextField();

        btnSend.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String value = jtfMessage.getText();
                jtfMessage.setText("");
                System.out.println(value);
                serverWindow.log.append(value + "\n");

                log.setForeground(Color.BLACK);
                log.append(value + "\n");
                saveMessages(value + "\n");
            }

        });
        jtfMessage.addKeyListener(new KeyListener(){

            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            public void keyReleased(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String value = jtfMessage.getText() + "\n";
                    jtfMessage.setText("");
                    System.out.println(value);

                    serverWindow.log.append(value);
                    log.append(value);

                    saveMessages(value);
                }
            }
        });

        bottomTop.add(jtfMessage, BorderLayout.CENTER);
        bottomTop.add(btnSend, BorderLayout.EAST);
        return bottomTop;
    }


    private Component createPaneLog(){
        log = new JTextArea();
        log.setEnabled(false);
        log.setForeground(Color.RED);
        JScrollPane scrollPane = new JScrollPane(log);
        Font font = new Font("Verdana", Font.BOLD, 12);
        log.setFont(font);
        log.setForeground(Color.BLUE);
        return scrollPane;
    }

    private void saveMessages(String value) {
        try{
            Files.write(Paths.get("messages.txt"), value.getBytes(), StandardOpenOption.APPEND);
        }catch (IOException ex){
            System.out.println(ex.getMessage());
        }


//        try(FileWriter writer = new FileWriter("messages.txt", false))
//        {
//            // запись всей строки
//            writer.write(value);
//            // запись по символам
//            writer.append('\n');
//            writer.flush();
//        }
//        catch(IOException ex){
//
//            System.out.println(ex.getMessage());
//        }
    }

}
