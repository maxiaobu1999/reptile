package com.norman.reptile;

import com.norman.reptile.dao.IMovieDao;
import com.norman.reptile.domain.Movie;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

public class TestMovie {
    private InputStream in;
    private SqlSessionFactory factory;
    private SqlSession session;
    private IMovieDao mMovieDao;

    @Before
    public void init() throws Exception {
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        factory = new SqlSessionFactoryBuilder().build(in);
        session = factory.openSession();
        mMovieDao = session.getMapper(IMovieDao.class);
    }

    @After
    public void destroy() throws Exception {
        session.commit();
        session.close();
        in.close();
    }


    /**
     * 查找全部
     */
    @Test
    public void testFindAll() {
        List<Movie> all = mMovieDao.findAll();
        for (Movie movie : all) {
            System.out.println(movie.getId());
        }
    }
    /**
     * 根据ID查课时
     */
    @Test
    public void testFindByName() {
        List<Movie> movies = mMovieDao.findByName("铁马寻桥粤语");
        if (movies.size()>0)
        System.out.println(movies.get(0));
    }
}
