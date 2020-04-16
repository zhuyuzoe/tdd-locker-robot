package cn.xpbootcamp.gilded_rose;

import org.junit.jupiter.api.Test;

import java.util.List;

import static cn.xpbootcamp.gilded_rose.LockersFactory.createLockersOfPlentyOfCapacity;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SuperLockerTest {

    @Test
    void should_get_the_ticket_when_super_robot_save_bag_into_cabinet_with_one_locker_of_enough_capacity() {
        List<Locker> lockersOfPlentyOfCapacity = createLockersOfPlentyOfCapacity(1);
        Bag bag = new Bag();
        SuperLockerRobot superLockerRobot = new SuperLockerRobot(lockersOfPlentyOfCapacity);
        Ticket ticket = superLockerRobot.saveBag(bag);
        assertNotNull(ticket);
    }
}
