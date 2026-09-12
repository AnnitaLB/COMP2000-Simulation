public abstract class Animal extends Entity {

    protected int energy;

    public Animal(int x, int y, int energy) {
        super(x, y);
        this.energy = energy;
    }

    public int getEnergy() {
        return energy;
    }
    public void addEnergy(int amount) {
        energy = energy + amount;
    }
    public void loseEnergy(int amount) {
    energy = energy - amount;
    }
    public boolean isAlive() {
    return energy > 0;
    }
    public void moveTowards(int targetX, int targetY){
       if(x < targetX) {
        x = x+10;
        } else if (x > targetX){
            x= x-10;
        } 
        if(y < targetY){
            y= y+10;
        }else if( y > targetY){
            y = y-10;
        }
    }
    public abstract void move();
}