package govsystem.domain;

/**
 * Description:新闻类（发布的信息）
 * Created by Myth on 3/12/2017.
 */
public class News {
    private int nid;
    private String context;
    //TODO 添加发表人外键
    //private int aid;
    private String posttime;
    private int ispublic; //1公开  0非公开

    public int getNid() {
        return nid;
    }

    public void setNid(int nid) {
        this.nid = nid;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getPosttime() {
        return posttime;
    }

    public void setPosttime(String posttime) {
        this.posttime = posttime;
    }

    public int getIspublic() {
        return ispublic;
    }

    public void setIspublic(int ispublic) {
        this.ispublic = ispublic;
    }
}
