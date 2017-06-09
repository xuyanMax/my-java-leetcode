package zoo;

// author Chris.Trathen
public class Bird extends Animal {

    public Bird(String name, Location loc) {
        this.name = name;
        this.typeOfAnimal = "Bird";
        this.loc = loc;
    }

    @Override
    public void speak() {
        System.out.println("Chirp");
    }

    @Override
    public boolean canFly() {
        return true;
    }

}
