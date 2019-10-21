package Thread.matrix;

public class ConcurrentMultiplyCalculate implements Runnable{

    private  final double[][] matrixA;

    private  final double[][] matrixB;

    private  final double[][] matrixC;

    private final int row;

    private final int column;

    public ConcurrentMultiplyCalculate(final double[][] matrixA,final double[][] matrixB,final double[][] matrixC,final int row,final int column){
        this.matrixA = matrixA;
        this.matrixB = matrixB;
        this.matrixC = matrixC;
        this.row = row;
        this.column = column;
    }


    @Override
    public void run() {
        this.matrixC[row][column] = 0.0;
        for (int k = 0; k < column; k++){
            this.matrixC[row][column] += matrixA[row][k] * matrixB[k][column];
        }

    }
}
