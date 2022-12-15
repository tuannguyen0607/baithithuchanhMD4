package cg.service;

import cg.model.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICityService {

    List<City> findAll();

    City findById(Long id);

    void deleteById(Long id);

    City save(City city);
}
