package com.Expedition67.unit;

import java.util.Arrays;
import java.util.stream.Collectors;

public enum UnitName {
    BIG_BAD_BOSS, CRYING_SLIME, LUKCHIN, PLAYER, RED_EYES, SON_AND_DAD, TILLY_THE_BIRD, VISION;

    private String toCapitalize(String str) {
        return str.charAt(0) + str.substring(1).toLowerCase();
    }

    @Override
    public String toString() {
        return Arrays.stream(super.toString().split("_")).map(this::toCapitalize).collect(Collectors.joining(" "));
    }
}
