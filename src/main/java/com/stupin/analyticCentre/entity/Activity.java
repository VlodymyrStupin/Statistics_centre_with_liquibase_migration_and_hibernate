package ua.stupin.analyticCentre.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "activities")
public class Activity {
    @Id
    @Column(name = "activity_id")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    @Column(name = "date_of_activities")
    private LocalDateTime date;
    @Column(name = "type_of_activities")
    private String type;
    @Column(name = "log_of_activities")
    private String logOfActivities;
    @ManyToOne
    @JoinColumn(name = "subscriber_id")
    private Subscriber subscriber;

    public Activity(LocalDateTime date, String type, String logOfActivities) {
        this.date = date;
        this.type = type;
        this.logOfActivities = logOfActivities;
    }

    @Override
    public String toString() {
        return "Activity{" +
                "id='" + id + '\'' +
                ", date=" + date +
                ", type='" + type + '\'' +
                ", logOfActivities='" + logOfActivities + '\'' +
                ", subscriber_id='" + subscriber.getId() +
                "'}";
    }

}
