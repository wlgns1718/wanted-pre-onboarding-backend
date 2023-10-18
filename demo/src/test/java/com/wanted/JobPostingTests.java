package com.wanted;


import com.wanted.entity.Company;
import com.wanted.entity.JobPosting;
import com.wanted.repository.CompanyRepository;
import com.wanted.repository.JobPostingRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class JobPostingTests {

    @Autowired
    private JobPostingRepository jobPostingRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Test
    @DisplayName("공고 등록")
    public void jobPosting_saveTest(){

        //given
        Company company = Company.builder()
                .name("원티드")
                .nation("한국")
                .region("서울")
                .build();
        companyRepository.save(company);

        JobPosting jobPosting1 = JobPosting.builder()
                .content("테스트 컨텐트1")
                .compensation(100000)
                .company(company)
                .position("테스트 포지션1")
                .skill("테스트 스킬1")
            .build();

        JobPosting jobPosting2 = JobPosting.builder()
                .content("테스트 컨텐트2")
                .compensation(200000)
                .company(company)
                .position("테스트 포지션2")
                .skill("테스트 스킬2")
                .build();

        //when
        JobPosting result1 = jobPostingRepository.save(jobPosting1);
        JobPosting result2 = jobPostingRepository.save(jobPosting2);

        //then
        Assertions.assertThat(result1.getSkill()).isEqualTo(jobPosting1.getSkill());
        Assertions.assertThat(result1.getContent()).isEqualTo(jobPosting1.getContent());
        Assertions.assertThat(result1.getCompensation()).isEqualTo(jobPosting1.getCompensation());
        Assertions.assertThat(result1.getPosition()).isEqualTo(jobPosting1.getPosition());
        Assertions.assertThat(result1.getCompany()).isEqualTo(jobPosting1.getCompany());

        Assertions.assertThat(result2.getSkill()).isEqualTo(jobPosting2.getSkill());
        Assertions.assertThat(result2.getContent()).isEqualTo(jobPosting2.getContent());
        Assertions.assertThat(result2.getCompensation()).isEqualTo(jobPosting2.getCompensation());
        Assertions.assertThat(result2.getPosition()).isEqualTo(jobPosting2.getPosition());
        Assertions.assertThat(result2.getCompany()).isEqualTo(jobPosting2.getCompany());

    }

    @Test
    @DisplayName("공고 수정하기")
    public void updateJobPosting(){
        //given
        Company company = Company.builder()
                .name("원티드")
                .nation("한국")
                .region("서울")
                .build();
        Company company_result = companyRepository.save(company);

        JobPosting jobPosting = JobPosting.builder()
                .content("테스트 컨텐트1")
                .compensation(100000)
                .company(company)
                .position("테스트 포지션1")
                .skill("테스트 스킬1")
                .build();

        jobPostingRepository.save(jobPosting);

        JobPosting update = jobPostingRepository.findByCompany_CompanyId(company_result.getCompanyId());
        update.setSkill("수정된 스킬");
        update.setCompensation(30000);
        update.setContent("수정된 내용");
        update.setPosition("수정된 포지션");

        //when
        JobPosting result = jobPostingRepository.findByCompany_CompanyId(company_result.getCompanyId());

        //then
        Assertions.assertThat(result.getSkill()).isEqualTo(update.getSkill());
        Assertions.assertThat(result.getCompensation()).isEqualTo(update.getCompensation());
        Assertions.assertThat(result.getContent()).isEqualTo(update.getContent());
        Assertions.assertThat(result.getPosition()).isEqualTo(update.getPosition());
    }

    @Test
    @DisplayName("공고 삭제하기")
    public void deleteJobPosting(){
        //given
        Company company = Company.builder()
                .name("원티드")
                .nation("한국")
                .region("서울")
                .build();
        companyRepository.save(company);

        JobPosting jobPosting = JobPosting.builder()
                .content("테스트 컨텐트1")
                .compensation(100000)
                .company(company)
                .position("테스트 포지션1")
                .skill("테스트 스킬1")
                .build();

        jobPostingRepository.save(jobPosting);
        List<JobPosting> list = jobPostingRepository.findAll();
        Long resultId = list.get(0).getJobPostingId();
        jobPostingRepository.deleteById(resultId);

        //when
        List<JobPosting> result = jobPostingRepository.findAll();

        //then
        Assertions.assertThat(result.size()).isEqualTo(0);
    }


    @Test
    @DisplayName("공고 검색하기")
    public void getSearchJobPosting(){

        //given
        Company company1 = Company.builder()
                .name("원티드")
                .nation("한국")
                .region("서울")
                .build();

        Company company2 = Company.builder()
                .name("카카오")
                .nation("한국")
                .region("서울")
                .build();

        companyRepository.save(company1);
        companyRepository.save(company2);

        JobPosting jobPosting1 = JobPosting.builder()
                .content("테스트 컨텐트1")
                .compensation(100000)
                .company(company1)
                .position("테스트 포지션1")
                .skill("테스트 스킬1")
                .build();

        JobPosting jobPosting2 = JobPosting.builder()
                .content("테스트 컨텐트2")
                .compensation(200000)
                .company(company2)
                .position("테스트 포지션2")
                .skill("테스트 스킬2")
                .build();

        jobPostingRepository.save(jobPosting1);
        jobPostingRepository.save(jobPosting2);

        //when
        List<JobPosting> result = jobPostingRepository.findByContainingWord("원티드");


        //then
        Assertions.assertThat(result.size()).isEqualTo(1);
        Assertions.assertThat(result.get(0).getCompany().getName()).isEqualTo("원티드");

    }

    @Test
    @DisplayName("공고 불러오기")
    public void getJobPostings(){

        //given
        Company company = Company.builder()
                .name("원티드")
                .nation("한국")
                .region("서울")
                .build();
        companyRepository.save(company);

        JobPosting jobPosting1 = JobPosting.builder()
                .content("테스트 컨텐트1")
                .compensation(100000)
                .company(company)
                .position("테스트 포지션1")
                .skill("테스트 스킬1")
                .build();

        JobPosting jobPosting2 = JobPosting.builder()
                .content("테스트 컨텐트2")
                .compensation(200000)
                .company(company)
                .position("테스트 포지션2")
                .skill("테스트 스킬2")
                .build();

        jobPostingRepository.save(jobPosting1);
        jobPostingRepository.save(jobPosting2);

        //when

        List<JobPosting> result = jobPostingRepository.findAll();

        //then
        Assertions.assertThat(result.size()).isEqualTo(2);

    }

    @Test
    @DisplayName("공고 상세보기")
    public void getJobPostingDetail(){

        //given
        Company company = Company.builder()
                .name("원티드")
                .nation("한국")
                .region("서울")
                .build();

        Company company_result = companyRepository.save(company);

        JobPosting jobPosting = JobPosting.builder()
                .content("테스트 컨텐트1")
                .compensation(100000)
                .company(company)
                .position("테스트 포지션1")
                .skill("테스트 스킬1")
                .build();

        jobPostingRepository.save(jobPosting);

        //when
        JobPosting result = jobPostingRepository.findByCompany_CompanyId(company_result.getCompanyId());

        //then
        Assertions.assertThat(result.getCompany().getNation()).isEqualTo(company.getNation());
        Assertions.assertThat(result.getCompany().getRegion()).isEqualTo(company.getRegion());
        Assertions.assertThat(result.getCompany().getName()).isEqualTo(company.getName());
        Assertions.assertThat(result.getSkill()).isEqualTo(jobPosting.getSkill());
        Assertions.assertThat(result.getCompensation()).isEqualTo(jobPosting.getCompensation());
        Assertions.assertThat(result.getContent()).isEqualTo(jobPosting.getContent());
        Assertions.assertThat(result.getPosition()).isEqualTo(jobPosting.getPosition());
    }


}
