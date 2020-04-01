package cn.xpbootcamp.gilded_rose;

import cn.xpbootcamp.gilded_rose.exception.InsufficientLockersException;
import cn.xpbootcamp.gilded_rose.exception.InvalidBagException;
import cn.xpbootcamp.gilded_rose.exception.InvalidTicketException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static cn.xpbootcamp.gilded_rose.CabinetFactory.createCabinetWithLockersOfPlentyOfCapacity;
import static org.junit.jupiter.api.Assertions.*;

public class SaveAndGetBagTest {
    @Test
    void should_get_a_ticket_when_save_the_bag() {
        Cabinet cabinet = createCabinetWithLockersOfPlentyOfCapacity(1);
        Ticket ticket = cabinet.save(new Bag());
        assertNotNull(ticket);
    }

    @Test
    void should_throw_with_message_when_put_nothing_in_the_cabinet() {
        Cabinet cabinet = createCabinetWithLockersOfPlentyOfCapacity(1);
        String message = assertThrows(InvalidBagException.class, () -> {
            cabinet.save(null);
        }).getMessage();
        assertEquals("Please put a bag into the cabinet.", message);
    }

    @Test
    void should_get_corresponded_bag_given_ticket() {
        Cabinet cabinet = createCabinetWithLockersOfPlentyOfCapacity(1);
        Bag savedBag = new Bag();
        Ticket ticket = cabinet.save(savedBag);
        Bag returnedBag = cabinet.get(ticket);

        assertNotNull(returnedBag);
        assertSame(savedBag, returnedBag);
    }

    @Test
    void should_throw_with_message_if_no_ticket_provided() {
        Cabinet cabinet = createCabinetWithLockersOfPlentyOfCapacity(1);
        cabinet.save(new Bag());
        String message = assertThrows(
                InvalidTicketException.class,
                () -> cabinet.get(null)).getMessage();
        assertEquals("Please insert a ticket to get your bag.", message);
    }

    @Test
    void should_throw_with_message_if_ticket_is_invalid() {
        Cabinet cabinet = createCabinetWithLockersOfPlentyOfCapacity(1);
        cabinet.save(new Bag());

        Ticket invalidTicket = new Ticket();

        String message = assertThrows(InvalidTicketException.class, () -> {
            cabinet.get(invalidTicket);
        }).getMessage();

        assertEquals("Please insert a valid ticket.", message);
    }

    @Test
    void should_throw_with_message_if_ticket_is_invalid_since_is_used() {
        Cabinet cabinet = createCabinetWithLockersOfPlentyOfCapacity(1);
        Ticket usedTicket = cabinet.save(new Bag());
        cabinet.get(usedTicket);

        String message = assertThrows(InvalidTicketException.class, () -> {
            cabinet.get(usedTicket);
        }).getMessage();

        assertEquals("Please insert a valid ticket.", message);
    }

    @Test
    void should_save_bag_if_there_are_empty_lockers_left() {
        List<Locker> lockers = new ArrayList<>();
        lockers.add(new Locker(10));
        Cabinet cabinet = new Cabinet(lockers);
        Ticket ticket = cabinet.save(new Bag());

        assertNotNull(ticket);
    }

    @Test
    void should_throw_if_cabinet_is_full() {
        List<Locker> lockers = new ArrayList<>();
        lockers.add(new Locker(1));
        Cabinet cabinet = new Cabinet(lockers);
        cabinet.save(new Bag());

        String message = assertThrows(
                InsufficientLockersException.class,
                () -> cabinet.save(new Bag())
        ).getMessage();

        assertEquals("Insufficient empty lockers.", message);
    }

    @Test
    void should_get_error_when_capacity_is_0() {
        assertThrows(IllegalArgumentException.class, () ->
                new Locker(0));
    }

    @Test
    void should_get_error_when_capacity_is_negative() {
        assertThrows(IllegalArgumentException.class, () ->
                new Locker(-1));
    }
}
