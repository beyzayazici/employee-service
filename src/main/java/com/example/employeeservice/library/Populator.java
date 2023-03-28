package com.example.employeeservice.library;

public interface Populator<SOURCE, TARGET> {
    void populate(SOURCE source, TARGET target);
}