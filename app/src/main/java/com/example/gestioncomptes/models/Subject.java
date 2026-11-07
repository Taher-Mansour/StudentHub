package com.example.gestioncomptes.models;

public class Subject {

    private int id;
    private String name;
    private String code;
    private String teacher;
    private String description;
    private int userId;

    public Subject() {
    }

    public Subject(String name, String code, String teacher,
                   String description, int userId) {
        this.name = name;
        this.code = code;
        this.teacher = teacher;
        this.description = description;
        this.userId = userId;
    }

    public Subject(int id, String name, String code, String teacher,
                   String description, int userId) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.teacher = teacher;
        this.description = description;
        this.userId = userId;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}