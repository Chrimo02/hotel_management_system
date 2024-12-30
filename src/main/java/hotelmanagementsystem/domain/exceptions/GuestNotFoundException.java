package hotelmanagementsystem.domain.exceptions;

public class GuestNotFoundException extends Exception{
    public GuestNotFoundException(){
        super();
    }
    public GuestNotFoundException(String message){
        super(message);
    }

}
