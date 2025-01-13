// Generated by the protocol buffer compiler.  DO NOT EDIT!
// NO CHECKED-IN PROTOBUF GENCODE
// source: api.proto
// Protobuf Java Version: 4.27.4

package hotelmanagementsystem.infrastructure.api.grpc.generated;

/**
 * Protobuf type {@code hotelmanagement.ListAvailableRoomsRequest}
 */
public final class ListAvailableRoomsRequest extends
    com.google.protobuf.GeneratedMessage implements
    // @@protoc_insertion_point(message_implements:hotelmanagement.ListAvailableRoomsRequest)
    ListAvailableRoomsRequestOrBuilder {
private static final long serialVersionUID = 0L;
  static {
    com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
      com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
      /* major= */ 4,
      /* minor= */ 27,
      /* patch= */ 4,
      /* suffix= */ "",
      ListAvailableRoomsRequest.class.getName());
  }
  // Use ListAvailableRoomsRequest.newBuilder() to construct.
  private ListAvailableRoomsRequest(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
    super(builder);
  }
  private ListAvailableRoomsRequest() {
    checkInDate_ = "";
    checkOutDate_ = "";
  }

  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return hotelmanagementsystem.infrastructure.api.grpc.generated.HotelManagement.internal_static_hotelmanagement_ListAvailableRoomsRequest_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return hotelmanagementsystem.infrastructure.api.grpc.generated.HotelManagement.internal_static_hotelmanagement_ListAvailableRoomsRequest_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            hotelmanagementsystem.infrastructure.api.grpc.generated.ListAvailableRoomsRequest.class, hotelmanagementsystem.infrastructure.api.grpc.generated.ListAvailableRoomsRequest.Builder.class);
  }

  public static final int HOTELID_FIELD_NUMBER = 1;
  private long hotelId_ = 0L;
  /**
   * <code>int64 hotelId = 1;</code>
   * @return The hotelId.
   */
  @java.lang.Override
  public long getHotelId() {
    return hotelId_;
  }

  public static final int CHECKINDATE_FIELD_NUMBER = 2;
  @SuppressWarnings("serial")
  private volatile java.lang.Object checkInDate_ = "";
  /**
   * <code>string checkInDate = 2;</code>
   * @return The checkInDate.
   */
  @java.lang.Override
  public java.lang.String getCheckInDate() {
    java.lang.Object ref = checkInDate_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      checkInDate_ = s;
      return s;
    }
  }
  /**
   * <code>string checkInDate = 2;</code>
   * @return The bytes for checkInDate.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString
      getCheckInDateBytes() {
    java.lang.Object ref = checkInDate_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      checkInDate_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int CHECKOUTDATE_FIELD_NUMBER = 3;
  @SuppressWarnings("serial")
  private volatile java.lang.Object checkOutDate_ = "";
  /**
   * <code>string checkOutDate = 3;</code>
   * @return The checkOutDate.
   */
  @java.lang.Override
  public java.lang.String getCheckOutDate() {
    java.lang.Object ref = checkOutDate_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      checkOutDate_ = s;
      return s;
    }
  }
  /**
   * <code>string checkOutDate = 3;</code>
   * @return The bytes for checkOutDate.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString
      getCheckOutDateBytes() {
    java.lang.Object ref = checkOutDate_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      checkOutDate_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
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
    if (hotelId_ != 0L) {
      output.writeInt64(1, hotelId_);
    }
    if (!com.google.protobuf.GeneratedMessage.isStringEmpty(checkInDate_)) {
      com.google.protobuf.GeneratedMessage.writeString(output, 2, checkInDate_);
    }
    if (!com.google.protobuf.GeneratedMessage.isStringEmpty(checkOutDate_)) {
      com.google.protobuf.GeneratedMessage.writeString(output, 3, checkOutDate_);
    }
    getUnknownFields().writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (hotelId_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(1, hotelId_);
    }
    if (!com.google.protobuf.GeneratedMessage.isStringEmpty(checkInDate_)) {
      size += com.google.protobuf.GeneratedMessage.computeStringSize(2, checkInDate_);
    }
    if (!com.google.protobuf.GeneratedMessage.isStringEmpty(checkOutDate_)) {
      size += com.google.protobuf.GeneratedMessage.computeStringSize(3, checkOutDate_);
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
    if (!(obj instanceof hotelmanagementsystem.infrastructure.api.grpc.generated.ListAvailableRoomsRequest)) {
      return super.equals(obj);
    }
    hotelmanagementsystem.infrastructure.api.grpc.generated.ListAvailableRoomsRequest other = (hotelmanagementsystem.infrastructure.api.grpc.generated.ListAvailableRoomsRequest) obj;

    if (getHotelId()
        != other.getHotelId()) return false;
    if (!getCheckInDate()
        .equals(other.getCheckInDate())) return false;
    if (!getCheckOutDate()
        .equals(other.getCheckOutDate())) return false;
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
    hash = (37 * hash) + HOTELID_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getHotelId());
    hash = (37 * hash) + CHECKINDATE_FIELD_NUMBER;
    hash = (53 * hash) + getCheckInDate().hashCode();
    hash = (37 * hash) + CHECKOUTDATE_FIELD_NUMBER;
    hash = (53 * hash) + getCheckOutDate().hashCode();
    hash = (29 * hash) + getUnknownFields().hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static hotelmanagementsystem.infrastructure.api.grpc.generated.ListAvailableRoomsRequest parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static hotelmanagementsystem.infrastructure.api.grpc.generated.ListAvailableRoomsRequest parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static hotelmanagementsystem.infrastructure.api.grpc.generated.ListAvailableRoomsRequest parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static hotelmanagementsystem.infrastructure.api.grpc.generated.ListAvailableRoomsRequest parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static hotelmanagementsystem.infrastructure.api.grpc.generated.ListAvailableRoomsRequest parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static hotelmanagementsystem.infrastructure.api.grpc.generated.ListAvailableRoomsRequest parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static hotelmanagementsystem.infrastructure.api.grpc.generated.ListAvailableRoomsRequest parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseWithIOException(PARSER, input);
  }
  public static hotelmanagementsystem.infrastructure.api.grpc.generated.ListAvailableRoomsRequest parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public static hotelmanagementsystem.infrastructure.api.grpc.generated.ListAvailableRoomsRequest parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseDelimitedWithIOException(PARSER, input);
  }

  public static hotelmanagementsystem.infrastructure.api.grpc.generated.ListAvailableRoomsRequest parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static hotelmanagementsystem.infrastructure.api.grpc.generated.ListAvailableRoomsRequest parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseWithIOException(PARSER, input);
  }
  public static hotelmanagementsystem.infrastructure.api.grpc.generated.ListAvailableRoomsRequest parseFrom(
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
  public static Builder newBuilder(hotelmanagementsystem.infrastructure.api.grpc.generated.ListAvailableRoomsRequest prototype) {
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
   * Protobuf type {@code hotelmanagement.ListAvailableRoomsRequest}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessage.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:hotelmanagement.ListAvailableRoomsRequest)
      hotelmanagementsystem.infrastructure.api.grpc.generated.ListAvailableRoomsRequestOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return hotelmanagementsystem.infrastructure.api.grpc.generated.HotelManagement.internal_static_hotelmanagement_ListAvailableRoomsRequest_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return hotelmanagementsystem.infrastructure.api.grpc.generated.HotelManagement.internal_static_hotelmanagement_ListAvailableRoomsRequest_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              hotelmanagementsystem.infrastructure.api.grpc.generated.ListAvailableRoomsRequest.class, hotelmanagementsystem.infrastructure.api.grpc.generated.ListAvailableRoomsRequest.Builder.class);
    }

    // Construct using hotelmanagementsystem.infrastructure.api.grpc.generated.ListAvailableRoomsRequest.newBuilder()
    private Builder() {

    }

    private Builder(
        com.google.protobuf.GeneratedMessage.BuilderParent parent) {
      super(parent);

    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      bitField0_ = 0;
      hotelId_ = 0L;
      checkInDate_ = "";
      checkOutDate_ = "";
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return hotelmanagementsystem.infrastructure.api.grpc.generated.HotelManagement.internal_static_hotelmanagement_ListAvailableRoomsRequest_descriptor;
    }

    @java.lang.Override
    public hotelmanagementsystem.infrastructure.api.grpc.generated.ListAvailableRoomsRequest getDefaultInstanceForType() {
      return hotelmanagementsystem.infrastructure.api.grpc.generated.ListAvailableRoomsRequest.getDefaultInstance();
    }

    @java.lang.Override
    public hotelmanagementsystem.infrastructure.api.grpc.generated.ListAvailableRoomsRequest build() {
      hotelmanagementsystem.infrastructure.api.grpc.generated.ListAvailableRoomsRequest result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public hotelmanagementsystem.infrastructure.api.grpc.generated.ListAvailableRoomsRequest buildPartial() {
      hotelmanagementsystem.infrastructure.api.grpc.generated.ListAvailableRoomsRequest result = new hotelmanagementsystem.infrastructure.api.grpc.generated.ListAvailableRoomsRequest(this);
      if (bitField0_ != 0) { buildPartial0(result); }
      onBuilt();
      return result;
    }

    private void buildPartial0(hotelmanagementsystem.infrastructure.api.grpc.generated.ListAvailableRoomsRequest result) {
      int from_bitField0_ = bitField0_;
      if (((from_bitField0_ & 0x00000001) != 0)) {
        result.hotelId_ = hotelId_;
      }
      if (((from_bitField0_ & 0x00000002) != 0)) {
        result.checkInDate_ = checkInDate_;
      }
      if (((from_bitField0_ & 0x00000004) != 0)) {
        result.checkOutDate_ = checkOutDate_;
      }
    }

    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof hotelmanagementsystem.infrastructure.api.grpc.generated.ListAvailableRoomsRequest) {
        return mergeFrom((hotelmanagementsystem.infrastructure.api.grpc.generated.ListAvailableRoomsRequest)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(hotelmanagementsystem.infrastructure.api.grpc.generated.ListAvailableRoomsRequest other) {
      if (other == hotelmanagementsystem.infrastructure.api.grpc.generated.ListAvailableRoomsRequest.getDefaultInstance()) return this;
      if (other.getHotelId() != 0L) {
        setHotelId(other.getHotelId());
      }
      if (!other.getCheckInDate().isEmpty()) {
        checkInDate_ = other.checkInDate_;
        bitField0_ |= 0x00000002;
        onChanged();
      }
      if (!other.getCheckOutDate().isEmpty()) {
        checkOutDate_ = other.checkOutDate_;
        bitField0_ |= 0x00000004;
        onChanged();
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
            case 8: {
              hotelId_ = input.readInt64();
              bitField0_ |= 0x00000001;
              break;
            } // case 8
            case 18: {
              checkInDate_ = input.readStringRequireUtf8();
              bitField0_ |= 0x00000002;
              break;
            } // case 18
            case 26: {
              checkOutDate_ = input.readStringRequireUtf8();
              bitField0_ |= 0x00000004;
              break;
            } // case 26
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

    private long hotelId_ ;
    /**
     * <code>int64 hotelId = 1;</code>
     * @return The hotelId.
     */
    @java.lang.Override
    public long getHotelId() {
      return hotelId_;
    }
    /**
     * <code>int64 hotelId = 1;</code>
     * @param value The hotelId to set.
     * @return This builder for chaining.
     */
    public Builder setHotelId(long value) {

      hotelId_ = value;
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }
    /**
     * <code>int64 hotelId = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearHotelId() {
      bitField0_ = (bitField0_ & ~0x00000001);
      hotelId_ = 0L;
      onChanged();
      return this;
    }

    private java.lang.Object checkInDate_ = "";
    /**
     * <code>string checkInDate = 2;</code>
     * @return The checkInDate.
     */
    public java.lang.String getCheckInDate() {
      java.lang.Object ref = checkInDate_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        checkInDate_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string checkInDate = 2;</code>
     * @return The bytes for checkInDate.
     */
    public com.google.protobuf.ByteString
        getCheckInDateBytes() {
      java.lang.Object ref = checkInDate_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        checkInDate_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string checkInDate = 2;</code>
     * @param value The checkInDate to set.
     * @return This builder for chaining.
     */
    public Builder setCheckInDate(
        java.lang.String value) {
      if (value == null) { throw new NullPointerException(); }
      checkInDate_ = value;
      bitField0_ |= 0x00000002;
      onChanged();
      return this;
    }
    /**
     * <code>string checkInDate = 2;</code>
     * @return This builder for chaining.
     */
    public Builder clearCheckInDate() {
      checkInDate_ = getDefaultInstance().getCheckInDate();
      bitField0_ = (bitField0_ & ~0x00000002);
      onChanged();
      return this;
    }
    /**
     * <code>string checkInDate = 2;</code>
     * @param value The bytes for checkInDate to set.
     * @return This builder for chaining.
     */
    public Builder setCheckInDateBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) { throw new NullPointerException(); }
      checkByteStringIsUtf8(value);
      checkInDate_ = value;
      bitField0_ |= 0x00000002;
      onChanged();
      return this;
    }

    private java.lang.Object checkOutDate_ = "";
    /**
     * <code>string checkOutDate = 3;</code>
     * @return The checkOutDate.
     */
    public java.lang.String getCheckOutDate() {
      java.lang.Object ref = checkOutDate_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        checkOutDate_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string checkOutDate = 3;</code>
     * @return The bytes for checkOutDate.
     */
    public com.google.protobuf.ByteString
        getCheckOutDateBytes() {
      java.lang.Object ref = checkOutDate_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        checkOutDate_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string checkOutDate = 3;</code>
     * @param value The checkOutDate to set.
     * @return This builder for chaining.
     */
    public Builder setCheckOutDate(
        java.lang.String value) {
      if (value == null) { throw new NullPointerException(); }
      checkOutDate_ = value;
      bitField0_ |= 0x00000004;
      onChanged();
      return this;
    }
    /**
     * <code>string checkOutDate = 3;</code>
     * @return This builder for chaining.
     */
    public Builder clearCheckOutDate() {
      checkOutDate_ = getDefaultInstance().getCheckOutDate();
      bitField0_ = (bitField0_ & ~0x00000004);
      onChanged();
      return this;
    }
    /**
     * <code>string checkOutDate = 3;</code>
     * @param value The bytes for checkOutDate to set.
     * @return This builder for chaining.
     */
    public Builder setCheckOutDateBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) { throw new NullPointerException(); }
      checkByteStringIsUtf8(value);
      checkOutDate_ = value;
      bitField0_ |= 0x00000004;
      onChanged();
      return this;
    }

    // @@protoc_insertion_point(builder_scope:hotelmanagement.ListAvailableRoomsRequest)
  }

  // @@protoc_insertion_point(class_scope:hotelmanagement.ListAvailableRoomsRequest)
  private static final hotelmanagementsystem.infrastructure.api.grpc.generated.ListAvailableRoomsRequest DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new hotelmanagementsystem.infrastructure.api.grpc.generated.ListAvailableRoomsRequest();
  }

  public static hotelmanagementsystem.infrastructure.api.grpc.generated.ListAvailableRoomsRequest getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<ListAvailableRoomsRequest>
      PARSER = new com.google.protobuf.AbstractParser<ListAvailableRoomsRequest>() {
    @java.lang.Override
    public ListAvailableRoomsRequest parsePartialFrom(
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

  public static com.google.protobuf.Parser<ListAvailableRoomsRequest> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<ListAvailableRoomsRequest> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public hotelmanagementsystem.infrastructure.api.grpc.generated.ListAvailableRoomsRequest getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

