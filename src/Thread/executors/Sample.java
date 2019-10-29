package Thread.executors;

/**
 * @ClassName: Sample
 * @Description: 需要比对的对象
 *  tag 标志 ：
 *  points 模拟n维度点的位置
 * @Author:
 * @Date: 2019/10/29 16:20
 * @Version V1.0
 **/
public class Sample {

    private double[] points;

    private String tag;

    public Sample(){

    }

    public Sample(double[] points, String tag) {
        this.points = points;
        this.tag = tag;
    }

    public double[] getPoints() {
        return points;
    }

    public void setPoints(double[] points) {
        this.points = points;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
