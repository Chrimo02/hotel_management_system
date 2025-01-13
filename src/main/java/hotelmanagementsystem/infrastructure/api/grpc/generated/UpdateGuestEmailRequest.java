// Generated by the protocol buffer compiler.  DO NOT EDIT!
// NO CHECKED-IN PROTOBUF GENCODE
// source: api.proto
// Protobuf Java Version: 4.27.4

package hotelmanagementsystem.infrastructure.api.grpc.generated;

/**
 * Protobuf type {@code hotelmanagement.UpdateGuestEmailRequest}
 */
public final class UpdateGuestEmailRequest extends
    com.google.protobuf.GeneratedMessage implements
    // @@protoc_insertion_point(message_implements:hotelmanagement.UpdateGuestEmailRequest)
    UpdateGuestEmailRequestOrBuilder {
private static final long serialVersionUID = 0L;
  static {
    com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
      com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
      /* major= */ 4,
      /* minor= */ 27,
      /* patch= */ 4,
      /* suffix= */ "",
      UpdateGuestEmailRequest.class.getName());
  }
  // Use UpdateGuestEmailRequest.newBuilder() to construct.
  private UpdateGuestEmailRequest(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
    super(builder);
  }
  private UpdateGuestEmailRequest() {
    newEmail_ = "";
  }

  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return hotelmanagementsystem.infrastructure.api.grpc.generated.HotelManagement.internal_static_hotelmanagement_UpdateGuestEmailRequest_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return hotelmanagementsystem.infrastructure.api.grpc.generated.HotelManagement.internal_static_hotelmanagement_UpdateGuestEmailRequest_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateGuestEmailRequest.class, hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateGuestEmailRequest.Builder.class);
  }

  public static final int GUESTID_FIELD_NUMBER = 1;
  private long guestId_ = 0L;
  /**
   * <code>int64 guestId = 1;</code>
   * @return The guestId.
   */
  @java.lang.Override
  public long getGuestId() {
    return guestId_;
  }

  public static final int NEWEMAIL_FIELD_NUMBER = 2;
  @SuppressWarnings("serial")
  private volatile java.lang.Object newEmail_ = "";
  /**
   * <code>string newEmail = 2;</code>
   * @return The newEmail.
   */
  @java.lang.Override
  public java.lang.String getNewEmail() {
    java.lang.Object ref = newEmail_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      newEmail_ = s;
      return s;
    }
  }
  /**
   * <code>string newEmail = 2;</code>
   * @return The bytes for newEmail.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString
      getNewEmailBytes() {
    java.lang.Object ref = newEmail_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      newEmail_ = b;
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
    if (guestId_ != 0L) {
      output.writeInt64(1, guestId_);
    }
    if (!com.google.protobuf.GeneratedMessage.isStringEmpty(newEmail_)) {
      com.google.protobuf.GeneratedMessage.writeString(output, 2, newEmail_);
    }
    getUnknownFields().writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (guestId_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(1, guestId_);
    }
    if (!com.google.protobuf.GeneratedMessage.isStringEmpty(newEmail_)) {
      size += com.google.protobuf.GeneratedMessage.computeStringSize(2, newEmail_);
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
    if (!(obj instanceof hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateGuestEmailRequest)) {
      return super.equals(obj);
    }
    hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateGuestEmailRequest other = (hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateGuestEmailRequest) obj;

    if (getGuestId()
        != other.getGuestId()) return false;
    if (!getNewEmail()
        .equals(other.getNewEmail())) return false;
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
    hash = (37 * hash) + GUESTID_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getGuestId());
    hash = (37 * hash) + NEWEMAIL_FIELD_NUMBER;
    hash = (53 * hash) + getNewEmail().hashCode();
    hash = (29 * hash) + getUnknownFields().hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateGuestEmailRequest parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateGuestEmailRequest parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateGuestEmailRequest parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateGuestEmailRequest parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateGuestEmailRequest parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateGuestEmailRequest parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateGuestEmailRequest parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseWithIOException(PARSER, input);
  }
  public static hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateGuestEmailRequest parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public static hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateGuestEmailRequest parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseDelimitedWithIOException(PARSER, input);
  }

  public static hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateGuestEmailRequest parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateGuestEmailRequest parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseWithIOException(PARSER, input);
  }
  public static hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateGuestEmailRequest parseFrom(
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
  public static Builder newBuilder(hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateGuestEmailRequest prototype) {
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
   * Protobuf type {@code hotelmanagement.UpdateGuestEmailRequest}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessage.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:hotelmanagement.UpdateGuestEmailRequest)
      hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateGuestEmailRequestOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return hotelmanagementsystem.infrastructure.api.grpc.generated.HotelManagement.internal_static_hotelmanagement_UpdateGuestEmailRequest_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return hotelmanagementsystem.infrastructure.api.grpc.generated.HotelManagement.internal_static_hotelmanagement_UpdateGuestEmailRequest_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateGuestEmailRequest.class, hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateGuestEmailRequest.Builder.class);
    }

    // Construct using hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateGuestEmailRequest.newBuilder()
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
      guestId_ = 0L;
      newEmail_ = "";
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return hotelmanagementsystem.infrastructure.api.grpc.generated.HotelManagement.internal_static_hotelmanagement_UpdateGuestEmailRequest_descriptor;
    }

    @java.lang.Override
    public hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateGuestEmailRequest getDefaultInstanceForType() {
      return hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateGuestEmailRequest.getDefaultInstance();
    }

    @java.lang.Override
    public hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateGuestEmailRequest build() {
      hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateGuestEmailRequest result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateGuestEmailRequest buildPartial() {
      hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateGuestEmailRequest result = new hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateGuestEmailRequest(this);
      if (bitField0_ != 0) { buildPartial0(result); }
      onBuilt();
      return result;
    }

    private void buildPartial0(hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateGuestEmailRequest result) {
      int from_bitField0_ = bitField0_;
      if (((from_bitField0_ & 0x00000001) != 0)) {
        result.guestId_ = guestId_;
      }
      if (((from_bitField0_ & 0x00000002) != 0)) {
        result.newEmail_ = newEmail_;
      }
    }

    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateGuestEmailRequest) {
        return mergeFrom((hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateGuestEmailRequest)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateGuestEmailRequest other) {
      if (other == hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateGuestEmailRequest.getDefaultInstance()) return this;
      if (other.getGuestId() != 0L) {
        setGuestId(other.getGuestId());
      }
      if (!other.getNewEmail().isEmpty()) {
        newEmail_ = other.newEmail_;
        bitField0_ |= 0x00000002;
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
              guestId_ = input.readInt64();
              bitField0_ |= 0x00000001;
              break;
            } // case 8
            case 18: {
              newEmail_ = input.readStringRequireUtf8();
              bitField0_ |= 0x00000002;
              break;
            } // case 18
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

    private long guestId_ ;
    /**
     * <code>int64 guestId = 1;</code>
     * @return The guestId.
     */
    @java.lang.Override
    public long getGuestId() {
      return guestId_;
    }
    /**
     * <code>int64 guestId = 1;</code>
     * @param value The guestId to set.
     * @return This builder for chaining.
     */
    public Builder setGuestId(long value) {

      guestId_ = value;
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }
    /**
     * <code>int64 guestId = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearGuestId() {
      bitField0_ = (bitField0_ & ~0x00000001);
      guestId_ = 0L;
      onChanged();
      return this;
    }

    private java.lang.Object newEmail_ = "";
    /**
     * <code>string newEmail = 2;</code>
     * @return The newEmail.
     */
    public java.lang.String getNewEmail() {
      java.lang.Object ref = newEmail_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        newEmail_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string newEmail = 2;</code>
     * @return The bytes for newEmail.
     */
    public com.google.protobuf.ByteString
        getNewEmailBytes() {
      java.lang.Object ref = newEmail_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        newEmail_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string newEmail = 2;</code>
     * @param value The newEmail to set.
     * @return This builder for chaining.
     */
    public Builder setNewEmail(
        java.lang.String value) {
      if (value == null) { throw new NullPointerException(); }
      newEmail_ = value;
      bitField0_ |= 0x00000002;
      onChanged();
      return this;
    }
    /**
     * <code>string newEmail = 2;</code>
     * @return This builder for chaining.
     */
    public Builder clearNewEmail() {
      newEmail_ = getDefaultInstance().getNewEmail();
      bitField0_ = (bitField0_ & ~0x00000002);
      onChanged();
      return this;
    }
    /**
     * <code>string newEmail = 2;</code>
     * @param value The bytes for newEmail to set.
     * @return This builder for chaining.
     */
    public Builder setNewEmailBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) { throw new NullPointerException(); }
      checkByteStringIsUtf8(value);
      newEmail_ = value;
      bitField0_ |= 0x00000002;
      onChanged();
      return this;
    }

    // @@protoc_insertion_point(builder_scope:hotelmanagement.UpdateGuestEmailRequest)
  }

  // @@protoc_insertion_point(class_scope:hotelmanagement.UpdateGuestEmailRequest)
  private static final hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateGuestEmailRequest DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateGuestEmailRequest();
  }

  public static hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateGuestEmailRequest getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<UpdateGuestEmailRequest>
      PARSER = new com.google.protobuf.AbstractParser<UpdateGuestEmailRequest>() {
    @java.lang.Override
    public UpdateGuestEmailRequest parsePartialFrom(
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

  public static com.google.protobuf.Parser<UpdateGuestEmailRequest> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<UpdateGuestEmailRequest> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateGuestEmailRequest getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

