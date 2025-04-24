package com.github.paicoding.forum.api.model.enums.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StarSourceEnum {
    /**
     * java进阶
     */
    JAVA_GUIDE(1),
    /**
     * 技术派
     */
    TECH_PAI(2),
    ;
    private int source;
}
