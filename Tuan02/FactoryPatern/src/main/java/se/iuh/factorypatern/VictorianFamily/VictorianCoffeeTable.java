package se.iuh.factorypatern.VictorianFamily;

import se.iuh.factorypatern.AbstracProducts.CoffeeTable;

public class VictorianCoffeeTable implements CoffeeTable {
    @Override
    public void putCoffee() {
        System.out.println("Put coffee on Victorian Table");
    }

    @Override
    public String getSize() {
        return "Large";
    }
}
