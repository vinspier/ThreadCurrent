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
        Map<String, Integer> resultMap = new HashMap<>();
        int index = 0;
        /**
         * 遍历计算目标点与续联集合点的距离
         */
        for (Sample origin : dataSet){
            Distance distance = new Distance();
            distance.setIndex(index);
            distance.setDistance(DistanceCalculaor.calculate(origin,target));
            index++;
        }

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
