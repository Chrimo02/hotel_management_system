// Generated by the protocol buffer compiler.  DO NOT EDIT!
// NO CHECKED-IN PROTOBUF GENCODE
// source: api.proto
// Protobuf Java Version: 4.27.4

package hotelmanagementsystem.infrastructure.api.grpc.generated;

/**
 * Protobuf type {@code hotelmanagement.BookingResponse}
 */
public final class BookingResponse extends
    com.google.protobuf.GeneratedMessage implements
    // @@protoc_insertion_point(message_implements:hotelmanagement.BookingResponse)
    BookingResponseOrBuilder {
private static final long serialVersionUID = 0L;
  static {
    com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
      com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
      /* major= */ 4,
      /* minor= */ 27,
      /* patch= */ 4,
      /* suffix= */ "",
      BookingResponse.class.getName());
  }
  // Use BookingResponse.newBuilder() to construct.
  private BookingResponse(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
    super(builder);
  }
  private BookingResponse() {
  }

  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return hotelmanagementsystem.infrastructure.api.grpc.generated.HotelManagement.internal_static_hotelmanagement_BookingResponse_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return hotelmanagementsystem.infrastructure.api.grpc.generated.HotelManagement.internal_static_hotelmanagement_BookingResponse_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            hotelmanagementsystem.infrastructure.api.grpc.generated.BookingResponse.class, hotelmanagementsystem.infrastructure.api.grpc.generated.BookingResponse.Builder.class);
  }

  private int bitField0_;
  public static final int BOOKING_FIELD_NUMBER = 1;
  private hotelmanagementsystem.infrastructure.api.grpc.generated.Booking booking_;
  /**
   * <code>.hotelmanagement.Booking booking = 1;</code>
   * @return Whether the booking field is set.
   */
  @java.lang.Override
  public boolean hasBooking() {
    return ((bitField0_ & 0x00000001) != 0);
  }
  /**
   * <code>.hotelmanagement.Booking booking = 1;</code>
   * @return The booking.
   */
  @java.lang.Override
  public hotelmanagementsystem.infrastructure.api.grpc.generated.Booking getBooking() {
    return booking_ == null ? hotelmanagementsystem.infrastructure.api.grpc.generated.Booking.getDefaultInstance() : booking_;
  }
  /**
   * <code>.hotelmanagement.Booking booking = 1;</code>
   */
  @java.lang.Override
  public hotelmanagementsystem.infrastructure.api.grpc.generated.BookingOrBuilder getBookingOrBuilder() {
    return booking_ == null ? hotelmanagementsystem.infrastructure.api.grpc.generated.Booking.getDefaultInstance() : booking_;
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (((bitField0_ & 0x00000001) != 0)) {
      output.writeMessage(1, getBooking());
    }
    getUnknownFields().writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (((bitField0_ & 0x00000001) != 0)) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(1, getBooking());
    }
    size += getUnknownFields().getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof hotelmanagementsystem.infrastructure.api.grpc.generated.BookingResponse)) {
      return super.equals(obj);
    }
    hotelmanagementsystem.infrastructure.api.grpc.generated.BookingResponse other = (hotelmanagementsystem.infrastructure.api.grpc.generated.BookingResponse) obj;

    if (hasBooking() != other.hasBooking()) return false;
    if (hasBooking()) {
      if (!getBooking()
          .equals(other.getBooking())) return false;
    }
    if (!getUnknownFields().equals(other.getUnknownFields())) return false;
    return true;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    if (hasBooking()) {
      hash = (37 * hash) + BOOKING_FIELD_NUMBER;
      hash = (53 * hash) + getBooking().hashCode();
    }
    hash = (29 * hash) + getUnknownFields().hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static hotelmanagementsystem.infrastructure.api.grpc.generated.BookingResponse parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static hotelmanagementsystem.infrastructure.api.grpc.generated.BookingResponse parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static hotelmanagementsystem.infrastructure.api.grpc.generated.BookingResponse parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static hotelmanagementsystem.infrastructure.api.grpc.generated.BookingResponse parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static hotelmanagementsystem.infrastructure.api.grpc.generated.BookingResponse parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static hotelmanagementsystem.infrastructure.api.grpc.generated.BookingResponse parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static hotelmanagementsystem.infrastructure.api.grpc.generated.BookingResponse parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseWithIOException(PARSER, input);
  }
  public static hotelmanagementsystem.infrastructure.api.grpc.generated.BookingResponse parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public static hotelmanagementsystem.infrastructure.api.grpc.generated.BookingResponse parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseDelimitedWithIOException(PARSER, input);
  }

  public static hotelmanagementsystem.infrastructure.api.grpc.generated.BookingResponse parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static hotelmanagementsystem.infrastructure.api.grpc.generated.BookingResponse parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseWithIOException(PARSER, input);
  }
  public static hotelmanagementsystem.infrastructure.api.grpc.generated.BookingResponse parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(hotelmanagementsystem.infrastructure.api.grpc.generated.BookingResponse prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessage.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code hotelmanagement.BookingResponse}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessage.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:hotelmanagement.BookingResponse)
      hotelmanagementsystem.infrastructure.api.grpc.generated.BookingResponseOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return hotelmanagementsystem.infrastructure.api.grpc.generated.HotelManagement.internal_static_hotelmanagement_BookingResponse_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return hotelmanagementsystem.infrastructure.api.grpc.generated.HotelManagement.internal_static_hotelmanagement_BookingResponse_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              hotelmanagementsystem.infrastructure.api.grpc.generated.BookingResponse.class, hotelmanagementsystem.infrastructure.api.grpc.generated.BookingResponse.Builder.class);
    }

    // Construct using hotelmanagementsystem.infrastructure.api.grpc.generated.BookingResponse.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessage.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessage
              .alwaysUseFieldBuilders) {
        getBookingFieldBuilder();
      }
    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      bitField0_ = 0;
      booking_ = null;
      if (bookingBuilder_ != null) {
        bookingBuilder_.dispose();
        bookingBuilder_ = null;
      }
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return hotelmanagementsystem.infrastructure.api.grpc.generated.HotelManagement.internal_static_hotelmanagement_BookingResponse_descriptor;
    }

    @java.lang.Override
    public hotelmanagementsystem.infrastructure.api.grpc.generated.BookingResponse getDefaultInstanceForType() {
      return hotelmanagementsystem.infrastructure.api.grpc.generated.BookingResponse.getDefaultInstance();
    }

    @java.lang.Override
    public hotelmanagementsystem.infrastructure.api.grpc.generated.BookingResponse build() {
      hotelmanagementsystem.infrastructure.api.grpc.generated.BookingResponse result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public hotelmanagementsystem.infrastructure.api.grpc.generated.BookingResponse buildPartial() {
      hotelmanagementsystem.infrastructure.api.grpc.generated.BookingResponse result = new hotelmanagementsystem.infrastructure.api.grpc.generated.BookingResponse(this);
      if (bitField0_ != 0) { buildPartial0(result); }
      onBuilt();
      return result;
    }

    private void buildPartial0(hotelmanagementsystem.infrastructure.api.grpc.generated.BookingResponse result) {
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) != 0)) {
        result.booking_ = bookingBuilder_ == null
            ? booking_
            : bookingBuilder_.build();
        to_bitField0_ |= 0x00000001;
      }
      result.bitField0_ |= to_bitField0_;
    }

    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof hotelmanagementsystem.infrastructure.api.grpc.generated.BookingResponse) {
        return mergeFrom((hotelmanagementsystem.infrastructure.api.grpc.generated.BookingResponse)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(hotelmanagementsystem.infrastructure.api.grpc.generated.BookingResponse other) {
      if (other == hotelmanagementsystem.infrastructure.api.grpc.generated.BookingResponse.getDefaultInstance()) return this;
      if (other.hasBooking()) {
        mergeBooking(other.getBooking());
      }
      this.mergeUnknownFields(other.getUnknownFields());
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      if (extensionRegistry == null) {
        throw new java.lang.NullPointerException();
      }
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            case 10: {
              input.readMessage(
                  getBookingFieldBuilder().getBuilder(),
                  extensionRegistry);
              bitField0_ |= 0x00000001;
              break;
            } // case 10
            default: {
              if (!super.parseUnknownField(input, extensionRegistry, tag)) {
                done = true; // was an endgroup tag
              }
              break;
            } // default:
          } // switch (tag)
        } // while (!done)
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.unwrapIOException();
      } finally {
        onChanged();
      } // finally
      return this;
    }
    private int bitField0_;

    private hotelmanagementsystem.infrastructure.api.grpc.generated.Booking booking_;
    private com.google.protobuf.SingleFieldBuilder<
        hotelmanagementsystem.infrastructure.api.grpc.generated.Booking, hotelmanagementsystem.infrastructure.api.grpc.generated.Booking.Builder, hotelmanagementsystem.infrastructure.api.grpc.generated.BookingOrBuilder> bookingBuilder_;
    /**
     * <code>.hotelmanagement.Booking booking = 1;</code>
     * @return Whether the booking field is set.
     */
    public boolean hasBooking() {
      return ((bitField0_ & 0x00000001) != 0);
    }
    /**
     * <code>.hotelmanagement.Booking booking = 1;</code>
     * @return The booking.
     */
    public hotelmanagementsystem.infrastructure.api.grpc.generated.Booking getBooking() {
      if (bookingBuilder_ == null) {
        return booking_ == null ? hotelmanagementsystem.infrastructure.api.grpc.generated.Booking.getDefaultInstance() : booking_;
      } else {
        return bookingBuilder_.getMessage();
      }
    }
    /**
     * <code>.hotelmanagement.Booking booking = 1;</code>
     */
    public Builder setBooking(hotelmanagementsystem.infrastructure.api.grpc.generated.Booking value) {
      if (bookingBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        booking_ = value;
      } else {
        bookingBuilder_.setMessage(value);
      }
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }
    /**
     * <code>.hotelmanagement.Booking booking = 1;</code>
     */
    public Builder setBooking(
        hotelmanagementsystem.infrastructure.api.grpc.generated.Booking.Builder builderForValue) {
      if (bookingBuilder_ == null) {
        booking_ = builderForValue.build();
      } else {
        bookingBuilder_.setMessage(builderForValue.build());
      }
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }
    /**
     * <code>.hotelmanagement.Booking booking = 1;</code>
     */
    public Builder mergeBooking(hotelmanagementsystem.infrastructure.api.grpc.generated.Booking value) {
      if (bookingBuilder_ == null) {
        if (((bitField0_ & 0x00000001) != 0) &&
          booking_ != null &&
          booking_ != hotelmanagementsystem.infrastructure.api.grpc.generated.Booking.getDefaultInstance()) {
          getBookingBuilder().mergeFrom(value);
        } else {
          booking_ = value;
        }
      } else {
        bookingBuilder_.mergeFrom(value);
      }
      if (booking_ != null) {
        bitField0_ |= 0x00000001;
        onChanged();
      }
      return this;
    }
    /**
     * <code>.hotelmanagement.Booking booking = 1;</code>
     */
    public Builder clearBooking() {
      bitField0_ = (bitField0_ & ~0x00000001);
      booking_ = null;
      if (bookingBuilder_ != null) {
        bookingBuilder_.dispose();
        bookingBuilder_ = null;
      }
      onChanged();
      return this;
    }
    /**
     * <code>.hotelmanagement.Booking booking = 1;</code>
     */
    public hotelmanagementsystem.infrastructure.api.grpc.generated.Booking.Builder getBookingBuilder() {
      bitField0_ |= 0x00000001;
      onChanged();
      return getBookingFieldBuilder().getBuilder();
    }
    /**
     * <code>.hotelmanagement.Booking booking = 1;</code>
     */
    public hotelmanagementsystem.infrastructure.api.grpc.generated.BookingOrBuilder getBookingOrBuilder() {
      if (bookingBuilder_ != null) {
        return bookingBuilder_.getMessageOrBuilder();
      } else {
        return booking_ == null ?
            hotelmanagementsystem.infrastructure.api.grpc.generated.Booking.getDefaultInstance() : booking_;
      }
    }
    /**
     * <code>.hotelmanagement.Booking booking = 1;</code>
     */
    private com.google.protobuf.SingleFieldBuilder<
        hotelmanagementsystem.infrastructure.api.grpc.generated.Booking, hotelmanagementsystem.infrastructure.api.grpc.generated.Booking.Builder, hotelmanagementsystem.infrastructure.api.grpc.generated.BookingOrBuilder> 
        getBookingFieldBuilder() {
      if (bookingBuilder_ == null) {
        bookingBuilder_ = new com.google.protobuf.SingleFieldBuilder<
            hotelmanagementsystem.infrastructure.api.grpc.generated.Booking, hotelmanagementsystem.infrastructure.api.grpc.generated.Booking.Builder, hotelmanagementsystem.infrastructure.api.grpc.generated.BookingOrBuilder>(
                getBooking(),
                getParentForChildren(),
                isClean());
        booking_ = null;
      }
      return bookingBuilder_;
    }

    // @@protoc_insertion_point(builder_scope:hotelmanagement.BookingResponse)
  }

  // @@protoc_insertion_point(class_scope:hotelmanagement.BookingResponse)
  private static final hotelmanagementsystem.infrastructure.api.grpc.generated.BookingResponse DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new hotelmanagementsystem.infrastructure.api.grpc.generated.BookingResponse();
  }

  public static hotelmanagementsystem.infrastructure.api.grpc.generated.BookingResponse getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<BookingResponse>
      PARSER = new com.google.protobuf.AbstractParser<BookingResponse>() {
    @java.lang.Override
    public BookingResponse parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      Builder builder = newBuilder();
      try {
        builder.mergeFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(builder.buildPartial());
      } catch (com.google.protobuf.UninitializedMessageException e) {
        throw e.asInvalidProtocolBufferException().setUnfinishedMessage(builder.buildPartial());
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(e)
            .setUnfinishedMessage(builder.buildPartial());
      }
      return builder.buildPartial();
    }
  };

  public static com.google.protobuf.Parser<BookingResponse> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<BookingResponse> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public hotelmanagementsystem.infrastructure.api.grpc.generated.BookingResponse getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

