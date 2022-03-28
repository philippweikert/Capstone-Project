package de.neuefische.capstone;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
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

}
