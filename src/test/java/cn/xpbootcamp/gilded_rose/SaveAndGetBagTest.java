package cn.xpbootcamp.gilded_rose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SaveAndGetBagTest {
    @Test
    void should_get_a_ticket_when_save_the_bag() {
        Cabinet cabinet = new Cabinet();
        Ticket ticket = cabinet.save(new Bag());
        assertNotNull(ticket);
    }

    @Test
    void should_throw_with_message_when_put_nothing_in_the_cabinet() {
        Cabinet cabinet = new Cabinet();
        String message = assertThrows(IllegalArgumentException.class, () -> {
            cabinet.save(null);
        }).getMessage();
        assertEquals("Please put a bag into the cabinet.", message);
    }

    @Test
    void should_get_corresponded_bag_given_ticket() {
        Cabinet cabinet = new Cabinet();
        Bag savedBag = new Bag();
        Ticket ticket = cabinet.save(savedBag);
        Bag returnedBag = cabinet.getBag(ticket);

        assertNotNull(returnedBag);
        assertSame(savedBag, returnedBag);
    }

    @Test
    void should_throw_with_message_if_no_ticket_provided() {
        Cabinet cabinet = new Cabinet();
        cabinet.save(new Bag());
        String message = assertThrows(
                IllegalArgumentException.class,
                () -> cabinet.getBag(null)).getMessage();
        assertEquals("Please insert a ticket to get your bag.", message);
    }

    @Test
    void should_throw_with_message_if_ticket_is_invalid() {
        Cabinet cabinet = new Cabinet();
        cabinet.save(new Bag());

        Ticket invalidTicket = new Ticket();

        String message = assertThrows(IllegalArgumentException.class, () -> {
            cabinet.getBag(invalidTicket);
        }).getMessage();

        assertEquals("Please insert a valid ticket.", message);
    }

    @Test
    void should_throw_with_message_if_ticket_is_invalid_since_is_used() {
        Cabinet cabinet = new Cabinet();
        Ticket usedTicket = cabinet.save(new Bag());
        cabinet.getBag(usedTicket);

        String message = assertThrows(IllegalArgumentException.class, () -> {
            cabinet.getBag(usedTicket);
        }).getMessage();

        assertEquals("Please insert a valid ticket.", message);
    }
}
