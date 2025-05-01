package uk.ac.ncl.team5project.form;

import java.util.List;

import lombok.Data;

@Data
public class BasketPaidForm {
    private Integer userId;
    private List<Integer> bookIds;
}
