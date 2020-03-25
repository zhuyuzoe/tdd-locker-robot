package cn.xpbootcamp.gilded_rose;

import java.util.HashMap;

public class Locker {

    private HashMap<Ticket, Bag> locker = new HashMap<>();

    public void saveBagIntoLocker(Bag bag, Ticket ticket) {
        locker.put(ticket, bag);
    }

    Bag getBagFromLocker(Ticket ticket) {
        return locker.remove(ticket);
    }
}
