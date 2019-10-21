package Thread.matrix;

import java.util.Random;

/**
 * 随机产生一个矩阵
 */
public class MatrixGenerator {

    private MatrixGenerator(){

    }

    public static double[][] generate(int rows,int columns){
        double[][] matrix = new double[rows][columns];
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < columns; j++){
                matrix[i][j] = new Random().nextDouble()*10;
            }
        }
        return matrix;
    }

}
