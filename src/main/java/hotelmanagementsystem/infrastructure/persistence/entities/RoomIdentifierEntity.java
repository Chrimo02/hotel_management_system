package hotelmanagementsystem.infrastructure.persistence.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "room_identifier")
public class RoomIdentifierEntity {
    @Id
    private long id;
    private String building;
    private int floor;
    private String roomNumber;
    @OneToOne
    @JoinColumn(name = "room_id")
    private RoomEntity room;
    public RoomIdentifierEntity() {

    }
    public RoomIdentifierEntity(String building, int floor, String roomNumber) {
        this.building = building;
        this.floor = floor;
        this.roomNumber = roomNumber;
    }
    public RoomIdentifierEntity(long id, String building, int floor, String roomNumber) {
        this.id = id;
        this.building = building;
        this.floor = floor;
        this.roomNumber = roomNumber;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }
}
