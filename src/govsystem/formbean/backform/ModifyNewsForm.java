package govsystem.formbean.backform;

/**
 * Description: 修改新闻信息表单
 * Created by Myth on 3/14/2017.
 */
public class ModifyNewsForm {
    private int nid;
    private String title;
    private String digest;
    private String context;
    private String postTime;
    //TODO 添加发表人(管理员)外键，每次修改时候都要改变aid,与时间
    //private int aid;
    private int isPublic; //1公开  0非公开

    public int getNid() {
        return nid;
    }

    public void setNid(int nid) {
        this.nid = nid;
    }

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
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getPostTime() {
        return postTime;
    }

    public void setPostTime(String postTime) {
        this.postTime = postTime;
    }

    public int getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(int isPublic) {
        this.isPublic = isPublic;
    }
}
