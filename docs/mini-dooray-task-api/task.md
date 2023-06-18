`````````````# /task

## 프로젝트 내 전체 업무 조회

* Request

  ```
  GET http://localhost:9091/projects/{project-id}/posts
  ```

* Response

  ```
  HTTP/1.1 200
  Content-Type: application/json;charset=UTF-8
  Transfer-Encoding: chunked
  Date: Sun, 18 Jun 2023 02:42:14 GMT
  Keep-Alive: timeout=60
  Connection: keep-alive

  [
    {
      "taskId": 4,
      "taskWriterMemberId": "suebin",
      "milestone": {
        "milestoneId": 1,
        "name": "없음",
        "startPeriod": null,
        "endPeriod": null,
        "status": "진행",
        "project": {
          "projectId": 1,
          "projectStatus": {
            "projectStatusId": 2,
            "name": "휴면"
          },
          "name": "미니 두레이",
          "description": "미니 두레이"
        }
      },
      "title": "Practice Spring boot",
      "content": "Practice Spring Boot"
    },
    {
      "taskId": 7,
      "taskWriterMemberId": "suebin",
      "milestone": {
        "milestoneId": 1,
        "name": "없음",
        "startPeriod": null,
        "endPeriod": null,
        "status": "진행",
        "project": {
          "projectId": 1,
          "projectStatus": {
            "projectStatusId": 2,
            "name": "휴면"
          },
          "name": "미니 두레이",
          "description": "미니 두레이"
        }
      },
      "title": "ss",
      "content": "text"
    }
  ]
  ```

## 프로젝트 내 특정 업무 조회

* Request

  ```
  GET http://localhost:9091/projects/{project-id}/posts/{task-id}
  ```

* Response

  ```
  HTTP/1.1 200
  Content-Type: application/json;charset=UTF-8
  Transfer-Encoding: chunked
  Date: Sun, 18 Jun 2023 02:50:15 GMT
  Keep-Alive: timeout=60
  Connection: keep-alive

  {
    "taskId": 4,
    "taskWriterMemberId": "suebin",
    "milestone": {
      "milestoneId": 1,
      "name": "없음",
      "startPeriod": null,
      "endPeriod": null,
      "status": "진행",
      "project": {
        "projectId": 1,
        "projectStatus": {
          "projectStatusId": 2,
          "name": "휴면"
        },
        "name": "미니 두레이",
        "description": "미니 두레이"
      }
    },
    "title": "Practice Spring boot",
    "content": "Practice Spring Boot"
  }
  ```

## 프로젝트 내 업무 생성

* Request

  ```
  POST http://localhost:9091/projects/{project-id}/posts
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
  Date: Sun, 18 Jun 2023 03:00:00 GMT
  Keep-Alive: timeout=60
  Connection: keep-alive

  {
    "taskId": 16
  }
  ```

## 프로젝트 내 업무 수정

* Request

  ```
  PUT http://localhost:9091/projects/{project-id}/posts/{task-id}
  Content-Type: application/json

  {
    "taskWriterMemberId" : "naht94",
    "milestoneId" : 1,
    "title" : "업무 수정",
    "content" : "테스트 업무 수정"
  }
  ```

* Response

  ```
  HTTP/1.1 200 
  Content-Type: application/json;charset=UTF-8
  Transfer-Encoding: chunked
  Date: Fri, 09 Jun 2023 05:58:41 GMT
  Keep-Alive: timeout=60
  Connection: keep-alive

  {
    "memberId": "naht94"
  }
  ```

## 업무 삭제

* Request

  ```
  DELETE http://localhost:9091/projects/posts/{task-id}
  ```

* Response

  ```
  HTTP/1.1 200
  Content-Type: application/json;charset=UTF-8
  Transfer-Encoding: chunked
  Date: Sun, 18 Jun 2023 05:56:00 GMT
  Keep-Alive: timeout=60
  Connection: keep-alive


  {
    "result": "OK"
  }
  ````````````````