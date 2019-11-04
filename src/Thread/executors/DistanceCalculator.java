package Thread.executors;

import java.util.*;

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

    public static List<Distance> sort(List<Distance> distanceList){
        /**
         * 对计算好的距离集合进行排序
         */
        Collections.sort(distanceList,new Comparator<Distance>() {
            @Override
            public int compare(Distance o1, Distance o2) {
                if (o1.getDistance() - o2.getDistance() > 0){
                    return 1;
                }else if (o1.getDistance() - o2.getDistance() < 0){
                    return -1;
                }else {
                    return 0;
                }
            }
        });
        return distanceList;
    }

    /**
     * 获取k个数值中最邻近的值
     * @param distanceList
     * @param dataSet
     * @param k
     * @return
     */
    public static String getK(List<Distance> distanceList,List<? extends Sample> dataSet,int k){
        Map<String, Integer> resultMap = new HashMap<>();
        sort(distanceList);
        /**
         * 取指定的k个距离最近的值
         */
        for(int i = 0; i < k; i++){
            Sample sample = dataSet.get(distanceList.get(i).getIndex());
            String tag = sample.getTag();
            resultMap.merge(tag,1,(a,b) -> a + b);
        }
        return Collections.max(resultMap.entrySet(),Map.Entry.comparingByValue()).getKey();
    }

}
