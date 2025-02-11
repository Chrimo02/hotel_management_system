package hotelmanagementsystem.infrastructure.api.grpc.client;

import hotelmanagementsystem.infrastructure.api.grpc.generated.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TestClient {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost", 8080)
                .usePlaintext()
                .build();

        HotelServiceGrpc.HotelServiceBlockingStub hotelStub = HotelServiceGrpc.newBlockingStub(channel);
        GuestServiceGrpc.GuestServiceBlockingStub guestStub = GuestServiceGrpc.newBlockingStub(channel);
        BookingServiceGrpc.BookingServiceBlockingStub bookingStub = BookingServiceGrpc.newBlockingStub(channel);
        RoomServiceGrpc.RoomServiceBlockingStub roomStub = RoomServiceGrpc.newBlockingStub(channel);

        while (true) {
            System.out.println("\n--- Hotel Management System ---");
            System.out.println("1. Guest Operations");
            System.out.println("2. Hotel Operations");
            System.out.println("3. Booking Operations");
            System.out.println("4. Room Operations");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1 -> guestOperations(guestStub);
                case 2 -> hotelOperations(hotelStub);
                case 3 -> bookingOperations(bookingStub);
                case 4 -> roomOperations(roomStub);
                case 5 -> {
                    channel.shutdown();
                    System.out.println("Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid option! Try again.");
            }
        }
    }

    // --- Guest Operations ---
    private static void guestOperations(GuestServiceGrpc.GuestServiceBlockingStub guestStub) {
        System.out.println("\n--- Guest Operations ---");
        System.out.println("1. Create Guest");
        System.out.println("2. Get Guest By ID");
        System.out.println("3. Update Guest Email");
        System.out.println("4. Update Guest Phone");
        System.out.println("5. Update Guest Last Name");
        System.out.println("6. Update Guest Title");
        System.out.println("7. Delete Guest");
        System.out.println("8. Get Bookings By Guest ID");
        System.out.print("Choose an option: ");
        int choice = Integer.parseInt(scanner.nextLine());

        switch (choice) {
            case 1 -> createGuest(guestStub);
            case 2 -> getGuestById(guestStub);
            case 3 -> updateGuestEmail(guestStub);
            case 4 -> updateGuestPhone(guestStub);
            case 5 -> updateGuestLastName(guestStub);
            case 6 -> updateGuestTitle(guestStub);
            case 7 -> deleteGuest(guestStub);
            case 8 -> getBookingsByGuestId(guestStub);
            default -> System.out.println("Invalid option!");
        }
    }

    private static void createGuest(GuestServiceGrpc.GuestServiceBlockingStub guestStub) {
        System.out.print("First Name: ");
        String firstName = scanner.nextLine();
        System.out.print("Last Name: ");
        String lastName = scanner.nextLine();
        System.out.print("Birthday (YYYY-MM-DD): ");
        String birthday = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Phone Number: ");
        String phone = scanner.nextLine();

        CreateGuestRequest request = CreateGuestRequest.newBuilder()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setBirthday(birthday)
                .setEmail(email)
                .setPhoneNumber(phone)
                .build();

        GuestResponse response = guestStub.createGuest(request);
        System.out.println("Guest created: " + response.getGuest());
    }

    private static void getGuestById(GuestServiceGrpc.GuestServiceBlockingStub guestStub) {
        System.out.print("Enter Guest ID: ");
        long id = Long.parseLong(scanner.nextLine());

        GetGuestByIdRequest request = GetGuestByIdRequest.newBuilder().setId(id).build();
        GuestResponse response = guestStub.getGuestById(request);
        System.out.println("Guest: " + response.getGuest());
    }

    private static void updateGuestEmail(GuestServiceGrpc.GuestServiceBlockingStub guestStub) {
        System.out.print("Enter Guest ID: ");
        long id = Long.parseLong(scanner.nextLine());
        System.out.print("Enter New Email: ");
        String email = scanner.nextLine();

        UpdateGuestEmailRequest request = UpdateGuestEmailRequest.newBuilder()
                .setGuestId(id)
                .setNewEmail(email)
                .build();

        GuestResponse response = guestStub.updateGuestEmail(request);
        System.out.println("Updated Guest: " + response.getGuest());
    }

    private static void updateGuestPhone(GuestServiceGrpc.GuestServiceBlockingStub guestStub) {
        System.out.print("Enter Guest ID: ");
        long id = Long.parseLong(scanner.nextLine());
        System.out.print("Enter New Phone: ");
        String phone = scanner.nextLine();

        UpdateGuestPhoneRequest request = UpdateGuestPhoneRequest.newBuilder()
                .setGuestId(id)
                .setNewPhone(phone)
                .build();

        GuestResponse response = guestStub.updateGuestPhone(request);
        System.out.println("Updated Guest: " + response.getGuest());
    }

    private static void updateGuestLastName(GuestServiceGrpc.GuestServiceBlockingStub guestStub) {
        System.out.print("Enter Guest ID: ");
        long id = Long.parseLong(scanner.nextLine());
        System.out.print("Enter New Last Name: ");
        String lastName = scanner.nextLine();

        UpdateGuestLastNameRequest request = UpdateGuestLastNameRequest.newBuilder()
                .setGuestId(id)
                .setNewLastName(lastName)
                .build();

        GuestResponse response = guestStub.updateGuestLastName(request);
        System.out.println("Updated Guest: " + response.getGuest());
    }

    private static void updateGuestTitle(GuestServiceGrpc.GuestServiceBlockingStub guestStub) {
        System.out.print("Enter Guest ID: ");
        long id = Long.parseLong(scanner.nextLine());
        System.out.print("Enter New Title: ");
        String title = scanner.nextLine();

        UpdateGuestTitleRequest request = UpdateGuestTitleRequest.newBuilder()
                .setGuestId(id)
                .setNewTitle(title)
                .build();

        GuestResponse response = guestStub.updateGuestTitle(request);
        System.out.println("Updated Guest: " + response.getGuest());
    }

    private static void deleteGuest(GuestServiceGrpc.GuestServiceBlockingStub guestStub) {
        System.out.print("Enter Guest ID: ");
        long id = Long.parseLong(scanner.nextLine());

        DeleteGuestRequest request = DeleteGuestRequest.newBuilder().setId(id).build();
        guestStub.deleteGuest(request);
        System.out.println("Guest deleted.");
    }

    private static void getBookingsByGuestId(GuestServiceGrpc.GuestServiceBlockingStub guestStub) {
        System.out.print("Enter Guest ID: ");
        long guestId = Long.parseLong(scanner.nextLine());

        GetBookingsByGuestIdRequest request = GetBookingsByGuestIdRequest.newBuilder().setGuestId(guestId).build();
        ListBookingsResponse response = guestStub.getBookingsByGuestId(request);
        System.out.println("Bookings: " + response.getBookingsList());
    }
    // --- Hotel Operations ---
    private static void hotelOperations(HotelServiceGrpc.HotelServiceBlockingStub hotelStub) {
        System.out.println("\n--- Hotel Operations ---");
        System.out.println("1. Create Hotel");
        System.out.println("2. Get Hotel By ID");
        System.out.println("3. List Hotels");
        System.out.println("4. Update Hotel");
        System.out.println("5. Delete Hotel");
        System.out.println("6. Rate Hotel");
        System.out.println("7. Find Available Rooms");
        System.out.print("Choose an option: ");
        int choice = Integer.parseInt(scanner.nextLine());

        switch (choice) {
            case 1 -> createHotel(hotelStub);
            case 2 -> getHotelById(hotelStub);
            case 3 -> listHotels(hotelStub);
            case 4 -> updateHotel(hotelStub);
            case 5 -> deleteHotel(hotelStub);
            case 6 -> rateHotel(hotelStub);
            case 7 -> findAvailableRooms(hotelStub);
            default -> System.out.println("Invalid option!");
        }
    }

    private static void createHotel(HotelServiceGrpc.HotelServiceBlockingStub hotelStub) {
        System.out.print("Hotel Name: ");
        String name = scanner.nextLine();
        System.out.print("Description: ");
        String description = scanner.nextLine();
        System.out.print("City: ");
        String city = scanner.nextLine();
        System.out.print("Country: ");
        String country = scanner.nextLine();
        System.out.print("Address: ");
        String address = scanner.nextLine();

        HotelLocation location = HotelLocation.newBuilder()
                .setCity(city)
                .setCountry(country)
                .setAddress(address)
                .build();

        CreateHotelRequest request = CreateHotelRequest.newBuilder()
                .setName(name)
                .setDescription(description)
                .setLocation(location)
                .build();

        HotelResponse response = hotelStub.createHotel(request);
        System.out.println("Hotel created: " + response.getHotel());
    }

    private static void getHotelById(HotelServiceGrpc.HotelServiceBlockingStub hotelStub) {
        System.out.print("Enter Hotel ID: ");
        long id = Long.parseLong(scanner.nextLine());

        GetHotelByIdRequest request = GetHotelByIdRequest.newBuilder().setId(id).build();
        HotelResponse response = hotelStub.getHotelById(request);
        System.out.println("Hotel: " + response.getHotel());
    }

    private static void listHotels(HotelServiceGrpc.HotelServiceBlockingStub hotelStub) {
        System.out.print("Filter by City (leave empty for all): ");
        String filterCity = scanner.nextLine();

        System.out.print("Minimum Rating (0 for no filter): ");
        double minRating = Double.parseDouble(scanner.nextLine());

        System.out.print("Page Number: ");
        int pageNumber = Integer.parseInt(scanner.nextLine());

        System.out.print("Page Size: ");
        int pageSize = Integer.parseInt(scanner.nextLine());

        ListHotelsRequest request = ListHotelsRequest.newBuilder()
                .setFilterCity(filterCity)
                .setMinRating(minRating)
                .setPageNumber(pageNumber)
                .setPageSize(pageSize)
                .build();

        ListHotelsResponse response = hotelStub.listHotels(request);
        System.out.println("Hotels: " + response.getHotelsList());
    }

    private static void updateHotel(HotelServiceGrpc.HotelServiceBlockingStub hotelStub) {
        System.out.print("Enter Hotel ID: ");
        long id = Long.parseLong(scanner.nextLine());

        System.out.print("New Hotel Name: ");
        String name = scanner.nextLine();

        System.out.print("New Description: ");
        String description = scanner.nextLine();

        System.out.print("New City: ");
        String city = scanner.nextLine();

        System.out.print("New Country: ");
        String country = scanner.nextLine();

        System.out.print("New Address: ");
        String address = scanner.nextLine();

        HotelLocation location = HotelLocation.newBuilder()
                .setCity(city)
                .setCountry(country)
                .setAddress(address)
                .build();

        UpdateHotelRequest request = UpdateHotelRequest.newBuilder()
                .setId(id)
                .setName(name)
                .setDescription(description)
                .setLocation(location)
                .build();

        HotelResponse response = hotelStub.updateHotel(request);
        System.out.println("Updated Hotel: " + response.getHotel());
    }

    private static void deleteHotel(HotelServiceGrpc.HotelServiceBlockingStub hotelStub) {
        System.out.print("Enter Hotel ID: ");
        long id = Long.parseLong(scanner.nextLine());

        DeleteHotelRequest request = DeleteHotelRequest.newBuilder().setId(id).build();
        hotelStub.deleteHotel(request);
        System.out.println("Hotel deleted.");
    }

    private static void rateHotel(HotelServiceGrpc.HotelServiceBlockingStub hotelStub) {
        System.out.print("Enter Hotel ID: ");
        long hotelId = Long.parseLong(scanner.nextLine());

        System.out.print("Enter Guest ID: ");
        long guestId = Long.parseLong(scanner.nextLine());

        System.out.print("Enter Rating (1-5): ");
        int rating = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter Comment: ");
        String comment = scanner.nextLine();

        RateHotelRequest request = RateHotelRequest.newBuilder()
                .setHotelId(hotelId)
                .setGuestId(guestId)
                .setRating(rating)
                .setComment(comment)
                .build();

        hotelStub.rateHotel(request);
        System.out.println("Hotel rated successfully.");
    }

    private static void findAvailableRooms(HotelServiceGrpc.HotelServiceBlockingStub hotelStub) {
        System.out.print("Enter Hotel ID: ");
        long hotelId = Long.parseLong(scanner.nextLine());

        System.out.print("Enter Room Type (or leave empty for all): ");
        String roomType = scanner.nextLine();

        System.out.print("Enter Check-In Date (YYYY-MM-DD): ");
        String checkInDate = scanner.nextLine();

        System.out.print("Enter Check-Out Date (YYYY-MM-DD): ");
        String checkOutDate = scanner.nextLine();

        FindAvailableRoomsRequest request = FindAvailableRoomsRequest.newBuilder()
                .setHotelId(hotelId)
                .setRoomType(roomType)
                .setCheckInDate(checkInDate)
                .setCheckOutDate(checkOutDate)
                .build();

        ListRoomsResponse response = hotelStub.findAvailableRooms(request);
        System.out.println("Available Rooms: " + response.getRoomsList());
    }
    private static void bookingOperations(BookingServiceGrpc.BookingServiceBlockingStub bookingStub) {
        System.out.println("\n--- Booking Operations ---");
        System.out.println("1. Create Booking");
        System.out.println("2. Get Booking By ID");
        System.out.println("3. Cancel Booking");
        System.out.println("4. Check-in Guest");
        System.out.println("5. Check-out Guest");
        System.out.print("Choose an option: ");
        int choice = Integer.parseInt(scanner.nextLine());

        switch (choice) {
            case 1 -> createBooking(bookingStub);
            case 2 -> getBookingById(bookingStub);
            case 3 -> cancelBooking(bookingStub);
            case 4 -> checkInGuest(bookingStub);
            case 5 -> checkOutGuest(bookingStub);
            default -> System.out.println("Invalid option!");
        }
    }

    private static void createBooking(BookingServiceGrpc.BookingServiceBlockingStub bookingStub) {
        System.out.print("Enter Hotel ID: ");
        long hotelId = Long.parseLong(scanner.nextLine());

        System.out.print("Enter number of guests: ");
        int guestCount = Integer.parseInt(scanner.nextLine());

        List<Long> guestIds = new ArrayList<>();
        for (int i = 0; i < guestCount; i++) {
            System.out.print("Enter Guest ID #" + (i + 1) + ": ");
            guestIds.add(Long.parseLong(scanner.nextLine()));
        }

        System.out.print("Enter number of room types: ");
        int roomTypeCount = Integer.parseInt(scanner.nextLine());

        List<String> roomTypes = new ArrayList<>();
        for (int i = 0; i < roomTypeCount; i++) {
            System.out.print("Enter Room Type #" + (i + 1) + " (e.g., SINGLE, DOUBLE): ");
            roomTypes.add(scanner.nextLine());
        }

        System.out.print("Enter Check-in Date (YYYY-MM-DD): ");
        String checkInDate = scanner.nextLine();

        System.out.print("Enter Check-out Date (YYYY-MM-DD): ");
        String checkOutDate = scanner.nextLine();

        CreateBookingRequest request = CreateBookingRequest.newBuilder()
                .setHotelId(hotelId)
                .addAllGuestIds(guestIds)
                .addAllRoomTypes(roomTypes)
                .setCheckInDate(checkInDate)
                .setCheckOutDate(checkOutDate)
                .build();

        BookingResponse response = bookingStub.createBooking(request);
        System.out.println("Booking created: " + response.getBooking());
    }

    private static void getBookingById(BookingServiceGrpc.BookingServiceBlockingStub bookingStub) {
        System.out.print("Enter Booking ID: ");
        long bookingId = Long.parseLong(scanner.nextLine());

        GetBookingByIdRequest request = GetBookingByIdRequest.newBuilder().setId(bookingId).build();
        BookingResponse response = bookingStub.getBookingById(request);
        System.out.println("Booking Details: " + response.getBooking());
    }

    private static void cancelBooking(BookingServiceGrpc.BookingServiceBlockingStub bookingStub) {
        System.out.print("Enter Booking ID: ");
        long bookingId = Long.parseLong(scanner.nextLine());

        CancelBookingRequest request = CancelBookingRequest.newBuilder().setId(bookingId).build();
        bookingStub.cancelBooking(request);
        System.out.println("Booking canceled successfully.");
    }

    private static void checkInGuest(BookingServiceGrpc.BookingServiceBlockingStub bookingStub) {
        System.out.print("Enter Booking ID: ");
        long bookingId = Long.parseLong(scanner.nextLine());

        CheckInGuestRequest request = CheckInGuestRequest.newBuilder().setBookingId(bookingId).build();
        bookingStub.checkInGuest(request);
        System.out.println("Guest checked in successfully.");
    }

    private static void checkOutGuest(BookingServiceGrpc.BookingServiceBlockingStub bookingStub) {
        System.out.print("Enter Booking ID: ");
        long bookingId = Long.parseLong(scanner.nextLine());

        CheckOutGuestRequest request = CheckOutGuestRequest.newBuilder().setBookingId(bookingId).build();
        bookingStub.checkOutGuest(request);
        System.out.println("Guest checked out successfully.");
    }
    private static void roomOperations(RoomServiceGrpc.RoomServiceBlockingStub roomStub) {
        System.out.println("\n--- Room Operations ---");
        System.out.println("1. Create Room");
        System.out.println("2. Get Room By ID");
        System.out.println("3. Update Room Price");
        System.out.println("4. Remove Room");
        System.out.print("Choose an option: ");
        int choice = Integer.parseInt(scanner.nextLine());

        switch (choice) {
            case 1 -> createRoom(roomStub);
            case 2 -> getRoomById(roomStub);
            case 3 -> updateRoomPrice(roomStub);
            case 4 -> removeRoom(roomStub);
            default -> System.out.println("Invalid option!");
        }
    }

    private static void createRoom(RoomServiceGrpc.RoomServiceBlockingStub roomStub) {
        System.out.print("Enter Hotel ID: ");
        long hotelId = Long.parseLong(scanner.nextLine());

        System.out.print("Enter Room Type (e.g., SINGLE, DOUBLE): ");
        String type = scanner.nextLine();

        System.out.print("Enter Price Per Night: ");
        double price = Double.parseDouble(scanner.nextLine());

        System.out.print("Enter Building: ");
        String building = scanner.nextLine();

        System.out.print("Enter Floor: ");
        int floor = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter Room Number: ");
        String roomNumber = scanner.nextLine();

        RoomIdentifier roomIdentifier = RoomIdentifier.newBuilder()
                .setBuilding(building)
                .setFloor(floor)
                .setRoomNumber(roomNumber)
                .build();

        CreateRoomRequest request = CreateRoomRequest.newBuilder()
                .setHotelId(hotelId)
                .setType(type)
                .setPricePerNight(price)
                .setRoomIdentifier(roomIdentifier)
                .build();

        RoomResponse response = roomStub.createRoom(request);
        System.out.println("Room created: " + response.getRoom());
    }

    private static void getRoomById(RoomServiceGrpc.RoomServiceBlockingStub roomStub) {
        System.out.print("Enter Room ID: ");
        long id = Long.parseLong(scanner.nextLine());

        GetRoomByIdRequest request = GetRoomByIdRequest.newBuilder().setId(id).build();
        RoomResponse response = roomStub.getRoomById(request);
        System.out.println("Room: " + response.getRoom());
    }

    private static void updateRoomPrice(RoomServiceGrpc.RoomServiceBlockingStub roomStub) {
        System.out.print("Enter Room ID: ");
        long id = Long.parseLong(scanner.nextLine());

        System.out.print("Enter New Price Per Night: ");
        double newPrice = Double.parseDouble(scanner.nextLine());

        UpdateRoomPriceRequest request = UpdateRoomPriceRequest.newBuilder()
                .setRoomId(id)
                .setNewPricePerNight(newPrice)
                .build();

        RoomResponse response = roomStub.updateRoomPrice(request);
        System.out.println("Updated Room: " + response.getRoom());
    }

    private static void removeRoom(RoomServiceGrpc.RoomServiceBlockingStub roomStub) {
        System.out.print("Enter Room ID: ");
        long id = Long.parseLong(scanner.nextLine());

        RemoveRoomRequest request = RemoveRoomRequest.newBuilder().setRoomId(id).build();
        roomStub.removeRoom(request);
        System.out.println("Room removed successfully.");
    }


}
