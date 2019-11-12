package Thread.TaskReturnValue1.task;

import Thread.TaskReturnValue1.common.DocumentData;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * 为 文档存储对象 生成 词汇倒叙索引
 */
public class WordsInvertedIndexTask implements Runnable{

    private CompletionService<DocumentData> completionService;
    private ConcurrentHashMap<String,ConcurrentLinkedDeque<String>> invertedIndex;

    public WordsInvertedIndexTask(CompletionService<DocumentData> completionService, ConcurrentHashMap<String, ConcurrentLinkedDeque<String>> invertedIndex) {
        this.completionService = completionService;
        this.invertedIndex = invertedIndex;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()){
                try {
                    DocumentData documentData = completionService.take().get();
                    refreshInvertedIndex(documentData.getWords(),invertedIndex,documentData.getFileName());
                } catch (Exception e) {
                    e.printStackTrace();
                    break;
                }
            }
            while (true){
                Future<DocumentData> future = completionService.poll();
                if (future == null){
                    break;
                }
                DocumentData documentData = future.get();
                refreshInvertedIndex(documentData.getWords(),invertedIndex,documentData.getFileName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void refreshInvertedIndex(Map<String,Integer> words, ConcurrentHashMap<String,ConcurrentLinkedDeque<String>> invertedIndex, String filename){
        for (String word : words.keySet()){
            invertedIndex.computeIfAbsent(word,k -> new ConcurrentLinkedDeque<>()).add(filename);
        }
    }
}
