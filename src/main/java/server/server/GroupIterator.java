package server.server;

import server.client.Client;

import java.util.Iterator;
import java.util.List;

public class GroupIterator<Client> implements Iterator<Client> {
    private List<Client> clientList;
    private int index = 0;
    public GroupIterator(List<Client> clientList) {
    }

    @Override
    public boolean hasNext() {
        return index < clientList.size();
    }

    @Override
    public Client next() {
        return clientList.get(index++);
    }

}
