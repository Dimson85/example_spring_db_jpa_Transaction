package ru.learnup.db.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="buyers_name")
    String buyersName;

    @Column(name = "title_premiere")
    String titlePremiere;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_premiere")
    private Premiere premiere;


    public Ticket(Premiere premiere,String buyersName) {
        this.titlePremiere = premiere.getTitle();
        this.buyersName = buyersName;
        this.premiere = premiere;
    }


    @Override
    public String toString() {
        return "Ticket{" +
                "buyersName='" + buyersName + '\'' +
                ", titlePremiere='" + titlePremiere + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Ticket ticket = (Ticket) o;
        return Objects.equals(id, ticket.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
