package ru.maxima.exceptions;

public class PersonNotSuccessCreatedException extends RuntimeException {
    public PersonNotSuccessCreatedException(String message) {
        super(message);
    }
}
