package se.iuh.singletonpatern;

public class LazySingleton {

    private static LazySingleton instance;

    private LazySingleton() {
        System.out.println("Instance created");
    }

    public static LazySingleton getInstance() {
        if(instance == null){
            instance = new LazySingleton();
        }
        return instance;
    }
}