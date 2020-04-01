package cn.xpbootcamp.gilded_rose;
import cn.xpbootcamp.gilded_rose.exception.InvalidTicketException;
import org.junit.jupiter.api.Test;

import static cn.xpbootcamp.gilded_rose.CabinetFactory.createCabinetWithPlentyOfCapacity;
import static org.junit.jupiter.api.Assertions.*;

public class RobotSaveAndGetBagTest {
    @Test
    void should_get_the_ticket_when_the_robot_help_to_save_bag_into_locker() {
        Cabinet cabinet = createCabinetWithPlentyOfCapacity();
        Bag bag = new Bag();
        LockerRobot lockerRobot = new LockerRobot(cabinet);
        Ticket ticket = lockerRobot.saveBag(bag);
        assertNotNull(ticket);
    }

    @Test
    void should_get_error_message_when_the_robot_help_to_save_nothing_into_locker() {
        Cabinet cabinet = createCabinetWithPlentyOfCapacity();
        LockerRobot lockerRobot = new LockerRobot(cabinet);
        String message = assertThrows(IllegalArgumentException.class, () -> {
            lockerRobot.saveBag(null);
        }).getMessage();
        assertEquals("Please put a bag into the cabinet.", message);
    }

    @Test
    void should_get_corresponded_bag_when_give_right_ticket_to_the_robot() {
        Cabinet cabinet = createCabinetWithPlentyOfCapacity();
        LockerRobot lockerRobot = new LockerRobot(cabinet);
        Bag savedBag = new Bag();
        Ticket ticket = lockerRobot.saveBag(savedBag);

        Bag fetchedBag = lockerRobot.getBag(ticket);
        assertSame(savedBag, fetchedBag);
    }

    @Test
    void should_get_error_message_without_ticket_for_robot_to_get_bag() {
        Cabinet cabinet = createCabinetWithPlentyOfCapacity();
        LockerRobot lockerRobot = new LockerRobot(cabinet);
        Bag savedBag = new Bag();
        lockerRobot.saveBag(savedBag);

        String message = assertThrows(InvalidTicketException.class, () -> {
            lockerRobot.getBag(null);
        }).getMessage();
        assertEquals("Please insert a ticket to get your bag.", message);
    }
}
