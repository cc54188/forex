package com.example.forex.repositorys;

import com.example.forex.models.DayForex;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DayForexRepository extends JpaRepository<DayForex, Long> {

    Optional<DayForex> findTopByOrderByDateDesc();

    /**
     * 日期查詢外匯資料
     * @param startDate
     * @param endDate
     * @return
     */
    List<DayForex> findByDateBetween(@Param("startDate") String startDate, @Param("endDate") String endDate);
}
