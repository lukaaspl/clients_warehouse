package pl.wsb.clients_warehouse;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ClientsImplTest {
    Clients clients = new ClientsImpl();
    String clientId = clients.createNewClient("John", "Doe");

    @Test
    void testIfClientHasBeenCreated() {
        Assertions.assertEquals("John Doe", clients.getClientFullName(clientId));
    }

    @Test
    void testIfMultipleClientsCanBeCreated() {
        clients.createNewClient("Jane", "Doe");
        Assertions.assertEquals(2, clients.getNumberOfClients());
    }

    @Test
    void testIfNewClientStartsWithoutPremiumAccount() {
        Assertions.assertFalse(clients.isPremiumClient(clientId));
    }

    @Test
    void testIfPremiumAccountCanBeActivated() {
        Assertions.assertEquals(0, clients.getNumberOfPremiumClients());
        clients.activatePremiumAccount(clientId);
        Assertions.assertTrue(clients.isPremiumClient(clientId));
        Assertions.assertEquals(1, clients.getNumberOfPremiumClients());
    }

    @Test
    void testIfCreationDateIsCorrect() {
        LocalDate currentDate = LocalDate.now();
        Assertions.assertEquals(currentDate.toString(), clients.getClientCreationDate(clientId).toString());
    }
}
