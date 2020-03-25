package cn.xpbootcamp.gilded_rose;

public class Cabinet {
    private Locker locker = new Locker();

    public Ticket save(Bag bag) {
        if (bag == null) {
            throw new IllegalArgumentException("Please put a bag into the cabinet.");
        }
        Ticket ticket = new Ticket();
        locker.saveBagIntoLocker(bag, ticket);
        return ticket;
    }


    public Bag getBag(Ticket ticket) {
        if (ticket == null) {
            throw new IllegalArgumentException("Please insert a ticket to get your bag.");
        }
        return locker.getBagFromLocker(ticket);
    }
}
