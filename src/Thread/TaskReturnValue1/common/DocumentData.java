package Thread.TaskReturnValue1.common;

import java.util.Map;

/**
 * 降文档信息转换成文档对现象存储
 * fileName：文档的文件名
 * words：文档中 各个单词 已经出现的次数
 */
public class DocumentData {

    private String fileName;

    private Map<String,Integer> words;

    public DocumentData() {
    }

    public DocumentData(String fileName, Map<String, Integer> words) {
        this.fileName = fileName;
        this.words = words;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Map<String, Integer> getWords() {
        return words;
    }

    public void setWords(Map<String, Integer> words) {
        this.words = words;
    }
}
