package pro.ganyushkin.springdatajpamultipleds.repository.pg;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pro.ganyushkin.springdatajpamultipleds.entity.pg.TextChunkEntry;
import pro.ganyushkin.springdatajpamultipleds.entity.shared.PlantEntry;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PgTextChunkRepository extends CrudRepository<TextChunkEntry, UUID> {
    List<TextChunkEntry> findAllByPlant(PlantEntry plant);
    Optional<TextChunkEntry> findFirstByPlant(PlantEntry plant);
}
