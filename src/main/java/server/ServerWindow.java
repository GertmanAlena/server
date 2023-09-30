package server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ServerWindow extends JFrame{

    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;

    JTextArea log;
    JButton btnStart, btnStop;

    ClientGUI clientGUI;
    Scanner scan;
    ArrayList<String> all = new ArrayList<>();

    ServerWindow(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setTitle("Chat server");
        setResizable(false);

        btnStart = new JButton("Start");
        btnStop = new JButton("Stop");

        clientGUI = new ClientGUI(this);
        createFile();

        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    readFile();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                clientGUI.setVisible(true);
            }
        });
        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        add(createPaneLog());

        add(createMainPanel(), BorderLayout.SOUTH);
        setVisible(true);
    }

    private void createFile() {
        try {
            File file = new File("messages.txt");
            if (file.createNewFile()) {
                System.out.println("Файл создан");
            } else {
                System.out.println("Файл уже существует");
            }
        } catch (IOException e) {
            System.out.println("Ошибка при создании файла");
            e.printStackTrace();
        }
    }

    private Component createMainPanel(){
        JPanel panBottom = new JPanel(new GridLayout(1, 2));

        panBottom.add(btnStart);
        panBottom.add(btnStop);
        return panBottom;
    }
    private Component createPaneLog(){
        log = new JTextArea();
        log.setEnabled(false);
        JScrollPane scrollPane = new JScrollPane(log);
        return scrollPane;
    }

public ArrayList<String> readFile() throws IOException {
    FileReader fr= new FileReader("messages.txt");
    scan = new Scanner(fr);

    while (scan.hasNextLine()) {
        log.append(scan.nextLine());
    }
    fr.close();

    return all;
}







}
