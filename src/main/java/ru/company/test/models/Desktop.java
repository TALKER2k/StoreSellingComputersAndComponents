package ru.company.test.models;

import com.fasterxml.jackson.annotation.JsonTypeName;
import jakarta.persistence.Entity;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.*;
import lombok.experimental.Accessors;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@ToString
@JsonTypeName("DESKTOP")
public class Desktop extends Product {
    private String formFactor;

    @PrePersist
    @PreUpdate
    public void setProductType() {
        this.setProductType(ProductType.DESKTOP);
    }
}
