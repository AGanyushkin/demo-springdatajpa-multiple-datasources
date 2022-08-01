package pro.ganyushkin.springdatajpamultipleds.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pro.ganyushkin.springdatajpamultipleds.entity.pg.PlantImageEntry;
import pro.ganyushkin.springdatajpamultipleds.entity.pg.TextChunkEntry;
import pro.ganyushkin.springdatajpamultipleds.entity.shared.PlantEntry;
import pro.ganyushkin.springdatajpamultipleds.repository.mysql.MySQLPlantRepository;
import pro.ganyushkin.springdatajpamultipleds.repository.pg.PgPlantImageRepository;
import pro.ganyushkin.springdatajpamultipleds.repository.pg.PgPlantRepository;
import pro.ganyushkin.springdatajpamultipleds.repository.pg.PgTextChunkRepository;
import pro.ganyushkin.springdatajpamultipleds.rest.entity.ImageResponse;
import pro.ganyushkin.springdatajpamultipleds.rest.entity.PlantResponse;
import pro.ganyushkin.springdatajpamultipleds.rest.entity.TextResponse;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ContentService {
    private final PgPlantRepository pgPlantRepository;
    private final MySQLPlantRepository mySQLPlantRepository;
    private final PgPlantImageRepository pgPlantImageRepository;
    private final PgTextChunkRepository pgTextChunkRepository;

    @Transactional
    public PlantResponse createNewPlant(String name) {
        var plantEntry = new PlantEntry(
                UUID.randomUUID(),
                name,
                true
        );
        var mySQLPlantEntry = mySQLPlantRepository.save(plantEntry);
        var pgPlantEntry = pgPlantRepository.save(plantEntry);
        return new PlantResponse(mySQLPlantEntry.getId(), mySQLPlantEntry.getName());
    }

    @Transactional
    public ImageResponse createNewImage(UUID plantId, String url) {
        var plant = findPlantEntry(plantId);
        var plantEntry = pgPlantImageRepository.save(
                new PlantImageEntry(
                        UUID.randomUUID(),
                        url,
                        plant
                )
        );
        return new ImageResponse(plantEntry.getId(), plantEntry.getUrl());
    }

    @Transactional
    public TextResponse createNewChunkContent(UUID plantId, String textChunk) {
        var plant = findPlantEntry(plantId);
        var textEntry = pgTextChunkRepository.save(
                new TextChunkEntry(
                        UUID.randomUUID(),
                        textChunk,
                        plant
                )
        );
        return new TextResponse(textEntry.getId());
    }

    private PlantEntry findPlantEntry(UUID plantId) {
        return mySQLPlantRepository
                .findByAvailableIsTrueAndId(plantId)
                .orElseThrow();
    }

    @Transactional(readOnly = true)
    public List<PlantResponse> findAllPlants() {
        return mySQLPlantRepository.findAllByAvailableIsTrue().stream()
                .map(entry -> new PlantResponse(entry.getId(), entry.getName()))
                .toList();
    }

    @Transactional(readOnly = true)
    public List<ImageResponse> findAllImages(UUID plantId) {
        return pgPlantImageRepository
                .findAllByPlant(findPlantEntry(plantId)).stream()
                .map(entry -> new ImageResponse(entry.getId(), entry.getUrl()))
                .toList();
    }

    @Transactional(readOnly = true)
    public List<TextResponse> findAllTextEntries(UUID plantId) {
        return pgTextChunkRepository
                .findAllByPlant(findPlantEntry(plantId)).stream()
                .map(entry -> new TextResponse(entry.getId()))
                .toList();
    }

    @Transactional(readOnly = true)
    public String getTextChunkContent(UUID plantId, UUID chunkId) {
        return pgTextChunkRepository
                .findFirstByPlant(findPlantEntry(plantId))
                .orElseThrow()
                .getText();
    }
}
