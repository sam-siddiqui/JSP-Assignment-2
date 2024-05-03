<!DOCTYPE html>
<html data-bs-theme="light" lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>jsp-assignment-2</title>
    <link rel="stylesheet" href="resources/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="resources/css/style.css">
</head>

<body class="d-flex flex-column flex-grow-1">
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
                            <li class="nav-item"><a class="nav-link" href="#">Help</a></li>
                        </ul>
                    </div>
                </div>
            </nav>
        </div>
    </div>
    <div class="row text-center d-flex flex-row flex-grow-1 flex-fill align-self-stretch justify-content-lg-center">
        <div class="col d-flex flex-column flex-grow-1 flex-shrink-0 flex-fill">
            <section class="position-relative py-4 py-xl-5">
                <div class="container position-relative">
                    <div class="row d-flex justify-content-center">
                        <div class="col-md-8 col-lg-6 col-xl-5 col-xxl-4">
                            <div class="card mb-5">
                                <div class="card-body p-sm-5">
                                    <div class="bs-icon-xl bs-icon-circle bs-icon-primary d-inline-flex bs-icon my-4"><svg xmlns="http://www.w3.org/2000/svg" width="1em" height="1em" fill="currentColor" viewBox="0 0 16 16" class="bi bi-person">
                                        <path d="M8 8a3 3 0 1 0 0-6 3 3 0 0 0 0 6m2-3a2 2 0 1 1-4 0 2 2 0 0 1 4 0m4 8c0 1-1 1-1 1H3s-1 0-1-1 1-4 6-4 6 3 6 4m-1-.004c-.001-.246-.154-.986-.832-1.664C11.516 10.68 10.289 10 8 10c-2.29 0-3.516.68-4.168 1.332-.678.678-.83 1.418-.832 1.664z"></path>
                                    </svg></div>
                                    <h2 class="text-center mb-4">Login</h2>
                                    <form method="post" action="${pageContext.request.contextPath}/login">
                                        <div class="mb-3"><input class="form-control" type="text" id="name-1" name="username" placeholder="User Name" required=""></div>
                                        <div class="mb-3"><input class="form-control" type="text" id="name-3" name="password" placeholder="Password" required=""></div>
                                        <div><button class="btn btn-primary d-block w-100" type="submit">Submit</button></div>
                                    </form>
                                    <a class="d-flex justify-content-xxl-center" href="${pageContext.request.contextPath}/register">No Account? Register!</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
        </div>
    </div>
    <div class="row d-md-flex" style="bottom: 0; position: absolute; width: 100%">
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
<script src="resources/bootstrap/js/bootstrap.min.js"></script>
</body>

</html>