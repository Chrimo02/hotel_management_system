package hotelmanagementsystem.presentation.cli;

import hotelmanagementsystem.application.BookingApplication;
import hotelmanagementsystem.application.GuestApplication;
import hotelmanagementsystem.application.HotelApplication;
import hotelmanagementsystem.application.RoomApplication;
import hotelmanagementsystem.infrastructure.api.grpc.generated.*;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * HotelManagementCLI - A single command-line interface for
 * Hotel, Guest, Booking, and Room operations, plus a QuarkusMain
 * entrypoint so that the gRPC server + CLI run together.
 */
@QuarkusMain
@Dependent
public class HotelManagementCLI implements QuarkusApplication {

    @Inject
    BookingApplication bookingApplication;

    @Inject
    HotelApplication hotelApplication;

    @Inject
    GuestApplication guestApplication;

    @Inject
    RoomApplication roomApplication;

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public int run(String... args) {
        System.out.println("✅ Hotel Management CLI started!");
        mainMenu();
        // Once mainMenu() returns, we exit Quarkus
        return 0;
    }

    private void mainMenu() {
        while (true) {
            System.out.println("\n==== Main Menu ====");
            System.out.println("1. Manage Hotels");
            System.out.println("2. Manage Guests");
            System.out.println("3. Manage Bookings");
            System.out.println("4. Manage Rooms");
            System.out.println("5. Exit");
            System.out.print("Select an option: ");

            int choice = readInt();
            switch (choice) {
                case 1 -> hotelMenu();
                case 2 -> guestMenu();
                case 3 -> bookingMenu();
                case 4 -> roomMenu();
                case 5 -> {
                    System.out.println("🚪 Exiting CLI...");
                    return; // ends the loop => run() returns => Quarkus ends
                }
                default -> System.out.println("⚠ Invalid option! Try again.");
            }
        }
    }

    // -------------------------------------------------------------------
    //  HOTEL MENU
    // -------------------------------------------------------------------
    private void hotelMenu() {
        while (true) {
            System.out.println("\n==== Hotel Menu ====");
            System.out.println("1. Create Hotel");
            System.out.println("2. Get Hotel by ID");
            System.out.println("3. List Hotels (with optional filtering/paging)");
            System.out.println("4. Update Hotel");
            System.out.println("5. Delete Hotel");
            System.out.println("6. Rate Hotel");
            System.out.println("7. Find Available Rooms");
            System.out.println("8. Back to Main Menu");
            System.out.print("Select an option: ");

            int choice = readInt();
            switch (choice) {
                case 1 -> createHotel();
                case 2 -> getHotelById();
                case 3 -> listHotels();
                case 4 -> updateHotel();
                case 5 -> deleteHotel();
                case 6 -> rateHotel();
                case 7 -> findAvailableRooms();
                case 8 -> {
                    return; // break from hotelMenu()
                }
                default -> System.out.println("⚠ Invalid option! Try again.");
            }
        }
    }

    private void createHotel() {
        System.out.print("🏨 Enter hotel name: ");
        String name = scanner.nextLine();
        System.out.print("📝 Enter description: ");
        String description = scanner.nextLine();

        System.out.println("📍 Enter hotel location:");
        System.out.print("   Location ID (long, if any known, else 0): ");
        long locationId = readLong();
        System.out.print("   Address: ");
        String address = scanner.nextLine();
        System.out.print("   City: ");
        String city = scanner.nextLine();
        System.out.print("   Country: ");
        String country = scanner.nextLine();

        HotelLocation location = HotelLocation.newBuilder()
                .setId(locationId)
                .setAddress(address)
                .setCity(city)
                .setCountry(country)
                .build();

        try {
            HotelResponse response = hotelApplication.createHotel(name, description, location);
            // Print the ID properly:
            System.out.println("✅ Hotel created with ID: " + response.getHotel().getId());
        } catch (Exception e) {
            System.err.println("Error creating hotel: " + e.getMessage());
        }
    }

    private void getHotelById() {
        System.out.print("🔍 Enter hotel ID: ");
        long hotelId = readLong();
        try {
            HotelResponse response = hotelApplication.getHotelById(hotelId);
            Hotel h = response.getHotel();
            System.out.println("Hotel ID: " + h.getId()
                    + " | Name: " + h.getName()
                    + " | Description: " + h.getDescription()
                    + " | Rating: " + h.getAverageRating());
        } catch (Exception e) {
            System.err.println("Error getting hotel: " + e.getMessage());
        }
    }

    private void listHotels() {
        System.out.print("Filter by city? (blank for none): ");
        String city = scanner.nextLine();
        System.out.print("Minimum rating? (0 for none): ");
        double minRating = readDouble();
        System.out.print("Page number (1=default): ");
        int pageNumber = readInt();
        System.out.print("Page size (10=default): ");
        int pageSize = readInt();

        try {
            ListHotelsResponse response = hotelApplication.listHotels(
                    city.isBlank() ? "" : city,
                    minRating,
                    pageNumber < 1 ? 1 : pageNumber,
                    pageSize < 1 ? 10 : pageSize
            );
            System.out.println("📋 Found " + response.getTotalCount() + " total hotels. Showing page "
                    + response.getPageNumber() + " with pageSize=" + response.getPageSize());
            for (Hotel h : response.getHotelsList()) {
                System.out.println("- ID: " + h.getId()
                        + " | " + h.getName()
                        + " | AvgRating=" + h.getAverageRating()
                        + " | City=" + h.getLocation().getCity());
            }
        } catch (Exception e) {
            System.err.println("Error listing hotels: " + e.getMessage());
        }
    }

    private void updateHotel() {
        System.out.print("Enter hotel ID to update: ");
        long hotelId = readLong();

        System.out.print("New name: ");
        String name = scanner.nextLine();
        System.out.print("New description: ");
        String description = scanner.nextLine();

        System.out.println("Update location as well (optional): ");
        System.out.print("   Location ID (long): ");
        long locationId = readLong();
        System.out.print("   Address: ");
        String address = scanner.nextLine();
        System.out.print("   City: ");
        String city = scanner.nextLine();
        System.out.print("   Country: ");
        String country = scanner.nextLine();

        HotelLocation location = HotelLocation.newBuilder()
                .setId(locationId)
                .setAddress(address)
                .setCity(city)
                .setCountry(country)
                .build();

        try {
            HotelResponse response = hotelApplication.updateHotel(hotelId, name, description, location);
            System.out.println("✅ Hotel updated. New name: " + response.getHotel().getName());
        } catch (Exception e) {
            System.err.println("Error updating hotel: " + e.getMessage());
        }
    }

    private void deleteHotel() {
        System.out.print("Enter hotel ID to delete: ");
        long hotelId = readLong();
        try {
            hotelApplication.deleteHotel(hotelId);
            System.out.println("✅ Hotel deleted.");
        } catch (Exception e) {
            System.err.println("Error deleting hotel: " + e.getMessage());
        }
    }

    private void rateHotel() {
        System.out.print("Enter hotel ID to rate: ");
        long hotelId = readLong();
        System.out.print("Enter guest ID who is rating: ");
        long guestId = readLong();
        System.out.print("Rating (1-5): ");
        int rating = readInt();
        System.out.print("Comment: ");
        String comment = scanner.nextLine();

        try {
            hotelApplication.rateHotel(hotelId, guestId, rating, comment);
            System.out.println("✅ Hotel rated successfully.");
        } catch (Exception e) {
            System.err.println("Error rating hotel: " + e.getMessage());
        }
    }

    private void findAvailableRooms() {
        System.out.print("Enter hotel ID: ");
        long hotelId = readLong();
        System.out.print("Room type filter? (blank for none): ");
        String roomType = scanner.nextLine();
        System.out.print("Check-in date (YYYY-MM-DD): ");
        String checkIn = scanner.nextLine();
        System.out.print("Check-out date (YYYY-MM-DD): ");
        String checkOut = scanner.nextLine();

        try {
            ListRoomsResponse response = hotelApplication.findAvailableRooms(
                    hotelId,
                    roomType.isBlank() ? "" : roomType,
                    checkIn,
                    checkOut
            );
            System.out.println("Available rooms:");
            for (Room r : response.getRoomsList()) {
                System.out.println("- Room ID: " + r.getId()
                        + " | " + r.getType()
                        + " | Price: " + r.getPricePerNight());
            }
        } catch (Exception e) {
            System.err.println("Error finding available rooms: " + e.getMessage());
        }
    }

    // -------------------------------------------------------------------
    //  GUEST MENU
    // -------------------------------------------------------------------
    private void guestMenu() {
        while (true) {
            System.out.println("\n==== Guest Menu ====");
            System.out.println("1. Create Guest");
            System.out.println("2. Get Guest by ID");
            System.out.println("3. Update Guest Email");
            System.out.println("4. Update Guest Phone");
            System.out.println("5. Update Guest LastName");
            System.out.println("6. Update Guest Title");
            System.out.println("7. Delete Guest");
            System.out.println("8. Get Bookings by Guest ID");
            System.out.println("9. Back to Main Menu");
            System.out.print("Select an option: ");

            int choice = readInt();
            switch (choice) {
                case 1 -> createGuest();
                case 2 -> getGuestById();
                case 3 -> updateGuestEmail();
                case 4 -> updateGuestPhone();
                case 5 -> updateGuestLastName();
                case 6 -> updateGuestTitle();
                case 7 -> deleteGuest();
                case 8 -> getBookingsByGuestId();
                case 9 -> {
                    return;
                }
                default -> System.out.println("⚠ Invalid option! Try again.");
            }
        }
    }

    private void createGuest() {
        System.out.print("First name: ");
        String firstName = scanner.nextLine();
        System.out.print("Last name: ");
        String lastName = scanner.nextLine();
        System.out.print("Title (optional, press Enter to skip): ");
        String title = scanner.nextLine();
        title = title.isBlank() ? null : title;

        System.out.print("Birthday (YYYY-MM-DD): ");
        String birthday = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Phone: ");
        String phone = scanner.nextLine();

        try {
            GuestResponse response = guestApplication.createGuest(firstName, lastName, title, birthday, email, phone);
            System.out.println("✅ Guest created with ID: " + response.getGuest().getId());
        } catch (Exception e) {
            System.err.println("Error creating guest: " + e.getMessage());
        }
    }

    private void getGuestById() {
        System.out.print("Enter guest ID: ");
        long guestId = readLong();
        try {
            GuestResponse response = guestApplication.getGuestById(guestId);
            Guest g = response.getGuest();
            System.out.println("✅ Guest found: ID=" + g.getId()
                    + " | " + g.getFirstName() + " " + g.getLastName()
                    + " (Email: " + g.getEmail() + ")");
        } catch (Exception e) {
            System.err.println("Error retrieving guest: " + e.getMessage());
        }
    }

    private void updateGuestEmail() {
        System.out.print("Guest ID: ");
        long guestId = readLong();
        System.out.print("New email: ");
        String newEmail = scanner.nextLine();

        try {
            GuestResponse response = guestApplication.updateGuestEmail(guestId, newEmail);
            System.out.println("✅ Email updated. Current email: " + response.getGuest().getEmail());
        } catch (Exception e) {
            System.err.println("Error updating email: " + e.getMessage());
        }
    }

    private void updateGuestPhone() {
        System.out.print("Guest ID: ");
        long guestId = readLong();
        System.out.print("New phone: ");
        String newPhone = scanner.nextLine();

        try {
            GuestResponse response = guestApplication.updateGuestPhone(guestId, newPhone);
            System.out.println("✅ Phone updated. Current phone: " + response.getGuest().getPhoneNumber());
        } catch (Exception e) {
            System.err.println("Error updating phone: " + e.getMessage());
        }
    }

    private void updateGuestLastName() {
        System.out.print("Guest ID: ");
        long guestId = readLong();
        System.out.print("New last name: ");
        String newLastName = scanner.nextLine();

        try {
            GuestResponse response = guestApplication.updateGuestLastName(guestId, newLastName);
            System.out.println("✅ Last name updated. Current last name: " + response.getGuest().getLastName());
        } catch (Exception e) {
            System.err.println("Error updating last name: " + e.getMessage());
        }
    }

    private void updateGuestTitle() {
        System.out.print("Guest ID: ");
        long guestId = readLong();
        System.out.print("New title: ");
        String newTitle = scanner.nextLine();

        try {
            GuestResponse response = guestApplication.updateGuestTitle(guestId, newTitle);
            System.out.println("✅ Title updated. Current title: " + response.getGuest().getTitle());
        } catch (Exception e) {
            System.err.println("Error updating title: " + e.getMessage());
        }
    }

    private void deleteGuest() {
        System.out.print("Guest ID to delete: ");
        long guestId = readLong();
        try {
            guestApplication.deleteGuest(guestId);
            System.out.println("✅ Guest deleted.");
        } catch (Exception e) {
            System.err.println("Error deleting guest: " + e.getMessage());
        }
    }

    private void getBookingsByGuestId() {
        System.out.print("Guest ID: ");
        long guestId = readLong();
        try {
            ListBookingsResponse response = guestApplication.getBookingsByGuestId(guestId);
            if (response.getBookingsCount() == 0) {
                System.out.println("No bookings for this guest.");
            } else {
                System.out.println("📋 Bookings for guest " + guestId + ":");
                for (Booking b : response.getBookingsList()) {
                    System.out.println(" - Booking ID: " + b.getId()
                            + " | Hotel ID: " + b.getHotelId()
                            + " | Status: " + b.getStatus());
                }
            }
        } catch (Exception e) {
            System.err.println("Error retrieving bookings: " + e.getMessage());
        }
    }

    // -------------------------------------------------------------------
    //  BOOKING MENU
    // -------------------------------------------------------------------
    private void bookingMenu() {
        while (true) {
            System.out.println("\n==== Booking Menu ====");
            System.out.println("1. Create Booking");
            System.out.println("2. Get Booking by ID");
            System.out.println("3. Cancel Booking");
            System.out.println("4. Check-In Guest");
            System.out.println("5. Check-Out Guest");
            System.out.println("6. Back to Main Menu");
            System.out.print("Select an option: ");

            int choice = readInt();
            switch (choice) {
                case 1 -> createBooking();
                case 2 -> getBookingById();
                case 3 -> cancelBooking();
                case 4 -> checkInGuest();
                case 5 -> checkOutGuest();
                case 6 -> {
                    return;
                }
                default -> System.out.println("⚠ Invalid option! Try again.");
            }
        }
    }

    private void createBooking() {
        System.out.print("🏨 Enter hotel ID: ");
        long hotelId = readLong();

        System.out.print("🛏 Enter room types (comma separated, e.g. SINGLE,DOUBLE): ");
        List<String> roomTypes = Arrays.stream(scanner.nextLine().split(","))
                .map(String::trim)
                .collect(Collectors.toList());

        System.out.print("👥 Enter guest IDs (comma separated): ");
        List<Long> guestIds = Arrays.stream(scanner.nextLine().split(","))
                .map(String::trim)
                .map(Long::parseLong)
                .collect(Collectors.toList());

        System.out.print("📅 Enter check-in date (YYYY-MM-DD): ");
        String checkInDate = scanner.nextLine();

        System.out.print("📅 Enter check-out date (YYYY-MM-DD): ");
        String checkOutDate = scanner.nextLine();

        try {
            BookingResponse response = bookingApplication.createBooking(
                    hotelId,
                    roomTypes,
                    guestIds,
                    checkInDate,
                    checkOutDate
            );
            System.out.println("✅ Booking created with ID: " + response.getBooking().getId());
        } catch (Exception e) {
            System.err.println("Error creating booking: " + e.getMessage());
        }
    }

    private void getBookingById() {
        System.out.print("🔍 Enter booking ID: ");
        long bookingId = readLong();

        try {
            BookingResponse response = bookingApplication.getBookingById(bookingId);
            Booking b = response.getBooking();
            System.out.println("📌 Booking ID: " + b.getId()
                    + " | Hotel ID: " + b.getHotelId()
                    + " | Status: " + b.getStatus());
        } catch (Exception e) {
            System.err.println("Error getting booking: " + e.getMessage());
        }
    }

    private void cancelBooking() {
        System.out.print("❌ Enter booking ID to cancel: ");
        long bookingId = readLong();

        try {
            bookingApplication.cancelBooking(bookingId);
            System.out.println("✅ Booking canceled.");
        } catch (Exception e) {
            System.err.println("Error canceling booking: " + e.getMessage());
        }
    }

    private void checkInGuest() {
        System.out.print("🛎 Enter booking ID to check in: ");
        long bookingId = readLong();

        try {
            bookingApplication.checkInGuest(bookingId);
            System.out.println("✅ Guest checked in.");
        } catch (Exception e) {
            System.err.println("Error checking in: " + e.getMessage());
        }
    }

    private void checkOutGuest() {
        System.out.print("🏁 Enter booking ID to check out: ");
        long bookingId = readLong();

        try {
            bookingApplication.checkOutGuest(bookingId);
            System.out.println("✅ Guest checked out.");
        } catch (Exception e) {
            System.err.println("Error checking out: " + e.getMessage());
        }
    }

    // -------------------------------------------------------------------
    //  ROOM MENU
    // -------------------------------------------------------------------
    private void roomMenu() {
        while (true) {
            System.out.println("\n==== Room Menu ====");
            System.out.println("1. Create Room");
            System.out.println("2. Get Room by ID");
            System.out.println("3. Update Room Price");
            System.out.println("4. Remove Room");
            System.out.println("5. Back to Main Menu");
            System.out.print("Select an option: ");

            int choice = readInt();
            switch (choice) {
                case 1 -> createRoom();
                case 2 -> getRoomById();
                case 3 -> updateRoomPrice();
                case 4 -> removeRoom();
                case 5 -> {
                    return;
                }
                default -> System.out.println("⚠ Invalid option! Try again.");
            }
        }
    }

    private void createRoom() {
        System.out.print("Enter price per night: ");
        double price = readDouble();

        System.out.print("Enter room type (SINGLE or DOUBLE): ");
        String type = scanner.nextLine();

        System.out.print("Enter hotel ID: ");
        long hotelId = readLong();

        System.out.println("Enter room identifier data:");
        System.out.print("  Building: ");
        String building = scanner.nextLine();
        System.out.print("  Floor (int): ");
        int floor = readInt();
        System.out.print("  Room number: ");
        String roomNumber = scanner.nextLine();

        RoomIdentifier roomIdentifier = RoomIdentifier.newBuilder()
                .setBuilding(building)
                .setFloor(floor)
                .setRoomNumber(roomNumber)
                .build();

        try {
            RoomResponse response = roomApplication.createRoom(price, type, hotelId, roomIdentifier);
            System.out.println("✅ Room created with ID: " + response.getRoom().getId());
        } catch (Exception e) {
            System.err.println("Error creating room: " + e.getMessage());
        }
    }

    private void getRoomById() {
        System.out.print("Enter room ID: ");
        long roomId = readLong();
        try {
            RoomResponse response = roomApplication.getRoomById(roomId);
            Room room = response.getRoom();
            System.out.println("Room: ID=" + room.getId()
                    + " | Type=" + room.getType()
                    + " | Price=" + room.getPricePerNight());
        } catch (Exception e) {
            System.err.println("Error retrieving room: " + e.getMessage());
        }
    }

    private void updateRoomPrice() {
        System.out.print("Enter room ID: ");
        long roomId = readLong();
        System.out.print("Enter new price: ");
        double newPrice = readDouble();
        try {
            RoomResponse response = roomApplication.updateRoomPrice(roomId, newPrice);
            System.out.println("✅ Price updated. New price: " + response.getRoom().getPricePerNight());
        } catch (Exception e) {
            System.err.println("Error updating room price: " + e.getMessage());
        }
    }

    private void removeRoom() {
        System.out.print("Enter room ID to remove: ");
        long roomId = readLong();
        try {
            roomApplication.removeRoom(roomId);
            System.out.println("✅ Room removed.");
        } catch (Exception e) {
            System.err.println("Error removing room: " + e.getMessage());
        }
    }

    // -------------------------------------------------------------------
    //  HELPER METHODS
    // -------------------------------------------------------------------
    private int readInt() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException ex) {
            return -1;
        }
    }

    private long readLong() {
        try {
            return Long.parseLong(scanner.nextLine());
        } catch (NumberFormatException ex) {
            return -1;
        }
    }

    private double readDouble() {
        try {
            return Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException ex) {
            return 0.0;
        }
    }
}
