package hiebrnate.envers.enhancement.representation.dtos.mapper;

import hiebrnate.envers.enhancement.domain.entity.Person;
import hiebrnate.envers.enhancement.domain.value_object.Name;
import hiebrnate.envers.enhancement.representation.dtos.NameDto;
import hiebrnate.envers.enhancement.representation.dtos.PersonDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PersonDtoMapper {
    PersonDtoMapper INSTANCE = Mappers.getMapper(PersonDtoMapper.class);


    @Mapping(target = "name", source = "name")
    Person toPerson(PersonDto personDto);

    Name toName(NameDto nameDto);

    @Mapping(target = "name", source = "name")
    PersonDto toPersonDto(Person person);

    NameDto toNameDto(Name name);

    List<PersonDto> toPersonDtoList(List<Person> personList);
}
