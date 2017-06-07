package govsystem.dao;

import govsystem.domain.Video;

import java.util.List;

/**
 * Description: 操作Video
 * Created by Myth on 4/1/2017.
 */
public interface VideoDao {
    /**
     * 列出所有Video
     * @return
     */
    List<Video> list();

    /**
     * 添加
     * @param video
     * @return
     */
    boolean add(Video video);

    /**
     * 删除
     * @param video(根据vid)
     * @return
     */
    boolean delete(Video video);

    /**
     * 修改
     * @param video
     * @return
     */
    boolean update(Video video);

    /**
     * 获得Video对象
     * @param fileName
     * @return
     */
    Video get(String fileName);
}
