package pl.wsb.clients_warehouse;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class WarehouseWithRegistrationTest {
    Clients clients = new ClientsImpl();
    String clientId = clients.createNewClient("John", "Doe");
    WarehouseWithRegistration warehouse = new WarehouseWithRegistration(10);

    @BeforeEach
    void setUp() {
        warehouse.registerClient(clientId);
    }

    @Test
    void testIfMetalIngotCanBeAdded() {
        Assertions.assertEquals(0, warehouse.getTotalVolumeOccupiedByClient(clientId));
        warehouse.addMetalIngot(clientId, SupportedMetalType.GOLD, 1);
        Assertions.assertTrue(warehouse.getTotalVolumeOccupiedByClient(clientId) > 0);
    }

    @Test
    void testIfThrowsErrorWhenClientIsNotRegistered() {
        Assertions.assertThrows(ClientNotFoundException.class, () -> {
            warehouse.addMetalIngot("notRegisteredClientId", SupportedMetalType.GOLD, 1);
        });
    }

    @Test
    void testIfThrowsErrorWhenWarehouseIsFull() {
        Assertions.assertThrows(FullWarehouseException.class, () -> {
            warehouse.addMetalIngot(clientId, SupportedMetalType.GOLD, 100_000_000);
        });
    }

    @Test
    void testIfMultipleMetalIngotsCanBeAddedForSingleClient() {
        warehouse.addMetalIngot(clientId, SupportedMetalType.GOLD, 1);
        warehouse.addMetalIngot(clientId, SupportedMetalType.GOLD, 1);
        warehouse.addMetalIngot(clientId, SupportedMetalType.GOLD, 1);
        Assertions.assertEquals(3, warehouse.getMetalTypesToMassStoredByClient(clientId).get(SupportedMetalType.GOLD));
    }

    @Test
    void testIfListsAllMetalTypesStoredByClient() {
        warehouse.addMetalIngot(clientId, SupportedMetalType.GOLD, 1);
        warehouse.addMetalIngot(clientId, SupportedMetalType.SILVER, 1);
        warehouse.addMetalIngot(clientId, SupportedMetalType.PLATINUM, 1);

        List<SupportedMetalType> metalTypes = warehouse.getStoredMetalTypesByClient(clientId);

        Assertions.assertTrue(metalTypes.size() == 3);
        Assertions.assertTrue(metalTypes.contains(SupportedMetalType.GOLD));
        Assertions.assertTrue(metalTypes.contains(SupportedMetalType.SILVER));
        Assertions.assertTrue(metalTypes.contains(SupportedMetalType.PLATINUM));
    }
}
