package pl.wsb.clients_warehouse;

import java.util.Map;
import java.util.HashMap;
import java.util.List;

public class WarehouseWithRegistration implements Warehouse {
    Map<String, ClientWarehouseState> clientIdToWarehouseState = new HashMap<String, ClientWarehouseState>();
    double maxCapacity;

    public WarehouseWithRegistration(double maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public void registerClient(String clientId) {
        this.clientIdToWarehouseState.put(clientId, new ClientWarehouseState());
    }

    @Override
    public void addMetalIngot(String clientId, SupportedMetalType metalType, double mass)
            throws ClientNotFoundException, ProhibitedMetalTypeException, FullWarehouseException {
        if (!this.clientIdToWarehouseState.containsKey(clientId)) {
            throw new ClientNotFoundException();
        }

        if (!SupportedMetalType.isValid(metalType.name())) {
            throw new ProhibitedMetalTypeException();
        }

        if (!canContainAnotherIngot(metalType, mass)) {
            throw new FullWarehouseException();
        }

        this.clientIdToWarehouseState.get(clientId).add(metalType, mass);
    }

    @Override
    public Map<SupportedMetalType, Double> getMetalTypesToMassStoredByClient(String clientId)
            throws ClientNotFoundException {
        if (this.clientIdToWarehouseState.containsKey(clientId)) {
            return this.clientIdToWarehouseState.get(clientId);
        }

        return this.clientIdToWarehouseState.get(clientId);
    }

    @Override
    public double getTotalVolumeOccupiedByClient(String clientId) throws ClientNotFoundException {
        if (!this.clientIdToWarehouseState.containsKey(clientId)) {
            throw new ClientNotFoundException();
        }

        return this.clientIdToWarehouseState.get(clientId).getTotalVolume();
    }

    @Override
    public List<SupportedMetalType> getStoredMetalTypesByClient(String clientId) throws ClientNotFoundException {
        if (!this.clientIdToWarehouseState.containsKey(clientId)) {
            throw new ClientNotFoundException();
        }

        return this.clientIdToWarehouseState.get(clientId).getStoredMetalTypes();
    }

    private boolean canContainAnotherIngot(SupportedMetalType metalType, double mass) {
        return this.getTotalOccupiedVolume() + metalType.getVolume(mass) <= this.maxCapacity;
    }

    private double getTotalOccupiedVolume() {
        double totalOccupiedVolume = 0.0;

        for (ClientWarehouseState state : this.clientIdToWarehouseState.values()) {
            totalOccupiedVolume += state.getTotalVolume();
        }

        return totalOccupiedVolume;
    }
}
