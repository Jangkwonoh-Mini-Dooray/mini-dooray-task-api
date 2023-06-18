# /tag

## 프로젝트 내 전체 태그 조회

* Request

  ```
  GET http://localhost:9091/projects/{project-id}/tags
  ```

* Response

  ```
  HTTP/1.1 200
  Content-Type: application/json;charset=UTF-8
  Transfer-Encoding: chunked
  Date: Sun, 18 Jun 2023 06:00:08 GMT
  Keep-Alive: timeout=60
  Connection: keep-alive

  [
    {
      "tagId": 3,
      "name": "개발"
    },
    {
      "tagId": 4,
      "name": "배포"
    }
  ]
  ```

## 프로젝트 업무 내 전체 태그 조회

* Request

  ```
  GET http://localhost:9091/projects/{project-id}/tags/{task-id}
  ```

* Response

  ```
  HTTP/1.1 200 
  Content-Type: application/json;charset=UTF-8
  Transfer-Encoding: chunked
  Date: Sun, 18 Jun 2023 06:11:31 GMT
  Keep-Alive: timeout=60
  Connection: keep-alive

  [
    {
      "tagId": 3,
      "name": "개발"
    },
    {
      "tagId": 4,
      "name": "배포"
    }
  ]
    
  ```

## 프로젝트 내 태그 생성

* Request

  ```
  POST http://localhost:9091/projects/{project-id}/tags
  Content-Type: application/json

  {
    "taskId" : "28",
    "name" : "지난활동"
  }
  ```

* Response

  ```
  HTTP/1.1 201
  Content-Type: application/json;charset=UTF-8
  Transfer-Encoding: chunked
  Date: Sun, 18 Jun 2023 06:29:47 GMT
  Keep-Alive: timeout=60
  Connection: keep-alive

  {
    "tagId": 17
  }
  ```

## 프로젝트 내 태그 생성

* Request

  ```
  POST http://localhost:9091/projects/{project-id}/tags
  Content-Type: application/json

  {
    "taskId" : null,
    "name" : "태그만"
  }
  ```

* Response

  ```
  HTTP/1.1 201
  Content-Type: application/json;charset=UTF-8
  Transfer-Encoding: chunked
  Date: Sun, 18 Jun 2023 06:29:47 GMT
  Keep-Alive: timeout=60
  Connection: keep-alive

  {
    "tagId": 18
  }
  ```

## 프로젝트 내 태그 수정

* Request

  ```
  PUT http://localhost:9091/projects/{project-id}/tags/{tag-id}
  Content-Type: application/json

  {
    "taskId" : "28",
    "name" : "지난활동"
  }
  ```

* Response

  ```
  HTTP/1.1 201
  Content-Type: application/json;charset=UTF-8
  Transfer-Encoding: chunked
  Date: Sun, 18 Jun 2023 06:38:25 GMT
  Keep-Alive: timeout=60
  Connection: keep-alive

  {
    "tagId": 18
  }
  ```

## 프로젝트 내 태그 수정

* Request

  ```
  PUT http://localhost:9091/projects/{project-id}/tags/{tag-id}
  Content-Type: application/json

  {
    "taskId" : null,
    "name" : "지난활동2"
  }
  ```

* Response

  ```
  HTTP/1.1 201
  Content-Type: application/json;charset=UTF-8
  Transfer-Encoding: chunked
  Date: Sun, 18 Jun 2023 06:38:25 GMT
  Keep-Alive: timeout=60
  Connection: keep-alive

  {
    "tagId": 18
  }
  ```

## 프로젝트 내 태그 삭제

* Request

  ```
  DELETE http://localhost:9091/projects/tags/{tag-id}
  ```

* Response

  ```
  HTTP/1.1 200 
  Content-Type: application/json;charset=UTF-8
  Transfer-Encoding: chunked
  Date: Sun, 18 Jun 2023 07:08:13 GMT
  Keep-Alive: timeout=60
  Connection: keep-alive

  {
    "result": "OK"
  }
  ```