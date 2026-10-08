package com.drive.hire.driverhire.model;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
public class Driver {

    @NotNull
    private int id;
    @NotNull
    private String name;
    @NotNull
    private String pass;
    private String email;
    @NotNull
    private int phNum;
    private String rating;
    private String pic;
}
