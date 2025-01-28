package hotelmanagementsystem.infrastructure.api.dto;


public class RoomIdentifierDTO {
    private long id;
    private String building;
    private int floor;
    private String roomNumber;

    public RoomIdentifierDTO(String building, int floor, String roomNumber) {
        this.building = building;
        this.floor = floor;
        this.roomNumber = roomNumber;
    }
    public RoomIdentifierDTO(long id, String building, int floor, String roomNumber) {
        this.id = id;
        this.building = building;
        this.floor = floor;
        this.roomNumber = roomNumber;
    }
    public long getId() {
        return id;
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
