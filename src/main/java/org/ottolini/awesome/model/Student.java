package org.ottolini.awesome.model;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Entity;
/**
 * Created by virtual on 25-12-2017.
 */

@Entity(name="STUDENTS")
public class Student extends AbstractEntity {
    @Column(name="STUDENT_NAME")
    @NotEmpty //JSR 303
    private String name;

    @Column(name="EMAIL")
    @NotEmpty
    @Email
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
