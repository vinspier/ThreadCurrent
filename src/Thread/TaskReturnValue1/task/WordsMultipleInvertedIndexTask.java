package Thread.TaskReturnValue1.task;

import Thread.TaskReturnValue1.common.DocumentData;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.Future;

/**
 * 为 文档存储对象 生成 词汇倒叙索引
 */
public class WordsMultipleInvertedIndexTask implements Runnable{

    private CompletionService<List<DocumentData>> completionService;
    private ConcurrentHashMap<String,ConcurrentLinkedDeque<String>> invertedIndex;

    public WordsMultipleInvertedIndexTask(CompletionService<List<DocumentData>> completionService, ConcurrentHashMap<String, ConcurrentLinkedDeque<String>> invertedIndex) {
        this.completionService = completionService;
        this.invertedIndex = invertedIndex;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()){
                try {
                    List<DocumentData> documentDataList = completionService.take().get();
                    for (DocumentData documentData : documentDataList) {
                        refreshInvertedIndex(documentData.getWords(), invertedIndex, documentData.getFileName());
                    }
                } catch (Exception e) {
                  //  e.printStackTrace();
                    break;
                }
            }
            while (true){
                Future<List<DocumentData>> future = completionService.poll();
                if (future == null){
                    break;
                }
                List<DocumentData> documentDataList = future.get();
                for (DocumentData documentData : documentDataList) {
                    refreshInvertedIndex(documentData.getWords(), invertedIndex, documentData.getFileName());
                }
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
