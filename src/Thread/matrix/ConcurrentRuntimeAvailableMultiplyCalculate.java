package Thread.matrix;

/**
 * 根据 运行时 的最大线程数来处理
 */
public class ConcurrentRuntimeAvailableMultiplyCalculate implements Runnable{

    private  final double[][] matrixA;

    private  final double[][] matrixB;

    private  final double[][] matrixC;

    private final int rowStart;

    private final int rowEnd;

    public ConcurrentRuntimeAvailableMultiplyCalculate(double[][] matrixA, double[][] matrixB, double[][] matrixC, int rowStart, int rowEnd) {
        this.matrixA = matrixA;
        this.matrixB = matrixB;
        this.matrixC = matrixC;
        this.rowStart = rowStart;
        this.rowEnd = rowEnd;
    }

    @Override
    public void run() {
        for (int i = rowStart; i < rowEnd; i++){ // 遍历起始的行数
            for(int j = 0; j < matrixB[0].length; j++){
                matrixC[i][j] = 0.0;
                for (int k = 0; k < matrixA[0].length; k++){
                    matrixC[i][j] += matrixA[i][k] * matrixB[k][j];
                }
            }
        }
    }
}
