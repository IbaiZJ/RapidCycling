package com.rapidcycling.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "pricing_policy")
public class PricingPolicy {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pricingPolicyId;

    @NotNull @PositiveOrZero
    @Column(nullable = false)
    private Integer includedMinutes = 0;

    @NotNull @Min(1)
    @Column(nullable = false)
    private Integer billingIntervalMinutes = 1;

    @NotNull @DecimalMin("0.00")
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal pricePerInterval = BigDecimal.ZERO;

    @NotNull @DecimalMin("0.00")
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal dailyCap = BigDecimal.ZERO;
}
