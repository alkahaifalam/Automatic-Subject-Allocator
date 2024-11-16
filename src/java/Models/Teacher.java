/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.util.List;

/**
 *
 * @author suhaib
 */
public class Teacher {
    String username;
    String name;
    int experience;
    List<String> preferences;

    public String getName() {
        return name;
    }

    public List<String> getPreferences() {
        return preferences;
    }

    public int getExperience() {
        return experience;
    }
    
    public String getUsername(){
        return username;
    }

    public Teacher(String name, String username, List<String> preferences, int experience) {
        this.name = name;
        this.username = username;
        this.preferences = preferences;
        this.experience = experience;
    }
}

