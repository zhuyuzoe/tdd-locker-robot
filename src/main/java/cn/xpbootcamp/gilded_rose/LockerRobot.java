package cn.xpbootcamp.gilded_rose;

import cn.xpbootcamp.gilded_rose.exception.InsufficientLockersException;
import cn.xpbootcamp.gilded_rose.exception.InvalidBagException;
import cn.xpbootcamp.gilded_rose.exception.InvalidTicketException;

import java.util.List;
import java.util.Optional;

public abstract class LockerRobot {
    protected int lockerOrderIndex;
    protected List<Locker> lockers;

    public LockerRobot(List<Locker> lockers) {
        this.lockers = lockers;
    }

    abstract Ticket saveBag(Bag bag);

    protected void validateCabinetLeftCapacity() {
        Optional<Locker> firstEmptyLocker = getFirstEmptyLocker();
        if (!firstEmptyLocker.isPresent()) {
            throw new InsufficientLockersException();
        }
    }

    public Bag getBag(Ticket ticket) {
        validateTicket(ticket);

        if (lockers.get(lockerOrderIndex).getLocker().containsKey(ticket)) {
            return lockers.get(lockerOrderIndex).getBagFromLocker(ticket);
        }

        throw new InvalidTicketException();
    }

    private void validateTicket(Ticket ticket) {
        if (ticket == null) {
            throw new InvalidTicketException();
        }
    }

    protected void validateBag(Bag bag) {
        if (bag == null) {
            throw new InvalidBagException();
        }
    }

    private Optional<Locker> getFirstEmptyLocker() {
        return lockers.stream().filter(locker -> !locker.isLockerFull()).findFirst();
    }

}
