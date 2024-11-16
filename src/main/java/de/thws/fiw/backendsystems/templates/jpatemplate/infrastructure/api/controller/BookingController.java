package de.thws.fiw.backendsystems.templates.jpatemplate.infrastructure.api.controller;

import de.thws.fiw.backendsystems.templates.jpatemplate.domain.service.BookingService;

import java.util.Scanner;

public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    // Methode zur Benutzerinteraktion über die Kommandozeile
    public void addRating() {
        Scanner scanner = new Scanner(System.in);

        // Eingabe der Buchungs-ID
        System.out.print("Geben Sie die Buchungs-ID ein: ");
        long bookingId = scanner.nextLong();
        scanner.nextLine();  // Zum Einlesen der nächsten Zeile

        // Eingabe der Sternebewertung
        System.out.print("Geben Sie die Sternebewertung (1-5) ein: ");
        int starRating = scanner.nextInt();
        scanner.nextLine();  // Zum Einlesen der nächsten Zeile

        // Eingabe des Kommentars
        System.out.print("Geben Sie Ihren Kommentar ein: ");
        String comment = scanner.nextLine();

        try {
            // Erstelle die Buchung (normalerweise aus der DB, hier simuliert)
            //Booking booking = finde Buchung

            // Füge die Bewertung hinzu
            //bookingService.rateBooking(booking, comment, starRating);

            // Bestätigung
            //System.out.println("Die Bewertung wurde erfolgreich hinzugefügt.");

        } catch (IllegalArgumentException e) {
            // Fehler bei der Eingabe der Bewertung
            System.out.println("Fehler: " + e.getMessage());
        } catch (Exception e) {
            // Allgemeine Fehlerbehandlung
            System.out.println("Fehler beim Hinzufügen der Bewertung: " + e.getMessage());
        }
    }
}

