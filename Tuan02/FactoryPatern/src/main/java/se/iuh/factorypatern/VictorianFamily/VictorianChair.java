package se.iuh.factorypatern.VictorianFamily;

import se.iuh.factorypatern.AbstracProducts.Chair;

public class VictorianChair implements Chair {
    @Override
    public void sitOn() {
        System.out.println("Sit on Victorian Chair");
    }

    @Override
    public boolean hasLegs() {
        return false;
    }
}
