package se.iuh.factorypatern.ModernFamily;

import se.iuh.factorypatern.AbstracProducts.Sofa;

public class ModernSofa implements Sofa {
    @Override
    public void lieOn() {
        System.out.println("Lie on Modern Sofa");
    }

    @Override
    public boolean isComfortable() {
        return true;
    }
}
