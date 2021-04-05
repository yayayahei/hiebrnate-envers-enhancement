package hiebrnate.envers.enhancement.representation.dtos.mapper;


import hiebrnate.envers.enhancement.domain.command.PagingQueryCommand;
import hiebrnate.envers.enhancement.representation.dtos.request.CommonPageRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface CommonPageRequestMapper {
    CommonPageRequestMapper INSTANCE = Mappers.getMapper(CommonPageRequestMapper.class);

    PagingQueryCommand toCommand(CommonPageRequest commonPageRequest);

}
