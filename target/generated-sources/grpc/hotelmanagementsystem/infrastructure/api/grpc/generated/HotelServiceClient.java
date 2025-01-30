package hotelmanagementsystem.infrastructure.api.grpc.generated;

import java.util.function.BiFunction;
import io.quarkus.grpc.MutinyClient;

@jakarta.annotation.Generated(value = "by Mutiny Grpc generator", comments = "Source: api.proto")
public class HotelServiceClient implements HotelService, MutinyClient<MutinyHotelServiceGrpc.MutinyHotelServiceStub> {

    private final MutinyHotelServiceGrpc.MutinyHotelServiceStub stub;

    public HotelServiceClient(String name, io.grpc.Channel channel, BiFunction<String, MutinyHotelServiceGrpc.MutinyHotelServiceStub, MutinyHotelServiceGrpc.MutinyHotelServiceStub> stubConfigurator) {
        this.stub = stubConfigurator.apply(name, MutinyHotelServiceGrpc.newMutinyStub(channel));
    }

    private HotelServiceClient(MutinyHotelServiceGrpc.MutinyHotelServiceStub stub) {
        this.stub = stub;
    }

    public HotelServiceClient newInstanceWithStub(MutinyHotelServiceGrpc.MutinyHotelServiceStub stub) {
        return new HotelServiceClient(stub);
    }

    @Override
    public MutinyHotelServiceGrpc.MutinyHotelServiceStub getStub() {
        return stub;
    }

    @Override
    public io.smallrye.mutiny.Uni<hotelmanagementsystem.infrastructure.api.grpc.generated.HotelResponse> createHotel(hotelmanagementsystem.infrastructure.api.grpc.generated.CreateHotelRequest request) {
        return stub.createHotel(request);
    }

    @Override
    public io.smallrye.mutiny.Uni<hotelmanagementsystem.infrastructure.api.grpc.generated.HotelResponse> getHotelById(hotelmanagementsystem.infrastructure.api.grpc.generated.GetHotelByIdRequest request) {
        return stub.getHotelById(request);
    }

    @Override
    public io.smallrye.mutiny.Uni<hotelmanagementsystem.infrastructure.api.grpc.generated.ListHotelsResponse> listHotels(hotelmanagementsystem.infrastructure.api.grpc.generated.ListHotelsRequest request) {
        return stub.listHotels(request);
    }

    @Override
    public io.smallrye.mutiny.Uni<hotelmanagementsystem.infrastructure.api.grpc.generated.HotelResponse> updateHotel(hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateHotelRequest request) {
        return stub.updateHotel(request);
    }

    @Override
    public io.smallrye.mutiny.Uni<hotelmanagementsystem.infrastructure.api.grpc.generated.Empty> deleteHotel(hotelmanagementsystem.infrastructure.api.grpc.generated.DeleteHotelRequest request) {
        return stub.deleteHotel(request);
    }

    @Override
    public io.smallrye.mutiny.Uni<hotelmanagementsystem.infrastructure.api.grpc.generated.Empty> rateHotel(hotelmanagementsystem.infrastructure.api.grpc.generated.RateHotelRequest request) {
        return stub.rateHotel(request);
    }

    @Override
    public io.smallrye.mutiny.Uni<hotelmanagementsystem.infrastructure.api.grpc.generated.ListRoomsResponse> findAvailableRooms(hotelmanagementsystem.infrastructure.api.grpc.generated.FindAvailableRoomsRequest request) {
        return stub.findAvailableRooms(request);
    }
}
