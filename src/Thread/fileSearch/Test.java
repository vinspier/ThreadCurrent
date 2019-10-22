package Thread.fileSearch;

public class Test {
    public static void main(String[] args) {
        Result result = new Result("package.json");
        String startPath = "E:\\VINSPIER\\vue";
        SerialSearch.searchFromPath(startPath,result.getFilename(),result);
        if (result.getFound()){
            System.out.println(result.getPath());
        }
    }

}
