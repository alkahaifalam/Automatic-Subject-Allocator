/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author suhaib
 */
public class Allocation {
    Subject subject;
    Teacher teacher;

    public Subject getSubject() {
        return subject;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public Allocation(Subject subject, Teacher teacher) {
        this.subject = subject;
        this.teacher = teacher;
    }
}
