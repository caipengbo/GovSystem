package govsystem.domain;

/**
 * Description:留言（新闻下面用户留言）
 * Created by Myth on 3/12/2017.
 */
public class Message {
    private int mid;
    private String content; // 内容
    private String postTime;    //发表时间
    private int uid;  //外键，发表人
    private String name;  //发表人名字
    private int nid;  //对应的新闻
    private int state; //0待审核 1审核通过

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
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

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getNid() {
        return nid;
    }

    public void setNid(int nid) {
        this.nid = nid;
    }
}
