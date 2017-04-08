package govsystem.formbean.backform;

/**
 * Description:添加听证视频表单
 * Created by Myth on 4/1/2017.
 */
public class AddVideoForm {
    private String title;  //标题
    private String description; //说明

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
