package com.sharonghranui.roomgenius.data.entity;

import com.sharonghranui.roomgenius.data.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "rg_hotel")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Hotel extends BaseEntity {

    private String name;

    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Staff> staffs = new LinkedHashSet<>();

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = "hotel", orphanRemoval = true)
    private Set<HotelRoom> hotelRooms = new LinkedHashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Hotel hotel = (Hotel) o;
        return id != null && Objects.equals(id, hotel.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
