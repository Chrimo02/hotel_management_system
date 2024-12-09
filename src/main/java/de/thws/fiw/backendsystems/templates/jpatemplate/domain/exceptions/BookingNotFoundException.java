package de.thws.fiw.backendsystems.templates.jpatemplate.domain.exceptions;

public class BookingNotFoundException extends Exception {
    public BookingNotFoundException(){
        super();
    }
    public BookingNotFoundException(String message){
        super(message);
    }
}
