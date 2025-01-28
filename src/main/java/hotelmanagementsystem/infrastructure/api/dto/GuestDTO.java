package hotelmanagementsystem.infrastructure.api.dto;

import hotelmanagementsystem.infrastructure.api.grpc.generated.Guest;

import java.time.LocalDate;

public class GuestDTO {
    private long id;
    private String firstName;
    private String lastName;
    private String title;
    private LocalDate birthday;
    private String eMail;
    private String phoneNumber;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public Guest toProtobuf(){
        return Guest.newBuilder()
                .setId(this.id)
                .setFirstName(this.firstName)
                .setLastName(this.lastName)
                .setBirthday(this.birthday.toString())
                .setEmail(this.eMail)
                .setPhoneNumber(this.phoneNumber)
                .build();
    }
}
