package com.Expedition67.unit;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Defines the names for all units in the game.
 */
public enum UnitName {
    BIG_BAD_BOSS,
    CRYING_SLIME,
    LUKCHIN,
    PLAYER,
    RED_EYES,
    SON_AND_DAD,
    TILLY_THE_BIRD,
    VISION;

    /**
     * Converts an enum name into a human-readable string.
     *
     * @return A formatted string with spaces and capitalized words.
     */
    @Override
    public String toString() {
        return Arrays.stream(super.toString().split("_"))
                .map(this::capitalize)
                .collect(Collectors.joining(" "));
    }

    /**
     * Capitalizes the first letter of a string and converts the rest to lowercase.
     *
     * @param str The string to capitalize.
     * @return The capitalized string.
     */
    private String capitalize(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }
}
