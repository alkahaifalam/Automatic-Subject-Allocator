/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author suhaib
 */
public class Subject {
    String code;
    String name;
    List<Teacher> teachers;

    public String getName() {
        return name;
    }
    
    public String getCode() {
        return code;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public Subject(String code, String name) {
        this.name = name;
        this.code = code;
        this.teachers = new ArrayList<>();
    }
}
