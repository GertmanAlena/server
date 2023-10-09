package server.client;

import server.server.Server;

/**
 * Логика работы клиента
 */
public class Client {

    private String name;
    private final ClientView clientView;
    private final Server server;
    private boolean connected;

    /**
     * Конструктор получает
     *
     * @param clientView объект View
     * @param server     объект Server
     */
    public Client(ClientView clientView, Server server) {
        this.clientView = clientView;
        this.server = server;
    }

    /**
     * Сервер возьмёт имя клиента из сообщения при первом сообщении
     *
     * @return name
     */
    public String getName() {
        return name;

    }

    /**
     * Метод подключения клиента к серверу
     * @param name имя клиента из сообщения
     *             в server.connectUser добавляем клиента в list
     * @return истина, если подключился
     */
    public boolean connectToServer(String name) {
        this.name = name;
        if (server.connectUser(this)) {
            printText(name + "Вы успешно подключились!!\n");
            connected = true;
            String log = server.getHistory();
            if (log != null) {
                printText(log);
            }
            return true;
        } else {
            printText("Подключение не удалось!!\n");
            return false;
        }
    }

    /**
     * Посылаем сообщение-запрос серверу
     * @param message сообщение серверу
     */
    public void sendMessage(String message) {
        if (connected) {
            if (!message.isEmpty()) {
                server.sendMessage(name + ":" + message);
            }
        } else {
            printText("нет подключения к серверу!!!");
        }
    }

    /**
     * Возврат ответа с сервера. Всё, что нам сервер сообщит, выводим в консоль
     *
     * @param answer
     */
    public void serverAnswe(String answer) {
        printText(answer);
    }

    public void disconnect(Client client) {
        if (connected) {
            connected = false;
            clientView.disconnectFromServer(this);  //когда пользователя отключили, сообщаем графическому интерфейсу, что нужно вывести поля для ввода
//            server.disconnectUser(this);
            printText("Вы были отключены от сервера!!!");
        }
    }

    public void printText(String text) {
        clientView.showMessage(text);
    }


}
