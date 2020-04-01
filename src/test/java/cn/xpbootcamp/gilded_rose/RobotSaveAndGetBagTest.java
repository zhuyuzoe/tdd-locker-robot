package cn.xpbootcamp.gilded_rose;
import cn.xpbootcamp.gilded_rose.exception.InsufficientLockersException;
import cn.xpbootcamp.gilded_rose.exception.InvalidTicketException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static cn.xpbootcamp.gilded_rose.CabinetFactory.createCabinetWithLockersOfPlentyOfCapacity;
import static cn.xpbootcamp.gilded_rose.CabinetFactory.createEmptyLocker;
import static cn.xpbootcamp.gilded_rose.CabinetFactory.createFullLocker;
import static org.junit.jupiter.api.Assertions.*;

public class RobotSaveAndGetBagTest {

    private final int CABINET_DEFAULT_CAPACITY = 10;

    @Test
    void should_get_the_ticket_when_the_robot_help_to_save_bag_into_locker() {
        Cabinet cabinet = createCabinetWithLockersOfPlentyOfCapacity(1);
        Bag bag = new Bag();
        LockerRobot lockerRobot = new LockerRobot(cabinet);
        Ticket ticket = lockerRobot.saveBag(bag);
        assertNotNull(ticket);
    }

    @Test
    void should_get_error_message_when_the_robot_help_to_save_nothing_into_locker() {
        Cabinet cabinet = createCabinetWithLockersOfPlentyOfCapacity(1);
        LockerRobot lockerRobot = new LockerRobot(cabinet);
        String message = assertThrows(IllegalArgumentException.class, () -> {
            lockerRobot.saveBag(null);
        }).getMessage();
        assertEquals("Please put a bag into the cabinet.", message);
    }

    @Test
    void should_get_corresponded_bag_when_give_right_ticket_to_the_robot() {
        Cabinet cabinet = createCabinetWithLockersOfPlentyOfCapacity(1);
        LockerRobot lockerRobot = new LockerRobot(cabinet);
        Bag savedBag = new Bag();
        Ticket ticket = lockerRobot.saveBag(savedBag);

        Bag fetchedBag = lockerRobot.getBag(ticket);
        assertSame(savedBag, fetchedBag);
    }

    @Test
    void should_get_error_message_without_ticket_for_robot_to_get_bag() {
        Cabinet cabinet = createCabinetWithLockersOfPlentyOfCapacity(1);
        LockerRobot lockerRobot = new LockerRobot(cabinet);
        Bag savedBag = new Bag();
        lockerRobot.saveBag(savedBag);

        String message = assertThrows(InvalidTicketException.class, () -> {
            lockerRobot.getBag(null);
        }).getMessage();
        assertEquals("Please insert a ticket to get your bag.", message);
    }

    @Test
    void should_get_error_message_with_invalid_ticket_for_robot_to_get_bag() {
        Cabinet cabinet = createCabinetWithLockersOfPlentyOfCapacity(1);
        LockerRobot lockerRobot = new LockerRobot(cabinet);
        Bag savedBag = new Bag();
        lockerRobot.saveBag(savedBag);

        Ticket invalidTicket = new Ticket();

        String message = assertThrows(InvalidTicketException.class, () -> {
            lockerRobot.getBag(invalidTicket);
        }).getMessage();
        assertEquals("Please insert a valid ticket.", message);
    }

    @Test
    void should_get_error_message_with_used_ticket_for_robot_to_get_bag() {
        Cabinet cabinet = createCabinetWithLockersOfPlentyOfCapacity(1);
        LockerRobot lockerRobot = new LockerRobot(cabinet);
        Bag savedBag = new Bag();
        Ticket usedTicket = lockerRobot.saveBag(savedBag);
        cabinet.get(usedTicket);


        String message = assertThrows(InvalidTicketException.class, () -> {
            lockerRobot.getBag(usedTicket);
        }).getMessage();
        assertEquals("Please insert a valid ticket.", message);
    }

    @Test
    void should_throw_if_cabinet_lockers_are_full() {
        List<Locker> lockers = new ArrayList<>();
        lockers.add(createFullLocker(CABINET_DEFAULT_CAPACITY));
        Cabinet fullCabinet = new Cabinet(lockers);
        Bag savedBag = new Bag();
        LockerRobot lockerRobot = new LockerRobot(fullCabinet);

        InsufficientLockersException error = assertThrows(
                InsufficientLockersException.class,
                () ->  lockerRobot.saveBag(savedBag));
        assertEquals("Insufficient empty lockers.", error.getMessage());
    }

    @Test
    void should_get_ticket_from_first_locker_when_cabinet_with_two_empty_lockers() {
        Cabinet emptyCabinet = createCabinetWithLockersOfPlentyOfCapacity(2);
        Bag savedBag = new Bag();

        LockerRobot lockerRobot = new LockerRobot(emptyCabinet);
        Ticket ticket = lockerRobot.saveBag(savedBag);

        Locker firstLocker = emptyCabinet.getLockerWithOrder(1);
        Bag fetchedBag = firstLocker.getBagFromLocker(ticket);
        assertSame(savedBag, fetchedBag);
    }

    @Test
    void should_get_ticket_from_second_locker_when_cabinet_with_first_locker_full_and_second_empty() {
        List<Locker> lockers = new ArrayList<>();
        Locker firstFullLocker = createFullLocker(CABINET_DEFAULT_CAPACITY);
        Locker secondEmptyLocker = createEmptyLocker();

        lockers.add(firstFullLocker);
        lockers.add(secondEmptyLocker);

        Cabinet cabinet = new Cabinet(lockers);
        Bag savedBag = new Bag();

        LockerRobot lockerRobot = new LockerRobot(cabinet);
        Ticket ticket = lockerRobot.saveBag(savedBag);

        Locker secondLocker = cabinet.getLockerWithOrder(2);
        Bag fetchedBag = secondLocker.getBagFromLocker(ticket);
        assertSame(savedBag, fetchedBag);

    }

    @Test
    void should_get_error_message_when_two_lockers_are_full() {
        List<Locker> lockers = new ArrayList<>();
        Locker firstFullLocker = createFullLocker(CABINET_DEFAULT_CAPACITY);
        Locker secondFullLocker = createFullLocker(CABINET_DEFAULT_CAPACITY + 1);

        lockers.add(firstFullLocker);
        lockers.add(secondFullLocker);

        Cabinet cabinet = new Cabinet(lockers);
        Bag savedBag = new Bag();

        LockerRobot lockerRobot = new LockerRobot(cabinet);
        InsufficientLockersException error = assertThrows(
                InsufficientLockersException.class,
                () ->  lockerRobot.saveBag(savedBag));
        assertEquals("Insufficient empty lockers.", error.getMessage());

    }
}
