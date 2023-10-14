package com.wanted.service;

import com.wanted.entity.Company;
import com.wanted.entity.JobPosting;
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
        try{
            companyInit();
            jobPostingInit();
        }catch (Exception e){
            e.getMessage();
        }

    }

    private void companyInit(){
        String[] names = {"","원티드랩","원티드코리아","네이버","카카오"};
        for(Long i = 1L; i < 5; i++){
            Company company = new Company(i,names[Math.toIntExact(i)], "한국","서울");
            companyRepository.save(company);
        }
    }
    private void jobPostingInit() throws Exception{

        for(Long i = 1L; i < 5; i++){
            Company company = companyRepository.findById(i).orElseThrow(Exception::new);
            JobPosting jobPosting = new JobPosting(i,company,"포지션"+i,
                    (int)(i * 1000),"아무나오세요"+i,"java" + i);
            jobPostingRepository.save(jobPosting);
        }
    }


}
