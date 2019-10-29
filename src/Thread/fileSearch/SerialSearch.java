package Thread.fileSearch;

import java.io.File;

/**
 * 递归 串行方式搜索文件
 */
public class SerialSearch {

    private SerialSearch(){

    }

    public static void searchFromPath(String startPath,String targetFile,Result result){
        File file = new File(startPath);
        searchFromFile(file,targetFile,result);
    }

    public static void searchFromFile(File file,String targetFile,Result result){
        File[] files = file.listFiles();
        if (files == null || files.length < 1){
            return;
        }
        for (File f : files){
            if (f.isDirectory()){
                searchFromFile(f,targetFile,result);
            }else {
                if (f.getName().equals(targetFile)){
                    result.setPath(f.getAbsolutePath());
                    result.setFound(true);
                    return;
                }
            }
            if (result.getFound()){
                return;
            }
        }
    }

}
