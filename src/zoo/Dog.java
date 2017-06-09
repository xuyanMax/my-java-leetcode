
package zoo;

// author Chris.Trathen
public class Dog extends Animal  {

    public Dog(String n, Location loc) {
        this.name = n;
        this.typeOfAnimal = "Dog";
        this.loc = loc;
    }
  
    @Override
    public void speak() {
        System.out.println("Woof");
    }
    
}
