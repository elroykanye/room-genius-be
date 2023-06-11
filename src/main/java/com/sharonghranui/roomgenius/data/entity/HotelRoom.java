package com.sharonghranui.roomgenius.data.entity;

import com.sharonghranui.roomgenius.data.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "rg_hotel_room")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class HotelRoom extends BaseEntity {
    private  String number;


    @OneToMany(mappedBy = "hotelRoom", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Reservation> reservations = new LinkedHashSet<>();

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "hotel_id", nullable = false)
    private Hotel hotel;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        HotelRoom hotelRoom = (HotelRoom) o;
        return id != null && Objects.equals(id, hotelRoom.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
