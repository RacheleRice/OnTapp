package com.techelevator.dao;

import com.techelevator.model.Review;

import java.util.List;

public interface ReviewDao {
    List<Review> getReviewsByBeerId(int beerId);

    List<Integer> getRatingsByBeerId(int beerId);

    Review getReviewById(int reviewId);

    List<Review> getReviewsByUserId(int UserId);

    boolean createReview(Review review);

    boolean updateReview(Review review);

    boolean deleteReview(Review review);

    double getAverage(List<Integer> ratings);

    double getAverageRating(int beerId);


}
