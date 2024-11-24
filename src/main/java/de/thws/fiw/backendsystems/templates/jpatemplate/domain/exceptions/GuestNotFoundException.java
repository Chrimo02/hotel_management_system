package de.thws.fiw.backendsystems.templates.jpatemplate.domain.exceptions;

import de.thws.fiw.backendsystems.templates.jpatemplate.domain.models.Guest;

public class GuestNotFoundException extends Exception{
    public GuestNotFoundException(){
        super();
    }
    public GuestNotFoundException(String message){
        super(message);
    }

    //TODO: Je nachdem wie wir die Methoden später benutzen,
    //      könnten wir überlegen custom-Exceptions für unsere domain-Klassen anzulegen
}
