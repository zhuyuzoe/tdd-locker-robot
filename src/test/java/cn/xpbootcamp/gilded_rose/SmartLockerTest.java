package cn.xpbootcamp.gilded_rose;

import cn.xpbootcamp.gilded_rose.exception.InvalidTicketException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static cn.xpbootcamp.gilded_rose.LockersFactory.createLockersOfCapacityLeft;
import static cn.xpbootcamp.gilded_rose.LockersFactory.getLockerWithOrder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SmartLockerTest {

    @Test
    void should_get_ticket_from_first_locker_when_cabinet_with_first_locker_of_3_capacity_left_and_second_of_2() {
        // Given
        List<Locker> lockersOfCapacityLeft = createLockersOfCapacityLeft(new ArrayList<>(Arrays.asList(3, 2)));
        Bag savedBag = new Bag();
        SmartLockerRobot lockerRobot = new SmartLockerRobot(lockersOfCapacityLeft);

        // When
        Ticket ticket = lockerRobot.saveBag(savedBag);

        // Then
        Bag fetchedBag = getLockerWithOrder(lockersOfCapacityLeft,1).getBagFromLocker(ticket);
        assertSame(savedBag, fetchedBag);
    }

    @Test
    void should_get_ticket_from_second_locker_when_cabinet_with_first_locker_of_2_capacity_left_and_second_of_3() {
        // Given
        List<Locker> lockersOfCapacityLeft = createLockersOfCapacityLeft(new ArrayList<>(Arrays.asList(2, 3)));
        Bag savedBag = new Bag();
        SmartLockerRobot lockerRobot = new SmartLockerRobot(lockersOfCapacityLeft);

        // When
        Ticket ticket = lockerRobot.saveBag(savedBag);

        // Then
        Bag fetchedBag = getLockerWithOrder(lockersOfCapacityLeft,2).getBagFromLocker(ticket);
        assertSame(savedBag, fetchedBag);
    }

    @Test
    void should_get_ticket_from_first_locker_when_cabinet_with_both_lockers_of_2_capacity_left() {
        // Given
        List<Locker> lockersOfCapacityLeft = createLockersOfCapacityLeft(new ArrayList<>(Arrays.asList(2, 2)));
        Bag savedBag = new Bag();
        SmartLockerRobot lockerRobot = new SmartLockerRobot(lockersOfCapacityLeft);

        // When
        Ticket ticket = lockerRobot.saveBag(savedBag);

        // Then
        Bag fetchedBag = getLockerWithOrder(lockersOfCapacityLeft,1).getBagFromLocker(ticket);
        assertSame(savedBag, fetchedBag);
    }

    @Test
    void should_get_ticket_from_third_locker_when_cabinet_with_three_lockers_are_of_1_2_3_capacity_left_in_order() {
        // Given
        List<Locker> lockersOfCapacityLeft = createLockersOfCapacityLeft(new ArrayList<>(Arrays.asList(1, 2, 3)));
        Bag savedBag = new Bag();
        SmartLockerRobot lockerRobot = new SmartLockerRobot(lockersOfCapacityLeft);

        // When
        Ticket ticket = lockerRobot.saveBag(savedBag);

        // Then
        Bag fetchedBag = getLockerWithOrder(lockersOfCapacityLeft,3).getBagFromLocker(ticket);
        assertSame(savedBag, fetchedBag);
    }

    @Test
    void should_get_ticket_from_second_locker_when_cabinet_with_three_lockers_are_of_2_3_3_capacity_left_in_order() {
        // Given
        List<Locker> lockersOfCapacityLeft = createLockersOfCapacityLeft(new ArrayList<>(Arrays.asList(2, 3, 3)));
        Bag savedBag = new Bag();
        SmartLockerRobot lockerRobot = new SmartLockerRobot(lockersOfCapacityLeft);

        // When
        Ticket ticket = lockerRobot.saveBag(savedBag);

        // Then
        Bag fetchedBag = getLockerWithOrder(lockersOfCapacityLeft,2).getBagFromLocker(ticket);
        assertSame(savedBag, fetchedBag);
    }

    @Test
    void should_get_ticket_from_first_locker_when_cabinet_with_three_lockers_are_of_2_2_2_capacity_left_in_order() {
        // Given
        List<Locker> lockersOfCapacityLeft = createLockersOfCapacityLeft(new ArrayList<>(Arrays.asList(2, 2, 2)));
        Bag savedBag = new Bag();
        SmartLockerRobot lockerRobot = new SmartLockerRobot(lockersOfCapacityLeft);

        // When
        Ticket ticket = lockerRobot.saveBag(savedBag);

        // Then
        Bag fetchedBag = getLockerWithOrder(lockersOfCapacityLeft,1).getBagFromLocker(ticket);
        assertSame(savedBag, fetchedBag);
    }

    @Test
    void should_get_corresponded_bag_when_robot_save_bag_into_cabinet_with_first_locker_of_3_capacity_left_and_second_of_2_successfully_and_give_robot_corresponded_ticket() {
        // Given
        List<Locker> lockersOfCapacityLeft = createLockersOfCapacityLeft(new ArrayList<>(Arrays.asList(3, 2)));
        Bag savedBag = new Bag();
        SmartLockerRobot lockerRobot = new SmartLockerRobot(lockersOfCapacityLeft);

        // When
        Ticket ticket = lockerRobot.saveBag(savedBag);

        // Then
        Bag fetchedBag = lockerRobot.getBag(ticket);
        assertSame(savedBag, fetchedBag);
    }

    @Test
    void should_get_invalid_ticket_error_when_robot_save_bag_into_cabinet_with_first_locker_of_3_capacity_left_and_second_of_2_successfully_and_give_robot_no_ticket() {
        // Given
        List<Locker> lockersOfCapacityLeft = createLockersOfCapacityLeft(new ArrayList<>(Arrays.asList(3, 2)));
        Bag savedBag = new Bag();
        SmartLockerRobot lockerRobot = new SmartLockerRobot(lockersOfCapacityLeft);

        // When
        Ticket ticket = lockerRobot.saveBag(savedBag);

        // Then
        String message = assertThrows(InvalidTicketException.class, () -> {
            lockerRobot.getBag(null);
        }).getMessage();
        assertEquals("Please insert a ticket to get your bag.", message);
    }

    @Test
    void should_get_invalid_ticket_error_when_robot_save_bag_into_cabinet_with_first_locker_of_3_capacity_left_and_second_of_2_successfully_and_give_robot_invalid_ticket() {
        // Given
        List<Locker> lockersOfCapacityLeft = createLockersOfCapacityLeft(new ArrayList<>(Arrays.asList(3, 2)));
        Bag savedBag = new Bag();
        SmartLockerRobot lockerRobot = new SmartLockerRobot(lockersOfCapacityLeft);

        // When
        lockerRobot.saveBag(savedBag);

        // Then
        Ticket invalidTicket = new Ticket();
        String message = assertThrows(InvalidTicketException.class, () -> {
            lockerRobot.getBag(invalidTicket);
        }).getMessage();
        assertEquals("Please insert a valid ticket.", message);
    }

    @Test
    void should_get_corresponded_bag_when_robot_save_bag_into_cabinet_with_first_locker_of_2_capacity_left_and_second_third_of_3_successfully_and_give_robot_corresponded_ticket() {
        // Given
        List<Locker> lockersOfCapacityLeft = createLockersOfCapacityLeft(new ArrayList<>(Arrays.asList(2, 3, 3)));
        Bag savedBag = new Bag();
        SmartLockerRobot lockerRobot = new SmartLockerRobot(lockersOfCapacityLeft);

        // When
        Ticket ticket = lockerRobot.saveBag(savedBag);

        // Then
        Bag fetchedBag = lockerRobot.getBag(ticket);
        assertSame(savedBag, fetchedBag);
    }
}
