package hotelmanagementsystem.infrastructure.persistence.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class GuestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column (nullable = false)
    private String firstName;
    @Column (nullable = false)
    private String lastName;
    @Column (nullable = false)
    private LocalDate birthday;
    @Column (nullable = false)
    private String eMail;
    @Column (nullable = false)
    private String phoneNumber;


    public GuestEntity(String firstName, String lastName, int yearBirthday,int monthBirtday, int dayBirthday, String eMail, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = LocalDate.of(yearBirthday,monthBirtday,dayBirthday);
        this.eMail = eMail;
        this.phoneNumber = phoneNumber;
    }
    public GuestEntity(){};

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public String geteMail() {
        return eMail;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }


    public void setId(long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}
