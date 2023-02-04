package com.techelevator.dao;

import com.techelevator.model.Review;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcReviewDao implements ReviewDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcReviewDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Review> getReviewsByBeerId(int beerId) {
        List<Review> reviews = new ArrayList<>();
        String sql = "SELECT * FROM reviews WHERE beer_id = ?";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, beerId);
        while(results.next()) {
            Review review = mapRowToReview(results);
            reviews.add(review);
        }
        return reviews;
    }

    @Override
    public List<Integer> getRatingsByBeerId(int beerId) {
        List<Integer> ratings = new ArrayList<>();
        String sql = "SELECT rating FROM reviews WHERE beer_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, beerId);
        while(results.next()){
            ratings.add(results.getInt("rating"));
        }
        return ratings;
    }

    public Review getReviewById(int reviewId) {
        String sql = "SELECT * FROM reviews WHERE review_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, reviewId);
        if (results.next()) {
            return mapRowToReview(results);
        } else {
            throw new RuntimeException("review ID " + reviewId + " was not found.");
        }
    }

    @Override
    public List<Review> getReviewsByUserId (int userId) {
        List<Review> userReviews = new ArrayList<>();
        String sql = "SELECT * FROM reviews WHERE user_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
        while (results.next()) {
            userReviews.add(mapRowToReview(results));
        }
        return userReviews;
    }

    @Override
    public boolean createReview(Review review) {
        int userId = review.getUserId();
        int beerId = review.getBeerId();
        int rating = review.getRating();
        String reviewText = review.getReview();

        try {
            String sql = "INSERT into reviews (user_id, beer_id, rating, review) VALUES(?, ?, ?, ?); ";
            jdbcTemplate.update(sql, userId, beerId, rating, reviewText);
            return true;
        }
        catch(DataAccessException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean updateReview(Review review) {
        int reviewId = review.getReviewId();
        int userId = review.getUserId();
        int beerId = review.getBeerId();
        int rating = review.getRating();
        String reviewText = review.getReview();

        String sql = "UPDATE reviews SET user_id = ?, beer_id = ?, rating = ?, review = ? " +
                "WHERE review_id = ?;";
        try{
        jdbcTemplate.update(sql, userId, beerId, rating, reviewText, reviewId);
        return true;
        }
        catch(DataAccessException e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteReview(Review review) {
        int reviewId = review.getReviewId();
        String sql = "DELETE FROM reviews WHERE review_id = ?; ";
        try{
            jdbcTemplate.update(sql, reviewId);
            return true;
        }
        catch(DataAccessException e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public double getAverage(List<Integer> ratings){
        int sum = 0;
        for (int rating: ratings) {
            sum += rating;
        }
        return (double)(sum /(ratings.size()));
    }

    @Override
    public double getAverageRating(int beerId) {
        try {
            return jdbcTemplate.queryForObject("SELECT AVG(rating) FROM reviews WHERE beer_id = ?;", double.class, beerId);
        }
        catch(Exception e) {
            throw new RuntimeException("No ratings found for beer ID " + beerId);
        }
//        String sql = "SELECT AVG(rating) FROM reviews WHERE beer_id = ?;";
//        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
//        if (results.next()) {
//            return results.getDouble("avg");
//        }
//        else {
//            throw new RuntimeException("No ratings found for beer ID " + beerId);
//        }
    }

    private Review mapRowToReview(SqlRowSet rs) {
        Review review = new Review();
        review.setReviewId(rs.getInt("review_id"));
        review.setUserId(rs.getInt("user_id"));
        review.setBeerId(rs.getInt("beer_id"));
        review.setRating(rs.getInt("rating"));
        review.setReview(rs.getString("review"));
        review.setCreateDate(rs.getDate("create_date").toLocalDate());
        return review;
    }
}
