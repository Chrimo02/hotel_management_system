package hotelmanagementsystem.infrastructure.api.grpc.generated;

import java.util.function.BiFunction;
import io.quarkus.grpc.MutinyClient;

@jakarta.annotation.Generated(value = "by Mutiny Grpc generator", comments = "Source: api.proto")
public class BookingServiceClient implements BookingService, MutinyClient<MutinyBookingServiceGrpc.MutinyBookingServiceStub> {

    private final MutinyBookingServiceGrpc.MutinyBookingServiceStub stub;

    public BookingServiceClient(String name, io.grpc.Channel channel, BiFunction<String, MutinyBookingServiceGrpc.MutinyBookingServiceStub, MutinyBookingServiceGrpc.MutinyBookingServiceStub> stubConfigurator) {
        this.stub = stubConfigurator.apply(name, MutinyBookingServiceGrpc.newMutinyStub(channel));
    }

    private BookingServiceClient(MutinyBookingServiceGrpc.MutinyBookingServiceStub stub) {
        this.stub = stub;
    }

    public BookingServiceClient newInstanceWithStub(MutinyBookingServiceGrpc.MutinyBookingServiceStub stub) {
        return new BookingServiceClient(stub);
    }

    @Override
    public MutinyBookingServiceGrpc.MutinyBookingServiceStub getStub() {
        return stub;
    }

    @Override
    public io.smallrye.mutiny.Uni<hotelmanagementsystem.infrastructure.api.grpc.generated.BookingResponse> createBooking(hotelmanagementsystem.infrastructure.api.grpc.generated.CreateBookingRequest request) {
        return stub.createBooking(request);
    }

    @Override
    public io.smallrye.mutiny.Uni<hotelmanagementsystem.infrastructure.api.grpc.generated.BookingResponse> getBookingById(hotelmanagementsystem.infrastructure.api.grpc.generated.GetBookingByIdRequest request) {
        return stub.getBookingById(request);
    }

    @Override
    public io.smallrye.mutiny.Uni<hotelmanagementsystem.infrastructure.api.grpc.generated.Empty> cancelBooking(hotelmanagementsystem.infrastructure.api.grpc.generated.CancelBookingRequest request) {
        return stub.cancelBooking(request);
    }

    @Override
    public io.smallrye.mutiny.Uni<hotelmanagementsystem.infrastructure.api.grpc.generated.Empty> checkInGuest(hotelmanagementsystem.infrastructure.api.grpc.generated.CheckInGuestRequest request) {
        return stub.checkInGuest(request);
    }

    @Override
    public io.smallrye.mutiny.Uni<hotelmanagementsystem.infrastructure.api.grpc.generated.Empty> checkOutGuest(hotelmanagementsystem.infrastructure.api.grpc.generated.CheckOutGuestRequest request) {
        return stub.checkOutGuest(request);
    }
}
