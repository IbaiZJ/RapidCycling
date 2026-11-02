package com.rapidcycling.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rapidcycling.enums.BikeStatus;
import com.rapidcycling.enums.MaintenanceFlag;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "bike")
public class Bike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bikeId;
    
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private BikeStatus status = BikeStatus.LOCK;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private MaintenanceFlag maintenanceFlag = MaintenanceFlag.NONE;

    @OneToOne(mappedBy = "bike", fetch = FetchType.LAZY)
    @JsonIgnore
    private Dock dock;

    @Version
    private Long version;
}
