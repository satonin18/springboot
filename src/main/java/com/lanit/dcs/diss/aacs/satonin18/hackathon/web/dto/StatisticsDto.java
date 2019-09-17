package com.lanit.dcs.diss.aacs.satonin18.hackathon.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//todo validation
/*
JSON, представляющий объект StatisticsDto
{
personcount: Long , - количество объектов Person, прошедших валидацию и сохраненных
carcount: Long,  - количество объектов Car, прошедших валидацию и сохраненных
uniquevendorcount: Long - количество уникальных производителей среди прошедших валидацию и сохраненных объектов Car, регистронезависимо
}
*/
//todo ТЗ непонятно, цитата:"JSON StatisticsDto carcount: Long, - количество объектов Car, прошедших валидацию и сохраненных"
// + толи тупо выдать кол-во сохр записей из БД,(ТОЧНО ДА, уточнил ТЗ)
// - толи в ПЕРЕМЕННАЯ на уровне приложения,(НЕТ)
// - толи в другие поля ставим переменнуб validated (НЕТ)
// - (СУДЯ ПО ТЗ, нужен тип прокси-логер-счетчик на уровне после прошедших валидацию и сохраненных) (НЕТ),
// - толи как часть БД, в которую сами вписываем данные //без ID //ток 3поля и 1 строка-запись(которую UPDATE-тим) (НЕТ)

//todo JSON only 3 var

//TODO add Service SpringDataJpa FROM DATA ON OTHER TABLES

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatisticsDto {
    //TODO логика = ВЫЧЕСЛЯЕМЫЕ ПОЛЯ (на каком уровне APP ВЫЧЕСЛЯТЬ ???)
    // - вычесляумые столбцы
    // - после тиригеров обновляемые поля
    // + обычный вызов COUNT(obj) в БД
    Long personcount;
    Long carcount;

    //todo COUNT_УНИКАЛЬНЫХ_ЗНАЧЕНИЙ(obj) //todo SET INDEX on field
    // + (distinct) на MySql=своя команда, HibernateSql=своя, , CriteriaAPI=своя, SpringDataJpa=своя=countDistinct*By*()
    Long uniquevendorcount; //регистронезависимо //can be on level db-MySql
}
