package cn.xpbootcamp.gilded_rose;

import cn.xpbootcamp.gilded_rose.exception.InsufficientLockersException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static cn.xpbootcamp.gilded_rose.LockersFactory.createFullLocker;
import static cn.xpbootcamp.gilded_rose.LockersFactory.createLockersOfPlentyOfCapacity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SuperLockerTest {
    private final int CABINET_DEFAULT_CAPACITY = 10;

    @Test
    void should_get_the_ticket_when_super_robot_save_bag_into_one_locker_with_enough_capacity() {
        List<Locker> lockersOfPlentyOfCapacity = createLockersOfPlentyOfCapacity(1);
        Bag bag = new Bag();
        SuperLockerRobot superLockerRobot = new SuperLockerRobot(lockersOfPlentyOfCapacity);
        Ticket ticket = superLockerRobot.saveBag(bag);
        assertNotNull(ticket);
    }

    @Test
    void should_throw_insufficient_locker_exception_when_super_robot_save_bag_into_one_locker_which_is_full() {
        List<Locker> lockers = new ArrayList<>();
        lockers.add(createFullLocker(CABINET_DEFAULT_CAPACITY));
        Bag bag = new Bag();
        SuperLockerRobot superLockerRobot = new SuperLockerRobot(lockers);
        assertThrows(InsufficientLockersException.class,
                () ->  superLockerRobot.saveBag(bag));
    }

}
