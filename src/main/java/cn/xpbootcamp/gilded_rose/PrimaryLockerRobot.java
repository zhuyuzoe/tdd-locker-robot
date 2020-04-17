package cn.xpbootcamp.gilded_rose;

import java.util.List;
import java.util.Optional;

public class PrimaryLockerRobot extends LockerRobot{
    public PrimaryLockerRobot(List<Locker> lockers) {
        super(lockers);
    }

    @Override
    Ticket saveBag(Bag bag) {
        validateBag(bag);
        validateCabinetLeftCapacity();

        Optional<Locker> savedLocker = getLockerWithMaxCapacityLeftInOrder(this.lockers);
        lockerOrderIndex = lockers.indexOf(lockers.size() == 1 ? lockers.get(0) : savedLocker.get());

        Ticket ticket = lockers.size() == 1 ? lockers.get(0).saveBagIntoLocker(bag) : savedLocker.get().saveBagIntoLocker(bag);

        return ticket;
    }

    private Optional<Locker> getLockerWithMaxCapacityLeftInOrder(List<Locker> lockers) {
        return lockers.stream().filter(locker -> locker.leftCapacity() > 0).findFirst();
    }
}
