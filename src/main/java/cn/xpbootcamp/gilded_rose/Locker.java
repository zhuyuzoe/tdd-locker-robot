package cn.xpbootcamp.gilded_rose;

import cn.xpbootcamp.gilded_rose.exception.InsufficientLockersException;

import java.util.HashMap;

public class Locker {
    private int capacity;

    private HashMap<Ticket, Bag> locker = new HashMap<>();

    Locker(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException();
        }
        this.capacity = capacity;
    }

    public HashMap<Ticket, Bag> getLocker() {
        return locker;
    }

    public void saveBagIntoLocker(Bag bag, Ticket ticket) {
        if (isLockerFull()) {
            throw new InsufficientLockersException("Insufficient empty lockers.");
        }
        locker.put(ticket, bag);
    }

    public boolean isLockerFull() {
        return locker.size() >= capacity;
    }

    Bag getBagFromLocker(Ticket ticket) {
        return locker.remove(ticket);
    }
}
