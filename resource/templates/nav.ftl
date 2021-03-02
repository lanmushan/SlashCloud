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
                <a href="${basePath}" class="sidenav__menu-link sidenav__menu-link-category sidenav__menu-link--orange">首页</a>
            </li>
            <#list navList as nav>
                <#if nav.children?if_exists>
                    <li>
                        <a href="#" class="sidenav__menu-link">${nav.categoryName}</a>
                        <button class="sidenav__menu-toggle" aria-haspopup="true" aria-label="Open dropdown"><i
                                    class="ui-arrow-down"></i></button>
                        <ul class="sidenav__menu-dropdown">
                            <#list nav.children as nav1>
                            <li><a href="${basePath}/category/${nav1.categoryCode}.html" class="sidenav__menu-link">${nav1.categoryName}</a></li>
                            </#list>
                        </ul>
                    </li>
                <#else>
                    <li>
                        <a  href="${basePath}/category/${nav.categoryCode}.html" class="sidenav__menu-link sidenav__menu-link-category ">${nav.categoryName}</a>
                    </li>
                </#if>
            </#list>
        </ul>
    </nav>

    <div class="socials sidenav__socials">
        <#--<a class="social social-facebook" href="#" target="_blank" aria-label="facebook">-->
            <#--<i class="ui-facebook"></i>-->
        <#--</a>-->
        <#--<a class="social social-twitter" href="#" target="_blank" aria-label="twitter">-->
            <#--<i class="ui-twitter"></i>-->
        <#--</a>-->
        <#--<a class="social social-google-plus" href="#" target="_blank" aria-label="google">-->
            <#--<i class="ui-google"></i>-->
        <#--</a>-->
        <#--<a class="social social-youtube" href="#" target="_blank" aria-label="youtube">-->
            <#--<i class="ui-youtube"></i>-->
        <#--</a>-->
        <#--<a class="social social-instagram" href="#" target="_blank" aria-label="instagram">-->
            <#--<i class="ui-instagram"></i>-->
        <#--</a>-->
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
                </button>
                <!-- end Side menu button -->
                <!-- Mobile logo -->
                <a href="/" class="logo logo--mobile d-lg-none" style="text-align: center;color: white">
                     ${siteName}
                </a>
                <!-- Nav-wrap -->
                <nav class="flex-child nav__wrap d-none d-lg-block">
                    <ul class="nav__menu">
                        <li class="active">
                            <a href="${basePath}">首页</a>
                        </li>
                        <#list navList as nav>
                            <#if nav.children?if_exists>
                                <li class="nav__dropdown">
                                    <a href="#">${nav.categoryName}</a>
                                    <ul class="nav__dropdown-menu">
                                        <#list nav.children as nav1>
                                            <li><a href="${basePath}/category/${nav1.categoryCode}.html">${nav1.categoryName}</a></li>
                                        </#list>
                                    </ul>
                                </li>
                            <#else>
                                <li>
                                    <a href="${basePath}/category/${nav.categoryCode}.html">${nav.categoryName}</a>
                                </li>
                            </#if>

                        </#list>

                    </ul> <!-- end menu -->
                </nav> <!-- end nav-wrap -->

                <!-- Nav Right -->
                <#--<div class="nav__right nav--align-right d-lg-flex">-->

                    <#--<!-- Socials &ndash;&gt;-->
                    <#--<div class="nav__right-item socials nav__socials d-none d-lg-flex">-->
                        <#--<a class="social social-facebook social--nobase" href="#" target="_blank"-->
                           <#--aria-label="facebook">-->
                            <#--<i class="ui-facebook"></i>-->
                        <#--</a>-->
                        <#--<a class="social social-twitter social--nobase" href="#" target="_blank"-->
                           <#--aria-label="twitter">-->
                            <#--<i class="ui-twitter"></i>-->
                        <#--</a>-->
                        <#--<a class="social social-google social--nobase" href="#" target="_blank" aria-label="google">-->
                            <#--<i class="ui-google"></i>-->
                        <#--</a>-->
                        <#--<a class="social social-youtube social--nobase" href="#" target="_blank"-->
                           <#--aria-label="youtube">-->
                            <#--<i class="ui-youtube"></i>-->
                        <#--</a>-->
                        <#--<a class="social social-instagram social--nobase" href="#" target="_blank"-->
                           <#--aria-label="instagram">-->
                            <#--<i class="ui-instagram"></i>-->
                        <#--</a>-->
                    <#--</div>-->

                    <#--<!-- Search &ndash;&gt;-->
                    <#--<div class="nav__right-item nav__search">-->
                        <#--<a href="#" class="nav__search-trigger" id="nav__search-trigger">-->
                            <#--<i class="ui-search nav__search-trigger-icon"></i>-->
                        <#--</a>-->
                        <#--<div class="nav__search-box" id="nav__search-box">-->
                            <#--<form class="nav__search-form">-->
                                <#--<input type="text" placeholder="Search an article" class="nav__search-input">-->
                                <#--<button type="submit" class="search-button btn btn-lg btn-color btn-button">-->
                                    <#--<i class="ui-search nav__search-icon"></i>-->
                                <#--</button>-->
                            <#--</form>-->
                        <#--</div>-->

                    <#--</div>-->

                <#--</div>-->
                <!-- end nav right -->

            </div> <!-- end flex-parent -->
        </div> <!-- end container -->

    </div>
</header> <!-- end navigation -->
