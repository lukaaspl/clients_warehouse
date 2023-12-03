package pl.wsb.clients_warehouse;

import java.time.LocalDate;
import java.util.UUID;

public class Client {
    String id;
    String firstName;
    String lastName;
    Boolean hasPremium;
    LocalDate createdAt;

    public Client(String firstName, String lastName) {
        this.id = UUID.randomUUID().toString();
        this.firstName = firstName;
        this.lastName = lastName;
        this.hasPremium = false;
        this.createdAt = LocalDate.now();
    }
}