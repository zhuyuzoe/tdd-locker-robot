package cn.xpbootcamp.gilded_rose;


class CabinetFactory {
    static Cabinet createCabinetWithPlentyOfCapacity() {
        return new Cabinet(Integer.MAX_VALUE);
    }

    static Cabinet createCabinetWithFullLockers(int defaultCapacity) {

        Cabinet cabinet = new Cabinet(defaultCapacity);
        for (int i = 0; i < defaultCapacity; i++) {
            cabinet.save(new Bag());
        }

        return cabinet;
    }
}
