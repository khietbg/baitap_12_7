package bt.n11_t7.mapper;

import java.util.List;

public interface GenericMapper<D,E> {
    E toEntity(D dto);
    D toDto(E entity);
    List<E> toEntity(List<D> dtoList);
    List<D> toDto(List<E> entityList);

}
