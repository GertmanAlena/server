package server.server;

import server.client.Client;

public interface ServerView {

    void showMessage(String text);

    void appendLog(String text);

    void hideHeaderPanel(boolean x);

    void disconnect();

    void addList(Client client);

    void answerAll(String text);
}
