package se.iuh.factorypatern.ModernFamily;

import se.iuh.factorypatern.AbstracProducts.CoffeeTable;

public class ModernCoffeeTable implements CoffeeTable {
    @Override
    public void putCoffee() {
        System.out.println("Put coffee on Modern Table");
    }

    @Override
    public String getSize() {
        return "medium";
    }
}
