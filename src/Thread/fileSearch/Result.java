package Thread.fileSearch;

import java.util.List;

/**
 * 存放所搜结果类
 */
public class Result {

    private String filename;

    private String path;

    private boolean found = false;

    private List<String> foundsPath;

    public Result() {
    }

    public Result(String filename) {
        this.filename = filename;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getPath() {
        return path ;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public boolean getFound() {
        return found;
    }

    public void setFound(boolean found) {
        this.found = found;
    }

    public List<String> getFoundsPath() {
        return foundsPath;
    }

    public void setFoundsPath(List<String> foundsPath) {
        this.foundsPath = foundsPath;
    }
}
