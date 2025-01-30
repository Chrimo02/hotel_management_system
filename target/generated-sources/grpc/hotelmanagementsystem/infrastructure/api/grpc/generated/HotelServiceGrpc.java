package hotelmanagementsystem.infrastructure.api.grpc.generated;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Hotel Service
 * </pre>
 */
@io.quarkus.Generated(value = "by gRPC proto compiler (version 1.64.0)", comments = "Source: api.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class HotelServiceGrpc {

    private HotelServiceGrpc() {
    }

    public static final java.lang.String SERVICE_NAME = "hotelmanagement.HotelService";

    // Static method descriptors that strictly reflect the proto.
    private static volatile io.grpc.MethodDescriptor<hotelmanagementsystem.infrastructure.api.grpc.generated.CreateHotelRequest, hotelmanagementsystem.infrastructure.api.grpc.generated.HotelResponse> getCreateHotelMethod;

    @io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/' + "CreateHotel", requestType = hotelmanagementsystem.infrastructure.api.grpc.generated.CreateHotelRequest.class, responseType = hotelmanagementsystem.infrastructure.api.grpc.generated.HotelResponse.class, methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<hotelmanagementsystem.infrastructure.api.grpc.generated.CreateHotelRequest, hotelmanagementsystem.infrastructure.api.grpc.generated.HotelResponse> getCreateHotelMethod() {
        io.grpc.MethodDescriptor<hotelmanagementsystem.infrastructure.api.grpc.generated.CreateHotelRequest, hotelmanagementsystem.infrastructure.api.grpc.generated.HotelResponse> getCreateHotelMethod;
        if ((getCreateHotelMethod = HotelServiceGrpc.getCreateHotelMethod) == null) {
            synchronized (HotelServiceGrpc.class) {
                if ((getCreateHotelMethod = HotelServiceGrpc.getCreateHotelMethod) == null) {
                    HotelServiceGrpc.getCreateHotelMethod = getCreateHotelMethod = io.grpc.MethodDescriptor.<hotelmanagementsystem.infrastructure.api.grpc.generated.CreateHotelRequest, hotelmanagementsystem.infrastructure.api.grpc.generated.HotelResponse>newBuilder().setType(io.grpc.MethodDescriptor.MethodType.UNARY).setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreateHotel")).setSampledToLocalTracing(true).setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(hotelmanagementsystem.infrastructure.api.grpc.generated.CreateHotelRequest.getDefaultInstance())).setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(hotelmanagementsystem.infrastructure.api.grpc.generated.HotelResponse.getDefaultInstance())).setSchemaDescriptor(new HotelServiceMethodDescriptorSupplier("CreateHotel")).build();
                }
            }
        }
        return getCreateHotelMethod;
    }

    private static volatile io.grpc.MethodDescriptor<hotelmanagementsystem.infrastructure.api.grpc.generated.GetHotelByIdRequest, hotelmanagementsystem.infrastructure.api.grpc.generated.HotelResponse> getGetHotelByIdMethod;

    @io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/' + "GetHotelById", requestType = hotelmanagementsystem.infrastructure.api.grpc.generated.GetHotelByIdRequest.class, responseType = hotelmanagementsystem.infrastructure.api.grpc.generated.HotelResponse.class, methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<hotelmanagementsystem.infrastructure.api.grpc.generated.GetHotelByIdRequest, hotelmanagementsystem.infrastructure.api.grpc.generated.HotelResponse> getGetHotelByIdMethod() {
        io.grpc.MethodDescriptor<hotelmanagementsystem.infrastructure.api.grpc.generated.GetHotelByIdRequest, hotelmanagementsystem.infrastructure.api.grpc.generated.HotelResponse> getGetHotelByIdMethod;
        if ((getGetHotelByIdMethod = HotelServiceGrpc.getGetHotelByIdMethod) == null) {
            synchronized (HotelServiceGrpc.class) {
                if ((getGetHotelByIdMethod = HotelServiceGrpc.getGetHotelByIdMethod) == null) {
                    HotelServiceGrpc.getGetHotelByIdMethod = getGetHotelByIdMethod = io.grpc.MethodDescriptor.<hotelmanagementsystem.infrastructure.api.grpc.generated.GetHotelByIdRequest, hotelmanagementsystem.infrastructure.api.grpc.generated.HotelResponse>newBuilder().setType(io.grpc.MethodDescriptor.MethodType.UNARY).setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetHotelById")).setSampledToLocalTracing(true).setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(hotelmanagementsystem.infrastructure.api.grpc.generated.GetHotelByIdRequest.getDefaultInstance())).setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(hotelmanagementsystem.infrastructure.api.grpc.generated.HotelResponse.getDefaultInstance())).setSchemaDescriptor(new HotelServiceMethodDescriptorSupplier("GetHotelById")).build();
                }
            }
        }
        return getGetHotelByIdMethod;
    }

    private static volatile io.grpc.MethodDescriptor<hotelmanagementsystem.infrastructure.api.grpc.generated.ListHotelsRequest, hotelmanagementsystem.infrastructure.api.grpc.generated.ListHotelsResponse> getListHotelsMethod;

    @io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/' + "ListHotels", requestType = hotelmanagementsystem.infrastructure.api.grpc.generated.ListHotelsRequest.class, responseType = hotelmanagementsystem.infrastructure.api.grpc.generated.ListHotelsResponse.class, methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<hotelmanagementsystem.infrastructure.api.grpc.generated.ListHotelsRequest, hotelmanagementsystem.infrastructure.api.grpc.generated.ListHotelsResponse> getListHotelsMethod() {
        io.grpc.MethodDescriptor<hotelmanagementsystem.infrastructure.api.grpc.generated.ListHotelsRequest, hotelmanagementsystem.infrastructure.api.grpc.generated.ListHotelsResponse> getListHotelsMethod;
        if ((getListHotelsMethod = HotelServiceGrpc.getListHotelsMethod) == null) {
            synchronized (HotelServiceGrpc.class) {
                if ((getListHotelsMethod = HotelServiceGrpc.getListHotelsMethod) == null) {
                    HotelServiceGrpc.getListHotelsMethod = getListHotelsMethod = io.grpc.MethodDescriptor.<hotelmanagementsystem.infrastructure.api.grpc.generated.ListHotelsRequest, hotelmanagementsystem.infrastructure.api.grpc.generated.ListHotelsResponse>newBuilder().setType(io.grpc.MethodDescriptor.MethodType.UNARY).setFullMethodName(generateFullMethodName(SERVICE_NAME, "ListHotels")).setSampledToLocalTracing(true).setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(hotelmanagementsystem.infrastructure.api.grpc.generated.ListHotelsRequest.getDefaultInstance())).setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(hotelmanagementsystem.infrastructure.api.grpc.generated.ListHotelsResponse.getDefaultInstance())).setSchemaDescriptor(new HotelServiceMethodDescriptorSupplier("ListHotels")).build();
                }
            }
        }
        return getListHotelsMethod;
    }

    private static volatile io.grpc.MethodDescriptor<hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateHotelRequest, hotelmanagementsystem.infrastructure.api.grpc.generated.HotelResponse> getUpdateHotelMethod;

    @io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/' + "UpdateHotel", requestType = hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateHotelRequest.class, responseType = hotelmanagementsystem.infrastructure.api.grpc.generated.HotelResponse.class, methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateHotelRequest, hotelmanagementsystem.infrastructure.api.grpc.generated.HotelResponse> getUpdateHotelMethod() {
        io.grpc.MethodDescriptor<hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateHotelRequest, hotelmanagementsystem.infrastructure.api.grpc.generated.HotelResponse> getUpdateHotelMethod;
        if ((getUpdateHotelMethod = HotelServiceGrpc.getUpdateHotelMethod) == null) {
            synchronized (HotelServiceGrpc.class) {
                if ((getUpdateHotelMethod = HotelServiceGrpc.getUpdateHotelMethod) == null) {
                    HotelServiceGrpc.getUpdateHotelMethod = getUpdateHotelMethod = io.grpc.MethodDescriptor.<hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateHotelRequest, hotelmanagementsystem.infrastructure.api.grpc.generated.HotelResponse>newBuilder().setType(io.grpc.MethodDescriptor.MethodType.UNARY).setFullMethodName(generateFullMethodName(SERVICE_NAME, "UpdateHotel")).setSampledToLocalTracing(true).setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateHotelRequest.getDefaultInstance())).setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(hotelmanagementsystem.infrastructure.api.grpc.generated.HotelResponse.getDefaultInstance())).setSchemaDescriptor(new HotelServiceMethodDescriptorSupplier("UpdateHotel")).build();
                }
            }
        }
        return getUpdateHotelMethod;
    }

    private static volatile io.grpc.MethodDescriptor<hotelmanagementsystem.infrastructure.api.grpc.generated.DeleteHotelRequest, hotelmanagementsystem.infrastructure.api.grpc.generated.Empty> getDeleteHotelMethod;

    @io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/' + "DeleteHotel", requestType = hotelmanagementsystem.infrastructure.api.grpc.generated.DeleteHotelRequest.class, responseType = hotelmanagementsystem.infrastructure.api.grpc.generated.Empty.class, methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<hotelmanagementsystem.infrastructure.api.grpc.generated.DeleteHotelRequest, hotelmanagementsystem.infrastructure.api.grpc.generated.Empty> getDeleteHotelMethod() {
        io.grpc.MethodDescriptor<hotelmanagementsystem.infrastructure.api.grpc.generated.DeleteHotelRequest, hotelmanagementsystem.infrastructure.api.grpc.generated.Empty> getDeleteHotelMethod;
        if ((getDeleteHotelMethod = HotelServiceGrpc.getDeleteHotelMethod) == null) {
            synchronized (HotelServiceGrpc.class) {
                if ((getDeleteHotelMethod = HotelServiceGrpc.getDeleteHotelMethod) == null) {
                    HotelServiceGrpc.getDeleteHotelMethod = getDeleteHotelMethod = io.grpc.MethodDescriptor.<hotelmanagementsystem.infrastructure.api.grpc.generated.DeleteHotelRequest, hotelmanagementsystem.infrastructure.api.grpc.generated.Empty>newBuilder().setType(io.grpc.MethodDescriptor.MethodType.UNARY).setFullMethodName(generateFullMethodName(SERVICE_NAME, "DeleteHotel")).setSampledToLocalTracing(true).setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(hotelmanagementsystem.infrastructure.api.grpc.generated.DeleteHotelRequest.getDefaultInstance())).setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(hotelmanagementsystem.infrastructure.api.grpc.generated.Empty.getDefaultInstance())).setSchemaDescriptor(new HotelServiceMethodDescriptorSupplier("DeleteHotel")).build();
                }
            }
        }
        return getDeleteHotelMethod;
    }

    private static volatile io.grpc.MethodDescriptor<hotelmanagementsystem.infrastructure.api.grpc.generated.RateHotelRequest, hotelmanagementsystem.infrastructure.api.grpc.generated.Empty> getRateHotelMethod;

    @io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/' + "RateHotel", requestType = hotelmanagementsystem.infrastructure.api.grpc.generated.RateHotelRequest.class, responseType = hotelmanagementsystem.infrastructure.api.grpc.generated.Empty.class, methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<hotelmanagementsystem.infrastructure.api.grpc.generated.RateHotelRequest, hotelmanagementsystem.infrastructure.api.grpc.generated.Empty> getRateHotelMethod() {
        io.grpc.MethodDescriptor<hotelmanagementsystem.infrastructure.api.grpc.generated.RateHotelRequest, hotelmanagementsystem.infrastructure.api.grpc.generated.Empty> getRateHotelMethod;
        if ((getRateHotelMethod = HotelServiceGrpc.getRateHotelMethod) == null) {
            synchronized (HotelServiceGrpc.class) {
                if ((getRateHotelMethod = HotelServiceGrpc.getRateHotelMethod) == null) {
                    HotelServiceGrpc.getRateHotelMethod = getRateHotelMethod = io.grpc.MethodDescriptor.<hotelmanagementsystem.infrastructure.api.grpc.generated.RateHotelRequest, hotelmanagementsystem.infrastructure.api.grpc.generated.Empty>newBuilder().setType(io.grpc.MethodDescriptor.MethodType.UNARY).setFullMethodName(generateFullMethodName(SERVICE_NAME, "RateHotel")).setSampledToLocalTracing(true).setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(hotelmanagementsystem.infrastructure.api.grpc.generated.RateHotelRequest.getDefaultInstance())).setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(hotelmanagementsystem.infrastructure.api.grpc.generated.Empty.getDefaultInstance())).setSchemaDescriptor(new HotelServiceMethodDescriptorSupplier("RateHotel")).build();
                }
            }
        }
        return getRateHotelMethod;
    }

    private static volatile io.grpc.MethodDescriptor<hotelmanagementsystem.infrastructure.api.grpc.generated.FindAvailableRoomsRequest, hotelmanagementsystem.infrastructure.api.grpc.generated.ListRoomsResponse> getFindAvailableRoomsMethod;

    @io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/' + "FindAvailableRooms", requestType = hotelmanagementsystem.infrastructure.api.grpc.generated.FindAvailableRoomsRequest.class, responseType = hotelmanagementsystem.infrastructure.api.grpc.generated.ListRoomsResponse.class, methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<hotelmanagementsystem.infrastructure.api.grpc.generated.FindAvailableRoomsRequest, hotelmanagementsystem.infrastructure.api.grpc.generated.ListRoomsResponse> getFindAvailableRoomsMethod() {
        io.grpc.MethodDescriptor<hotelmanagementsystem.infrastructure.api.grpc.generated.FindAvailableRoomsRequest, hotelmanagementsystem.infrastructure.api.grpc.generated.ListRoomsResponse> getFindAvailableRoomsMethod;
        if ((getFindAvailableRoomsMethod = HotelServiceGrpc.getFindAvailableRoomsMethod) == null) {
            synchronized (HotelServiceGrpc.class) {
                if ((getFindAvailableRoomsMethod = HotelServiceGrpc.getFindAvailableRoomsMethod) == null) {
                    HotelServiceGrpc.getFindAvailableRoomsMethod = getFindAvailableRoomsMethod = io.grpc.MethodDescriptor.<hotelmanagementsystem.infrastructure.api.grpc.generated.FindAvailableRoomsRequest, hotelmanagementsystem.infrastructure.api.grpc.generated.ListRoomsResponse>newBuilder().setType(io.grpc.MethodDescriptor.MethodType.UNARY).setFullMethodName(generateFullMethodName(SERVICE_NAME, "FindAvailableRooms")).setSampledToLocalTracing(true).setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(hotelmanagementsystem.infrastructure.api.grpc.generated.FindAvailableRoomsRequest.getDefaultInstance())).setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(hotelmanagementsystem.infrastructure.api.grpc.generated.ListRoomsResponse.getDefaultInstance())).setSchemaDescriptor(new HotelServiceMethodDescriptorSupplier("FindAvailableRooms")).build();
                }
            }
        }
        return getFindAvailableRoomsMethod;
    }

    /**
     * Creates a new async stub that supports all call types for the service
     */
    public static HotelServiceStub newStub(io.grpc.Channel channel) {
        io.grpc.stub.AbstractStub.StubFactory<HotelServiceStub> factory = new io.grpc.stub.AbstractStub.StubFactory<HotelServiceStub>() {

            @java.lang.Override
            public HotelServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
                return new HotelServiceStub(channel, callOptions);
            }
        };
        return HotelServiceStub.newStub(factory, channel);
    }

    /**
     * Creates a new blocking-style stub that supports unary and streaming output calls on the service
     */
    public static HotelServiceBlockingStub newBlockingStub(io.grpc.Channel channel) {
        io.grpc.stub.AbstractStub.StubFactory<HotelServiceBlockingStub> factory = new io.grpc.stub.AbstractStub.StubFactory<HotelServiceBlockingStub>() {

            @java.lang.Override
            public HotelServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
                return new HotelServiceBlockingStub(channel, callOptions);
            }
        };
        return HotelServiceBlockingStub.newStub(factory, channel);
    }

    /**
     * Creates a new ListenableFuture-style stub that supports unary calls on the service
     */
    public static HotelServiceFutureStub newFutureStub(io.grpc.Channel channel) {
        io.grpc.stub.AbstractStub.StubFactory<HotelServiceFutureStub> factory = new io.grpc.stub.AbstractStub.StubFactory<HotelServiceFutureStub>() {

            @java.lang.Override
            public HotelServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
                return new HotelServiceFutureStub(channel, callOptions);
            }
        };
        return HotelServiceFutureStub.newStub(factory, channel);
    }

    /**
     * <pre>
     * Hotel Service
     * </pre>
     */
    public interface AsyncService {

        /**
         */
        default void createHotel(hotelmanagementsystem.infrastructure.api.grpc.generated.CreateHotelRequest request, io.grpc.stub.StreamObserver<hotelmanagementsystem.infrastructure.api.grpc.generated.HotelResponse> responseObserver) {
            io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCreateHotelMethod(), responseObserver);
        }

        /**
         */
        default void getHotelById(hotelmanagementsystem.infrastructure.api.grpc.generated.GetHotelByIdRequest request, io.grpc.stub.StreamObserver<hotelmanagementsystem.infrastructure.api.grpc.generated.HotelResponse> responseObserver) {
            io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetHotelByIdMethod(), responseObserver);
        }

        /**
         */
        default void listHotels(hotelmanagementsystem.infrastructure.api.grpc.generated.ListHotelsRequest request, io.grpc.stub.StreamObserver<hotelmanagementsystem.infrastructure.api.grpc.generated.ListHotelsResponse> responseObserver) {
            io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getListHotelsMethod(), responseObserver);
        }

        /**
         */
        default void updateHotel(hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateHotelRequest request, io.grpc.stub.StreamObserver<hotelmanagementsystem.infrastructure.api.grpc.generated.HotelResponse> responseObserver) {
            io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getUpdateHotelMethod(), responseObserver);
        }

        /**
         */
        default void deleteHotel(hotelmanagementsystem.infrastructure.api.grpc.generated.DeleteHotelRequest request, io.grpc.stub.StreamObserver<hotelmanagementsystem.infrastructure.api.grpc.generated.Empty> responseObserver) {
            io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDeleteHotelMethod(), responseObserver);
        }

        /**
         */
        default void rateHotel(hotelmanagementsystem.infrastructure.api.grpc.generated.RateHotelRequest request, io.grpc.stub.StreamObserver<hotelmanagementsystem.infrastructure.api.grpc.generated.Empty> responseObserver) {
            io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getRateHotelMethod(), responseObserver);
        }

        /**
         */
        default void findAvailableRooms(hotelmanagementsystem.infrastructure.api.grpc.generated.FindAvailableRoomsRequest request, io.grpc.stub.StreamObserver<hotelmanagementsystem.infrastructure.api.grpc.generated.ListRoomsResponse> responseObserver) {
            io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getFindAvailableRoomsMethod(), responseObserver);
        }
    }

    /**
     * Base class for the server implementation of the service HotelService.
     * <pre>
     * Hotel Service
     * </pre>
     */
    public static abstract class HotelServiceImplBase implements io.grpc.BindableService, AsyncService {

        @java.lang.Override
        public io.grpc.ServerServiceDefinition bindService() {
            return HotelServiceGrpc.bindService(this);
        }
    }

    /**
     * A stub to allow clients to do asynchronous rpc calls to service HotelService.
     * <pre>
     * Hotel Service
     * </pre>
     */
    public static class HotelServiceStub extends io.grpc.stub.AbstractAsyncStub<HotelServiceStub> {

        private HotelServiceStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            super(channel, callOptions);
        }

        @java.lang.Override
        protected HotelServiceStub build(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            return new HotelServiceStub(channel, callOptions);
        }

        /**
         */
        public void createHotel(hotelmanagementsystem.infrastructure.api.grpc.generated.CreateHotelRequest request, io.grpc.stub.StreamObserver<hotelmanagementsystem.infrastructure.api.grpc.generated.HotelResponse> responseObserver) {
            io.grpc.stub.ClientCalls.asyncUnaryCall(getChannel().newCall(getCreateHotelMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         */
        public void getHotelById(hotelmanagementsystem.infrastructure.api.grpc.generated.GetHotelByIdRequest request, io.grpc.stub.StreamObserver<hotelmanagementsystem.infrastructure.api.grpc.generated.HotelResponse> responseObserver) {
            io.grpc.stub.ClientCalls.asyncUnaryCall(getChannel().newCall(getGetHotelByIdMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         */
        public void listHotels(hotelmanagementsystem.infrastructure.api.grpc.generated.ListHotelsRequest request, io.grpc.stub.StreamObserver<hotelmanagementsystem.infrastructure.api.grpc.generated.ListHotelsResponse> responseObserver) {
            io.grpc.stub.ClientCalls.asyncUnaryCall(getChannel().newCall(getListHotelsMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         */
        public void updateHotel(hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateHotelRequest request, io.grpc.stub.StreamObserver<hotelmanagementsystem.infrastructure.api.grpc.generated.HotelResponse> responseObserver) {
            io.grpc.stub.ClientCalls.asyncUnaryCall(getChannel().newCall(getUpdateHotelMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         */
        public void deleteHotel(hotelmanagementsystem.infrastructure.api.grpc.generated.DeleteHotelRequest request, io.grpc.stub.StreamObserver<hotelmanagementsystem.infrastructure.api.grpc.generated.Empty> responseObserver) {
            io.grpc.stub.ClientCalls.asyncUnaryCall(getChannel().newCall(getDeleteHotelMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         */
        public void rateHotel(hotelmanagementsystem.infrastructure.api.grpc.generated.RateHotelRequest request, io.grpc.stub.StreamObserver<hotelmanagementsystem.infrastructure.api.grpc.generated.Empty> responseObserver) {
            io.grpc.stub.ClientCalls.asyncUnaryCall(getChannel().newCall(getRateHotelMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         */
        public void findAvailableRooms(hotelmanagementsystem.infrastructure.api.grpc.generated.FindAvailableRoomsRequest request, io.grpc.stub.StreamObserver<hotelmanagementsystem.infrastructure.api.grpc.generated.ListRoomsResponse> responseObserver) {
            io.grpc.stub.ClientCalls.asyncUnaryCall(getChannel().newCall(getFindAvailableRoomsMethod(), getCallOptions()), request, responseObserver);
        }
    }

    /**
     * A stub to allow clients to do synchronous rpc calls to service HotelService.
     * <pre>
     * Hotel Service
     * </pre>
     */
    public static class HotelServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<HotelServiceBlockingStub> {

        private HotelServiceBlockingStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            super(channel, callOptions);
        }

        @java.lang.Override
        protected HotelServiceBlockingStub build(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            return new HotelServiceBlockingStub(channel, callOptions);
        }

        /**
         */
        public hotelmanagementsystem.infrastructure.api.grpc.generated.HotelResponse createHotel(hotelmanagementsystem.infrastructure.api.grpc.generated.CreateHotelRequest request) {
            return io.grpc.stub.ClientCalls.blockingUnaryCall(getChannel(), getCreateHotelMethod(), getCallOptions(), request);
        }

        /**
         */
        public hotelmanagementsystem.infrastructure.api.grpc.generated.HotelResponse getHotelById(hotelmanagementsystem.infrastructure.api.grpc.generated.GetHotelByIdRequest request) {
            return io.grpc.stub.ClientCalls.blockingUnaryCall(getChannel(), getGetHotelByIdMethod(), getCallOptions(), request);
        }

        /**
         */
        public hotelmanagementsystem.infrastructure.api.grpc.generated.ListHotelsResponse listHotels(hotelmanagementsystem.infrastructure.api.grpc.generated.ListHotelsRequest request) {
            return io.grpc.stub.ClientCalls.blockingUnaryCall(getChannel(), getListHotelsMethod(), getCallOptions(), request);
        }

        /**
         */
        public hotelmanagementsystem.infrastructure.api.grpc.generated.HotelResponse updateHotel(hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateHotelRequest request) {
            return io.grpc.stub.ClientCalls.blockingUnaryCall(getChannel(), getUpdateHotelMethod(), getCallOptions(), request);
        }

        /**
         */
        public hotelmanagementsystem.infrastructure.api.grpc.generated.Empty deleteHotel(hotelmanagementsystem.infrastructure.api.grpc.generated.DeleteHotelRequest request) {
            return io.grpc.stub.ClientCalls.blockingUnaryCall(getChannel(), getDeleteHotelMethod(), getCallOptions(), request);
        }

        /**
         */
        public hotelmanagementsystem.infrastructure.api.grpc.generated.Empty rateHotel(hotelmanagementsystem.infrastructure.api.grpc.generated.RateHotelRequest request) {
            return io.grpc.stub.ClientCalls.blockingUnaryCall(getChannel(), getRateHotelMethod(), getCallOptions(), request);
        }

        /**
         */
        public hotelmanagementsystem.infrastructure.api.grpc.generated.ListRoomsResponse findAvailableRooms(hotelmanagementsystem.infrastructure.api.grpc.generated.FindAvailableRoomsRequest request) {
            return io.grpc.stub.ClientCalls.blockingUnaryCall(getChannel(), getFindAvailableRoomsMethod(), getCallOptions(), request);
        }
    }

    /**
     * A stub to allow clients to do ListenableFuture-style rpc calls to service HotelService.
     * <pre>
     * Hotel Service
     * </pre>
     */
    public static class HotelServiceFutureStub extends io.grpc.stub.AbstractFutureStub<HotelServiceFutureStub> {

        private HotelServiceFutureStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            super(channel, callOptions);
        }

        @java.lang.Override
        protected HotelServiceFutureStub build(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            return new HotelServiceFutureStub(channel, callOptions);
        }

        /**
         */
        public com.google.common.util.concurrent.ListenableFuture<hotelmanagementsystem.infrastructure.api.grpc.generated.HotelResponse> createHotel(hotelmanagementsystem.infrastructure.api.grpc.generated.CreateHotelRequest request) {
            return io.grpc.stub.ClientCalls.futureUnaryCall(getChannel().newCall(getCreateHotelMethod(), getCallOptions()), request);
        }

        /**
         */
        public com.google.common.util.concurrent.ListenableFuture<hotelmanagementsystem.infrastructure.api.grpc.generated.HotelResponse> getHotelById(hotelmanagementsystem.infrastructure.api.grpc.generated.GetHotelByIdRequest request) {
            return io.grpc.stub.ClientCalls.futureUnaryCall(getChannel().newCall(getGetHotelByIdMethod(), getCallOptions()), request);
        }

        /**
         */
        public com.google.common.util.concurrent.ListenableFuture<hotelmanagementsystem.infrastructure.api.grpc.generated.ListHotelsResponse> listHotels(hotelmanagementsystem.infrastructure.api.grpc.generated.ListHotelsRequest request) {
            return io.grpc.stub.ClientCalls.futureUnaryCall(getChannel().newCall(getListHotelsMethod(), getCallOptions()), request);
        }

        /**
         */
        public com.google.common.util.concurrent.ListenableFuture<hotelmanagementsystem.infrastructure.api.grpc.generated.HotelResponse> updateHotel(hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateHotelRequest request) {
            return io.grpc.stub.ClientCalls.futureUnaryCall(getChannel().newCall(getUpdateHotelMethod(), getCallOptions()), request);
        }

        /**
         */
        public com.google.common.util.concurrent.ListenableFuture<hotelmanagementsystem.infrastructure.api.grpc.generated.Empty> deleteHotel(hotelmanagementsystem.infrastructure.api.grpc.generated.DeleteHotelRequest request) {
            return io.grpc.stub.ClientCalls.futureUnaryCall(getChannel().newCall(getDeleteHotelMethod(), getCallOptions()), request);
        }

        /**
         */
        public com.google.common.util.concurrent.ListenableFuture<hotelmanagementsystem.infrastructure.api.grpc.generated.Empty> rateHotel(hotelmanagementsystem.infrastructure.api.grpc.generated.RateHotelRequest request) {
            return io.grpc.stub.ClientCalls.futureUnaryCall(getChannel().newCall(getRateHotelMethod(), getCallOptions()), request);
        }

        /**
         */
        public com.google.common.util.concurrent.ListenableFuture<hotelmanagementsystem.infrastructure.api.grpc.generated.ListRoomsResponse> findAvailableRooms(hotelmanagementsystem.infrastructure.api.grpc.generated.FindAvailableRoomsRequest request) {
            return io.grpc.stub.ClientCalls.futureUnaryCall(getChannel().newCall(getFindAvailableRoomsMethod(), getCallOptions()), request);
        }
    }

    private static final int METHODID_CREATE_HOTEL = 0;

    private static final int METHODID_GET_HOTEL_BY_ID = 1;

    private static final int METHODID_LIST_HOTELS = 2;

    private static final int METHODID_UPDATE_HOTEL = 3;

    private static final int METHODID_DELETE_HOTEL = 4;

    private static final int METHODID_RATE_HOTEL = 5;

    private static final int METHODID_FIND_AVAILABLE_ROOMS = 6;

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
                case METHODID_CREATE_HOTEL:
                    serviceImpl.createHotel((hotelmanagementsystem.infrastructure.api.grpc.generated.CreateHotelRequest) request, (io.grpc.stub.StreamObserver<hotelmanagementsystem.infrastructure.api.grpc.generated.HotelResponse>) responseObserver);
                    break;
                case METHODID_GET_HOTEL_BY_ID:
                    serviceImpl.getHotelById((hotelmanagementsystem.infrastructure.api.grpc.generated.GetHotelByIdRequest) request, (io.grpc.stub.StreamObserver<hotelmanagementsystem.infrastructure.api.grpc.generated.HotelResponse>) responseObserver);
                    break;
                case METHODID_LIST_HOTELS:
                    serviceImpl.listHotels((hotelmanagementsystem.infrastructure.api.grpc.generated.ListHotelsRequest) request, (io.grpc.stub.StreamObserver<hotelmanagementsystem.infrastructure.api.grpc.generated.ListHotelsResponse>) responseObserver);
                    break;
                case METHODID_UPDATE_HOTEL:
                    serviceImpl.updateHotel((hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateHotelRequest) request, (io.grpc.stub.StreamObserver<hotelmanagementsystem.infrastructure.api.grpc.generated.HotelResponse>) responseObserver);
                    break;
                case METHODID_DELETE_HOTEL:
                    serviceImpl.deleteHotel((hotelmanagementsystem.infrastructure.api.grpc.generated.DeleteHotelRequest) request, (io.grpc.stub.StreamObserver<hotelmanagementsystem.infrastructure.api.grpc.generated.Empty>) responseObserver);
                    break;
                case METHODID_RATE_HOTEL:
                    serviceImpl.rateHotel((hotelmanagementsystem.infrastructure.api.grpc.generated.RateHotelRequest) request, (io.grpc.stub.StreamObserver<hotelmanagementsystem.infrastructure.api.grpc.generated.Empty>) responseObserver);
                    break;
                case METHODID_FIND_AVAILABLE_ROOMS:
                    serviceImpl.findAvailableRooms((hotelmanagementsystem.infrastructure.api.grpc.generated.FindAvailableRoomsRequest) request, (io.grpc.stub.StreamObserver<hotelmanagementsystem.infrastructure.api.grpc.generated.ListRoomsResponse>) responseObserver);
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
        return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor()).addMethod(getCreateHotelMethod(), io.grpc.stub.ServerCalls.asyncUnaryCall(new MethodHandlers<hotelmanagementsystem.infrastructure.api.grpc.generated.CreateHotelRequest, hotelmanagementsystem.infrastructure.api.grpc.generated.HotelResponse>(service, METHODID_CREATE_HOTEL))).addMethod(getGetHotelByIdMethod(), io.grpc.stub.ServerCalls.asyncUnaryCall(new MethodHandlers<hotelmanagementsystem.infrastructure.api.grpc.generated.GetHotelByIdRequest, hotelmanagementsystem.infrastructure.api.grpc.generated.HotelResponse>(service, METHODID_GET_HOTEL_BY_ID))).addMethod(getListHotelsMethod(), io.grpc.stub.ServerCalls.asyncUnaryCall(new MethodHandlers<hotelmanagementsystem.infrastructure.api.grpc.generated.ListHotelsRequest, hotelmanagementsystem.infrastructure.api.grpc.generated.ListHotelsResponse>(service, METHODID_LIST_HOTELS))).addMethod(getUpdateHotelMethod(), io.grpc.stub.ServerCalls.asyncUnaryCall(new MethodHandlers<hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateHotelRequest, hotelmanagementsystem.infrastructure.api.grpc.generated.HotelResponse>(service, METHODID_UPDATE_HOTEL))).addMethod(getDeleteHotelMethod(), io.grpc.stub.ServerCalls.asyncUnaryCall(new MethodHandlers<hotelmanagementsystem.infrastructure.api.grpc.generated.DeleteHotelRequest, hotelmanagementsystem.infrastructure.api.grpc.generated.Empty>(service, METHODID_DELETE_HOTEL))).addMethod(getRateHotelMethod(), io.grpc.stub.ServerCalls.asyncUnaryCall(new MethodHandlers<hotelmanagementsystem.infrastructure.api.grpc.generated.RateHotelRequest, hotelmanagementsystem.infrastructure.api.grpc.generated.Empty>(service, METHODID_RATE_HOTEL))).addMethod(getFindAvailableRoomsMethod(), io.grpc.stub.ServerCalls.asyncUnaryCall(new MethodHandlers<hotelmanagementsystem.infrastructure.api.grpc.generated.FindAvailableRoomsRequest, hotelmanagementsystem.infrastructure.api.grpc.generated.ListRoomsResponse>(service, METHODID_FIND_AVAILABLE_ROOMS))).build();
    }

    private static abstract class HotelServiceBaseDescriptorSupplier implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {

        HotelServiceBaseDescriptorSupplier() {
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
            return hotelmanagementsystem.infrastructure.api.grpc.generated.HotelManagement.getDescriptor();
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
            return getFileDescriptor().findServiceByName("HotelService");
        }
    }

    private static final class HotelServiceFileDescriptorSupplier extends HotelServiceBaseDescriptorSupplier {

        HotelServiceFileDescriptorSupplier() {
        }
    }

    private static final class HotelServiceMethodDescriptorSupplier extends HotelServiceBaseDescriptorSupplier implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {

        private final java.lang.String methodName;

        HotelServiceMethodDescriptorSupplier(java.lang.String methodName) {
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
            synchronized (HotelServiceGrpc.class) {
                result = serviceDescriptor;
                if (result == null) {
                    serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME).setSchemaDescriptor(new HotelServiceFileDescriptorSupplier()).addMethod(getCreateHotelMethod()).addMethod(getGetHotelByIdMethod()).addMethod(getListHotelsMethod()).addMethod(getUpdateHotelMethod()).addMethod(getDeleteHotelMethod()).addMethod(getRateHotelMethod()).addMethod(getFindAvailableRoomsMethod()).build();
                }
            }
        }
        return result;
    }
}
