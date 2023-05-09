package ru.stqa.pft.sandbox;

public class HelloWorld {
  public static void main(String[] args) {
    hello("world");
    hello("user");
    hello("Artur");

    Square s = new Square(5);
    System.out.println("Площадь квадрата со стороной " + s.a + " = " + s.area());

    Rectangle r = new Rectangle(4,6);
    System.out.println("Площадь прямоугольник со сторонами " + r.a + " и " + r.b + " = " + r.area());
  }

  public static void hello(String somebody) { //Функция void не возвращает никакого зн-я
    System.out.println("Hello, " + somebody + "!");
  }



}
