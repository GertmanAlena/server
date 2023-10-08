package server.client;

import server.server.Server;
import server.server.ServerGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Работа с графическим интерфейсом (всё, что относится к панелям)
 */
public class ClientGUI extends JFrame implements ClientView {

    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;

    JTextArea log;

    JTextField jtfHost, jtfPort, jtfLogin, jtfMessage;
    JPasswordField jtfPassword;
    JButton btnLogin, btnSend;
    JPanel headerPanel;

    private Client client;

    public ClientGUI(Server server){
        this.client = new Client(this, server);
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setTitle("Chat client");
        setSize(WIDTH, HEIGHT);
        createPanel();
        setVisible(true);
    }

    /**
     * Метод при нажатии на кнопку
     */
    private void connectToServer(){
        if(client.connectToServer(jtfLogin.getText())){
            hideHeaderPanel(false); //если подключился клиент успешно, скрываем панельку
        }
    }
    /**
     * Ответ от сервера
     */

    private void hideHeaderPanel(boolean visible) {
        headerPanel.setVisible(visible);
    }

    /**
     * Метод отправки сообщения по нажатию кнопки
     */
    public void sendMessage(){
        client.sendMessage(jtfMessage.getText());
        jtfMessage.setText("");
    }

    private void appendLog(String text) {
        log.append(text + "\n");
    }
    private void createPanel() {
        add(createTopPanel(), BorderLayout.NORTH);
        add(createLog());
        add(createFooter(), BorderLayout.SOUTH);
    }
    private Component createTopPanel(){
        headerPanel = new JPanel(new GridLayout(2, 3));
        jtfHost = new JTextField();
        jtfPort = new JTextField();
        jtfPassword = new JPasswordField();
        jtfLogin = new JTextField();
        btnLogin = new JButton("login");
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                connectToServer();
            }
        });
        headerPanel.add(jtfHost);
        headerPanel.add(jtfPort);
        headerPanel.add(new JPanel());
        headerPanel.add(jtfLogin);
        headerPanel.add(jtfPassword);
        headerPanel.add(btnLogin);
        return headerPanel;
    }
    /**
     * Создание центральной панельки
     * setEnabled(false) недоступна для редактирования
     * @return
     */
    private Component createLog() {
        log = new JTextArea();
        log.setEnabled(false);
        return new JScrollPane(log);
    }
    private Component createFooter() {
        JPanel panel = new JPanel(new BorderLayout());
        jtfMessage = new JTextField();
        jtfMessage.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(e.getKeyChar() == '\n'){
                    sendMessage();
                }
            }
            @Override
            public void keyPressed(KeyEvent e) {

            }
            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        btnSend = new JButton("send");
        btnSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });
        panel.add(jtfMessage);
        panel.add(btnSend, BorderLayout.EAST);
        return panel;
    }

    @Override
    public void showMessage(String text) {
        appendLog(text);
    }

    @Override
    public void disconnectFromServer(Client client) {
        hideHeaderPanel(true);
        client.disconnect(client);
    }


//    @Override
//    protected void processWindowEvent(WindowEvent e){
//        super.processWindowEvent(e);
//        if(e.getID() == WindowEvent.WINDOW_CLOSING){
//            disconnectFromServer();
//        }
//    }


}
