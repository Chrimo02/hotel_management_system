package hotelmanagementsystem.infrastructure.api.grpc.client;

import hotelmanagementsystem.infrastructure.api.grpc.generated.*;
import io.quarkus.grpc.GrpcClient;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

/**
 * HotelClient - Calls the HotelService gRPC methods.
 */
@ApplicationScoped
public class HotelClient {

    @Inject
    @GrpcClient("hotel")
    HotelServiceGrpc.HotelServiceBlockingStub hotelStub;

    public HotelResponse createHotel(CreateHotelRequest request) {
        return hotelStub.createHotel(request);
    }

    public HotelResponse getHotelById(GetHotelByIdRequest request) {
        return hotelStub.getHotelById(request);
    }

    public ListHotelsResponse listHotels(ListHotelsRequest request) {
        return hotelStub.listHotels(request);
    }

    public HotelResponse updateHotel(UpdateHotelRequest request) {
        return hotelStub.updateHotel(request);
    }

    public void deleteHotel(DeleteHotelRequest request) {
        hotelStub.deleteHotel(request);
    }

    public void rateHotel(RateHotelRequest request) {
        hotelStub.rateHotel(request);
    }

    public ListRoomsResponse findAvailableRooms(FindAvailableRoomsRequest request) {
        return hotelStub.findAvailableRooms(request);
    }
}
