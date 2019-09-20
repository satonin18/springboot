package com.lanit.dcs.diss.aacs.satonin18.hackathon.web.dto.input.valid;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

@Data
@NoArgsConstructor
public class CarDto4save {

    //такой id не дожен лежать в бд
    @NotNull
    Long id;

    @NotNull
    @Pattern(regexp = "^[^-]{1,50}-.{1,50}$") //"^"=start  "$"=end  "[^-]"=любой_символ_кромеТИРЕ  "."=любой_символ  "+"=OneOrMore "{1,50}"-min=1,max=50
    String model; //имя переменой совпадает с другой переменой

    @NotNull
    @Positive
    Integer horsepower;

    //он должен быть старше 18 лет
    @NotNull
    Long ownerId;
}
