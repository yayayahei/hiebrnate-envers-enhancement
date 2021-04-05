package hiebrnate.envers.enhancement.representation.dtos.mapper;

import hiebrnate.envers.enhancement.domain.entities.Person;
import hiebrnate.envers.enhancement.domain.value_object.Name;
import hiebrnate.envers.enhancement.representation.dtos.NameDto;
import hiebrnate.envers.enhancement.representation.dtos.PersonDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PersonDtoMapper {
    PersonDtoMapper INSTANCE = Mappers.getMapper(PersonDtoMapper.class);


    Person toPerson(PersonDto personDto);

    Name toName(NameDto nameDto);

    PersonDto toPersonDto(Person person);

    NameDto toNameDto(Name name);

    List<PersonDto> toPersonDtoList(List<Person> personList);
}
