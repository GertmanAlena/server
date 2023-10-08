package server.server;

import server.client.Client;

public interface RepositiryInterfase {
    void disconnectUser(Client client);
    public void addList(Client client);
    void answerAll(String text);
    void saveInLog(String text);
    String getHistory();
}
