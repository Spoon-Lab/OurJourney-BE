package pudding.toy.ourJourney.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import pudding.toy.ourJourney.dto.content.EditContentRequest;
import pudding.toy.ourJourney.entity.Contents;

@Mapper(componentModel = "spring") //bean 등록
public interface ContentsMapper extends GenericMapper<EditContentRequest,Contents>{


}
