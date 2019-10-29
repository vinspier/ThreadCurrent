package Thread.executors;

/**
 * @ClassName: Distance
 * @Description: 存放距离辅助类
 * @Author:
 * @Date: 2019/10/29 17:12
 * @Version V1.0
 **/
public class Distance {

    private int index;

    private double distance;

    public Distance() {
    }

    public Distance(int index, double distance) {
        this.index = index;
        this.distance = distance;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
}
