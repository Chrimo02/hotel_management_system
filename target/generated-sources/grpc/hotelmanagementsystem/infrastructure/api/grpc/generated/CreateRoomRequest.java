// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: api.proto
// Protobuf Java Version: 3.25.0
package hotelmanagementsystem.infrastructure.api.grpc.generated;

/**
 * Protobuf type {@code hotelmanagement.CreateRoomRequest}
 */
public final class CreateRoomRequest extends com.google.protobuf.GeneratedMessageV3 implements // @@protoc_insertion_point(message_implements:hotelmanagement.CreateRoomRequest)
CreateRoomRequestOrBuilder {

    private static final long serialVersionUID = 0L;

    // Use CreateRoomRequest.newBuilder() to construct.
    private CreateRoomRequest(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
        super(builder);
    }

    private CreateRoomRequest() {
        type_ = "";
    }

    @java.lang.Override
    @SuppressWarnings({ "unused" })
    protected java.lang.Object newInstance(UnusedPrivateParameter unused) {
        return new CreateRoomRequest();
    }

    public static final com.google.protobuf.Descriptors.Descriptor getDescriptor() {
        return hotelmanagementsystem.infrastructure.api.grpc.generated.HotelManagement.internal_static_hotelmanagement_CreateRoomRequest_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return hotelmanagementsystem.infrastructure.api.grpc.generated.HotelManagement.internal_static_hotelmanagement_CreateRoomRequest_fieldAccessorTable.ensureFieldAccessorsInitialized(hotelmanagementsystem.infrastructure.api.grpc.generated.CreateRoomRequest.class, hotelmanagementsystem.infrastructure.api.grpc.generated.CreateRoomRequest.Builder.class);
    }

    private int bitField0_;

    public static final int PRICEPERNIGHT_FIELD_NUMBER = 1;

    private double pricePerNight_ = 0D;

    /**
     * <code>double pricePerNight = 1;</code>
     * @return The pricePerNight.
     */
    @java.lang.Override
    public double getPricePerNight() {
        return pricePerNight_;
    }

    public static final int TYPE_FIELD_NUMBER = 2;

    @SuppressWarnings("serial")
    private volatile java.lang.Object type_ = "";

    /**
     * <code>string type = 2;</code>
     * @return The type.
     */
    @java.lang.Override
    public java.lang.String getType() {
        java.lang.Object ref = type_;
        if (ref instanceof java.lang.String) {
            return (java.lang.String) ref;
        } else {
            com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
            java.lang.String s = bs.toStringUtf8();
            type_ = s;
            return s;
        }
    }

    /**
     * <code>string type = 2;</code>
     * @return The bytes for type.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getTypeBytes() {
        java.lang.Object ref = type_;
        if (ref instanceof java.lang.String) {
            com.google.protobuf.ByteString b = com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
            type_ = b;
            return b;
        } else {
            return (com.google.protobuf.ByteString) ref;
        }
    }

    public static final int HOTELID_FIELD_NUMBER = 3;

    private long hotelId_ = 0L;

    /**
     * <code>int64 hotelId = 3;</code>
     * @return The hotelId.
     */
    @java.lang.Override
    public long getHotelId() {
        return hotelId_;
    }

    public static final int ROOMIDENTIFIER_FIELD_NUMBER = 4;

    private hotelmanagementsystem.infrastructure.api.grpc.generated.RoomIdentifier roomIdentifier_;

    /**
     * <code>.hotelmanagement.RoomIdentifier roomIdentifier = 4;</code>
     * @return Whether the roomIdentifier field is set.
     */
    @java.lang.Override
    public boolean hasRoomIdentifier() {
        return ((bitField0_ & 0x00000001) != 0);
    }

    /**
     * <code>.hotelmanagement.RoomIdentifier roomIdentifier = 4;</code>
     * @return The roomIdentifier.
     */
    @java.lang.Override
    public hotelmanagementsystem.infrastructure.api.grpc.generated.RoomIdentifier getRoomIdentifier() {
        return roomIdentifier_ == null ? hotelmanagementsystem.infrastructure.api.grpc.generated.RoomIdentifier.getDefaultInstance() : roomIdentifier_;
    }

    /**
     * <code>.hotelmanagement.RoomIdentifier roomIdentifier = 4;</code>
     */
    @java.lang.Override
    public hotelmanagementsystem.infrastructure.api.grpc.generated.RoomIdentifierOrBuilder getRoomIdentifierOrBuilder() {
        return roomIdentifier_ == null ? hotelmanagementsystem.infrastructure.api.grpc.generated.RoomIdentifier.getDefaultInstance() : roomIdentifier_;
    }

    private byte memoizedIsInitialized = -1;

    @java.lang.Override
    public final boolean isInitialized() {
        byte isInitialized = memoizedIsInitialized;
        if (isInitialized == 1)
            return true;
        if (isInitialized == 0)
            return false;
        memoizedIsInitialized = 1;
        return true;
    }

    @java.lang.Override
    public void writeTo(com.google.protobuf.CodedOutputStream output) throws java.io.IOException {
        if (java.lang.Double.doubleToRawLongBits(pricePerNight_) != 0) {
            output.writeDouble(1, pricePerNight_);
        }
        if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(type_)) {
            com.google.protobuf.GeneratedMessageV3.writeString(output, 2, type_);
        }
        if (hotelId_ != 0L) {
            output.writeInt64(3, hotelId_);
        }
        if (((bitField0_ & 0x00000001) != 0)) {
            output.writeMessage(4, getRoomIdentifier());
        }
        getUnknownFields().writeTo(output);
    }

    @java.lang.Override
    public int getSerializedSize() {
        int size = memoizedSize;
        if (size != -1)
            return size;
        size = 0;
        if (java.lang.Double.doubleToRawLongBits(pricePerNight_) != 0) {
            size += com.google.protobuf.CodedOutputStream.computeDoubleSize(1, pricePerNight_);
        }
        if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(type_)) {
            size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, type_);
        }
        if (hotelId_ != 0L) {
            size += com.google.protobuf.CodedOutputStream.computeInt64Size(3, hotelId_);
        }
        if (((bitField0_ & 0x00000001) != 0)) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(4, getRoomIdentifier());
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
        if (!(obj instanceof hotelmanagementsystem.infrastructure.api.grpc.generated.CreateRoomRequest)) {
            return super.equals(obj);
        }
        hotelmanagementsystem.infrastructure.api.grpc.generated.CreateRoomRequest other = (hotelmanagementsystem.infrastructure.api.grpc.generated.CreateRoomRequest) obj;
        if (java.lang.Double.doubleToLongBits(getPricePerNight()) != java.lang.Double.doubleToLongBits(other.getPricePerNight()))
            return false;
        if (!getType().equals(other.getType()))
            return false;
        if (getHotelId() != other.getHotelId())
            return false;
        if (hasRoomIdentifier() != other.hasRoomIdentifier())
            return false;
        if (hasRoomIdentifier()) {
            if (!getRoomIdentifier().equals(other.getRoomIdentifier()))
                return false;
        }
        if (!getUnknownFields().equals(other.getUnknownFields()))
            return false;
        return true;
    }

    @java.lang.Override
    public int hashCode() {
        if (memoizedHashCode != 0) {
            return memoizedHashCode;
        }
        int hash = 41;
        hash = (19 * hash) + getDescriptor().hashCode();
        hash = (37 * hash) + PRICEPERNIGHT_FIELD_NUMBER;
        hash = (53 * hash) + com.google.protobuf.Internal.hashLong(java.lang.Double.doubleToLongBits(getPricePerNight()));
        hash = (37 * hash) + TYPE_FIELD_NUMBER;
        hash = (53 * hash) + getType().hashCode();
        hash = (37 * hash) + HOTELID_FIELD_NUMBER;
        hash = (53 * hash) + com.google.protobuf.Internal.hashLong(getHotelId());
        if (hasRoomIdentifier()) {
            hash = (37 * hash) + ROOMIDENTIFIER_FIELD_NUMBER;
            hash = (53 * hash) + getRoomIdentifier().hashCode();
        }
        hash = (29 * hash) + getUnknownFields().hashCode();
        memoizedHashCode = hash;
        return hash;
    }

    public static hotelmanagementsystem.infrastructure.api.grpc.generated.CreateRoomRequest parseFrom(java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static hotelmanagementsystem.infrastructure.api.grpc.generated.CreateRoomRequest parseFrom(java.nio.ByteBuffer data, com.google.protobuf.ExtensionRegistryLite extensionRegistry) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static hotelmanagementsystem.infrastructure.api.grpc.generated.CreateRoomRequest parseFrom(com.google.protobuf.ByteString data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static hotelmanagementsystem.infrastructure.api.grpc.generated.CreateRoomRequest parseFrom(com.google.protobuf.ByteString data, com.google.protobuf.ExtensionRegistryLite extensionRegistry) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static hotelmanagementsystem.infrastructure.api.grpc.generated.CreateRoomRequest parseFrom(byte[] data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static hotelmanagementsystem.infrastructure.api.grpc.generated.CreateRoomRequest parseFrom(byte[] data, com.google.protobuf.ExtensionRegistryLite extensionRegistry) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static hotelmanagementsystem.infrastructure.api.grpc.generated.CreateRoomRequest parseFrom(java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseWithIOException(PARSER, input);
    }

    public static hotelmanagementsystem.infrastructure.api.grpc.generated.CreateRoomRequest parseFrom(java.io.InputStream input, com.google.protobuf.ExtensionRegistryLite extensionRegistry) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static hotelmanagementsystem.infrastructure.api.grpc.generated.CreateRoomRequest parseDelimitedFrom(java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
    }

    public static hotelmanagementsystem.infrastructure.api.grpc.generated.CreateRoomRequest parseDelimitedFrom(java.io.InputStream input, com.google.protobuf.ExtensionRegistryLite extensionRegistry) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static hotelmanagementsystem.infrastructure.api.grpc.generated.CreateRoomRequest parseFrom(com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseWithIOException(PARSER, input);
    }

    public static hotelmanagementsystem.infrastructure.api.grpc.generated.CreateRoomRequest parseFrom(com.google.protobuf.CodedInputStream input, com.google.protobuf.ExtensionRegistryLite extensionRegistry) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }

    @java.lang.Override
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(hotelmanagementsystem.infrastructure.api.grpc.generated.CreateRoomRequest prototype) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
    }

    @java.lang.Override
    public Builder toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    @java.lang.Override
    protected Builder newBuilderForType(com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
        Builder builder = new Builder(parent);
        return builder;
    }

    /**
     * Protobuf type {@code hotelmanagement.CreateRoomRequest}
     */
    public static final class Builder extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements // @@protoc_insertion_point(builder_implements:hotelmanagement.CreateRoomRequest)
    hotelmanagementsystem.infrastructure.api.grpc.generated.CreateRoomRequestOrBuilder {

        public static final com.google.protobuf.Descriptors.Descriptor getDescriptor() {
            return hotelmanagementsystem.infrastructure.api.grpc.generated.HotelManagement.internal_static_hotelmanagement_CreateRoomRequest_descriptor;
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return hotelmanagementsystem.infrastructure.api.grpc.generated.HotelManagement.internal_static_hotelmanagement_CreateRoomRequest_fieldAccessorTable.ensureFieldAccessorsInitialized(hotelmanagementsystem.infrastructure.api.grpc.generated.CreateRoomRequest.class, hotelmanagementsystem.infrastructure.api.grpc.generated.CreateRoomRequest.Builder.class);
        }

        // Construct using hotelmanagementsystem.infrastructure.api.grpc.generated.CreateRoomRequest.newBuilder()
        private Builder() {
            maybeForceBuilderInitialization();
        }

        private Builder(com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
            super(parent);
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            if (com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders) {
                getRoomIdentifierFieldBuilder();
            }
        }

        @java.lang.Override
        public Builder clear() {
            super.clear();
            bitField0_ = 0;
            pricePerNight_ = 0D;
            type_ = "";
            hotelId_ = 0L;
            roomIdentifier_ = null;
            if (roomIdentifierBuilder_ != null) {
                roomIdentifierBuilder_.dispose();
                roomIdentifierBuilder_ = null;
            }
            return this;
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
            return hotelmanagementsystem.infrastructure.api.grpc.generated.HotelManagement.internal_static_hotelmanagement_CreateRoomRequest_descriptor;
        }

        @java.lang.Override
        public hotelmanagementsystem.infrastructure.api.grpc.generated.CreateRoomRequest getDefaultInstanceForType() {
            return hotelmanagementsystem.infrastructure.api.grpc.generated.CreateRoomRequest.getDefaultInstance();
        }

        @java.lang.Override
        public hotelmanagementsystem.infrastructure.api.grpc.generated.CreateRoomRequest build() {
            hotelmanagementsystem.infrastructure.api.grpc.generated.CreateRoomRequest result = buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        @java.lang.Override
        public hotelmanagementsystem.infrastructure.api.grpc.generated.CreateRoomRequest buildPartial() {
            hotelmanagementsystem.infrastructure.api.grpc.generated.CreateRoomRequest result = new hotelmanagementsystem.infrastructure.api.grpc.generated.CreateRoomRequest(this);
            if (bitField0_ != 0) {
                buildPartial0(result);
            }
            onBuilt();
            return result;
        }

        private void buildPartial0(hotelmanagementsystem.infrastructure.api.grpc.generated.CreateRoomRequest result) {
            int from_bitField0_ = bitField0_;
            if (((from_bitField0_ & 0x00000001) != 0)) {
                result.pricePerNight_ = pricePerNight_;
            }
            if (((from_bitField0_ & 0x00000002) != 0)) {
                result.type_ = type_;
            }
            if (((from_bitField0_ & 0x00000004) != 0)) {
                result.hotelId_ = hotelId_;
            }
            int to_bitField0_ = 0;
            if (((from_bitField0_ & 0x00000008) != 0)) {
                result.roomIdentifier_ = roomIdentifierBuilder_ == null ? roomIdentifier_ : roomIdentifierBuilder_.build();
                to_bitField0_ |= 0x00000001;
            }
            result.bitField0_ |= to_bitField0_;
        }

        @java.lang.Override
        public Builder clone() {
            return super.clone();
        }

        @java.lang.Override
        public Builder setField(com.google.protobuf.Descriptors.FieldDescriptor field, java.lang.Object value) {
            return super.setField(field, value);
        }

        @java.lang.Override
        public Builder clearField(com.google.protobuf.Descriptors.FieldDescriptor field) {
            return super.clearField(field);
        }

        @java.lang.Override
        public Builder clearOneof(com.google.protobuf.Descriptors.OneofDescriptor oneof) {
            return super.clearOneof(oneof);
        }

        @java.lang.Override
        public Builder setRepeatedField(com.google.protobuf.Descriptors.FieldDescriptor field, int index, java.lang.Object value) {
            return super.setRepeatedField(field, index, value);
        }

        @java.lang.Override
        public Builder addRepeatedField(com.google.protobuf.Descriptors.FieldDescriptor field, java.lang.Object value) {
            return super.addRepeatedField(field, value);
        }

        @java.lang.Override
        public Builder mergeFrom(com.google.protobuf.Message other) {
            if (other instanceof hotelmanagementsystem.infrastructure.api.grpc.generated.CreateRoomRequest) {
                return mergeFrom((hotelmanagementsystem.infrastructure.api.grpc.generated.CreateRoomRequest) other);
            } else {
                super.mergeFrom(other);
                return this;
            }
        }

        public Builder mergeFrom(hotelmanagementsystem.infrastructure.api.grpc.generated.CreateRoomRequest other) {
            if (other == hotelmanagementsystem.infrastructure.api.grpc.generated.CreateRoomRequest.getDefaultInstance())
                return this;
            if (other.getPricePerNight() != 0D) {
                setPricePerNight(other.getPricePerNight());
            }
            if (!other.getType().isEmpty()) {
                type_ = other.type_;
                bitField0_ |= 0x00000002;
                onChanged();
            }
            if (other.getHotelId() != 0L) {
                setHotelId(other.getHotelId());
            }
            if (other.hasRoomIdentifier()) {
                mergeRoomIdentifier(other.getRoomIdentifier());
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
        public Builder mergeFrom(com.google.protobuf.CodedInputStream input, com.google.protobuf.ExtensionRegistryLite extensionRegistry) throws java.io.IOException {
            if (extensionRegistry == null) {
                throw new java.lang.NullPointerException();
            }
            try {
                boolean done = false;
                while (!done) {
                    int tag = input.readTag();
                    switch(tag) {
                        case 0:
                            done = true;
                            break;
                        case 9:
                            {
                                pricePerNight_ = input.readDouble();
                                bitField0_ |= 0x00000001;
                                break;
                            }
                        // case 9
                        case 18:
                            {
                                type_ = input.readStringRequireUtf8();
                                bitField0_ |= 0x00000002;
                                break;
                            }
                        // case 18
                        case 24:
                            {
                                hotelId_ = input.readInt64();
                                bitField0_ |= 0x00000004;
                                break;
                            }
                        // case 24
                        case 34:
                            {
                                input.readMessage(getRoomIdentifierFieldBuilder().getBuilder(), extensionRegistry);
                                bitField0_ |= 0x00000008;
                                break;
                            }
                        // case 34
                        default:
                            {
                                if (!super.parseUnknownField(input, extensionRegistry, tag)) {
                                    // was an endgroup tag
                                    done = true;
                                }
                                break;
                            }
                    }
                    // switch (tag)
                }
                // while (!done)
            } catch (com.google.protobuf.InvalidProtocolBufferException e) {
                throw e.unwrapIOException();
            } finally {
                onChanged();
            }
            // finally
            return this;
        }

        private int bitField0_;

        private double pricePerNight_;

        /**
         * <code>double pricePerNight = 1;</code>
         * @return The pricePerNight.
         */
        @java.lang.Override
        public double getPricePerNight() {
            return pricePerNight_;
        }

        /**
         * <code>double pricePerNight = 1;</code>
         * @param value The pricePerNight to set.
         * @return This builder for chaining.
         */
        public Builder setPricePerNight(double value) {
            pricePerNight_ = value;
            bitField0_ |= 0x00000001;
            onChanged();
            return this;
        }

        /**
         * <code>double pricePerNight = 1;</code>
         * @return This builder for chaining.
         */
        public Builder clearPricePerNight() {
            bitField0_ = (bitField0_ & ~0x00000001);
            pricePerNight_ = 0D;
            onChanged();
            return this;
        }

        private java.lang.Object type_ = "";

        /**
         * <code>string type = 2;</code>
         * @return The type.
         */
        public java.lang.String getType() {
            java.lang.Object ref = type_;
            if (!(ref instanceof java.lang.String)) {
                com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
                java.lang.String s = bs.toStringUtf8();
                type_ = s;
                return s;
            } else {
                return (java.lang.String) ref;
            }
        }

        /**
         * <code>string type = 2;</code>
         * @return The bytes for type.
         */
        public com.google.protobuf.ByteString getTypeBytes() {
            java.lang.Object ref = type_;
            if (ref instanceof String) {
                com.google.protobuf.ByteString b = com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
                type_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        /**
         * <code>string type = 2;</code>
         * @param value The type to set.
         * @return This builder for chaining.
         */
        public Builder setType(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            type_ = value;
            bitField0_ |= 0x00000002;
            onChanged();
            return this;
        }

        /**
         * <code>string type = 2;</code>
         * @return This builder for chaining.
         */
        public Builder clearType() {
            type_ = getDefaultInstance().getType();
            bitField0_ = (bitField0_ & ~0x00000002);
            onChanged();
            return this;
        }

        /**
         * <code>string type = 2;</code>
         * @param value The bytes for type to set.
         * @return This builder for chaining.
         */
        public Builder setTypeBytes(com.google.protobuf.ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            checkByteStringIsUtf8(value);
            type_ = value;
            bitField0_ |= 0x00000002;
            onChanged();
            return this;
        }

        private long hotelId_;

        /**
         * <code>int64 hotelId = 3;</code>
         * @return The hotelId.
         */
        @java.lang.Override
        public long getHotelId() {
            return hotelId_;
        }

        /**
         * <code>int64 hotelId = 3;</code>
         * @param value The hotelId to set.
         * @return This builder for chaining.
         */
        public Builder setHotelId(long value) {
            hotelId_ = value;
            bitField0_ |= 0x00000004;
            onChanged();
            return this;
        }

        /**
         * <code>int64 hotelId = 3;</code>
         * @return This builder for chaining.
         */
        public Builder clearHotelId() {
            bitField0_ = (bitField0_ & ~0x00000004);
            hotelId_ = 0L;
            onChanged();
            return this;
        }

        private hotelmanagementsystem.infrastructure.api.grpc.generated.RoomIdentifier roomIdentifier_;

        private com.google.protobuf.SingleFieldBuilderV3<hotelmanagementsystem.infrastructure.api.grpc.generated.RoomIdentifier, hotelmanagementsystem.infrastructure.api.grpc.generated.RoomIdentifier.Builder, hotelmanagementsystem.infrastructure.api.grpc.generated.RoomIdentifierOrBuilder> roomIdentifierBuilder_;

        /**
         * <code>.hotelmanagement.RoomIdentifier roomIdentifier = 4;</code>
         * @return Whether the roomIdentifier field is set.
         */
        public boolean hasRoomIdentifier() {
            return ((bitField0_ & 0x00000008) != 0);
        }

        /**
         * <code>.hotelmanagement.RoomIdentifier roomIdentifier = 4;</code>
         * @return The roomIdentifier.
         */
        public hotelmanagementsystem.infrastructure.api.grpc.generated.RoomIdentifier getRoomIdentifier() {
            if (roomIdentifierBuilder_ == null) {
                return roomIdentifier_ == null ? hotelmanagementsystem.infrastructure.api.grpc.generated.RoomIdentifier.getDefaultInstance() : roomIdentifier_;
            } else {
                return roomIdentifierBuilder_.getMessage();
            }
        }

        /**
         * <code>.hotelmanagement.RoomIdentifier roomIdentifier = 4;</code>
         */
        public Builder setRoomIdentifier(hotelmanagementsystem.infrastructure.api.grpc.generated.RoomIdentifier value) {
            if (roomIdentifierBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                roomIdentifier_ = value;
            } else {
                roomIdentifierBuilder_.setMessage(value);
            }
            bitField0_ |= 0x00000008;
            onChanged();
            return this;
        }

        /**
         * <code>.hotelmanagement.RoomIdentifier roomIdentifier = 4;</code>
         */
        public Builder setRoomIdentifier(hotelmanagementsystem.infrastructure.api.grpc.generated.RoomIdentifier.Builder builderForValue) {
            if (roomIdentifierBuilder_ == null) {
                roomIdentifier_ = builderForValue.build();
            } else {
                roomIdentifierBuilder_.setMessage(builderForValue.build());
            }
            bitField0_ |= 0x00000008;
            onChanged();
            return this;
        }

        /**
         * <code>.hotelmanagement.RoomIdentifier roomIdentifier = 4;</code>
         */
        public Builder mergeRoomIdentifier(hotelmanagementsystem.infrastructure.api.grpc.generated.RoomIdentifier value) {
            if (roomIdentifierBuilder_ == null) {
                if (((bitField0_ & 0x00000008) != 0) && roomIdentifier_ != null && roomIdentifier_ != hotelmanagementsystem.infrastructure.api.grpc.generated.RoomIdentifier.getDefaultInstance()) {
                    getRoomIdentifierBuilder().mergeFrom(value);
                } else {
                    roomIdentifier_ = value;
                }
            } else {
                roomIdentifierBuilder_.mergeFrom(value);
            }
            if (roomIdentifier_ != null) {
                bitField0_ |= 0x00000008;
                onChanged();
            }
            return this;
        }

        /**
         * <code>.hotelmanagement.RoomIdentifier roomIdentifier = 4;</code>
         */
        public Builder clearRoomIdentifier() {
            bitField0_ = (bitField0_ & ~0x00000008);
            roomIdentifier_ = null;
            if (roomIdentifierBuilder_ != null) {
                roomIdentifierBuilder_.dispose();
                roomIdentifierBuilder_ = null;
            }
            onChanged();
            return this;
        }

        /**
         * <code>.hotelmanagement.RoomIdentifier roomIdentifier = 4;</code>
         */
        public hotelmanagementsystem.infrastructure.api.grpc.generated.RoomIdentifier.Builder getRoomIdentifierBuilder() {
            bitField0_ |= 0x00000008;
            onChanged();
            return getRoomIdentifierFieldBuilder().getBuilder();
        }

        /**
         * <code>.hotelmanagement.RoomIdentifier roomIdentifier = 4;</code>
         */
        public hotelmanagementsystem.infrastructure.api.grpc.generated.RoomIdentifierOrBuilder getRoomIdentifierOrBuilder() {
            if (roomIdentifierBuilder_ != null) {
                return roomIdentifierBuilder_.getMessageOrBuilder();
            } else {
                return roomIdentifier_ == null ? hotelmanagementsystem.infrastructure.api.grpc.generated.RoomIdentifier.getDefaultInstance() : roomIdentifier_;
            }
        }

        /**
         * <code>.hotelmanagement.RoomIdentifier roomIdentifier = 4;</code>
         */
        private com.google.protobuf.SingleFieldBuilderV3<hotelmanagementsystem.infrastructure.api.grpc.generated.RoomIdentifier, hotelmanagementsystem.infrastructure.api.grpc.generated.RoomIdentifier.Builder, hotelmanagementsystem.infrastructure.api.grpc.generated.RoomIdentifierOrBuilder> getRoomIdentifierFieldBuilder() {
            if (roomIdentifierBuilder_ == null) {
                roomIdentifierBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<hotelmanagementsystem.infrastructure.api.grpc.generated.RoomIdentifier, hotelmanagementsystem.infrastructure.api.grpc.generated.RoomIdentifier.Builder, hotelmanagementsystem.infrastructure.api.grpc.generated.RoomIdentifierOrBuilder>(getRoomIdentifier(), getParentForChildren(), isClean());
                roomIdentifier_ = null;
            }
            return roomIdentifierBuilder_;
        }

        @java.lang.Override
        public final Builder setUnknownFields(final com.google.protobuf.UnknownFieldSet unknownFields) {
            return super.setUnknownFields(unknownFields);
        }

        @java.lang.Override
        public final Builder mergeUnknownFields(final com.google.protobuf.UnknownFieldSet unknownFields) {
            return super.mergeUnknownFields(unknownFields);
        }
        // @@protoc_insertion_point(builder_scope:hotelmanagement.CreateRoomRequest)
    }

    // @@protoc_insertion_point(class_scope:hotelmanagement.CreateRoomRequest)
    private static final hotelmanagementsystem.infrastructure.api.grpc.generated.CreateRoomRequest DEFAULT_INSTANCE;

    static {
        DEFAULT_INSTANCE = new hotelmanagementsystem.infrastructure.api.grpc.generated.CreateRoomRequest();
    }

    public static hotelmanagementsystem.infrastructure.api.grpc.generated.CreateRoomRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<CreateRoomRequest> PARSER = new com.google.protobuf.AbstractParser<CreateRoomRequest>() {

        @java.lang.Override
        public CreateRoomRequest parsePartialFrom(com.google.protobuf.CodedInputStream input, com.google.protobuf.ExtensionRegistryLite extensionRegistry) throws com.google.protobuf.InvalidProtocolBufferException {
            Builder builder = newBuilder();
            try {
                builder.mergeFrom(input, extensionRegistry);
            } catch (com.google.protobuf.InvalidProtocolBufferException e) {
                throw e.setUnfinishedMessage(builder.buildPartial());
            } catch (com.google.protobuf.UninitializedMessageException e) {
                throw e.asInvalidProtocolBufferException().setUnfinishedMessage(builder.buildPartial());
            } catch (java.io.IOException e) {
                throw new com.google.protobuf.InvalidProtocolBufferException(e).setUnfinishedMessage(builder.buildPartial());
            }
            return builder.buildPartial();
        }
    };

    public static com.google.protobuf.Parser<CreateRoomRequest> parser() {
        return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<CreateRoomRequest> getParserForType() {
        return PARSER;
    }

    @java.lang.Override
    public hotelmanagementsystem.infrastructure.api.grpc.generated.CreateRoomRequest getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
