package com.techelevator.dao;

import java.util.ArrayList;
import java.util.List;

import com.techelevator.model.Beer;
import com.techelevator.model.Brewery;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

@Service
public class JdbcBreweryDao implements BreweryDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcBreweryDao(JdbcTemplate jdbcTemplate) {this.jdbcTemplate = jdbcTemplate;}



    @Override
    public List<Brewery> findAll() {
        List<Brewery> breweries = new ArrayList<>();
        String sql = "SELECT * from breweries";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while(results.next()) {
            Brewery brewery = mapRowToBrewery(results);
            breweries.add(brewery);
        }

        return breweries;
    }


    @Override
    public Brewery getBreweryById(int breweryId) {
        String sql = "SELECT * FROM breweries WHERE brewery_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, breweryId);
        if(results.next()) {
            return mapRowToBrewery(results);
        } else {
            throw new RuntimeException("brewery ID " + breweryId + " was not found.");
        }
    }


    @Override
    public Brewery findByBreweryName(String breweryName) {
        String sql = "SELECT * FROM breweries WHERE brewery_name = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, breweryName);
        if(results.next()) {
            return mapRowToBrewery(results);
        } else {
            throw new RuntimeException("brewery name "+breweryName+" was not found.");
        }
    }

    @Override
    public List<Beer> findBeersByBreweryId() {
        List<Beer> beers = new ArrayList<>();
        String sql = "SELECT * from beers " +
                "WHERE brewery_id = ?";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while(results.next()) {
            Beer beer = mapRowToBeer2(results);
            beers.add(beer);
        }

        return beers;
    }


    @Override
    public boolean createBrewery(Brewery brewery) {

        String breweryName = brewery.getBreweryName();
        int ownerId = brewery.getBreweryId();
        String breweryImg = brewery.getBreweryImg();
        String description = brewery.getDescription();
        boolean isActive = brewery.getIsActive();
        String address = brewery.getAddress();
        String city = brewery.getCity();
        String state = brewery.getState();
        String zip = brewery.getZip();


        try {
            String sql = "INSERT into breweries " +
                    "(brewery_name, owner_id, brewery_img, description, isActive, address, city, state, zip) " +
                    "VALUES( ?, ?, ?, ?, ?, ?, ?, ?, ?); ";
            jdbcTemplate.update(sql, breweryName, ownerId, breweryImg, description, isActive, address, city, state, zip);
            return true;
        }
        catch(DataAccessException e) {
            System.out.println(e.getMessage());
            return false;
        }

    }


    @Override
    public boolean deleteBrewery(Brewery brewery) {
        int breweryId = brewery.getBreweryId();
        String sql = "DELETE FROM breweries WHERE brewery_id = ?";
        try{
            jdbcTemplate.update(sql, breweryId);
            return true;
        }
        catch (DataAccessException e){
            System.out.print(e.getMessage());
            return false;
        }

    }



    @Override
    public boolean updateBrewery(Brewery brewery) {
        int breweryId = brewery.getBreweryId();
        String breweryName = brewery.getBreweryName();
        int ownerId = brewery.getBreweryId();
        String breweryImg = brewery.getBreweryImg();
        String description = brewery.getDescription();
        boolean isActive = brewery.getIsActive();
        String address = brewery.getAddress();
        String city = brewery.getCity();
        String state = brewery.getState();
        String zip = brewery.getZip();

        String sql = "UPDATE breweries SET brewery_name = ?, owner_id = ?, brewery_img = ?, description = ?, isActive = ? " +
                "address = ?, city = ?, state = ?, zip = ?" +
                "WHERE brewery_id = ?; ";
        try {
            jdbcTemplate.update(sql,breweryName, ownerId, breweryImg, description, isActive, breweryId, address, city, state, zip);
            return true;
        }
        catch (DataAccessException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    //helper method
    private Brewery mapRowToBrewery(SqlRowSet rs) {
        Brewery brewery = new Brewery();
        brewery.setBreweryId(rs.getInt("brewery_id"));
        brewery.setBreweryName(rs.getString("brewery_name"));
        brewery.setOwnerId(rs.getInt("owner_id"));
        brewery.setBreweryImg(rs.getString("brewery_img"));
        brewery.setDescription(rs.getString("description"));
        brewery.setActive(rs.getBoolean("is_active"));
        brewery.setAddress(rs.getString("address"));
        brewery.setCity(rs.getString("city"));
        brewery.setState(rs.getString("state"));
        brewery.setZip(rs.getString("zip"));

        return brewery;
    }

    //second helper method
    private Beer mapRowToBeer2(SqlRowSet results) {

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
