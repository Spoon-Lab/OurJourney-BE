package pudding.toy.ourJourney.mapper;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import pudding.toy.ourJourney.dto.content.EditContentRequest;
import pudding.toy.ourJourney.entity.Contents;

@Mapper(uses = JsonNullableMapper.class,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = "spring"
)
public interface EditContentsMapper extends GenericMapper<EditContentRequest,Contents>{
}
