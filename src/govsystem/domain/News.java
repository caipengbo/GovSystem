package govsystem.domain;

/**
 * Description:新闻类（发布的信息）
 * Created by Myth on 3/12/2017.
 */
public class News {
    // nid  title digest content posttime aid applynum  lookednum messagenum ispublic
    private int nid;
    private String title;   //标题
    private String digest;  //摘要
    private String content; // 内容
    private String postTime;    //发表时间
    private String name;  //外键，发表人
    private int aid;
    private int status; //查看状态 0 申请查看 1：申请中 2：可查看 仅供用户查看使用
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }



    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
