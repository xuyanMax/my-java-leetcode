package zoo;

//author Chris.Trathen
public class Location {

    private int locX;
    private int locY;

    public Location(int x, int y) {
        this.locX = x;
        this.locY = y;
    }

    public int getLocX() {
        return locX;
    }

    public int getLocY() {
        return locY;
    }

    public String toString() {
        return "(" + locX + "," + locY + ")";
    }

    public double distanceFrom(Location lc) {
        int x1 = this.getLocX();
        int y1 = this.getLocY();
        int x2 = lc.getLocX();
        int y2 = lc.getLocY();
        double distance = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
        return distance;
    }

}
