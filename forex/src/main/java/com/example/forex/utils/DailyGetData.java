package com.example.forex.utils;

import com.example.forex.models.DayForex;
import com.example.forex.services.DayForexService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

@Component
public class DailyGetData {

    private final RestTemplate restTemplate;

    private static final Logger log = LoggerFactory.getLogger(DailyGetData.class);

    @Autowired
    private DayForexService dayForexService;

    @Autowired
    public DailyGetData(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

//    @Scheduled(fixedRate = 3000) // todo: 3秒執行一次，測試用，上傳前註解掉
    @Scheduled(cron = "0 0 18 * * ?") // todo: 上傳github前用這個
    public List<DayForex> fetchData() throws IOException {
        log.info("排程-DailyGetData-fetchData");
        // 使用带有 headers 参数的 getForEntity 方法
        String apiUrl = "https://openapi.taifex.com.tw/v1/DailyForeignExchangeRates";
        ResponseEntity<String> responseEntity = new RestTemplate().getForEntity(apiUrl, String.class);
        String responseBody = responseEntity.getBody();

        ObjectMapper objectMapper = new ObjectMapper();
        // 使用 TypeReference 來指定 List<DayForex> 的類型
        List<DayForex> dayForexList = objectMapper.readValue(responseBody, new TypeReference<List<DayForex>>() {});
        List<DayForex> result = dayForexService.save(dayForexList);
        return result;
    }
}
