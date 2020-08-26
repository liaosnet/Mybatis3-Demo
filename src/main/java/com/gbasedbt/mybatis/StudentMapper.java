package com.gbasedbt.mybatis;

import java.util.ArrayList;
import org.apache.ibatis.annotations.Param;

public interface StudentMapper {

    public ArrayList <Student> listStudents();

    public ArrayList <Student> listStudentsBypage(@Param("pageNum") int pageNum, @Param("pageSize") int pageSize);

    public void addStudent(Student student);
    public void batchaddStudent(ArrayList <Student> students);
    public void foreachaddStudent(ArrayList <Student> students);

    public void updateStudent(@Param("id") int id, @Param("userText") String userText);
    public void deleteStudent(@Param("id") int id);

    public void createStudent();

}