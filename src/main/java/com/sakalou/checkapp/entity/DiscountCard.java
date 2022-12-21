package com.sakalou.checkapp.entity;

import com.sakalou.checkapp.entity.level.Level;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

/**
 * entity for discount card
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class DiscountCard {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    private Level level;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DiscountCard that)) return false;
        return Objects.equals(id, that.id) && level == that.level;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, level);
    }

    @Override
    public String toString() {
        return "DiscountCard{" +
                "id=" + id +
                ", level=" + level +
                '}';
    }
}
