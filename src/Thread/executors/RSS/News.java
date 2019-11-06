package Thread.executors.RSS;

/**
 * @ClassName: News
 * @Description: 新闻实体类信息
 * @Author:
 * @Date: 2019/11/6 17:24
 * @Version V1.0
 **/
public class News {

    private String id;

    private String tag;

    private String title;

    private String source;

    private String content;

    public News() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
