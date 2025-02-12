package fit_zone.domain;

import java.util.Objects;

public class Client {
    private int id;
    private String name;
    private String lastName;
    private int memebership;

    public Client() {
    }

    public Client(int id) {
        this.id = id;
    }

    public Client(String name, String lastName, int memebership) {
        this.name = name;
        this.lastName = lastName;
        this.memebership = memebership;
    }

    public Client(int id, String name, String lastName, int memebership) {
        this(name, lastName, memebership);
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return id == client.id && memebership == client.memebership && Objects.equals(name, client.name) && Objects.equals(lastName, client.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, lastName, memebership);
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", memebership=" + memebership +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getMemebership() {
        return memebership;
    }

    public void setMemebership(int memebership) {
        this.memebership = memebership;
    }
}
