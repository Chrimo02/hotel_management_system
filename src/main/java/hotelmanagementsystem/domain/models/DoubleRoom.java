package hotelmanagementsystem.domain.models;

public class DoubleRoom extends Room{
    /*public DoubleRoom(long id, double pricePerNight, RoomIdentifier roomIdentifier, Hotel hotel) {
        super(id, pricePerNight, roomIdentifier, hotel);
    }
    public DoubleRoom(double pricePerNight, RoomIdentifier roomIdentifier, Hotel hotel) {
        super(pricePerNight, roomIdentifier, hotel);
    }*/
    public DoubleRoom(Builder builder) {
        super(builder.id, builder.pricePerNight, builder.roomIdentifier, builder.hotel);
    }
    public static class Builder {
        private long id;
        private double pricePerNight;
        private RoomIdentifier roomIdentifier;
        private Hotel hotel;

        public Builder (double pricePerNight, RoomIdentifier roomIdentifier, Hotel hotel) {
            this.pricePerNight = pricePerNight;
            this.roomIdentifier = roomIdentifier;
            this.hotel = hotel;
        }
        public Builder withId(long id) {
            this.id = id;
            return this;
        }
        public DoubleRoom build() {
            return new DoubleRoom(this);
        }
    }
}
