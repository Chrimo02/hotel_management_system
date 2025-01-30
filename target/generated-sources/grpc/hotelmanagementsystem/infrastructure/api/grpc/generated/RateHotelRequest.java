// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: api.proto
// Protobuf Java Version: 3.25.0
package hotelmanagementsystem.infrastructure.api.grpc.generated;

/**
 * Protobuf type {@code hotelmanagement.RateHotelRequest}
 */
public final class RateHotelRequest extends com.google.protobuf.GeneratedMessageV3 implements // @@protoc_insertion_point(message_implements:hotelmanagement.RateHotelRequest)
RateHotelRequestOrBuilder {

    private static final long serialVersionUID = 0L;

    // Use RateHotelRequest.newBuilder() to construct.
    private RateHotelRequest(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
        super(builder);
    }

    private RateHotelRequest() {
        comment_ = "";
    }

    @java.lang.Override
    @SuppressWarnings({ "unused" })
    protected java.lang.Object newInstance(UnusedPrivateParameter unused) {
        return new RateHotelRequest();
    }

    public static final com.google.protobuf.Descriptors.Descriptor getDescriptor() {
        return hotelmanagementsystem.infrastructure.api.grpc.generated.HotelManagement.internal_static_hotelmanagement_RateHotelRequest_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return hotelmanagementsystem.infrastructure.api.grpc.generated.HotelManagement.internal_static_hotelmanagement_RateHotelRequest_fieldAccessorTable.ensureFieldAccessorsInitialized(hotelmanagementsystem.infrastructure.api.grpc.generated.RateHotelRequest.class, hotelmanagementsystem.infrastructure.api.grpc.generated.RateHotelRequest.Builder.class);
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

    public static final int GUESTID_FIELD_NUMBER = 2;

    private long guestId_ = 0L;

    /**
     * <code>int64 guestId = 2;</code>
     * @return The guestId.
     */
    @java.lang.Override
    public long getGuestId() {
        return guestId_;
    }

    public static final int RATING_FIELD_NUMBER = 3;

    private int rating_ = 0;

    /**
     * <code>int32 rating = 3;</code>
     * @return The rating.
     */
    @java.lang.Override
    public int getRating() {
        return rating_;
    }

    public static final int COMMENT_FIELD_NUMBER = 4;

    @SuppressWarnings("serial")
    private volatile java.lang.Object comment_ = "";

    /**
     * <code>string comment = 4;</code>
     * @return The comment.
     */
    @java.lang.Override
    public java.lang.String getComment() {
        java.lang.Object ref = comment_;
        if (ref instanceof java.lang.String) {
            return (java.lang.String) ref;
        } else {
            com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
            java.lang.String s = bs.toStringUtf8();
            comment_ = s;
            return s;
        }
    }

    /**
     * <code>string comment = 4;</code>
     * @return The bytes for comment.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getCommentBytes() {
        java.lang.Object ref = comment_;
        if (ref instanceof java.lang.String) {
            com.google.protobuf.ByteString b = com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
            comment_ = b;
            return b;
        } else {
            return (com.google.protobuf.ByteString) ref;
        }
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
        if (hotelId_ != 0L) {
            output.writeInt64(1, hotelId_);
        }
        if (guestId_ != 0L) {
            output.writeInt64(2, guestId_);
        }
        if (rating_ != 0) {
            output.writeInt32(3, rating_);
        }
        if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(comment_)) {
            com.google.protobuf.GeneratedMessageV3.writeString(output, 4, comment_);
        }
        getUnknownFields().writeTo(output);
    }

    @java.lang.Override
    public int getSerializedSize() {
        int size = memoizedSize;
        if (size != -1)
            return size;
        size = 0;
        if (hotelId_ != 0L) {
            size += com.google.protobuf.CodedOutputStream.computeInt64Size(1, hotelId_);
        }
        if (guestId_ != 0L) {
            size += com.google.protobuf.CodedOutputStream.computeInt64Size(2, guestId_);
        }
        if (rating_ != 0) {
            size += com.google.protobuf.CodedOutputStream.computeInt32Size(3, rating_);
        }
        if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(comment_)) {
            size += com.google.protobuf.GeneratedMessageV3.computeStringSize(4, comment_);
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
        if (!(obj instanceof hotelmanagementsystem.infrastructure.api.grpc.generated.RateHotelRequest)) {
            return super.equals(obj);
        }
        hotelmanagementsystem.infrastructure.api.grpc.generated.RateHotelRequest other = (hotelmanagementsystem.infrastructure.api.grpc.generated.RateHotelRequest) obj;
        if (getHotelId() != other.getHotelId())
            return false;
        if (getGuestId() != other.getGuestId())
            return false;
        if (getRating() != other.getRating())
            return false;
        if (!getComment().equals(other.getComment()))
            return false;
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
        hash = (37 * hash) + HOTELID_FIELD_NUMBER;
        hash = (53 * hash) + com.google.protobuf.Internal.hashLong(getHotelId());
        hash = (37 * hash) + GUESTID_FIELD_NUMBER;
        hash = (53 * hash) + com.google.protobuf.Internal.hashLong(getGuestId());
        hash = (37 * hash) + RATING_FIELD_NUMBER;
        hash = (53 * hash) + getRating();
        hash = (37 * hash) + COMMENT_FIELD_NUMBER;
        hash = (53 * hash) + getComment().hashCode();
        hash = (29 * hash) + getUnknownFields().hashCode();
        memoizedHashCode = hash;
        return hash;
    }

    public static hotelmanagementsystem.infrastructure.api.grpc.generated.RateHotelRequest parseFrom(java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static hotelmanagementsystem.infrastructure.api.grpc.generated.RateHotelRequest parseFrom(java.nio.ByteBuffer data, com.google.protobuf.ExtensionRegistryLite extensionRegistry) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static hotelmanagementsystem.infrastructure.api.grpc.generated.RateHotelRequest parseFrom(com.google.protobuf.ByteString data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static hotelmanagementsystem.infrastructure.api.grpc.generated.RateHotelRequest parseFrom(com.google.protobuf.ByteString data, com.google.protobuf.ExtensionRegistryLite extensionRegistry) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static hotelmanagementsystem.infrastructure.api.grpc.generated.RateHotelRequest parseFrom(byte[] data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static hotelmanagementsystem.infrastructure.api.grpc.generated.RateHotelRequest parseFrom(byte[] data, com.google.protobuf.ExtensionRegistryLite extensionRegistry) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static hotelmanagementsystem.infrastructure.api.grpc.generated.RateHotelRequest parseFrom(java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseWithIOException(PARSER, input);
    }

    public static hotelmanagementsystem.infrastructure.api.grpc.generated.RateHotelRequest parseFrom(java.io.InputStream input, com.google.protobuf.ExtensionRegistryLite extensionRegistry) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static hotelmanagementsystem.infrastructure.api.grpc.generated.RateHotelRequest parseDelimitedFrom(java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
    }

    public static hotelmanagementsystem.infrastructure.api.grpc.generated.RateHotelRequest parseDelimitedFrom(java.io.InputStream input, com.google.protobuf.ExtensionRegistryLite extensionRegistry) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static hotelmanagementsystem.infrastructure.api.grpc.generated.RateHotelRequest parseFrom(com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseWithIOException(PARSER, input);
    }

    public static hotelmanagementsystem.infrastructure.api.grpc.generated.RateHotelRequest parseFrom(com.google.protobuf.CodedInputStream input, com.google.protobuf.ExtensionRegistryLite extensionRegistry) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }

    @java.lang.Override
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(hotelmanagementsystem.infrastructure.api.grpc.generated.RateHotelRequest prototype) {
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
     * Protobuf type {@code hotelmanagement.RateHotelRequest}
     */
    public static final class Builder extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements // @@protoc_insertion_point(builder_implements:hotelmanagement.RateHotelRequest)
    hotelmanagementsystem.infrastructure.api.grpc.generated.RateHotelRequestOrBuilder {

        public static final com.google.protobuf.Descriptors.Descriptor getDescriptor() {
            return hotelmanagementsystem.infrastructure.api.grpc.generated.HotelManagement.internal_static_hotelmanagement_RateHotelRequest_descriptor;
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return hotelmanagementsystem.infrastructure.api.grpc.generated.HotelManagement.internal_static_hotelmanagement_RateHotelRequest_fieldAccessorTable.ensureFieldAccessorsInitialized(hotelmanagementsystem.infrastructure.api.grpc.generated.RateHotelRequest.class, hotelmanagementsystem.infrastructure.api.grpc.generated.RateHotelRequest.Builder.class);
        }

        // Construct using hotelmanagementsystem.infrastructure.api.grpc.generated.RateHotelRequest.newBuilder()
        private Builder() {
        }

        private Builder(com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
            super(parent);
        }

        @java.lang.Override
        public Builder clear() {
            super.clear();
            bitField0_ = 0;
            hotelId_ = 0L;
            guestId_ = 0L;
            rating_ = 0;
            comment_ = "";
            return this;
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
            return hotelmanagementsystem.infrastructure.api.grpc.generated.HotelManagement.internal_static_hotelmanagement_RateHotelRequest_descriptor;
        }

        @java.lang.Override
        public hotelmanagementsystem.infrastructure.api.grpc.generated.RateHotelRequest getDefaultInstanceForType() {
            return hotelmanagementsystem.infrastructure.api.grpc.generated.RateHotelRequest.getDefaultInstance();
        }

        @java.lang.Override
        public hotelmanagementsystem.infrastructure.api.grpc.generated.RateHotelRequest build() {
            hotelmanagementsystem.infrastructure.api.grpc.generated.RateHotelRequest result = buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        @java.lang.Override
        public hotelmanagementsystem.infrastructure.api.grpc.generated.RateHotelRequest buildPartial() {
            hotelmanagementsystem.infrastructure.api.grpc.generated.RateHotelRequest result = new hotelmanagementsystem.infrastructure.api.grpc.generated.RateHotelRequest(this);
            if (bitField0_ != 0) {
                buildPartial0(result);
            }
            onBuilt();
            return result;
        }

        private void buildPartial0(hotelmanagementsystem.infrastructure.api.grpc.generated.RateHotelRequest result) {
            int from_bitField0_ = bitField0_;
            if (((from_bitField0_ & 0x00000001) != 0)) {
                result.hotelId_ = hotelId_;
            }
            if (((from_bitField0_ & 0x00000002) != 0)) {
                result.guestId_ = guestId_;
            }
            if (((from_bitField0_ & 0x00000004) != 0)) {
                result.rating_ = rating_;
            }
            if (((from_bitField0_ & 0x00000008) != 0)) {
                result.comment_ = comment_;
            }
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
            if (other instanceof hotelmanagementsystem.infrastructure.api.grpc.generated.RateHotelRequest) {
                return mergeFrom((hotelmanagementsystem.infrastructure.api.grpc.generated.RateHotelRequest) other);
            } else {
                super.mergeFrom(other);
                return this;
            }
        }

        public Builder mergeFrom(hotelmanagementsystem.infrastructure.api.grpc.generated.RateHotelRequest other) {
            if (other == hotelmanagementsystem.infrastructure.api.grpc.generated.RateHotelRequest.getDefaultInstance())
                return this;
            if (other.getHotelId() != 0L) {
                setHotelId(other.getHotelId());
            }
            if (other.getGuestId() != 0L) {
                setGuestId(other.getGuestId());
            }
            if (other.getRating() != 0) {
                setRating(other.getRating());
            }
            if (!other.getComment().isEmpty()) {
                comment_ = other.comment_;
                bitField0_ |= 0x00000008;
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
                        case 8:
                            {
                                hotelId_ = input.readInt64();
                                bitField0_ |= 0x00000001;
                                break;
                            }
                        // case 8
                        case 16:
                            {
                                guestId_ = input.readInt64();
                                bitField0_ |= 0x00000002;
                                break;
                            }
                        // case 16
                        case 24:
                            {
                                rating_ = input.readInt32();
                                bitField0_ |= 0x00000004;
                                break;
                            }
                        // case 24
                        case 34:
                            {
                                comment_ = input.readStringRequireUtf8();
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

        private long hotelId_;

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

        private long guestId_;

        /**
         * <code>int64 guestId = 2;</code>
         * @return The guestId.
         */
        @java.lang.Override
        public long getGuestId() {
            return guestId_;
        }

        /**
         * <code>int64 guestId = 2;</code>
         * @param value The guestId to set.
         * @return This builder for chaining.
         */
        public Builder setGuestId(long value) {
            guestId_ = value;
            bitField0_ |= 0x00000002;
            onChanged();
            return this;
        }

        /**
         * <code>int64 guestId = 2;</code>
         * @return This builder for chaining.
         */
        public Builder clearGuestId() {
            bitField0_ = (bitField0_ & ~0x00000002);
            guestId_ = 0L;
            onChanged();
            return this;
        }

        private int rating_;

        /**
         * <code>int32 rating = 3;</code>
         * @return The rating.
         */
        @java.lang.Override
        public int getRating() {
            return rating_;
        }

        /**
         * <code>int32 rating = 3;</code>
         * @param value The rating to set.
         * @return This builder for chaining.
         */
        public Builder setRating(int value) {
            rating_ = value;
            bitField0_ |= 0x00000004;
            onChanged();
            return this;
        }

        /**
         * <code>int32 rating = 3;</code>
         * @return This builder for chaining.
         */
        public Builder clearRating() {
            bitField0_ = (bitField0_ & ~0x00000004);
            rating_ = 0;
            onChanged();
            return this;
        }

        private java.lang.Object comment_ = "";

        /**
         * <code>string comment = 4;</code>
         * @return The comment.
         */
        public java.lang.String getComment() {
            java.lang.Object ref = comment_;
            if (!(ref instanceof java.lang.String)) {
                com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
                java.lang.String s = bs.toStringUtf8();
                comment_ = s;
                return s;
            } else {
                return (java.lang.String) ref;
            }
        }

        /**
         * <code>string comment = 4;</code>
         * @return The bytes for comment.
         */
        public com.google.protobuf.ByteString getCommentBytes() {
            java.lang.Object ref = comment_;
            if (ref instanceof String) {
                com.google.protobuf.ByteString b = com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
                comment_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        /**
         * <code>string comment = 4;</code>
         * @param value The comment to set.
         * @return This builder for chaining.
         */
        public Builder setComment(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            comment_ = value;
            bitField0_ |= 0x00000008;
            onChanged();
            return this;
        }

        /**
         * <code>string comment = 4;</code>
         * @return This builder for chaining.
         */
        public Builder clearComment() {
            comment_ = getDefaultInstance().getComment();
            bitField0_ = (bitField0_ & ~0x00000008);
            onChanged();
            return this;
        }

        /**
         * <code>string comment = 4;</code>
         * @param value The bytes for comment to set.
         * @return This builder for chaining.
         */
        public Builder setCommentBytes(com.google.protobuf.ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            checkByteStringIsUtf8(value);
            comment_ = value;
            bitField0_ |= 0x00000008;
            onChanged();
            return this;
        }

        @java.lang.Override
        public final Builder setUnknownFields(final com.google.protobuf.UnknownFieldSet unknownFields) {
            return super.setUnknownFields(unknownFields);
        }

        @java.lang.Override
        public final Builder mergeUnknownFields(final com.google.protobuf.UnknownFieldSet unknownFields) {
            return super.mergeUnknownFields(unknownFields);
        }
        // @@protoc_insertion_point(builder_scope:hotelmanagement.RateHotelRequest)
    }

    // @@protoc_insertion_point(class_scope:hotelmanagement.RateHotelRequest)
    private static final hotelmanagementsystem.infrastructure.api.grpc.generated.RateHotelRequest DEFAULT_INSTANCE;

    static {
        DEFAULT_INSTANCE = new hotelmanagementsystem.infrastructure.api.grpc.generated.RateHotelRequest();
    }

    public static hotelmanagementsystem.infrastructure.api.grpc.generated.RateHotelRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<RateHotelRequest> PARSER = new com.google.protobuf.AbstractParser<RateHotelRequest>() {

        @java.lang.Override
        public RateHotelRequest parsePartialFrom(com.google.protobuf.CodedInputStream input, com.google.protobuf.ExtensionRegistryLite extensionRegistry) throws com.google.protobuf.InvalidProtocolBufferException {
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

    public static com.google.protobuf.Parser<RateHotelRequest> parser() {
        return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<RateHotelRequest> getParserForType() {
        return PARSER;
    }

    @java.lang.Override
    public hotelmanagementsystem.infrastructure.api.grpc.generated.RateHotelRequest getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
