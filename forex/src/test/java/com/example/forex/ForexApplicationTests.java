package com.example.forex;

import com.example.forex.models.DayForex;
import com.example.forex.repositorys.DayForexRepository;
import com.example.forex.services.DayForexService;
import com.example.forex.utils.DailyGetData;
import com.example.forex.utils.Function;
import com.example.forex.vos.RequestVo;
import com.example.forex.vos.ResponseVo;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;

@SpringBootTest
class ForexApplicationTests {

    @Autowired

    private DayForexRepository dayForexRepository;

    @Autowired
    private DayForexService dayForexService;

    @Autowired
    private DailyGetData dailyGetData;

    private static final Logger log = LoggerFactory.getLogger(ForexApplicationTests.class);

    /**
     * 查詢日期區間外匯資料的unit test
     */
    @Test
    public void testFindByDateBetween() {
        RequestVo requestVo = new RequestVo();
        requestVo.setStartDate(Function.randomDateStr()); // 隨機產生一個日期字串
        requestVo.setEndDate(Function.randomDateStr()); // 隨機產生一個日期字串
        requestVo.setCurrency("usd"); // 目前不確定是否還有別的匯率，先以這個為查詢條件
        ResponseVo responseVo = dayForexService.findList(requestVo);
        log.info("request:" + requestVo); // 印出請求
        log.info("response:" + responseVo); // 印出回傳
    }

    /**
     * 排程取外匯資料的unit test
     * @throws IOException
     */
    @Test
    public void saveDataTest() throws IOException {
        List<DayForex> result = dailyGetData.fetchData(); // 成功會印出openApi存入DB的資料
        for (DayForex dayForex: result) {
            log.info("insert資料:\n" +
                    "date: " + dayForex.getDate() +
                    ", \nUSD/NTD: " + dayForex.getUsdNtd());
        }
    }

    @Test
    void contextLoads() {
    }
}
