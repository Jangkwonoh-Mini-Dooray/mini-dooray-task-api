# /project_status

## 프로젝트 상태 목록 정보 조회

* Request

  ```
  GET http://localhost:9091/project-status
  ```

* Response

  ```
  HTTP/1.1 200
  Content-Type: application/json;charset=UTF-8
  Transfer-Encoding: chunked
  Date: Sun, 18 Jun 2023 08:53:54 GMT
  Keep-Alive: timeout=60
  Connection: keep-alive
  
  [
    {
      "projectStatusId": 1,
      "name": "활성"
    },
    {
      "projectStatusId": 2,
      "name": "휴면"
    },
    {
      "projectStatusId": 3,
      "name": "종료"
    }
  ]
  ```

## 프로젝트 상태 단건 정보 조회

* Request

  ```
  GET http://localhost:9091/project-status/{project-status-id}
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
    "name": "활성"
  }
  ```

## 프로젝트 상태 정보 생성

* Request

  ```
  POST http://localhost:9091/project-status
  Content-Type: application/json

  {
    "projectStatusId" : 4,
    "name" : "name4"
  }
  ```

* Response

  ```
  HTTP/1.1 201
  Content-Type: application/json;charset=UTF-8
  Transfer-Encoding: chunked
  Date: Sun, 18 Jun 2023 09:53:46 GMT
  Keep-Alive: timeout=60
  Connection: keep-alive
  
  {
    "projectStatusId": 4
  }
  ```

## 프로젝트 상태 정보 수정

* Request

  ```
  PUT http://localhost:9091/project-status/{project-status-id}
  Content-Type: application/json

  ```

* Response

  ```
  HTTP/1.1 201 
  Content-Type: application/json;charset=UTF-8
  Transfer-Encoding: chunked
  Date: Sun, 18 Jun 2023 09:56:10 GMT
  Keep-Alive: timeout=60
  Connection: keep-alive

  {
    "projectStatusId": 4
  }
  ```

## 프로젝트 상태 정보 삭제

* Request

  ```
  DELETE http://localhost:9091/project-status/{project-status-id}
  ```

* Response

  ```
  HTTP/1.1 200 
  Content-Type: application/json;charset=UTF-8
  Transfer-Encoding: chunked
  Date: Sun, 18 Jun 2023 09:57:57 GMT
  Keep-Alive: timeout=60
  Connection: keep-alive

  {
    "result": "OK"
  }
  ```