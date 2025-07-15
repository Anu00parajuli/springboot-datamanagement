package com.eon.springbootdatamanagement.util;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EnumUtil {

    private EnumUtil(){}

    public static <E extends Enum<E>> String getEnumConstants(Class<E> enumClass) {
        return Stream.of(enumClass.getEnumConstants()).map(Enum::name).collect(Collectors.joining(", "));
    }
}