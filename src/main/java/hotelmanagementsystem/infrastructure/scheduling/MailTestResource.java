/*
package hotelmanagementsystem.infrastructure.scheduling;

import io.quarkus.mailer.Mail;
import io.quarkus.mailer.Mailer;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/test-email")
public class MailTestResource {

    @Inject
    Mailer mailer;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String sendTestEmail() {
        try {
            mailer.send(
                    Mail.withText("chmodel02@gmail.com", "Test-Email", "Hallo, dies ist eine Test-Mail von Quarkus!")
            );
            return "✅ Test-E-Mail wurde gesendet!";
        } catch (Exception e) {
            return "❌ Fehler beim Senden: " + e.getMessage();
        }
    }
}
*/
