<!DOCTYPE html>
<html>
<head>
  <title>${idArticle[0].contentTitle}</title>

  <meta charset="utf-8">
  <!--[if IE]><meta http-equiv='X-UA-Compatible' content='IE=edge,chrome=1'><![endif]-->
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
  <meta name="keywords" content="${idArticle[0].contentKeyword}">
  <meta name="description" content="${idArticle[0].contentDescription}">
  <link rel="shortcut icon" type="images/x-icon"  href="${favicon}">
  <!-- Google Fonts -->
  <!--<link href='https://fonts.googleapis.com/css?family=Open+Sans:400,600,700' rel='stylesheet'>-->
  <!-- Css -->
  <link rel="stylesheet" href="/css/bootstrap.min.css" />
  <link rel="stylesheet" href="/css/font-icons.css" />
  <link rel="stylesheet" href="/css/style.css" />
  <link rel="stylesheet" href="/css/layout.css">
  <link rel="stylesheet" href="/css/pinkstyle.css">
    <link rel="stylesheet" href="/css/quill.snow.css">
<!-- 主要的库文件，js -->
<script src="//cdn.quilljs.com/1.3.6/quill.js"></script>
<script src="//cdn.quilljs.com/1.3.6/quill.min.js"></script>

<!-- css文件，有两个主题，snow和bubble -->
<link href="//cdn.quilljs.com/1.3.6/quill.snow.css" rel="stylesheet">
<link href="//cdn.quilljs.com/1.3.6/quill.bubble.css" rel="stylesheet">
  <!-- Lazyload ${basePath}-->
  <script src="/js/lazysizes.min.js"></script>
</head>

<body class="bg-light single-post">

  <!-- Preloader -->
  <div class="loader-mask">
    <div class="loader">
      <div></div>
    </div>
  </div>

  <#include "nav.ftl">

  <main class="main oh" id="main">
    <#include "header.ftl">

    <div class="main-container container mt-30" id="main-container">
      <!-- Content -->
      <div class="row">
            
        <!-- post content -->
        <div class="col-lg-8 blog__content mb-30">

          <!-- Breadcrumbs -->
          <ul class="breadcrumbs">
            <li class="breadcrumbs__item">
              <a href="${basePath}" class="breadcrumbs__url">
                首页
              </a>
            </li>
            <li class="breadcrumbs__item breadcrumbs__item--current">
              ${idArticle[0].categoryName}
            </li>
          </ul>

          <!-- standard post -->
          <article class="entry">
            
            <div class="single-post__entry-header entry__header">
              <a href="#" class="entry__meta-category"> ${idArticle[0].categoryName}</a>
              <h1 class="single-post__entry-title">
                ${idArticle[0].contentTitle}
              </h1>

              <ul class="entry__meta">
                <li class="entry__meta-author">
                  <i class="ui-author"></i>
                  <a href="#">   ${idArticle[0].contentAuthor}</a>
                </li>
                <li class="entry__meta-date">
                  <i class="ui-date"></i>
                  ${idArticle[0].createTime}
                </li>
                <li class="entry__meta-comments">
                  <i class="ui-comments"></i>
                  ${idArticle[0].contentHit}
                </li>
              </ul>
            </div>
            <div class="ql-container">
                <div class="ql-editor">
                 ${idArticle[0].contentDetails}
                </div>
             
            </div>
            <#--<!-- Author &ndash;&gt;-->
            <#--<div class="title-wrap mt-40">-->
              <#--<h5 class="uppercase">说明</h5>-->
            <#--</div>-->
            <#--<div class="entry-author clearfix">-->
              <#--<img alt="" data-src="img/blog/author_large.jpg" src="img/empty.png" class="avatar lazyload">-->
              <#--<div class="entry-author__info">-->
                <#--<h6 class="entry-author__name">-->
                  <#--<a href="#">John Carpet</a>-->
                <#--</h6>-->
                <#--<p class="mb-0">But unfortunately for most of us our role as gardener has never been explained to us. And in misunderstanding our role, we have allowed seeds of all types, both good and bad, to enter our inner garden.</p>-->
              <#--</div>-->
            <#--</div>-->

            <!-- Prev / Next Post -->
            <nav class="entry-navigation">
              <div class="clearfix">
                <div class="entry-navigation--left">
                  <i class="ui-arrow-left"></i>
                  <span class="entry-navigation__label">上一篇</span>
                  <div class="entry-navigation__link">
                    <#if lastArticle?? && (lastArticle?size > 0) >
                      <a class="text-line-1" href="/detail/${lastArticle[0].id?c}.html" rel="next">${lastArticle[0].contentTitle}</a>
                    <#else>
                      <a class="text-line-1" href="/" rel="next">无上一篇</a>
                    </#if>
                  </div>
                </div>
                <div class="entry-navigation--right">
                  <span class="entry-navigation__label">下一篇</span>
                  <i class="ui-arrow-right"></i>
                  <div class="entry-navigation__link">
                    <#if nextArticle?? && (nextArticle?size > 0) >
                      <a class="text-line-1" href="/detail/${nextArticle[0].id?c}.html" rel="next">${nextArticle[0].contentTitle}</a>

                    <#else>
                      <a class="text-line-1" href="/" rel="next">无下一篇</a>
                    </#if>
                  </div>
                </div>
              </div>
            </nav>
            <div class="text-center pb-40 " id="detail-advert">
              <a href="#">
                <img src="${basePath}/img/blog/placeholder_leaderboard.jpg" alt="">
              </a>
            </div>
            <!-- Related Posts -->
            <#--<div class="related-posts">-->
              <#--<div class="title-wrap mt-40">-->
                <#--<h5 class="uppercase">Related Posts</h5>-->
              <#--</div>-->
              <#--<div class="row row-20">-->
                <#--<div class="col-md-4">-->
                  <#--<article class="entry">-->
                    <#--<div class="entry__img-holder">-->
                      <#--<a href="single-post.html">-->
                        <#--<div class="thumb-container thumb-75">-->
                          <#--<img data-src="img/blog/carousel_img_1.jpg" src="img/empty.png" class="entry__img lazyload" alt="">-->
                        <#--</div>-->
                      <#--</a>-->
                    <#--</div>-->

                    <#--<div class="entry__body">-->
                      <#--<div class="entry__header">-->
                        <#--<h2 class="entry__title entry__title--sm">-->
                          <#--<a href="single-post.html">Satelite cost tens of millions or even hundreds of millions of dollars to build</a>-->
                        <#--</h2>-->
                      <#--</div>-->
                    <#--</div>-->
                  <#--</article>-->
                <#--</div>-->
                <#--<div class="col-md-4">-->
                  <#--<article class="entry">-->
                    <#--<div class="entry__img-holder">-->
                      <#--<a href="single-post.html">-->
                        <#--<div class="thumb-container thumb-75">-->
                          <#--<img data-src="img/blog/carousel_img_2.jpg" src="img/empty.png" class="entry__img lazyload" alt="">-->
                        <#--</div>-->
                      <#--</a>-->
                    <#--</div>-->

                    <#--<div class="entry__body">-->
                      <#--<div class="entry__header">-->
                        <#--<h2 class="entry__title entry__title--sm">-->
                          <#--<a href="single-post.html">Satelite cost tens of millions or even hundreds of millions of dollars to build</a>-->
                        <#--</h2>-->
                      <#--</div>-->
                    <#--</div>-->
                  <#--</article>-->
                <#--</div>-->
                <#--<div class="col-md-4">-->
                  <#--<article class="entry">-->
                    <#--<div class="entry__img-holder">-->
                      <#--<a href="single-post.html">-->
                        <#--<div class="thumb-container thumb-75">-->
                          <#--<img data-src="img/blog/carousel_img_3.jpg" src="img/empty.png" class="entry__img lazyload" alt="">-->
                        <#--</div>-->
                      <#--</a>-->
                    <#--</div>-->

                    <#--<div class="entry__body">-->
                      <#--<div class="entry__header">-->
                        <#--<h2 class="entry__title entry__title--sm">-->
                          <#--<a href="single-post.html">Satelite cost tens of millions or even hundreds of millions of dollars to build</a>-->
                        <#--</h2>-->
                      <#--</div>-->
                    <#--</div>-->
                  <#--</article>-->
                <#--</div>-->
              <#--</div>-->
            <#--</div> -->
            <!-- end related posts -->
            <section class="section mb-20">
              <div class="title-wrap">
                <h3 class="section-title section-title--sm">更多文章</h3>
                <div class="carousel-nav">
                  <button class="carousel-nav__btn carousel-nav__btn--prev" aria-label="previous slide">
                    <i class="ui-arrow-left"></i>
                  </button>
                  <button class="carousel-nav__btn carousel-nav__btn--next" aria-label="next slide">
                    <i class="ui-arrow-right"></i>
                  </button>
                </div>
              </div>

              <!-- Slider -->
              <div id="owl-posts" class="owl-carousel owl-theme">
                <#list supportList as  row>

                  <article class="entry">
                    <div class="entry__img-holder">
                      <a href="${basePath}/detail/${row.id?c}.html">
                        <div class="thumb-container thumb-75">
                          <img data-src="${row.contentImgs?split(";")[0]}" src="${basePath}/img/blog/carousel_img_1.jpg"
                               class="entry__img owl-lazy" alt="${row.contentTitle}"/>
                        </div>
                      </a>
                    </div>

                    <div class="entry__body">
                      <div class="entry__header">
                        <h2 class="entry__title entry__title--sm">
                          <a href="${basePath}/detail/${row.id?c}.html" title="${row.contentTitle}" class="text-line-1">${row.contentTitle}</a>
                        </h2>
                        <ul class="entry__meta">
                          <li class="entry__meta-date">
                            <i class="ui-date"></i>
                            ${row.createTime}
                          </li>
                          <li class="entry__meta-comments">
                            <i class="ui-comments"></i>
                            <a href="javascript:void(0)">${row.supportCount}</a>
                          </li>
                        </ul>
                      </div>
                    </div>
                  </article>
                </#list>
              </div> <!-- end slider -->

            </section>

          </article> <!-- end standard post -->


          <#--<!-- Comments &ndash;&gt;-->
          <#--<div class="entry-comments mt-30">-->
            <#--<div class="title-wrap mt-40">-->
              <#--<h5 class="uppercase">3 comments</h5>-->
            <#--</div>-->
            <#--<ul class="comment-list">-->
              <#--<li class="comment">  -->
                <#--<div class="comment-body">-->
                  <#--<div class="comment-avatar">-->
                    <#--<img alt="" src="img/blog/comment_1.jpg">-->
                  <#--</div>-->
                  <#--<div class="comment-text">-->
                    <#--<h6 class="comment-author">Joeby Ragpa</h6>-->
                    <#--<div class="comment-metadata">-->
                      <#--<a href="#" class="comment-date">July 17, 2017 at 12:48 pm</a>-->
                    <#--</div>                      -->
                    <#--<p>This template is so awesome. I didn’t expect so many features inside. E-commerce pages are very useful, you can launch your online store in few seconds. I will rate 5 stars.</p>-->
                    <#--<a href="#" class="comment-reply">Reply</a>-->
                  <#--</div>-->
                <#--</div>-->

                <#--<ul class="children">-->
                  <#--<li class="comment">-->
                    <#--<div class="comment-body">-->
                      <#--<div class="comment-avatar">-->
                        <#--<img alt="" src="img/blog/comment_2.jpg">-->
                      <#--</div>-->
                      <#--<div class="comment-text">-->
                        <#--<h6 class="comment-author">Alexander Samokhin</h6>-->
                        <#--<div class="comment-metadata">-->
                          <#--<a href="#" class="comment-date">July 17, 2017 at 12:48 pm</a>  -->
                        <#--</div>                      -->
                        <#--<p>This template is so awesome. I didn’t expect so many features inside. E-commerce pages are very useful, you can launch your online store in few seconds. I will rate 5 stars.</p>-->
                        <#--<a href="#" class="comment-reply">Reply</a>-->
                      <#--</div>-->
                    <#--</div>-->
                  <#--</li> <!-- end reply comment &ndash;&gt;-->
                <#--</ul>-->

              <#--</li> <!-- end 1-2 comment &ndash;&gt;-->

              <#--<li>-->
                <#--<div class="comment-body">-->
                  <#--<div class="comment-avatar">-->
                    <#--<img alt="" src="img/blog/comment_3.jpg">-->
                  <#--</div>-->
                  <#--<div class="comment-text">-->
                    <#--<h6 class="comment-author">Chris Root</h6>-->
                    <#--<div class="comment-metadata">-->
                      <#--<a href="#" class="comment-date">July 17, 2017 at 12:48 pm</a>  -->
                    <#--</div>                      -->
                    <#--<p>This template is so awesome. I didn’t expect so many features inside. E-commerce pages are very useful, you can launch your online store in few seconds. I will rate 5 stars.</p>-->
                    <#--<a href="#" class="comment-reply">Reply</a>-->
                  <#--</div>-->
                <#--</div>-->
              <#--</li> <!-- end 3 comment &ndash;&gt;-->

            <#--</ul>         -->
          <#--</div> <!-- end comments &ndash;&gt;-->


          <#--<!-- Comment Form &ndash;&gt;-->
          <#--<div id="respond" class="comment-respond">-->
            <#--<div class="title-wrap">-->
              <#--<h5 class="comment-respond__title uppercase">Leave a Reply</h5>-->
            <#--</div>-->
            <#--<form id="form" class="comment-form" method="post" action="#">-->
              <#--<p class="comment-form-comment">-->
                <#--<!-- <label for="comment">Comment</label> &ndash;&gt;-->
                <#--<textarea id="comment" name="comment" rows="5" required="required" placeholder="Comment*"></textarea>-->
              <#--</p>-->

              <#--<div class="row row-20">-->
                <#--<div class="col-lg-4">-->
                  <#--<input name="name" id="name" type="text" placeholder="Name*">-->
                <#--</div>-->
                <#--<div class="col-lg-4">-->
                  <#--<input name="email" id="email" type="email" placeholder="Email*">-->
                <#--</div>-->
                <#--<div class="col-lg-4">-->
                  <#--<input name="website" id="website" type="text" placeholder="Website">-->
                <#--</div>-->
              <#--</div>-->

              <#--<p class="comment-form-submit">-->
                <#--<input type="submit" class="btn btn-lg btn-color btn-button" value="Post Comment" id="submit-message">-->
              <#--</p>-->
              <#---->
            <#--</form>-->
          <#--</div> <!-- end comment form &ndash;&gt;-->

        </div> <!-- end col -->
        
        <!-- Sidebar -->
        <aside class="col-lg-4 sidebar sidebar--right">

        <#include "right.ftl">
        </aside> <!-- end sidebar -->
      
      </div> <!-- end content -->
    </div> <!-- end main container -->
  <#include "footer.ftl">
    <!-- Footer -->
    <#--<footer class="footer footer--dark">-->
      <#--<div class="container">-->
        <#--<div class="footer__widgets">-->
          <#--<div class="row">-->

            <#--<div class="col-lg-3 col-md-6">-->
              <#--<div class="widget">-->
                <#--<a href="index.index">-->
                  <#--<img src="img/logo_mobile.png" srcset="img/logo_mobile.png 1x, img/logo_mobile@2x.png 2x" class="logo__img" alt="">-->
                <#--</a>-->
                <#--<p class="mt-20">We bring you the best Premium WordPress Themes. Deliver smart websites faster with this amazing theme. We care about our buyers.</p>-->
              <#--</div>-->
            <#--</div>-->

            <#--<div class="col-lg-3 col-md-6">-->
              <#--<h4 class="widget-title">Latest Posts</h4>-->
              <#--<ul class="post-list-small">-->
                <#--<li class="post-list-small__item">-->
                  <#--<article class="post-list-small__entry clearfix">-->
                    <#--<div class="post-list-small__img-holder">-->
                      <#--<div class="thumb-container thumb-75">-->
                        <#--<a href="single-post.html">-->
                          <#--<img data-src="img/blog/popular_post_1.jpg" src="img/empty.png" alt="" class="lazyload">-->
                        <#--</a>-->
                      <#--</div>-->
                    <#--</div>-->
                    <#--<div class="post-list-small__body">-->
                      <#--<h3 class="post-list-small__entry-title">-->
                        <#--<a href="single-post.html">Google is fixing its troubling burger emoji in Android 8.1</a>-->
                      <#--</h3>-->
                      <#--<ul class="entry__meta">-->
                        <#--<li class="entry__meta-date">-->
                          <#--<i class="ui-date"></i>-->
                          <#--21 October, 2017-->
                        <#--</li>-->
                      <#--</ul>-->
                    <#--</div>                  -->
                  <#--</article>-->
                <#--</li>-->
                <#--<li class="post-list-small__item">-->
                  <#--<article class="post-list-small__entry clearfix">-->
                    <#--<div class="post-list-small__img-holder">-->
                      <#--<div class="thumb-container thumb-75">-->
                        <#--<a href="single-post.html">-->
                          <#--<img data-src="img/blog/popular_post_2.jpg" src="img/empty.png" alt="" class="lazyload">-->
                        <#--</a>-->
                      <#--</div>-->
                    <#--</div>-->
                    <#--<div class="post-list-small__body">-->
                      <#--<h3 class="post-list-small__entry-title">-->
                        <#--<a href="single-post.html">How Meditation Can Transform Your Business</a>-->
                      <#--</h3>-->
                      <#--<ul class="entry__meta">-->
                        <#--<li class="entry__meta-date">-->
                          <#--<i class="ui-date"></i>-->
                          <#--21 October, 2017-->
                        <#--</li>-->
                      <#--</ul>-->
                    <#--</div>                  -->
                  <#--</article>-->
                <#--</li>-->
              <#--</ul>-->
            <#--</div>-->

            <#--<div class="col-lg-3 col-md-6">-->
              <#--<div class="widget widget__newsletter">-->
                <#--<h4 class="widget-title">Follow us</h4>-->

                <#--<div class="socials mb-20">-->
                  <#--<a href="#" class="social social-facebook" aria-label="facebook"><i class="ui-facebook"></i></a>-->
                  <#--<a href="#" class="social social-twitter" aria-label="twitter"><i class="ui-twitter"></i></a>-->
                  <#--<a href="#" class="social social-google-plus" aria-label="google+"><i class="ui-google"></i></a>-->
                  <#--<a href="#" class="social social-youtube" aria-label="youtube"><i class="ui-youtube"></i></a>-->
                  <#--<a href="#" class="social social-instagram" aria-label="instagram"><i class="ui-instagram"></i></a>-->
                <#--</div>-->

                <#--<form class="mc4wp-form" method="post">-->
                  <#--<div class="mc4wp-form-fields">-->
                    <#--<p>-->
                      <#--<input type="email" name="EMAIL" placeholder="Your email" required="">-->
                    <#--</p>-->
                    <#--<p>-->
                      <#--<input type="submit" class="btn btn-lg btn-color" value="Subscribe">-->
                    <#--</p>-->
                  <#--</div>-->
                <#--</form>-->
                <#---->
              <#--</div>-->
            <#--</div>            -->

            <#--<div class="col-lg-3 col-md-6">-->
              <#--<div class="widget widget_nav_menu">-->
                <#--<h4 class="widget-title">Useful Links</h4>-->
                <#--<ul>-->
                  <#--<li><a href="#">About</a></li>-->
                  <#--<li><a href="#">Contact</a></li>-->
                  <#--<li><a href="#">Categories</a></li>-->
                  <#--<li><a href="#">Shortcodes</a></li>-->
                <#--</ul>-->
              <#--</div>-->
            <#--</div>            -->

          <#--</div>-->
        <#--</div>    -->
      <#--</div> <!-- end container &ndash;&gt;-->

      <#--<div class="footer__bottom">-->
        <#--<div class="container">-->
          <#--<div class="row">-->
            <#--<div class="col-lg-7 order-lg-2 text-right text-md-center">-->
              <#--<div class="widget widget_nav_menu">-->
                <#--<ul>-->
                  <#--<li><a href="#">Terms</a></li>-->
                  <#--<li><a href="#">Privacy</a></li>-->
                  <#--<li><a href="#">Advertise</a></li>-->
                  <#--<li><a href="#">Affiliates</a></li>-->
                  <#--<li><a href="#">Newsletter</a></li>-->
                <#--</ul>-->
              <#--</div>              -->
            <#--</div>-->
            <#--<div class="col-lg-5 order-lg-1 text-md-center">-->
              <#--<span class="copyright">-->
                <#--Copyright &copy; 2018.Company name All rights reserved.<a target="_blank" href="http://sc.chinaz.com/moban/">&#x7F51;&#x9875;&#x6A21;&#x677F;</a>-->
              <#--</span>-->
            <#--</div>            -->
          <#--</div>-->
          <#---->
        <#--</div>-->
      <#--</div> <!-- end bottom footer &ndash;&gt;-->
    <#--</footer> -->
    <!-- end footer -->

    <div id="back-to-top">
      <a href="#top" aria-label="Go to top"><i class="ui-arrow-up"></i></a>
    </div>

  </main> <!-- end main-wrapper -->

  
  <!-- jQuery Scripts -->
  <script src="/js/jquery.min.js"></script>
  <script src="/js/bootstrap.min.js"></script>
  <script src="/js/easing.min.js"></script>
  <script src="/js/owl-carousel.min.js"></script>
  <script src="/js/twitterFetcher_min.js"></script>
  <script src="/js/modernizr.min.js"></script>
  <script src="/js/jquery.appear.min.js"></script>
  <script src="/js/scripts.js"></script>
  <script src="/js/right.js"></script>
  <script src="/js/advert.js"></script>
  <script src="/js/statistic.js"></script>

</body>
</html>
