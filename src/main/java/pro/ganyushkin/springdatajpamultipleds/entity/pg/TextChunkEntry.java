package pro.ganyushkin.springdatajpamultipleds.entity.pg;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import pro.ganyushkin.springdatajpamultipleds.entity.shared.PlantEntry;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "plant_text_chunk", schema = "text")
public class TextChunkEntry {

    @Id
    @Type(type="uuid-char")
    private UUID id;

    @Column(columnDefinition = "TEXT")
    private String text;

    @ManyToOne
    private PlantEntry plant;
}
