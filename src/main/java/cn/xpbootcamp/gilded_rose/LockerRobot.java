package cn.xpbootcamp.gilded_rose;

import cn.xpbootcamp.gilded_rose.exception.InvalidBagException;
import cn.xpbootcamp.gilded_rose.exception.InvalidTicketException;

public class LockerRobot {
    private Cabinet cabinet;

    public LockerRobot(Cabinet cabinet) {
        this.cabinet = cabinet;
    }

    public Ticket saveBag(Bag bag) {
        validateBag(bag);
        return cabinet.save(bag);
    }

    public Bag getBag(Ticket ticket) {
        validateTicket(ticket);
        return cabinet.get(ticket);
    }

    private void validateTicket(Ticket ticket) {
        if (ticket == null) {
            throw new InvalidTicketException("Please insert a ticket to get your bag.");
        }
    }

    private void validateBag(Bag bag) {
        if (bag == null) {
            throw new InvalidBagException("Please put a bag into the cabinet.");
        }
    }
}
