<nav class="navbar navbar-expand-lg" id="mainNav" style="background-color: #1b1e21">
    <div class="container-fluid">
        <a class="navbar-brand ">
            <img src="../../img/logomindhunters.jpg" alt="logo">
        </a>

        <#if role??>
            <#if role = "ADMIN" || role = "SUPER_ADMIN">

                <div class="btn-group">
                    <button type="button" class="btn dropdown-toggle btn-outline-warning btn-background"
                            data-toggle="dropdown" aria-haspopup="true"
                            aria-expanded="false">

                        Admin panel
                    </button>
                    <div class="dropdown-menu dropdown-menu-right" style="text-align: center">


                        <li><a href="/admin-users">User Management</a></li>
                        <div class="dropdown-divider"></div>

                        <li><a href="/#userMANAGEMENT">Statistics</a></li>
                        <div class="dropdown-divider"></div>

                        <li><a href="/#userMANAGEMENT">Drink Management</a></li>
                        <div class="dropdown-divider"></div>

                        <li><a href="/#userMANAGEMENT">Back up database load</a></li>


                    </div>
                </div>

            </#if>
        </#if>


        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav text-uppercase ml-auto">


                <li class="nav-item"><a class="nav-link js-scroll-trigger text-warning">
                        << Welcome ${name ! "Stranger"} >></a>
                </li>
                <li class="nav-item"><a class="nav-link js-scroll-trigger text-warning" href="/">Home</a></li>
                <li class="nav-item"><a class="nav-link js-scroll-trigger text-warning" href="/list">Recipes</a>
                </li>
                <li class="nav-item"><a class="nav-link js-scroll-trigger text-warning" href="/search">Search</a>
                </li>

                <#if role??>
                    <#if role = "USER" || role = "ADMIN" || role = "SUPER_ADMIN">
                        <li class="nav-item"><a class="nav-link js-scroll-trigger text-warning" href="/favourites">Favourites</a>
                        <li class="nav-item"><a class="nav-link js-scroll-trigger text-warning" href="/drink-management">Drink management</a>
                        </li>
                    </#if>
                </#if>


                <div>
                    <li class="nav-item">
                    </li>
                </div>


                <div class="btn btn-outline-warning btn-background">

                    <#if role??>
                        <#if role = "USER" || role = "ADMIN" || role = "SUPER_ADMIN">
                            <li class="nav-item"><a class="nav-link js-scroll-trigger text-warning"
                                                    href="/logout">Logout</a>
                        <#else>
                            <li class="nav-item"><a class="nav-link js-scroll-trigger text-warning"
                                                    href="/login">Login</a>
                            </li>
                        </#if>

                        </li>
                    </#if>
                </div>

            </ul>
        </div>
    </div>
</nav>