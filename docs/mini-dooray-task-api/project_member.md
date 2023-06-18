# /project_member

## 프로젝트 멤버 조회

* Request

  ```
  GET http://localhost:9091/projects/{project-id}/members
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
      "projectAuthorityId": 2,
      "targetMemberId": "suebin"
    },
    {
      "projectAuthorityId": 2,
      "targetMemberId": "jang"
    }
  ]
  ```

## 프로젝트 멤버 초대

* Request

  ```
  POST http://localhost:9091/projects/{project-id}/members
  Content-Type: application/json

  [
    {
      "targetMemberId": "user1",
      "projectAuthorityId": 2
    },
    {
      "targetMemberId": "user2",
      "projectAuthorityId": 2
    }
  ]
  ```
  
* Response

  ```
  HTTP/1.1 201
  Content-Type: application/json;charset=UTF-8
  Transfer-Encoding: chunked
  Date: Sat, 17 Jun 2023 21:11:57 GMT
  Keep-Alive: timeout=60
  Connection: keep-alive

  {
    "result": "OK"
  }
  ```

## 프로젝트 멤버 수정

* Request

  ```
  PUT http://localhost:9091/projects/{project-id}/members
  Content-Type: application/json

  {
    "targetMemberId": "suebin",
    "projectAuthorityId": 1
  }
  ```

* Response

  ```
  HTTP/1.1 200
  Content-Type: application/json;charset=UTF-8
  Transfer-Encoding: chunked
  Date: Sat, 17 Jun 2023 21:21:24 GMT
  Keep-Alive: timeout=60
  Connection: keep-alive

  {
    "result": "OK"
  }
  ```

## 프로젝트 멤버 삭제

* Request

  ```
  DELETE http://localhost:9091/projects/{project-id}/members
  [
    {
      "targetMemberId": "user1"
    },
    {
      "targetMemberId": "user2"
    }
  ]
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