package ru.stqa.pft.sandbox;


import org.testng.Assert;
import org.testng.annotations.Test;
@Test
public class DistanceTest_2 {
  public void Distance(){
    Point p1 = new Point (0,0);
    Point p2 = new Point (10,33 );
    String formattedDouble = String.format("%.2f", p1.distance(p2));
    Assert.assertEquals(formattedDouble,("34,48"));
  }
}
