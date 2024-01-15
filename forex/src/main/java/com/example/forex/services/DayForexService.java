package com.example.forex.services;

import com.example.forex.dtos.DayForexDTO;
import com.example.forex.models.*;
import com.example.forex.vos.Error;
import com.example.forex.repositorys.DayForexRepository;
import com.example.forex.vos.RequestVo;
import com.example.forex.vos.ResponseVo;
import com.example.forex.vos.Success;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class DayForexService {

    @Autowired
    private DayForexRepository dayForexRepository;

    public List<DayForex> save(List<DayForex> list) {
        List<DayForex> saveList = new ArrayList<>();
        if (dayForexRepository.count() == 0) { // DB內尚無資料
            saveList = dayForexRepository.saveAll(list);
        } else { // 若DB已有資料
            Optional<DayForex> optional = dayForexRepository.findTopByOrderByDateDesc();
            if (optional.isPresent()) {
                DayForex dayForexDb = optional.get(); // 若資料的日期 > 資料庫內最後一筆資料的日期，需要insert

                saveList = list.stream()
                        .filter(item -> Integer.parseInt(item.getDate()) > Integer.parseInt(dayForexDb.getDate()))
                        .collect(Collectors.toList());
                        dayForexRepository.saveAll(saveList);
            }
        }
        return saveList;
    }

    /**
     * 查詢資料庫內幣別匯率資料
     * @param requestVo
     * @return
     */
    public ResponseVo findList(RequestVo requestVo) {
        if (!validateDateRange(requestVo)) {
            Error error = new Error("E001", "日期區間不符");
            return new ResponseVo(error);
        }
        requestVo.setStartDate(dateToStr(requestVo.getStartDate()));
        requestVo.setEndDate(dateToStr(requestVo.getEndDate()));
        List<DayForex> list = dayForexRepository.findByDateBetween(requestVo.getStartDate(), requestVo.getEndDate());
        List<Map<String, Object>> mapList = new ArrayList<>();
        Error error = new Error("0000", "成功");
        for (DayForex item: list) {
            Map<String, Object> map = new HashMap<>();
            map.put("date", item.getDate());
            // 根據請求的currency回傳不同的匯率資料
            switch (requestVo.getCurrency()) {
                case "usd":
                    map.put("usd", item.getUsdNtd());
                    break;
                default: // 目前幣別只有usd，除此之外都回錯誤訊息
                    Error errorCurrency = new Error("E002", "查無符合幣別!");
                    return new ResponseVo(errorCurrency);
            }
            mapList.add(map);
        }
        Success success = new Success(error, mapList);
        return success;
    }

    /**
     * 日期字串去除 "/"
     * @param date
     * @return
     */
    public String dateToStr(String date) {
        String[] dateArr = date.split("/");
        String dateStr = dateArr[0];
        for (int i = 1; i < dateArr.length; i++) {
            dateStr += dateArr[i];
        }
        return dateStr;
    }

    /**
     * 驗證startDate是否>=一年前，endDate是否<=一天前
     * @param requestVo
     * @return
     */
    public boolean validateDateRange(RequestVo requestVo) {
        LocalDate currentDate = LocalDate.now(); // 今天
        LocalDate oneYearAgo = currentDate.minus(Period.ofYears(1)); // 一年前
        LocalDate oneDayAhead = currentDate.minus(Period.ofDays(1)); // 一天前

        LocalDate startDate = LocalDate.parse(requestVo.getStartDate().replace("/", "-")); // 請求日期字串轉LocalDate
        LocalDate endDate = LocalDate.parse(requestVo.getEndDate().replace("/", "-"));

        return (startDate.isAfter(oneYearAgo) || startDate.isEqual(oneYearAgo)) &&
                (endDate.isBefore(oneDayAhead) || endDate.isEqual(oneDayAhead)) &&
                (endDate.isAfter(startDate) || endDate.isEqual(startDate));
    }
}
