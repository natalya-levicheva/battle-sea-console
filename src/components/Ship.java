package components;

public class Ship {
    int size;
    int x;
    int y;
    int direction;
    boolean kill = false;
    int wounds = 0;


  public Ship(int size, int x, int y, int direction) {
    this.size = size;
    this.x = x;
    this.y = y;
    this.direction = direction;
  }

  public boolean isKill() {
    return kill;
  }

  public void setKill(boolean kill) {
    this.kill = kill;
  }

  public int getWounds() {
    return wounds;
  }

  public void setWounds(int wounds) {
    this.wounds = wounds;
  }

  public int getSize() {
    return size;
  }

  public void setSize(int size) {
    this.size = size;
  }

  public int getX() {
    return x;
  }

  public void setX(int x) {
    this.x = x;
  }

  public int getY() {
    return y;
  }

  public void setY(int y) {
    this.y = y;
  }

  public int getDirection() {
    return direction;
  }

  public void setDirection(int direction) {
    this.direction = direction;
  }
}
