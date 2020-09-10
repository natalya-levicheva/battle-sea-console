package components;

public class AttackPoint {
  int x;
  int y;
  boolean result;

  public AttackPoint(int x, int y, boolean result) {
    this.x = x;
    this.y = y;
    this.result = result;
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

  public boolean isResult() {
    return result;
  }

  public void setResult(boolean result) {
    this.result = result;
  }
}
