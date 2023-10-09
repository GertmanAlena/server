package server.server;

import server.client.Client;

public interface RepositiryInterfase {
    void saveInLog(String text);
    String getHistory();
}
