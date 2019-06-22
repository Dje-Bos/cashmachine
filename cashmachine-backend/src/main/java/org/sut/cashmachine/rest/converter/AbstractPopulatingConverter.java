package org.sut.cashmachine.rest.converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sut.cashmachine.rest.populator.Populator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public abstract class AbstractPopulatingConverter<S, T> implements Converter<S, T> {
    private Logger LOG = LoggerFactory.getLogger(AbstractPopulatingConverter.class);
    private Class<T> targetType;
    private List<Populator<S, T>> populators = new ArrayList<>();

    public AbstractPopulatingConverter(Class<T> targetType) {
        this.targetType = targetType;
    }

    @Override
    public T convert(S object) {
        Objects.requireNonNull(object);
        LOG.debug("Creating target conversion type '{}'", targetType.getSimpleName());

        T target = createTarget();
        populators.forEach((populator) -> populator.populate(object, target));
        return target;
    }

    @Override
    public Collection<T> convertAll(Collection<S> objects) {
        throw new UnsupportedOperationException("Convert all is not supported yet");
    }

    protected T createTarget() {
        try {
            return targetType.getConstructor().newInstance();
        } catch (ReflectiveOperationException e) {
            LOG.error(String.format("Wrong target type configuration class='%s'", targetType.getCanonicalName()));
            throw new IllegalStateException("Cannot create target conversion type", e);
        }
    }

    protected Class getTargetType() {
        return targetType;
    }

    public void setTargetType(Class targetType) {
        this.targetType = targetType;
    }

    protected List<Populator<S, T>> getPopulators() {
        return populators;
    }

    public void setPopulators(List<Populator<S, T>> populators) {
        this.populators = populators;
    }
}
