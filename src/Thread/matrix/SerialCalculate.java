package Thread.matrix;

/**
 * 串行矩阵计算方式
 */
public class SerialCalculate {

    /**
     * 串行计算两个矩阵 暂时不做矩阵维度的校验
     * @param matrixA
     * @param matrixB
     * @return
     */
    public static double[][] multiply(double[][] matrixA,double[][] matrixB){
        double[][] matrixC = new double[matrixA.length][matrixB[0].length];
        for (int i = 0; i < matrixA.length; i++){
            for (int j = 0; j < matrixB[0].length; j++){
                matrixC[i][j] = 0.0;
                for (int k = 0; k < matrixB.length; k++){
                    matrixC[i][j] += matrixA[i][k] * matrixB[k][j];
                }
            }
        }
        return matrixC;
    }
}
