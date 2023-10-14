package com.wanted.service;


import com.wanted.dto.RegistDto;
import com.wanted.dto.UpdateDto;
import org.springframework.stereotype.Service;

@Service
public interface JobPostingService {

    Long registJobPosting(RegistDto registDto) throws Exception;

    Long updateJobPosting(UpdateDto updateDto) throws Exception;
}
