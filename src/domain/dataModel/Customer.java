package domain.dataModel;

import domain.enums.ClientType;

public class Customer {
    private Long id;
    private String name;
    private ClientType tier;

    public Customer(Long id, String name, ClientType tier) {
        this.id = id;
        this.name = name;
        this.tier = tier;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ClientType getTier() {
        return tier;
    }

    public void setTier(ClientType tier) {
        this.tier = tier;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", tier=" + tier +
                '}';
    }
}
