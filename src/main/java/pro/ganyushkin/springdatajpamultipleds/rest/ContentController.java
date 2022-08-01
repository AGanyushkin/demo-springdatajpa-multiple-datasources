package pro.ganyushkin.springdatajpamultipleds.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pro.ganyushkin.springdatajpamultipleds.rest.entity.ImageCreateRequest;
import pro.ganyushkin.springdatajpamultipleds.rest.entity.ImageResponse;
import pro.ganyushkin.springdatajpamultipleds.rest.entity.PlantCreateRequest;
import pro.ganyushkin.springdatajpamultipleds.rest.entity.PlantResponse;
import pro.ganyushkin.springdatajpamultipleds.rest.entity.TextResponse;
import pro.ganyushkin.springdatajpamultipleds.service.ContentService;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/v1/content")
public class ContentController {
    private final ContentService contentService;

    @RequestMapping(method = RequestMethod.PUT,
            path = "/plant",
            produces = "application/json;charset=utf-8",
            consumes = "application/json;charset=utf-8")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public PlantResponse createNewPlantEntry(@RequestBody PlantCreateRequest plant) {
        return contentService.createNewPlant(plant.getName());
    }

    @RequestMapping(method = RequestMethod.GET,
            path = "/plant",
            produces = "application/json;charset=utf-8")
    @ResponseStatus(HttpStatus.OK)
    public List<PlantResponse> getPlantList() {
        return contentService.findAllPlants();
    }

    @RequestMapping(method = RequestMethod.GET,
            path = "/plant/{plantId}/image",
            produces = "application/json;charset=utf-8")
    @ResponseStatus(HttpStatus.OK)
    public List<ImageResponse> getPlantImages(@PathVariable UUID plantId) {
        return contentService.findAllImages(plantId);
    }

    @RequestMapping(method = RequestMethod.PUT,
            path = "/plant/{plantId}/image",
            produces = "application/json;charset=utf-8")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ImageResponse createNewImage(@PathVariable UUID plantId,
            @RequestBody ImageCreateRequest image) {
        return contentService.createNewImage(plantId, image.getUrl());
    }

    @RequestMapping(method = RequestMethod.GET,
            path = "/plant/{plantId}/text",
            produces = "application/json;charset=utf-8")
    @ResponseStatus(HttpStatus.OK)
    public List<TextResponse> getPlantTextList(@PathVariable UUID plantId) {
        return contentService.findAllTextEntries(plantId);
    }

    @RequestMapping(method = RequestMethod.GET,
            path = "/plant/{plantId}/text/{chunkId}",
            produces = "text/plain; charset=utf-8")
    @ResponseStatus(HttpStatus.OK)
    public String getPlantTextList(@PathVariable UUID plantId,
                                   @PathVariable UUID chunkId) {
        return contentService.getTextChunkContent(plantId, chunkId);
    }

    @RequestMapping(method = RequestMethod.PUT,
            path = "/plant/{plantId}/text",
            produces = "application/json;charset=utf-8")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public TextResponse createNewTextChunk(@PathVariable UUID plantId,
                                           @RequestBody String textChunk) {
        return contentService.createNewChunkContent(plantId, textChunk);
    }
}
