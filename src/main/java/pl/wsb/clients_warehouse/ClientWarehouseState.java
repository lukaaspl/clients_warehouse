package pl.wsb.clients_warehouse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClientWarehouseState extends HashMap<SupportedMetalType, Double> {
    public void add(SupportedMetalType metalType, double mass) {
        if (this.containsKey(metalType)) {
            this.put(metalType, this.get(metalType) + mass);
        } else {
            this.put(metalType, mass);
        }
    }

    public double getTotalVolume() {
        double totalVolume = 0.0;

        for (Map.Entry<SupportedMetalType, Double> entry : this.entrySet()) {
            totalVolume += entry.getKey().getVolume(entry.getValue());
        }

        return totalVolume;
    }

    public List<SupportedMetalType> getStoredMetalTypes() {
        List<SupportedMetalType> storedMetalTypes = new ArrayList<SupportedMetalType>();

        for (Map.Entry<SupportedMetalType, Double> entry : this.entrySet()) {
            storedMetalTypes.add(entry.getKey());
        }

        return storedMetalTypes;
    }
}