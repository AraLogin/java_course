package ru.stqa.pft.sandbox;

public class Distance {
  public static void main(String[] args) {
    Point p1 = new Point(1.00,2.00);
    Point p2 = new Point(7.00,8.00);
    System.out.println("Расстояние между точками А("+p1.x+","+p1.y+") и Б("+p2.x+","+p2.y+") = " + p1.distance(p2));
  }
}
