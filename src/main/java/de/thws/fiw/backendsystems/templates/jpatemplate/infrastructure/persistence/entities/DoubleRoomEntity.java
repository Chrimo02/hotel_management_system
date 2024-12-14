package de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.entities;
import jakarta.persistence.*;

@Entity
@DiscriminatorValue("DOUBLE")
public class DoubleRoomEntity extends RoomEntity {
    public DoubleRoomEntity() {
        super();
    }

    public DoubleRoomEntity(long id, double pricePerNight, RoomIdentifierEntity roomIdentifierEntity, HotelEntity hotelEntity) {
        super(id, pricePerNight, roomIdentifierEntity, hotelEntity);
    }

    public DoubleRoomEntity(double pricePerNight, RoomIdentifierEntity roomIdentifierEntity, HotelEntity hotelEntity) {
        super(pricePerNight, roomIdentifierEntity, hotelEntity);
    }
}