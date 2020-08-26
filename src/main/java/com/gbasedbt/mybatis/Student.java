package com.gbasedbt.mybatis;

/*
drop table if exists student;
create table student
(
  id serial not null,
  username varchar(60),
  usertext text,
  userphoto byte,
  primary key(id)
);
 */

public class Student {

    // 序号，自增长
    private int Id;
    // 用户名
    private String userName;
    // 简单信息
    private String userText;
    // 照片
    private byte[] userPhoto;

    public int getId() {
        return Id;
    }
    public void setId(int id) {
        Id = id;
    }

    public Student() {}

    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserText() {
        return userText;
    }
    public void setUserText(String userText) {
        this.userText = userText;
    }

    public byte[] getUserPhoto() {
        return userPhoto;
    }
    public void setUserPhoto(byte[] userPhoto) {
        this.userPhoto = userPhoto;
    }

    public void setStudent(String userName, String userText, byte[] userPhoto) {
        this.userName = userName;
        this.userText = userText;
        this.userPhoto = userPhoto;
    }

    public void setStudent(String userName, String userText) {
        this.userName = userName;
        this.userText = userText;
    }

    @Override
    public String toString() {
        return "学生  [序号 = " + Id + "\t姓名 = " + userName + "\t简介= " + userText + "]";
    }

}
