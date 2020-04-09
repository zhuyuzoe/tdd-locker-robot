package cn.xpbootcamp.gilded_rose;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static cn.xpbootcamp.gilded_rose.CabinetFactory.createLockerWithSomeCapacityLeft;
import static org.junit.jupiter.api.Assertions.assertSame;

public class SmartRobotSaveAndGetBagTest {

    @Test
    void should_get_ticket_from_first_locker_when_cabinet_with_first_locker_of_3_capacity_left_and_second_of_2() {
        List<Locker> lockers = new ArrayList<>();
        Locker firstLocker = createLockerWithSomeCapacityLeft(3);
        Locker secondLocker = createLockerWithSomeCapacityLeft(2);

        lockers.add(firstLocker);
        lockers.add(secondLocker);

        Cabinet cabinet = new Cabinet(lockers);
        Bag savedBag = new Bag();

        LockerRobot lockerRobot = new LockerRobot(cabinet);
        Ticket ticket = lockerRobot.saveBag(savedBag);

        Bag fetchedBag = cabinet.getLockerWithOrder(1).getBagFromLocker(ticket);
        assertSame(savedBag, fetchedBag);
    }

    @Test
    void should_get_ticket_from_second_locker_when_cabinet_with_first_locker_of_2_capacity_left_and_second_of_3() {
        List<Locker> lockers = new ArrayList<>();
        Locker firstLocker = createLockerWithSomeCapacityLeft(2);
        Locker secondLocker = createLockerWithSomeCapacityLeft(3);

        lockers.add(firstLocker);
        lockers.add(secondLocker);

        Cabinet cabinet = new Cabinet(lockers);
        Bag savedBag = new Bag();

        LockerRobot lockerRobot = new LockerRobot(cabinet);
        Ticket ticket = lockerRobot.saveBag(savedBag);

        Bag fetchedBag = cabinet.getLockerWithOrder(2).getBagFromLocker(ticket);
        assertSame(savedBag, fetchedBag);
    }
}
