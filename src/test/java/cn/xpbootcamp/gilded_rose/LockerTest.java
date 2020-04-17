package cn.xpbootcamp.gilded_rose;

import cn.xpbootcamp.gilded_rose.exception.InsufficientLockersException;
import cn.xpbootcamp.gilded_rose.exception.InvalidBagException;
import cn.xpbootcamp.gilded_rose.exception.InvalidTicketException;
import org.junit.jupiter.api.Test;

import static cn.xpbootcamp.gilded_rose.LockersFactory.createEmptyLocker;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LockerTest {
    @Test
    void should_get_a_ticket_when_save_the_bag() {
        Locker emptyLocker = createEmptyLocker();
        Ticket ticket = emptyLocker.saveBagIntoLocker(new Bag());
        assertNotNull(ticket);
    }

    @Test
    void should_throw_with_message_when_put_nothing_in_the_cabinet() {
        Locker emptyLocker = createEmptyLocker();
        assertThrows(InvalidBagException.class, () -> {
            emptyLocker.saveBagIntoLocker(null);
        }).getMessage();
    }

    @Test
    void should_get_corresponded_bag_given_ticket() {
        Locker emptyLocker = createEmptyLocker();
        Bag savedBag = new Bag();
        Ticket ticket = emptyLocker.saveBagIntoLocker(savedBag);
        Bag returnedBag = emptyLocker.getBagFromLocker(ticket);

        assertNotNull(returnedBag);
        assertSame(savedBag, returnedBag);
    }

    @Test
    void should_throw_with_message_if_no_ticket_provided() {
        Locker emptyLocker = createEmptyLocker();
        emptyLocker.saveBagIntoLocker(new Bag());
        assertThrows(
                InvalidTicketException.class,
                () -> emptyLocker.getBagFromLocker(null)).getMessage();
    }

    @Test
    void should_throw_with_message_if_ticket_is_invalid() {
        Locker emptyLocker = createEmptyLocker();
        emptyLocker.saveBagIntoLocker(new Bag());

        Ticket invalidTicket = new Ticket();

        assertThrows(InvalidTicketException.class, () -> {
            emptyLocker.getBagFromLocker(invalidTicket);
        }).getMessage();

    }

    @Test
    void should_throw_with_message_if_ticket_is_invalid_since_is_used() {
        Locker emptyLocker = createEmptyLocker();
        Ticket usedTicket = emptyLocker.saveBagIntoLocker(new Bag());
        emptyLocker.getBagFromLocker(usedTicket);

        assertThrows(InvalidTicketException.class, () -> {
            emptyLocker.getBagFromLocker(usedTicket);
        }).getMessage();

    }

    @Test
    void should_save_bag_if_there_are_empty_lockers_left() {
        Locker locker = new Locker(10);
        locker.saveBagIntoLocker(new Bag());

        Ticket ticket = locker.saveBagIntoLocker(new Bag());

        assertNotNull(ticket);
    }

    @Test
    void should_throw_if_cabinet_is_full() {
        Locker locker = new Locker(1);
        locker.saveBagIntoLocker(new Bag());

        assertThrows(
                InsufficientLockersException.class,
                () -> locker.saveBagIntoLocker(new Bag())
        ).getMessage();

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
