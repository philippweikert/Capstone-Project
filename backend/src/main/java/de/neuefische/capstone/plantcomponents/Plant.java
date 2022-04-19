package de.neuefische.capstone.plantcomponents;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "plants")
public class Plant {

    @Id
    private String id;
    private String scientificName;
    private String nonScName;
    private String location;
    private String pouring;
    private String soil;
    private String manure;
    private String repot;
    private String user;

    public Plant updatePlant(Plant changedPlant) {
        setScientificName(changedPlant.getScientificName());
        setNonScName(changedPlant.getNonScName());
        setLocation(changedPlant.getLocation());
        setPouring(changedPlant.getPouring());
        setSoil(changedPlant.getSoil());
        setManure(changedPlant.getManure());
        setRepot(changedPlant.getRepot());
        return this;
    }
}


