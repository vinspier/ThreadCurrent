package Thread.executors;

/**
 * @ClassName: CalculateDistance
 * @Description: 计算两点之间的距离
 * @Author:
 * @Date: 2019/10/29 16:56
 * @Version V1.0
 **/
public class DistanceCalculator {

    private DistanceCalculator() {
    }

    public static double calculate(Sample origin,Sample target){
        double distance = 0.0d;
        double[] point1 = origin.getPoints();
        double[] point2 = target.getPoints();
        if (point1.length != point2.length){
            throw new IllegalArgumentException ("length of sample`s points must have the same length");
        }
        for (int i = 0; i < point1.length; i++){
            distance += Math.pow((point1[i] - point2[i]),2);
        }
        return Math.sqrt(distance);
    }

}
