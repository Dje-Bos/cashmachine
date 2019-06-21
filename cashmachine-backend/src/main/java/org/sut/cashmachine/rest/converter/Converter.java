package org.sut.cashmachine.rest.converter;

@FunctionalInterface
public interface Converter<T,V> {
    V convert(T object);
}
