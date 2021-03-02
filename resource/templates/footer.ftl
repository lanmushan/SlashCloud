<footer class="footer footer--dark">
    <div class="container">
        <div class="footer__widgets">
            <div class="row">

                <div class="col-lg-6 col-md-6">
                    <div class="widget">
                        <a href="${basePath}">
                            <img src="${logo}"
                                 class="logo__img"
                                 alt="">
                        </a>
                        <p class="mt-20">
                            ${siteDescription}
                        </p>
                    </div>
                </div>

                <#--<div class="col-lg-6 col-md-6">-->
                    <#--<div class="widget widget__newsletter">-->
                        <#--<h4 class="widget-title">关于</h4>-->
                    <#--</div>-->
                <#--</div>-->

                <div class="col-lg-4 col-md-6">
                    <div class="widget widget_nav_menu">
                        <h4 class="widget-title">友情链接</h4>
                        <ul>
                            <#list friendChain as row>
                            <li><a href="${row.url}" target="_blank">${row.name}</a></li>
                            </#list>

                        </ul>
                    </div>
                </div>

            </div>
        </div>
    </div> <!-- end container -->
    <div class="footer__bottom">
        <div class="container">
            <div class="row" >
                <span  class="text-center" style="display: inline-block;margin: 0 auto">  ${siteCopyRight}</span>
            </div>

        </div>
    </div> <!-- end bottom footer -->
</footer> <!-- end footer -->

<div id="back-to-top">
    <a href="#top" aria-label="Go to top"><i class="ui-arrow-up"></i></a>
</div>
