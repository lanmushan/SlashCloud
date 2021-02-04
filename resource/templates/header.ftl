<header class="sidenav" id="sidenav">

    <!-- close -->
    <div class="sidenav__close">
        <button class="sidenav__close-button" id="sidenav__close-button" aria-label="close sidenav">
            <i class="ui-close sidenav__close-icon"></i>
        </button>
    </div>

    <!-- 移动端右侧导航栏 -->
    <nav class="sidenav__menu-container">
        <ul class="sidenav__menu" role="menubar">
            <!-- Categories -->

            <li>
                <a href="#" class="sidenav__menu-link sidenav__menu-link-category sidenav__menu-link--orange">首页</a>
            </li>
            <#list navList as nav>
                <#if nav.children?if_exists>
                    <li>
                        <a href="#" class="sidenav__menu-link">${nav.categoryName}</a>
                        <button class="sidenav__menu-toggle" aria-haspopup="true" aria-label="Open dropdown"><i
                                    class="ui-arrow-down"></i></button>
                        <ul class="sidenav__menu-dropdown">
                            <li><a href="#" class="sidenav__menu-link">Gallery Post</a></li>
                            <li><a href="single-post.html" class="sidenav__menu-link">Video Post</a></li>
                            <li><a href="single-post.html" class="sidenav__menu-link">Audio Post</a></li>
                            <li><a href="#" class="sidenav__menu-link">Quote Post</a></li>
                        </ul>
                    </li>


                <#else>
                    <li>
                        <a href="#" class="sidenav__menu-link sidenav__menu-link-category ">${nav.categoryName}</a>
                    </li>
                </#if>


            </#list>
        </ul>
    </nav>

    <div class="socials sidenav__socials">
        <a class="social social-facebook" href="#" target="_blank" aria-label="facebook">
            <i class="ui-facebook"></i>
        </a>
        <a class="social social-twitter" href="#" target="_blank" aria-label="twitter">
            <i class="ui-twitter"></i>
        </a>
        <a class="social social-google-plus" href="#" target="_blank" aria-label="google">
            <i class="ui-google"></i>
        </a>
        <a class="social social-youtube" href="#" target="_blank" aria-label="youtube">
            <i class="ui-youtube"></i>
        </a>
        <a class="social social-instagram" href="#" target="_blank" aria-label="instagram">
            <i class="ui-instagram"></i>
        </a>
    </div>
</header> <!-- end sidenav -->
<!-- 顶部导航栏 -->
<header class="nav">

    <div class="nav__holder nav--sticky">
        <div class="container relative">
            <div class="flex-parent">
                <!-- Side Menu Button -->
                <button class="nav-icon-toggle" id="nav-icon-toggle" aria-label="Open side menu">
              <span class="nav-icon-toggle__box">
                <span class="nav-icon-toggle__inner"></span>
              </span>
                </button> <!-- end Side menu button -->
                <!-- Mobile logo -->
                <a href="index.ftl" class="logo logo--mobile d-lg-none">
                    <img class="logo__img" src="img/logo_mobile.png"
                         srcset="img/logo_mobile.png 1x, img/logo_mobile@2x.png 2x" alt="logo">
                </a>

                <!-- Nav-wrap -->
                <nav class="flex-child nav__wrap d-none d-lg-block">
                    <ul class="nav__menu">

                        <li class="active">
                            <a href="index.html">xxx</a>
                        </li>

                        <li class="nav__dropdown">
                            <a href="#">彩妆美容</a>
                            <ul class="nav__dropdown-menu">
                                <li><a href="#">Gallery Post</a></li>
                                <li><a href="single-post.html">Video Post</a></li>
                                <li><a href="single-post.html">Audio Post</a></li>
                                <li><a href="#">Quote Post</a></li>
                            </ul>
                        </li>

                        <li class="nav__dropdown">
                            <a href="#">时尚穿搭</a>
                            <ul class="nav__dropdown-menu">
                                <li><a href="#">About</a></li>
                                <li><a href="#">Contact</a></li>
                                <li><a href="#">Search Results</a></li>
                                <li><a href="#">Categories</a></li>
                                <li><a href="#">404</a></li>
                            </ul>
                        </li>

                        <li class="nav__dropdown">
                            <a href="#">美体塑身</a>
                            <ul class="nav__dropdown-menu">
                                <li><a href="#">Lazy Load</a></li>
                                <li><a href="#">Shortcodes</a></li>
                            </ul>
                        </li>

                        <li>
                            <a href="#">沟通话术</a>
                        </li>
                        <li>
                            <a href="#">精致生活</a>
                        </li>
                        <li>
                            <a href="#">情感交流</a>
                        </li>
                    </ul> <!-- end menu -->
                </nav> <!-- end nav-wrap -->

                <!-- Nav Right -->
                <div class="nav__right nav--align-right d-lg-flex">

                    <!-- Socials -->
                    <div class="nav__right-item socials nav__socials d-none d-lg-flex">
                        <a class="social social-facebook social--nobase" href="#" target="_blank"
                           aria-label="facebook">
                            <i class="ui-facebook"></i>
                        </a>
                        <a class="social social-twitter social--nobase" href="#" target="_blank"
                           aria-label="twitter">
                            <i class="ui-twitter"></i>
                        </a>
                        <a class="social social-google social--nobase" href="#" target="_blank" aria-label="google">
                            <i class="ui-google"></i>
                        </a>
                        <a class="social social-youtube social--nobase" href="#" target="_blank"
                           aria-label="youtube">
                            <i class="ui-youtube"></i>
                        </a>
                        <a class="social social-instagram social--nobase" href="#" target="_blank"
                           aria-label="instagram">
                            <i class="ui-instagram"></i>
                        </a>
                    </div>

                    <!-- Search -->
                    <div class="nav__right-item nav__search">
                        <a href="#" class="nav__search-trigger" id="nav__search-trigger">
                            <i class="ui-search nav__search-trigger-icon"></i>
                        </a>
                        <div class="nav__search-box" id="nav__search-box">
                            <form class="nav__search-form">
                                <input type="text" placeholder="Search an article" class="nav__search-input">
                                <button type="submit" class="search-button btn btn-lg btn-color btn-button">
                                    <i class="ui-search nav__search-icon"></i>
                                </button>
                            </form>
                        </div>

                    </div>

                </div> <!-- end nav right -->

            </div> <!-- end flex-parent -->
        </div> <!-- end container -->

    </div>
</header> <!-- end navigation -->
