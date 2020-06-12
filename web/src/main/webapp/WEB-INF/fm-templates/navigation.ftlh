<nav class="navbar navbar-expand-lg" id="mainNav" style="background-color: #1b1e21">
    <div class="container-fluid">
        <a class="navbar-brand " href="#page-top">
            <img src="../../img/logomindhunters.jpg" alt="logo">
        </a>
        <div class="navbar-toggler  text-warning" type="button"
             data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive"
             aria-expanded="false" aria-label="Toggle navigation" style="border-color:#fed136">
            <a> Menu
                <svg class="bi bi-justify" width="1.5em" height="1.4em" viewBox="0 0 16 16" fill="currentColor"
                     xmlns="http://www.w3.org/2000/svg">
                    <path fill-rule="evenodd"
                          d="M2 12.5a.5.5 0 01.5-.5h11a.5.5 0 010 1h-11a.5.5 0 01-.5-.5zm0-3a.5.5 0 01.5-.5h11a.5.5 0
                          010 1h-11a.5.5 0 01-.5-.5zm0-3a.5.5 0 01.5-.5h11a.5.5 0 010 1h-11a.5.5 0 01-.5-.5zm0-3a.5.5
                          0 01.5-.5h11a.5.5 0 010 1h-11a.5.5 0 01-.5-.5z" clip-rule="evenodd"/>
                </svg>
            </a>
        </div>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav text-uppercase ml-auto">
                <li class="nav-item"><a class="nav-link js-scroll-trigger text-warning" href="/">Home</a></li>
                <li class="nav-item"><a class="nav-link js-scroll-trigger text-warning" href="/list">Recipes</a>
                </li>
                <li class="nav-item"><a class="nav-link js-scroll-trigger text-warning" href="/search">Search</a>
                </li>
                <li class="nav-item"><a class="nav-link js-scroll-trigger text-warning" href="#settingPage">Setting</a>
                </li>




                <div class="btn-group">
                    <button type="button" class="btn dropdown-toggle btn-outline-warning btn-background"
                            data-toggle="dropdown" aria-haspopup="true"
                            aria-expanded="false">

                        <#if !role = "USER" || !role = "ADMIN" || !role = "SUPER_ADMIN">
                        Login
                        <#else>
                        Logout

                    </button>
                    <div class="dropdown-menu dropdown-menu-right">
                        <form class="px-4 py-3">
                            <div class="form-group">

                            </div>
                            <div class="form-group">

                            </div>
                            <div class="form-group">
                                <div class="form-check">


                                </div>
                            </div>

                            <button type="submit" class="btn btn-outline-warning" id="submitLogin">Sign in</button>

                        </form>

                </div>




<#--

                    <#if role??>
                    <ul class="text-uppercase">
                        <#if role = "USER" || role = "ADMIN" || role = "SUPER_ADMIN">
                            <li><a>${name}</a></li>
                            <li><a href="/logout" class="active">${logOut}</a></li>
                        <#else>
                            <li><a href="/login" class="active">${logIn}</a></li>
                        </#if>
                        </#if>
                    </ul>
-->








            </ul>
        </div>
    </div>
</nav>