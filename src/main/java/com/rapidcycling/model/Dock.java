package com.rapidcycling.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "dock", uniqueConstraints = @UniqueConstraint(columnNames = {"station_id", "dock_number"}))
public class Dock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer dockId;

    @NotNull @Min(1)
    @Column(name = "dock_number", nullable = false)
    private Integer dockNumber;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "station_id", nullable = false)
    @JsonIgnore
    private Station station;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bike_id", unique = true)
    private Bike bike;
}
