# /project_authority

## 프로젝트 권한 목록 정보 조회

* Request

  ```
  GET http://localhost:9091/project-authority
  ```

* Response

  ```
  HTTP/1.1 200 
  Content-Type: application/json;charset=UTF-8
  Transfer-Encoding: chunked
  Date: Sun, 18 Jun 2023 08:28:41 GMT
  Keep-Alive: timeout=60
  Connection: keep-alive

  [
    {
      "projectAuthorityId": 1,
      "name": "ADMIN"
    },
    {
      "projectAuthorityId": 2,
      "name": "MEMBER"
    } 
  ]
  ```

## 프로젝트 권한 단건 정보 조회

* Request

  ```
  GET http://localhost:9091/project-authority/{project-authority-id}
  ```

* Response

  ```
  HTTP/1.1 200 
  Content-Type: application/json;charset=UTF-8
  Transfer-Encoding: chunked
  Date: Sun, 18 Jun 2023 08:30:06 GMT
  Keep-Alive: timeout=60
  Connection: keep-alive

  {
    "name": "ADMIN"
  }
  ```

## 프로젝트 권한 정보 생성

* Request

  ```
  POST http://localhost:9091/project-authority
  Content-Type: application/json

  {
    "projectAuthorityId": 3,
    "name" : "name3"
  }
  ```

* Response

  ```
  HTTP/1.1 201
  Content-Type: application/json;charset=UTF-8
  Transfer-Encoding: chunked
  Date: Sun, 18 Jun 2023 09:59:29 GMT
  Keep-Alive: timeout=60
  Connection: keep-alive

  {
    "projectAuthorityId": 4
  }
  ```

## 프로젝트 권한 정보 수정

* Request

  ```
  PUT http://localhost:909/project-authority/{project-authority-id}
  Content-Type: application/json

  {
    "name" : "updated name"
  }
  ```

* Response

  ```
  HTTP/1.1 201
  Content-Type: application/json;charset=UTF-8
  Transfer-Encoding: chunked
  Date: Sun, 18 Jun 2023 10:00:39 GMT
  Keep-Alive: timeout=60
  Connection: keep-alive

  {
    "projectAuthorityId": 4
  }
  ```

## 프로젝트 권한 정보 삭제

* Request

  ```
  DELETE http://localhost:9091/project-authority/{project-authority-id}
  ```

* Response

  ```
  HTTP/1.1 202
  Content-Type: application/json;charset=UTF-8
  Transfer-Encoding: chunked
  Date: Sun, 18 Jun 2023 10:01:43 GMT
  Keep-Alive: timeout=60
  Connection: keep-alive

  {
    "result": "OK"
  }
  ```