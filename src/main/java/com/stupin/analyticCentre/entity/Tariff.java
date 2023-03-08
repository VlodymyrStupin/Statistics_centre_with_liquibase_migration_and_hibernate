package ua.stupin.analyticCentre.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "tariffs")
public class Tariff {
    @Id
    @Column(name = "tariff")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String tariff;
    @Column(name = "price")
    private int price;
    @OneToMany(mappedBy = "tariff", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Subscriber> subscribers;

    public Tariff(String tariff, int price) {
        this.tariff = tariff;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Tariff{" +
                "tariff='" + tariff + '\'' +
                ", price=" + price +
                '}';
    }

}
