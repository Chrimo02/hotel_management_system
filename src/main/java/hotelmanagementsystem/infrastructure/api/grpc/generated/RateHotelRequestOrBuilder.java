// Generated by the protocol buffer compiler.  DO NOT EDIT!
// NO CHECKED-IN PROTOBUF GENCODE
// source: api.proto
// Protobuf Java Version: 4.27.4

package hotelmanagementsystem.infrastructure.api.grpc.generated;

public interface RateHotelRequestOrBuilder extends
    // @@protoc_insertion_point(interface_extends:hotelmanagement.RateHotelRequest)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>int64 hotelId = 1;</code>
   * @return The hotelId.
   */
  long getHotelId();

  /**
   * <code>int64 guestId = 2;</code>
   * @return The guestId.
   */
  long getGuestId();

  /**
   * <code>int32 rating = 3;</code>
   * @return The rating.
   */
  int getRating();

  /**
   * <code>string comment = 4;</code>
   * @return The comment.
   */
  java.lang.String getComment();
  /**
   * <code>string comment = 4;</code>
   * @return The bytes for comment.
   */
  com.google.protobuf.ByteString
      getCommentBytes();
}
