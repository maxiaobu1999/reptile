package com.norman.reptile.utils;

import com.norman.reptile.dao.IMovieDao;
import com.norman.reptile.dao.IPlayAddressDao;
import com.norman.reptile.domain.Movie;
import com.norman.reptile.domain.PlayAddress;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class OkzywDbHelper {
    private InputStream in;
    private SqlSessionFactory factory;
    private SqlSession session;
    private IMovieDao mMovieDao;
    private IPlayAddressDao mPlayAddressDao;

    public OkzywDbHelper() throws IOException {
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        factory = new SqlSessionFactoryBuilder().build(in);
        session = factory.openSession();
        mMovieDao = session.getMapper(IMovieDao.class);
        mPlayAddressDao = session.getMapper(IPlayAddressDao.class);
    }

    public void destory() throws IOException {
        session.close();
        in.close();
    }

    public void addMovie(Movie movie) {
        List<Movie> list = mMovieDao.findByName(movie.getName());
        if (list.size() == 0) {
            //新增
            mMovieDao.saveMovie(movie);
        } else {
            //修改
            mMovieDao.updateMovie(movie);
        }
        session.commit();
        list = mMovieDao.findByName(movie.getName());
        Movie movie1 = list.get(0);
        List<PlayAddress> playAddressesOld = mPlayAddressDao.findByMovieId(movie1.getId());
        for (PlayAddress playAddress : movie.getPlayAddresses()) {
            boolean repeat = false;
            for (PlayAddress addressOld : playAddressesOld) {
                if (addressOld.getUrl().equals(playAddress.getUrl())) {
                    repeat = true;
                }
            }
            if (repeat) continue;
            playAddress.setMovie_id(movie1.getId());
            mPlayAddressDao.savePlayAddress(playAddress);
        }
        session.commit();
    }

    /** ture有更新或新的影片 */
    public boolean checkUpdatae(String name, long time) {
        List<Movie> movies = mMovieDao.findByName(name);
        if (movies.size() == 0) {
            return true;
        }
        Movie movie = movies.get(0);
        Long update_time = movie.getUpdate_time();
        if (time > update_time) {
            //有更新
            return true;
        }
        return false;
    }




}
