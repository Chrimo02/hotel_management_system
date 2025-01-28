package hotelmanagementsystem.infrastructure.api.grpc.impl;

import io.grpc.Server;
import io.grpc.netty.NettyServerBuilder;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.io.IOException;

@ApplicationScoped
public class GrpcServer {

	// Injektion der gRPC-Service-Implementierungen ohne private Modifier
	@Inject
	BookingServiceGrpcImpl bookingGrpc;

	@Inject
	GuestServiceGrpcImpl guestGrpc;

	@Inject
	HotelServiceGrpcImpl hotelGrpc;

	@Inject
	RoomServiceGrpcImpl roomGrpc;

	// Port aus der Konfiguration lesen, Standardwert 8888
	@ConfigProperty(name = "grpc.server.port", defaultValue = "8888")
	int port;

	private Server server;

	/**
	 * Diese Methode wird nach dem Konstruktor
	 * von CDI automatisch aufgerufen (@PostConstruct).
	 * Hier wird der gRPC-Server gebaut und gestartet.
	 */
	@PostConstruct
	public void init() {
		try {
			server = NettyServerBuilder
					.forPort(port)
					.addService(bookingGrpc)
					.addService(guestGrpc)
					.addService(hotelGrpc)
					.addService(roomGrpc)
					.build()
					.start();
			System.out.println("gRPC-Server läuft auf Port " + port);
		} catch (IOException e) {
			System.err.println("Fehler beim Starten des gRPC-Servers auf Port " + port);
			throw new RuntimeException("Failed to start gRPC server", e);
		}
	}

	/**
	 * Wird von CDI gerufen, wenn der Context beendet wird (@PreDestroy).
	 * Hier wird der gRPC-Server ordnungsgemäß heruntergefahren.
	 */
	@PreDestroy
	public void stop() {
		if (server != null) {
			System.out.println("Shutting down gRPC server...");
			server.shutdown();
			System.out.println("gRPC-Server wurde heruntergefahren.");
		}
	}
}
