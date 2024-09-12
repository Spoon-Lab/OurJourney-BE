package pudding.toy.ourJourney.mapper;

import org.mapstruct.*;
import pudding.toy.ourJourney.dto.content.UpdateContentRequest;
import pudding.toy.ourJourney.entity.Contents;

@Mapper(uses = JsonNullableMapper.class,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = "spring"
)
public interface UpdateContentsMapper extends GenericMapper<UpdateContentRequest,Contents>{
}
