package com.rapidcycling.model.dto;

import com.rapidcycling.enums.BikeStatus;
import com.rapidcycling.enums.MaintenanceFlag;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BikeRequestDTO {

    @NotNull(message = "Bike status is required")
    private BikeStatus status;

    private MaintenanceFlag maintenanceFlag;
}
