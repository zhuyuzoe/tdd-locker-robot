package cn.xpbootcamp.gilded_rose;

import cn.xpbootcamp.gilded_rose.exception.InsufficientLockersException;
import cn.xpbootcamp.gilded_rose.exception.InvalidBagException;
import cn.xpbootcamp.gilded_rose.exception.InvalidTicketException;

import java.util.List;
import java.util.Optional;

public class Cabinet {
    private int lockerOrderIndex;
    private List<Locker> lockers;

    public Cabinet(List<Locker> lockers) {
        this.lockers = lockers;
    }

    public Ticket save(Bag bag) {
        validateBag(bag);

        Ticket ticket = new Ticket();

        Optional<Locker> firstEmptyLocker = getFirstEmptyLocker();
        if (!firstEmptyLocker.isPresent()) {
            throw new InsufficientLockersException("Insufficient empty lockers.");
        }
        Optional<Locker> savedLocker = getLockerWithMaxCapacityLeftInOrder();
        lockerOrderIndex = lockers.indexOf(savedLocker.get());
        savedLocker.get().saveBagIntoLocker(bag, ticket);

        return ticket;
    }

    public Bag get(Ticket ticket) {
        validateTicket(ticket);

        if (lockers.get(lockerOrderIndex).getLocker().containsKey(ticket)) {
            return lockers.get(lockerOrderIndex).getBagFromLocker(ticket);
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

    private Optional<Locker> getFirstEmptyLocker() {
        return lockers.stream().filter(locker -> !locker.isLockerFull()).findFirst();
    }

    public Locker getLockerWithOrder(int order) {
        return lockers.get(order - 1);
    }

    private Optional<Locker> getLockerWithMaxCapacityLeftInOrder() {
        int maxCapacity = lockers.stream().map(locker -> locker.leftCapacity()).mapToInt(capacity -> capacity).max().getAsInt();
        return lockers.stream().filter(locker -> locker.leftCapacity() == maxCapacity).findFirst();
    }
}
