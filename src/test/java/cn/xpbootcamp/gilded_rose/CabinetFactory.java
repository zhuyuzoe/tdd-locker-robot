package cn.xpbootcamp.gilded_rose;

import java.util.ArrayList;
import java.util.List;

class CabinetFactory {
    private static int DEFAULT_CAPACITY = 10;

    static Cabinet createCabinetWithLockersOfPlentyOfCapacity(int numberOfLockers) {
        List<Locker> lockers = new ArrayList<>();
        for (int i = 0; i < numberOfLockers; i++) {
            lockers.add(createEmptyLocker());
        }
        return new Cabinet(lockers);
    }

    static Locker createEmptyLocker() {
        return new Locker(Integer.MAX_VALUE);
    }

    static Locker createFullLocker(int defaultCapacity) {
        Locker locker = new Locker(defaultCapacity);
        for (int i = 0; i < defaultCapacity; i++) {
            locker.saveBagIntoLocker(new Bag(), new Ticket());
        }
        return locker;
    }

    static Locker createLockerWithSomeCapacityLeft(int leftCapacity) {
        Locker locker = new Locker(DEFAULT_CAPACITY);
        int savedCapacity = 0;
        while (leftCapacity < DEFAULT_CAPACITY - savedCapacity) {
            locker.saveBagIntoLocker(new Bag(), new Ticket());
            savedCapacity++;
        }
        return locker;
    }
}
