package com.gbasedbt.mybatis;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class StudentTest {

    private static SqlSessionFactory sf;
    private static SqlSession sqlsession;
    private static StudentMapper studentMapper;
    static String resource = "mybatis-config.xml";

    public static void main(String[] args) throws Exception {

        InputStream inputStream = Resources.getResourceAsStream(resource);
        sf = new SqlSessionFactoryBuilder().build(inputStream);
        sqlsession = sf.openSession();
        studentMapper = sqlsession.getMapper(StudentMapper.class);

        // 创建表
        doCreateStudent();

        // 增加用户
        for (int i=1; i<=10; i++) {
            doAddStudent("test00" + i, "user text 00" + i, ("user photo 00" + i).getBytes());
        }

        // 分页显示
        doListStudentsBypage(2,5);

        // 更新用户
        doUpdateStudent(8, "sdfasdfasdlfalsdflasdf");

        // 删除用户
        doDeleteStudent(9);
        doListStudentsBypage(2,5);

        // 批量增加用户
        ArrayList <Student> students = new ArrayList<Student>();
        Student stu1 = new Student();
        stu1.setStudent("batch 001", "text001");
        Student stu2 = new Student();
        stu2.setStudent("batch 002", "text001");
        Student stu3 = new Student();
        stu3.setStudent("batch 003", "text001");

        students.add(stu1);
        students.add(stu2);
        students.add(stu3);

        doBatchaddStudent(students);

        // 显示所有用户
        doListStudents();

        sqlsession.close();
    }

    /**
     * 执行增加单用户
     * @param username
     * @param usertext
     * @param userphoto
     */
    public static void doAddStudent(String username, String usertext, byte[] userphoto) {
        Student student = new Student();
        student.setUserName(username);
        student.setUserText(usertext);
        student.setUserPhoto(userphoto);

        studentMapper.addStudent(student);
        sqlsession.commit();
    }

    /**
     * 执行批量增加用户
     * @param students
     */
    public static void doBatchaddStudent(ArrayList <Student> students) {
        studentMapper.batchaddStudent(students);
        sqlsession.commit();
    }

    /**
     * 执行分页显示用户
     * @param pagenum
     * @param pagesize
     */
    public static void doListStudentsBypage(int pagenum, int pagesize) {
        int skiprows = 0;
        if (pagesize > 0) {
            skiprows = (pagenum - 1) * pagesize;
        }
        System.out.println("从第 " + (skiprows + 1) + " 行开始，显示 " + pagesize + " 行");
        List <Student> students = studentMapper.listStudentsBypage(skiprows, pagesize);
        for (Student stu : students) {
            System.out.println(stu.toString());
        }
    }

    /**
     * 执行显示所有用户
     */
    public static void doListStudents() {
        System.out.println("显示所有用户");
        List <Student> students = studentMapper.listStudents();
        for (Student stu : students) {
            System.out.println(stu.toString());
        }
    }

    /**
     * 删除指定用户
     * @param id
     */
    public static void doDeleteStudent(int id) {
        studentMapper.deleteStudent(id);
        sqlsession.commit();
    }

    /**
     * 修改指定用户
     * @param id
     * @param usertext
     */
    public static void doUpdateStudent(int id, String usertext) {
        studentMapper.updateStudent(id, usertext);
        sqlsession.commit();
    }

    /**
     * 创建student表
     */
    public static void doCreateStudent() {
        studentMapper.createStudent();
        sqlsession.commit();
    }

}
