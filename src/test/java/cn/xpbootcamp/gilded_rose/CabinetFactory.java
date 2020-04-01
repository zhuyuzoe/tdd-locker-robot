package cn.xpbootcamp.gilded_rose;

import java.util.ArrayList;
import java.util.List;

class CabinetFactory {

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
}
