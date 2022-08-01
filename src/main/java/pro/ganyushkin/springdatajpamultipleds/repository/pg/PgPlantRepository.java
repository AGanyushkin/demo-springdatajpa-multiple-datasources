package pro.ganyushkin.springdatajpamultipleds.repository.pg;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pro.ganyushkin.springdatajpamultipleds.entity.shared.PlantEntry;

import java.util.UUID;

@Repository
public interface PgPlantRepository extends CrudRepository<PlantEntry, UUID> {
}
