package com.Expedition67.card;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Defines the names for all cards in the game.
 */
public enum CardName {

    CELESTIAL_SINGULARITY,
    ECHOING_STRIKE,
    ETERNAL_SOUL_REBIRTH,
    ETHEREAL_RESTORATION,
    EVENT_HORIZON,
    HARMONIC_RESONANCE,
    REMNANT_HIT,
    SOUL_AEGIS,
    SOUL_FLICKER,
    SOUL_RESONANCE,
    SOVEREIGNS_OVERDRIVE,
    SPECTRAL_VEIL,
    VOID,
    VOID_DRAGON;

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
