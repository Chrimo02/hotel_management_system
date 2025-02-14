package hotelmanagementsystem.cli;

import hotelmanagementsystem.infrastructure.api.grpc.generated.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.quarkus.runtime.annotations.QuarkusMain;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//@QuarkusMain
public class HotelManagementCLI {

    private static final Scanner scanner = new Scanner(System.in);
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_CYAN = "\u001B[36m";

    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost", 8080)
                .usePlaintext()
                .build();

        HotelServiceGrpc.HotelServiceBlockingStub hotelStub = HotelServiceGrpc.newBlockingStub(channel);
        GuestServiceGrpc.GuestServiceBlockingStub guestStub = GuestServiceGrpc.newBlockingStub(channel);
        BookingServiceGrpc.BookingServiceBlockingStub bookingStub = BookingServiceGrpc.newBlockingStub(channel);
        RoomServiceGrpc.RoomServiceBlockingStub roomStub = RoomServiceGrpc.newBlockingStub(channel);

        boolean exit = false;
        while (!exit) {
            printMainMenu();
            String input = scanner.nextLine();
            switch (input) {
                case "1":
                    guestOperations(guestStub);
                    break;
                case "2":
                    hotelOperations(hotelStub);
                    break;
                case "3":
                    bookingOperations(bookingStub);
                    break;
                case "4":
                    roomOperations(roomStub);
                    break;
                case "5":
                    System.out.println(ANSI_GREEN + "Goodbye!" + ANSI_RESET);
                    exit = true;
                    break;
                default:
                    System.out.println(ANSI_RED + "Invalid option! Please try again." + ANSI_RESET);
            }
        }
        channel.shutdown();
    }

    private static void printMainMenu() {
        System.out.println(ANSI_CYAN + "\n=== Hotel Management System ===" + ANSI_RESET);
        System.out.println("1. Guest Operations");
        System.out.println("2. Hotel Operations");
        System.out.println("3. Booking Operations");
        System.out.println("4. Room Operations");
        System.out.println("5. Exit");
        System.out.print("Choose an option: ");
    }

    // ---------- Guest Operations ----------
    private static void guestOperations(GuestServiceGrpc.GuestServiceBlockingStub guestStub) {
        boolean back = false;
        while (!back) {
            System.out.println(ANSI_CYAN + "\n--- Guest Operations ---" + ANSI_RESET);
            System.out.println("0. Go Back");
            System.out.println("1. Create Guest");
            System.out.println("2. Get Guest By ID");
            System.out.println("3. Update Guest Email");
            System.out.println("4. Update Guest Phone");
            System.out.println("5. Update Guest Last Name");
            System.out.println("6. Delete Guest");
            System.out.println("7. Get Bookings By Guest ID");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine();
            switch (choice) {
                case "0":
                    back = true;
                    break;
                case "1":
                    createGuest(guestStub);
                    break;
                case "2":
                    getGuestById(guestStub);
                    break;
                case "3":
                    updateGuestEmail(guestStub);
                    break;
                case "4":
                    updateGuestPhone(guestStub);
                    break;
                case "5":
                    updateGuestLastName(guestStub);
                    break;
                case "6":
                    deleteGuest(guestStub);
                    break;
                case "7":
                    getBookingsByGuestId(guestStub);
                    break;
                default:
                    System.out.println(ANSI_RED + "Invalid option!" + ANSI_RESET);
            }
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

        try {
            GuestResponse response = guestStub.createGuest(request);
            System.out.println(ANSI_GREEN + "Guest created:" + ANSI_RESET);
            printGuest(response.getGuest());
        } catch (Exception e) {
            System.out.println(ANSI_RED + "Error: " + e.getMessage() + ANSI_RESET);
        }
    }

    private static void getGuestById(GuestServiceGrpc.GuestServiceBlockingStub guestStub) {
        System.out.print("Enter Guest ID: ");
        long id = Long.parseLong(scanner.nextLine());
        GetGuestByIdRequest request = GetGuestByIdRequest.newBuilder().setId(id).build();
        try {
            GuestResponse response = guestStub.getGuestById(request);
            System.out.println(ANSI_GREEN + "Guest:" + ANSI_RESET);
            printGuest(response.getGuest());
        } catch (Exception e) {
            System.out.println(ANSI_RED + "Error: " + e.getMessage() + ANSI_RESET);
        }
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
        try {
            GuestResponse response = guestStub.updateGuestEmail(request);
            System.out.println(ANSI_GREEN + "Updated Guest:" + ANSI_RESET);
            printGuest(response.getGuest());
        } catch (Exception e) {
            System.out.println(ANSI_RED + "Error: " + e.getMessage() + ANSI_RESET);
        }
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
        try {
            GuestResponse response = guestStub.updateGuestPhone(request);
            System.out.println(ANSI_GREEN + "Updated Guest:" + ANSI_RESET);
            printGuest(response.getGuest());
        } catch (Exception e) {
            System.out.println(ANSI_RED + "Error: " + e.getMessage() + ANSI_RESET);
        }
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
        try {
            GuestResponse response = guestStub.updateGuestLastName(request);
            System.out.println(ANSI_GREEN + "Updated Guest:" + ANSI_RESET);
            printGuest(response.getGuest());
        } catch (Exception e) {
            System.out.println(ANSI_RED + "Error: " + e.getMessage() + ANSI_RESET);
        }
    }

    private static void deleteGuest(GuestServiceGrpc.GuestServiceBlockingStub guestStub) {
        System.out.print("Enter Guest ID: ");
        long id = Long.parseLong(scanner.nextLine());
        DeleteGuestRequest request = DeleteGuestRequest.newBuilder().setId(id).build();
        try {
            guestStub.deleteGuest(request);
            System.out.println(ANSI_GREEN + "Guest deleted." + ANSI_RESET);
        } catch (Exception e) {
            System.out.println(ANSI_RED + "Error: " + e.getMessage() + ANSI_RESET);
        }
    }

    private static void getBookingsByGuestId(GuestServiceGrpc.GuestServiceBlockingStub guestStub) {
        System.out.print("Enter Guest ID: ");
        long guestId = Long.parseLong(scanner.nextLine());
        GetBookingsByGuestIdRequest request = GetBookingsByGuestIdRequest.newBuilder().setGuestId(guestId).build();
        try {
            ListBookingsResponse response = guestStub.getBookingsByGuestId(request);
            System.out.println(ANSI_GREEN + "Bookings for Guest ID " + guestId + ":" + ANSI_RESET);
            if (response.getBookingsCount() == 0) {
                System.out.println("No bookings found.");
            } else {
                for (Booking booking : response.getBookingsList()) {
                    printBooking(booking);
                    System.out.println("-------------------------------");
                }
            }
        } catch (Exception e) {
            System.out.println(ANSI_RED + "Error: " + e.getMessage() + ANSI_RESET);
        }
    }

    // ---------- Hotel Operations ----------
    private static void hotelOperations(HotelServiceGrpc.HotelServiceBlockingStub hotelStub) {
        boolean back = false;
        while (!back) {
            System.out.println(ANSI_CYAN + "\n--- Hotel Operations ---" + ANSI_RESET);
            System.out.println("0. Go Back");
            System.out.println("1. Create Hotel");
            System.out.println("2. Get Hotel By ID");
            System.out.println("3. List Hotels");
            System.out.println("4. Update Hotel");
            System.out.println("5. Delete Hotel");
            System.out.println("6. Rate Hotel");
            System.out.println("7. Find Available Rooms");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine();
            switch (choice) {
                case "0":
                    back = true;
                    break;
                case "1":
                    createHotel(hotelStub);
                    break;
                case "2":
                    getHotelById(hotelStub);
                    break;
                case "3":
                    listHotels(hotelStub);
                    break;
                case "4":
                    updateHotel(hotelStub);
                    break;
                case "5":
                    deleteHotel(hotelStub);
                    break;
                case "6":
                    rateHotel(hotelStub);
                    break;
                case "7":
                    findAvailableRooms(hotelStub);
                    break;
                default:
                    System.out.println(ANSI_RED + "Invalid option!" + ANSI_RESET);
            }
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

        try {
            HotelResponse response = hotelStub.createHotel(request);
            System.out.println(ANSI_GREEN + "Hotel created:" + ANSI_RESET);
            printHotel(response.getHotel());
        } catch (Exception e) {
            System.out.println(ANSI_RED + "Error: " + e.getMessage() + ANSI_RESET);
        }
    }

    private static void getHotelById(HotelServiceGrpc.HotelServiceBlockingStub hotelStub) {
        System.out.print("Enter Hotel ID: ");
        long id = Long.parseLong(scanner.nextLine());
        GetHotelByIdRequest request = GetHotelByIdRequest.newBuilder().setId(id).build();
        try {
            HotelResponse response = hotelStub.getHotelById(request);
            System.out.println(ANSI_GREEN + "Hotel:" + ANSI_RESET);
            printHotel(response.getHotel());
        } catch (Exception e) {
            System.out.println(ANSI_RED + "Error: " + e.getMessage() + ANSI_RESET);
        }
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
        try {
            ListHotelsResponse response = hotelStub.listHotels(request);
            System.out.println(ANSI_GREEN + "Hotels:" + ANSI_RESET);
            for (Hotel hotel : response.getHotelsList()) {
                printHotel(hotel);
                System.out.println("-------------------------------");
            }
            System.out.println("Total Count: " + response.getTotalCount());
            System.out.println("Page: " + response.getPageNumber() + " of size " + response.getPageSize());
        } catch (Exception e) {
            System.out.println(ANSI_RED + "Error: " + e.getMessage() + ANSI_RESET);
        }
    }

    private static void updateHotel(HotelServiceGrpc.HotelServiceBlockingStub hotelStub) {
        System.out.print("Enter Hotel ID: ");
        long id = Long.parseLong(scanner.nextLine());
        System.out.print("New Hotel Name (leave empty if no change): ");
        String name = scanner.nextLine();
        System.out.print("New Description (leave empty if no change): ");
        String description = scanner.nextLine();

        UpdateHotelRequest request = UpdateHotelRequest.newBuilder()
                .setId(id)
                .setName(name)
                .setDescription(description)
                .build();
        try {
            HotelResponse response = hotelStub.updateHotel(request);
            System.out.println(ANSI_GREEN + "Updated Hotel:" + ANSI_RESET);
            printHotel(response.getHotel());
        } catch (Exception e) {
            System.out.println(ANSI_RED + "Error: " + e.getMessage() + ANSI_RESET);
        }
    }

    private static void deleteHotel(HotelServiceGrpc.HotelServiceBlockingStub hotelStub) {
        System.out.print("Enter Hotel ID: ");
        long id = Long.parseLong(scanner.nextLine());
        DeleteHotelRequest request = DeleteHotelRequest.newBuilder().setId(id).build();
        try {
            hotelStub.deleteHotel(request);
            System.out.println(ANSI_GREEN + "Hotel deleted." + ANSI_RESET);
        } catch (Exception e) {
            System.out.println(ANSI_RED + "Error: " + e.getMessage() + ANSI_RESET);
        }
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
        try {
            hotelStub.rateHotel(request);
            System.out.println(ANSI_GREEN + "Hotel rated successfully." + ANSI_RESET);
        } catch (Exception e) {
            System.out.println(ANSI_RED + "Error: " + e.getMessage() + ANSI_RESET);
        }
    }

    private static void findAvailableRooms(HotelServiceGrpc.HotelServiceBlockingStub hotelStub) {
        System.out.print("Enter Hotel ID: ");
        long hotelId = Long.parseLong(scanner.nextLine());
        System.out.print("Enter Room Type (SINGLE or DOUBLE, leave empty for all): ");
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
        try {
            ListRoomsResponse response = hotelStub.findAvailableRooms(request);
            System.out.println(ANSI_GREEN + "Available Rooms:" + ANSI_RESET);
            if (response.getRoomsCount() == 0) {
                System.out.println("No available rooms found.");
            } else {
                for (Room room : response.getRoomsList()) {
                    printRoom(room);
                    System.out.println("-------------------------------");
                }
            }
        } catch (Exception e) {
            System.out.println(ANSI_RED + "Error: " + e.getMessage() + ANSI_RESET);
        }
    }

    // ---------- Booking Operations ----------
    private static void bookingOperations(BookingServiceGrpc.BookingServiceBlockingStub bookingStub) {
        boolean back = false;
        while (!back) {
            System.out.println(ANSI_CYAN + "\n--- Booking Operations ---" + ANSI_RESET);
            System.out.println("0. Go Back");
            System.out.println("1. Create Booking");
            System.out.println("2. Get Booking By ID");
            System.out.println("3. Cancel Booking");
            System.out.println("4. Check-in Guest");
            System.out.println("5. Check-out Guest");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine();
            switch (choice) {
                case "0":
                    back = true;
                    break;
                case "1":
                    createBooking(bookingStub);
                    break;
                case "2":
                    getBookingById(bookingStub);
                    break;
                case "3":
                    cancelBooking(bookingStub);
                    break;
                case "4":
                    checkInGuest(bookingStub);
                    break;
                case "5":
                    checkOutGuest(bookingStub);
                    break;
                default:
                    System.out.println(ANSI_RED + "Invalid option!" + ANSI_RESET);
            }
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
        try {
            BookingResponse response = bookingStub.createBooking(request);
            System.out.println(ANSI_GREEN + "Booking created:" + ANSI_RESET);
            printBooking(response.getBooking());
        } catch (Exception e) {
            System.out.println(ANSI_RED + "Error: " + e.getMessage() + ANSI_RESET);
        }
    }

    private static void getBookingById(BookingServiceGrpc.BookingServiceBlockingStub bookingStub) {
        System.out.print("Enter Booking ID: ");
        long bookingId = Long.parseLong(scanner.nextLine());
        GetBookingByIdRequest request = GetBookingByIdRequest.newBuilder().setId(bookingId).build();
        try {
            BookingResponse response = bookingStub.getBookingById(request);
            System.out.println(ANSI_GREEN + "Booking Details:" + ANSI_RESET);
            printBooking(response.getBooking());
        } catch (Exception e) {
            System.out.println(ANSI_RED + "Error: " + e.getMessage() + ANSI_RESET);
        }
    }

    private static void cancelBooking(BookingServiceGrpc.BookingServiceBlockingStub bookingStub) {
        System.out.print("Enter Booking ID: ");
        long bookingId = Long.parseLong(scanner.nextLine());
        CancelBookingRequest request = CancelBookingRequest.newBuilder().setId(bookingId).build();
        try {
            bookingStub.cancelBooking(request);
            System.out.println(ANSI_GREEN + "Booking canceled successfully." + ANSI_RESET);
        } catch (Exception e) {
            System.out.println(ANSI_RED + "Error: " + e.getMessage() + ANSI_RESET);
        }
    }

    private static void checkInGuest(BookingServiceGrpc.BookingServiceBlockingStub bookingStub) {
        System.out.print("Enter Booking ID: ");
        long bookingId = Long.parseLong(scanner.nextLine());
        CheckInGuestRequest request = CheckInGuestRequest.newBuilder().setBookingId(bookingId).build();
        try {
            bookingStub.checkInGuest(request);
            System.out.println(ANSI_GREEN + "Guest checked in successfully." + ANSI_RESET);
        } catch (Exception e) {
            System.out.println(ANSI_RED + "Error: " + e.getMessage() + ANSI_RESET);
        }
    }

    private static void checkOutGuest(BookingServiceGrpc.BookingServiceBlockingStub bookingStub) {
        System.out.print("Enter Booking ID: ");
        long bookingId = Long.parseLong(scanner.nextLine());
        CheckOutGuestRequest request = CheckOutGuestRequest.newBuilder().setBookingId(bookingId).build();
        try {
            bookingStub.checkOutGuest(request);
            System.out.println(ANSI_GREEN + "Guest checked out successfully." + ANSI_RESET);
        } catch (Exception e) {
            System.out.println(ANSI_RED + "Error: " + e.getMessage() + ANSI_RESET);
        }
    }

    // ---------- Room Operations ----------
    private static void roomOperations(RoomServiceGrpc.RoomServiceBlockingStub roomStub) {
        boolean back = false;
        while (!back) {
            System.out.println(ANSI_CYAN + "\n--- Room Operations ---" + ANSI_RESET);
            System.out.println("0. Go Back");
            System.out.println("1. Create Room");
            System.out.println("2. Get Room By ID");
            System.out.println("3. Update Room Price");
            System.out.println("4. Remove Room");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine();
            switch (choice) {
                case "0":
                    back = true;
                    break;
                case "1":
                    createRoom(roomStub);
                    break;
                case "2":
                    getRoomById(roomStub);
                    break;
                case "3":
                    updateRoomPrice(roomStub);
                    break;
                case "4":
                    removeRoom(roomStub);
                    break;
                default:
                    System.out.println(ANSI_RED + "Invalid option!" + ANSI_RESET);
            }
        }
    }

    private static void createRoom(RoomServiceGrpc.RoomServiceBlockingStub roomStub) {
        System.out.print("Enter Hotel ID: ");
        long hotelId = Long.parseLong(scanner.nextLine());
        System.out.print("Enter Room Type (SINGLE or DOUBLE): ");
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
        try {
            RoomResponse response = roomStub.createRoom(request);
            System.out.println(ANSI_GREEN + "Room created:" + ANSI_RESET);
            printRoom(response.getRoom());
        } catch (Exception e) {
            System.out.println(ANSI_RED + "Error: " + e.getMessage() + ANSI_RESET);
        }
    }

    private static void getRoomById(RoomServiceGrpc.RoomServiceBlockingStub roomStub) {
        System.out.print("Enter Room ID: ");
        long id = Long.parseLong(scanner.nextLine());
        GetRoomByIdRequest request = GetRoomByIdRequest.newBuilder().setId(id).build();
        try {
            RoomResponse response = roomStub.getRoomById(request);
            System.out.println(ANSI_GREEN + "Room:" + ANSI_RESET);
            printRoom(response.getRoom());
        } catch (Exception e) {
            System.out.println(ANSI_RED + "Error: " + e.getMessage() + ANSI_RESET);
        }
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
        try {
            RoomResponse response = roomStub.updateRoomPrice(request);
            System.out.println(ANSI_GREEN + "Updated Room:" + ANSI_RESET);
            printRoom(response.getRoom());
        } catch (Exception e) {
            System.out.println(ANSI_RED + "Error: " + e.getMessage() + ANSI_RESET);
        }
    }

    private static void removeRoom(RoomServiceGrpc.RoomServiceBlockingStub roomStub) {
        System.out.print("Enter Room ID: ");
        long id = Long.parseLong(scanner.nextLine());
        RemoveRoomRequest request = RemoveRoomRequest.newBuilder().setRoomId(id).build();
        try {
            roomStub.removeRoom(request);
            System.out.println(ANSI_GREEN + "Room removed successfully." + ANSI_RESET);
        } catch (Exception e) {
            System.out.println(ANSI_RED + "Error: " + e.getMessage() + ANSI_RESET);
        }
    }

    // ---------- Helper Methods to Print Message Attributes ----------

    private static void printHotel(Hotel hotel) {
        System.out.println("Hotel ID: " + hotel.getId());
        System.out.println("Name: " + hotel.getName());
        System.out.println("Description: " + hotel.getDescription());
        if (hotel.hasLocation()) {
            HotelLocation location = hotel.getLocation();
            System.out.println("Location:");
            if (location.getId() != 0) {
                System.out.println("  Location ID: " + location.getId());
            }
            System.out.println("  Address: " + location.getAddress());
            System.out.println("  City: " + location.getCity());
            System.out.println("  Country: " + location.getCountry());
        }
        System.out.println("Average Rating: " + hotel.getAverageRating());
        System.out.println("Room IDs: " + hotel.getRoomIdsList());
        System.out.println("Booking IDs: " + hotel.getBookingIdsList());
        System.out.println("Hotel Ratings: " + hotel.getHotelRatingsList());
    }

    private static void printGuest(Guest guest) {
        System.out.println("Guest ID: " + guest.getId());
        System.out.println("First Name: " + guest.getFirstName());
        System.out.println("Last Name: " + guest.getLastName());
        System.out.println("Birthday: " + guest.getBirthday());
        System.out.println("Email: " + guest.getEmail());
        System.out.println("Phone Number: " + guest.getPhoneNumber());
    }

    private static void printBooking(Booking booking) {
        System.out.println("Booking ID: " + booking.getId());
        System.out.println("Hotel ID: " + booking.getHotelId());
        System.out.println("Guest IDs: " + booking.getGuestIdsList());
        System.out.println("Room IDs: " + booking.getRoomIdsList());
        System.out.println("Check-In Date: " + booking.getCheckInDate());
        System.out.println("Check-Out Date: " + booking.getCheckOutDate());
        System.out.println("Status: " + booking.getStatus());
        System.out.println("Total Price: " + booking.getTotalPrice());
        if (!booking.getCheckInTime().isEmpty()) {
            System.out.println("Check-In Time: " + booking.getCheckInTime());
        }
        if (!booking.getCheckOutTime().isEmpty()) {
            System.out.println("Check-Out Time: " + booking.getCheckOutTime());
        }
    }

    private static void printRoom(Room room) {
        System.out.println("Room ID: " + room.getId());
        System.out.println("Hotel ID: " + room.getHotelId());
        System.out.println("Type: " + room.getType());
        System.out.println("Price Per Night: " + room.getPricePerNight());
        if (room.hasRoomIdentifier()) {
            RoomIdentifier identifier = room.getRoomIdentifier();
            System.out.println("Room Identifier:");
            System.out.println("  Building: " + identifier.getBuilding());
            System.out.println("  Floor: " + identifier.getFloor());
            System.out.println("  Room Number: " + identifier.getRoomNumber());
        }
        if (room.getBookingsCount() > 0) {
            System.out.println("Bookings:");
            for (Booking booking : room.getBookingsList()) {
                System.out.println("  - Booking ID: " + booking.getId());
            }
        }
    }
}
