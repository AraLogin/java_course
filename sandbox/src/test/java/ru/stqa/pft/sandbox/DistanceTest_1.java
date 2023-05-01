package ru.stqa.pft.sandbox;


import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class DistanceTest_1 {
  public void Distance(){
    Point p1 = new Point(1.00,2.00);
    Point p2 = new Point(7.00,8.00);
    String formattedDouble = String.format("%.2f", p1.distance(p2));
    Assert.assertEquals(formattedDouble,("8,49"));
  }
}

