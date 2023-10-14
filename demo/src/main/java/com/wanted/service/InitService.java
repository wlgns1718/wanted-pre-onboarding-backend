package com.wanted.service;

import com.wanted.entity.Company;
import com.wanted.entity.JobPosting;
import com.wanted.entity.User;
import com.wanted.repository.CompanyRepository;
import com.wanted.repository.JobPostingRepository;
import com.wanted.repository.UserRepository;
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
    private final UserRepository userRepository;


    @PostConstruct
    public void init(){
        try{
            companyInit();
            jobPostingInit();
            userInit();
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
        String[] positinos = {"","Django 백엔드 개발자","Django 백엔드 개발자","Java 백엔드 개발자","프론트엔드 개발자"};
        for(Long i = 1L; i < 5; i++){
            Company company = companyRepository.findById(i).orElseThrow(Exception::new);
            JobPosting jobPosting = new JobPosting(i,company,positinos[Math.toIntExact(i)],
                    (int)(i * 1000),"아무나오세요"+i,"java" + i);
            jobPostingRepository.save(jobPosting);
        }
    }

    private void userInit(){
        for(Long i = 1L; i < 5; i++){
            User user = new User(i,"홍길동"+i,(int) (20+i));
            userRepository.save(user);
        }
    }
}
