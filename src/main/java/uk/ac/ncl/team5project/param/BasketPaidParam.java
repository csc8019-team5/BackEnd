package uk.ac.ncl.team5project.param;

import java.util.List;

import lombok.Data;

@Data
public class BasketPaidParam {
     private Integer userId;
    private List<Integer> bookIds;
}
