package dto;

import lombok.Data;

@Data
public class BookMark {
    private int id;
    private String bookmark_group_name;
    private String wifi_name;
    private String register_date;
}
