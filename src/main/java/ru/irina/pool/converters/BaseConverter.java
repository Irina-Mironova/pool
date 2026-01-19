package ru.irina.pool.converters;

public interface BaseConverter <E, D> {
    public D entityToDto(E entity);

    public E dtoToEntity(E entity, D dto);

}
