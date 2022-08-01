package pro.ganyushkin.springdatajpamultipleds.rest.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class PlantResponse {
    private UUID id;
    private String name;
}
