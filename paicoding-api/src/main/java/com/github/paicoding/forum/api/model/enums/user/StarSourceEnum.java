package com.github.paicoding.forum.api.model.enums.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 星球来源枚举
 * */
@Getter
@AllArgsConstructor
public enum StarSourceEnum {
    /**
     * java进阶
     */
    JAVA_GUIDE(1),
    /**
     * 
     */
    TECH_PAI(2),
    ;
    private int source;
}
