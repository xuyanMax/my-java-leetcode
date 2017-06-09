package zoo;

// author Chris.Trathen
public abstract class Animal {

    protected String name;
    protected String typeOfAnimal;
    protected Location loc;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTypeOfAnimal() {
        return typeOfAnimal;
    }

    public void setTypeOfAnimal(String typeOfAnimal) {
        this.typeOfAnimal = typeOfAnimal;
    }

    public Location getLoc() {
        return loc;
    }

    public void setLoc(Location loc) {
        this.loc = loc;
    }

    // This will be overriden in subclasses
    public void speak() {
        System.out.println("nothing");
    }

    // The animal moves to anither location.
    // The distance travelled is calculated.
    public double walk(Location lc) {
        double distance = this.loc.distanceFrom(lc);
        this.loc = lc;
        return distance;
    }

    public boolean canFly() {
        return false;
    }

}
