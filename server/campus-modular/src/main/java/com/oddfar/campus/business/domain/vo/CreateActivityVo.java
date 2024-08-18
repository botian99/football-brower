package com.oddfar.campus.business.domain.vo;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

public class CreateActivityVo {

    /**
     * title
     */
    @NotNull(message = "Title cannot be empty")
    private String name;

    /**
     * 内容
     */
    @Size(min = 0, max = 500, message = "The description length cannot exceed 500 characters")
    private String description;

    /**
     * 内容
     */
    private String location;


    @NotNull(message = "Start time cannot be empty")
    private Date startTime;

    @NotNull(message = "End time cannot be empty")
    private Date endTime;

    public @NotNull(message = "Title cannot be empty") String getName() {
        return name;
    }

    public @Size(min = 0, max = 500, message = "The description length cannot exceed 500 characters") String getDescription() {
        return description;
    }

    public @NotNull(message = "Start time cannot be empty") Date getStartTime() {
        return startTime;
    }

    public @NotNull(message = "End time cannot be empty") Date getEndTime() {
        return endTime;
    }

    public String getLocation() {
        return location;
    }
}
