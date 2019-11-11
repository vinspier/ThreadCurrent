package Thread.TaskReturnValue.task;

import Thread.TaskReturnValue.common.LevenshteinDistance;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * @ClassName: ExistBasicTask
 * @Description: 字符串匹配存在的任务类
 * @Author:
 * @Date: 2019/11/11 10:56
 * @Version V1.0
 **/
public class ExistBasicTask implements Callable<Map<String,Object>> {

    private int start;

    private int end;

    private String target;

    private List<String> dictionary;

    public ExistBasicTask(int start, int end, String target, List<String> dictionary) {
        this.start = start;
        this.end = end;
        this.target = target;
        this.dictionary = dictionary;
    }

    @Override
    public Map<String, Object> call() throws Exception {
        Map<String, Object> result = new HashMap<>();
        result.put("source",target);
        result.put("targetIndex",-1);
        result.put("targer","");
        for (int i = start; i < end; i++){
            int distance = LevenshteinDistance.calculate(dictionary.get(i),target);
            if (distance == 0){
                result.put("target",dictionary.get(i));
                result.put("targetIndex",i);
            }
        }
        return result;
    }
}
