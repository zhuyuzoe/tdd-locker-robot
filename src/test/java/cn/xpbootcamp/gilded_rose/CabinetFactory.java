package cn.xpbootcamp.gilded_rose;


class CabinetFactory {
    static Cabinet createCabinetWithPlentyOfCapacity() {
        return new Cabinet(Integer.MAX_VALUE);
    }
}
