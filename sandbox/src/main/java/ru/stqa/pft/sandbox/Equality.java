package ru.stqa.pft.sandbox;

public class Equality {
    public static void main(String[] args) {
        String s1 = "firefox 2.0";
        String s2 = "firefox " + Math.sqrt(4.0);

        System.out.println(s1==s2); //сравнение ссылок в котором проверяется идентичность объектов
        System.out.println(s1.equals(s2)); //сравнение содержимого объектов, это логическое сравнение в отличие от физического сравнения путём ==

    }
}
