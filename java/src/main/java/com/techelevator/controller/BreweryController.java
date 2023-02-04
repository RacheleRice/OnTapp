package com.techelevator.controller;

import com.techelevator.dao.BeerDao;
import com.techelevator.dao.BreweryDao;
import com.techelevator.dao.ReviewDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Beer;
import com.techelevator.model.Brewery;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin
//@PreAuthorize("isAuthenticated()")
public class BreweryController {

    private UserDao userDao;
    private BeerDao beerDao;
    private BreweryDao breweryDao;
    private ReviewDao reviewDao;


    public BreweryController(UserDao userDao, BeerDao beerDao, BreweryDao breweryDao, ReviewDao reviewDao) {
        this.userDao = userDao;
        this.beerDao = beerDao;
        this.breweryDao = breweryDao;
        this.reviewDao = reviewDao;

    }

    @GetMapping(path="/brewery/all")
    public List<Brewery> getAllBreweries() { return breweryDao.findAll(); }

    @GetMapping(path="/brewery/{id}")
    public Brewery getBreweryById(@PathVariable int id) { return breweryDao.getBreweryById(id); }

    @GetMapping(path="/brewery/q={name}")
    public Brewery findBreweryByName(@PathVariable String name) {
        return breweryDao.findByBreweryName(name);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path="/brewery/create")
    public boolean createBrewery(@RequestBody Brewery brewery) {
        return breweryDao.createBrewery(brewery);
    }

//    @GetMapping(path="/brewery/all")
//    public List<Beer> findBeersByBreweryId() { return breweryDao.findBeersByBreweryId(); }

    @PutMapping(path="/brewery/beers/{id}")
    public boolean updateBrewery(@RequestBody Brewery brewery, @PathVariable int id) {
        return breweryDao.updateBrewery(brewery);
    }

}