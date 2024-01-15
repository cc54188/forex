package com.example.forex.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "DAY_FOREX")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class DayForex {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JsonProperty("Date")
    @Column(name = "date", unique = true, nullable = false, length = 20)
    private String date;
    @JsonProperty("USD/NTD")
    @Column(name = "USD_NTD", nullable = false, precision = 12, scale = 6)
    private BigDecimal usdNtd;
    @JsonProperty("RMB/NTD")
//    @Transient
    @Column(name = "RMB_NTD", nullable = false, precision = 12, scale = 6)
    private BigDecimal rmbNtd;
    @JsonProperty("EUR/USD")
//    @Transient
    @Column(name = "EUR_USD", nullable = false, precision = 12, scale = 6)
    private BigDecimal eurUsd;
    @JsonProperty("USD/JPY")
//    @Transient
    @Column(name = "USD_JPY", nullable = false, precision = 12, scale = 6)
    private BigDecimal usdJpy;
    @JsonProperty("GBP/USD")
//    @Transient
    @Column(name = "GBP_USD", nullable = false, precision = 12, scale = 6)
    private BigDecimal gbpUsd;
    @JsonProperty("AUD/USD")
//    @Transient
    @Column(name = "AUD_USD", nullable = false, precision = 12, scale = 6)
    private BigDecimal audUsd;
    @JsonProperty("USD/HKD")
//    @Transient
    @Column(name = "USD_HKD", nullable = false, precision = 12, scale = 6)
    private BigDecimal usdHkd;
    @JsonProperty("USD/RMB")
//    @Transient
    @Column(name = "USD_RMB", nullable = false, precision = 12, scale = 6)
    private BigDecimal usdRmb;
    @JsonProperty("USD/ZAR")
//    @Transient
    @Column(name = "USD_ZAR", nullable = false, precision = 12, scale = 6)
    private BigDecimal usdZar;
    @JsonProperty("NZD/USD")
//    @Transient
    @Column(name = "NZD_USD", nullable = false, precision = 12, scale = 6)
    private BigDecimal nzdUsd;
}
