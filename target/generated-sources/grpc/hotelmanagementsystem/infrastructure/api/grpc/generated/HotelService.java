package hotelmanagementsystem.infrastructure.api.grpc.generated;

import io.quarkus.grpc.MutinyService;

@jakarta.annotation.Generated(value = "by Mutiny Grpc generator", comments = "Source: api.proto")
public interface HotelService extends MutinyService {

    io.smallrye.mutiny.Uni<hotelmanagementsystem.infrastructure.api.grpc.generated.HotelResponse> createHotel(hotelmanagementsystem.infrastructure.api.grpc.generated.CreateHotelRequest request);

    io.smallrye.mutiny.Uni<hotelmanagementsystem.infrastructure.api.grpc.generated.HotelResponse> getHotelById(hotelmanagementsystem.infrastructure.api.grpc.generated.GetHotelByIdRequest request);

    io.smallrye.mutiny.Uni<hotelmanagementsystem.infrastructure.api.grpc.generated.ListHotelsResponse> listHotels(hotelmanagementsystem.infrastructure.api.grpc.generated.ListHotelsRequest request);

    io.smallrye.mutiny.Uni<hotelmanagementsystem.infrastructure.api.grpc.generated.HotelResponse> updateHotel(hotelmanagementsystem.infrastructure.api.grpc.generated.UpdateHotelRequest request);

    io.smallrye.mutiny.Uni<hotelmanagementsystem.infrastructure.api.grpc.generated.Empty> deleteHotel(hotelmanagementsystem.infrastructure.api.grpc.generated.DeleteHotelRequest request);

    io.smallrye.mutiny.Uni<hotelmanagementsystem.infrastructure.api.grpc.generated.Empty> rateHotel(hotelmanagementsystem.infrastructure.api.grpc.generated.RateHotelRequest request);

    io.smallrye.mutiny.Uni<hotelmanagementsystem.infrastructure.api.grpc.generated.ListRoomsResponse> findAvailableRooms(hotelmanagementsystem.infrastructure.api.grpc.generated.FindAvailableRoomsRequest request);
}
