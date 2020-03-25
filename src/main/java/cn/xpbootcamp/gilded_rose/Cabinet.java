package cn.xpbootcamp.gilded_rose;

public class Cabinet {
    public Ticket save(Bag bag) {
        if (bag == null) {
            throw new IllegalArgumentException("Please put a bag into the cabinet.");
        }
        return new Ticket();
    }
}
