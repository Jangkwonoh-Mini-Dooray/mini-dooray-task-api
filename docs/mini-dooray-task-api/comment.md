# /comment

## 업무 내 댓글 전체 조회

* Request

  ```
  GET http://localhost:9091/task/{task-id}/comments
  ```

* Response

  ```
  HTTP/1.1 200 
  Content-Type: application/json;charset=UTF-8
  Transfer-Encoding: chunked
  Date: Sun, 18 Jun 2023 07:50:45 GMT
  Keep-Alive: timeout=60
  Connection: keep-alive

  [
    {
      "commentId": 1,
      "commentWriterMemberId": "2",
      "comment": "파이팅"
    },
    {
      "commentId": 2,
      "commentWriterMemberId": "1",
      "comment": "안녕하세요."
    }
  ]
  ```

## 댓글 등록

* Request

  ```
  POST http://localhost:9091/task/{task-id}/comments
  Content-Type: application/json

  {
    "commentWriterMemberId" : 2,
    "commentContent" : "파이팅"
  }
  ```a

* Response

  ```
  HTTP/1.1 201
  Content-Type: application/json;charset=UTF-8
  Transfer-Encoding: chunked
  Date: Sun, 18 Jun 2023 07:30:11 GMT
  Keep-Alive: timeout=60
  Connection: keep-alive

  {
    "commentId": 1
  }
  ```

## 댓글 수정

* Request

  ```
  PUT http://localhost:9091/task/comments/{comment-id}
  Content-Type: application/json

  {
    "commentWriterMemberId" : 1,
    "commentContent" : "수정합니다"
  }
  ```

* Response

  ```
  HTTP/1.1 201 
  Content-Type: application/json;charset=UTF-8
  Transfer-Encoding: chunked
  Date: Sun, 18 Jun 2023 07:59:43 GMT
  Keep-Alive: timeout=60
  Connection: keep-alive

  {
    "commentId": 2
  }
  ```

## 댓글 삭제

* Request

  ```
  DELETE http://localhost:9091/task/comments/{comment-id}
  ```

* Response

  ```
  HTTP/1.1 200
  Content-Type: application/json;charset=UTF-8
  Transfer-Encoding: chunked
  Date: Sun, 18 Jun 2023 08:04:21 GMT
  Keep-Alive: timeout=60
  Connection: keep-alive

  {
    "result": "OK"
  }
  ```