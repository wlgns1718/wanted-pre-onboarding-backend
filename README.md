# wanted-pre-onboarding-backend
wanted-pre-onboarding-backend

# 사용 기술 스택
```
- Java : 17
- Spring boot 2.7.17-SNAPSHOT
- Gradle
- MySQL 8.0.34
```


# 요구 사항 분석

## Erd-Diagram
![image](https://github.com/wlgns1718/wanted-pre-onboarding-backend/assets/113763592/73258761-b13a-4f7b-95d7-29d47b470927)


- 회사는 공고를 등록할 수 있기 때문에 데이터 참조 무결성을 유지하기 위해 외래키를 지정이 필요하다 생각했습니다.
- 유저가 공고에 지원할 땐 기존의 존재하는 공고와 유저의 참조 무결성을 유지하기 위해 외래키 지정이 필요하다 생각했습니다.
- 공고 등록을 위해 회사의 정보를 InitService를 이용하여 더미 데이터로 넣었습니다.
<br>

> 데이터 베이스와 연동을 위해 ORM기술로는 JPA를 사용했습니다.
> 
> 데이터 베이는 MySQL을 사용했습니다.

# 구현 과정

## 1. 채용공고 등록

> POST http://127.0.0.1:8002/wanted

```
{
    "companyId" : "1",
    "position" : "백엔드",
    "compensation" : 10000,
    "content" : "아무나 오세요",
    "skill" : "java"
}
```

### Response
```
{
    "msg": "공고 등록 완료",
    "JobPostingId": "5"
}
```

## 2. 채용공고 수정

> PUT 127.0.0.1:8002/wanted

```
{
    "jobPostingId" : "1",
    "position" : "백엔드",
    "compensation" : 10000,
    "content" : "자바 고수만",
    "skill" : "python"
}
```

### Response
```
{
    "msg": "공고 정보 수정 완료",
    "JobPostingId": "1"
}
```

## 3. 채용공고 삭제
> DELETE 127.0.0.1:8002/wanted/4

### Response
```
{
    "msg": "공고 정보 삭제 완료"
}
```

# 4. 채용공고

## 4.1 채용공고 목록
> GET 127.0.0.1:8002/wanted

### Response
```
"msg": "공고 불러오기 성공",
    "data": [
        {
            "채용공고_id": 1,
            "채용보상금": 10000,
            "채용포지션": "백엔드",
            "지역": "서울",
            "국가": "한국",
            "회사명": "원티드랩",
            "사용기술": "python"
        },
        {
            "채용공고_id": 2,
            "채용보상금": 2000,
            "채용포지션": "Django 백엔드 개발자",
            "지역": "서울",
            "국가": "한국",
            "회사명": "원티드코리아",
            "사용기술": "java2"
        },
        {
            "채용공고_id": 3,
            "채용보상금": 3000,
            "채용포지션": "Java 백엔드 개발자",
            "지역": "서울",
            "국가": "한국",
            "회사명": "네이버",
            "사용기술": "java3"
        },
        {
            "채용공고_id": 5,
            "채용보상금": 10000,
            "채용포지션": "백엔드",
            "지역": "서울",
            "국가": "한국",
            "회사명": "원티드랩",
            "사용기술": "java"
        }
    ]
}
```

## 4.2 채용공고 검색 기능
> GET 127.0.0.1:8002/wanted/search?search={keyword}



> 127.0.0.1:8002/wanted/search?search=원티드

## Response

```
{
    "msg": "공고 검색 성공",
    "data": [
        {
            "채용공고_id": 1,
            "채용보상금": 10000,
            "채용포지션": "백엔드",
            "지역": "서울",
            "국가": "한국",
            "회사명": "원티드랩",
            "사용기술": "python"
        },
        {
            "채용공고_id": 5,
            "채용보상금": 10000,
            "채용포지션": "백엔드",
            "지역": "서울",
            "국가": "한국",
            "회사명": "원티드랩",
            "사용기술": "java"
        },
        {
            "채용공고_id": 2,
            "채용보상금": 2000,
            "채용포지션": "Django 백엔드 개발자",
            "지역": "서울",
            "국가": "한국",
            "회사명": "원티드코리아",
            "사용기술": "java2"
        }
    ]
}
```

## 5. 채용 상세 페이지

> GET 127.0.0.1:8002/wanted/detail/{jobpostingid}

```
127.0.0.1:8002/wanted/detail/1
```

## Response

```
{
    "msg": "공고 상제 정보 조회 성공",
    "data": {
        "채용내용": "자바 고수만",
        "채용공고_id": 1,
        "채용보상금": 10000,
        "채용포지션": "백엔드",
        "지역": "서울",
        "국가": "한국",
        "회사명": "원티드랩",
        "사용기술": "python",
        "회사가올린다른채용공고": [
            5
        ]
    }
}
```

## 6. 채용공고에 지원
> POST 127.0.0.1:8002/wanted/apply

```
{
    "userId" : 1,
    "jobPostingId":2
    
}
```

## Response

```
{
    "msg": "지원 정보 등록 완료"
}
```
### 중복 지원시
```
{
    "userId" : 1,
    "jobPostingId":2
    
}
```
## Response
```
{
    "msg": "지원 정보 등록 실패",
    "error": "java.lang.Exception: 이미 지원한 공고입니다."
}
```













