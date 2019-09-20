package com.lanit.dcs.diss.aacs.satonin18.hackathon.web.dto.valid;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.lanit.dcs.diss.aacs.satonin18.hackathon.web.helper.PropertiesApp;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;

//todo validation
/*
JSON, представляющий объект Person
{
id: Long (not null),
name: String (not null),
birthdate: Date (not null,формат dd.MM.yyyy),
}

Все поля удовлетворяют ограничениям на тип и формат
Дата рождения в прошлом
Дата рождения в нужном формате
Ранее валидный объект с таким id не передавался
*/

// todo ограничения БД
// TODO SET VALIDATOR

@Data
@NoArgsConstructor
@ToString //ATTATION!!!
public class PersonDto4save {

    @NotNull
    Long id;

    @NotNull
//    @NotBlank
    @Size(min = 0, max = 100)
    String name;

//    @NotNull

//    @DateTimeFormat(PropertiesApp.DATA_FORMAT_BIRTHDATE)
//    java.sql.Date birthdate;

//    @NotBlank
//    @Pattern(regexp = )
//    String birthdate;//todo + PropertiesApp.DATA_FORMAT_BIRTHDATE
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = PropertiesApp.DATA_FORMAT_BIRTHDATE)
    @JsonDeserialize(using = LocalDateDeserializer.class)

    @NotNull
    @Past

    LocalDate birthdate; //java.sql.Date
}
