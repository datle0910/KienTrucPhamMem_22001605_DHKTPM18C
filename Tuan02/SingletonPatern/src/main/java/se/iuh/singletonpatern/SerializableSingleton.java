package se.iuh.singletonpatern;

import java.io.Serializable;

public class SerializableSingleton implements Serializable {

    private static final long serialVersionUID = 1L;

    private static SerializableSingleton instance =
            new SerializableSingleton();

    private SerializableSingleton(){}

    public static SerializableSingleton getInstance(){
        return instance;
    }

    protected Object readResolve(){

        return instance;

    }

}
