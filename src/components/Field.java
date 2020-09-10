package components;
import java.util.ArrayList;
import java.util.Scanner;

public class Field {

  public Field(){
    initPlayer();
  }

  int fieldSize = 10;
  int[][] field = new int[fieldSize][fieldSize];;
  Player player1 = new Player();
  Player player2 = new Player();
  Scanner scn = new Scanner(System.in);

  public void initPlayer(){
    System.out.println("Игрок 1, введите свое имя");
    String nameFirstPlayer = scn.next();
    player1.setName(nameFirstPlayer);
    System.out.println("Игрок 2, введите свое имя");
    String nameSecondPlayer = scn.next();
    player2.setName(nameSecondPlayer);
    this.alignmentShips(player1);
    this.nullField();
    this.alignmentShips(player2);
    this.nullField();
    while ((this.checkWin(player1) == false)||(this.checkWin(player2) == false)) {
      if (this.attack(player1) == false)
        this.attack(player2);
      else
        this.attack(player1);
    }
  }

  private void outField(Player player){
    System.out.println("   A B C D E F G H I J");
    ArrayList<AttackPoint> attackPoints = player.getAttackPoints();
    for(AttackPoint attackPoint : attackPoints){
      if (attackPoint.isResult() == false)
        this.field[attackPoint.getY()][attackPoint.getX()] = 2;
      else this.field[attackPoint.getY()][attackPoint.getX()] = 3;
    }
    for (int i = 0; i < 10; i++){
      if(i < 9)
        System.out.print((i + 1) + "  ");
      else
        System.out.print((i + 1) + " ");
      for (int j = 0; j < 10; j++){
        if (this.field[i][j] == 0)
          System.out.print(". ");
        else if ((this.field[i][j] == 1) || (this.field[i][j] == 3))
          System.out.print("* ");
        else if (this.field[i][j] == 2)
          System.out.print("  ");
      }
      System.out.println();
    }
  }

  private void nullField(){
    for (int i = 0; i < fieldSize; i++){
      for (int j = 0; j < fieldSize; j++){
        this.field[i][j] = 0;
      }
    }
  }

  private int getX(){
    String coordinates = scn.next();
    int x = 0;
    switch (coordinates.charAt(0)){
      case 'A':
        x = 0;
        break;
      case 'B':
        x = 1;
        break;
      case 'C':
        x = 2;
        break;
      case 'D':
        x = 3;
        break;
      case 'E':
        x = 4;
        break;
      case 'F':
        x = 5;
        break;
      case 'G':
        x = 6;
        break;
      case 'H':
        x = 7;
        break;
      case 'I':
        x = 8;
        break;
      case 'J':
        x = 9;
        break;
      default:
        System.out.println("Неверное значение");
        this.getX();
    }
    return x;
  }

  private void alignmentShips(Player player){
    ArrayList<Ship> ships = new ArrayList<>();
    this.outField(player);
    System.out.print(player.getName());
    System.out.println(", расставьте корабли");
    for(int i = 1; i <= 4; i++){
      for (int j = 4; j>=i; j--){
          System.out.println("Введите координату X " + i + " палубного корабля");
          int x = this.getX();
          System.out.println("Введите координату Y " + i + " палубного корабля");
          int y = scn.nextInt() - 1;
          if (i!=1) {
            System.out.println("Построить корабль");
            System.out.println("Горизонтально (1)");
            System.out.println("Вертикально (2)");
            int direction = scn.nextInt();
            ships.add(new Ship(i, x, y, direction));
          }
          else ships.add(new Ship(i, x, y, 1));
          player.setShips(ships);
          this.outFieldWithShips(player);
      }
    }
  }

  private void outFieldWithShips(Player player){
    ArrayList<Ship> ships = player.getShips();
    for(Ship ship : ships){
      if (ship.getDirection() == 2){
        for (int i = ship.getY(); i < ship.getY()+ship.getSize(); i++){
          this.field[i][ship.getX()] = 1;
        }
      }
      else{
        for (int i = ship.getX(); i < ship.getX()+ship.getSize(); i++){
          this.field[ship.getY()][i] = 1;
        }
      }
    }
    this.outField(player);
  }

  private boolean attack(Player player){
    boolean attack = false;
    System.out.print(player.getName());
    System.out.println(", Ваш ход");
    System.out.println("Введите координату х");
    int x = this.getX();
    System.out.println("Введите координату y");
    int y = scn.nextInt() - 1;

    if (player == player1)
      player = player2;
    else player = player1;
    ArrayList<Ship> ships = player.getShips();

    for (Ship ship: ships){
      if (ship.getDirection() == 2) {
        for (int i = ship.getY(); i < ship.getY() + ship.getSize(); i++) {
          if ((ship.getX() == x) && (i == y)) {
            attack = isAttack(ship);
          }
        }
      }
      else{
        for (int i = ship.getX(); i < ship.getX()+ship.getSize(); i++){
          if ((i == x) && (ship.getY() == y)) {
            attack = isAttack(ship);
          }
        }
      }
      if (attack == false)
        attack = isAttack(ship);
      if (player == player1)
      this.attackPoint(player2, x, x, attack);
      else this.attackPoint(player1, x, x, attack);
    }
    return attack;
  }

  private boolean isAttack(Ship ship){
    ship.setWounds(ship.getWounds() + 1);
    if (ship.getWounds() == ship.getSize())
      ship.setKill(true);
    if (ship.kill == true)
      System.out.println("корабль убит");
    else
      System.out.println("корабль ранен");
    return true;
  }

  private void attackPoint(Player player, int x, int y, boolean result){
    ArrayList<AttackPoint> attackPoints = player.getAttackPoints();
    attackPoints.add(new AttackPoint(x, y, result));
    player.setAttackPoints(attackPoints);
  }

  private boolean checkWin(Player player){
    ArrayList<Ship> ships = player.getShips();
    int i = 0;
    for (Ship ship: ships){
      if (ship.isKill() == true)
        i++;
      if(i == 10){
        if (player == player1){
          System.out.println(player2.getName());
        }
        else{
          System.out.println(player1.getName());
        }
        System.out.println(" победил");
        return true;
      }
    }
    return false;
  }
}
