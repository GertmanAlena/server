package server.server;

public interface ServerView {

    void showMessage(String text);
    void appendLog(String text);

    void hideHeaderPanel(boolean x);
}
