package Thread.matrix;

import java.util.Arrays;

public class Test {
    private static double[][] matrixA = MatrixGenerator.generate(500,2000);
    private static double[][] matrixB = MatrixGenerator.generate(2000,2000);
    private static double[][] matrixC = new double[500][200];

    public static void main(String[] args) {
        //testSerial();
       // testConcurrent();
       // testConcurrentPerRow();
    }

    public static void testSerial(){
        long start = System.currentTimeMillis();
        SerialCalculate.multiply(matrixA,matrixB);
        long end = System.currentTimeMillis();
        System.out.println("serial matrix calculate takes time : " + (end - start));
    }

    public static void testConcurrent(){
        long start = System.currentTimeMillis();
        ConcurrentMultiply.multiply(matrixA,matrixB,matrixC);
        long end = System.currentTimeMillis();
        System.out.println("concurrent matrix calculate takes time : " + (end - start));
    }

    public static void testConcurrentPerRow(){
        long start = System.currentTimeMillis();
        ConcurrentMultiply.multiplyPerRow(matrixA,matrixB,matrixC);
        long end = System.currentTimeMillis();
        System.out.println("testConcurrentPerRow matrix calculate takes time : " + (end - start));
    }

}
