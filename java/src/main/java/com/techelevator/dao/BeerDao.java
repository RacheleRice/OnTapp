package com.techelevator.dao;

import com.techelevator.model.Beer;

import java.util.List;

public interface BeerDao {

    List<Beer> findAll();
    List<Beer> fetchAllBeersByBreweryId(int breweryId);
    Beer findBeerById(int beerId);
    Beer findBeerByName(String beerName);
    boolean createBeer(Beer beer);
    boolean deleteBeer(Beer beer);
    boolean updateBeer(Beer beer);





}
