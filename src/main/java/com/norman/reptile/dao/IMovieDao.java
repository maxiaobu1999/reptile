package com.norman.reptile.dao;

import com.norman.reptile.domain.Movie;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;
@CacheNamespace(blocking = true)//开启二级缓存
public interface IMovieDao {


    /**
     * 保存影片
     *
     * @param movie 视频
     */
    @Insert("insert into movie(name,title_tip,type,tag ,cover_url,score,score_total,score_num,alias" +
            ",director,actors,language,region" +
            ",release_time" +
            ",length" +
            ",update_time,views_total,views_today" +
            ",summary)" +
            "values(#{name},#{title_tip},#{type},#{tag},#{cover_url},#{score},#{score_total},#{score_num},#{alias}" +
            ",#{director},#{actors},#{language},#{region}" +
            ",#{release_time}" +
            ",#{length}" +
            ",#{update_time},#{views_total},#{views_today}" +
            ",#{summary})")
    @Results(id = "movieMap", value = {
            // @Many对多；FetchType.LAZY延迟加载，对多用
            @Result(property = "playAddresses", column = "movie_id",
                    many = @Many(select = "com.norman.reptile.dao.IPlayAddressDao.findByMovieId",
                            fetchType = FetchType.LAZY))
    })
    void saveMovie(Movie movie);

    /**
     * 根据名称修改影片
     */
    @Update("update movie set name=#{name},title_tip=#{title_tip}" +
            ",type=#{type},tag=#{tag},cover_url=#{cover_url},score=#{score},score_total=#{score_total}" +
            ",score_num=#{score_num},alias=#{alias},director=#{director},actors=#{actors}," +
            "language=#{language},region=#{region},release_time=#{release_time},length=#{length},update_time=#{update_time}," +
            "views_total=#{views_total},views_today=#{views_today},summary=#{summary} where name=#{name}")
    void updateMovie(Movie movie);

    /**
     * 查询所有视频
     */
    @Select("select * from movie")
    List<Movie> findAll();

    /**
     * 根据影片名称查询影片
     */
    @Select("select * from movie  where name=#{name} ")
    List<Movie> findByName(String name);
    /**
     * 根据影片id查询影片
     */
    @Select("select * from movie  where id=#{id} ")
    Movie findById(int id);
}
