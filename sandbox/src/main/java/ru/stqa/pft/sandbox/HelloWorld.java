package ru.stqa.pft.sandbox;

public class HelloWorld {
  public static void main(String[] args) {
    hello("world");
    hello("user");
    hello("Artur");

    double l =5;
    System.out.println("Площадь квадрата со стороной " + l + " = " + area(l));

    double a = 4;
    double b = 6;
    System.out.println("Площадь прямоугольник со сторонами " + a + " и " + b + " = "+ area(a,b));
  }

  public static void hello(String somebody) { //Функция void не возвращает никакого зн-я
    System.out.println("Hello, " + somebody + "!");
  }
  public static double area(double len){ // Вычисляемая функция static возвращает зн-е return
    return len*len;
  }

  public static double area(double a, double b){
    return a*b;
  }
}
