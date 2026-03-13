package se.iuh.singletonpatern;

public enum EnumSighleton {
    INSTANCE;
    int value;
    public int getValue(){
        return value;
    }
    public void setValue(int value){
        this.value = value;
    }
}
