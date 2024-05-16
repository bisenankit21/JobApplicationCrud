package com.example.springboot.JobApplicationCrud.review;

import com.example.springboot.JobApplicationCrud.job.Job;
import com.example.springboot.JobApplicationCrud.job.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.query.PartTreeJpaQuery;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("companies/{companyId}")
public class ReviewController {
    @Autowired
    private final ReviewSevice reviewSevice;

    public ReviewController(ReviewSevice reviewSevice) {
        this.reviewSevice=reviewSevice;
    }

    @GetMapping("/reviews")
    public ResponseEntity<List<Review>> findAllReviews(@PathVariable Long companyId){
        return new ResponseEntity<>(reviewSevice.getAllReviews(companyId),HttpStatus.OK);  // we can do like this
    }

    @PostMapping("/reviews")
    public ResponseEntity<String> addReview(@PathVariable Long companyId,@RequestBody Review review) {
        boolean isReviewsaved = reviewSevice.addReview(companyId, review);
        if (isReviewsaved) {
            return new ResponseEntity<>("Review added successfully", HttpStatus.CREATED);
        }
        else {
            return new ResponseEntity<>("Review not added", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/reviews/{reviewId}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long companyId,
                                                @PathVariable long reviewId){
        Review review=reviewSevice.getReview(companyId,reviewId);
        if(review!=null){
            return new ResponseEntity<>(review,HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/reviews/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable Long companyId,
                                               @PathVariable Long reviewId,
                                               @RequestBody Review review) {
        boolean isReviewUpdated = reviewSevice.updateReview(companyId,reviewId,review);
        if (isReviewUpdated) {
            return new ResponseEntity<>("Review updated successfully", HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity<>("Review not updated",HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/reviews/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long companyId,
                                               @PathVariable Long reviewId) {
        boolean isReviewDeleted = reviewSevice.deleteReview(companyId,reviewId);
        if (isReviewDeleted) {
            return new ResponseEntity<>("Review successfully deleted", HttpStatus.OK);

        } else {
            return new ResponseEntity<>("Review Not Found", HttpStatus.NOT_FOUND);
        }
    }


}
