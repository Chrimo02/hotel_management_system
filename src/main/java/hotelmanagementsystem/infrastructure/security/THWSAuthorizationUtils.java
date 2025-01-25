package hotelmanagementsystem.infrastructure.security;

import de.thws.adapter.exceptions.AuthenticationException;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import static de.thws.adapter.authentification.AuthSettings.AUTH_SERVER_URL;
import static de.thws.adapter.authentification.AuthSettings.TOKEN_HEADER_NAME;

public class THWSAuthorizationUtils {
    /**
     * Validates the provided token and retrieves the associated card number.
     *
     * @param token the token to validate
     * @return the card number associated with the token
     * @throws AuthenticationException if the token validation fails
     */
    public static String validateTokenAndGetCardNumber(String token) throws AuthenticationException {

        try (final HttpClient httpClient = HttpClient.newHttpClient()) {
            if (token == null || token.isEmpty()) {
                throw new AuthenticationException("Token is NULL");
            }
            if (token.equals(AuthSettings.TESTPERMATOKEN) && AuthSettings.BYPASS_AUTH_WITH_TESTUSER) {
                return AuthSettings.TESTUSERCARDNUMBER;
            }
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(AUTH_SERVER_URL))
                    .header("Authorization", "Bearer " + token)
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                return response.body().replace('{', ' ').replace('}', ' ').split(",")[0].split(":")[1].trim().replace("\"", "");
            } else {
                throw new RuntimeException("Token validation failed");
            }
        } catch (Exception e) {
            throw new AuthenticationException("Token validation failed", e);
        }
    }

    /**
     * Authenticates a user with the given username and password.
     * Sends a request to the authentication server and returns a token if the credentials are valid.
     *
     * @param username the username of the user
     * @param password the password of the user
     * @return the authentication token if the credentials are valid
     * @throws AuthenticationException if the authentication fails
     */
    public static String authenticate(String username, String password) throws AuthenticationException {
        try (final HttpClient httpClient = HttpClient.newHttpClient()) {
            if (username.equals(AuthSettings.TESTUSERNAME) && password.equals(AuthSettings.TESTPASSWORD) && AuthSettings.BYPASS_AUTH_WITH_TESTUSER) {
                return AuthSettings.TESTPERMATOKEN;
            }

            String auth = username + ":" + password;
            String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes(StandardCharsets.UTF_8));

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(AUTH_SERVER_URL))
                    .header("Authorization", "Basic " + encodedAuth)
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                String token = response.headers().map().get(TOKEN_HEADER_NAME).getFirst(); //extract the token from the response header
                return token;
            } else {
                throw new RuntimeException("Authentication failed, Username or Password are not Valid");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new AuthenticationException("Authentication failed, Username or Password are not Valid", e);
        }
    }
}
