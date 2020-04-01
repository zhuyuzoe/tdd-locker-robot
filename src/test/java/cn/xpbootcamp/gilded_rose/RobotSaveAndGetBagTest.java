package cn.xpbootcamp.gilded_rose;
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
}
