package hotelmanagementsystem.infrastructure.api.grpc.client;

import hotelmanagementsystem.infrastructure.api.grpc.generated.Hotel;
import hotelmanagementsystem.infrastructure.api.grpc.generated.HotelServiceGrpc;
import hotelmanagementsystem.infrastructure.api.grpc.generated.ListHotelsRequest;
import hotelmanagementsystem.infrastructure.api.grpc.generated.ListHotelsResponse;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class TestClient {
    public static void main(String[] args) {
        // 1) Channel aufbauen
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost", 8888) // ggf. richtigen Port anpassen
                .usePlaintext()
                .build();

        // 2) Stub erzeugen (blocking oder async)
        HotelServiceGrpc.HotelServiceBlockingStub hotelStub =
                HotelServiceGrpc.newBlockingStub(channel);

        // 3) Request bauen (Filter/Paging)
        ListHotelsRequest request = ListHotelsRequest.newBuilder()
                .setFilterCity("")  // kein City-Filter
                .setMinRating(0.0)  // kein Min-Rating-Filter
                .setPageNumber(1)   // 1. Seite
                .setPageSize(5)     // nur 5 Hotels pro Seite
                .build();

        // 4) RPC aufrufen
        ListHotelsResponse response = hotelStub.listHotels(request);

        // 5) Ergebnis inspizieren
        System.out.println("Received totalCount: " + response.getTotalCount());
        for (Hotel h : response.getHotelsList()) {
            System.out.println("Hotel ID: " + h.getId()
                    + ", Name: " + h.getName()
                    + ", City: " + h.getLocation().getCity()
                    + ", Rating: " + h.getAverageRating());
        }

        // 6) Channel schließen
        channel.shutdown();
    }
}

