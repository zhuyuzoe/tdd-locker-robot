package cn.xpbootcamp.gilded_rose;

import cn.xpbootcamp.gilded_rose.exception.InsufficientLockersException;
import cn.xpbootcamp.gilded_rose.exception.InvalidBagException;
import cn.xpbootcamp.gilded_rose.exception.InvalidTicketException;

import java.util.List;
import java.util.Optional;

public class LockerRobot {
    private int lockerOrderIndex;
    private List<Locker> lockers;

    public LockerRobot(List<Locker> lockers) {
        this.lockers = lockers;
    }

    public Ticket saveBag(Bag bag) {
        validateBag(bag);
        validateCabinetLeftCapacity();

        Optional<Locker> savedLocker = getLockerWithMaxCapacityLeftInOrder(this.lockers);
        lockerOrderIndex = lockers.indexOf(savedLocker.get());

        Ticket ticket = savedLocker.get().saveBagIntoLocker(bag);

        return ticket;
    }

    private void validateCabinetLeftCapacity() {
        Optional<Locker> firstEmptyLocker = getFirstEmptyLocker();
        if (!firstEmptyLocker.isPresent()) {
            throw new InsufficientLockersException("Insufficient empty lockers.");
        }
    }

    public Bag getBag(Ticket ticket) {
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

    public Optional<Locker> getLockerWithMaxCapacityLeftInOrder(List<Locker> lockers) {
        return lockers.stream().filter(locker -> locker.leftCapacity() > 0).findFirst();
    }

}
