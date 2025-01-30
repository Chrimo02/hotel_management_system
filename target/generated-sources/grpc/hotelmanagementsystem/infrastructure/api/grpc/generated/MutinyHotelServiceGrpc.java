package hotelmanagementsystem.infrastructure.api.grpc.generated;

import static hotelmanagementsystem.infrastructure.api.grpc.generated.HotelServiceGrpc.getServiceDescriptor;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;

@jakarta.annotation.Generated(value = "by Mutiny Grpc generator", comments = "Source: api.proto")
public final class MutinyHotelServiceGrpc implements io.quarkus.grpc.MutinyGrpc {

    private MutinyHotelServiceGrpc() {
    }

    public static MutinyHotelServiceStub newMutinyStub(io.grpc.Channel channel) {
        return new MutinyHotelServiceStub(channel);
    }

    /**
     * <pre>
     *  Hotel Service
     * </pre>
     */
    public static class MutinyHotelServiceStub extends io.grpc.stub.AbstractStub<MutinyHotelServiceStub> implements io.quarkus.grpc.MutinyStub {

        private HotelServiceGrpc.HotelServiceStub delegateStub;

        private MutinyHotelServiceStub(io.grpc.Channel channel) {
            super(channel);
            delegateStub = HotelServiceGrpc.newStub(channel);
        }

        private MutinyHotelServiceStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            super(channel, callOptions);
            delegateStub = HotelServiceGrpc.newStub(channel).build(channel, callOptions);
        }

        @Override
        protected MutinyHotelServiceStub build(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            return new MutinyHotelServiceStub(channel, callOptions);
        }

        public io.smallrye.mutiny.Uni<hotelmanagementsystem.infrastructure.api.grpc.generated.HotelResponse> createHotel(hotelmanagementsystem.infrastructure.api.grpc.generated.CreateHotelRequest request) {
            return io.quarkus.grpc.stubs.ClientCalls.oneToOne(request, delegateStub::createHotel);
        }

        public io.smallrye.mutiny.Uni<hotelmanagementsystem.infrastructure.api.grpc.generated.HotelResponse> getHotelById(hotelmanagementsystem.infrastructure.api.grpc.generated.GetHotelByIdRequest request) {
            return io.quarkus.grpc.stubs.ClientCalls.oneToOne(request, delegateStub::getHotelById);
        }

        public io.smallrye.mutiny.Uni<hotelmanagementsystem.infrastructure.api.grpc.generated.ListHotelsResponse> listHotels(hotelmanagementsystem.infrastructure.api.grpc.generated.ListHotelsRequest request) {
            return io.quarkus.grpc.stubs.ClientCalls.oneToOne(request, delegateStub::listHotels);
        }

        public io.smallrye.mutiny.Uni<hotelmanagementsystem.infrastructure.api.grpc.generated.HotelResponse> updateHotel(hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateHotelRequest request) {
            return io.quarkus.grpc.stubs.ClientCalls.oneToOne(request, delegateStub::updateHotel);
        }

        public io.smallrye.mutiny.Uni<hotelmanagementsystem.infrastructure.api.grpc.generated.Empty> deleteHotel(hotelmanagementsystem.infrastructure.api.grpc.generated.DeleteHotelRequest request) {
            return io.quarkus.grpc.stubs.ClientCalls.oneToOne(request, delegateStub::deleteHotel);
        }

        public io.smallrye.mutiny.Uni<hotelmanagementsystem.infrastructure.api.grpc.generated.Empty> rateHotel(hotelmanagementsystem.infrastructure.api.grpc.generated.RateHotelRequest request) {
            return io.quarkus.grpc.stubs.ClientCalls.oneToOne(request, delegateStub::rateHotel);
        }

        public io.smallrye.mutiny.Uni<hotelmanagementsystem.infrastructure.api.grpc.generated.ListRoomsResponse> findAvailableRooms(hotelmanagementsystem.infrastructure.api.grpc.generated.FindAvailableRoomsRequest request) {
            return io.quarkus.grpc.stubs.ClientCalls.oneToOne(request, delegateStub::findAvailableRooms);
        }
    }

    /**
     * <pre>
     *  Hotel Service
     * </pre>
     */
    public static abstract class HotelServiceImplBase implements io.grpc.BindableService {

        private String compression;

        /**
         * Set whether the server will try to use a compressed response.
         *
         * @param compression the compression, e.g {@code gzip}
         */
        public HotelServiceImplBase withCompression(String compression) {
            this.compression = compression;
            return this;
        }

        public io.smallrye.mutiny.Uni<hotelmanagementsystem.infrastructure.api.grpc.generated.HotelResponse> createHotel(hotelmanagementsystem.infrastructure.api.grpc.generated.CreateHotelRequest request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        public io.smallrye.mutiny.Uni<hotelmanagementsystem.infrastructure.api.grpc.generated.HotelResponse> getHotelById(hotelmanagementsystem.infrastructure.api.grpc.generated.GetHotelByIdRequest request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        public io.smallrye.mutiny.Uni<hotelmanagementsystem.infrastructure.api.grpc.generated.ListHotelsResponse> listHotels(hotelmanagementsystem.infrastructure.api.grpc.generated.ListHotelsRequest request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        public io.smallrye.mutiny.Uni<hotelmanagementsystem.infrastructure.api.grpc.generated.HotelResponse> updateHotel(hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateHotelRequest request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        public io.smallrye.mutiny.Uni<hotelmanagementsystem.infrastructure.api.grpc.generated.Empty> deleteHotel(hotelmanagementsystem.infrastructure.api.grpc.generated.DeleteHotelRequest request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        public io.smallrye.mutiny.Uni<hotelmanagementsystem.infrastructure.api.grpc.generated.Empty> rateHotel(hotelmanagementsystem.infrastructure.api.grpc.generated.RateHotelRequest request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        public io.smallrye.mutiny.Uni<hotelmanagementsystem.infrastructure.api.grpc.generated.ListRoomsResponse> findAvailableRooms(hotelmanagementsystem.infrastructure.api.grpc.generated.FindAvailableRoomsRequest request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        @java.lang.Override
        public io.grpc.ServerServiceDefinition bindService() {
            return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor()).addMethod(hotelmanagementsystem.infrastructure.api.grpc.generated.HotelServiceGrpc.getCreateHotelMethod(), asyncUnaryCall(new MethodHandlers<hotelmanagementsystem.infrastructure.api.grpc.generated.CreateHotelRequest, hotelmanagementsystem.infrastructure.api.grpc.generated.HotelResponse>(this, METHODID_CREATE_HOTEL, compression))).addMethod(hotelmanagementsystem.infrastructure.api.grpc.generated.HotelServiceGrpc.getGetHotelByIdMethod(), asyncUnaryCall(new MethodHandlers<hotelmanagementsystem.infrastructure.api.grpc.generated.GetHotelByIdRequest, hotelmanagementsystem.infrastructure.api.grpc.generated.HotelResponse>(this, METHODID_GET_HOTEL_BY_ID, compression))).addMethod(hotelmanagementsystem.infrastructure.api.grpc.generated.HotelServiceGrpc.getListHotelsMethod(), asyncUnaryCall(new MethodHandlers<hotelmanagementsystem.infrastructure.api.grpc.generated.ListHotelsRequest, hotelmanagementsystem.infrastructure.api.grpc.generated.ListHotelsResponse>(this, METHODID_LIST_HOTELS, compression))).addMethod(hotelmanagementsystem.infrastructure.api.grpc.generated.HotelServiceGrpc.getUpdateHotelMethod(), asyncUnaryCall(new MethodHandlers<hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateHotelRequest, hotelmanagementsystem.infrastructure.api.grpc.generated.HotelResponse>(this, METHODID_UPDATE_HOTEL, compression))).addMethod(hotelmanagementsystem.infrastructure.api.grpc.generated.HotelServiceGrpc.getDeleteHotelMethod(), asyncUnaryCall(new MethodHandlers<hotelmanagementsystem.infrastructure.api.grpc.generated.DeleteHotelRequest, hotelmanagementsystem.infrastructure.api.grpc.generated.Empty>(this, METHODID_DELETE_HOTEL, compression))).addMethod(hotelmanagementsystem.infrastructure.api.grpc.generated.HotelServiceGrpc.getRateHotelMethod(), asyncUnaryCall(new MethodHandlers<hotelmanagementsystem.infrastructure.api.grpc.generated.RateHotelRequest, hotelmanagementsystem.infrastructure.api.grpc.generated.Empty>(this, METHODID_RATE_HOTEL, compression))).addMethod(hotelmanagementsystem.infrastructure.api.grpc.generated.HotelServiceGrpc.getFindAvailableRoomsMethod(), asyncUnaryCall(new MethodHandlers<hotelmanagementsystem.infrastructure.api.grpc.generated.FindAvailableRoomsRequest, hotelmanagementsystem.infrastructure.api.grpc.generated.ListRoomsResponse>(this, METHODID_FIND_AVAILABLE_ROOMS, compression))).build();
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

        private final HotelServiceImplBase serviceImpl;

        private final int methodId;

        private final String compression;

        MethodHandlers(HotelServiceImplBase serviceImpl, int methodId, String compression) {
            this.serviceImpl = serviceImpl;
            this.methodId = methodId;
            this.compression = compression;
        }

        @java.lang.Override
        @java.lang.SuppressWarnings("unchecked")
        public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
            switch(methodId) {
                case METHODID_CREATE_HOTEL:
                    io.quarkus.grpc.stubs.ServerCalls.oneToOne((hotelmanagementsystem.infrastructure.api.grpc.generated.CreateHotelRequest) request, (io.grpc.stub.StreamObserver<hotelmanagementsystem.infrastructure.api.grpc.generated.HotelResponse>) responseObserver, compression, serviceImpl::createHotel);
                    break;
                case METHODID_GET_HOTEL_BY_ID:
                    io.quarkus.grpc.stubs.ServerCalls.oneToOne((hotelmanagementsystem.infrastructure.api.grpc.generated.GetHotelByIdRequest) request, (io.grpc.stub.StreamObserver<hotelmanagementsystem.infrastructure.api.grpc.generated.HotelResponse>) responseObserver, compression, serviceImpl::getHotelById);
                    break;
                case METHODID_LIST_HOTELS:
                    io.quarkus.grpc.stubs.ServerCalls.oneToOne((hotelmanagementsystem.infrastructure.api.grpc.generated.ListHotelsRequest) request, (io.grpc.stub.StreamObserver<hotelmanagementsystem.infrastructure.api.grpc.generated.ListHotelsResponse>) responseObserver, compression, serviceImpl::listHotels);
                    break;
                case METHODID_UPDATE_HOTEL:
                    io.quarkus.grpc.stubs.ServerCalls.oneToOne((hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateHotelRequest) request, (io.grpc.stub.StreamObserver<hotelmanagementsystem.infrastructure.api.grpc.generated.HotelResponse>) responseObserver, compression, serviceImpl::updateHotel);
                    break;
                case METHODID_DELETE_HOTEL:
                    io.quarkus.grpc.stubs.ServerCalls.oneToOne((hotelmanagementsystem.infrastructure.api.grpc.generated.DeleteHotelRequest) request, (io.grpc.stub.StreamObserver<hotelmanagementsystem.infrastructure.api.grpc.generated.Empty>) responseObserver, compression, serviceImpl::deleteHotel);
                    break;
                case METHODID_RATE_HOTEL:
                    io.quarkus.grpc.stubs.ServerCalls.oneToOne((hotelmanagementsystem.infrastructure.api.grpc.generated.RateHotelRequest) request, (io.grpc.stub.StreamObserver<hotelmanagementsystem.infrastructure.api.grpc.generated.Empty>) responseObserver, compression, serviceImpl::rateHotel);
                    break;
                case METHODID_FIND_AVAILABLE_ROOMS:
                    io.quarkus.grpc.stubs.ServerCalls.oneToOne((hotelmanagementsystem.infrastructure.api.grpc.generated.FindAvailableRoomsRequest) request, (io.grpc.stub.StreamObserver<hotelmanagementsystem.infrastructure.api.grpc.generated.ListRoomsResponse>) responseObserver, compression, serviceImpl::findAvailableRooms);
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
