package govsystem.formbean.backform;

/**
 * Description: 添加新闻表单
 * Created by Myth on 3/15/2017.
 */
public class AddNewsForm {
    private String title;
    private String digest;
    private String content;
    private int isPublic;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public String getContext() {
        return content;
    }

    public void setContext(String context) {
        this.content = context;
    }

    public int getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(int isPublic) {
        this.isPublic = isPublic;
    }
}
