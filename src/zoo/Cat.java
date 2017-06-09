
package zoo;

// author Chris.Trathen
public class Cat extends Animal  {
    
    public Cat(String n, Location loc) {
        this.name = n;
        this.typeOfAnimal = "Cat";
        this.loc = loc;
    }

    @Override
    public void speak() {
        System.out.println("Meow");
    }
       
}
