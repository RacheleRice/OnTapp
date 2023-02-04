package com.techelevator.model;

import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.time.LocalDate;

public class Review {


    private int reviewId;
    @NotNull
    private int userId;
    @NotNull
    private int beerId;
    @NotNull
    private int rating;
    private String review;
    private LocalDate createDate;

    public Review() {}

    public Review(int beerId, int rating, String review, LocalDate createDate) {
        this.beerId = beerId;
        this.rating = rating;
        this.review = review;
        this.createDate = createDate;
    }

    public int getReviewId (){return reviewId;}
    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBeerId() {
        return beerId;
    }

    public void setBeerId(int beerId) {
        this.beerId = beerId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "Review{" +
                "reviewId=" + reviewId +
                ", userId=" + userId +
                ", beerId=" + beerId +
                ", rating=" + rating +
                ", review='" + review + '\'' +
                ", createDate=" + createDate +
                '}';
    }
}
