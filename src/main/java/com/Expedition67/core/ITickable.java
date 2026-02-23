package com.Expedition67.core;

import com.Expedition67.unit.Unit;

/**
 * An interface for objects that perform an action on a regular game tick.
 */
public interface ITickable {

    /**
     * Called every time a game "tick" occurs.
     *
     * @param owner The Unit that this tickable effect is attached to.
     */
    void onTick(Unit owner);
}
