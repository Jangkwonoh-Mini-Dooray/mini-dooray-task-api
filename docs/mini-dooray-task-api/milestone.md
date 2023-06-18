# /milestone

## 프로젝트 내 전체 마일스톤 조회

* Request

  ```
  GET http://localhost:9091/milestones/projects/{project-id}
  ```

* Response

  ```
  HTTP/1.1 200
  Content-Type: application/json;charset=UTF-8
  Transfer-Encoding: chunked
  Date: Sun, 18 Jun 2023 05:39:19 GMT
  Keep-Alive: timeout=60
  Connection: keep-alive

  [
    {
      "milestoneId": 1,
      "name": "없음",
      "startPeriod": null,
      "endPeriod": null,
      "status": "진행"
    },
    {
      "milestoneId": 4,
      "name": "배포",
      "startPeriod": "2023-06-15",
      "endPeriod": "2023-06-18",
      "status": "종료"
    },
    {
      "milestoneId": 8,
      "name": "ds",
      "startPeriod": null,
      "endPeriod": null,
      "status": "종료"
    }
  ]
  ```

## 특정 마일스톤 조회

* Request

  ```
  GET http://localhost:9091/milestones/{milestone-id}
  ```

* Response

  ```
  HTTP/1.1 200
  Content-Type: application/json;charset=UTF-8
  Transfer-Encoding: chunked
  Date: Sun, 18 Jun 2023 05:41:48 GMT
  Keep-Alive: timeout=60
  Connection: keep-alive

  {
    "milestoneId": 1,
    "name": "없음",
    "startPeriod": null,
    "endPeriod": null,
    "status": "진행"
  }
  ```

## 프로젝트 내 마일스톤 생성

* Request

  ```
  POST http://localhost:9091/milestones/projects/{project-id}
  Content-Type: application/json

  {
    "taskWriterMemberId" :  "naht94",
    "milestoneId" : 1,
    "title" : "업무",
    "content" : "테스트 업무"
  }
  ```

* Response

  ```
  HTTP/1.1 201
  Content-Type: application/json;charset=UTF-8
  Transfer-Encoding: chunked
  Date: Sun, 18 Jun 2023 05:43:51 GMT
  Keep-Alive: timeout=60
  Connection: keep-alive

  {
    "milestoneId": 13
  }
  ```

## 마일스톤 수정

* Request

  ```
  PUT http://localhost:9091/milestones/{milestone-id}
  Content-Type: application/json

  {
    "name" : "Account API",
    "startPeriod" : "2023-06-10",
    "endPeriod" : "2023-06-20",
    "status" : "진행"
  }
  ```

* Response

  ```
  HTTP/1.1 200
  Content-Type: application/json;charset=UTF-8
  Transfer-Encoding: chunked
  Date: Sun, 18 Jun 2023 05:46:01 GMT
  Keep-Alive: timeout=60
  Connection: keep-alive

  {
    "milestoneId": 13
  }
  ```

## 마일스톤 삭제

* Request

  ```
  DELETE http://localhost:9091/milestones/{milestone-id}
  ```

* Response

  ```
  HTTP/1.1 200
  Content-Type: application/json;charset=UTF-8
  Transfer-Encoding: chunked
  Date: Sun, 18 Jun 2023 05:48:15 GMT
  Keep-Alive: timeout=60
  Connection: keep-alive

  {
    "result": "OK"
  }
  ```