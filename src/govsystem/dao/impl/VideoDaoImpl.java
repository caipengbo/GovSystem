package govsystem.dao.impl;

import govsystem.dao.VideoDao;
import govsystem.domain.Video;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Description:
 * Created by Myth on 4/1/2017.
 */
@Repository
public class VideoDaoImpl implements VideoDao {
    @Resource
    private JdbcTemplate jdbcTemplate;

    class VideoRowMapper implements RowMapper<Video> {
        //rs为返回结果集，以每行为单位封装着
        public Video mapRow(ResultSet rs, int rowNum) throws SQLException {
            Video video = new Video();
            video.setVid(rs.getInt("vid"));
            video.setFileName(rs.getString("filename"));
            video.setTitle(rs.getString("title"));
            video.setDescription(rs.getString("description"));
            video.setAid(rs.getInt("aid"));
            video.setName(rs.getString("name"));
            video.setPosttime(rs.getString("posttime"));
            return video;
        }

    }

    @Override
    public List<Video> list() {
        List<Video> videoList;
        String sql = "select * from tb_video,tb_admin where tb_video.aid=tb_admin.aid";
        try {
            videoList = jdbcTemplate.query(sql, new VideoRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return  videoList;
    }

    @Override
    public boolean add(Video video) {
        String sql = "insert into tb_video(filename,title,description,aid,posttime) " +
                "values(?,?,?,?,now())";
        int affectedNum = jdbcTemplate.update(sql,video.getFileName(),video.getTitle(),
                video.getDescription(),video.getAid());
        if (affectedNum != 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean delete(Video video) {
        String sql = "delete from tb_video where vid=?";
        int affectedNum = jdbcTemplate.update(sql,video.getVid());
        if (affectedNum != 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean update(Video video) {
        String sql = "";
        int effectedNum = 0;
        sql = "update tb_video set title=?,description=? where vid=?";
        try {
            effectedNum = jdbcTemplate.update(sql,video.getTitle(),
                    video.getDescription(),video.getVid());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return (effectedNum != 0);
    }

    @Override
    public Video get(String fileName) {
        List<Video> videoList;
        String sql = "select * from tb_video,tb_admin where tb_video.aid=tb_admin.aid and filename=?";
        try {
            videoList = jdbcTemplate.query(sql, new VideoRowMapper(),fileName);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        if (videoList != null) {
            return videoList.get(0);
        }
        return null;
    }
}
