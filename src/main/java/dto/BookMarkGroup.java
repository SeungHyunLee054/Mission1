package dto;

import lombok.Data;

@Data
public class BookMarkGroup {
    private int id;
    private String bookmark_group_name;
    private int order;
    private String register_date;
    private String edit_date;
}
