package cn.xpbootcamp.gilded_rose;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static cn.xpbootcamp.gilded_rose.CabinetFactory.createCabinetWithLockersOfCapacityLeft;
import static org.junit.jupiter.api.Assertions.assertSame;

public class SmartRobotSaveAndGetBagTest {

    @Test
    void should_get_ticket_from_first_locker_when_cabinet_with_first_locker_of_3_capacity_left_and_second_of_2() {
        // Given
        Cabinet cabinet = createCabinetWithLockersOfCapacityLeft(new ArrayList<>(Arrays.asList(3, 2)));
        Bag savedBag = new Bag();
        LockerRobot lockerRobot = new LockerRobot(cabinet);

        // When
        Ticket ticket = lockerRobot.saveBag(savedBag);

        // Then
        Bag fetchedBag = cabinet.getLockerWithOrder(1).getBagFromLocker(ticket);
        assertSame(savedBag, fetchedBag);
    }

    @Test
    void should_get_ticket_from_second_locker_when_cabinet_with_first_locker_of_2_capacity_left_and_second_of_3() {
        // Given
        Cabinet cabinet = createCabinetWithLockersOfCapacityLeft(new ArrayList<>(Arrays.asList(2, 3)));
        Bag savedBag = new Bag();
        LockerRobot lockerRobot = new LockerRobot(cabinet);

        // When
        Ticket ticket = lockerRobot.saveBag(savedBag);

        // Then
        Bag fetchedBag = cabinet.getLockerWithOrder(2).getBagFromLocker(ticket);
        assertSame(savedBag, fetchedBag);
    }

    @Test
    void should_get_ticket_from_first_locker_when_cabinet_with_both_lockers_of_2_capacity_left() {
        // Given
        Cabinet cabinet = createCabinetWithLockersOfCapacityLeft(new ArrayList<>(Arrays.asList(2, 2)));
        Bag savedBag = new Bag();
        LockerRobot lockerRobot = new LockerRobot(cabinet);

        // When
        Ticket ticket = lockerRobot.saveBag(savedBag);

        // Then
        Bag fetchedBag = cabinet.getLockerWithOrder(1).getBagFromLocker(ticket);
        assertSame(savedBag, fetchedBag);
    }

    @Test
    void should_get_ticket_from_third_locker_when_cabinet_with_three_lockers_are_of_1_2_3_capacity_left_in_order() {
        // Given
        Cabinet cabinet = createCabinetWithLockersOfCapacityLeft(new ArrayList<>(Arrays.asList(1, 2, 3)));
        Bag savedBag = new Bag();
        LockerRobot lockerRobot = new LockerRobot(cabinet);

        // When
        Ticket ticket = lockerRobot.saveBag(savedBag);

        // Then
        Bag fetchedBag = cabinet.getLockerWithOrder(3).getBagFromLocker(ticket);
        assertSame(savedBag, fetchedBag);
    }

    @Test
    void should_get_ticket_from_second_locker_when_cabinet_with_three_lockers_are_of_2_3_3_capacity_left_in_order() {
        // Given
        Cabinet cabinet = createCabinetWithLockersOfCapacityLeft(new ArrayList<>(Arrays.asList(2, 3, 3)));
        Bag savedBag = new Bag();
        LockerRobot lockerRobot = new LockerRobot(cabinet);

        // When
        Ticket ticket = lockerRobot.saveBag(savedBag);

        // Then
        Bag fetchedBag = cabinet.getLockerWithOrder(2).getBagFromLocker(ticket);
        assertSame(savedBag, fetchedBag);
    }
}
