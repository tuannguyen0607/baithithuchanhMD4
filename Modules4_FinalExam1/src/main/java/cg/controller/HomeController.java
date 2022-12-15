package cg.controller;

import cg.model.City;
import cg.service.ICityService;
import cg.service.ICountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private ICityService cityService;

    @Autowired
    private ICountryService countryService;

    @GetMapping
    public ModelAndView showAll() {
        ModelAndView modelAndView = new ModelAndView("list");
        List<City> cities = cityService.findAll();
        if (cities.isEmpty()) {
            modelAndView.addObject("message", "No Product!!!");
        }
        modelAndView.addObject("cities", cities);
        return modelAndView;

    }

    @GetMapping("/view")
    public ModelAndView getDetail(@RequestParam("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("detail");
        City city = cityService.findById(id);
        modelAndView.addObject("city", city);
        return modelAndView;
    }

    @GetMapping("/delete")
    public ModelAndView deleteCity(@RequestParam("id") Long id) {
//        ModelAndView modelAndView = new ModelAndView("list");
        cityService.deleteById(id);
        return showAll();
    }

    @GetMapping("/create")
    public ModelAndView createNewCity() {
        ModelAndView modelAndView = new ModelAndView("create");
        modelAndView.addObject("countries", countryService.findAll());
        modelAndView.addObject("city", new City());
        return modelAndView;
    }

    @PostMapping("/save")
    public ModelAndView save(@Validated @ModelAttribute City city, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView("create");
        if (bindingResult.hasFieldErrors()) {
            modelAndView.addObject("message", "Create Fail !!!");
            modelAndView.addObject("countries", countryService.findAll());
            return modelAndView;
        }
        City city1 = cityService.save(city);
        if (city1 != null) {
            modelAndView.addObject("message", "Create Product Successfully !!!");
        }
        modelAndView.addObject("countries", countryService.findAll());
        return modelAndView;
    }

    @GetMapping("/edit")
    public ModelAndView editCity(@RequestParam("id") Long id){
        ModelAndView modelAndView = new ModelAndView("edit");
        City city = cityService.findById(id);
        modelAndView.addObject("city", city);
        modelAndView.addObject("countries", countryService.findAll());
        return modelAndView;
    }

    @PostMapping("/update")
    public ModelAndView updateCity(@RequestParam("id") Long id, @Validated @ModelAttribute City city, BindingResult bindingResult){
        ModelAndView modelAndView = new ModelAndView("edit");
        if (bindingResult.hasFieldErrors()) {
            modelAndView.addObject("message", "Update Fail!!!");
            modelAndView.addObject("countries", countryService.findAll());
            return modelAndView;
        }
        city.setIdCity(id);
        cityService.save(city);
        modelAndView.addObject("countries", countryService.findAll());
        modelAndView.addObject("message", "Update Product Successfully !!!");
        return modelAndView;
    }
}
