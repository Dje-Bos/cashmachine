package org.sut.cashmachine.rest.populator;

/**
 * Populates target instance with values from source one
 * @param <S> - type of source object
 * @param <T> - type of target object
 */
public interface Populator<S,T> {

    void populate(S source, T target);
}
