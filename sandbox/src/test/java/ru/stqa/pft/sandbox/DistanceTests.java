package ru.stqa.pft.sandbox;


import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class DistanceTests {
  public void Distance_test_1() {
    Point p1 = new Point(1.00, 2.00);
    Point p2 = new Point(7.00, 8.00);
    String formattedDouble = String.format("%.2f", p1.distance(p2));
    Assert.assertEquals(formattedDouble, ("8,49"));
  }

  public void Distance_test_2() {
    Point p1 = new Point(0, 0);
    Point p2 = new Point(10, 33);
    String formattedDouble = String.format("%.2f", p1.distance(p2));
    Assert.assertEquals(formattedDouble, ("34,48"));
  }

  public void Distance_test_3() {
    Point p1 = new Point(0, 0);
    Point p2 = new Point(0, 0);
    String formattedDouble = String.format("%.2f", p1.distance(p2));
    Assert.assertEquals(formattedDouble, ("0,00"));
  }
}

