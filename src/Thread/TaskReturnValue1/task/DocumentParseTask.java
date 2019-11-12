package Thread.TaskReturnValue1.task;

import Thread.TaskReturnValue1.common.DocumentData;
import Thread.TaskReturnValue1.common.DocumentParser;

import java.io.File;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * 解析文档为文档存储对象
 */
public class DocumentParseTask implements Callable<DocumentData> {

    private File file;

    public DocumentParseTask(File file) {
        this.file = file;
    }

    @Override
    public DocumentData call() throws Exception {
        Map<String,Integer> words = DocumentParser.parse(file.getAbsolutePath());
        DocumentData documentData = new DocumentData(file.getName(),words);
        return documentData;
    }

}
