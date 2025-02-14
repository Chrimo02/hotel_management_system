package hotelmanagementsystem.infrastructure.persistence.entities;
import jakarta.persistence.*;

@Entity
@DiscriminatorValue("SINGLE")
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