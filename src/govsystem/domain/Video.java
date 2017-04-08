package govsystem.domain;

/**
 * Description: 发布的听证视频（文件存在服务器中，文件名存在数据库中）
 * Created by Myth on 4/1/2017.
 */
public class Video {
    private int vid;
    private String fileName; //文件名
    private String title;  //标题
    private String description; //说明
    private int aid; //发布人
    private String name; //发布人姓名
    private String posttime;//发布时间

    public int getVid() {
        return vid;
    }

    public void setVid(int vid) {
        this.vid = vid;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getPosttime() {
        return posttime;
    }

    public void setPosttime(String posttime) {
        this.posttime = posttime;
    }
}
