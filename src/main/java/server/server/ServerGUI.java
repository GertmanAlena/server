package server.server;

import server.client.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServerGUI extends JFrame implements ServerView{

    public static final int WIDTH = 400;
    public static final int HEIGHT = 300;

    JButton btnStart, btnStop;
    JTextArea log;

    Server server;
    JPanel headerPanel;

    public ServerGUI(Server server){
        this.server = server;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setTitle("Chat server");
        setLocationRelativeTo(null);

        createPanel();
        setVisible(true);
    }

    public Server getServer() {
        return server;
    }

    private void createPanel() {
        log = new JTextArea();
        add(log);
        add(createButtons(), BorderLayout.SOUTH);
    }

    private Component createButtons() {
        JPanel headerPanel = new JPanel(new GridLayout(1, 2));
        btnStart = new JButton("Start");
        btnStop = new JButton("Stop");

        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                server.connect();
            }
        });

        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                server.disconnect();

            }
        });

        headerPanel.add(btnStart);
        headerPanel.add(btnStop);
        return headerPanel;
    }

    public void appendLog(String text){
        log.append(text + "\n");
    }

    @Override
    public void hideHeaderPanel(boolean x) {
//        headerPanel.setVisible(x);
        log.replaceSelection("");
    }

    @Override
    public void showMessage(String text) {
        appendLog(text);
    }




}
