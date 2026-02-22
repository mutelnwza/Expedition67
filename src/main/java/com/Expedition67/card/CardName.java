package com.Expedition67.card;

import java.util.Arrays;
import java.util.stream.Collectors;

public enum CardName {

    CELESTIAL_SINGULARITY, ECHOING_STRIKE, ETERNAL_SOUL_REBIRTH, ETHEREAL_RESTORATION, EVENT_HORIZON,
    HARMONIC_RESONANCE, REMNANT_HIT, SOUL_AEGIS, SOUL_FLICKER, SOUL_RESONANCE, SOVEREIGNS_OVERDRIVE,
    SPECTRAL_VEIL, VOID, VOID_DRAGON;

    private String toCapitalize(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    @Override
    public String toString() {
        return Arrays.stream(super.toString().split("_")).map(this::toCapitalize).collect(Collectors.joining(" "));
    }
}
