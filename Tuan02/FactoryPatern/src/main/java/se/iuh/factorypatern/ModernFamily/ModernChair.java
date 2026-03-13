package se.iuh.factorypatern.ModernFamily;

import se.iuh.factorypatern.AbstracProducts.Chair;

public class ModernChair implements Chair {
    @Override
    public void sitOn() {
        System.out.println("Sit on Modern Chair");
    }

    @Override
    public boolean hasLegs() {
        return true;
    }
}
