package org.sut.cashmachine.rest.converter;

import java.util.Collection;

/**
 * Interface is responsible for conversion from source to target type.
 *
 * @param <S> - type to convert from
 * @param <T> - type to convert to
 */
public interface Converter<S,T> {
    T convert(S source);

    Collection<T> convertAll(Collection<S> objects);
}
