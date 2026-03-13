package se.iuh.factorypatern;

import se.iuh.factorypatern.AbstracProducts.Chair;
import se.iuh.factorypatern.AbstracProducts.CoffeeTable;
import se.iuh.factorypatern.AbstracProducts.Sofa;

public interface FurnitureFactory {

    Chair createChair();

    Sofa createSofa();

    CoffeeTable createCoffeeTable();

}
