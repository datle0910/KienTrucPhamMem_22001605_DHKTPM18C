package se.iuh.factorypatern;

public class Main {

    public static void main(String[] args){

        FurnitureFactory factory;

        factory = new ModernFurnitureFactory();

        InteriorDesigner room =
                new InteriorDesigner(factory);

        room.decorate();

    }

}
