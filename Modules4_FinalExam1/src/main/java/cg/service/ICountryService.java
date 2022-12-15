package cg.service;

import cg.model.Country;

import java.util.List;

public interface ICountryService {

    List<Country> findAll();

    Country findById(Long id);

    void deleteById(Long id);

    Country save(Country city);
}
