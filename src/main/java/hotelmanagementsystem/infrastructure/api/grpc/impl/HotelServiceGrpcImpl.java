package hotelmanagementsystem.infrastructure.api.grpc.impl;
import hotelmanagementsystem.infrastructure.api.grpc.generated.*;
import io.grpc.stub.StreamObserver;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class HotelServiceGrpcImpl  extends HotelServiceGrpc.HotelServiceImplBase
{
    @Override
    public void createHotel(CreateHotelRequest request, StreamObserver<HotelResponse> responseObserver) {
        super.createHotel(request, responseObserver);
    }

    @Override
    public void getHotelById(GetHotelByIdRequest request, StreamObserver<HotelResponse> responseObserver) {
        super.getHotelById(request, responseObserver);
    }

    @Override
    public void listHotels(ListHotelsRequest request, StreamObserver<ListHotelsResponse> responseObserver) {
        super.listHotels(request, responseObserver);
    }

    @Override
    public void updateHotel(UpdateHotelRequest request, StreamObserver<HotelResponse> responseObserver) {
        super.updateHotel(request, responseObserver);
    }

    @Override
    public void deleteHotel(DeleteHotelRequest request, StreamObserver<Empty> responseObserver) {
        super.deleteHotel(request, responseObserver);
    }

    @Override
    public void rateHotel(RateHotelRequest request, StreamObserver<Empty> responseObserver) {
        super.rateHotel(request, responseObserver);
    }

    @Override
    public void findAvailableRooms(FindAvailableRoomsRequest request, StreamObserver<ListRoomsResponse> responseObserver) {
        super.findAvailableRooms(request, responseObserver);
    }
}
