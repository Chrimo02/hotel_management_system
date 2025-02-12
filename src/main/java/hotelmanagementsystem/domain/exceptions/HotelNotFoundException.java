package hotelmanagementsystem.domain.exceptions;

public class HotelNotFoundException extends Exception {
    public HotelNotFoundException(){
        super();
    }
    public HotelNotFoundException(String message){
        super(message);
    }
}
