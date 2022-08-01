package pro.ganyushkin.springdatajpamultipleds.repository.mysql;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pro.ganyushkin.springdatajpamultipleds.entity.shared.PlantEntry;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface MySQLPlantRepository extends CrudRepository<PlantEntry, UUID> {
    List<PlantEntry> findAllByAvailableIsTrue();
    Optional<PlantEntry> findByAvailableIsTrueAndId(UUID id);
}
