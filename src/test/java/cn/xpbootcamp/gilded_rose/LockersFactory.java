package cn.xpbootcamp.gilded_rose;

import java.util.ArrayList;
import java.util.List;

class LockersFactory {
    private static int DEFAULT_CAPACITY = 10;

    static List<Locker> createLockersOfPlentyOfCapacity(int numberOfLockers) {
        List<Locker> lockers = new ArrayList<>();
        for (int i = 0; i < numberOfLockers; i++) {
            lockers.add(createEmptyLocker());
        }
        return lockers;
    }

    static Locker createEmptyLocker() {
        return new Locker(Integer.MAX_VALUE);
    }

    static Locker createFullLocker(int defaultCapacity) {
        Locker locker = new Locker(defaultCapacity);
        for (int i = 0; i < defaultCapacity; i++) {
            locker.saveBagIntoLocker(new Bag());
        }
        return locker;
    }

    static Locker createLockerWithSomeCapacityLeft(int leftCapacity) {
        Locker locker = new Locker(DEFAULT_CAPACITY);
        int savedCapacity = 0;
        while (leftCapacity < DEFAULT_CAPACITY - savedCapacity) {
            locker.saveBagIntoLocker(new Bag());
            savedCapacity++;
        }
        return locker;
    }

    static Locker createSpecificSizeLockerWithSomeCapacityLeft(int totalCapacity, int leftCapacity) {
        Locker locker = new Locker(totalCapacity);
        int savedCapacity = 0;
        while (leftCapacity < totalCapacity - savedCapacity) {
            locker.saveBagIntoLocker(new Bag());
            savedCapacity++;
        }
        return locker;
    }

    static List<Locker> createLockersOfCapacityLeft(ArrayList<Integer> leftCapacity) {
        List<Locker> lockers = new ArrayList<>();
        leftCapacity.forEach(capacity -> lockers.add(createLockerWithSomeCapacityLeft(capacity)));

        return lockers;
    }
}
