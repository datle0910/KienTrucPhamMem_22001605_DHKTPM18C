package se.iuh.factorypatern;

import se.iuh.factorypatern.AbstracProducts.Chair;
import se.iuh.factorypatern.AbstracProducts.CoffeeTable;
import se.iuh.factorypatern.AbstracProducts.Sofa;
import se.iuh.factorypatern.VictorianFamily.VictorianChair;
import se.iuh.factorypatern.VictorianFamily.VictorianCoffeeTable;
import se.iuh.factorypatern.VictorianFamily.VictorianSofa;

public class VictorianFurnitureFactory
        implements FurnitureFactory{

    public Chair createChair(){

        return new VictorianChair();

    }

    public Sofa createSofa(){

        return new VictorianSofa();

    }

    public CoffeeTable createCoffeeTable(){

        return new VictorianCoffeeTable();

    }

}
