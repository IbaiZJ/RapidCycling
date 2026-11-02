package com.rapidcycling.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "station")
public class Station {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer stationId;

    @NotBlank @Size(max = 120)
    @Column(nullable = false, length = 120)
    private String name;

    @Min(1)
    @Column(nullable = false)
    private Integer capacity;

    @Min(0)
    @Column(nullable = false)
    private Integer occupiedDocks = 0;

    @Version
    private Long version;
}
