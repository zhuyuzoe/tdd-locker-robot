package cn.xpbootcamp.gilded_rose;

import java.util.List;
import java.util.Optional;

public class SuperLockerRobot extends LockerRobot {
    public SuperLockerRobot(List<Locker> lockers) {
        super(lockers);
    }

    @Override
    public Optional<Locker> getLockerWithMaxCapacityLeftInOrder(List<Locker> lockers) {
        double maxCapacityPercentage = lockers.stream().map(locker -> locker.leftCapacityPercentage()).mapToDouble(capacityPercentage -> capacityPercentage).max().getAsDouble();
        return lockers.stream().filter(locker -> locker.leftCapacityPercentage() == maxCapacityPercentage).findFirst();
    }
}
