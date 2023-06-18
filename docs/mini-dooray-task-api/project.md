# /project

## 전체 프로젝트 조회

* Request

  ```
  GET http://localhost:9091/projects
  ```

* Response

  ```
  HTTP/1.1 200 
  Content-Type: application/json;charset=UTF-8
  Transfer-Encoding: chunked
  Date: Fri, 16 Jun 2023 04:53:38 GMT
  Keep-Alive: timeout=60
  Connection: keep-alive

  [
    {
      "projectId": 1,
      "projectStatus": {
        "projectStatusId": 2,
        "name": "휴면"
      },
      "name": "미니 두레이",
      "description": "미니 두레이"
    },
    {
      "projectId": 2,
      "projectStatus": {
        "projectStatusId": 1,
        "name": "활성"
      },
      "name": "NHN Academy",
      "description": "NHN Academy 입니다."
    }
  ]
  ```

## 개별 프로젝트 조회

* Request

  ```
  GET http://localhost:9091/projects/{project-id}
  ```

* Response

  ```
  HTTP/1.1 200 
  Content-Type: application/json;charset=UTF-8
  Transfer-Encoding: chunked
  Date: Fri, 16 Jun 2023 05:05:37 GMT
  Keep-Alive: timeout=60
  Connection: keep-alive

  {
    "projectId": 1,
    "projectStatusName": "휴면",
    "name": "미니 두레이",
    "description": "미니 두레이"
  }
  ```

## 프로젝트 생성

* Request

  ```
  POST http://localhost:9091/projects
  Content-Type: application/json

  {
    "projectStatusName" : "활성",
    "name" : "NHN Academy",
    "description" : "NHN Academy 입니다."
  }
  ```

* Response

  ```
  HTTP/1.1 201 
  Content-Type: application/json;charset=UTF-8
  Transfer-Encoding: chunked
  Date: Fri, 16 Jun 2023 05:03:52 GMT
  Keep-Alive: timeout=60
  Connection: keep-alive

  {
    "projectId": 2
  }
  ```

## 프로젝트 수정

* Request

  ```
  PUT http://localhost:9091/projects/{project-id}
  Content-Type: application/json

  {
    "name" : "프로젝트2 수정",
    "projectStatusName" : "휴면",
    "description" : "updated"
  }
  ```

* Response

  ```
  HTTP/1.1 200 
  Content-Type: application/json;charset=UTF-8
  Transfer-Encoding: chunked
  Date: Fri, 16 Jun 2023 05:13:24 GMT
  Keep-Alive: timeout=60
  Connection: keep-alive

  {
    "projectId": 2
  }
  ```

## 프로젝트 멤버 아이디로 조회

* Request

  ```
  GET http://localhost:9091/projects/list/{member-id}
  ```

* Response

  ```
  HTTP/1.1 200 
  Content-Type: application/json;charset=UTF-8
  Transfer-Encoding: chunked
  Date: Sat, 17 Jun 2023 21:31:50 GMT
  Keep-Alive: timeout=60
  Connection: keep-alive


  [
    {
      "projectId": 1,
      "projectStatusName": "휴면",
      "name": "미니 두레이",
      "description": "미니 두레이"
    },
    {
      "projectId": 6,
      "projectStatusName": "활성",
      "name": "project 6",
      "description": "project 6"
    }
  ]
  ```

## 프로젝트 삭제

* Request

  ```
  DELETE http://localhost:9091/projects/{project-id}
  ```

* Response

  ```
  HTTP/1.1 200 
  Content-Type: application/json;charset=UTF-8
  Transfer-Encoding: chunked
  Date: Sat, 17 Jun 2023 21:05:24 GMT
  Keep-Alive: timeout=60
  Connection: keep-alive

  {
    "result": "OK"
  }
  ```