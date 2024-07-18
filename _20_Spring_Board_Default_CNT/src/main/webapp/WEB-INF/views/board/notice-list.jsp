<%--
  Created by IntelliJ IDEA.
  User: bitcamp
  Date: 24. 7. 15.
  Time: 오전 9:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<html>
<head>

</head>
<body>
    <div>
        <jsp:include page="${pageContext.request.contextPath}/header.jsp"></jsp:include>

        <main>
            <div class="container mt-5 w-75">
                <h4>공지사항</h4>
            </div>
            <div class="container mt-3 mb-5 w-75 card-wrapper">
                <c:forEach items="${noticeList}" var="notice">
                    <div class="card" style="width: 18rem;">
                        <svg class="bd-placeholder-img card-img-top" width="100%" height="180" xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: Image cap" preserveAspectRatio="xMidYMid slice" focusable="false"><title>Placeholder</title><rect width="100%" height="100%" fill="#868e96"></rect></svg>
                        <div class="card-body">
                            <h5 class="card-title">${notice.title}</h5>
                            <p class="card-text">작성일:
                                <!--fmt:parseDate: LocalDateTime 타입을 Date 타입으로 변환-->
                                <fmt:parseDate value="${notice.regdate}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedRegdate" type="both"/>
                                <!--fmt:formatDate: Date타입의 날짜를 형식 지정에 맞게 표출-->
                                <fmt:formatDate value="${parsedRegdate}" pattern="yyyy-MM-dd"></fmt:formatDate>
                            </p>
                            <a href="/board/update-cnt.do?id=${notice.id}&type=notice" class="btn btn-outline-secondary btn-sm">자세히 보기</a>
                        </div>
                    </div>
                </c:forEach>
            </div>
            <div class="container mt-3 w-50">
                <form id="search-form" action="/board/notice-list.do" method="post">
                    <div class="row d-flex justify-content-center">
                        <div class="col-6">
                            <div class="row">
                                <div class="col-11">
                                    <input type="text" class="form-control w-100" name="searchKeyword" value="${searchMap.searchKeyword}">
                                </div>
                                <div class="col-1 d-flex align-items-center">
                                    <i class="bi bi-search" id="search-icon"></i>
                                    <button type="submit" id="btnSearch">검색</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>

            <c:if test="${loginMember ne null and loginMember.role eq 'ADMIN'}">
<%--        <c:if test="${loginMember != null && loginMember.role == 'ADMIN'}">    --%>
                <div class="container mt-3 mb-5 w-50 d-flex justify-content-end">
                    <button type="button" class="btn btn-outline-secondary" onclick="location.href='/board/post.do'">글 등록</button>
                </div>
            </c:if>
        </main>

        <jsp:include page="${pageContext.request.contextPath}/footer.jsp"></jsp:include>
    </div>
    <script>
        $(() => {
            $('#search-icon').on('click', (e) => {
                $('#search-form').submit();
            });
        });
    </script>
</body>
</html>
