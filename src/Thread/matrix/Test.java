package Thread.matrix;

public class Test {
    private static double[][] matrixA = MatrixGenerator.generate(500,2000);
    private static double[][] matrixB = MatrixGenerator.generate(2000,200);
    private static double[][] matrixC = new double[matrixA.length][matrixB[0].length];

    public static void main(String[] args) {
       // System.out.println(Runtime.getRuntime().availableProcessors());

        testSerial();
        testConcurrent();
        testConcurrentPerRow();
        testConcurrentRuntimeAvailable();
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

    public static void testConcurrentRuntimeAvailable(){
        long start = System.currentTimeMillis();
        ConcurrentMultiply.multiplyRuntimeAvailable(matrixA,matrixB,matrixC);
        long end = System.currentTimeMillis();
        System.out.println("testConcurrentPerRow matrix calculate takes time : " + (end - start));
    }

}
