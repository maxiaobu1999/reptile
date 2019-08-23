package com.norman.reptile.utils;

public class Utils {

    public static void napping(int mill) {
        try {
            Thread.sleep(mill);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
