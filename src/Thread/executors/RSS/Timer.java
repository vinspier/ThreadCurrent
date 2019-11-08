package Thread.executors.RSS;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: Timer
 * @Description:
 * @Author:
 * @Date: 2019/11/8 15:24
 * @Version V1.0
 **/
public class Timer {
    public static long getPeriod() {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        if ((hour >= 6) && (hour <= 8)) {
            return TimeUnit.MILLISECONDS.convert(1, TimeUnit.MINUTES);
        }
        if ((hour >= 13) && (hour <= 14)) {
            return TimeUnit.MILLISECONDS.convert(1, TimeUnit.MINUTES);
        }
        if ((hour >= 20) && (hour <= 22)) {
            return TimeUnit.MILLISECONDS.convert(1, TimeUnit.MINUTES);
        }
        return TimeUnit.MILLISECONDS.convert(2, TimeUnit.MINUTES);
    }
}
