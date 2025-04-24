package com.github.paicoding.forum.api.model.vo.rank.dto;

import com.github.paicoding.forum.api.model.enums.rank.ActivityRankTimeEnum;
import lombok.Data;

import java.util.List;

@Data
public class RankInfoDTO {
    private ActivityRankTimeEnum time;
    private List<RankItemDTO> items;
}
