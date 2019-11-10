package Thread.TaskReturnValue.common;

/**
 * 相似度算法 编辑距离
 * 计算两个字符串对象的 Levenshtein 距离
 */
public class LevenshteinDistance {

    public static int calculate(String source,String target){
        int[][] distances = new int[source.length() + 1][target.length() + 1];
        // distances[0][0] = 0; 默认初始化的
        for (int i = 1; i <= source.length(); i++){
            distances[i][0] = i;
        }
        for (int j = 1; j <= target.length(); j++){
            distances[0][j] = j;
        }
        for (int i = 1; i <= source.length(); i++){
            for (int j = 1; j <= target.length(); j++){
                if (source.charAt(i - 1) == target.charAt(j - 1)){
                    distances[i][j] = distances[i - 1][j - 1];
                }else {
                    distances[i][j] = Math.min(distances[i -1][j],Math.min(distances[i - 1][j - 1],distances[i][j - 1])) + 1;
                }
            }
        }
        return distances[source.length()][target.length()];
    }

}
