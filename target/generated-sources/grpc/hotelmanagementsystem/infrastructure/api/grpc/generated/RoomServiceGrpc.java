package hotelmanagementsystem.infrastructure.api.grpc.generated;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Room Service
 * </pre>
 */
@io.quarkus.Generated(value = "by gRPC proto compiler (version 1.64.0)", comments = "Source: api.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class RoomServiceGrpc {

    private RoomServiceGrpc() {
    }

    public static final java.lang.String SERVICE_NAME = "hotelmanagement.RoomService";

    // Static method descriptors that strictly reflect the proto.
    private static volatile io.grpc.MethodDescriptor<hotelmanagementsystem.infrastructure.api.grpc.generated.CreateRoomRequest, hotelmanagementsystem.infrastructure.api.grpc.generated.RoomResponse> getCreateRoomMethod;

    @io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/' + "CreateRoom", requestType = hotelmanagementsystem.infrastructure.api.grpc.generated.CreateRoomRequest.class, responseType = hotelmanagementsystem.infrastructure.api.grpc.generated.RoomResponse.class, methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<hotelmanagementsystem.infrastructure.api.grpc.generated.CreateRoomRequest, hotelmanagementsystem.infrastructure.api.grpc.generated.RoomResponse> getCreateRoomMethod() {
        io.grpc.MethodDescriptor<hotelmanagementsystem.infrastructure.api.grpc.generated.CreateRoomRequest, hotelmanagementsystem.infrastructure.api.grpc.generated.RoomResponse> getCreateRoomMethod;
        if ((getCreateRoomMethod = RoomServiceGrpc.getCreateRoomMethod) == null) {
            synchronized (RoomServiceGrpc.class) {
                if ((getCreateRoomMethod = RoomServiceGrpc.getCreateRoomMethod) == null) {
                    RoomServiceGrpc.getCreateRoomMethod = getCreateRoomMethod = io.grpc.MethodDescriptor.<hotelmanagementsystem.infrastructure.api.grpc.generated.CreateRoomRequest, hotelmanagementsystem.infrastructure.api.grpc.generated.RoomResponse>newBuilder().setType(io.grpc.MethodDescriptor.MethodType.UNARY).setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreateRoom")).setSampledToLocalTracing(true).setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(hotelmanagementsystem.infrastructure.api.grpc.generated.CreateRoomRequest.getDefaultInstance())).setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(hotelmanagementsystem.infrastructure.api.grpc.generated.RoomResponse.getDefaultInstance())).setSchemaDescriptor(new RoomServiceMethodDescriptorSupplier("CreateRoom")).build();
                }
            }
        }
        return getCreateRoomMethod;
    }

    private static volatile io.grpc.MethodDescriptor<hotelmanagementsystem.infrastructure.api.grpc.generated.GetRoomByIdRequest, hotelmanagementsystem.infrastructure.api.grpc.generated.RoomResponse> getGetRoomByIdMethod;

    @io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/' + "GetRoomById", requestType = hotelmanagementsystem.infrastructure.api.grpc.generated.GetRoomByIdRequest.class, responseType = hotelmanagementsystem.infrastructure.api.grpc.generated.RoomResponse.class, methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<hotelmanagementsystem.infrastructure.api.grpc.generated.GetRoomByIdRequest, hotelmanagementsystem.infrastructure.api.grpc.generated.RoomResponse> getGetRoomByIdMethod() {
        io.grpc.MethodDescriptor<hotelmanagementsystem.infrastructure.api.grpc.generated.GetRoomByIdRequest, hotelmanagementsystem.infrastructure.api.grpc.generated.RoomResponse> getGetRoomByIdMethod;
        if ((getGetRoomByIdMethod = RoomServiceGrpc.getGetRoomByIdMethod) == null) {
            synchronized (RoomServiceGrpc.class) {
                if ((getGetRoomByIdMethod = RoomServiceGrpc.getGetRoomByIdMethod) == null) {
                    RoomServiceGrpc.getGetRoomByIdMethod = getGetRoomByIdMethod = io.grpc.MethodDescriptor.<hotelmanagementsystem.infrastructure.api.grpc.generated.GetRoomByIdRequest, hotelmanagementsystem.infrastructure.api.grpc.generated.RoomResponse>newBuilder().setType(io.grpc.MethodDescriptor.MethodType.UNARY).setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetRoomById")).setSampledToLocalTracing(true).setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(hotelmanagementsystem.infrastructure.api.grpc.generated.GetRoomByIdRequest.getDefaultInstance())).setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(hotelmanagementsystem.infrastructure.api.grpc.generated.RoomResponse.getDefaultInstance())).setSchemaDescriptor(new RoomServiceMethodDescriptorSupplier("GetRoomById")).build();
                }
            }
        }
        return getGetRoomByIdMethod;
    }

    private static volatile io.grpc.MethodDescriptor<hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateRoomPriceRequest, hotelmanagementsystem.infrastructure.api.grpc.generated.RoomResponse> getUpdateRoomPriceMethod;

    @io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/' + "UpdateRoomPrice", requestType = hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateRoomPriceRequest.class, responseType = hotelmanagementsystem.infrastructure.api.grpc.generated.RoomResponse.class, methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateRoomPriceRequest, hotelmanagementsystem.infrastructure.api.grpc.generated.RoomResponse> getUpdateRoomPriceMethod() {
        io.grpc.MethodDescriptor<hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateRoomPriceRequest, hotelmanagementsystem.infrastructure.api.grpc.generated.RoomResponse> getUpdateRoomPriceMethod;
        if ((getUpdateRoomPriceMethod = RoomServiceGrpc.getUpdateRoomPriceMethod) == null) {
            synchronized (RoomServiceGrpc.class) {
                if ((getUpdateRoomPriceMethod = RoomServiceGrpc.getUpdateRoomPriceMethod) == null) {
                    RoomServiceGrpc.getUpdateRoomPriceMethod = getUpdateRoomPriceMethod = io.grpc.MethodDescriptor.<hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateRoomPriceRequest, hotelmanagementsystem.infrastructure.api.grpc.generated.RoomResponse>newBuilder().setType(io.grpc.MethodDescriptor.MethodType.UNARY).setFullMethodName(generateFullMethodName(SERVICE_NAME, "UpdateRoomPrice")).setSampledToLocalTracing(true).setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateRoomPriceRequest.getDefaultInstance())).setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(hotelmanagementsystem.infrastructure.api.grpc.generated.RoomResponse.getDefaultInstance())).setSchemaDescriptor(new RoomServiceMethodDescriptorSupplier("UpdateRoomPrice")).build();
                }
            }
        }
        return getUpdateRoomPriceMethod;
    }

    private static volatile io.grpc.MethodDescriptor<hotelmanagementsystem.infrastructure.api.grpc.generated.RemoveRoomRequest, hotelmanagementsystem.infrastructure.api.grpc.generated.Empty> getRemoveRoomMethod;

    @io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/' + "RemoveRoom", requestType = hotelmanagementsystem.infrastructure.api.grpc.generated.RemoveRoomRequest.class, responseType = hotelmanagementsystem.infrastructure.api.grpc.generated.Empty.class, methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<hotelmanagementsystem.infrastructure.api.grpc.generated.RemoveRoomRequest, hotelmanagementsystem.infrastructure.api.grpc.generated.Empty> getRemoveRoomMethod() {
        io.grpc.MethodDescriptor<hotelmanagementsystem.infrastructure.api.grpc.generated.RemoveRoomRequest, hotelmanagementsystem.infrastructure.api.grpc.generated.Empty> getRemoveRoomMethod;
        if ((getRemoveRoomMethod = RoomServiceGrpc.getRemoveRoomMethod) == null) {
            synchronized (RoomServiceGrpc.class) {
                if ((getRemoveRoomMethod = RoomServiceGrpc.getRemoveRoomMethod) == null) {
                    RoomServiceGrpc.getRemoveRoomMethod = getRemoveRoomMethod = io.grpc.MethodDescriptor.<hotelmanagementsystem.infrastructure.api.grpc.generated.RemoveRoomRequest, hotelmanagementsystem.infrastructure.api.grpc.generated.Empty>newBuilder().setType(io.grpc.MethodDescriptor.MethodType.UNARY).setFullMethodName(generateFullMethodName(SERVICE_NAME, "RemoveRoom")).setSampledToLocalTracing(true).setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(hotelmanagementsystem.infrastructure.api.grpc.generated.RemoveRoomRequest.getDefaultInstance())).setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(hotelmanagementsystem.infrastructure.api.grpc.generated.Empty.getDefaultInstance())).setSchemaDescriptor(new RoomServiceMethodDescriptorSupplier("RemoveRoom")).build();
                }
            }
        }
        return getRemoveRoomMethod;
    }

    /**
     * Creates a new async stub that supports all call types for the service
     */
    public static RoomServiceStub newStub(io.grpc.Channel channel) {
        io.grpc.stub.AbstractStub.StubFactory<RoomServiceStub> factory = new io.grpc.stub.AbstractStub.StubFactory<RoomServiceStub>() {

            @java.lang.Override
            public RoomServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
                return new RoomServiceStub(channel, callOptions);
            }
        };
        return RoomServiceStub.newStub(factory, channel);
    }

    /**
     * Creates a new blocking-style stub that supports unary and streaming output calls on the service
     */
    public static RoomServiceBlockingStub newBlockingStub(io.grpc.Channel channel) {
        io.grpc.stub.AbstractStub.StubFactory<RoomServiceBlockingStub> factory = new io.grpc.stub.AbstractStub.StubFactory<RoomServiceBlockingStub>() {

            @java.lang.Override
            public RoomServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
                return new RoomServiceBlockingStub(channel, callOptions);
            }
        };
        return RoomServiceBlockingStub.newStub(factory, channel);
    }

    /**
     * Creates a new ListenableFuture-style stub that supports unary calls on the service
     */
    public static RoomServiceFutureStub newFutureStub(io.grpc.Channel channel) {
        io.grpc.stub.AbstractStub.StubFactory<RoomServiceFutureStub> factory = new io.grpc.stub.AbstractStub.StubFactory<RoomServiceFutureStub>() {

            @java.lang.Override
            public RoomServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
                return new RoomServiceFutureStub(channel, callOptions);
            }
        };
        return RoomServiceFutureStub.newStub(factory, channel);
    }

    /**
     * <pre>
     * Room Service
     * </pre>
     */
    public interface AsyncService {

        /**
         */
        default void createRoom(hotelmanagementsystem.infrastructure.api.grpc.generated.CreateRoomRequest request, io.grpc.stub.StreamObserver<hotelmanagementsystem.infrastructure.api.grpc.generated.RoomResponse> responseObserver) {
            io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCreateRoomMethod(), responseObserver);
        }

        /**
         */
        default void getRoomById(hotelmanagementsystem.infrastructure.api.grpc.generated.GetRoomByIdRequest request, io.grpc.stub.StreamObserver<hotelmanagementsystem.infrastructure.api.grpc.generated.RoomResponse> responseObserver) {
            io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetRoomByIdMethod(), responseObserver);
        }

        /**
         */
        default void updateRoomPrice(hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateRoomPriceRequest request, io.grpc.stub.StreamObserver<hotelmanagementsystem.infrastructure.api.grpc.generated.RoomResponse> responseObserver) {
            io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getUpdateRoomPriceMethod(), responseObserver);
        }

        /**
         */
        default void removeRoom(hotelmanagementsystem.infrastructure.api.grpc.generated.RemoveRoomRequest request, io.grpc.stub.StreamObserver<hotelmanagementsystem.infrastructure.api.grpc.generated.Empty> responseObserver) {
            io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getRemoveRoomMethod(), responseObserver);
        }
    }

    /**
     * Base class for the server implementation of the service RoomService.
     * <pre>
     * Room Service
     * </pre>
     */
    public static abstract class RoomServiceImplBase implements io.grpc.BindableService, AsyncService {

        @java.lang.Override
        public io.grpc.ServerServiceDefinition bindService() {
            return RoomServiceGrpc.bindService(this);
        }
    }

    /**
     * A stub to allow clients to do asynchronous rpc calls to service RoomService.
     * <pre>
     * Room Service
     * </pre>
     */
    public static class RoomServiceStub extends io.grpc.stub.AbstractAsyncStub<RoomServiceStub> {

        private RoomServiceStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            super(channel, callOptions);
        }

        @java.lang.Override
        protected RoomServiceStub build(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            return new RoomServiceStub(channel, callOptions);
        }

        /**
         */
        public void createRoom(hotelmanagementsystem.infrastructure.api.grpc.generated.CreateRoomRequest request, io.grpc.stub.StreamObserver<hotelmanagementsystem.infrastructure.api.grpc.generated.RoomResponse> responseObserver) {
            io.grpc.stub.ClientCalls.asyncUnaryCall(getChannel().newCall(getCreateRoomMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         */
        public void getRoomById(hotelmanagementsystem.infrastructure.api.grpc.generated.GetRoomByIdRequest request, io.grpc.stub.StreamObserver<hotelmanagementsystem.infrastructure.api.grpc.generated.RoomResponse> responseObserver) {
            io.grpc.stub.ClientCalls.asyncUnaryCall(getChannel().newCall(getGetRoomByIdMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         */
        public void updateRoomPrice(hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateRoomPriceRequest request, io.grpc.stub.StreamObserver<hotelmanagementsystem.infrastructure.api.grpc.generated.RoomResponse> responseObserver) {
            io.grpc.stub.ClientCalls.asyncUnaryCall(getChannel().newCall(getUpdateRoomPriceMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         */
        public void removeRoom(hotelmanagementsystem.infrastructure.api.grpc.generated.RemoveRoomRequest request, io.grpc.stub.StreamObserver<hotelmanagementsystem.infrastructure.api.grpc.generated.Empty> responseObserver) {
            io.grpc.stub.ClientCalls.asyncUnaryCall(getChannel().newCall(getRemoveRoomMethod(), getCallOptions()), request, responseObserver);
        }
    }

    /**
     * A stub to allow clients to do synchronous rpc calls to service RoomService.
     * <pre>
     * Room Service
     * </pre>
     */
    public static class RoomServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<RoomServiceBlockingStub> {

        private RoomServiceBlockingStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            super(channel, callOptions);
        }

        @java.lang.Override
        protected RoomServiceBlockingStub build(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            return new RoomServiceBlockingStub(channel, callOptions);
        }

        /**
         */
        public hotelmanagementsystem.infrastructure.api.grpc.generated.RoomResponse createRoom(hotelmanagementsystem.infrastructure.api.grpc.generated.CreateRoomRequest request) {
            return io.grpc.stub.ClientCalls.blockingUnaryCall(getChannel(), getCreateRoomMethod(), getCallOptions(), request);
        }

        /**
         */
        public hotelmanagementsystem.infrastructure.api.grpc.generated.RoomResponse getRoomById(hotelmanagementsystem.infrastructure.api.grpc.generated.GetRoomByIdRequest request) {
            return io.grpc.stub.ClientCalls.blockingUnaryCall(getChannel(), getGetRoomByIdMethod(), getCallOptions(), request);
        }

        /**
         */
        public hotelmanagementsystem.infrastructure.api.grpc.generated.RoomResponse updateRoomPrice(hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateRoomPriceRequest request) {
            return io.grpc.stub.ClientCalls.blockingUnaryCall(getChannel(), getUpdateRoomPriceMethod(), getCallOptions(), request);
        }

        /**
         */
        public hotelmanagementsystem.infrastructure.api.grpc.generated.Empty removeRoom(hotelmanagementsystem.infrastructure.api.grpc.generated.RemoveRoomRequest request) {
            return io.grpc.stub.ClientCalls.blockingUnaryCall(getChannel(), getRemoveRoomMethod(), getCallOptions(), request);
        }
    }

    /**
     * A stub to allow clients to do ListenableFuture-style rpc calls to service RoomService.
     * <pre>
     * Room Service
     * </pre>
     */
    public static class RoomServiceFutureStub extends io.grpc.stub.AbstractFutureStub<RoomServiceFutureStub> {

        private RoomServiceFutureStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            super(channel, callOptions);
        }

        @java.lang.Override
        protected RoomServiceFutureStub build(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            return new RoomServiceFutureStub(channel, callOptions);
        }

        /**
         */
        public com.google.common.util.concurrent.ListenableFuture<hotelmanagementsystem.infrastructure.api.grpc.generated.RoomResponse> createRoom(hotelmanagementsystem.infrastructure.api.grpc.generated.CreateRoomRequest request) {
            return io.grpc.stub.ClientCalls.futureUnaryCall(getChannel().newCall(getCreateRoomMethod(), getCallOptions()), request);
        }

        /**
         */
        public com.google.common.util.concurrent.ListenableFuture<hotelmanagementsystem.infrastructure.api.grpc.generated.RoomResponse> getRoomById(hotelmanagementsystem.infrastructure.api.grpc.generated.GetRoomByIdRequest request) {
            return io.grpc.stub.ClientCalls.futureUnaryCall(getChannel().newCall(getGetRoomByIdMethod(), getCallOptions()), request);
        }

        /**
         */
        public com.google.common.util.concurrent.ListenableFuture<hotelmanagementsystem.infrastructure.api.grpc.generated.RoomResponse> updateRoomPrice(hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateRoomPriceRequest request) {
            return io.grpc.stub.ClientCalls.futureUnaryCall(getChannel().newCall(getUpdateRoomPriceMethod(), getCallOptions()), request);
        }

        /**
         */
        public com.google.common.util.concurrent.ListenableFuture<hotelmanagementsystem.infrastructure.api.grpc.generated.Empty> removeRoom(hotelmanagementsystem.infrastructure.api.grpc.generated.RemoveRoomRequest request) {
            return io.grpc.stub.ClientCalls.futureUnaryCall(getChannel().newCall(getRemoveRoomMethod(), getCallOptions()), request);
        }
    }

    private static final int METHODID_CREATE_ROOM = 0;

    private static final int METHODID_GET_ROOM_BY_ID = 1;

    private static final int METHODID_UPDATE_ROOM_PRICE = 2;

    private static final int METHODID_REMOVE_ROOM = 3;

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
                case METHODID_CREATE_ROOM:
                    serviceImpl.createRoom((hotelmanagementsystem.infrastructure.api.grpc.generated.CreateRoomRequest) request, (io.grpc.stub.StreamObserver<hotelmanagementsystem.infrastructure.api.grpc.generated.RoomResponse>) responseObserver);
                    break;
                case METHODID_GET_ROOM_BY_ID:
                    serviceImpl.getRoomById((hotelmanagementsystem.infrastructure.api.grpc.generated.GetRoomByIdRequest) request, (io.grpc.stub.StreamObserver<hotelmanagementsystem.infrastructure.api.grpc.generated.RoomResponse>) responseObserver);
                    break;
                case METHODID_UPDATE_ROOM_PRICE:
                    serviceImpl.updateRoomPrice((hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateRoomPriceRequest) request, (io.grpc.stub.StreamObserver<hotelmanagementsystem.infrastructure.api.grpc.generated.RoomResponse>) responseObserver);
                    break;
                case METHODID_REMOVE_ROOM:
                    serviceImpl.removeRoom((hotelmanagementsystem.infrastructure.api.grpc.generated.RemoveRoomRequest) request, (io.grpc.stub.StreamObserver<hotelmanagementsystem.infrastructure.api.grpc.generated.Empty>) responseObserver);
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
        return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor()).addMethod(getCreateRoomMethod(), io.grpc.stub.ServerCalls.asyncUnaryCall(new MethodHandlers<hotelmanagementsystem.infrastructure.api.grpc.generated.CreateRoomRequest, hotelmanagementsystem.infrastructure.api.grpc.generated.RoomResponse>(service, METHODID_CREATE_ROOM))).addMethod(getGetRoomByIdMethod(), io.grpc.stub.ServerCalls.asyncUnaryCall(new MethodHandlers<hotelmanagementsystem.infrastructure.api.grpc.generated.GetRoomByIdRequest, hotelmanagementsystem.infrastructure.api.grpc.generated.RoomResponse>(service, METHODID_GET_ROOM_BY_ID))).addMethod(getUpdateRoomPriceMethod(), io.grpc.stub.ServerCalls.asyncUnaryCall(new MethodHandlers<hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateRoomPriceRequest, hotelmanagementsystem.infrastructure.api.grpc.generated.RoomResponse>(service, METHODID_UPDATE_ROOM_PRICE))).addMethod(getRemoveRoomMethod(), io.grpc.stub.ServerCalls.asyncUnaryCall(new MethodHandlers<hotelmanagementsystem.infrastructure.api.grpc.generated.RemoveRoomRequest, hotelmanagementsystem.infrastructure.api.grpc.generated.Empty>(service, METHODID_REMOVE_ROOM))).build();
    }

    private static abstract class RoomServiceBaseDescriptorSupplier implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {

        RoomServiceBaseDescriptorSupplier() {
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
            return hotelmanagementsystem.infrastructure.api.grpc.generated.HotelManagement.getDescriptor();
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
            return getFileDescriptor().findServiceByName("RoomService");
        }
    }

    private static final class RoomServiceFileDescriptorSupplier extends RoomServiceBaseDescriptorSupplier {

        RoomServiceFileDescriptorSupplier() {
        }
    }

    private static final class RoomServiceMethodDescriptorSupplier extends RoomServiceBaseDescriptorSupplier implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {

        private final java.lang.String methodName;

        RoomServiceMethodDescriptorSupplier(java.lang.String methodName) {
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
            synchronized (RoomServiceGrpc.class) {
                result = serviceDescriptor;
                if (result == null) {
                    serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME).setSchemaDescriptor(new RoomServiceFileDescriptorSupplier()).addMethod(getCreateRoomMethod()).addMethod(getGetRoomByIdMethod()).addMethod(getUpdateRoomPriceMethod()).addMethod(getRemoveRoomMethod()).build();
                }
            }
        }
        return result;
    }
}
