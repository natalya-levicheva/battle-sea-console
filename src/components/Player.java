package components;
import java.util.ArrayList;

public class Player {
  String name;
  ArrayList<Ship> ships = new ArrayList<>();
  ArrayList<AttackPoint> attackPoints = new ArrayList<>();


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public ArrayList<Ship> getShips() {
    return ships;
  }

  public void setShips(ArrayList<Ship> ships) {
    this.ships = ships;
  }

  public ArrayList<AttackPoint> getAttackPoints() {
    return attackPoints;
  }

  public void setAttackPoints(ArrayList<AttackPoint> attackPoints) {
    this.attackPoints = attackPoints;
  }
}
