package Thread.TaskReturnValue.parallel;

import Thread.TaskReturnValue.common.LevenshteinDistance;
import Thread.TaskReturnValue.common.WordsLoader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: ParallelMatchSingleExist
 * @Description: 寻找字符串 是否在字典中存在
 * 若存在 则 返回找到的第一个数据
 * @Author:
 * @Date: 2019/11/11 10:06
 * @Version V1.0
 **/
public class ParallelMatchSingleExist {

    public static Map<String,Object> find(String target){
        Map<String ,Object> result = new HashMap<>();
        result.put("source","target");
        result.put("target","");
        result.put("targetIndex",-1);
        List<String> dictionary = new ArrayList<>();
        try {
            dictionary = WordsLoader.load("resources/UKACD.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        int index = 0;
        for (String source : dictionary){
            int distance = LevenshteinDistance.calculate(source,target);
            if (distance == 0){
                result.put("target",source);
                result.put("targetIndex",index);
                return result;
            }
            index ++;
        }
        return result;
    }

}
