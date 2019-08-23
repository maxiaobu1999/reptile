package com.norman.reptile.utils;

import com.norman.reptile.dao.ICourseDao;
import com.norman.reptile.domain.Course;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class DbHelper {
    private InputStream in;
    private SqlSessionFactory factory;
    private SqlSession session;
    private ICourseDao mCourseDao;

    public DbHelper() throws IOException {
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        factory = new SqlSessionFactoryBuilder().build(in);
        session = factory.openSession();
        mCourseDao = session.getMapper(ICourseDao.class);
    }

    public void destory() throws IOException {
        session.close();
        in.close();
    }


    /**
     * 添加课程
     */
    public void addCourse(Course course) {
        List<Course> list = mCourseDao.findByCourseId(course.getCourseId());
        if (list.size() == 0) {
            //新增
            mCourseDao.saveCourse(course);
        } else {
            //修改
            Course courseOld = list.get(0);
            courseOld.setTitle(course.getTitle());
            courseOld.setCourseProfile(course.getCourseProfile());
            courseOld.setViews(course.getViews());
            courseOld.setOrigin(course.getOrigin());
            courseOld.setNode(course.getNode());
            courseOld.setNodeId(course.getNodeId());
            courseOld.setTime(course.getTime());
            mCourseDao.updateCourse(courseOld);
        }
        session.commit();
    }

}
