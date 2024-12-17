package de.thws.fiw.backendsystems.templates.jpatemplate.domain.exceptions;

public class RoomNotFoundException extends Exception{
    public RoomNotFoundException(){
        super();
    }
    public RoomNotFoundException(String message){
        super(message);
    }
}
