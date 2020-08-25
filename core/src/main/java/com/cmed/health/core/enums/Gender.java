package com.cmed.health.core.enums;

import lombok.Getter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Objects;

/**
 * Creator : Tanvir Chowdhury
 * Date    : 2020-08-23
 */

@Getter
public enum Gender {

    Man(1, "Man"),
    Woman(2, "Woman");

    private Integer id;
    private String name;

    Gender(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public static Gender findById(Integer id) {
        for (Gender gender : Gender.values()) {
            if (Objects.equals(gender.getId(), id)) {
                return gender;
            }
        }
        return null;
    }

    public static Gender findByName(String name) {
        for (Gender gender : Gender.values()) {
            if (gender.getName().equalsIgnoreCase(name)) {
                return gender;
            }
        }
        return null;
    }

    @Converter
    public static class TypeConverter
            implements AttributeConverter<Gender, String> {

        @Override
        public String convertToDatabaseColumn(Gender type) {
            return type != null ? type.getName() : null;
        }

        @Override
        public Gender convertToEntityAttribute(String name) {
            return name != null ? findByName(name) : null;
        }
    }
}
