package ru.stqa.pft.sandbox;

public class Square {

  public double a;

  public Square(double a ) {//конструктор
    this.a=a;
  }

  public double area(){ // Вычисляемая функция static возвращает зн-е return
    return this.a * this.a;
  }

}
