package hotelmanagementsystem.domain.exceptions;

public class BookingNotFoundException extends Exception {
    public BookingNotFoundException(){
        super();
    }
    public BookingNotFoundException(String message){
        super(message);
    }
}
