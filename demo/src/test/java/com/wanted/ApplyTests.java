package com.wanted;

import com.wanted.entity.Apply;
import com.wanted.entity.Company;
import com.wanted.entity.JobPosting;
import com.wanted.entity.User;
import com.wanted.repository.ApplyRepository;
import com.wanted.repository.CompanyRepository;
import com.wanted.repository.JobPostingRepository;
import com.wanted.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ApplyTests {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private JobPostingRepository jobPostingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ApplyRepository applyRepository;


    @Test
    @DisplayName("공고 지원 테스트")
    public void duplicatedApplyTest(){

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

        JobPosting jobPosting_result = jobPostingRepository.save(jobPosting);

        User user = User.builder()
                .age(27)
                .name("홍길동")
                .build();

        User user_result = userRepository.save(user);

        Apply apply = Apply.builder()
                .user(user)
                .jobPosting(jobPosting)
                .build();



        //when
        Apply apply_result = applyRepository.save(apply);

        //then
        Assertions.assertThat(apply_result.getUser()).isEqualTo(user);
        Assertions.assertThat(apply_result.getJobPosting()).isEqualTo(jobPosting);
    }
}
