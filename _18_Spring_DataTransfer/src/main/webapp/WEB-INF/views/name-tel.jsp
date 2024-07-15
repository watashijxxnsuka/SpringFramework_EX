<%--
  Created by IntelliJ IDEA.
  User: shoon
  Date: 2024-07-15
  Time: AM 10:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>name-tel</title>
    <style>
        div > p {
            display: inline-block;
            margin-left: 10px;
        }
    </style>
</head>
<body>
    <form action="/name-tel.do" method="post">
        <input type="text" name="name">
        <input type="text" name="tel">
        <input type="submit" value="전송">
    </form>
<%--    <div>--%>
        <!-- java 에서 전송한 데이터를 el 표기법을 이용해서 표출한다.
            자바에서도 데이터를 전송할 때 키: 벨류 매핑된 데이터로 오기 때문에
            키를 이용해서 벨류값을 사용한다.
        -->
<%--        <p>${nm}님 안녕하세요.</p><br>--%>
<%--        <p>입력된 전화번호는 ${tl}입니다.</p>--%>

        <!-- 자바에서 전송한 데이터가 클래스타입일 경우, '전송한 키.클래스의 멤버변수명'으로
            데이터를 꺼내서 사용할 수 있다.
        -->
<%--        <p>${nameTelDto.name}님 안녕하세요.</p><br>--%>
<%--        <p>입력된 전화번호는 ${nameTelDto.tel}입니다.</p>--%>

<%--    </div>--%>

    <!-- jstl 의 forEach 구문으로 List 형태로 자바에서 넘어온 데이터를 하나씩 꺼내서 표출한다. -->
    <c:forEach items="${nameTelList}" var="aaa">
        <div>
            <p>${aaa.name}</p>
            <p>${aaa.tel}</p>
        </div>
    </c:forEach>

</body>
</html>
