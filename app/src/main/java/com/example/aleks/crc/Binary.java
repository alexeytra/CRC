package com.example.aleks.crc;

/**
 * Created by aleks on 11.03.2018.
 */

public class Binary {
    public static String binar(int a){
        int b;
        String temp = "";
        while(a !=0){
            b = a%2;
            temp = b + temp;
            a = a/2;
        }
        return temp;
    }
}
