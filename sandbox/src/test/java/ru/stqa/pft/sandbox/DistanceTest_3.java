package ru.stqa.pft.sandbox;


import org.testng.Assert;
import org.testng.annotations.Test;
@Test
public class DistanceTest_3 {
  public void Distance(){
    Point p1 = new Point (0,0);
    Point p2 = new Point (0,0 );
    String formattedDouble = String.format("%.2f", p1.distance(p2));
    Assert.assertEquals(formattedDouble,("0,00"));
  }
}
