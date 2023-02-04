package com.techelevator.dao;


import com.techelevator.model.Beer;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcBeerDao implements BeerDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcBeerDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Beer> findAll() {
        List<Beer> beers = new ArrayList<>();
        String sql = "select * from beers";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            Beer beer = mapRowToBeer(results);
            beers.add(beer);
        }

        return beers;
    }

    //sql injection?  try ? way and test later, may work now that variable fixed in model
    @Override
    public List<Beer> fetchAllBeersByBreweryId(int breweryId) {
        String sql = "SELECT * FROM beers WHERE brewery_id = " + breweryId;

        List<Beer> beers = jdbcTemplate.query(
                sql,
                new BeanPropertyRowMapper(Beer.class));

        return beers;

    }

    @Override
    public Beer findBeerById(int beerId) {
        String sql = "SELECT * FROM beers WHERE beer_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, beerId);
        if (results.next()) {
            return mapRowToBeer(results);
        } else {
            throw new RuntimeException("beer ID " + beerId + " was not found.");
        }

    }

    @Override
    public Beer findBeerByName(String beerName) {
        String sql = "SELECT * FROM beers WHERE beer_name = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, beerName);
        if (results.next()) {
            return mapRowToBeer(results);
        } else {
            throw new RuntimeException("beer name " + beerName + " was not found.");
        }

    }

    @Override
    public boolean createBeer(Beer beer) {
        String beerName = beer.getBeerName();
        String beerImg = beer.getBeerImg();
        String description = beer.getDescription();
        double abv = beer.getAbv();
        String beerType = beer.getBeerType();
        int breweryId = beer.getBreweryId();
        boolean isActive = beer.isActive();

        try {
            String sql = "INSERT into beers " +
                    "(beer_name, beer_img, description, abv, beer_type, brewery_id, is_active) " +
                    "VALUES(?, ?, ?, ?, ?, ?, ?)";
            jdbcTemplate.update(sql, beerName, beerImg, description, abv, beerType, breweryId, isActive);
            return true;
        } catch (DataAccessException e) {
            System.out.println(e.getMessage());
            return false;
        }

    }

    @Override
    public boolean deleteBeer(Beer beer) {
        int beerId = beer.getBeerId();
        String sql = "DELETE FROM beers WHERE beer_id = ?";
        try {
            jdbcTemplate.update(sql, beerId);
            return true;
        } catch (DataAccessException e) {
            System.out.println(e.getMessage());
            return false;
        }

    }

    @Override
    public boolean updateBeer(Beer beer) {
        int beerId = beer.getBeerId();
        String beerName = beer.getBeerName();
        String beerImg = beer.getBeerImg();
        String description = beer.getDescription();
        double abv = beer.getAbv();
        String beerType = beer.getBeerType();
        int breweryId = beer.getBreweryId();
        boolean isActive = beer.isActive();

        String sql = "UPDATE beers SET beer_name = ?, beer_img = ?, description = ?, abv = ?, beer_type = ?, brewery_id =?, is_active = ?" +
                "WHERE beer_id = ?";
        try {
            jdbcTemplate.update(sql, beerName, beerImg, description, abv, beerType, breweryId, isActive, beerId);
            return true;
        } catch (DataAccessException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }


    private Beer mapRowToBeer(SqlRowSet results) {

        Beer beer = new Beer();
        beer.setBeerId(results.getInt("beer_id"));
        beer.setBeerName(results.getString("beer_name"));
        beer.setBeerImg(results.getString("beer_img"));
        beer.setDescription(results.getString("description"));
        beer.setAbv(results.getDouble("abv"));
        beer.setBeerType(results.getString("beer_type"));
        beer.setBreweryId(results.getInt("brewery_id"));
        beer.setActive(results.getBoolean("is_active"));
        return beer;

    }
}
