package com.nhnacademy.minidooraytaskapi.projectstatus.code;

import lombok.Getter;

@Getter
public enum ProjectStatusCode {
    ACTIVATE("활성", 1),
    REST("휴면", 2),
    FINISH("종료", 3);

    private final String code;
    private final int value;

    ProjectStatusCode(String code, int value) {
        this.code = code;
        this.value = value;
    }

    public static int getValue(String code) {
        for (ProjectStatusCode projectStatus : ProjectStatusCode.values()) {
            if (projectStatus.getCode().equals(code)) {
                return projectStatus.getValue();
            }
        }
        return 0;
    }
}
