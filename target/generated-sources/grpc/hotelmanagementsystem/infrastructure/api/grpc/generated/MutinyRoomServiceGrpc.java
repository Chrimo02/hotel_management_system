package hotelmanagementsystem.infrastructure.api.grpc.generated;

import static hotelmanagementsystem.infrastructure.api.grpc.generated.RoomServiceGrpc.getServiceDescriptor;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;

@jakarta.annotation.Generated(value = "by Mutiny Grpc generator", comments = "Source: api.proto")
public final class MutinyRoomServiceGrpc implements io.quarkus.grpc.MutinyGrpc {

    private MutinyRoomServiceGrpc() {
    }

    public static MutinyRoomServiceStub newMutinyStub(io.grpc.Channel channel) {
        return new MutinyRoomServiceStub(channel);
    }

    /**
     * <pre>
     *  Room Service
     * </pre>
     */
    public static class MutinyRoomServiceStub extends io.grpc.stub.AbstractStub<MutinyRoomServiceStub> implements io.quarkus.grpc.MutinyStub {

        private RoomServiceGrpc.RoomServiceStub delegateStub;

        private MutinyRoomServiceStub(io.grpc.Channel channel) {
            super(channel);
            delegateStub = RoomServiceGrpc.newStub(channel);
        }

        private MutinyRoomServiceStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            super(channel, callOptions);
            delegateStub = RoomServiceGrpc.newStub(channel).build(channel, callOptions);
        }

        @Override
        protected MutinyRoomServiceStub build(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            return new MutinyRoomServiceStub(channel, callOptions);
        }

        public io.smallrye.mutiny.Uni<hotelmanagementsystem.infrastructure.api.grpc.generated.RoomResponse> createRoom(hotelmanagementsystem.infrastructure.api.grpc.generated.CreateRoomRequest request) {
            return io.quarkus.grpc.stubs.ClientCalls.oneToOne(request, delegateStub::createRoom);
        }

        public io.smallrye.mutiny.Uni<hotelmanagementsystem.infrastructure.api.grpc.generated.RoomResponse> getRoomById(hotelmanagementsystem.infrastructure.api.grpc.generated.GetRoomByIdRequest request) {
            return io.quarkus.grpc.stubs.ClientCalls.oneToOne(request, delegateStub::getRoomById);
        }

        public io.smallrye.mutiny.Uni<hotelmanagementsystem.infrastructure.api.grpc.generated.RoomResponse> updateRoomPrice(hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateRoomPriceRequest request) {
            return io.quarkus.grpc.stubs.ClientCalls.oneToOne(request, delegateStub::updateRoomPrice);
        }

        public io.smallrye.mutiny.Uni<hotelmanagementsystem.infrastructure.api.grpc.generated.Empty> removeRoom(hotelmanagementsystem.infrastructure.api.grpc.generated.RemoveRoomRequest request) {
            return io.quarkus.grpc.stubs.ClientCalls.oneToOne(request, delegateStub::removeRoom);
        }
    }

    /**
     * <pre>
     *  Room Service
     * </pre>
     */
    public static abstract class RoomServiceImplBase implements io.grpc.BindableService {

        private String compression;

        /**
         * Set whether the server will try to use a compressed response.
         *
         * @param compression the compression, e.g {@code gzip}
         */
        public RoomServiceImplBase withCompression(String compression) {
            this.compression = compression;
            return this;
        }

        public io.smallrye.mutiny.Uni<hotelmanagementsystem.infrastructure.api.grpc.generated.RoomResponse> createRoom(hotelmanagementsystem.infrastructure.api.grpc.generated.CreateRoomRequest request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        public io.smallrye.mutiny.Uni<hotelmanagementsystem.infrastructure.api.grpc.generated.RoomResponse> getRoomById(hotelmanagementsystem.infrastructure.api.grpc.generated.GetRoomByIdRequest request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        public io.smallrye.mutiny.Uni<hotelmanagementsystem.infrastructure.api.grpc.generated.RoomResponse> updateRoomPrice(hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateRoomPriceRequest request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        public io.smallrye.mutiny.Uni<hotelmanagementsystem.infrastructure.api.grpc.generated.Empty> removeRoom(hotelmanagementsystem.infrastructure.api.grpc.generated.RemoveRoomRequest request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        @java.lang.Override
        public io.grpc.ServerServiceDefinition bindService() {
            return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor()).addMethod(hotelmanagementsystem.infrastructure.api.grpc.generated.RoomServiceGrpc.getCreateRoomMethod(), asyncUnaryCall(new MethodHandlers<hotelmanagementsystem.infrastructure.api.grpc.generated.CreateRoomRequest, hotelmanagementsystem.infrastructure.api.grpc.generated.RoomResponse>(this, METHODID_CREATE_ROOM, compression))).addMethod(hotelmanagementsystem.infrastructure.api.grpc.generated.RoomServiceGrpc.getGetRoomByIdMethod(), asyncUnaryCall(new MethodHandlers<hotelmanagementsystem.infrastructure.api.grpc.generated.GetRoomByIdRequest, hotelmanagementsystem.infrastructure.api.grpc.generated.RoomResponse>(this, METHODID_GET_ROOM_BY_ID, compression))).addMethod(hotelmanagementsystem.infrastructure.api.grpc.generated.RoomServiceGrpc.getUpdateRoomPriceMethod(), asyncUnaryCall(new MethodHandlers<hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateRoomPriceRequest, hotelmanagementsystem.infrastructure.api.grpc.generated.RoomResponse>(this, METHODID_UPDATE_ROOM_PRICE, compression))).addMethod(hotelmanagementsystem.infrastructure.api.grpc.generated.RoomServiceGrpc.getRemoveRoomMethod(), asyncUnaryCall(new MethodHandlers<hotelmanagementsystem.infrastructure.api.grpc.generated.RemoveRoomRequest, hotelmanagementsystem.infrastructure.api.grpc.generated.Empty>(this, METHODID_REMOVE_ROOM, compression))).build();
        }
    }

    private static final int METHODID_CREATE_ROOM = 0;

    private static final int METHODID_GET_ROOM_BY_ID = 1;

    private static final int METHODID_UPDATE_ROOM_PRICE = 2;

    private static final int METHODID_REMOVE_ROOM = 3;

    private static final class MethodHandlers<Req, Resp> implements io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>, io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>, io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>, io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {

        private final RoomServiceImplBase serviceImpl;

        private final int methodId;

        private final String compression;

        MethodHandlers(RoomServiceImplBase serviceImpl, int methodId, String compression) {
            this.serviceImpl = serviceImpl;
            this.methodId = methodId;
            this.compression = compression;
        }

        @java.lang.Override
        @java.lang.SuppressWarnings("unchecked")
        public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
            switch(methodId) {
                case METHODID_CREATE_ROOM:
                    io.quarkus.grpc.stubs.ServerCalls.oneToOne((hotelmanagementsystem.infrastructure.api.grpc.generated.CreateRoomRequest) request, (io.grpc.stub.StreamObserver<hotelmanagementsystem.infrastructure.api.grpc.generated.RoomResponse>) responseObserver, compression, serviceImpl::createRoom);
                    break;
                case METHODID_GET_ROOM_BY_ID:
                    io.quarkus.grpc.stubs.ServerCalls.oneToOne((hotelmanagementsystem.infrastructure.api.grpc.generated.GetRoomByIdRequest) request, (io.grpc.stub.StreamObserver<hotelmanagementsystem.infrastructure.api.grpc.generated.RoomResponse>) responseObserver, compression, serviceImpl::getRoomById);
                    break;
                case METHODID_UPDATE_ROOM_PRICE:
                    io.quarkus.grpc.stubs.ServerCalls.oneToOne((hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateRoomPriceRequest) request, (io.grpc.stub.StreamObserver<hotelmanagementsystem.infrastructure.api.grpc.generated.RoomResponse>) responseObserver, compression, serviceImpl::updateRoomPrice);
                    break;
                case METHODID_REMOVE_ROOM:
                    io.quarkus.grpc.stubs.ServerCalls.oneToOne((hotelmanagementsystem.infrastructure.api.grpc.generated.RemoveRoomRequest) request, (io.grpc.stub.StreamObserver<hotelmanagementsystem.infrastructure.api.grpc.generated.Empty>) responseObserver, compression, serviceImpl::removeRoom);
                    break;
                default:
                    throw new java.lang.AssertionError();
            }
        }

        @java.lang.Override
        @java.lang.SuppressWarnings("unchecked")
        public io.grpc.stub.StreamObserver<Req> invoke(io.grpc.stub.StreamObserver<Resp> responseObserver) {
            switch(methodId) {
                default:
                    throw new java.lang.AssertionError();
            }
        }
    }
}
