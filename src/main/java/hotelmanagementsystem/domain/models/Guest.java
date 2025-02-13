package hotelmanagementsystem.domain.models;
import java.time.LocalDate;

public class Guest {
    private long id;
    private String firstName;
    private String lastName;
    private LocalDate birthday;
    private String eMail;
    private String phoneNumber;

    private Guest(GuestBuilder guestBuilder) {
        this.firstName = guestBuilder.firstName;
        this.lastName = guestBuilder.lastName;
        this.birthday = guestBuilder.birthday;
        this.eMail = guestBuilder.eMail;
        this.phoneNumber = guestBuilder.phoneNumber;
        this.id = guestBuilder.id;
    }

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

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }




    public static class GuestBuilder{
        long id;
        String firstName;
        String lastName;
        LocalDate birthday;
        String eMail;
        String phoneNumber;

        public GuestBuilder(){};
        public GuestBuilder withFirstName(String firstName){
            this.firstName = firstName;
            return this;
        }
        public GuestBuilder withLastName(String lastName){
            this.lastName = lastName;
            return this;
        }

        public GuestBuilder withBirthday(int year, int month, int day){
            this.birthday = LocalDate.of(year,month,day);
            return this;
        }
        public GuestBuilder withEMail(String eMail){
            this.eMail = eMail;
            return this;
        }
        public GuestBuilder withPhoneNumber(String phoneNumber){
            this.phoneNumber = phoneNumber;
            return this;
        }
        public GuestBuilder withId(long id){
            this.id = id;
            return this;
        }

        public Guest build(){
            return new Guest(this);
        }
    }
}
