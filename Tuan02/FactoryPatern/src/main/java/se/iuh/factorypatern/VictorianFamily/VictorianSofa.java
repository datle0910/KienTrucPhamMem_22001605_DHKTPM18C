package se.iuh.factorypatern.VictorianFamily;

import se.iuh.factorypatern.AbstracProducts.Sofa;

public class VictorianSofa implements Sofa {
    @Override
    public void lieOn() {
        System.out.println("Lie on Victorian Sofa");
    }

    @Override
    public boolean isComfortable() {
        return false;
    }
}
