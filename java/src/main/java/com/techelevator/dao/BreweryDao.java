package com.techelevator.dao;

import com.techelevator.model.Beer;
import com.techelevator.model.Brewery;

import java.util.List;

public interface BreweryDao {
    List<Brewery> findAll();

    Brewery getBreweryById(int breweryId);

    Brewery findByBreweryName(String breweryName);

    List<Beer> findBeersByBreweryId();
    boolean createBrewery(Brewery brewery);

    boolean deleteBrewery(Brewery brewery);

    boolean updateBrewery(Brewery brewery);



}



