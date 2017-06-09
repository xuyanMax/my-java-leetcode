
package zoo;

// author Chris.Trathen
public class Panda extends Animal  {
    
    public Panda(String n, Location loc) {
        this.name = n;
        this.typeOfAnimal = "Panda";
        this.loc = loc;
    }

    @Override
    public void speak() {
        System.out.println("Give me some bamboo");
    }
       
}
