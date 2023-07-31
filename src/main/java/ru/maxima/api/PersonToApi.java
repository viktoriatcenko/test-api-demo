package ru.maxima.api;

import lombok.Data;

@Data
public class PersonToApi {
    private Long id;
    private String name;
    private String job;
    private String createdAt;
}
