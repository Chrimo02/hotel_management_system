package hotelmanagementsystem.infrastructure.api.grpc.generated;

import java.util.function.BiFunction;
import io.quarkus.grpc.MutinyClient;

@jakarta.annotation.Generated(value = "by Mutiny Grpc generator", comments = "Source: api.proto")
public class GuestServiceClient implements GuestService, MutinyClient<MutinyGuestServiceGrpc.MutinyGuestServiceStub> {

    private final MutinyGuestServiceGrpc.MutinyGuestServiceStub stub;

    public GuestServiceClient(String name, io.grpc.Channel channel, BiFunction<String, MutinyGuestServiceGrpc.MutinyGuestServiceStub, MutinyGuestServiceGrpc.MutinyGuestServiceStub> stubConfigurator) {
        this.stub = stubConfigurator.apply(name, MutinyGuestServiceGrpc.newMutinyStub(channel));
    }

    private GuestServiceClient(MutinyGuestServiceGrpc.MutinyGuestServiceStub stub) {
        this.stub = stub;
    }

    public GuestServiceClient newInstanceWithStub(MutinyGuestServiceGrpc.MutinyGuestServiceStub stub) {
        return new GuestServiceClient(stub);
    }

    @Override
    public MutinyGuestServiceGrpc.MutinyGuestServiceStub getStub() {
        return stub;
    }

    @Override
    public io.smallrye.mutiny.Uni<hotelmanagementsystem.infrastructure.api.grpc.generated.GuestResponse> createGuest(hotelmanagementsystem.infrastructure.api.grpc.generated.CreateGuestRequest request) {
        return stub.createGuest(request);
    }

    @Override
    public io.smallrye.mutiny.Uni<hotelmanagementsystem.infrastructure.api.grpc.generated.GuestResponse> getGuestById(hotelmanagementsystem.infrastructure.api.grpc.generated.GetGuestByIdRequest request) {
        return stub.getGuestById(request);
    }

    @Override
    public io.smallrye.mutiny.Uni<hotelmanagementsystem.infrastructure.api.grpc.generated.GuestResponse> updateGuestEmail(hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateGuestEmailRequest request) {
        return stub.updateGuestEmail(request);
    }

    @Override
    public io.smallrye.mutiny.Uni<hotelmanagementsystem.infrastructure.api.grpc.generated.GuestResponse> updateGuestPhone(hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateGuestPhoneRequest request) {
        return stub.updateGuestPhone(request);
    }

    @Override
    public io.smallrye.mutiny.Uni<hotelmanagementsystem.infrastructure.api.grpc.generated.GuestResponse> updateGuestLastName(hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateGuestLastNameRequest request) {
        return stub.updateGuestLastName(request);
    }

    @Override
    public io.smallrye.mutiny.Uni<hotelmanagementsystem.infrastructure.api.grpc.generated.GuestResponse> updateGuestTitle(hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateGuestTitleRequest request) {
        return stub.updateGuestTitle(request);
    }

    @Override
    public io.smallrye.mutiny.Uni<hotelmanagementsystem.infrastructure.api.grpc.generated.Empty> deleteGuest(hotelmanagementsystem.infrastructure.api.grpc.generated.DeleteGuestRequest request) {
        return stub.deleteGuest(request);
    }

    @Override
    public io.smallrye.mutiny.Uni<hotelmanagementsystem.infrastructure.api.grpc.generated.ListBookingsResponse> getBookingsByGuestId(hotelmanagementsystem.infrastructure.api.grpc.generated.GetBookingsByGuestIdRequest request) {
        return stub.getBookingsByGuestId(request);
    }
}
