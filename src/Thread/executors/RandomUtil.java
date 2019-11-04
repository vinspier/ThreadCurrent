package Thread.executors;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @ClassName: RandomTag
 * @Description: 随机产生数据 工具类
 * @Author:
 * @Date: 2019/10/29 16:32
 * @Version V1.0
 **/
public class RandomUtil {

    private static final String[] TAGS = new String[100];

    private static final String PREFIX = "TAG-";

    /**
     * 静态初始化
     */
    static {
        for (int i = 0; i < 100; i++){
            TAGS[i] = PREFIX + i;
        }
    }

    private RandomUtil(){

    }

    /**
     * 随机获取一个标志位
     * @return
     */
    public static String getTag(){
        Random random = new Random();
        int index = random.nextInt(100);
        return TAGS[index];
    }

    /**
     * 获取一个长度为len的随机点
     * 改点 可以想象为len维度的空间的点的坐标
     * @param len
     * @return
     */
    public static double[] getPoints(int len){
        double[] points = new double[len];
        Random random = new Random();
        for (int i = 0; i < len; i++){
            points[i] = random.nextDouble();
        }
        return points;
    }

    /**
     * 获取指定个数点集合
     * @param count
     * @return
     */
    public static List<Sample> getDataSet(int count,int len){
        List<Sample> dataSet = new ArrayList<>();
        for (int i = 0; i < count; i++){
            Sample sample = new Sample();
            sample.setTag(getTag());
            sample.setPoints(getPoints(len));
            dataSet.add(sample);
        }
        return dataSet;
    }

}
