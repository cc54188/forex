package com.example.forex.vos;

import com.example.forex.dtos.DayForexDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Success extends ResponseVo {
    private List<Map<String, Object>> currency;

    public Success(Error error, List<Map<String, Object>> currency) {
        super(error);
        this.currency = currency;
    }
}
