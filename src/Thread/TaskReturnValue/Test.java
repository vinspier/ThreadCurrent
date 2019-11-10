package Thread.TaskReturnValue;

import Thread.TaskReturnValue.common.BestMatchingData;
import Thread.TaskReturnValue.common.LevenshteinDistance;
import Thread.TaskReturnValue.common.WordsLoader;
import Thread.TaskReturnValue.parallel.ParallelMatchMultiple;
import Thread.TaskReturnValue.parallel.ParallelMatchSingle;
import Thread.TaskReturnValue.serial.SerialMatch;

public class Test {

    public static void main(String[] args) throws Exception{
        //testWordsLoader();
        testSerialMatch("target");
        System.out.println("=======================");
        testParallelMatch("target");
        System.out.println("=======================");
        testParallelMultipleMatch("target");
      // testDistance("zizzing","target");
    }

    public static void testWordsLoader() throws Exception{
        WordsLoader.load("resources/UKACD.txt");
    }

    public static void testSerialMatch(String target){
        long start = System.currentTimeMillis();
        BestMatchingData matchingData = SerialMatch.getBestMatchingWords(target);
        long end = System.currentTimeMillis();
        System.out.println("time took : " + (end - start) + " ms");
        System.out.println("min distance : " + matchingData.getDistance());
        System.out.println("match string:" );
        matchingData.getMatchData().forEach(System.out::println);
    }

    public static void testParallelMatch(String target) throws Exception{
        long start = System.currentTimeMillis();
        BestMatchingData matchingData = ParallelMatchSingle.getMatchData(target);
        long end = System.currentTimeMillis();
        System.out.println("time took : " + (end - start) + " ms");
        System.out.println("min distance : " + matchingData.getDistance());
        System.out.println("match string:" );
        matchingData.getMatchData().forEach(System.out::println);
    }

    public static void testParallelMultipleMatch(String target) throws Exception{
        long start = System.currentTimeMillis();
        BestMatchingData matchingData = ParallelMatchMultiple.getMatchData(target);
        long end = System.currentTimeMillis();
        System.out.println("time took : " + (end - start) + " ms");
        System.out.println("min distance : " + matchingData.getDistance());
        System.out.println("match string:" );
        matchingData.getMatchData().forEach(System.out::println);
    }


    public static void testDistance(String source,String target){
        System.out.println(LevenshteinDistance.calculate(source,target));
    }

}
