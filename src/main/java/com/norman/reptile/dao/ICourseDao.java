package com.norman.reptile.dao;

import com.norman.reptile.domain.Course;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ICourseDao {
    /**
     * 保存课程
     *
     * @param course 课程
     */
    @Insert("insert into course(courseId,imgUrl,title,node,nodeId,origin,time,views,courseProfile)" +
            "values(#{courseId},#{imgUrl},#{title},#{node},#{nodeId},#{origin},#{time},#{views},#{courseProfile})")
    @Results(id = "userMap", value = {
            @Result(id = true, column = "ID", property = "ID"),
            @Result(column = "courseId", property = "courseId"),
            @Result(column = "imgUrl", property = "imgUrl"),
            @Result(column = "title", property = "title"),
            @Result(column = "node", property = "node"),
            @Result(column = "nodeId", property = "nodeId"),
            @Result(column = "origin", property = "origin"),
            @Result(column = "time", property = "time"),
            @Result(column = "views", property = "views"),
            @Result(column = "courseProfile", property = "courseProfile")
//            ,
//            // @Many对多；FetchType.LAZY延迟加载，对多用
//            @Result(property = "lessons", column = "courseId",
//                    many = @Many(select = "com.norman.learndu.com.norman.reptile.dao.ILessonDao.saveLesson",
//                            fetchType = FetchType.LAZY))
    })
    void saveCourse(Course course);


    /**
     * 查询所有课程
     */
    @Select("select * from course")
    List<Course> findAll();


    /**
     * 根据id查询课程
     *
     * @param ID
     * @return
     */
    @Select("select * from course  where id=#{id} ")
    Course findById(Integer ID);

    /**
     * 根据courseId查询课程
     *
     * @param courseId
     */
    @Select("select * from course  where courseId=#{courseId} ")
    List<Course> findByCourseId(Integer courseId);

    /**
     * 修改课程
     */
    @Update("update course set imgUrl=#{imgUrl},title=#{title}" +
            ",node=#{node},nodeId=#{nodeId},origin=#{origin},time=#{time}" +
            ",views=#{views},courseProfile=#{courseProfile} where courseId=#{courseId}")
    void updateCourse(Course course);
//
//    /**
//     * 删除用户
//     * @param userId
//     */
//    @Delete("delete from user where id=#{id} ")
//    void deleteUser(Integer userId);

//    /**
//     * 根据用户名称模糊查询
//     * @param username
//     * @return
//     */
////    @Select("select * from user where username like #{username} ")
//    @Select("select * from user where username like '%${value}%' ")
//    List<User> findUserByName(String username);
//
//    /**
//     * 查询总用户数量
//     * @return
//     */
//    @Select("select count(*) from user ")
//    int findTotalUser();
}
