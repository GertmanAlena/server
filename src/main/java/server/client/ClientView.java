package server.client;

/**
 * Клиент к GUI через интерфейс
 */
public interface ClientView {

    void showMessage(String text);
    void disconnectFromServer(Client client);
}
