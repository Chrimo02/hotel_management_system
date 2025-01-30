package hotelmanagementsystem.presentation.cli;

import hotelmanagementsystem.application.BookingApplication;
import hotelmanagementsystem.infrastructure.api.grpc.generated.BookingResponse;
import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import jakarta.enterprise.inject.spi.CDI;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * BookingCLI - A command-line interface for testing bookings.
 */
public class BookingCLI implements QuarkusApplication {

    private final BookingApplication bookingApplication;
    private final Scanner scanner = new Scanner(System.in);

    public BookingCLI() {
        this.bookingApplication = CDI.current().select(BookingApplication.class).get();
    }

    @Override
    public int run(String... args) {
        while (true) {
            System.out.println("\n1. Create Booking\n2. Get Booking\n3. Cancel Booking\n4. Check-In Guest\n5. Check-Out Guest\n6. Exit");
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> createBooking();
                case 2 -> getBookingById();
                case 3 -> cancelBooking();
                case 4 -> checkInGuest();
                case 5 -> checkOutGuest();
                case 6 -> {
                    System.out.println("Exiting...");
                    return 0;
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    private void createBooking() {
        System.out.print("Enter hotel ID: ");
        long hotelId = scanner.nextLong();
        scanner.nextLine();

        System.out.print("Enter room types (comma separated): ");
        List<String> roomTypes = Arrays.asList(scanner.nextLine().split(","));

        System.out.print("Enter guest IDs (comma separated): ");
        List<Long> guestIds = Arrays.stream(scanner.nextLine().split(","))
                .map(Long::parseLong)
                .toList();

        System.out.print("Enter check-in date (YYYY-MM-DD): ");
        String checkInDate = scanner.nextLine();

        System.out.print("Enter check-out date (YYYY-MM-DD): ");
        String checkOutDate = scanner.nextLine();

        BookingResponse response = bookingApplication.createBooking(hotelId, roomTypes, guestIds, checkInDate, checkOutDate);
        System.out.println("✅ Booking created with ID: " + response.getBooking().getId());
    }

    private void getBookingById() {
        System.out.print("Enter booking ID: ");
        long bookingId = scanner.nextLong();
        scanner.nextLine();

        BookingResponse response = bookingApplication.getBookingById(bookingId);
        System.out.println("📌 Booking ID: " + response.getBooking().getId());
    }

    private void cancelBooking() {
        System.out.print("Enter booking ID to cancel: ");
        long bookingId = scanner.nextLong();
        scanner.nextLine();

        bookingApplication.cancelBooking(bookingId);
        System.out.println("✅ Booking canceled.");
    }

    private void checkInGuest() {
        System.out.print("Enter booking ID to check in: ");
        long bookingId = scanner.nextLong();
        scanner.nextLine();

        bookingApplication.checkInGuest(bookingId);
        System.out.println("✅ Guest checked in.");
    }

    private void checkOutGuest() {
        System.out.print("Enter booking ID to check out: ");
        long bookingId = scanner.nextLong();
        scanner.nextLine();

        bookingApplication.checkOutGuest(bookingId);
        System.out.println("✅ Guest checked out.");
    }

    public static void main(String[] args) {
        Quarkus.run(BookingCLI.class, args);
    }
}
