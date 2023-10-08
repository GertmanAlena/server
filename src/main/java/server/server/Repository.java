package server.server;

import server.client.Client;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class Repository  implements RepositiryInterfase  {

    public static final String LOG_PATH = "src/server/log.txt";
    List<Client> clientList;

    public Repository() {
        clientList = new ArrayList<>();
    }

    public String getHistory() {
        return readLog();
    }

    public void sendMessage(String text){
        text += "";
        answerAll(text);
        saveInLog(text);
    }
    @Override
    public void disconnectUser(Client client) {
        for (Client cl : clientList) {
            disconnect(cl);

        }
    }
    /**
     * удаляем из list
     * @param client
     */
    public void disconnect (Client client){
        clientList.remove(client);
//        if (client != null){
//            clientGUI.disconnectFromServer();
//        }
    }

    @Override
    public void addList(Client client) {
        clientList.add(client);
    }

    public void answerAll(String text){
        for (Client clientGUI: clientList){
//            clientGUI.answer(text);
        }
    }
    public void saveInLog(String text){
        try (FileWriter writer = new FileWriter(LOG_PATH, true)){
            writer.write(text);
            writer.write("\n");
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    private String readLog(){
        StringBuilder stringBuilder = new StringBuilder();
        try (FileReader reader = new FileReader(LOG_PATH);){
            int c;
            while ((c = reader.read()) != -1){
                stringBuilder.append((char) c);
            }
            stringBuilder.delete(stringBuilder.length()-1, stringBuilder.length());
            return stringBuilder.toString();
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

}
