package se.iuh.factorypatern;

import se.iuh.factorypatern.AbstracProducts.Chair;
import se.iuh.factorypatern.AbstracProducts.CoffeeTable;
import se.iuh.factorypatern.AbstracProducts.Sofa;
import se.iuh.factorypatern.ModernFamily.ModernChair;
import se.iuh.factorypatern.ModernFamily.ModernCoffeeTable;
import se.iuh.factorypatern.ModernFamily.ModernSofa;

public class ModernFurnitureFactory
        implements FurnitureFactory{

    public Chair createChair(){

        return new ModernChair();

    }

    public Sofa createSofa(){

        return new ModernSofa();

    }

    public CoffeeTable createCoffeeTable(){

        return new ModernCoffeeTable();

    }

}