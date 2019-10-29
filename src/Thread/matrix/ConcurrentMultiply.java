package Thread.matrix;

import java.util.ArrayList;
import java.util.List;

public class ConcurrentMultiply {

    public static void multiply(double[][] matrixA,double[][] matrixB,double[][] matrixC){
        List<Thread> threadList = new ArrayList<>();
        for (int i = 0; i < matrixA.length; i++){
            for (int j = 0; j < matrixB[0].length; j++){
                Thread thread = new Thread(new ConcurrentMultiplyCalculate(matrixA,matrixB,matrixC,i,j));
                thread.start();
                threadList.add(thread);
                if (threadList.size() > 0 && threadList.size() % 10 == 0){
                    waitForThreads(threadList);
                }
            }
        }
    }

    public static void multiplyPerRow(double[][] matrixA,double[][] matrixB,double[][] matrixC){
        List<Thread> threadList = new ArrayList<>();
        for (int i = 0; i < matrixA.length; i++){
            Thread thread = new Thread(new ConcurrentPerRowMultiplyCalculate(matrixA,matrixB,matrixC,i));
            thread.start();
            threadList.add(thread);
            if (threadList.size() > 0 && threadList.size() % 10 == 0){
                waitForThreads(threadList);
            }
        }
    }

    public static void multiplyRuntimeAvailable(double[][] matrixA,double[][] matrixB,double[][] matrixC){
        List<Thread> threadList = new ArrayList<>();
        int availableThreds = Runtime.getRuntime().availableProcessors();
        int totalRows = matrixA.length;
        int increment = totalRows / availableThreds; // 计算每个线程执行的行数
        int startRow = 0;
        int endRow = increment;
        for (int i = 0; i < availableThreds; i++){
            Thread thread = new Thread(new ConcurrentRuntimeAvailableMultiplyCalculate(matrixA,matrixB,matrixC,startRow,endRow));
            thread.start();
            threadList.add(thread);
            // availableThreds - 2 需要一个主线程来支持其他子线程的运行（最后一个为主线程）
            endRow = i == availableThreds - 2 ? totalRows : endRow + increment;
        }
        for (Thread thread : threadList){
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void waitForThreads(List<Thread> threadList){
        for (Thread thread : threadList){
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        threadList.clear();
    }

}
