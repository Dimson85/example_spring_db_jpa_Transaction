package ru.learnup.db.entity;


import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "premiere")
public class Premiere {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "age_limit")
    private String ageLimit;

    @Column(name = "number_seats")
    private int numberOfSeats;

    @OneToMany(mappedBy = "premiere", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<Ticket> ticketList;

    public Premiere(String title, String description, String ageLimit, int numberOfSeats) {
        this.title = title;
        this.description = description;
        this.ageLimit = ageLimit;
        this.numberOfSeats = numberOfSeats;
        this.ticketList = new ArrayList<>();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Premiere premiere = (Premiere) o;
        return Objects.equals(id, premiere.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}