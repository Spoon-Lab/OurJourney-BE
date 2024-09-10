package pudding.toy.ourJourney.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import pudding.toy.ourJourney.dto.content.EditContentRequest;
import pudding.toy.ourJourney.entity.Contents;

public interface GenericMapper<D,E> {
    D toDto(E e);
    E toEntity(D d);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    //source의 필드가 null일 때 정책으로 null인 값은 무시.
    void updateEntityFromDto(D dto, @MappingTarget E entity);
}
