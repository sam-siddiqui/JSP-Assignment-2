<!DOCTYPE html>
<html data-bs-theme="light" lang="en">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>JSPLibrary</title>
    <link rel="stylesheet" href="resources/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="resources/css/style.css">
</head>

<body class="d-flex flex-column flex-grow-1 align-items-stretch" style="background: var(--bs-secondary-bg);">
<jsp:useBean id="totalBooksAvailable" scope="request" type="java.util.ArrayList<com.samsidd.jspassignment2.models.BookExtended>"/>
<jsp:useBean id="totalTopics" scope="request" type="java.util.ArrayList<com.samsidd.jspassignment2.models.Topic>"/>
<jsp:useBean id="selectedTopicID" scope="request" type="java.lang.String"/>

<div class="col offset-md-0 d-flex flex-column justify-content-between align-items-stretch align-self-stretch align-self-sm-stretch align-self-md-stretch align-self-lg-stretch align-self-xl-stretch align-self-xxl-stretch" style="padding-right: 10px;padding-left: 10px;">
    <div class="row d-flex" style="margin-bottom: 11px;margin-top: -5px;">
        <div class="col" style="padding-left: 0px;padding-right: 0px;">
            <nav class="navbar navbar-expand-md bg-primary-subtle d-flex py-3" style="padding-left: 0px;">
                <div class="container"><a class="navbar-brand d-flex align-items-center" href="#"><span class="bs-icon-sm bs-icon-rounded bs-icon-primary d-flex justify-content-center align-items-center me-2 bs-icon"><svg xmlns="http://www.w3.org/2000/svg" width="1em" height="1em" fill="currentColor" viewBox="0 0 16 16" class="bi bi-bezier">
                                    <path fill-rule="evenodd" d="M0 10.5A1.5 1.5 0 0 1 1.5 9h1A1.5 1.5 0 0 1 4 10.5v1A1.5 1.5 0 0 1 2.5 13h-1A1.5 1.5 0 0 1 0 11.5zm1.5-.5a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm10.5.5A1.5 1.5 0 0 1 13.5 9h1a1.5 1.5 0 0 1 1.5 1.5v1a1.5 1.5 0 0 1-1.5 1.5h-1a1.5 1.5 0 0 1-1.5-1.5zm1.5-.5a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zM6 4.5A1.5 1.5 0 0 1 7.5 3h1A1.5 1.5 0 0 1 10 4.5v1A1.5 1.5 0 0 1 8.5 7h-1A1.5 1.5 0 0 1 6 5.5zM7.5 4a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5z"></path>
                                    <path d="M6 4.5H1.866a1 1 0 1 0 0 1h2.668A6.517 6.517 0 0 0 1.814 9H2.5c.123 0 .244.015.358.043a5.517 5.517 0 0 1 3.185-3.185A1.503 1.503 0 0 1 6 5.5zm3.957 1.358A1.5 1.5 0 0 0 10 5.5v-1h4.134a1 1 0 1 1 0 1h-2.668a6.517 6.517 0 0 1 2.72 3.5H13.5c-.123 0-.243.015-.358.043a5.517 5.517 0 0 0-3.185-3.185z"></path>
                                </svg></span><span>JSPLibrary</span></a><button data-bs-toggle="collapse" class="navbar-toggler" data-bs-target="#navcol-2"><span class="visually-hidden">Toggle navigation</span><span class="navbar-toggler-icon"></span></button>
                    <div class="collapse navbar-collapse" id="navcol-2">
                        <ul class="navbar-nav ms-auto">
                            <li class="nav-item"></li>
                            <li class="nav-item"><a class="nav-link" href="#" data-bs-target="#modal-2" data-bs-toggle="modal">Request A Book</a></li>
                            <li class="nav-item"><a class="nav-link" href="#">Help</a></li>
                        </ul><a class="btn btn-primary ms-md-2" role="button" href="#" data-bs-target="#modal-1" data-bs-toggle="modal">Login</a>
                    </div>
                </div>
            </nav>
        </div>
    </div>
    <div class="row text-center d-flex flex-row flex-grow-1 flex-fill align-self-stretch justify-content-lg-center align-items-lg-center align-items-xl-start">
        <div class="col d-flex flex-column flex-grow-1 flex-shrink-0 flex-fill">
            <div class="row d-xl-flex justify-content-xl-center">
                <div class="col-md-6 d-flex d-md-flex flex-column" style="padding-right: 2px;">
                    <div class="row">
                        <div class="col">
                            <div class="row">
                                <div class="col d-xl-flex justify-content-xl-center"><span class="bs-icon-xl bs-icon-rounded bs-icon-primary d-flex justify-content-center align-items-center me-2 bs-icon"><svg xmlns="http://www.w3.org/2000/svg" width="1em" height="1em" fill="currentColor" viewBox="0 0 16 16" class="bi bi-bezier">
                                                <path fill-rule="evenodd" d="M0 10.5A1.5 1.5 0 0 1 1.5 9h1A1.5 1.5 0 0 1 4 10.5v1A1.5 1.5 0 0 1 2.5 13h-1A1.5 1.5 0 0 1 0 11.5zm1.5-.5a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm10.5.5A1.5 1.5 0 0 1 13.5 9h1a1.5 1.5 0 0 1 1.5 1.5v1a1.5 1.5 0 0 1-1.5 1.5h-1a1.5 1.5 0 0 1-1.5-1.5zm1.5-.5a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zM6 4.5A1.5 1.5 0 0 1 7.5 3h1A1.5 1.5 0 0 1 10 4.5v1A1.5 1.5 0 0 1 8.5 7h-1A1.5 1.5 0 0 1 6 5.5zM7.5 4a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5z"></path>
                                                <path d="M6 4.5H1.866a1 1 0 1 0 0 1h2.668A6.517 6.517 0 0 0 1.814 9H2.5c.123 0 .244.015.358.043a5.517 5.517 0 0 1 3.185-3.185A1.503 1.503 0 0 1 6 5.5zm3.957 1.358A1.5 1.5 0 0 0 10 5.5v-1h4.134a1 1 0 1 1 0 1h-2.668a6.517 6.517 0 0 1 2.72 3.5H13.5c-.123 0-.243.015-.358.043a5.517 5.517 0 0 0-3.185-3.185z"></path>
                                            </svg></span></div>
                            </div>
                            <div class="row">
                                <div class="col">
                                    <h1 class="text-center d-md-flex justify-content-md-center align-items-md-center"><br>
                                        <c:if test="${totalBooksAvailable.size() < 1}" >No </c:if>Available Books
                                    </h1>
                                    <c:choose>
                                        <c:when test="${totalBooksAvailable.size() < 1}" >
                                            <p>Come back later to find more books!</p>
                                        </c:when>
                                        <c:otherwise>
                                            <p>Login to reserve the books!</p>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row d-flex flex-column" style="padding-top: 16px;">
                        <div class="col-md-12" style="max-height: 45vh; overflow-y: scroll;">
                            <div class="row" style="margin-bottom: 5px;">
                                <div class="col">
                                    <div id="searchBox" class="d-flex flex-row flex-grow-1 flex-fill justify-content-center">
                                        <select id="topicSelector" class="flex-grow-1" style="margin-right: 10px;" name="topic">
                                            <option value="0" selected="">Any Topic</option>
                                            <c:forEach items="${totalTopics}" var="topic">
                                                <c:choose>
                                                    <c:when test="${topic.topic_id != selectedTopicID}">
                                                        <option value="${topic.topic_id}">${topic.topic_name}</option>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <option value="${topic.topic_id}" selected>${topic.topic_name}</option>
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:forEach>
                                        </select>
                                        <button class="btn btn-primary btn-sm" type="button" onclick="filterResults()">Filter</button></div>
                                </div>
                            </div>
                            <div class="table-responsive text-center d-xxl-flex align-self-center">
                                <table class="table table-striped table-sm table-bordered tableFixHead">
                                    <thead class="tableFixHead">
                                    <tr>
                                        <th class="tableFixHead">Topic</th>
                                        <th class="tableFixHead">Book Name</th>
                                        <th class="tableFixHead">Author</th>
                                        <th class="tableFixHead"></th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${totalBooksAvailable}" var="currentBook">
                                        <tr>
                                            <td><c:out value="${currentBook.topic.topic_name}"/></td>
                                            <td><c:out value="${currentBook.book_name}"/></td>
                                            <td><c:out value="${currentBook.author.author_name}"/></td>
                                            <td><button class="btn btn-secondary btn-sm" type="button" id="reserve-${currentBook.book_id}" onclick="location.href = '${pageContext.request.contextPath}/login'">Reserve!</button></td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="row d-md-flex" style="bottom: 0;position: absolute;width: 100%;">
        <div class="col">
            <footer class="text-center py-4">
                <div class="container">
                    <div class="row row-cols-1 row-cols-lg-3 d-lg-flex justify-content-lg-center">
                        <div class="col">
                            <p class="text-muted my-2">Copyright © 2024 JSPLibrary</p>
                        </div>
                    </div>
                </div>
            </footer>
        </div>
    </div>
</div>
<div class="modal fade" role="dialog" tabindex="-1" id="modal-1">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">Login</h4><button class="btn-close" type="button" aria-label="Close" data-bs-dismiss="modal"></button>
            </div>
            <div class="modal-body d-flex flex-row flex-grow-1 flex-fill">
                <div class="row d-flex flex-grow-1 justify-content-center align-self-stretch">
                    <div class="col-md-6 col-xl-4" style="padding-right: 0px;width: 75%;min-width: 50%;">
                        <div class="card mb-5">
                            <div class="card-body d-flex flex-column align-items-center">
                                <div class="bs-icon-xl bs-icon-circle bs-icon-primary bs-icon my-4"><svg xmlns="http://www.w3.org/2000/svg" width="1em" height="1em" fill="currentColor" viewBox="0 0 16 16" class="bi bi-person">
                                    <path d="M8 8a3 3 0 1 0 0-6 3 3 0 0 0 0 6m2-3a2 2 0 1 1-4 0 2 2 0 0 1 4 0m4 8c0 1-1 1-1 1H3s-1 0-1-1 1-4 6-4 6 3 6 4m-1-.004c-.001-.246-.154-.986-.832-1.664C11.516 10.68 10.289 10 8 10c-2.29 0-3.516.68-4.168 1.332-.678.678-.83 1.418-.832 1.664z"></path>
                                </svg></div>
                                <form class="text-center" method="post" action="${pageContext.request.contextPath}/login">
                                    <div class="mb-3"><input class="form-control" type="text" name="username" placeholder="Username"></div>
                                    <div class="mb-3"><input class="form-control" type="password" name="password" placeholder="Password"></div>
                                    <div class="mb-3"><button class="btn btn-primary d-block w-100" type="submit">Login</button></div>
                                    <a class="d-flex justify-content-xxl-center" href="#">Forgot your Password?</a>
                                    <a class="d-flex justify-content-xxl-center" href="${pageContext.request.contextPath}/register">No Account? Register!</a>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" role="dialog" tabindex="-1" id="modal-2">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">Request a Book!</h4><button class="btn-close" type="button" aria-label="Close" data-bs-dismiss="modal"></button>
            </div>
            <div class="modal-body">
                <form method="post" action="${pageContext.request.contextPath}/request">
                    <div class="d-flex mb-3"><input class="form-control" type="text" id="name-2" name="book_name" placeholder="Book Name" style="margin-right: 5px;" minlength="2" required=""></div>
                    <div class="mb-3"><input class="form-control" type="text" id="email-2" name="author_name" placeholder="Author Name" required=""></div>
                    <div class="mb-3"><input class="form-control" type="text" id="name-1" name="publisher_name" placeholder="Publisher" required=""></div>
                    <div class="mb-3"><input class="form-control" type="text" id="name-3" name="isbn" placeholder="IBSN" required=""></div>
                    <div><button class="btn btn-primary d-block w-100" type="submit">Submit</button></div>
                </form>
            </div>
        </div>
    </div>
</div>
<script>
    function filterResults() {
        console.log("Here");
        let topicSelector = document.getElementById("topicSelector");
        const form = document.createElement("form");
        form.setAttribute("action", "${pageContext.request.contextPath}/");
        form.setAttribute("method", "post");
        form.setAttribute("style", "display: none");

        const topicID = document.createElement("input");
        topicID.setAttribute("type", "text");
        topicID.setAttribute("name", "selectedTopicID");
        topicID.setAttribute("value", topicSelector.value);

        const s = document.createElement("input");
        s.setAttribute("type", "submit");
        s.setAttribute("value", "Submit");

        form.appendChild(topicID);
        form.appendChild(s);

        document.querySelector(`#searchBox`).append(form);
        form.submit();
    }
</script>
<script src="resources/bootstrap/js/bootstrap.min.js"></script>
</body>

</html>