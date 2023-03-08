package ua.stupin.analyticCentre.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "devices")
public class Device {
    @Id
    @Column
    private String model;
    @Column
    private String type;
    @OneToMany(mappedBy = "device", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Subscriber> subscribers;

    public Device(String model, String type) {
        this.model = model;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Device{" +
                "model='" + model + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

}
