package com.example.forex.vos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestVo {
    private String startDate;

    private String endDate;

    private String currency;
}
