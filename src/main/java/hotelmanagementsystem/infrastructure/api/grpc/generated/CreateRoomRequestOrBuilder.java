// Generated by the protocol buffer compiler.  DO NOT EDIT!
// NO CHECKED-IN PROTOBUF GENCODE
// source: api.proto
// Protobuf Java Version: 4.27.4

package hotelmanagementsystem.infrastructure.api.grpc.generated;

public interface CreateRoomRequestOrBuilder extends
    // @@protoc_insertion_point(interface_extends:hotelmanagement.CreateRoomRequest)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>double pricePerNight = 1;</code>
   * @return The pricePerNight.
   */
  double getPricePerNight();

  /**
   * <code>string type = 2;</code>
   * @return The type.
   */
  java.lang.String getType();
  /**
   * <code>string type = 2;</code>
   * @return The bytes for type.
   */
  com.google.protobuf.ByteString
      getTypeBytes();

  /**
   * <code>int64 hotelId = 3;</code>
   * @return The hotelId.
   */
  long getHotelId();
}
