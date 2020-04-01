package cn.xpbootcamp.gilded_rose;

public class Cabinet {
    private Locker locker;

    public Cabinet(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException();
        }
        this.locker = new Locker(capacity);
    }

    public Ticket save(Bag bag) {
        if (bag == null) {
            throw new IllegalArgumentException("Please put a bag into the cabinet.");
        }

        Ticket ticket = new Ticket();
        locker.saveBagIntoLocker(bag, ticket);
        return ticket;
    }


    public Bag get(Ticket ticket) {
        if (ticket == null) {
            throw new IllegalArgumentException("Please insert a ticket to get your bag.");
        }
        if (locker.getLocker().containsKey(ticket)) {
            return locker.getBagFromLocker(ticket);
        }

        throw new IllegalArgumentException("Please insert a valid ticket.");
    }
}
