package com.norman.reptile.dao;

import com.norman.reptile.domain.PlayAddress;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface IPlayAddressDao {
    @Results(id="PlayAddressMap",value = {
//            @Result(id=true,column = "id",property = "id"),
//            @Result(column = "uid",property = "uid"),
//            @Result(column = "money",property = "money"),
            //FetchType.EAGER》》立即加载 对一用
            //FetchType.LAZY》》延迟加载  对二用
            //FetchType.DEFAULT 》》
            //one=@One》》对一
            @Result(property = "movie",column = "movie_id",one=@One(select="com.norman.reptile.dao.IMovieDao.findById",fetchType= FetchType.EAGER))
    })
    /**
     * 保存播放地址
     *
     * @param playAddress 播放地址
     */
    @Insert("insert into play_address(movie_id,media_type,url " +
            ",title)" +
            "values(#{movie_id},#{media_type},#{url}" +
            ",#{title})")
    void savePlayAddress(PlayAddress playAddress);


    /**
     * 根据影片id查询链接
     */
    @Select("select * from play_address  where movie_id=#{movie_id} ")
    List<PlayAddress> findByMovieId(int movie_id);

}
