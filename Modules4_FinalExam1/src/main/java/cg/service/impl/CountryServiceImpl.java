package cg.service.impl;

import cg.model.Country;
import cg.repository.ICountryRepository;
import cg.service.ICountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryServiceImpl implements ICountryService {

    @Autowired
    private ICountryRepository countryRepository;

    @Override
    public List<Country> findAll() {
        return countryRepository.findAll();
    }

    @Override
    public Country findById(Long id) {
        if (countryRepository.findById(id).isPresent()){
            return countryRepository.findById(id).get();
        }
        return null;
    }

    @Override
    public void deleteById(Long id) {
        countryRepository.deleteById(id);
    }

    @Override
    public Country save(Country city) {
        return countryRepository.save(city);
    }
}
