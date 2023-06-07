package ru.stqa.pft.sandbox;

import java.util.Arrays;
import java.util.List;

public class Collection {
    public static void main (String[] args){
        //String[] langs = new String[4];  объявленна переманная типа массив[] строк.
        //langs [0] = "Java"; Порядковый номер массива начинается с 0
        //String[] langs = {"Java","C#","Python","PHP"}; также можно вносить элементы массива при создании массива

        List<String> languages = Arrays.asList("Java","C#","Python","PHP");

        for (String l :languages ){ // вводим переменную l, которая будет являться ссылкой на элемент массива
            System.out.println("Я хочу выучить " + l);
        }
    }
}
