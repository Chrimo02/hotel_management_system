package hotelmanagementsystem.domain.models;

public class DoubleRoom extends Room{

    public DoubleRoom(Builder builder) {
        super(builder.id, builder.pricePerNight, builder.roomIdentifier, builder.hotel);
    }
    public static class Builder {
        private long id;
        private final double pricePerNight;
        private final RoomIdentifier roomIdentifier;
        private final Hotel hotel;

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
