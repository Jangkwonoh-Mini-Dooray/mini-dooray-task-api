# /comment mention

## 댓글 내 멘션 전체 조회

* Request

  ```
  GET http://localhost:9091/mentions/{comment-id}
  ```

* Response

  ```
  HTTP/1.1 200
  Content-Type: application/json;charset=UTF-8
  Transfer-Encoding: chunked
  Date: Sun, 18 Jun 2023 08:38:40 GMT
  Keep-Alive: timeout=60
  Connection: keep-alive

  [
    {
      "targetMemberName": "1"
    },
    {
      "targetMemberName": "2"
    },
    {
      "targetMemberName": "3"
    }
  ]
  ```

## 댓글 내 멘션 등록

* Request

  ```
  POST http://localhost:9091/mentions/{comment-id}

  {
    "targetMemberId" : ["1", "2", "3"]
  }
  ```

* Response

  ```
  HTTP/1.1 201
  Content-Type: application/json;charset=UTF-8
  Transfer-Encoding: chunked
  Date: Sun, 18 Jun 2023 08:43:02 GMT
  Keep-Alive: timeout=60
  Connection: keep-alive

  {
    "result": "OK"
  }
  ```

## 댓글 내 멘션 수정

* Request

  ```
  PUT http://localhost:9091/mentions/{comment-id}
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
  Date: Sun, 18 Jun 2023 08:45:26 GMT
  Keep-Alive: timeout=60
  Connection: keep-alive

  {
    "commentId": 1
  }
  ```

## 댓글 내 멘션 삭제

* Request

  ```
  DELETE http://localhost:9091/mentions/{comment-id}
  ```

* Response

  ```
  HTTP/1.1 200
  Content-Type: application/json;charset=UTF-8
  Transfer-Encoding: chunked
  Date: Sun, 18 Jun 2023 08:48:08 GMT
  Keep-Alive: timeout=60
  Connection: keep-alive

  {
    "result": "OK"
  }
  ```