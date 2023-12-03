package pl.wsb.clients_warehouse;

import java.time.LocalDate;
import java.util.ArrayList;

public class ClientsImpl implements Clients {
  ArrayList<Client> clients = new ArrayList<Client>();

  @Override
  public String createNewClient(String firstName, String lastName) {
    Client client = new Client(firstName, lastName);
    this.clients.add(client);
    return client.id;
  }

  @Override
  public String activatePremiumAccount(String clientId) {
    Client client = this.getClientById(clientId);
    client.hasPremium = true;
    return client.id;
  }

  @Override
  public String getClientFullName(String clientId) {
    Client client = this.getClientById(clientId);
    return client.firstName + " " + client.lastName;
  }

  @Override
  public LocalDate getClientCreationDate(String clientId) {
    Client client = this.getClientById(clientId);
    return client.createdAt;

  }

  @Override
  public boolean isPremiumClient(String clientId) {
    Client client = this.getClientById(clientId);
    return client.hasPremium;
  }

  @Override
  public int getNumberOfClients() {
    return this.clients.size();
  }

  @Override
  public int getNumberOfPremiumClients() {
    int count = 0;

    for (Client user : this.clients) {
      if (user.hasPremium) {
        count = count + 1;
      }
    }

    return count;
  }

  private Client getClientById(String clientId) {
    for (Client user : this.clients) {
      if (user.id == clientId) {
        return user;
      }
    }

    throw new ClientNotFoundException();
  }
}
