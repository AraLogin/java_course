package ru.stqa.pft.sandbox;

public class Primes {
    public static boolean isPrime(int n){  //если число простое, то возвращается true, если не простое, то false
        for (int i = 2; //переменная счётчик. Первая чассть инициализация(int начинается с двойки
             i< n ; // где он должен остановиться
             i++){ //то что происходит с переменной счётчика на каждой итерации цикла ++(прибавляет на 1),для
                   // того чтобы прибавлять другое число используем +=
            if (n % i == 0){ //если i делится на n без остатка, то возвращается false
                return false;
            }
        }
        return true;
    }
    public static boolean isPrimeFast(int n){
        int m = (int)Math.sqrt(n);
        for (int i = 2;i < m ;i++){
            if (n % i == 0){
                return false;
            }
        }
        return true;
    }
    public static boolean isPrimeWhile(int n) {
        int i =2;
        while (i < n && n % i != 0){
            i++;
        }
        return i == n;
    }
    public static boolean isPrime(long n){
        for (long i = 2; i< n; i++){
            if (n % i == 0){
                return false;
            }
        }
        return true;
    }
}
