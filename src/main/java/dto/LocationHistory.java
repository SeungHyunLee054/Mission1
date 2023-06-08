package dto;

import lombok.Data;

@Data
public class LocationHistory {
    private int id;
    private double LAT;
    private double LNT;
    private String FIND_DATE;
}
