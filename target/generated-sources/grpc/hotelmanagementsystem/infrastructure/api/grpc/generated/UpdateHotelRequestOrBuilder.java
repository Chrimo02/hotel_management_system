// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: api.proto
// Protobuf Java Version: 3.25.0
package hotelmanagementsystem.infrastructure.api.grpc.generated;

public interface UpdateHotelRequestOrBuilder extends // @@protoc_insertion_point(interface_extends:hotelmanagement.UpdateHotelRequest)
com.google.protobuf.MessageOrBuilder {

    /**
     * <code>int64 id = 1;</code>
     * @return The id.
     */
    long getId();

    /**
     * <code>string name = 2;</code>
     * @return The name.
     */
    java.lang.String getName();

    /**
     * <code>string name = 2;</code>
     * @return The bytes for name.
     */
    com.google.protobuf.ByteString getNameBytes();

    /**
     * <code>string description = 3;</code>
     * @return The description.
     */
    java.lang.String getDescription();

    /**
     * <code>string description = 3;</code>
     * @return The bytes for description.
     */
    com.google.protobuf.ByteString getDescriptionBytes();

    /**
     * <code>.hotelmanagement.HotelLocation location = 4;</code>
     * @return Whether the location field is set.
     */
    boolean hasLocation();

    /**
     * <code>.hotelmanagement.HotelLocation location = 4;</code>
     * @return The location.
     */
    hotelmanagementsystem.infrastructure.api.grpc.generated.HotelLocation getLocation();

    /**
     * <code>.hotelmanagement.HotelLocation location = 4;</code>
     */
    hotelmanagementsystem.infrastructure.api.grpc.generated.HotelLocationOrBuilder getLocationOrBuilder();
}
