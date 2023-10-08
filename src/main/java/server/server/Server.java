package server.server;

import server.client.Client;

public class Server {

    private final Repository repository;
    private final ServerView serverView;
    RepositiryInterfase repositiryInterfase;
    boolean work;

    public Server(Repository repository) {
        this.repository = repository;
        this.serverView = new ServerGUI(this);
        this.repositiryInterfase = repositiryInterfase;
    }

    public void connect(){
        if (work){
            printText("Сервер уже был запущен");
        } else {
            work = true;
            printText("Сервер запущен!");
        }
    }

    public void disconnect(Client client){
        if (!work){
            printText("Сервер уже был остановлен");
        }
        else {
            work = false;
                disconnectUser(client); // удаление из list
                printText("Сервер остановлен!");
                client.disconnect();
        }
    }
    public boolean connectUser(Client client){
        if (!work){
            return false;
        }
        addList(client);
        return true;
    }

    public void sendMessage(String text){
        if (!work){
            return;
        }
        appendLog(text);
        answerAll(text);
        saveInLog(text);
    }
    public String getHistory() {
        return getHistoryRepo();
    }

    public String getHistoryRepo(){

        return repositiryInterfase.getHistory();
    }

    public void answerAll(String text){
        repositiryInterfase.answerAll(text);
    }
    public void saveInLog(String text){
        repositiryInterfase.saveInLog(text);
    }

    public void disconnectUser(Client client){
        repositiryInterfase.disconnectUser(client);
    }

    public void printText(String text){
        serverView.showMessage(text);
    }
    public void addList(Client client){
        repositiryInterfase.addList(client);
    }
    public void appendLog(String text){
        serverView.appendLog(text);
    }

}
