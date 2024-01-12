package com.example.forex.controllers;

import com.example.forex.vos.RequestVo;
import com.example.forex.vos.ResponseVo;
import com.example.forex.services.DayForexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/forex")
public class DayForexController {

    @Autowired
    private DayForexService dayForexService;

    @PostMapping("/search")
    public ResponseEntity<ResponseVo> searchData(@RequestBody RequestVo requestVo) {
        return ResponseEntity.ok(dayForexService.findList(requestVo));
    }
}
