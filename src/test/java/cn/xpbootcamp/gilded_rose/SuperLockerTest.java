package cn.xpbootcamp.gilded_rose;

import cn.xpbootcamp.gilded_rose.exception.InsufficientLockersException;
import cn.xpbootcamp.gilded_rose.exception.InvalidBagException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static cn.xpbootcamp.gilded_rose.LockersFactory.createFullLocker;
import static cn.xpbootcamp.gilded_rose.LockersFactory.createLockersOfPlentyOfCapacity;
import static cn.xpbootcamp.gilded_rose.LockersFactory.createSpecificSizeLockerWithSomeCapacityLeft;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
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
                () -> superLockerRobot.saveBag(bag));
    }

    @Test
    void should_throw_invalid_bag_exception_when_super_robot_help_to_save_nothing_into_locker() {
        List<Locker> lockersOfPlentyOfCapacity = createLockersOfPlentyOfCapacity(1);
        SuperLockerRobot superLockerRobot = new SuperLockerRobot(lockersOfPlentyOfCapacity);
        assertThrows(InvalidBagException.class, () -> {
            superLockerRobot.saveBag(null);
        }).getMessage();
    }

    @Test
    void should_get_ticket_from_third_locker_when_three_lockers_are_of_10_20_30_percentage_capacity_left_in_order() {
        // Given
        List<Locker> lockers = new ArrayList<>();

        Locker tenPercentCapacityLeft = createSpecificSizeLockerWithSomeCapacityLeft(1000, 100);
        Locker twentyPercentCapacityLeft = createSpecificSizeLockerWithSomeCapacityLeft(100, 20);
        Locker thirtyPercentCapacityLeft = createSpecificSizeLockerWithSomeCapacityLeft(10, 3);
        lockers.add(tenPercentCapacityLeft);
        lockers.add(twentyPercentCapacityLeft);
        lockers.add(thirtyPercentCapacityLeft);

        Bag savedBag = new Bag();
        SuperLockerRobot lockerRobot = new SuperLockerRobot(lockers);

        // When
        Ticket ticket = lockerRobot.saveBag(savedBag);

        // Then
        Bag fetchedBag = lockerRobot.getLockerWithOrder(3).getBagFromLocker(ticket);
        assertSame(savedBag, fetchedBag);
    }

    @Test
    void should_get_ticket_from_second_locker_when_three_lockers_are_of_20_30_30_percentage_capacity_left_in_order() {
        // Given
        List<Locker> lockers = new ArrayList<>();

        Locker twentyPercentCapacityLeft = createSpecificSizeLockerWithSomeCapacityLeft(100, 20);
        Locker thirtyPercentCapacityLeftOne = createSpecificSizeLockerWithSomeCapacityLeft(10, 3);
        Locker thirtyPercentCapacityLeftAnother = createSpecificSizeLockerWithSomeCapacityLeft(10, 3);
        lockers.add(twentyPercentCapacityLeft);
        lockers.add(thirtyPercentCapacityLeftOne);
        lockers.add(thirtyPercentCapacityLeftAnother);

        Bag savedBag = new Bag();
        SuperLockerRobot lockerRobot = new SuperLockerRobot(lockers);

        // When
        Ticket ticket = lockerRobot.saveBag(savedBag);

        // Then
        Bag fetchedBag = lockerRobot.getLockerWithOrder(2).getBagFromLocker(ticket);
        assertSame(savedBag, fetchedBag);
    }

    @Test
    void should_get_ticket_from_first_locker_when_three_lockers_are_of_20_20_20_percentage_capacity_left_in_order() {
        // Given
        List<Locker> lockers = new ArrayList<>();

        Locker twentyPercentCapacityLeftFirst = createSpecificSizeLockerWithSomeCapacityLeft(10, 2);
        Locker twentyPercentCapacityLeftSecond = createSpecificSizeLockerWithSomeCapacityLeft(100, 20);
        Locker twentyPercentCapacityLeftThird = createSpecificSizeLockerWithSomeCapacityLeft(1000, 200);
        lockers.add(twentyPercentCapacityLeftFirst);
        lockers.add(twentyPercentCapacityLeftSecond);
        lockers.add(twentyPercentCapacityLeftThird);

        Bag savedBag = new Bag();
        SuperLockerRobot lockerRobot = new SuperLockerRobot(lockers);

        // When
        Ticket ticket = lockerRobot.saveBag(savedBag);

        // Then
        Bag fetchedBag = lockerRobot.getLockerWithOrder(1).getBagFromLocker(ticket);
        assertSame(savedBag, fetchedBag);
    }

    @Test
    void should_get_corresponded_bag_when_give_right_ticket_to_the_robot() {
        List<Locker> lockersOfPlentyOfCapacity = createLockersOfPlentyOfCapacity(1);
        SuperLockerRobot lockerRobot = new SuperLockerRobot(lockersOfPlentyOfCapacity);
        Bag savedBag = new Bag();
        Ticket ticket = lockerRobot.saveBag(savedBag);

        Bag fetchedBag = lockerRobot.getBag(ticket);
        assertSame(savedBag, fetchedBag);
    }
}
