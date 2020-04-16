package cn.xpbootcamp.gilded_rose;

import cn.xpbootcamp.gilded_rose.exception.InsufficientLockersException;
import cn.xpbootcamp.gilded_rose.exception.InvalidBagException;
import cn.xpbootcamp.gilded_rose.exception.InvalidTicketException;

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

    public Ticket saveBagIntoLocker(Bag bag) {
        if (isLockerFull()) {
            throw new InsufficientLockersException("Insufficient empty lockers.");
        }
        validateBag(bag);
        Ticket ticket = new Ticket();
        locker.put(ticket, bag);
        return ticket;
    }

    public boolean isLockerFull() {
        return locker.size() >= capacity;
    }

    public int leftCapacity() {
        return isLockerFull() ? 0 : capacity - locker.size();
    }

    public double leftCapacityPercentage() {
        return isLockerFull() ? 0 : ((capacity - locker.size()) / (double) capacity);
    }

    Bag getBagFromLocker(Ticket ticket) {
        validateTicket(ticket);
        if (locker.containsKey(ticket)) {
            return locker.remove(ticket);
        }

        throw new InvalidTicketException("Please insert a valid ticket.");
    }

    private void validateTicket(Ticket ticket) {
        if (ticket == null) {
            throw new InvalidTicketException("Please insert a ticket to get your bag.");
        }
    }

    private void validateBag(Bag bag) {
        if (bag == null) {
            throw new InvalidBagException("Please put a bag into the cabinet.");
        }
    }
}
