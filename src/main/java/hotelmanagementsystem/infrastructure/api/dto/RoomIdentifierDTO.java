package hotelmanagementsystem.infrastructure.api.dto;
import hotelmanagementsystem.infrastructure.api.grpc.generated.RoomIdentifier;
public class RoomIdentifierDTO {
    private String building;
    private int floor;
    private String roomNumber;

    public RoomIdentifierDTO(String building, int floor, String roomNumber) {
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

    public RoomIdentifier toProtobuf() {
        return RoomIdentifier.newBuilder()
                .setBuilding(this.building)
                .setFloor(this.floor)
                .setRoomNumber(this.roomNumber)
                .build();
    }

}
