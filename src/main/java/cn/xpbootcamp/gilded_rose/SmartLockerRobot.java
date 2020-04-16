package cn.xpbootcamp.gilded_rose;

import java.util.List;
import java.util.Optional;

public class SmartLockerRobot extends LockerRobot{
    public SmartLockerRobot(List<Locker> lockers) {
        super(lockers);
    }

    @Override
    public Optional<Locker> getLockerWithMaxCapacityLeftInOrder(List<Locker> lockers) {
        int maxCapacity = lockers.stream().map(locker -> locker.leftCapacity()).mapToInt(capacity -> capacity).max().getAsInt();
        return lockers.stream().filter(locker -> locker.leftCapacity() == maxCapacity).findFirst();
    }
}
