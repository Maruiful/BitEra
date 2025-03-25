package com.github.paicoding.forum.service.statistics.converter;

import com.github.paicoding.forum.api.model.vo.statistics.dto.StatisticsDayDTO;
import com.github.paicoding.forum.service.statistics.repository.entity.RequestCountDO;
import com.github.paicoding.forum.service.statistics.repository.entity.RequestCountExcelDO;
import com.github.paicoding.forum.service.statistics.repository.entity.StatisticsDayExcelDO;

import java.util.List;
import java.util.stream.Collectors;

public class StatisticsConverter {
    public static StatisticsDayExcelDO convertToExcelDO(StatisticsDayDTO dto) { return null; }

    public static RequestCountExcelDO ConvertToRequestCountDO(RequestCountDO requestCountDO) { return null; }

    public static List<RequestCountExcelDO> convertToRequestCountExcelDOList(List<RequestCountDO> requestCountDOList) { return null; }

    public static List<StatisticsDayExcelDO> convertToExcelDOList(List<StatisticsDayDTO> dtoList) {
        return dtoList.stream()
                .map(StatisticsConverter::convertToExcelDO)
                .collect(Collectors.toList());
    }
}