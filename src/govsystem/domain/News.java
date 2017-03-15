package govsystem.domain;

/**
 * Description:新闻类（发布的信息）
 * Created by Myth on 3/12/2017.
 */
public class News {
    // nid  title digest context posttime aid applynum  lookednum messagenum ispublic
    private int nid;
    private String title;   //标题
    private String digest;  //摘要
    private String context; // 内容
    private String postTime;    //发表时间
    private int aid;  //外键，发表人

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    private int applyNum;   //申请查看的用户数目
    private int lookedNum;  //可查看的用户数目
    private int messageNum; // 留言数目
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

    public int getApplyNum() {
        return applyNum;
    }

    public void setApplyNum(int applyNum) {
        this.applyNum = applyNum;
    }

    public int getLookedNum() {
        return lookedNum;
    }

    public void setLookedNum(int lookedNum) {
        this.lookedNum = lookedNum;
    }

    public int getMessageNum() {
        return messageNum;
    }

    public void setMessageNum(int messageNum) {
        this.messageNum = messageNum;
    }

    public int getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(int isPublic) {
        this.isPublic = isPublic;
    }
}
