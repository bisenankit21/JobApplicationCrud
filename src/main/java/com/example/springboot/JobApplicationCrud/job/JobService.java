package com.example.springboot.JobApplicationCrud.job;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface JobService {
    List<Job> findAll();
    void createJob(Job job);
    Job getJobById(Long id);
    boolean deleteJobById(Long id);
    boolean updateJobById(Long id,Job job);


}
