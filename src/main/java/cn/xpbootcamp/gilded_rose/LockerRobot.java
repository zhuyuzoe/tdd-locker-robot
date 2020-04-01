package cn.xpbootcamp.gilded_rose;

public class LockerRobot {
    private Cabinet cabinet;

    public LockerRobot(Cabinet cabinet) {
        this.cabinet = cabinet;
    }

    public LockerRobot() {}

    public Ticket saveBag(Bag bag) {

        return  cabinet.save(bag);
    }
}
