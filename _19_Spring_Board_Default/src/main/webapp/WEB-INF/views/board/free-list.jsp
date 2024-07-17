<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<%--<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>--%>
<%@taglib prefix="javatime" uri="http://sargue.net/jsptags/time" %>
<html>
<head>

</head>
<body>
    <div class="wrapper">
        <jsp:include page="${pageContext.request.contextPath}/header.jsp"></jsp:include>

        <main>
            <div class="container mt-5 w-50">
                <h4>자유게시판</h4>
            </div>
            <div class="container mt-3 w-50">
                <form id="search-form" action="" method="post">
                    <div class="row">
                        <div class="col-3">
                            <select class="form-select" name="searchCondition">
                                <option value="all" selected>전체</option>
                                <option value="title">제목</option>
                                <option value="content">내용</option>
                                <option value="writer">작성자</option>
                            </select>
                        </div>
                        <div class="col-9">
                            <div class="row">
                                <div class="col-11">
                                    <input type="text" class="form-control w-100" name="searchKeyword">
                                </div>
                                <div class="col-1 d-flex align-items-center">
                                    <i class="bi bi-search" id="searchIcon"></i>
                                    <button type="submit" id="btnSearch">검색</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="container mt-3 mb-5 w-50">
                <table class="table table-hover text-center">
                    <thead>
                        <tr>
                            <th>번호</th>
                            <th>제목</th>
                            <th>작성자</th>
                            <th>등록일</th>
                            <th>조회수</th>
                        </tr>
                    </thead>
                    <tbody class="table-group-divider">
                        <c:forEach items="${freeBoardList}" var="freeBoard">
                            <tr class="board-tr" onclick="location.href='/board/free-detail.do?id=${freeBoard.id}'">
                                <td>${freeBoard.id}</td>
                                <td>${freeBoard.title}</td>
                                <td>${freeBoard.nickname}</td>
                                <td>
                                    <javatime:format value="${freeBoard.regdate}" pattern="yyyy-MM-dd" />
                                </td>
                                <td>${freeBoard.cnt}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <nav aria-label="Page navigation">
                <ul class="pagination justify-content-center">
                    <li class="page-item">
                        <a class="page-link link-secondary" aria-label="Previous">
                            &laquo;
                        </a>
                    </li>

                    <li class="page-item">
                        <a class="page-link link-secondary">1</a>
                    </li>
                    <li class="page-item">
                        <a class="page-link link-secondary">2</a>
                    </li>
                    <li class="page-item">
                        <a class="page-link link-secondary">3</a>
                    </li>

                    <li class="page-item">
                        <a class="page-link link-secondary" aria-label="Next">
                            &raquo;
                        </a>
                    </li>
                </ul>
            </nav>

            <c:if test="${loginMember ne null}">
            <div class="container mt-3 mb-5 w-50 d-flex justify-content-end">
                <button type="button" class="btn btn-outline-secondary" onclick="location.href='/board/post.do'">글 등록</button>
            </div>
            </c:if>
        </main>

        <jsp:include page="${pageContext.request.contextPath}/footer.jsp"></jsp:include>
    </div>
</body>
</html>
