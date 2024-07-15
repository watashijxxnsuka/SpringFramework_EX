<%--
  Created by IntelliJ IDEA.
  User: shoon
  Date: 2024-07-12
  Time: PM 5:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

</head>
<body>
<div>
    <!-- pageContext.request.contextPate: WebServer 의 root path 인
    webapp 폴더(http://localhost:8090)
    -->
    <jsp:include page="${pageContext.request.contextPath}/header.jsp"></jsp:include>

    <main>
        <div class="container mt-5 w-25">
            <h4>로그인</h4>
        </div>
        <div class="container mt-4 mb-5 w-25">
            <form id="loginForm" action="/user/login.do" method="post">
                <div class="form-group">
                    <label for="username">아이디</label>
                    <input type="text" class="form-control" id="username" name="username" required>
                </div>
                <div class="form-group mt-4">
                    <label for="password">비밀번호</label>
                    <input type="password" class="form-control" id="password" name="password" required>
                </div>
                <div class="form-group mt-4">
                    <button type="submit" class="btn btn-outline-secondary w-100">로그인</button>
                </div>
            </form>
        </div>
    </main>

    <jsp:include page="${pageContext.request.contextPath}/footer.jsp"></jsp:include>
</div>
</body>
</html>
