package pudding.toy.ourJourney.mapper;

import io.swagger.v3.core.util.Json;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.openapitools.jackson.nullable.JsonNullable;
import pudding.toy.ourJourney.dto.profile.UpdateProfileRequest;
import pudding.toy.ourJourney.entity.Profile;

import java.util.Optional;

@Mapper(uses = JsonNullableMapper.class,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel =  "spring")
public interface UpdateProfileMapper extends GenericMapper<UpdateProfileRequest,Profile> {
}
