package server;

import server.client.Client;
import server.client.ClientGUI;
import server.client.ClientView;
import server.server.Repository;
import server.server.Server;
import server.server.ServerGUI;

public class Main {
    public static void main(String[] args) {

        Server server = new Server(new Repository());
        new ClientGUI(server);
        new ClientGUI(server);
    }
}