# mini-dooray-task-api
- Project, Task, Comment, Tag, Milestone 정보를 관리하는 Task-API

---
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
  
  ---
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
  
  ---
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
  
  ---
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
  
  ---
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
  
  ---
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
  
  ---
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
  
  ---
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
  
  ---
  # /task

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
  ```
