package step.learning;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Basics {
    public void run() {
        System.out.println("java basics ");
        int x = 10;
        Integer xx = 10;
        byte b = 127;
        b = -128;
        short s = 16000;
        long lng = 1000000000L;

        float f = 0.1f;
        double d = 1.99E-4;

        char c = 'A';
        boolean bool = true;

        System.out.println(x);
        System.out.printf("xx = %d,b = %d,s = %d, lng = %d,f = %e, d = %f %n",
                xx, b, s, lng, f, d);
        control();
    }

    private void control() {
        int x = 10;
        if (x % 2 == 1) {
            System.out.println("x is odd");
        } else {
            System.out.println("x is even");
        }
        System.out.println(x % 2 == 0 ? "even" : "odd");


        int[] arr1 = {1, 2, 3, 4, 5};
        int[] arr2 = new int[3];
        for (int i = 0; i < 3; i++) {
            arr2[i] = i;
        }

        for (int elem : arr1) {
            System.out.println(elem);
        }
        int[][] arrs = new int[4][];
        for (int i = 0; i < 4; i++) {
            arrs[i] = new int[5];
            for (int j = 0; j < 5; j++) {
                arrs[i][j] = i + 10 + j + 11;
            }
        }

        for(int[] subarr: arrs){
            for(int elem : subarr){
                System.out.print(elem + " ");
            }
            System.out.println();
        }

        //List<int> list; - error - only ref in collections
        List<Integer> list1;
        list1 = new ArrayList<>();
        list1 = new LinkedList<>();
    }
}
