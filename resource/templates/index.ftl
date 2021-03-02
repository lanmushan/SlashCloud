<!DOCTYPE html>
<html>
<head>
    <title>${siteName}</title>
    <meta charset="utf-8">
    <!--[if IE]>
    <meta http-equiv='X-UA-Compatible' content='IE=edge,chrome=1'><![endif]-->
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <meta name="keywords" content="${siteKeywords}">
    <meta name="description" content="${siteDescription}">
    <link rel="shortcut icon" type="images/x-icon"  href="${favicon}">
    <meta name="baidu-site-verification" content="code-S3WjiIiDpf" />

    <!-- Google Fonts -->
    <#--<link href='https://fonts.googleapis.com/css?family=Open+Sans:400,600,700' rel='stylesheet'>-->
    <!-- Css -->
    <link rel="stylesheet" href="/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="/css/font-icons.css"/>
    <link rel="stylesheet" href="/css/style.css"/>
    <link rel="stylesheet" href="/css/layout.css">
    <link rel="stylesheet" href="/css/pinkstyle.css">
    <!-- Lazyload -->
    <script src="/js/lazysizes.min.js"></script>
</head>

<body class="bg-light">

<!-- Preloader -->
<div class="loader-mask">
    <div class="loader">
        <div></div>
    </div>
</div>
<!-- Sidenav -->
<#include "nav.ftl">
<main class="main oh" id="main">
    <!-- Header -->
    <#include "header.ftl">
    <!-- end header -->

    <!-- Trending Now -->
    <#--<div class="trending-now">-->
        <#--<div class="container relative">-->
            <#--<span class="trending-now__label">Trending</span>-->
            <#--<ul class="newsticker">-->
                <#--<li class="newsticker__item"><a href="single-post.html" class="newsticker__item-url">A-HA theme is-->
                    <#--multi-purpose solution for any kind of business</a></li>-->
                <#--<li class="newsticker__item"><a href="#" class="newsticker__item-url">Satelite cost tens of millions or-->
                    <#--even hundreds of millions of dollars to build</a></li>-->
                <#--<li class="newsticker__item"><a href="#" class="newsticker__item-url">8 Hidden Costs of Starting and-->
                    <#--Running a Business</a></li>-->
            <#--</ul>-->
            <#--<div class="newsticker-buttons">-->
                <#--<button class="newsticker-button newsticker-button--prev" id="newsticker-button--prev"-->
                        <#--aria-label="next article"><i class="ui-arrow-left"></i></button>-->
                <#--<button class="newsticker-button newsticker-button--next" id="newsticker-button--next"-->
                        <#--aria-label="previous article"><i class="ui-arrow-right"></i></button>-->
            <#--</div>-->
        <#--</div>-->
    <#--</div>-->
    <#--<div>-->
        <#--<#list banner as ban>-->
        <#--<p>${ban.contentTitle}</p>-->
            <#--<p>${ban.contentTitle}</p>-->
            <#--<p>${ban.contentTitle}</p>-->
            <#--<p>${ban.contentTitle}</p>-->

        <#--</#list>-->
    <#--</div>-->
    <!-- Featured Posts Grid -->
    <section class="featured-posts-grid bg-dark">
        <div class="container clearfix">
            <#--banner-->
            <!-- Large post slider -->
            <div class="featured-posts-grid__item featured-posts-grid__item--lg">
                <div id="owl-hero-grid" class="owl-carousel owl-theme owl-carousel--dots-inside">
                    <#list banner as ban>
                    <article class="entry featured-posts-grid__entry">
                        <div class="thumb-bg-holder owl-lazy" data-src="${ban.contentImgs?split(";")[0]}">
                            <img src="/img/blog/featured_grid_slide_1.jpg" alt="" class="d-none">
                            <a href="${basePath}/detail/${ban.id?c}.html" class="thumb-url"></a>
                            <div class="bottom-gradient"></div>
                        </div>

                        <div class="thumb-text-holder">
                            <a href="${basePath}/detail/${ban.id?c}.html"
                               class="entry__meta-category entry__meta-category-color entry__meta-category-color--salad">Lifestyle</a>
                            <h2 class="thumb-entry-title" style="font-size: 18px">
                                <a href="${basePath}/detail/${ban.id?c}.html" class="text-line-2">${ban.contentTitle}</a>
                            </h2>
                        </div>
                    </article>
                    </#list>
                    <#--<article class="entry featured-posts-grid__entry">-->
                        <#--<div class="thumb-bg-holder owl-lazy" data-src="img/blog/featured_grid_slide_2.jpg">-->
                            <#--<img src="img/blog/featured_grid_slide_2.jpg" alt="" class="d-none">-->
                            <#--<a href="single-post.html" class="thumb-url"></a>-->
                            <#--<div class="bottom-gradient"></div>-->
                        <#--</div>-->

                        <#--<div class="thumb-text-holder">-->
                            <#--<a href="single-post.html"-->
                               <#--class="entry__meta-category entry__meta-category-color entry__meta-category-color--blue">Business</a>-->
                            <#--<h2 class="thumb-entry-title">-->
                                <#--<a href="single-post.html">BRICS Nations Agree to Create Own Development Bank</a>-->
                            <#--</h2>-->
                        <#--</div>-->
                    <#--</article>-->

                    <#--<article class="entry featured-posts-grid__entry">-->
                        <#--<div class="thumb-bg-holder owl-lazy" data-src="img/blog/featured_grid_slide_3.jpg">-->
                            <#--<img src="img/blog/featured_grid_slide_3.jpg" alt="" class="d-none">-->
                            <#--<a href="single-post.html" class="thumb-url"></a>-->
                            <#--<div class="bottom-gradient"></div>-->
                        <#--</div>-->

                        <#--<div class="thumb-text-holder">-->
                            <#--<a href="single-post.html"-->
                               <#--class="entry__meta-category entry__meta-category-color entry__meta-category-color--purple">Tech</a>-->
                            <#--<h2 class="thumb-entry-title">-->
                                <#--<a href="single-post.html">Tesla's Giant Battery Farm Is Now Live in South Australia</a>-->
                            <#--</h2>-->
                        <#--</div>-->
                    <#--</article>-->

                </div> <!-- end owl slider -->
            </div> <!-- end large post slider -->

            <#list topList as row>
            <div class="featured-posts-grid__item featured-posts-grid__item--sm">
                <article class="entry featured-posts-grid__entry">
                    <div class="thumb-bg-holder" style="background-image: url(${row.contentImgs?split(";")[0]});">
                        <a href="${basePath}/detail/${row.id?c}.html" class="thumb-url"></a>
                        <div class="bottom-gradient"></div>
                    </div>

                    <div class="thumb-text-holder">
                        <h2 class="thumb-entry-title thumb-entry-title--sm">
                            <a href="${basePath}/detail/${row.id?c}.html" class="text-line-2 ">${row.contentTitle}</a>
                        </h2>
                        <ul class="entry__meta">
                            <li class="entry__meta-author">
                                <i class="ui-author"></i>
                                <a href="javascript:void(0)">${row.contentAuthor}</a>
                            </li>
                            <li class="entry__meta-date">
                                <i class="ui-date"></i>
                                ${row.createTime}
                            </li>
                            <li class="entry__meta-comments">
                                <i class="ui-comments"></i>
                                <a href="javascript:void(0)"> ${row.contentHit}</a>
                            </li>
                        </ul>
                    </div>
                </article>
            </div>

            <#--<div class="featured-posts-grid__item featured-posts-grid__item--sm">-->
                <#--<article class="entry featured-posts-grid__entry">-->
                    <#--<div class="thumb-bg-holder" style="background-image: url(img/blog/featured_grid_3.jpg);">-->
                        <#--<a href="single-post.html" class="thumb-url"></a>-->
                        <#--<div class="bottom-gradient"></div>-->
                    <#--</div>-->

                    <#--<div class="thumb-text-holder">-->
                        <#--<h2 class="thumb-entry-title thumb-entry-title--sm">-->
                            <#--<a href="single-post.html">Experience the Grand Canyon National Park</a>-->
                        <#--</h2>-->
                        <#--<ul class="entry__meta">-->
                            <#--<li class="entry__meta-author">-->
                                <#--<i class="ui-author"></i>-->
                                <#--<a href="#">DeoThemes</a>-->
                            <#--</li>-->
                            <#--<li class="entry__meta-date">-->
                                <#--<i class="ui-date"></i>-->
                                <#--21 October, 2017-->
                            <#--</li>-->
                            <#--<li class="entry__meta-comments">-->
                                <#--<i class="ui-comments"></i>-->
                                <#--<a href="#">115</a>-->
                            <#--</li>-->
                        <#--</ul>-->
                    <#--</div>-->
                <#--</article>-->
            <#--</div>-->
            </#list>
        </div>
    </section> <!-- end featured posts grid -->

    <div class="main-container container mt-40" id="main-container">

        <!-- Content -->
        <div class="row">

            <!-- Posts -->
            <div class="col-lg-8 blog__content mb-30">

                <!-- Hot News -->
                <section class="section tab-post mb-10">
                    <div class="title-wrap">
                        <h3 class="section-title">热门文章</h3>

                        <div class="tabs tab-post__tabs">
                            <ul class="tabs__list">
                                <#list hotList as hot>
                                    <#--tabs__item--active-->
                                <li class="tabs__item <#if hot_index==0>tabs__item--active</#if>">
                                    <a href="#tab-${hot.categoryCode}" class="tabs__trigger">${hot.categoryName}</a>
                                </li>
                                <#--<li class="tabs__item">-->
                                    <#--<a href="#tab-world" class="tabs__trigger">World</a>-->
                                <#--</li>-->
                                <#--<li class="tabs__item">-->
                                    <#--<a href="#tab-business" class="tabs__trigger">Business</a>-->
                                <#--</li>-->
                                <#--<li class="tabs__item">-->
                                    <#--<a href="#tab-politics" class="tabs__trigger">Politics</a>-->
                                <#--</li>-->
                                <#--<li class="tabs__item">-->
                                    <#--<a href="#tab-marketing" class="tabs__trigger">Marketing</a>-->
                                <#--</li>-->
                                </#list>
                            </ul> <!-- end tabs -->
                        </div>
                    </div>
                    <!-- tab content -->
                    <div class="tabs__content tabs__content-trigger tab-post__tabs-content">
                        <#list hotList as hot>
                        <#--tabs__content-pane--active-->

                        <div class="tabs__content-pane  <#if hot_index==0>tabs__content-pane--active</#if>"  id="tab-${hot.categoryCode}">
                            <div class="row">
                                <#list  hot.articleList as row>
                                <div class="col-md-6">
                                    <article class="entry">
                                        <div class="entry__img-holder">
                                            <a href="${basePath}/detail/${row.id?c}.html">
                                                <div class="thumb-container thumb-75">
                                                    <img data-src="${row.contentImgs?split(";")[0]}" src="/img/empty.png"
                                                         class="entry__img lazyload" alt=""/>
                                                </div>
                                            </a>
                                        </div>
                                        <div class="entry__body">
                                            <div class="entry__header">
                                                <a href="javascript:void(0)" class="entry__meta-category">${hot.categoryName}</a>
                                                <h2 class="entry__title">
                                                    <a href="${basePath}/detail/${hot.id?c}.html" class="text-line-1">${row.contentTitle}</a>
                                                </h2>
                                                <ul class="entry__meta">
                                                    <li class="entry__meta-author">
                                                        <i class="ui-author"></i>
                                                        <a href="javascript:void(0)">${row.contentAuthor}</a>
                                                    </li>
                                                    <li class="entry__meta-date">
                                                        <i class="ui-date"></i>
                                                        ${row.createTime?string('dd.MM.yyyy HH:mm:ss')}
                                                    </li>
                                                    <li class="entry__meta-comments">
                                                        <i class="ui-comments"></i>
                                                        <a href="javascript:void(0)">${row.contentHit}</a>
                                                    </li>
                                                </ul>
                                            </div>
                                            <div class="entry__excerpt">
                                                <p class="text-line-3"> ${row.contentDescription}</p>
                                            </div>
                                        </div>
                                    </article>
                                </div>
                                </#list>
                            </div>
                        </div> <!-- end pane 1 -->
                        </#list>
                    </div> <!-- end tab content -->
                </section> <!-- end hot news -->
                <div class="text-center pb-40" id="index-advert">
                    <#--<a href="#">-->
                        <#--<img src="img/blog/placeholder_leaderboard.jpg" alt="">-->
                    <#--</a>-->
                </div>
                <section class="section editors-picks mb-20">
                    <div class="title-wrap">
                        <h3 class="section-title">小编推荐</h3>
                        <a href="#" class="all-posts-url">更多</a>
                    </div>
                    <div class="row">
                        <div class="col-lg-7">
                            <article class="entry">
                                <div class="entry__img-holder">
                                    <a href="${basePath}/detail/${recommend[0].id?c}.html">
                                    <div class="thumb-container thumb-75">
                                            <img data-src="${recommend[0].contentImgs?split(";")[0]}" src="/img/empty.png"
                                                 class="entry__img lazyload" alt=""/>
                                        </div>
                                    </a>
                                </div>

                                <div class="entry__body">
                                    <div class="entry__header">
                                        <a href="#" class="entry__meta-category">${recommend[0].categoryName}</a>
                                        <h2 class="entry__title">
                                            <a href="${basePath}/detail/${recommend[0].id?c}.html" class="text-line-2">${recommend[0].contentTitle}</a>
                                        </h2>
                                        <ul class="entry__meta">
                                            <li class="entry__meta-author">
                                                <i class="ui-author"></i>
                                                <a href="javascript:void(0)">${recommend[0].contentAuthor}</a>
                                            </li>
                                            <li class="entry__meta-date">
                                                <i class="ui-date"></i>
                                                ${recommend[0].createTime}
                                            </li>
                                            <li class="entry__meta-comments">
                                                <i class="ui-comments"></i>
                                                <a href="javascript:void(0)">${recommend[0].contentHit}</a>
                                            </li>
                                        </ul>
                                    </div>
                                    <div class="entry__excerpt">
                                        <p class="text-line-2">${recommend[0].contentDescription}</p>
                                    </div>
                                </div>
                            </article>
                        </div>
                        <div class="col-lg-5">
                            <ul class="post-list-small">
                                <#list recommendList as row>


                                <li class="post-list-small__item">
                                    <article class="post-list-small__entry">
                                        <div class="post-list-small__body">
                                            <h3 class="post-list-small__entry-title">
                                                <a  href="${basePath}/detail/${row.id?c}.html" class="text-line-1">${row.contentTitle}</a>
                                            </h3>
                                            <ul class="entry__meta">
                                                <li class="entry__meta-date">
                                                    <i class="ui-date"></i>
                                                    ${row.createTime}
                                                </li>
                                            </ul>
                                        </div>
                                    </article>
                                </li>
                                </#list>
                            </ul>
                        </div>
                    </div>
                </section>
                <!-- Latest News -->
                <section class="section">
                    <div class="title-wrap">
                        <h3 class="section-title">最新文章</h3>
                        <a href="#" class="all-posts-url">更多</a>
                    </div>
                    <#list newArticle as row>

                    <article class="entry post-list">
                        <div class="entry__img-holder post-list__img-holder">
                            <a href="${basePath}/detail/${row.id?c}.html">
                                <div class="thumb-container thumb-75">
                                    <img data-src="${row.contentImgs?split(";")[0]}" src="/img/empty.png"
                                         class="entry__img lazyload" alt="${row.contentTitle}">
                                </div>
                            </a>
                        </div>

                        <div class="entry__body post-list__body">
                            <div class="entry__header">
                                <a href="javascript:void(0)" class="entry__meta-category">${row.categoryName}</a>
                                <h2 class="entry__title">
                                    <a href="${basePath}/detail/${row.id?c}.html" class="text-line-2">${row.contentTitle}</a>
                                </h2>
                                <ul class="entry__meta">
                                    <li class="entry__meta-author">
                                        <i class="ui-author"></i>
                                        <a href="javascript:void(0)">${row.contentAuthor}</a>
                                    </li>
                                    <li class="entry__meta-date">
                                        <i class="ui-date"></i>
                                        ${row.createTime}
                                    </li>
                                    <li class="entry__meta-comments">
                                        <i class="ui-comments"></i>
                                        <a href="javascript:void(0)">${row.contentHit}</a>
                                    </li>
                                </ul>
                            </div>
                            <div class="entry__excerpt">
                                <p class="text-line-3">${row.contentDescription}</p>
                            </div>
                        </div>
                    </article>
                    </#list>
                </section>
                <!-- end latest news -->

                <!-- Ad Banner 728 -->


                <!-- Editor's Picks -->
              <!-- end editors picks -->

                <!-- Posts from categories -->
                <#--<section class="section mb-0">-->
                    <#--<div class="row">-->

                        <#--<!-- World &ndash;&gt;-->
                        <#--<div class="col-md-6 mb-40">-->
                            <#--<div class="title-wrap bottom-line bottom-line--orange">-->
                                <#--<h3 class="section-title section-title--sm">World</h3>-->
                            <#--</div>-->
                            <#--<article class="entry">-->
                                <#--<div class="entry__img-holder">-->
                                    <#--<a href="single-post.html">-->
                                        <#--<div class="thumb-container thumb-75">-->
                                            <#--<img data-src="img/blog/grid_post_img_3.jpg" src="img/empty.png"-->
                                                 <#--class="entry__img lazyload" alt=""/>-->
                                        <#--</div>-->
                                    <#--</a>-->
                                <#--</div>-->

                                <#--<div class="entry__body">-->
                                    <#--<div class="entry__header">-->
                                        <#--<h2 class="entry__title">-->
                                            <#--<a href="single-post.html">To Succeed in 2018, Ecommerce Entrepreneurs Must-->
                                                <#--Focus on This One Change</a>-->
                                        <#--</h2>-->
                                        <#--<ul class="entry__meta">-->
                                            <#--<li class="entry__meta-author">-->
                                                <#--<i class="ui-author"></i>-->
                                                <#--<a href="#">DeoThemes</a>-->
                                            <#--</li>-->
                                            <#--<li class="entry__meta-date">-->
                                                <#--<i class="ui-date"></i>-->
                                                <#--21 October, 2017-->
                                            <#--</li>-->
                                            <#--<li class="entry__meta-comments">-->
                                                <#--<i class="ui-comments"></i>-->
                                                <#--<a href="#">115</a>-->
                                            <#--</li>-->
                                        <#--</ul>-->
                                    <#--</div>-->
                                    <#--<div class="entry__excerpt">-->
                                        <#--<p>Class aptent taciti sociosqu ad litora torquent per conubia nostra, per-->
                                            <#--inceptos. Lorem ipsum dolor sit amet, consectetur adipiscing elit...</p>-->
                                    <#--</div>-->
                                <#--</div>-->
                            <#--</article>-->
                            <#--<ul class="post-list-small post-list-small--border-top">-->
                                <#--<li class="post-list-small__item">-->
                                    <#--<article class="post-list-small__entry">-->
                                        <#--<div class="post-list-small__body">-->
                                            <#--<h3 class="post-list-small__entry-title">-->
                                                <#--<a href="single-post.html">What Indie Beauty Companies Can Teach-->
                                                    <#--Entrepreneurs About Scaling</a>-->
                                            <#--</h3>-->
                                            <#--<ul class="entry__meta">-->
                                                <#--<li class="entry__meta-date">-->
                                                    <#--<i class="ui-date"></i>-->
                                                    <#--21 October, 2017-->
                                                <#--</li>-->
                                            <#--</ul>-->
                                        <#--</div>-->
                                    <#--</article>-->
                                <#--</li>-->
                                <#--<li class="post-list-small__item">-->
                                    <#--<article class="post-list-small__entry">-->
                                        <#--<div class="post-list-small__body">-->
                                            <#--<h3 class="post-list-small__entry-title">-->
                                                <#--<a href="single-post.html">Bill Gates's 5 Favorite Books of 2017</a>-->
                                            <#--</h3>-->
                                            <#--<ul class="entry__meta">-->
                                                <#--<li class="entry__meta-date">-->
                                                    <#--<i class="ui-date"></i>-->
                                                    <#--21 October, 2017-->
                                                <#--</li>-->
                                            <#--</ul>-->
                                        <#--</div>-->
                                    <#--</article>-->
                                <#--</li>-->
                                <#--<li class="post-list-small__item">-->
                                    <#--<article class="post-list-small__entry">-->
                                        <#--<div class="post-list-small__body">-->
                                            <#--<h3 class="post-list-small__entry-title">-->
                                                <#--<a href="single-post.html">Sheryl Sandberg's 6 Steps to Make Sure-->
                                                    <#--Everyone Feels Safe at Work</a>-->
                                            <#--</h3>-->
                                            <#--<ul class="entry__meta">-->
                                                <#--<li class="entry__meta-date">-->
                                                    <#--<i class="ui-date"></i>-->
                                                    <#--21 October, 2017-->
                                                <#--</li>-->
                                            <#--</ul>-->
                                        <#--</div>-->
                                    <#--</article>-->
                                <#--</li>-->
                            <#--</ul>-->
                        <#--</div> <!-- end world &ndash;&gt;-->

                        <#--<!-- Science &ndash;&gt;-->
                        <#--<div class="col-md-6 mb-40">-->
                            <#--<div class="title-wrap bottom-line bottom-line--violet">-->
                                <#--<h3 class="section-title section-title--sm">Science</h3>-->
                            <#--</div>-->
                            <#--<article class="entry">-->
                                <#--<div class="entry__img-holder">-->
                                    <#--<a href="single-post.html">-->
                                        <#--<div class="thumb-container thumb-75">-->
                                            <#--<img data-src="img/blog/grid_post_img_4.jpg" src="img/empty.png"-->
                                                 <#--class="entry__img lazyload" alt=""/>-->
                                        <#--</div>-->
                                    <#--</a>-->
                                <#--</div>-->

                                <#--<div class="entry__body">-->
                                    <#--<div class="entry__header">-->
                                        <#--<h2 class="entry__title">-->
                                            <#--<a href="single-post.html">What hospitals can do to help keep excess opioids-->
                                                <#--out of communities</a>-->
                                        <#--</h2>-->
                                        <#--<ul class="entry__meta">-->
                                            <#--<li class="entry__meta-author">-->
                                                <#--<i class="ui-author"></i>-->
                                                <#--<a href="#">DeoThemes</a>-->
                                            <#--</li>-->
                                            <#--<li class="entry__meta-date">-->
                                                <#--<i class="ui-date"></i>-->
                                                <#--21 October, 2017-->
                                            <#--</li>-->
                                            <#--<li class="entry__meta-comments">-->
                                                <#--<i class="ui-comments"></i>-->
                                                <#--<a href="#">115</a>-->
                                            <#--</li>-->
                                        <#--</ul>-->
                                    <#--</div>-->
                                    <#--<div class="entry__excerpt">-->
                                        <#--<p>Class aptent taciti sociosqu ad litora torquent per conubia nostra, per-->
                                            <#--inceptos. Lorem ipsum dolor sit amet, consectetur adipiscing elit...</p>-->
                                    <#--</div>-->
                                <#--</div>-->
                            <#--</article>-->
                            <#--<ul class="post-list-small post-list-small--border-top">-->
                                <#--<li class="post-list-small__item">-->
                                    <#--<article class="post-list-small__entry">-->
                                        <#--<div class="post-list-small__body">-->
                                            <#--<h3 class="post-list-small__entry-title">-->
                                                <#--<a href="single-post.html">What Indie Beauty Companies Can Teach-->
                                                    <#--Entrepreneurs About Scaling</a>-->
                                            <#--</h3>-->
                                            <#--<ul class="entry__meta">-->
                                                <#--<li class="entry__meta-date">-->
                                                    <#--<i class="ui-date"></i>-->
                                                    <#--21 October, 2017-->
                                                <#--</li>-->
                                            <#--</ul>-->
                                        <#--</div>-->
                                    <#--</article>-->
                                <#--</li>-->
                                <#--<li class="post-list-small__item">-->
                                    <#--<article class="post-list-small__entry">-->
                                        <#--<div class="post-list-small__body">-->
                                            <#--<h3 class="post-list-small__entry-title">-->
                                                <#--<a href="single-post.html">Bill Gates's 5 Favorite Books of 2017</a>-->
                                            <#--</h3>-->
                                            <#--<ul class="entry__meta">-->
                                                <#--<li class="entry__meta-date">-->
                                                    <#--<i class="ui-date"></i>-->
                                                    <#--21 October, 2017-->
                                                <#--</li>-->
                                            <#--</ul>-->
                                        <#--</div>-->
                                    <#--</article>-->
                                <#--</li>-->
                                <#--<li class="post-list-small__item">-->
                                    <#--<article class="post-list-small__entry">-->
                                        <#--<div class="post-list-small__body">-->
                                            <#--<h3 class="post-list-small__entry-title">-->
                                                <#--<a href="single-post.html">Sheryl Sandberg's 6 Steps to Make Sure-->
                                                    <#--Everyone Feels Safe at Work</a>-->
                                            <#--</h3>-->
                                            <#--<ul class="entry__meta">-->
                                                <#--<li class="entry__meta-date">-->
                                                    <#--<i class="ui-date"></i>-->
                                                    <#--21 October, 2017-->
                                                <#--</li>-->
                                            <#--</ul>-->
                                        <#--</div>-->
                                    <#--</article>-->
                                <#--</li>-->
                            <#--</ul>-->
                        <#--</div> <!-- end science &ndash;&gt;-->

                        <#--<!-- Sport &ndash;&gt;-->
                        <#--<div class="col-md-6 mb-40">-->
                            <#--<div class="title-wrap bottom-line bottom-line--light-blue">-->
                                <#--<h3 class="section-title section-title--sm">Sport</h3>-->
                            <#--</div>-->
                            <#--<article class="entry">-->
                                <#--<div class="entry__img-holder">-->
                                    <#--<a href="single-post.html">-->
                                        <#--<div class="thumb-container thumb-75">-->
                                            <#--<img data-src="img/blog/grid_post_img_5.jpg" src="img/empty.png"-->
                                                 <#--class="entry__img lazyload" alt=""/>-->
                                        <#--</div>-->
                                    <#--</a>-->
                                <#--</div>-->

                                <#--<div class="entry__body">-->
                                    <#--<div class="entry__header">-->
                                        <#--<h2 class="entry__title">-->
                                            <#--<a href="single-post.html">Phil Taylor: The Power and The Glory</a>-->
                                        <#--</h2>-->
                                        <#--<ul class="entry__meta">-->
                                            <#--<li class="entry__meta-author">-->
                                                <#--<i class="ui-author"></i>-->
                                                <#--<a href="#">DeoThemes</a>-->
                                            <#--</li>-->
                                            <#--<li class="entry__meta-date">-->
                                                <#--<i class="ui-date"></i>-->
                                                <#--21 October, 2017-->
                                            <#--</li>-->
                                            <#--<li class="entry__meta-comments">-->
                                                <#--<i class="ui-comments"></i>-->
                                                <#--<a href="#">115</a>-->
                                            <#--</li>-->
                                        <#--</ul>-->
                                    <#--</div>-->
                                    <#--<div class="entry__excerpt">-->
                                        <#--<p>Class aptent taciti sociosqu ad litora torquent per conubia nostra, per-->
                                            <#--inceptos. Lorem ipsum dolor sit amet, consectetur adipiscing elit...</p>-->
                                    <#--</div>-->
                                <#--</div>-->
                            <#--</article>-->
                            <#--<ul class="post-list-small post-list-small--border-top">-->
                                <#--<li class="post-list-small__item">-->
                                    <#--<article class="post-list-small__entry">-->
                                        <#--<div class="post-list-small__body">-->
                                            <#--<h3 class="post-list-small__entry-title">-->
                                                <#--<a href="single-post.html">What Indie Beauty Companies Can Teach-->
                                                    <#--Entrepreneurs About Scaling</a>-->
                                            <#--</h3>-->
                                            <#--<ul class="entry__meta">-->
                                                <#--<li class="entry__meta-date">-->
                                                    <#--<i class="ui-date"></i>-->
                                                    <#--21 October, 2017-->
                                                <#--</li>-->
                                            <#--</ul>-->
                                        <#--</div>-->
                                    <#--</article>-->
                                <#--</li>-->
                                <#--<li class="post-list-small__item">-->
                                    <#--<article class="post-list-small__entry">-->
                                        <#--<div class="post-list-small__body">-->
                                            <#--<h3 class="post-list-small__entry-title">-->
                                                <#--<a href="single-post.html">Bill Gates's 5 Favorite Books of 2017</a>-->
                                            <#--</h3>-->
                                            <#--<ul class="entry__meta">-->
                                                <#--<li class="entry__meta-date">-->
                                                    <#--<i class="ui-date"></i>-->
                                                    <#--21 October, 2017-->
                                                <#--</li>-->
                                            <#--</ul>-->
                                        <#--</div>-->
                                    <#--</article>-->
                                <#--</li>-->
                                <#--<li class="post-list-small__item">-->
                                    <#--<article class="post-list-small__entry">-->
                                        <#--<div class="post-list-small__body">-->
                                            <#--<h3 class="post-list-small__entry-title">-->
                                                <#--<a href="single-post.html">Sheryl Sandberg's 6 Steps to Make Sure-->
                                                    <#--Everyone Feels Safe at Work</a>-->
                                            <#--</h3>-->
                                            <#--<ul class="entry__meta">-->
                                                <#--<li class="entry__meta-date">-->
                                                    <#--<i class="ui-date"></i>-->
                                                    <#--21 October, 2017-->
                                                <#--</li>-->
                                            <#--</ul>-->
                                        <#--</div>-->
                                    <#--</article>-->
                                <#--</li>-->
                            <#--</ul>-->
                        <#--</div> <!-- end sport &ndash;&gt;-->

                        <#--<!-- Business &ndash;&gt;-->
                        <#--<div class="col-md-6 mb-40">-->
                            <#--<div class="title-wrap bottom-line bottom-line--blue">-->
                                <#--<h3 class="section-title section-title--sm">Business</h3>-->
                            <#--</div>-->
                            <#--<article class="entry">-->
                                <#--<div class="entry__img-holder">-->
                                    <#--<a href="single-post.html">-->
                                        <#--<div class="thumb-container thumb-75">-->
                                            <#--<img data-src="img/blog/grid_post_img_6.jpg" src="img/empty.png"-->
                                                 <#--class="entry__img lazyload" alt=""/>-->
                                        <#--</div>-->
                                    <#--</a>-->
                                <#--</div>-->

                                <#--<div class="entry__body">-->
                                    <#--<div class="entry__header">-->
                                        <#--<h2 class="entry__title">-->
                                            <#--<a href="single-post.html">7 Ways Ecommerce Businesses Can Prevent Holiday-->
                                                <#--Fraud</a>-->
                                        <#--</h2>-->
                                        <#--<ul class="entry__meta">-->
                                            <#--<li class="entry__meta-author">-->
                                                <#--<i class="ui-author"></i>-->
                                                <#--<a href="#">DeoThemes</a>-->
                                            <#--</li>-->
                                            <#--<li class="entry__meta-date">-->
                                                <#--<i class="ui-date"></i>-->
                                                <#--21 October, 2017-->
                                            <#--</li>-->
                                            <#--<li class="entry__meta-comments">-->
                                                <#--<i class="ui-comments"></i>-->
                                                <#--<a href="#">115</a>-->
                                            <#--</li>-->
                                        <#--</ul>-->
                                    <#--</div>-->
                                    <#--<div class="entry__excerpt">-->
                                        <#--<p>Class aptent taciti sociosqu ad litora torquent per conubia nostra, per-->
                                            <#--inceptos. Lorem ipsum dolor sit amet, consectetur adipiscing elit...</p>-->
                                    <#--</div>-->
                                <#--</div>-->
                            <#--</article>-->
                            <#--<ul class="post-list-small post-list-small--border-top">-->
                                <#--<li class="post-list-small__item">-->
                                    <#--<article class="post-list-small__entry">-->
                                        <#--<div class="post-list-small__body">-->
                                            <#--<h3 class="post-list-small__entry-title">-->
                                                <#--<a href="single-post.html">What Indie Beauty Companies Can Teach-->
                                                    <#--Entrepreneurs About Scaling</a>-->
                                            <#--</h3>-->
                                            <#--<ul class="entry__meta">-->
                                                <#--<li class="entry__meta-date">-->
                                                    <#--<i class="ui-date"></i>-->
                                                    <#--21 October, 2017-->
                                                <#--</li>-->
                                            <#--</ul>-->
                                        <#--</div>-->
                                    <#--</article>-->
                                <#--</li>-->
                                <#--<li class="post-list-small__item">-->
                                    <#--<article class="post-list-small__entry">-->
                                        <#--<div class="post-list-small__body">-->
                                            <#--<h3 class="post-list-small__entry-title">-->
                                                <#--<a href="single-post.html">Bill Gates's 5 Favorite Books of 2017</a>-->
                                            <#--</h3>-->
                                            <#--<ul class="entry__meta">-->
                                                <#--<li class="entry__meta-date">-->
                                                    <#--<i class="ui-date"></i>-->
                                                    <#--21 October, 2017-->
                                                <#--</li>-->
                                            <#--</ul>-->
                                        <#--</div>-->
                                    <#--</article>-->
                                <#--</li>-->
                                <#--<li class="post-list-small__item">-->
                                    <#--<article class="post-list-small__entry">-->
                                        <#--<div class="post-list-small__body">-->
                                            <#--<h3 class="post-list-small__entry-title">-->
                                                <#--<a href="single-post.html">Sheryl Sandberg's 6 Steps to Make Sure-->
                                                    <#--Everyone Feels Safe at Work</a>-->
                                            <#--</h3>-->
                                            <#--<ul class="entry__meta">-->
                                                <#--<li class="entry__meta-date">-->
                                                    <#--<i class="ui-date"></i>-->
                                                    <#--21 October, 2017-->
                                                <#--</li>-->
                                            <#--</ul>-->
                                        <#--</div>-->
                                    <#--</article>-->
                                <#--</li>-->
                            <#--</ul>-->
                        <#--</div> <!-- end business &ndash;&gt;-->

                    <#--</div>-->
                <#--</section>-->
                <!-- end posts from categories -->

                <!-- Carousel posts -->
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
                                        <img data-src="${row.contentImgs?split(";")[0]}" src="/img/blog/carousel_img_1.jpg"
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

            </div> <!-- end posts -->

            <!-- Sidebar -->
            <aside class="col-lg-4 sidebar sidebar--right">
                    <#include "right.ftl">
            </aside> <!-- end sidebar -->

        </div> <!-- end content -->
    </div> <!-- end main container -->

    <!-- Footer -->
    <#include "footer.ftl">

</main> <!-- end main-wrapper -->


<!-- jQuery Scripts -->
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/easing.min.js"></script>
<script src="js/owl-carousel.min.js"></script>
<script src="js/twitterFetcher_min.js"></script>
<script src="js/jquery.newsTicker.min.js"></script>
<script src="js/modernizr.min.js"></script>
<script src="js/scripts.js"></script>
<script src="js/right.js"></script>
<script src="/js/advert.js"></script>
<script src="/js/statistic.js"></script>

</body>
</html>
