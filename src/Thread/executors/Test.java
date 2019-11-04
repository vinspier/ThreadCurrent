package Thread.executors;

import java.util.List;

/**
 * @ClassName: Test
 * @Description:
 * @Author:
 * @Date: 2019/10/30 10:32
 * @Version V1.0
 **/
public class Test {

    public static void main(String[] args) throws InterruptedException{
       // testSerial();
        testParallel();
    }

    public static void testSerial(){
        int dataLen = 100000; // 基础数据匹配库的数据个数
        int pointCount = 3; // 元数据sample的坐标维度
        List<Sample> sampleList = RandomUtil.getDataSet(dataLen,pointCount);
        Sample sample = new Sample(RandomUtil.getPoints(pointCount),RandomUtil.getTag());
        SerialK serialK = new SerialK(sampleList,100);
        Long start = System.currentTimeMillis();
        String target = serialK.classify(sample);
        Long end = System.currentTimeMillis();
        System.out.println("target ==========>: " + target  + "\n"+ " serial method time took ==========>: " + (end - start) + " ms");
    }

    public static void testParallel() throws InterruptedException{
        int dataLen = 100000; // 基础数据匹配库的数据个数
        int pointCount = 3; // 元数据sample的坐标维度
        List<Sample> sampleList = RandomUtil.getDataSet(dataLen,pointCount);
        Sample sample = new Sample(RandomUtil.getPoints(pointCount),RandomUtil.getTag());
        ParallelK parallelK = new ParallelK(sampleList,100);
        Long start = System.currentTimeMillis();
        String target = parallelK.classify(sample);
        Long end = System.currentTimeMillis();
        System.out.println("target ==========>: " + target  + "\n"+ "parallel method time took ==========>: " + (end - start) + " ms");
    }

}
