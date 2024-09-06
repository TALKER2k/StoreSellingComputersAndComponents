package ru.company.test.models;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@ToString
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Desktop.class, name = "DESKTOP"),
        @JsonSubTypes.Type(value = Laptop.class, name = "LAPTOP"),
        @JsonSubTypes.Type(value = Monitor.class, name = "MONITOR"),
        @JsonSubTypes.Type(value = HardDrive.class, name = "HARD_DRIVE")
})
public abstract class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String serialNumber;
    private String manufacturer;
    private Double price;
    @Column(name = "STOCK_QUANTITY")
    private Integer quantity;
    @Enumerated(EnumType.STRING)
    private ProductType productType;

    public enum ProductType {
        DESKTOP, LAPTOP, MONITOR, HARD_DRIVE
    }
}
