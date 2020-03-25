package cn.xpbootcamp.gilded_rose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SaveAndGetBagTest {
    @Test
    void should_get_a_ticket_when_save_the_bag() {
        Cabinet cabinet = new Cabinet();
        Ticket ticket = cabinet.save(new Bag());
        assertNotNull(ticket);
    }
}
