package govsystem.domain;

/**
 * Description:留言（新闻下面用户留言）
 * Created by Myth on 3/12/2017.
 */
public class Message {
    private int mid;
    private String context; // 内容
    private String postTime;    //发表时间
    private int uid;  //外键，发表人
    private int nid;  //对应的新闻

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
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
