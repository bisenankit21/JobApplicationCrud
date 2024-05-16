package com.example.springboot.JobApplicationCrud.review.impl;

import com.example.springboot.JobApplicationCrud.company.Company;
import com.example.springboot.JobApplicationCrud.company.CompanyRepository;
import com.example.springboot.JobApplicationCrud.company.CompanyService;
import com.example.springboot.JobApplicationCrud.review.Review;
import com.example.springboot.JobApplicationCrud.review.ReviewController;
import com.example.springboot.JobApplicationCrud.review.ReviewRepository;
import com.example.springboot.JobApplicationCrud.review.ReviewSevice;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ReviewServiceImpl implements ReviewSevice {
    private ReviewRepository reviewRepository;
    private CompanyService companyService;

    public ReviewServiceImpl(CompanyService companyService, ReviewRepository reviewRepository) {
        this.companyService = companyService;
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<Review>  getAllReviews(Long companyId) {
        List<Review> reviews=reviewRepository.findByCompanyId(companyId);
        return reviews;
    }

    @Override
    public boolean addReview(Long companyId, Review review) {
        Company company=companyService.getCompanyById(companyId);
        if(company!=null){
            review.setCompany(company);
            reviewRepository.save(review);
            return true;
        }
        return false;
    }
    @Override
    public Review getReview(Long companyId, Long reviewId) {
        List<Review> reviews=reviewRepository.findByCompanyId(companyId);
        return reviews.stream().filter(review -> review.getId().equals(reviewId)).findFirst().orElse(null);

    }

    @Override
    public boolean updateReview(Long companyId, Long reviewId, Review review) {
        if(companyService.getCompanyById(companyId)!=null){
            review.setCompany(companyService.getCompanyById(companyId));
            review.setId(reviewId);
            reviewRepository.save(review);
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public boolean deleteReview(Long companyId, Long reviewId) {
        if(companyService.getCompanyById(companyId)!=null && reviewRepository.existsById(reviewId)){
            Review review=reviewRepository.findById(reviewId).orElse(null);
            Company company=review.getCompany();
            company.getReviews().remove(review);
            review.setCompany(null);
            companyService.updateCompany(company,companyId);
            reviewRepository.deleteById(reviewId);
            return true;
        }
        else{
            return false;
        }
    }
}
