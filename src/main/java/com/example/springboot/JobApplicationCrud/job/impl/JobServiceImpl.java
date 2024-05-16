package com.example.springboot.JobApplicationCrud.job.impl;

import com.example.springboot.JobApplicationCrud.job.Job;
import com.example.springboot.JobApplicationCrud.job.JobRepository;
import com.example.springboot.JobApplicationCrud.job.JobService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {
    JobRepository jobRepository;

    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public List<Job> findAll() {
        return jobRepository.findAll();
    }

    @Override
    public void createJob(Job job) {
        jobRepository.save(job);

    }

    @Override
    public Job getJobById(Long id) {
        return jobRepository.findById(id).orElse(null);
    }

    @Override
    public boolean deleteJobById(Long id) {
        try{
            jobRepository.deleteById(id);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean updateJobById(Long id, Job job) {
        Optional<Job> jobOptional=jobRepository.findById(id);
        if (jobOptional.isPresent()){
            Job jobToUpdate=jobOptional.get();
            jobToUpdate.setTitle(job.getTitle());
            jobToUpdate.setDescription(job.getDescription());
            jobToUpdate.setLocation(job.getLocation());
            jobToUpdate.setMinSalary(job.getMinSalary());
            jobToUpdate.setMaxSalary(job.getMaxSalary());
            jobRepository.save(job);
            return  true;
        }
        return false;
    }
}
