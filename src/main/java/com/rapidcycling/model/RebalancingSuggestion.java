package com.rapidcycling.model;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "rebalancing_suggestion", indexes = @Index(name = "idx_suggestion_created", columnList = "created_at"))
public class RebalancingSuggestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long suggestionId;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "source_station_id", nullable = false)
    private Station sourceStation;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "target_station_id", nullable = false)
    private Station targetStation;

    @NotNull @Min(1)
    @Column(nullable = false)
    private Integer bikeCount;

    @NotNull @Min(0)
    @Column(nullable = false)
    private Integer sourceOccupancy;

    @NotNull @Min(1)
    @Column(nullable = false)
    private Integer sourceCapacity;

    @NotNull @Min(0)
    @Column(nullable = false)
    private Integer targetOccupancy;

    @NotNull @Min(1)
    @Column(nullable = false)
    private Integer targetCapacity;

    @NotNull
    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @Size(max = 500)
    @Column(length = 500)
    private String driverExplanation;
}
