package se.iuh.factorypatern;

import se.iuh.factorypatern.AbstracProducts.Chair;
import se.iuh.factorypatern.AbstracProducts.CoffeeTable;
import se.iuh.factorypatern.AbstracProducts.Sofa;

public class InteriorDesigner {

    private Chair chair;

    private Sofa sofa;

    private CoffeeTable table;

    public InteriorDesigner(FurnitureFactory factory){

        chair = factory.createChair();

        sofa = factory.createSofa();

        table = factory.createCoffeeTable();

    }

    public void decorate(){

        chair.sitOn();

        sofa.lieOn();

        table.putCoffee();

    }

}