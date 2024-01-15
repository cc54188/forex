package com.example.forex.controllers;

import com.example.forex.models.DayForex;
import com.example.forex.services.DayForexService;
import com.example.forex.utils.DailyGetData;
import com.example.forex.vos.RequestVo;
import com.example.forex.vos.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/forex_test")
public class DayForexTestController {

    @Autowired
    private DailyGetData dailyGetData;

    @Autowired
    private DayForexService dayForexService;

    // 測試排程取匯率資料
    @GetMapping("/save")
    public ResponseEntity<List<DayForex>> saveDataTest() throws IOException {
        List<DayForex> result = dailyGetData.fetchData();
        return ResponseEntity.ok(result);
    }

    // 測試排程取匯率資料
    @PostMapping("/search")
    public ResponseEntity<ResponseVo> searchDataTest(@RequestBody RequestVo requestVo) {
        return ResponseEntity.ok(dayForexService.findList(requestVo));
    }
}
