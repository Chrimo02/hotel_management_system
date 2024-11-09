package de.thws.fiw.backendsystems.templates.jpatemplate.domain.model;

import java.time.LocalDate;

public abstract class Person {
    private String firstName;
    private String lastName;
    private String title;
    private LocalDate birthday;

    public Person(String firstName, String lastName, String title, LocalDate birthday) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.title = title;
        this.birthday = birthday;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }
    public String getName(){
        return firstName + " " + lastName;
    }
}
