package pl.wsb.clients_warehouse;

public class Main {
    public static void main(String[] args) {
        Clients clients = new ClientsImpl();
        WarehouseWithRegistration warehouse = new WarehouseWithRegistration(30);

        String johnId = clients.createNewClient("John", "Doe");
        String nickId = clients.createNewClient("Nick", "Field");

        warehouse.registerClient(johnId);
        warehouse.registerClient(nickId);

        warehouse.addMetalIngot(johnId, SupportedMetalType.GOLD, 20_000);
        warehouse.addMetalIngot(johnId, SupportedMetalType.SILVER, 50);
        warehouse.addMetalIngot(johnId, SupportedMetalType.SILVER, 300);
        warehouse.addMetalIngot(johnId, SupportedMetalType.SILVER, 400);
        warehouse.addMetalIngot(johnId, SupportedMetalType.PLATINUM, 30);
        warehouse.addMetalIngot(johnId, SupportedMetalType.TIN, 100_000);

        warehouse.addMetalIngot(nickId, SupportedMetalType.COPPER, 100_000);
        warehouse.addMetalIngot(nickId, SupportedMetalType.TUNGSTEN, 90_000);

        System.out.println(warehouse.getMetalTypesToMassStoredByClient(johnId));
        System.out.println(warehouse.getTotalVolumeOccupiedByClient(johnId));
        System.out.println(warehouse.getTotalVolumeOccupiedByClient(nickId));
        System.out.println(warehouse.getStoredMetalTypesByClient(johnId));

        // String janId = clients.createNewClient("Jan", "Kowalski");
        // String piotrId = clients.createNewClient("Piotr", "Nowak");
        // String alaId = clients.createNewClient("Ala", "Kowalska");

        // clients.activatePremiumAccount(piotrId);
        // clients.activatePremiumAccount(alaId);

        // System.out.println("Total clients: " + clients.getNumberOfClients());
        // System.out.println("Premium clients: " +
        // clients.getNumberOfPremiumClients());
    }
}