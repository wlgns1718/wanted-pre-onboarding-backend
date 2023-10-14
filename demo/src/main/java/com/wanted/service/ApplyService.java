package com.wanted.service;


import com.wanted.dto.ApplyDto;
import org.springframework.stereotype.Service;

@Service
public interface ApplyService {

    void applyJobPosting(ApplyDto applyDto) throws Exception;
}
