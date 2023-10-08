package server.server;

import server.client.Client;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Repository  implements RepositiryInterfase, Iterable<Client> {

    public static final String LOG_PATH = "D:\\studies\\JAVA\\Java Development Kit\\server\\src\\main\\java\\server\\log.txt";
    List<Client> clientList;

    public Repository() {

        clientList = new ArrayList<>();
    }
    @Override
    public String getHistory() {
        return readLog();
    }

    public void disconnect (Client client){
        if (client != null){
            client.disconnect(client);
        }
    }

    @Override
    public void disconnect() {
        if(!clientList.isEmpty()){
            Iterator<Client> iterator = clientList.iterator();
            while(iterator.hasNext()){
                Client client = iterator.next();
                iterator.remove();
                disconnect(client);
            }
        }
    }

    @Override
    public void addList(Client client) {
        clientList.add(client);
    }
    @Override
    public void answerAll(String text){
        for (Client client: clientList){
//            client.answer(text);
        }
    }
    public void sendMessage(String text){
        text += "";
        answerAll(text);
        saveInLog(text);
    }
    @Override
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
            if(stringBuilder.length() != 0){
                stringBuilder.delete(stringBuilder.length()-1, stringBuilder.length());
            }
            return stringBuilder.toString();
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Iterator<Client> iterator() {
        return new GroupIterator<>(clientList);
    }


}
