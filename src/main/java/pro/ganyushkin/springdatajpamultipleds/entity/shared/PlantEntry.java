package pro.ganyushkin.springdatajpamultipleds.entity.shared;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

// Shared entity. Will be stored in PostgreSQL and MySQL databases.

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "plant")
public class PlantEntry {

    @Id
    @Type(type="uuid-char")
    private UUID id;

    @Column
    private String name;

    @Column
    private Boolean available;
}
