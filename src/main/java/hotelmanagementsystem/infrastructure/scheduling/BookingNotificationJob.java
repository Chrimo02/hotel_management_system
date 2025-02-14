package hotelmanagementsystem.infrastructure.scheduling;

import hotelmanagementsystem.domain.models.Booking;
import hotelmanagementsystem.domain.models.Guest;
import hotelmanagementsystem.domain.services.BookingService;
import io.quarkus.mailer.Mail;
import io.quarkus.mailer.Mailer;
import io.quarkus.scheduler.Scheduled;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.time.LocalDate;
import java.util.List;

@Singleton
public class BookingNotificationJob {

    @Inject
    BookingService bookingService;

    @Inject
    Mailer mailer;

    /**
     * Diese Methode wird täglich um 00:00 Uhr ausgeführt.
     * Sie sucht nach Buchungen, die in genau 7 Tagen beginnen,
     * und sendet eine E-Mail-Benachrichtigung an die entsprechenden Gäste.
     */
    @Scheduled(cron = "0 0 0 * * ?")
    public void sendReminderForUpcomingBookings() {
        LocalDate targetDate = LocalDate.now().plusDays(7);

        List<Booking> upcomingBookings = bookingService.findAllByCheckInDate(targetDate);

        for (Booking booking : upcomingBookings) {
            for (Guest guest : booking.getGuests()) {
                String to = guest.geteMail();
                String subject = "Ihre Buchung startet bald!";
                String body = String.format(
                        "Sehr geehrte/r %s %s,\n\n" +
                                "Ihre Buchung in unserem Hotel beginnt am %s.\n" +
                                "Wir freuen uns darauf, Sie bei uns begrüßen zu dürfen!\n\n" +
                                "Mit freundlichen Grüßen,\nIhr Hotel-Team",
                        guest.getFirstName(),
                        guest.getLastName(),
                        booking.getCheckInDate()
                );

                try {
                    mailer.send(
                            Mail.withText(to, subject, body)
                    );
                    System.out.println("E-Mail an " + to + " gesendet.");
                } catch (Exception e) {
                    System.err.println("Fehler beim Senden der E-Mail an " + to + ": " + e.getMessage());
                }
            }
        }
    }
}


