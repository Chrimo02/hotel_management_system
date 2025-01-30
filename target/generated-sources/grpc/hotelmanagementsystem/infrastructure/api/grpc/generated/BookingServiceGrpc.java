package hotelmanagementsystem.infrastructure.api.grpc.generated;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Booking Service
 * </pre>
 */
@io.quarkus.Generated(value = "by gRPC proto compiler (version 1.64.0)", comments = "Source: api.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class BookingServiceGrpc {

    private BookingServiceGrpc() {
    }

    public static final java.lang.String SERVICE_NAME = "hotelmanagement.BookingService";

    // Static method descriptors that strictly reflect the proto.
    private static volatile io.grpc.MethodDescriptor<hotelmanagementsystem.infrastructure.api.grpc.generated.CreateBookingRequest, hotelmanagementsystem.infrastructure.api.grpc.generated.BookingResponse> getCreateBookingMethod;

    @io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/' + "CreateBooking", requestType = hotelmanagementsystem.infrastructure.api.grpc.generated.CreateBookingRequest.class, responseType = hotelmanagementsystem.infrastructure.api.grpc.generated.BookingResponse.class, methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<hotelmanagementsystem.infrastructure.api.grpc.generated.CreateBookingRequest, hotelmanagementsystem.infrastructure.api.grpc.generated.BookingResponse> getCreateBookingMethod() {
        io.grpc.MethodDescriptor<hotelmanagementsystem.infrastructure.api.grpc.generated.CreateBookingRequest, hotelmanagementsystem.infrastructure.api.grpc.generated.BookingResponse> getCreateBookingMethod;
        if ((getCreateBookingMethod = BookingServiceGrpc.getCreateBookingMethod) == null) {
            synchronized (BookingServiceGrpc.class) {
                if ((getCreateBookingMethod = BookingServiceGrpc.getCreateBookingMethod) == null) {
                    BookingServiceGrpc.getCreateBookingMethod = getCreateBookingMethod = io.grpc.MethodDescriptor.<hotelmanagementsystem.infrastructure.api.grpc.generated.CreateBookingRequest, hotelmanagementsystem.infrastructure.api.grpc.generated.BookingResponse>newBuilder().setType(io.grpc.MethodDescriptor.MethodType.UNARY).setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreateBooking")).setSampledToLocalTracing(true).setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(hotelmanagementsystem.infrastructure.api.grpc.generated.CreateBookingRequest.getDefaultInstance())).setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(hotelmanagementsystem.infrastructure.api.grpc.generated.BookingResponse.getDefaultInstance())).setSchemaDescriptor(new BookingServiceMethodDescriptorSupplier("CreateBooking")).build();
                }
            }
        }
        return getCreateBookingMethod;
    }

    private static volatile io.grpc.MethodDescriptor<hotelmanagementsystem.infrastructure.api.grpc.generated.GetBookingByIdRequest, hotelmanagementsystem.infrastructure.api.grpc.generated.BookingResponse> getGetBookingByIdMethod;

    @io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/' + "GetBookingById", requestType = hotelmanagementsystem.infrastructure.api.grpc.generated.GetBookingByIdRequest.class, responseType = hotelmanagementsystem.infrastructure.api.grpc.generated.BookingResponse.class, methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<hotelmanagementsystem.infrastructure.api.grpc.generated.GetBookingByIdRequest, hotelmanagementsystem.infrastructure.api.grpc.generated.BookingResponse> getGetBookingByIdMethod() {
        io.grpc.MethodDescriptor<hotelmanagementsystem.infrastructure.api.grpc.generated.GetBookingByIdRequest, hotelmanagementsystem.infrastructure.api.grpc.generated.BookingResponse> getGetBookingByIdMethod;
        if ((getGetBookingByIdMethod = BookingServiceGrpc.getGetBookingByIdMethod) == null) {
            synchronized (BookingServiceGrpc.class) {
                if ((getGetBookingByIdMethod = BookingServiceGrpc.getGetBookingByIdMethod) == null) {
                    BookingServiceGrpc.getGetBookingByIdMethod = getGetBookingByIdMethod = io.grpc.MethodDescriptor.<hotelmanagementsystem.infrastructure.api.grpc.generated.GetBookingByIdRequest, hotelmanagementsystem.infrastructure.api.grpc.generated.BookingResponse>newBuilder().setType(io.grpc.MethodDescriptor.MethodType.UNARY).setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetBookingById")).setSampledToLocalTracing(true).setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(hotelmanagementsystem.infrastructure.api.grpc.generated.GetBookingByIdRequest.getDefaultInstance())).setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(hotelmanagementsystem.infrastructure.api.grpc.generated.BookingResponse.getDefaultInstance())).setSchemaDescriptor(new BookingServiceMethodDescriptorSupplier("GetBookingById")).build();
                }
            }
        }
        return getGetBookingByIdMethod;
    }

    private static volatile io.grpc.MethodDescriptor<hotelmanagementsystem.infrastructure.api.grpc.generated.CancelBookingRequest, hotelmanagementsystem.infrastructure.api.grpc.generated.Empty> getCancelBookingMethod;

    @io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/' + "CancelBooking", requestType = hotelmanagementsystem.infrastructure.api.grpc.generated.CancelBookingRequest.class, responseType = hotelmanagementsystem.infrastructure.api.grpc.generated.Empty.class, methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<hotelmanagementsystem.infrastructure.api.grpc.generated.CancelBookingRequest, hotelmanagementsystem.infrastructure.api.grpc.generated.Empty> getCancelBookingMethod() {
        io.grpc.MethodDescriptor<hotelmanagementsystem.infrastructure.api.grpc.generated.CancelBookingRequest, hotelmanagementsystem.infrastructure.api.grpc.generated.Empty> getCancelBookingMethod;
        if ((getCancelBookingMethod = BookingServiceGrpc.getCancelBookingMethod) == null) {
            synchronized (BookingServiceGrpc.class) {
                if ((getCancelBookingMethod = BookingServiceGrpc.getCancelBookingMethod) == null) {
                    BookingServiceGrpc.getCancelBookingMethod = getCancelBookingMethod = io.grpc.MethodDescriptor.<hotelmanagementsystem.infrastructure.api.grpc.generated.CancelBookingRequest, hotelmanagementsystem.infrastructure.api.grpc.generated.Empty>newBuilder().setType(io.grpc.MethodDescriptor.MethodType.UNARY).setFullMethodName(generateFullMethodName(SERVICE_NAME, "CancelBooking")).setSampledToLocalTracing(true).setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(hotelmanagementsystem.infrastructure.api.grpc.generated.CancelBookingRequest.getDefaultInstance())).setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(hotelmanagementsystem.infrastructure.api.grpc.generated.Empty.getDefaultInstance())).setSchemaDescriptor(new BookingServiceMethodDescriptorSupplier("CancelBooking")).build();
                }
            }
        }
        return getCancelBookingMethod;
    }

    private static volatile io.grpc.MethodDescriptor<hotelmanagementsystem.infrastructure.api.grpc.generated.CheckInGuestRequest, hotelmanagementsystem.infrastructure.api.grpc.generated.Empty> getCheckInGuestMethod;

    @io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/' + "CheckInGuest", requestType = hotelmanagementsystem.infrastructure.api.grpc.generated.CheckInGuestRequest.class, responseType = hotelmanagementsystem.infrastructure.api.grpc.generated.Empty.class, methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<hotelmanagementsystem.infrastructure.api.grpc.generated.CheckInGuestRequest, hotelmanagementsystem.infrastructure.api.grpc.generated.Empty> getCheckInGuestMethod() {
        io.grpc.MethodDescriptor<hotelmanagementsystem.infrastructure.api.grpc.generated.CheckInGuestRequest, hotelmanagementsystem.infrastructure.api.grpc.generated.Empty> getCheckInGuestMethod;
        if ((getCheckInGuestMethod = BookingServiceGrpc.getCheckInGuestMethod) == null) {
            synchronized (BookingServiceGrpc.class) {
                if ((getCheckInGuestMethod = BookingServiceGrpc.getCheckInGuestMethod) == null) {
                    BookingServiceGrpc.getCheckInGuestMethod = getCheckInGuestMethod = io.grpc.MethodDescriptor.<hotelmanagementsystem.infrastructure.api.grpc.generated.CheckInGuestRequest, hotelmanagementsystem.infrastructure.api.grpc.generated.Empty>newBuilder().setType(io.grpc.MethodDescriptor.MethodType.UNARY).setFullMethodName(generateFullMethodName(SERVICE_NAME, "CheckInGuest")).setSampledToLocalTracing(true).setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(hotelmanagementsystem.infrastructure.api.grpc.generated.CheckInGuestRequest.getDefaultInstance())).setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(hotelmanagementsystem.infrastructure.api.grpc.generated.Empty.getDefaultInstance())).setSchemaDescriptor(new BookingServiceMethodDescriptorSupplier("CheckInGuest")).build();
                }
            }
        }
        return getCheckInGuestMethod;
    }

    private static volatile io.grpc.MethodDescriptor<hotelmanagementsystem.infrastructure.api.grpc.generated.CheckOutGuestRequest, hotelmanagementsystem.infrastructure.api.grpc.generated.Empty> getCheckOutGuestMethod;

    @io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/' + "CheckOutGuest", requestType = hotelmanagementsystem.infrastructure.api.grpc.generated.CheckOutGuestRequest.class, responseType = hotelmanagementsystem.infrastructure.api.grpc.generated.Empty.class, methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<hotelmanagementsystem.infrastructure.api.grpc.generated.CheckOutGuestRequest, hotelmanagementsystem.infrastructure.api.grpc.generated.Empty> getCheckOutGuestMethod() {
        io.grpc.MethodDescriptor<hotelmanagementsystem.infrastructure.api.grpc.generated.CheckOutGuestRequest, hotelmanagementsystem.infrastructure.api.grpc.generated.Empty> getCheckOutGuestMethod;
        if ((getCheckOutGuestMethod = BookingServiceGrpc.getCheckOutGuestMethod) == null) {
            synchronized (BookingServiceGrpc.class) {
                if ((getCheckOutGuestMethod = BookingServiceGrpc.getCheckOutGuestMethod) == null) {
                    BookingServiceGrpc.getCheckOutGuestMethod = getCheckOutGuestMethod = io.grpc.MethodDescriptor.<hotelmanagementsystem.infrastructure.api.grpc.generated.CheckOutGuestRequest, hotelmanagementsystem.infrastructure.api.grpc.generated.Empty>newBuilder().setType(io.grpc.MethodDescriptor.MethodType.UNARY).setFullMethodName(generateFullMethodName(SERVICE_NAME, "CheckOutGuest")).setSampledToLocalTracing(true).setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(hotelmanagementsystem.infrastructure.api.grpc.generated.CheckOutGuestRequest.getDefaultInstance())).setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(hotelmanagementsystem.infrastructure.api.grpc.generated.Empty.getDefaultInstance())).setSchemaDescriptor(new BookingServiceMethodDescriptorSupplier("CheckOutGuest")).build();
                }
            }
        }
        return getCheckOutGuestMethod;
    }

    /**
     * Creates a new async stub that supports all call types for the service
     */
    public static BookingServiceStub newStub(io.grpc.Channel channel) {
        io.grpc.stub.AbstractStub.StubFactory<BookingServiceStub> factory = new io.grpc.stub.AbstractStub.StubFactory<BookingServiceStub>() {

            @java.lang.Override
            public BookingServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
                return new BookingServiceStub(channel, callOptions);
            }
        };
        return BookingServiceStub.newStub(factory, channel);
    }

    /**
     * Creates a new blocking-style stub that supports unary and streaming output calls on the service
     */
    public static BookingServiceBlockingStub newBlockingStub(io.grpc.Channel channel) {
        io.grpc.stub.AbstractStub.StubFactory<BookingServiceBlockingStub> factory = new io.grpc.stub.AbstractStub.StubFactory<BookingServiceBlockingStub>() {

            @java.lang.Override
            public BookingServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
                return new BookingServiceBlockingStub(channel, callOptions);
            }
        };
        return BookingServiceBlockingStub.newStub(factory, channel);
    }

    /**
     * Creates a new ListenableFuture-style stub that supports unary calls on the service
     */
    public static BookingServiceFutureStub newFutureStub(io.grpc.Channel channel) {
        io.grpc.stub.AbstractStub.StubFactory<BookingServiceFutureStub> factory = new io.grpc.stub.AbstractStub.StubFactory<BookingServiceFutureStub>() {

            @java.lang.Override
            public BookingServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
                return new BookingServiceFutureStub(channel, callOptions);
            }
        };
        return BookingServiceFutureStub.newStub(factory, channel);
    }

    /**
     * <pre>
     * Booking Service
     * </pre>
     */
    public interface AsyncService {

        /**
         */
        default void createBooking(hotelmanagementsystem.infrastructure.api.grpc.generated.CreateBookingRequest request, io.grpc.stub.StreamObserver<hotelmanagementsystem.infrastructure.api.grpc.generated.BookingResponse> responseObserver) {
            io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCreateBookingMethod(), responseObserver);
        }

        /**
         */
        default void getBookingById(hotelmanagementsystem.infrastructure.api.grpc.generated.GetBookingByIdRequest request, io.grpc.stub.StreamObserver<hotelmanagementsystem.infrastructure.api.grpc.generated.BookingResponse> responseObserver) {
            io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetBookingByIdMethod(), responseObserver);
        }

        /**
         */
        default void cancelBooking(hotelmanagementsystem.infrastructure.api.grpc.generated.CancelBookingRequest request, io.grpc.stub.StreamObserver<hotelmanagementsystem.infrastructure.api.grpc.generated.Empty> responseObserver) {
            io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCancelBookingMethod(), responseObserver);
        }

        /**
         */
        default void checkInGuest(hotelmanagementsystem.infrastructure.api.grpc.generated.CheckInGuestRequest request, io.grpc.stub.StreamObserver<hotelmanagementsystem.infrastructure.api.grpc.generated.Empty> responseObserver) {
            io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCheckInGuestMethod(), responseObserver);
        }

        /**
         */
        default void checkOutGuest(hotelmanagementsystem.infrastructure.api.grpc.generated.CheckOutGuestRequest request, io.grpc.stub.StreamObserver<hotelmanagementsystem.infrastructure.api.grpc.generated.Empty> responseObserver) {
            io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCheckOutGuestMethod(), responseObserver);
        }
    }

    /**
     * Base class for the server implementation of the service BookingService.
     * <pre>
     * Booking Service
     * </pre>
     */
    public static abstract class BookingServiceImplBase implements io.grpc.BindableService, AsyncService {

        @java.lang.Override
        public io.grpc.ServerServiceDefinition bindService() {
            return BookingServiceGrpc.bindService(this);
        }
    }

    /**
     * A stub to allow clients to do asynchronous rpc calls to service BookingService.
     * <pre>
     * Booking Service
     * </pre>
     */
    public static class BookingServiceStub extends io.grpc.stub.AbstractAsyncStub<BookingServiceStub> {

        private BookingServiceStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            super(channel, callOptions);
        }

        @java.lang.Override
        protected BookingServiceStub build(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            return new BookingServiceStub(channel, callOptions);
        }

        /**
         */
        public void createBooking(hotelmanagementsystem.infrastructure.api.grpc.generated.CreateBookingRequest request, io.grpc.stub.StreamObserver<hotelmanagementsystem.infrastructure.api.grpc.generated.BookingResponse> responseObserver) {
            io.grpc.stub.ClientCalls.asyncUnaryCall(getChannel().newCall(getCreateBookingMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         */
        public void getBookingById(hotelmanagementsystem.infrastructure.api.grpc.generated.GetBookingByIdRequest request, io.grpc.stub.StreamObserver<hotelmanagementsystem.infrastructure.api.grpc.generated.BookingResponse> responseObserver) {
            io.grpc.stub.ClientCalls.asyncUnaryCall(getChannel().newCall(getGetBookingByIdMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         */
        public void cancelBooking(hotelmanagementsystem.infrastructure.api.grpc.generated.CancelBookingRequest request, io.grpc.stub.StreamObserver<hotelmanagementsystem.infrastructure.api.grpc.generated.Empty> responseObserver) {
            io.grpc.stub.ClientCalls.asyncUnaryCall(getChannel().newCall(getCancelBookingMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         */
        public void checkInGuest(hotelmanagementsystem.infrastructure.api.grpc.generated.CheckInGuestRequest request, io.grpc.stub.StreamObserver<hotelmanagementsystem.infrastructure.api.grpc.generated.Empty> responseObserver) {
            io.grpc.stub.ClientCalls.asyncUnaryCall(getChannel().newCall(getCheckInGuestMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         */
        public void checkOutGuest(hotelmanagementsystem.infrastructure.api.grpc.generated.CheckOutGuestRequest request, io.grpc.stub.StreamObserver<hotelmanagementsystem.infrastructure.api.grpc.generated.Empty> responseObserver) {
            io.grpc.stub.ClientCalls.asyncUnaryCall(getChannel().newCall(getCheckOutGuestMethod(), getCallOptions()), request, responseObserver);
        }
    }

    /**
     * A stub to allow clients to do synchronous rpc calls to service BookingService.
     * <pre>
     * Booking Service
     * </pre>
     */
    public static class BookingServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<BookingServiceBlockingStub> {

        private BookingServiceBlockingStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            super(channel, callOptions);
        }

        @java.lang.Override
        protected BookingServiceBlockingStub build(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            return new BookingServiceBlockingStub(channel, callOptions);
        }

        /**
         */
        public hotelmanagementsystem.infrastructure.api.grpc.generated.BookingResponse createBooking(hotelmanagementsystem.infrastructure.api.grpc.generated.CreateBookingRequest request) {
            return io.grpc.stub.ClientCalls.blockingUnaryCall(getChannel(), getCreateBookingMethod(), getCallOptions(), request);
        }

        /**
         */
        public hotelmanagementsystem.infrastructure.api.grpc.generated.BookingResponse getBookingById(hotelmanagementsystem.infrastructure.api.grpc.generated.GetBookingByIdRequest request) {
            return io.grpc.stub.ClientCalls.blockingUnaryCall(getChannel(), getGetBookingByIdMethod(), getCallOptions(), request);
        }

        /**
         */
        public hotelmanagementsystem.infrastructure.api.grpc.generated.Empty cancelBooking(hotelmanagementsystem.infrastructure.api.grpc.generated.CancelBookingRequest request) {
            return io.grpc.stub.ClientCalls.blockingUnaryCall(getChannel(), getCancelBookingMethod(), getCallOptions(), request);
        }

        /**
         */
        public hotelmanagementsystem.infrastructure.api.grpc.generated.Empty checkInGuest(hotelmanagementsystem.infrastructure.api.grpc.generated.CheckInGuestRequest request) {
            return io.grpc.stub.ClientCalls.blockingUnaryCall(getChannel(), getCheckInGuestMethod(), getCallOptions(), request);
        }

        /**
         */
        public hotelmanagementsystem.infrastructure.api.grpc.generated.Empty checkOutGuest(hotelmanagementsystem.infrastructure.api.grpc.generated.CheckOutGuestRequest request) {
            return io.grpc.stub.ClientCalls.blockingUnaryCall(getChannel(), getCheckOutGuestMethod(), getCallOptions(), request);
        }
    }

    /**
     * A stub to allow clients to do ListenableFuture-style rpc calls to service BookingService.
     * <pre>
     * Booking Service
     * </pre>
     */
    public static class BookingServiceFutureStub extends io.grpc.stub.AbstractFutureStub<BookingServiceFutureStub> {

        private BookingServiceFutureStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            super(channel, callOptions);
        }

        @java.lang.Override
        protected BookingServiceFutureStub build(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            return new BookingServiceFutureStub(channel, callOptions);
        }

        /**
         */
        public com.google.common.util.concurrent.ListenableFuture<hotelmanagementsystem.infrastructure.api.grpc.generated.BookingResponse> createBooking(hotelmanagementsystem.infrastructure.api.grpc.generated.CreateBookingRequest request) {
            return io.grpc.stub.ClientCalls.futureUnaryCall(getChannel().newCall(getCreateBookingMethod(), getCallOptions()), request);
        }

        /**
         */
        public com.google.common.util.concurrent.ListenableFuture<hotelmanagementsystem.infrastructure.api.grpc.generated.BookingResponse> getBookingById(hotelmanagementsystem.infrastructure.api.grpc.generated.GetBookingByIdRequest request) {
            return io.grpc.stub.ClientCalls.futureUnaryCall(getChannel().newCall(getGetBookingByIdMethod(), getCallOptions()), request);
        }

        /**
         */
        public com.google.common.util.concurrent.ListenableFuture<hotelmanagementsystem.infrastructure.api.grpc.generated.Empty> cancelBooking(hotelmanagementsystem.infrastructure.api.grpc.generated.CancelBookingRequest request) {
            return io.grpc.stub.ClientCalls.futureUnaryCall(getChannel().newCall(getCancelBookingMethod(), getCallOptions()), request);
        }

        /**
         */
        public com.google.common.util.concurrent.ListenableFuture<hotelmanagementsystem.infrastructure.api.grpc.generated.Empty> checkInGuest(hotelmanagementsystem.infrastructure.api.grpc.generated.CheckInGuestRequest request) {
            return io.grpc.stub.ClientCalls.futureUnaryCall(getChannel().newCall(getCheckInGuestMethod(), getCallOptions()), request);
        }

        /**
         */
        public com.google.common.util.concurrent.ListenableFuture<hotelmanagementsystem.infrastructure.api.grpc.generated.Empty> checkOutGuest(hotelmanagementsystem.infrastructure.api.grpc.generated.CheckOutGuestRequest request) {
            return io.grpc.stub.ClientCalls.futureUnaryCall(getChannel().newCall(getCheckOutGuestMethod(), getCallOptions()), request);
        }
    }

    private static final int METHODID_CREATE_BOOKING = 0;

    private static final int METHODID_GET_BOOKING_BY_ID = 1;

    private static final int METHODID_CANCEL_BOOKING = 2;

    private static final int METHODID_CHECK_IN_GUEST = 3;

    private static final int METHODID_CHECK_OUT_GUEST = 4;

    private static final class MethodHandlers<Req, Resp> implements io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>, io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>, io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>, io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {

        private final AsyncService serviceImpl;

        private final int methodId;

        MethodHandlers(AsyncService serviceImpl, int methodId) {
            this.serviceImpl = serviceImpl;
            this.methodId = methodId;
        }

        @java.lang.Override
        @java.lang.SuppressWarnings("unchecked")
        public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
            switch(methodId) {
                case METHODID_CREATE_BOOKING:
                    serviceImpl.createBooking((hotelmanagementsystem.infrastructure.api.grpc.generated.CreateBookingRequest) request, (io.grpc.stub.StreamObserver<hotelmanagementsystem.infrastructure.api.grpc.generated.BookingResponse>) responseObserver);
                    break;
                case METHODID_GET_BOOKING_BY_ID:
                    serviceImpl.getBookingById((hotelmanagementsystem.infrastructure.api.grpc.generated.GetBookingByIdRequest) request, (io.grpc.stub.StreamObserver<hotelmanagementsystem.infrastructure.api.grpc.generated.BookingResponse>) responseObserver);
                    break;
                case METHODID_CANCEL_BOOKING:
                    serviceImpl.cancelBooking((hotelmanagementsystem.infrastructure.api.grpc.generated.CancelBookingRequest) request, (io.grpc.stub.StreamObserver<hotelmanagementsystem.infrastructure.api.grpc.generated.Empty>) responseObserver);
                    break;
                case METHODID_CHECK_IN_GUEST:
                    serviceImpl.checkInGuest((hotelmanagementsystem.infrastructure.api.grpc.generated.CheckInGuestRequest) request, (io.grpc.stub.StreamObserver<hotelmanagementsystem.infrastructure.api.grpc.generated.Empty>) responseObserver);
                    break;
                case METHODID_CHECK_OUT_GUEST:
                    serviceImpl.checkOutGuest((hotelmanagementsystem.infrastructure.api.grpc.generated.CheckOutGuestRequest) request, (io.grpc.stub.StreamObserver<hotelmanagementsystem.infrastructure.api.grpc.generated.Empty>) responseObserver);
                    break;
                default:
                    throw new AssertionError();
            }
        }

        @java.lang.Override
        @java.lang.SuppressWarnings("unchecked")
        public io.grpc.stub.StreamObserver<Req> invoke(io.grpc.stub.StreamObserver<Resp> responseObserver) {
            switch(methodId) {
                default:
                    throw new AssertionError();
            }
        }
    }

    public static io.grpc.ServerServiceDefinition bindService(AsyncService service) {
        return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor()).addMethod(getCreateBookingMethod(), io.grpc.stub.ServerCalls.asyncUnaryCall(new MethodHandlers<hotelmanagementsystem.infrastructure.api.grpc.generated.CreateBookingRequest, hotelmanagementsystem.infrastructure.api.grpc.generated.BookingResponse>(service, METHODID_CREATE_BOOKING))).addMethod(getGetBookingByIdMethod(), io.grpc.stub.ServerCalls.asyncUnaryCall(new MethodHandlers<hotelmanagementsystem.infrastructure.api.grpc.generated.GetBookingByIdRequest, hotelmanagementsystem.infrastructure.api.grpc.generated.BookingResponse>(service, METHODID_GET_BOOKING_BY_ID))).addMethod(getCancelBookingMethod(), io.grpc.stub.ServerCalls.asyncUnaryCall(new MethodHandlers<hotelmanagementsystem.infrastructure.api.grpc.generated.CancelBookingRequest, hotelmanagementsystem.infrastructure.api.grpc.generated.Empty>(service, METHODID_CANCEL_BOOKING))).addMethod(getCheckInGuestMethod(), io.grpc.stub.ServerCalls.asyncUnaryCall(new MethodHandlers<hotelmanagementsystem.infrastructure.api.grpc.generated.CheckInGuestRequest, hotelmanagementsystem.infrastructure.api.grpc.generated.Empty>(service, METHODID_CHECK_IN_GUEST))).addMethod(getCheckOutGuestMethod(), io.grpc.stub.ServerCalls.asyncUnaryCall(new MethodHandlers<hotelmanagementsystem.infrastructure.api.grpc.generated.CheckOutGuestRequest, hotelmanagementsystem.infrastructure.api.grpc.generated.Empty>(service, METHODID_CHECK_OUT_GUEST))).build();
    }

    private static abstract class BookingServiceBaseDescriptorSupplier implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {

        BookingServiceBaseDescriptorSupplier() {
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
            return hotelmanagementsystem.infrastructure.api.grpc.generated.HotelManagement.getDescriptor();
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
            return getFileDescriptor().findServiceByName("BookingService");
        }
    }

    private static final class BookingServiceFileDescriptorSupplier extends BookingServiceBaseDescriptorSupplier {

        BookingServiceFileDescriptorSupplier() {
        }
    }

    private static final class BookingServiceMethodDescriptorSupplier extends BookingServiceBaseDescriptorSupplier implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {

        private final java.lang.String methodName;

        BookingServiceMethodDescriptorSupplier(java.lang.String methodName) {
            this.methodName = methodName;
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
            return getServiceDescriptor().findMethodByName(methodName);
        }
    }

    private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

    public static io.grpc.ServiceDescriptor getServiceDescriptor() {
        io.grpc.ServiceDescriptor result = serviceDescriptor;
        if (result == null) {
            synchronized (BookingServiceGrpc.class) {
                result = serviceDescriptor;
                if (result == null) {
                    serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME).setSchemaDescriptor(new BookingServiceFileDescriptorSupplier()).addMethod(getCreateBookingMethod()).addMethod(getGetBookingByIdMethod()).addMethod(getCancelBookingMethod()).addMethod(getCheckInGuestMethod()).addMethod(getCheckOutGuestMethod()).build();
                }
            }
        }
        return result;
    }
}
