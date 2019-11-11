package com.example.questionlarry;

import java.util.List;

public class Form {
    public String name;
    public String location;
    public String birthDate;
    private String gender;
    private List<String> hobbies;
    private String department;
    private String yearOfStudy;
    private String longAnswer;
    private String imageUri;


    public Form(){}

    public String getImageUri() {
        return imageUri;
    }

    public Form(String name, String location, String birthDate, String gender, List<String> hobbies,
                String department, String yearOfStudy, String longAnswer, String imageUri) {
        this.name = name;
        this.location = location;
        this.birthDate = birthDate;
        this.gender = gender;
        this.hobbies = hobbies;
        this.department = department;
        this.yearOfStudy = yearOfStudy;
        this.longAnswer = longAnswer;
        this.imageUri = imageUri;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getGender() {
        return gender;
    }

    public List<String> getHobbies() {
        return hobbies;
    }

    public String getDepartment() {
        return department;
    }

    public String getYearOfStudy() {
        return yearOfStudy;
    }

    public String getLongAnswer() {
        return longAnswer;
    }
}
