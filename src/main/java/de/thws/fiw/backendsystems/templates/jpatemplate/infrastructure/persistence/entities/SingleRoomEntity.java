package de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.entities;
import jakarta.persistence.*;

@Entity
@DiscriminatorValue("SINGLE") // Der Wert für diese Entität in der Tabelle
public class SingleRoomEntity extends RoomEntity {
    public SingleRoomEntity() {
        super();
    }

    public SingleRoomEntity(long id, double pricePerNight, RoomIdentifierEntity roomIdentifierEntity, HotelEntity hotelEntity) {
        super(id, pricePerNight, roomIdentifierEntity, hotelEntity);
    }

    public SingleRoomEntity(double pricePerNight, RoomIdentifierEntity roomIdentifierEntity, HotelEntity hotelEntity) {
        super(pricePerNight, roomIdentifierEntity, hotelEntity);
    }
}