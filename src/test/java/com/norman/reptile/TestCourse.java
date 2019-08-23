package com.norman.reptile;

import com.norman.reptile.dao.ICourseDao;
import com.norman.reptile.domain.Course;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

public class TestCourse {
    private InputStream in;
    private SqlSessionFactory factory;
    private SqlSession session;
    private ICourseDao mCourseDao;

    @Before
    public void init() throws Exception {
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        factory = new SqlSessionFactoryBuilder().build(in);
        session = factory.openSession();
        mCourseDao = session.getMapper(ICourseDao.class);
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
        List<Course> all = mCourseDao.findAll();
        for (Course course : all) {
            System.out.println(course.getID());
        }
    }

}
