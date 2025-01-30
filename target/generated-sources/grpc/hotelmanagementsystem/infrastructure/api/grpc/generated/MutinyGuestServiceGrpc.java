package hotelmanagementsystem.infrastructure.api.grpc.generated;

import static hotelmanagementsystem.infrastructure.api.grpc.generated.GuestServiceGrpc.getServiceDescriptor;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;

@jakarta.annotation.Generated(value = "by Mutiny Grpc generator", comments = "Source: api.proto")
public final class MutinyGuestServiceGrpc implements io.quarkus.grpc.MutinyGrpc {

    private MutinyGuestServiceGrpc() {
    }

    public static MutinyGuestServiceStub newMutinyStub(io.grpc.Channel channel) {
        return new MutinyGuestServiceStub(channel);
    }

    /**
     * <pre>
     *  Guest Service
     * </pre>
     */
    public static class MutinyGuestServiceStub extends io.grpc.stub.AbstractStub<MutinyGuestServiceStub> implements io.quarkus.grpc.MutinyStub {

        private GuestServiceGrpc.GuestServiceStub delegateStub;

        private MutinyGuestServiceStub(io.grpc.Channel channel) {
            super(channel);
            delegateStub = GuestServiceGrpc.newStub(channel);
        }

        private MutinyGuestServiceStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            super(channel, callOptions);
            delegateStub = GuestServiceGrpc.newStub(channel).build(channel, callOptions);
        }

        @Override
        protected MutinyGuestServiceStub build(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            return new MutinyGuestServiceStub(channel, callOptions);
        }

        public io.smallrye.mutiny.Uni<hotelmanagementsystem.infrastructure.api.grpc.generated.GuestResponse> createGuest(hotelmanagementsystem.infrastructure.api.grpc.generated.CreateGuestRequest request) {
            return io.quarkus.grpc.stubs.ClientCalls.oneToOne(request, delegateStub::createGuest);
        }

        public io.smallrye.mutiny.Uni<hotelmanagementsystem.infrastructure.api.grpc.generated.GuestResponse> getGuestById(hotelmanagementsystem.infrastructure.api.grpc.generated.GetGuestByIdRequest request) {
            return io.quarkus.grpc.stubs.ClientCalls.oneToOne(request, delegateStub::getGuestById);
        }

        public io.smallrye.mutiny.Uni<hotelmanagementsystem.infrastructure.api.grpc.generated.GuestResponse> updateGuestEmail(hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateGuestEmailRequest request) {
            return io.quarkus.grpc.stubs.ClientCalls.oneToOne(request, delegateStub::updateGuestEmail);
        }

        public io.smallrye.mutiny.Uni<hotelmanagementsystem.infrastructure.api.grpc.generated.GuestResponse> updateGuestPhone(hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateGuestPhoneRequest request) {
            return io.quarkus.grpc.stubs.ClientCalls.oneToOne(request, delegateStub::updateGuestPhone);
        }

        public io.smallrye.mutiny.Uni<hotelmanagementsystem.infrastructure.api.grpc.generated.GuestResponse> updateGuestLastName(hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateGuestLastNameRequest request) {
            return io.quarkus.grpc.stubs.ClientCalls.oneToOne(request, delegateStub::updateGuestLastName);
        }

        public io.smallrye.mutiny.Uni<hotelmanagementsystem.infrastructure.api.grpc.generated.GuestResponse> updateGuestTitle(hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateGuestTitleRequest request) {
            return io.quarkus.grpc.stubs.ClientCalls.oneToOne(request, delegateStub::updateGuestTitle);
        }

        public io.smallrye.mutiny.Uni<hotelmanagementsystem.infrastructure.api.grpc.generated.Empty> deleteGuest(hotelmanagementsystem.infrastructure.api.grpc.generated.DeleteGuestRequest request) {
            return io.quarkus.grpc.stubs.ClientCalls.oneToOne(request, delegateStub::deleteGuest);
        }

        public io.smallrye.mutiny.Uni<hotelmanagementsystem.infrastructure.api.grpc.generated.ListBookingsResponse> getBookingsByGuestId(hotelmanagementsystem.infrastructure.api.grpc.generated.GetBookingsByGuestIdRequest request) {
            return io.quarkus.grpc.stubs.ClientCalls.oneToOne(request, delegateStub::getBookingsByGuestId);
        }
    }

    /**
     * <pre>
     *  Guest Service
     * </pre>
     */
    public static abstract class GuestServiceImplBase implements io.grpc.BindableService {

        private String compression;

        /**
         * Set whether the server will try to use a compressed response.
         *
         * @param compression the compression, e.g {@code gzip}
         */
        public GuestServiceImplBase withCompression(String compression) {
            this.compression = compression;
            return this;
        }

        public io.smallrye.mutiny.Uni<hotelmanagementsystem.infrastructure.api.grpc.generated.GuestResponse> createGuest(hotelmanagementsystem.infrastructure.api.grpc.generated.CreateGuestRequest request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        public io.smallrye.mutiny.Uni<hotelmanagementsystem.infrastructure.api.grpc.generated.GuestResponse> getGuestById(hotelmanagementsystem.infrastructure.api.grpc.generated.GetGuestByIdRequest request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        public io.smallrye.mutiny.Uni<hotelmanagementsystem.infrastructure.api.grpc.generated.GuestResponse> updateGuestEmail(hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateGuestEmailRequest request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        public io.smallrye.mutiny.Uni<hotelmanagementsystem.infrastructure.api.grpc.generated.GuestResponse> updateGuestPhone(hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateGuestPhoneRequest request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        public io.smallrye.mutiny.Uni<hotelmanagementsystem.infrastructure.api.grpc.generated.GuestResponse> updateGuestLastName(hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateGuestLastNameRequest request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        public io.smallrye.mutiny.Uni<hotelmanagementsystem.infrastructure.api.grpc.generated.GuestResponse> updateGuestTitle(hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateGuestTitleRequest request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        public io.smallrye.mutiny.Uni<hotelmanagementsystem.infrastructure.api.grpc.generated.Empty> deleteGuest(hotelmanagementsystem.infrastructure.api.grpc.generated.DeleteGuestRequest request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        public io.smallrye.mutiny.Uni<hotelmanagementsystem.infrastructure.api.grpc.generated.ListBookingsResponse> getBookingsByGuestId(hotelmanagementsystem.infrastructure.api.grpc.generated.GetBookingsByGuestIdRequest request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        @java.lang.Override
        public io.grpc.ServerServiceDefinition bindService() {
            return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor()).addMethod(hotelmanagementsystem.infrastructure.api.grpc.generated.GuestServiceGrpc.getCreateGuestMethod(), asyncUnaryCall(new MethodHandlers<hotelmanagementsystem.infrastructure.api.grpc.generated.CreateGuestRequest, hotelmanagementsystem.infrastructure.api.grpc.generated.GuestResponse>(this, METHODID_CREATE_GUEST, compression))).addMethod(hotelmanagementsystem.infrastructure.api.grpc.generated.GuestServiceGrpc.getGetGuestByIdMethod(), asyncUnaryCall(new MethodHandlers<hotelmanagementsystem.infrastructure.api.grpc.generated.GetGuestByIdRequest, hotelmanagementsystem.infrastructure.api.grpc.generated.GuestResponse>(this, METHODID_GET_GUEST_BY_ID, compression))).addMethod(hotelmanagementsystem.infrastructure.api.grpc.generated.GuestServiceGrpc.getUpdateGuestEmailMethod(), asyncUnaryCall(new MethodHandlers<hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateGuestEmailRequest, hotelmanagementsystem.infrastructure.api.grpc.generated.GuestResponse>(this, METHODID_UPDATE_GUEST_EMAIL, compression))).addMethod(hotelmanagementsystem.infrastructure.api.grpc.generated.GuestServiceGrpc.getUpdateGuestPhoneMethod(), asyncUnaryCall(new MethodHandlers<hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateGuestPhoneRequest, hotelmanagementsystem.infrastructure.api.grpc.generated.GuestResponse>(this, METHODID_UPDATE_GUEST_PHONE, compression))).addMethod(hotelmanagementsystem.infrastructure.api.grpc.generated.GuestServiceGrpc.getUpdateGuestLastNameMethod(), asyncUnaryCall(new MethodHandlers<hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateGuestLastNameRequest, hotelmanagementsystem.infrastructure.api.grpc.generated.GuestResponse>(this, METHODID_UPDATE_GUEST_LAST_NAME, compression))).addMethod(hotelmanagementsystem.infrastructure.api.grpc.generated.GuestServiceGrpc.getUpdateGuestTitleMethod(), asyncUnaryCall(new MethodHandlers<hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateGuestTitleRequest, hotelmanagementsystem.infrastructure.api.grpc.generated.GuestResponse>(this, METHODID_UPDATE_GUEST_TITLE, compression))).addMethod(hotelmanagementsystem.infrastructure.api.grpc.generated.GuestServiceGrpc.getDeleteGuestMethod(), asyncUnaryCall(new MethodHandlers<hotelmanagementsystem.infrastructure.api.grpc.generated.DeleteGuestRequest, hotelmanagementsystem.infrastructure.api.grpc.generated.Empty>(this, METHODID_DELETE_GUEST, compression))).addMethod(hotelmanagementsystem.infrastructure.api.grpc.generated.GuestServiceGrpc.getGetBookingsByGuestIdMethod(), asyncUnaryCall(new MethodHandlers<hotelmanagementsystem.infrastructure.api.grpc.generated.GetBookingsByGuestIdRequest, hotelmanagementsystem.infrastructure.api.grpc.generated.ListBookingsResponse>(this, METHODID_GET_BOOKINGS_BY_GUEST_ID, compression))).build();
        }
    }

    private static final int METHODID_CREATE_GUEST = 0;

    private static final int METHODID_GET_GUEST_BY_ID = 1;

    private static final int METHODID_UPDATE_GUEST_EMAIL = 2;

    private static final int METHODID_UPDATE_GUEST_PHONE = 3;

    private static final int METHODID_UPDATE_GUEST_LAST_NAME = 4;

    private static final int METHODID_UPDATE_GUEST_TITLE = 5;

    private static final int METHODID_DELETE_GUEST = 6;

    private static final int METHODID_GET_BOOKINGS_BY_GUEST_ID = 7;

    private static final class MethodHandlers<Req, Resp> implements io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>, io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>, io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>, io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {

        private final GuestServiceImplBase serviceImpl;

        private final int methodId;

        private final String compression;

        MethodHandlers(GuestServiceImplBase serviceImpl, int methodId, String compression) {
            this.serviceImpl = serviceImpl;
            this.methodId = methodId;
            this.compression = compression;
        }

        @java.lang.Override
        @java.lang.SuppressWarnings("unchecked")
        public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
            switch(methodId) {
                case METHODID_CREATE_GUEST:
                    io.quarkus.grpc.stubs.ServerCalls.oneToOne((hotelmanagementsystem.infrastructure.api.grpc.generated.CreateGuestRequest) request, (io.grpc.stub.StreamObserver<hotelmanagementsystem.infrastructure.api.grpc.generated.GuestResponse>) responseObserver, compression, serviceImpl::createGuest);
                    break;
                case METHODID_GET_GUEST_BY_ID:
                    io.quarkus.grpc.stubs.ServerCalls.oneToOne((hotelmanagementsystem.infrastructure.api.grpc.generated.GetGuestByIdRequest) request, (io.grpc.stub.StreamObserver<hotelmanagementsystem.infrastructure.api.grpc.generated.GuestResponse>) responseObserver, compression, serviceImpl::getGuestById);
                    break;
                case METHODID_UPDATE_GUEST_EMAIL:
                    io.quarkus.grpc.stubs.ServerCalls.oneToOne((hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateGuestEmailRequest) request, (io.grpc.stub.StreamObserver<hotelmanagementsystem.infrastructure.api.grpc.generated.GuestResponse>) responseObserver, compression, serviceImpl::updateGuestEmail);
                    break;
                case METHODID_UPDATE_GUEST_PHONE:
                    io.quarkus.grpc.stubs.ServerCalls.oneToOne((hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateGuestPhoneRequest) request, (io.grpc.stub.StreamObserver<hotelmanagementsystem.infrastructure.api.grpc.generated.GuestResponse>) responseObserver, compression, serviceImpl::updateGuestPhone);
                    break;
                case METHODID_UPDATE_GUEST_LAST_NAME:
                    io.quarkus.grpc.stubs.ServerCalls.oneToOne((hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateGuestLastNameRequest) request, (io.grpc.stub.StreamObserver<hotelmanagementsystem.infrastructure.api.grpc.generated.GuestResponse>) responseObserver, compression, serviceImpl::updateGuestLastName);
                    break;
                case METHODID_UPDATE_GUEST_TITLE:
                    io.quarkus.grpc.stubs.ServerCalls.oneToOne((hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateGuestTitleRequest) request, (io.grpc.stub.StreamObserver<hotelmanagementsystem.infrastructure.api.grpc.generated.GuestResponse>) responseObserver, compression, serviceImpl::updateGuestTitle);
                    break;
                case METHODID_DELETE_GUEST:
                    io.quarkus.grpc.stubs.ServerCalls.oneToOne((hotelmanagementsystem.infrastructure.api.grpc.generated.DeleteGuestRequest) request, (io.grpc.stub.StreamObserver<hotelmanagementsystem.infrastructure.api.grpc.generated.Empty>) responseObserver, compression, serviceImpl::deleteGuest);
                    break;
                case METHODID_GET_BOOKINGS_BY_GUEST_ID:
                    io.quarkus.grpc.stubs.ServerCalls.oneToOne((hotelmanagementsystem.infrastructure.api.grpc.generated.GetBookingsByGuestIdRequest) request, (io.grpc.stub.StreamObserver<hotelmanagementsystem.infrastructure.api.grpc.generated.ListBookingsResponse>) responseObserver, compression, serviceImpl::getBookingsByGuestId);
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
