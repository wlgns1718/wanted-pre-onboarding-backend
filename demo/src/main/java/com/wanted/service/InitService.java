package com.wanted.service;

import com.wanted.entity.Company;
import com.wanted.repository.CompanyRepository;
import com.wanted.repository.JobPostingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class InitService {

    private final JobPostingRepository jobPostingRepository;
    private final CompanyRepository companyRepository;
    @PostConstruct
    public void init(){
        companyinit();
    }

    private void companyinit(){
        for(Long i = 1L; i < 5; i++){
            Company company = new Company(i,"회사"+i, "한국","서울");
            companyRepository.save(company);
        }
    }


}
