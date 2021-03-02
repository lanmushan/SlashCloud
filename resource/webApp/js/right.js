(function ($) {
    /*加载最新文章*/
    $.ajax({
        url: "/select/newArticle",
        data: {},
        type: "get",
        success: function (rows) {
            for (let row of rows) {
                let imgSrc = ""
                if (row.contentImgs != null) {
                    let imgs = row.contentImgs.split(";");
                    imgSrc = imgs.length > 0 ? imgs[0] : "";
                }
                let str = `<li class="post-list-small__item">
            <article class="post-list-small__entry clearfix">
                <div class="post-list-small__img-holder">
                    <div class="thumb-container thumb-75">
                        <a href="/detail/${row.id}.html">
                            <img data-src="${imgSrc}" src="/img/empty.png"
                                 alt="${row.contentTitle}" class=" lazyload">
                        </a>
                    </div>
                </div>
                <div class="post-list-small__body">
                    <h3 class="post-list-small__entry-title">
                        <a class="text-line-1" href="/detail/${row.id}.html">${row.contentTitle}</a>
                    </h3>
                     <span class="text-line-2">${row.contentDescription}</span>
                </div>
            </article>
        </li>`;
                $("#new_article").append(str);
            }
        }

    })
    $.ajax({
        url: "/select/updateArticleList",
        data: {},
        type: "get",
        success: function (rows) {
            console.log(rows);
            for (let row of rows) {
                let imgSrc = ""
                if (row.contentImgs != null) {
                    let imgs = row.contentImgs.split(";");
                    imgSrc = imgs.length > 0 ? imgs[0] : "";
                }
                let str = `<li class="post-list-small__item">
            <article class="post-list-small__entry clearfix">
                <div class="post-list-small__img-holder">
                    <div class="thumb-container thumb-75">
                        <a href="/detail/${row.id}.html">
                            <img data-src="${imgSrc}" src="/img/empty.png"
                                 alt="${row.contentTitle}" class=" lazyload">
                        </a>
                    </div>
                </div>
                <div class="post-list-small__body">
                    <h3 class="post-list-small__entry-title">
                        <a class="text-line-1" href="/detail/${row.id}.html">${row.contentTitle}</a>
                    </h3>
                     <span class="text-line-2">${row.contentDescription}</span>
                </div>
            </article>
        </li>`;
                $("#update_article").append(str);
            }
        }

    })

}($))