package Thread.matrix;

public class ConcurrentPerRowMultiplyCalculate implements Runnable{

    private  final double[][] matrixA;

    private  final double[][] matrixB;

    private  final double[][] matrixC;

    private final int row;

    public ConcurrentPerRowMultiplyCalculate(final double[][] matrixA, final double[][] matrixB, final double[][] matrixC, final int row){
        this.matrixA = matrixA;
        this.matrixB = matrixB;
        this.matrixC = matrixC;
        this.row = row;
    }


    @Override
    public void run() {
        for (int i = 0; i < matrixB[0].length; i++){
            this.matrixC[row][i] = 0.0;
            for (int j = 0; j < matrixA[row].length; j++){
                this.matrixC[row][i] += matrixA[row][i] * matrixB[j][i];
            }
        }
    }
}
