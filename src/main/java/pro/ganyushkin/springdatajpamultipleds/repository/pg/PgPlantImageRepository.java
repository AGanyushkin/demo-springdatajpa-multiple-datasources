package pro.ganyushkin.springdatajpamultipleds.repository.pg;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pro.ganyushkin.springdatajpamultipleds.entity.pg.PlantImageEntry;
import pro.ganyushkin.springdatajpamultipleds.entity.shared.PlantEntry;

import java.util.List;
import java.util.UUID;

@Repository
public interface PgPlantImageRepository extends CrudRepository<PlantImageEntry, UUID> {
    List<PlantImageEntry> findAllByPlant(PlantEntry plant);
}
