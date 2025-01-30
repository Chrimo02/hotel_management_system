package hotelmanagementsystem.infrastructure.api.grpc.generated;

import static hotelmanagementsystem.infrastructure.api.grpc.generated.BookingServiceGrpc.getServiceDescriptor;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;

@jakarta.annotation.Generated(value = "by Mutiny Grpc generator", comments = "Source: api.proto")
public final class MutinyBookingServiceGrpc implements io.quarkus.grpc.MutinyGrpc {

    private MutinyBookingServiceGrpc() {
    }

    public static MutinyBookingServiceStub newMutinyStub(io.grpc.Channel channel) {
        return new MutinyBookingServiceStub(channel);
    }

    /**
     * <pre>
     *  Booking Service
     * </pre>
     */
    public static class MutinyBookingServiceStub extends io.grpc.stub.AbstractStub<MutinyBookingServiceStub> implements io.quarkus.grpc.MutinyStub {

        private BookingServiceGrpc.BookingServiceStub delegateStub;

        private MutinyBookingServiceStub(io.grpc.Channel channel) {
            super(channel);
            delegateStub = BookingServiceGrpc.newStub(channel);
        }

        private MutinyBookingServiceStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            super(channel, callOptions);
            delegateStub = BookingServiceGrpc.newStub(channel).build(channel, callOptions);
        }

        @Override
        protected MutinyBookingServiceStub build(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            return new MutinyBookingServiceStub(channel, callOptions);
        }

        public io.smallrye.mutiny.Uni<hotelmanagementsystem.infrastructure.api.grpc.generated.BookingResponse> createBooking(hotelmanagementsystem.infrastructure.api.grpc.generated.CreateBookingRequest request) {
            return io.quarkus.grpc.stubs.ClientCalls.oneToOne(request, delegateStub::createBooking);
        }

        public io.smallrye.mutiny.Uni<hotelmanagementsystem.infrastructure.api.grpc.generated.BookingResponse> getBookingById(hotelmanagementsystem.infrastructure.api.grpc.generated.GetBookingByIdRequest request) {
            return io.quarkus.grpc.stubs.ClientCalls.oneToOne(request, delegateStub::getBookingById);
        }

        public io.smallrye.mutiny.Uni<hotelmanagementsystem.infrastructure.api.grpc.generated.Empty> cancelBooking(hotelmanagementsystem.infrastructure.api.grpc.generated.CancelBookingRequest request) {
            return io.quarkus.grpc.stubs.ClientCalls.oneToOne(request, delegateStub::cancelBooking);
        }

        public io.smallrye.mutiny.Uni<hotelmanagementsystem.infrastructure.api.grpc.generated.Empty> checkInGuest(hotelmanagementsystem.infrastructure.api.grpc.generated.CheckInGuestRequest request) {
            return io.quarkus.grpc.stubs.ClientCalls.oneToOne(request, delegateStub::checkInGuest);
        }

        public io.smallrye.mutiny.Uni<hotelmanagementsystem.infrastructure.api.grpc.generated.Empty> checkOutGuest(hotelmanagementsystem.infrastructure.api.grpc.generated.CheckOutGuestRequest request) {
            return io.quarkus.grpc.stubs.ClientCalls.oneToOne(request, delegateStub::checkOutGuest);
        }
    }

    /**
     * <pre>
     *  Booking Service
     * </pre>
     */
    public static abstract class BookingServiceImplBase implements io.grpc.BindableService {

        private String compression;

        /**
         * Set whether the server will try to use a compressed response.
         *
         * @param compression the compression, e.g {@code gzip}
         */
        public BookingServiceImplBase withCompression(String compression) {
            this.compression = compression;
            return this;
        }

        public io.smallrye.mutiny.Uni<hotelmanagementsystem.infrastructure.api.grpc.generated.BookingResponse> createBooking(hotelmanagementsystem.infrastructure.api.grpc.generated.CreateBookingRequest request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        public io.smallrye.mutiny.Uni<hotelmanagementsystem.infrastructure.api.grpc.generated.BookingResponse> getBookingById(hotelmanagementsystem.infrastructure.api.grpc.generated.GetBookingByIdRequest request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        public io.smallrye.mutiny.Uni<hotelmanagementsystem.infrastructure.api.grpc.generated.Empty> cancelBooking(hotelmanagementsystem.infrastructure.api.grpc.generated.CancelBookingRequest request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        public io.smallrye.mutiny.Uni<hotelmanagementsystem.infrastructure.api.grpc.generated.Empty> checkInGuest(hotelmanagementsystem.infrastructure.api.grpc.generated.CheckInGuestRequest request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        public io.smallrye.mutiny.Uni<hotelmanagementsystem.infrastructure.api.grpc.generated.Empty> checkOutGuest(hotelmanagementsystem.infrastructure.api.grpc.generated.CheckOutGuestRequest request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        @java.lang.Override
        public io.grpc.ServerServiceDefinition bindService() {
            return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor()).addMethod(hotelmanagementsystem.infrastructure.api.grpc.generated.BookingServiceGrpc.getCreateBookingMethod(), asyncUnaryCall(new MethodHandlers<hotelmanagementsystem.infrastructure.api.grpc.generated.CreateBookingRequest, hotelmanagementsystem.infrastructure.api.grpc.generated.BookingResponse>(this, METHODID_CREATE_BOOKING, compression))).addMethod(hotelmanagementsystem.infrastructure.api.grpc.generated.BookingServiceGrpc.getGetBookingByIdMethod(), asyncUnaryCall(new MethodHandlers<hotelmanagementsystem.infrastructure.api.grpc.generated.GetBookingByIdRequest, hotelmanagementsystem.infrastructure.api.grpc.generated.BookingResponse>(this, METHODID_GET_BOOKING_BY_ID, compression))).addMethod(hotelmanagementsystem.infrastructure.api.grpc.generated.BookingServiceGrpc.getCancelBookingMethod(), asyncUnaryCall(new MethodHandlers<hotelmanagementsystem.infrastructure.api.grpc.generated.CancelBookingRequest, hotelmanagementsystem.infrastructure.api.grpc.generated.Empty>(this, METHODID_CANCEL_BOOKING, compression))).addMethod(hotelmanagementsystem.infrastructure.api.grpc.generated.BookingServiceGrpc.getCheckInGuestMethod(), asyncUnaryCall(new MethodHandlers<hotelmanagementsystem.infrastructure.api.grpc.generated.CheckInGuestRequest, hotelmanagementsystem.infrastructure.api.grpc.generated.Empty>(this, METHODID_CHECK_IN_GUEST, compression))).addMethod(hotelmanagementsystem.infrastructure.api.grpc.generated.BookingServiceGrpc.getCheckOutGuestMethod(), asyncUnaryCall(new MethodHandlers<hotelmanagementsystem.infrastructure.api.grpc.generated.CheckOutGuestRequest, hotelmanagementsystem.infrastructure.api.grpc.generated.Empty>(this, METHODID_CHECK_OUT_GUEST, compression))).build();
        }
    }

    private static final int METHODID_CREATE_BOOKING = 0;

    private static final int METHODID_GET_BOOKING_BY_ID = 1;

    private static final int METHODID_CANCEL_BOOKING = 2;

    private static final int METHODID_CHECK_IN_GUEST = 3;

    private static final int METHODID_CHECK_OUT_GUEST = 4;

    private static final class MethodHandlers<Req, Resp> implements io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>, io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>, io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>, io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {

        private final BookingServiceImplBase serviceImpl;

        private final int methodId;

        private final String compression;

        MethodHandlers(BookingServiceImplBase serviceImpl, int methodId, String compression) {
            this.serviceImpl = serviceImpl;
            this.methodId = methodId;
            this.compression = compression;
        }

        @java.lang.Override
        @java.lang.SuppressWarnings("unchecked")
        public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
            switch(methodId) {
                case METHODID_CREATE_BOOKING:
                    io.quarkus.grpc.stubs.ServerCalls.oneToOne((hotelmanagementsystem.infrastructure.api.grpc.generated.CreateBookingRequest) request, (io.grpc.stub.StreamObserver<hotelmanagementsystem.infrastructure.api.grpc.generated.BookingResponse>) responseObserver, compression, serviceImpl::createBooking);
                    break;
                case METHODID_GET_BOOKING_BY_ID:
                    io.quarkus.grpc.stubs.ServerCalls.oneToOne((hotelmanagementsystem.infrastructure.api.grpc.generated.GetBookingByIdRequest) request, (io.grpc.stub.StreamObserver<hotelmanagementsystem.infrastructure.api.grpc.generated.BookingResponse>) responseObserver, compression, serviceImpl::getBookingById);
                    break;
                case METHODID_CANCEL_BOOKING:
                    io.quarkus.grpc.stubs.ServerCalls.oneToOne((hotelmanagementsystem.infrastructure.api.grpc.generated.CancelBookingRequest) request, (io.grpc.stub.StreamObserver<hotelmanagementsystem.infrastructure.api.grpc.generated.Empty>) responseObserver, compression, serviceImpl::cancelBooking);
                    break;
                case METHODID_CHECK_IN_GUEST:
                    io.quarkus.grpc.stubs.ServerCalls.oneToOne((hotelmanagementsystem.infrastructure.api.grpc.generated.CheckInGuestRequest) request, (io.grpc.stub.StreamObserver<hotelmanagementsystem.infrastructure.api.grpc.generated.Empty>) responseObserver, compression, serviceImpl::checkInGuest);
                    break;
                case METHODID_CHECK_OUT_GUEST:
                    io.quarkus.grpc.stubs.ServerCalls.oneToOne((hotelmanagementsystem.infrastructure.api.grpc.generated.CheckOutGuestRequest) request, (io.grpc.stub.StreamObserver<hotelmanagementsystem.infrastructure.api.grpc.generated.Empty>) responseObserver, compression, serviceImpl::checkOutGuest);
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
