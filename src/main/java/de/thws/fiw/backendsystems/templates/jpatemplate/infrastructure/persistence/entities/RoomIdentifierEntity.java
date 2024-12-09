package de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.persistence.entities;

import de.thws.fiw.backendsystems.templates.jpatemplate.domain.models.Room;
import jakarta.persistence.*;

@Entity
@Table(name = "room_identifier")
public class RoomIdentifierEntity {
    private String building;
    private int floor;
    @Id
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
