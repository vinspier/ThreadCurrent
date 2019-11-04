package Thread.executors;

import java.util.*;

/**
 * @ClassName: SerialK
 * @Description: 串行方式k邻近计算
 * @Author:
 * @Date: 2019/10/29 16:49
 * @Version V1.0
 **/
public class SerialK {

    private final List<? extends Sample> dataSet;

    private final int k;

    public SerialK(List<Sample> dataSet, int k) {
        this.dataSet = dataSet;
        this.k = k;
    }

    /**
     * 开始匹配目标点 与 训练集合点
     * @param target
     */
    public String classify(Sample target){
        List<Distance> distanceList = new ArrayList<>();

        int index = 0;
        /**
         * 遍历计算目标点与续联集合点的距离(串行计算)
         */
        for (Sample origin : dataSet){
            Distance distance = new Distance();
            distance.setIndex(index);
            /** 计算距离这一步可通过多线程 并行处理 */
            distance.setDistance(DistanceCalculator.calculate(origin,target));
            distanceList.add(distance);
            index++;
        }

       return DistanceCalculator.getK(distanceList,dataSet,k);
    }

}
