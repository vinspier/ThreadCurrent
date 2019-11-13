package Thread.TaskReturnValue1.task;

import Thread.TaskReturnValue1.common.DocumentData;
import Thread.TaskReturnValue1.common.DocumentParser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * 解析文档为文档存储对象
 */
public class DocumentMultipleParseTask implements Callable<List<DocumentData>> {

    private List<File> files;

    public DocumentMultipleParseTask(List<File> files) {
        this.files = files;
    }

    @Override
    public List<DocumentData> call() throws Exception {
        List<DocumentData> documentDataList = new ArrayList<>();
        for (File file : files){
            Map<String,Integer> words = DocumentParser.parse(file.getAbsolutePath());
            DocumentData documentData = new DocumentData(file.getName(),words);
            documentDataList.add(documentData);
        }
        return documentDataList;
    }

}
