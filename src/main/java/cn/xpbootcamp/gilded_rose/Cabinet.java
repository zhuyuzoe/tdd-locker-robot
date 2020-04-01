package cn.xpbootcamp.gilded_rose;

import cn.xpbootcamp.gilded_rose.exception.InsufficientLockersException;
import cn.xpbootcamp.gilded_rose.exception.InvalidTicketException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Cabinet {
    private int lockerOrderIndex;

    public Cabinet(List<Locker> lockers) {
        this.lockers = lockers;
    }

    private List<Locker> lockers = new ArrayList<>();

    public Ticket save(Bag bag) {
        if (bag == null) {
            throw new IllegalArgumentException("Please put a bag into the cabinet.");
        }

        Ticket ticket = new Ticket();

        Optional<Locker> firstEmptyLocker = getFirstEmptyLocker();
        if (!firstEmptyLocker.isPresent()) {
            throw new InsufficientLockersException("Insufficient empty lockers.");
        }

        lockerOrderIndex = lockers.indexOf(firstEmptyLocker.get());
        firstEmptyLocker.get().saveBagIntoLocker(bag, ticket);

        return ticket;
    }

    private Optional<Locker> getFirstEmptyLocker() {
        return lockers.stream().filter(locker -> !locker.isLockerFull()).findFirst();
    }


    public Bag get(Ticket ticket) {
        if (ticket == null) {
            throw new InvalidTicketException("Please insert a ticket to get your bag.");
        }
        if (lockers.get(lockerOrderIndex).getLocker().containsKey(ticket)) {
            return lockers.get(lockerOrderIndex).getBagFromLocker(ticket);
        }

        throw new InvalidTicketException("Please insert a valid ticket.");
    }

    public Locker getLockerWithOrder(int order) {
        return lockers.get(order - 1);
    }
}
