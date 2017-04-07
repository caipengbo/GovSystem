package govsystem.domain;

/**
 * Description:问卷调查类
 * Created by Myth on 3/12/2017.
 */
public class Question {
    private int qid;
    private String title;  //标题
    private int aid;     //发布人
    private String postTime; //发布时间
    private int allNum;    //总参与人数
    private int aNum;  //选A的人数
    private int bNum;  //选B
    private int cNum;  //选C
    private int dNum;  //选D
    private int available;  //是否开放（是否还可以回答）
    private String name; //发布者名字,(仅供)查询时使用
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getQid() {
        return qid;
    }

    public void setQid(int qid) {
        this.qid = qid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public String getPostTime() {
        return postTime;
    }

    public void setPostTime(String postTime) {
        this.postTime = postTime;
    }

    public int getAllNum() {
        return allNum;
    }

    public void setAllNum(int allNum) {
        this.allNum = allNum;
    }

    public int getaNum() {
        return aNum;
    }

    public void setaNum(int aNum) {
        this.aNum = aNum;
    }

    public int getbNum() {
        return bNum;
    }

    public void setbNum(int bNum) {
        this.bNum = bNum;
    }

    public int getcNum() {
        return cNum;
    }

    public void setcNum(int cNum) {
        this.cNum = cNum;
    }

    public int getdNum() {
        return dNum;
    }

    public void setdNum(int dNum) {
        this.dNum = dNum;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }
}
