package hotelmanagementsystem.infrastructure.startup;

import hotelmanagementsystem.domain.models.Hotel;
import hotelmanagementsystem.domain.models.HotelLocation;
import hotelmanagementsystem.domain.models.SingleRoom;
import hotelmanagementsystem.domain.models.DoubleRoom;
import hotelmanagementsystem.domain.models.RoomIdentifier;
import hotelmanagementsystem.domain.services.GuestService;
import hotelmanagementsystem.domain.services.HotelService;
import hotelmanagementsystem.domain.services.RoomService;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;

@ApplicationScoped
public class TestDataLoader {

    @Inject
    HotelService hotelService;

    @Inject
    RoomService roomService;
    @Inject
    GuestService guestService;

    public void loadBondTestData(@Observes StartupEvent ev) {
        createBondGuests();
        createBondFilmHotels();
        System.out.println("TestDataLoader: Hotels und Gäste wurden erfolgreich in die In-Memory-Datenbank geladen.");
    }

    private void createBondGuests() {
        guestService.createGuest("James", "Bond", 1920, 11, 11, "bond@mi6.co.uk", "007");
        guestService.createGuest("M", "M", 1890, 6, 1, "m@mi6.gov.uk", "M001");
        guestService.createGuest("Q", "Q", 1900, 11, 30, "q@mi6.gov.uk", "Q007");
        guestService.createGuest("Miss", "Moneypenny", 1915, 8, 20, "moneypenny@mi6.gov.uk", "MNP007");
        guestService.createGuest("Felix", "Leiter", 1925, 3, 5, "leiter@mi6.gov.uk", "F001");
        guestService.createGuest("Ernst", "Blofeld", 1937, 9, 9, "blofeld@criminal.org", "B666");
        guestService.createGuest("Rosa", "Klebb", 1925, 5, 15, "klebb@criminal.org", "RK007");
        guestService.createGuest("Le", "Chiffre", 1930, 10, 10, "lechiffre@criminal.org", "LC007");
        guestService.createGuest("Oddjob", "Unknown", 1920, 1, 1, "oddjob@criminal.org", "OJ007");
        guestService.createGuest("Jaws", "Unknown", 1940, 7, 7, "jaws@criminal.org", "JW007");
        guestService.createGuest("Mr.", "White", 1905, 2, 2, "white@criminal.org", "W007");
        guestService.createGuest("Mr.", "Wint", 1910, 4, 4, "wint@criminal.org", "WNT007");
        guestService.createGuest("Mr.", "Kidd", 1915, 12, 12, "kidd@criminal.org", "KD007");
        guestService.createGuest("Francisco", "Scaramanga", 1935, 5, 5, "scaramanga@criminal.org", "SC007");
        guestService.createGuest("Elektra", "King", 1945, 6, 6, "elektra.king@criminal.org", "EK007");
        guestService.createGuest("Alexei", "Zukovsky", 1930, 3, 3, "zukovsky@ally.gov", "AZ007");
        guestService.createGuest("Gogol", "Gogol", 1900, 1, 15, "gogol@kgb.ru", "GG007");
        guestService.createGuest("Colonel", "Hinx", 1928, 8, 8, "hinx@criminal.org", "H007");
        guestService.createGuest("Vesper", "Lynd", 1932, 11, 11, "vesper@mi6.gov.uk", "VL007");
        guestService.createGuest("Alec", "Trevelyan", 1925, 9, 25, "trevelyan@mi6.gov.uk", "AT007");
        guestService.createGuest("Xenia", "Onatopp", 1947, 2, 14, "xenia@criminal.org", "XO007");
        guestService.createGuest("Boris", "Grishenko", 1950, 4, 20, "grishenko@tech.org", "BG007");
        guestService.createGuest("Natalya", "Simonova", 1955, 5, 30, "simonova@mi6.gov.uk", "NS007");
        guestService.createGuest("Octopussy", "Unknown", 1940, 7, 21, "octopussy@criminal.org", "OP007");
        guestService.createGuest("Tiger", "Tanaka", 1918, 10, 10, "tanaka@mi6.gov.uk", "TT007");
        guestService.createGuest("Kissy", "Suzuki", 1920, 12, 5, "suzuki@mi6.gov.uk", "KS007");
        guestService.createGuest("Tracy", "Bond", 1922, 3, 10, "tracy@mi6.gov.uk", "TB007");
        guestService.createGuest("Jinx", "Unknown", 1970, 1, 25, "jinx@mi6.gov.uk", "JX007");
        guestService.createGuest("Fiona", "Volpe", 1939, 6, 18, "volpe@criminal.org", "FV007");
        guestService.createGuest("Emilio", "Largo", 1927, 7, 14, "largo@criminal.org", "EL007");
        guestService.createGuest("Dominic", "Greene", 1960, 8, 30, "greene@criminal.org", "DG007");
        guestService.createGuest("Raoul", "Silva", 1958, 11, 2, "silva@criminal.org", "RS007");
        guestService.createGuest("Max", "Zorin", 1948, 4, 4, "zorin@criminal.org", "MZ007");
        guestService.createGuest("May", "Day", 1955, 9, 9, "may.day@criminal.org", "MD007");
        guestService.createGuest("Stromberg", "Stromberg", 1932, 5, 5, "stromberg@criminal.org", "ST007");
        guestService.createGuest("Hugo", "Drax", 1938, 12, 12, "drax@criminal.org", "HD007");
        guestService.createGuest("Fatima", "Blush", 1942, 10, 10, "blush@criminal.org", "FB007");
        guestService.createGuest("Bill", "Fairbanks", 1926, 7, 7, "fairbanks@mi6.gov.uk", "BF007");
        guestService.createGuest("Agent", "008", 1930, 1, 1, "agent008@mi6.gov.uk", "A008");
        guestService.createGuest("Agent", "009", 1930, 2, 2, "agent009@mi6.gov.uk", "A009");
        guestService.createGuest("Major", "Gustavson", 1940, 3, 3, "gustavson@mi6.gov.uk", "MG007");
        guestService.createGuest("Captain", "Hargreaves", 1935, 4, 4, "hargreaves@mi6.gov.uk", "CH007");
        guestService.createGuest("Agent", "Kincaid", 1945, 5, 5, "kincaid@mi6.gov.uk", "AK007");
        guestService.createGuest("Dr.", "No", 1908, 9, 9, "dr.no@criminal.org", "DN007");
        guestService.createGuest("General", "Leonid", 1940, 10, 10, "leonid@criminal.org", "GL007");
        guestService.createGuest("Ivan", "Vasilyev", 1932, 11, 11, "vasilyev@criminal.org", "IV007");
        guestService.createGuest("Grigori", "Kirov", 1929, 8, 8, "kirov@criminal.org", "GK007");
        guestService.createGuest("Nikolai", "Diavolo", 1937, 7, 7, "diavolo@criminal.org", "ND007");
        guestService.createGuest("Vladimir", "Sokolov", 1933, 6, 6, "sokolov@criminal.org", "VS007");
        guestService.createGuest("Sergei", "Karamazov", 1935, 5, 5, "karamazov@criminal.org", "SK007");

    }

    private void createBondFilmHotels() {
        try {
            // Dr. No (1962)
            HotelLocation locDrNo = new HotelLocation.HotelLocationBuilder()
                    .withId(0L)
                    .withAddress("Mystery Island 1")
                    .withCity("Kingston")
                    .withCountry("Jamaica")
                    .build();
            Hotel hotelDrNo = hotelService.createHotel("Dr. No Retreat", "A secret getaway inspired by Dr. No (1962)", locDrNo);
            roomService.createRoom(180.0, new RoomIdentifier("Villa", 1, "101"), hotelDrNo.getId(), SingleRoom.class);
            roomService.createRoom(250.0, new RoomIdentifier("Villa", 1, "102"), hotelDrNo.getId(), DoubleRoom.class);

            // From Russia with Love (1963)
            HotelLocation locFRWL = new HotelLocation.HotelLocationBuilder()
                    .withId(0L)
                    .withAddress("KGB Street 10")
                    .withCity("Moscow")
                    .withCountry("Russia")
                    .build();
            Hotel hotelFRWL = hotelService.createHotel("From Russia with Love Inn", "Intrigue and passion in every corner (1963)", locFRWL);
            roomService.createRoom(200.0, new RoomIdentifier("Red Square", 1, "201"), hotelFRWL.getId(), SingleRoom.class);
            roomService.createRoom(300.0, new RoomIdentifier("Red Square", 1, "202"), hotelFRWL.getId(), DoubleRoom.class);

            // Goldfinger (1964)
            HotelLocation locGoldfinger = new HotelLocation.HotelLocationBuilder()
                    .withId(0L)
                    .withAddress("Golden Road 5")
                    .withCity("London")
                    .withCountry("UK")
                    .build();
            Hotel hotelGoldfinger = hotelService.createHotel("Goldfinger Grandeur", "Luxury touched by gold (1964)", locGoldfinger);
            roomService.createRoom(220.0, new RoomIdentifier("Gold Wing", 2, "301"), hotelGoldfinger.getId(), SingleRoom.class);
            roomService.createRoom(350.0, new RoomIdentifier("Gold Wing", 2, "302"), hotelGoldfinger.getId(), DoubleRoom.class);

            // Thunderball (1965)
            HotelLocation locThunderball = new HotelLocation.HotelLocationBuilder()
                    .withId(0L)
                    .withAddress("Ocean Drive 3")
                    .withCity("Nassau")
                    .withCountry("Bahamas")
                    .build();
            Hotel hotelThunderball = hotelService.createHotel("Thunderball Bay Resort", "Feel the splash of adventure (1965)", locThunderball);
            roomService.createRoom(240.0, new RoomIdentifier("Wave Block", 1, "401"), hotelThunderball.getId(), SingleRoom.class);
            roomService.createRoom(380.0, new RoomIdentifier("Wave Block", 1, "402"), hotelThunderball.getId(), DoubleRoom.class);

            // You Only Live Twice (1967)
            HotelLocation locYOLT = new HotelLocation.HotelLocationBuilder()
                    .withId(0L)
                    .withAddress("Rebirth Road 7")
                    .withCity("Tokyo")
                    .withCountry("Japan")
                    .build();
            Hotel hotelYOLT = hotelService.createHotel("You Only Live Twice Hotel", "A rebirth of luxury (1967)", locYOLT);
            roomService.createRoom(230.0, new RoomIdentifier("Zen Wing", 1, "501"), hotelYOLT.getId(), SingleRoom.class);
            roomService.createRoom(370.0, new RoomIdentifier("Zen Wing", 1, "502"), hotelYOLT.getId(), DoubleRoom.class);

            // On Her Majesty's Secret Service (1969)
            HotelLocation locOHMSS = new HotelLocation.HotelLocationBuilder()
                    .withId(0L)
                    .withAddress("Royal Lane 15")
                    .withCity("Geneva")
                    .withCountry("Switzerland")
                    .build();
            Hotel hotelOHMSS = hotelService.createHotel("Secret Service Suites", "Royalty meets espionage (1969)", locOHMSS);
            roomService.createRoom(250.0, new RoomIdentifier("Crown Wing", 1, "601"), hotelOHMSS.getId(), SingleRoom.class);
            roomService.createRoom(400.0, new RoomIdentifier("Crown Wing", 1, "602"), hotelOHMSS.getId(), DoubleRoom.class);

            // Diamonds Are Forever (1971)
            HotelLocation locDiamonds = new HotelLocation.HotelLocationBuilder()
                    .withId(0L)
                    .withAddress("Gemstone Avenue 8")
                    .withCity("Paris")
                    .withCountry("France")
                    .build();
            Hotel hotelDiamonds = hotelService.createHotel("Diamonds Forever Hotel", "Sparkle and elegance (1971)", locDiamonds);
            roomService.createRoom(260.0, new RoomIdentifier("Crystal Wing", 1, "701"), hotelDiamonds.getId(), SingleRoom.class);
            roomService.createRoom(420.0, new RoomIdentifier("Crystal Wing", 1, "702"), hotelDiamonds.getId(), DoubleRoom.class);

            // Live and Let Die (1973)
            HotelLocation locLLLD = new HotelLocation.HotelLocationBuilder()
                    .withId(0L)
                    .withAddress("Rebel Road 9")
                    .withCity("New Orleans")
                    .withCountry("USA")
                    .build();
            Hotel hotelLLLD = hotelService.createHotel("Live and Let Die Lodge", "Bold escapes await (1973)", locLLLD);
            roomService.createRoom(270.0, new RoomIdentifier("Rebel Block", 1, "801"), hotelLLLD.getId(), SingleRoom.class);
            roomService.createRoom(430.0, new RoomIdentifier("Rebel Block", 1, "802"), hotelLLLD.getId(), DoubleRoom.class);

            // The Man with the Golden Gun (1974)
            HotelLocation locGoldenGun = new HotelLocation.HotelLocationBuilder()
                    .withId(0L)
                    .withAddress("Bullet Boulevard 11")
                    .withCity("Bangkok")
                    .withCountry("Thailand")
                    .build();
            Hotel hotelGoldenGun = hotelService.createHotel("Golden Gun Hotel", "Precision and luxury (1974)", locGoldenGun);
            roomService.createRoom(280.0, new RoomIdentifier("Strike Wing", 1, "901"), hotelGoldenGun.getId(), SingleRoom.class);
            roomService.createRoom(450.0, new RoomIdentifier("Strike Wing", 1, "902"), hotelGoldenGun.getId(), DoubleRoom.class);

            // The Spy Who Loved Me (1977)
            HotelLocation locSpyLovedMe = new HotelLocation.HotelLocationBuilder()
                    .withId(0L)
                    .withAddress("Oceanic Avenue 12")
                    .withCity("Sydney")
                    .withCountry("Australia")
                    .build();
            Hotel hotelSpyLovedMe = hotelService.createHotel("Spy Who Loved Me Resort", "Underwater elegance (1977)", locSpyLovedMe);
            roomService.createRoom(300.0, new RoomIdentifier("Aqua Wing", 1, "1001"), hotelSpyLovedMe.getId(), SingleRoom.class);
            roomService.createRoom(480.0, new RoomIdentifier("Aqua Wing", 1, "1002"), hotelSpyLovedMe.getId(), DoubleRoom.class);

            // Moonraker (1979)
            HotelLocation locMoonraker = new HotelLocation.HotelLocationBuilder()
                    .withId(0L)
                    .withAddress("Sky High Road 3")
                    .withCity("Dubai")
                    .withCountry("UAE")
                    .build();
            Hotel hotelMoonraker = hotelService.createHotel("Moonraker Heights", "Space meets luxury (1979)", locMoonraker);
            roomService.createRoom(310.0, new RoomIdentifier("Sky Wing", 1, "1101"), hotelMoonraker.getId(), SingleRoom.class);
            roomService.createRoom(500.0, new RoomIdentifier("Sky Wing", 1, "1102"), hotelMoonraker.getId(), DoubleRoom.class);

            // For Your Eyes Only (1981)
            HotelLocation locFYE = new HotelLocation.HotelLocationBuilder()
                    .withId(0L)
                    .withAddress("Vision Boulevard 6")
                    .withCity("Barcelona")
                    .withCountry("Spain")
                    .build();
            Hotel hotelFYE = hotelService.createHotel("For Your Eyes Only Inn", "Exclusive elegance (1981)", locFYE);
            roomService.createRoom(320.0, new RoomIdentifier("Vision Block", 1, "1201"), hotelFYE.getId(), SingleRoom.class);
            roomService.createRoom(520.0, new RoomIdentifier("Vision Block", 1, "1202"), hotelFYE.getId(), DoubleRoom.class);

            // Octopussy (1983)
            HotelLocation locOctopussy = new HotelLocation.HotelLocationBuilder()
                    .withId(0L)
                    .withAddress("Tentacle Street 4")
                    .withCity("Mumbai")
                    .withCountry("India")
                    .build();
            Hotel hotelOctopussy = hotelService.createHotel("Octopussy Oasis", "Exotic allure (1983)", locOctopussy);
            roomService.createRoom(330.0, new RoomIdentifier("Tentacle Wing", 1, "1301"), hotelOctopussy.getId(), SingleRoom.class);
            roomService.createRoom(540.0, new RoomIdentifier("Tentacle Wing", 1, "1302"), hotelOctopussy.getId(), DoubleRoom.class);

            // A View to a Kill (1985)
            HotelLocation locAVTAK = new HotelLocation.HotelLocationBuilder()
                    .withId(0L)
                    .withAddress("Highrise Avenue 8")
                    .withCity("Los Angeles")
                    .withCountry("USA")
                    .build();
            Hotel hotelAVTAK = hotelService.createHotel("A View to a Kill Hotel", "Sky-high luxury (1985)", locAVTAK);
            roomService.createRoom(340.0, new RoomIdentifier("Skyline Wing", 1, "1401"), hotelAVTAK.getId(), SingleRoom.class);
            roomService.createRoom(560.0, new RoomIdentifier("Skyline Wing", 1, "1402"), hotelAVTAK.getId(), DoubleRoom.class);

            // The Living Daylights (1987)
            HotelLocation locLivingDaylights = new HotelLocation.HotelLocationBuilder()
                    .withId(0L)
                    .withAddress("Dawn Street 2")
                    .withCity("Berlin")
                    .withCountry("Germany")
                    .build();
            Hotel hotelLivingDaylights = hotelService.createHotel("Living Daylights Lodge", "A new dawn (1987)", locLivingDaylights);
            roomService.createRoom(350.0, new RoomIdentifier("Dawn Wing", 1, "1501"), hotelLivingDaylights.getId(), SingleRoom.class);
            roomService.createRoom(580.0, new RoomIdentifier("Dawn Wing", 1, "1502"), hotelLivingDaylights.getId(), DoubleRoom.class);

            // Licence to Kill (1989)
            HotelLocation locLicence = new HotelLocation.HotelLocationBuilder()
                    .withId(0L)
                    .withAddress("Danger Drive 5")
                    .withCity("Miami")
                    .withCountry("USA")
                    .build();
            Hotel hotelLicence = hotelService.createHotel("Licence to Kill Lodge", "On the edge of danger (1989)", locLicence);
            roomService.createRoom(360.0, new RoomIdentifier("Danger Wing", 1, "1601"), hotelLicence.getId(), SingleRoom.class);
            roomService.createRoom(600.0, new RoomIdentifier("Danger Wing", 1, "1602"), hotelLicence.getId(), DoubleRoom.class);

            // GoldenEye (1995)
            HotelLocation locGoldenEye = new HotelLocation.HotelLocationBuilder()
                    .withId(0L)
                    .withAddress("Spyglass Street 11")
                    .withCity("St. Petersburg")
                    .withCountry("Russia")
                    .build();
            Hotel hotelGoldenEye = hotelService.createHotel("GoldenEye Grand", "Modern espionage (1995)", locGoldenEye);
            roomService.createRoom(380.0, new RoomIdentifier("Spy Wing", 1, "1701"), hotelGoldenEye.getId(), SingleRoom.class);
            roomService.createRoom(650.0, new RoomIdentifier("Spy Wing", 1, "1702"), hotelGoldenEye.getId(), DoubleRoom.class);

            // Tomorrow Never Dies (1997)
            HotelLocation locTomorrow = new HotelLocation.HotelLocationBuilder()
                    .withId(0L)
                    .withAddress("Futurist Avenue 9")
                    .withCity("Singapore")
                    .withCountry("Singapore")
                    .build();
            Hotel hotelTomorrow = hotelService.createHotel("Tomorrow Never Dies Inn", "Where future meets intrigue (1997)", locTomorrow);
            roomService.createRoom(390.0, new RoomIdentifier("Future Wing", 1, "1801"), hotelTomorrow.getId(), SingleRoom.class);
            roomService.createRoom(670.0, new RoomIdentifier("Future Wing", 1, "1802"), hotelTomorrow.getId(), DoubleRoom.class);

            // The World Is Not Enough (1999)
            HotelLocation locWorld = new HotelLocation.HotelLocationBuilder()
                    .withId(0L)
                    .withAddress("Globe Road 7")
                    .withCity("Istanbul")
                    .withCountry("Turkey")
                    .build();
            Hotel hotelWorld = hotelService.createHotel("The World Is Not Enough Hotel", "A global escape (1999)", locWorld);
            roomService.createRoom(400.0, new RoomIdentifier("Global Wing", 1, "1901"), hotelWorld.getId(), SingleRoom.class);
            roomService.createRoom(700.0, new RoomIdentifier("Global Wing", 1, "1902"), hotelWorld.getId(), DoubleRoom.class);

            // Die Another Day (2002)
            HotelLocation locAnotherDay = new HotelLocation.HotelLocationBuilder()
                    .withId(0L)
                    .withAddress("Edge Street 4")
                    .withCity("Seoul")
                    .withCountry("South Korea")
                    .build();
            Hotel hotelAnotherDay = hotelService.createHotel("Die Another Day Hotel", "Reinventing espionage (2002)", locAnotherDay);
            roomService.createRoom(410.0, new RoomIdentifier("Edge Wing", 1, "2001"), hotelAnotherDay.getId(), SingleRoom.class);
            roomService.createRoom(720.0, new RoomIdentifier("Edge Wing", 1, "2002"), hotelAnotherDay.getId(), DoubleRoom.class);

            // Casino Royale (2006)
            HotelLocation locCasino = new HotelLocation.HotelLocationBuilder()
                    .withId(0L)
                    .withAddress("Casino Street 99")
                    .withCity("Monte Carlo")
                    .withCountry("Monaco")
                    .build();
            Hotel hotelCasino = hotelService.createHotel("Casino Royale Hotel", "Luck and luxury (2006)", locCasino);
            roomService.createRoom(420.0, new RoomIdentifier("Casino Wing", 1, "2101"), hotelCasino.getId(), SingleRoom.class);
            roomService.createRoom(750.0, new RoomIdentifier("Casino Wing", 1, "2102"), hotelCasino.getId(), DoubleRoom.class);

            // Quantum of Solace (2008)
            HotelLocation locQuantum = new HotelLocation.HotelLocationBuilder()
                    .withId(0L)
                    .withAddress("Quantum Road 5")
                    .withCity("Rome")
                    .withCountry("Italy")
                    .build();
            Hotel hotelQuantum = hotelService.createHotel("Quantum of Solace Suites", "Intense and refined (2008)", locQuantum);
            roomService.createRoom(430.0, new RoomIdentifier("Quantum Wing", 1, "2201"), hotelQuantum.getId(), SingleRoom.class);
            roomService.createRoom(780.0, new RoomIdentifier("Quantum Wing", 1, "2202"), hotelQuantum.getId(), DoubleRoom.class);

            // Skyfall (2012)
            HotelLocation locSkyfall = new HotelLocation.HotelLocationBuilder()
                    .withId(0L)
                    .withAddress("Heritage Street 12")
                    .withCity("Scotland")
                    .withCountry("UK")
                    .build();
            Hotel hotelSkyfall = hotelService.createHotel("Skyfall Castle Hotel", "A legacy of grandeur (2012)", locSkyfall);
            roomService.createRoom(450.0, new RoomIdentifier("Heritage Wing", 1, "2301"), hotelSkyfall.getId(), SingleRoom.class);
            roomService.createRoom(800.0, new RoomIdentifier("Heritage Wing", 1, "2302"), hotelSkyfall.getId(), DoubleRoom.class);

            // Spectre (2015)
            HotelLocation locSpectre = new HotelLocation.HotelLocationBuilder()
                    .withId(0L)
                    .withAddress("Secret Way 8")
                    .withCity("Vienna")
                    .withCountry("Austria")
                    .build();
            Hotel hotelSpectre = hotelService.createHotel("Spectre Suites", "Dark elegance (2015)", locSpectre);
            roomService.createRoom(460.0, new RoomIdentifier("Shadow Wing", 1, "2401"), hotelSpectre.getId(), SingleRoom.class);
            roomService.createRoom(820.0, new RoomIdentifier("Shadow Wing", 1, "2402"), hotelSpectre.getId(), DoubleRoom.class);

            // No Time to Die (2021)
            HotelLocation locNoTime = new HotelLocation.HotelLocationBuilder()
                    .withId(0L)
                    .withAddress("Finale Road 1")
                    .withCity("Dubai")
                    .withCountry("UAE")
                    .build();
            Hotel hotelNoTime = hotelService.createHotel("No Time to Die Resort", "The ultimate farewell (2021)", locNoTime);
            roomService.createRoom(480.0, new RoomIdentifier("Finale Wing", 1, "2501"), hotelNoTime.getId(), SingleRoom.class);
            roomService.createRoom(850.0, new RoomIdentifier("Finale Wing", 1, "2502"), hotelNoTime.getId(), DoubleRoom.class);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
