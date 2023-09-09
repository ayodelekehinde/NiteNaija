package github.cherrio.api

import io.ktor.client.engine.mock.*
import io.ktor.http.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertTrue


@OptIn(ExperimentalCoroutinesApi::class)
class ApiClientTest{

    @Test
    fun getMovie_returns_list() = runTest {
        val engine = MockEngine{
            respond(
                content = htmlData,
                status = HttpStatusCode.OK,
                headers = headersOf(HttpHeaders.ContentType, ContentType.Text.Html.toString())
            )
        }
        val client = MovieDataSource(engine)
        val expected = client.getMovies()
        assertTrue(expected.isNotEmpty())
    }

//    @Test
//    fun getDownloadUrl_return_validUrl() = runTest {
//        val engine = MockEngine{
//            respondRedirect(location = "url")
//        }
//        val client = MovieDataSource(engine)
//        val expected = client.getDownloadUrl("https://www.thenetnaija.net/videos/movies/17631-lady-chatterleys-lover-2022")
//        assertEquals(expected,"https://www.sabishare.com/file/GLahjFnOm39-scrooge-a-christmas-carol-2022-netnaija-com-mkv")
//    }
}


private val htmlData = """
    <!DOCTYPE html>
    <html lang="en">
        <head>
            <meta charset="utf-8">
            <meta name="theme-color" content="#044400">
            <meta name="viewport" content="width=device-width,initial-scale=1">
            <meta name="color-scheme" content="dark light">
            <link rel="stylesheet" media="all,handheld" href="https://www.thenetnaija.net/static/css/style.css?v=1.0.17">
                    <link rel="canonical" href="https://www.thenetnaija.net/videos/movies" itemprop="url">
                    <title>Movies  - Netnaija</title>
            <link
                rel="alternate"
                type="application/rss+xml"
                title="NetNaija Latest Uploads and Articles"
                href="https://feeds.feedburner.com/netnaija"
            />
            <meta property="fb:pages" content="1054723444645777,113042165519728,487375247957420" />
            <script src="/static/js/gan.js"></script>
            <script type="application/ld+json">
                {
                    "@context": "http://schema.org",
                    "@type": "WebSite",
                    "url": "https://www.thenetnaija.com/",
                    "potentialAction": {
                        "@type": "SearchAction",
                        "target": "https://www.thenetnaija.com/search?t={search_term_string}",
                        "query-input": "required name=search_term_string"
                    }
                }
            </script>
                                                <script data-cfasync="false" type="text/javascript" src="//spikscabrin.com/tsdp2WE18M5Vw/14818"></script>
                                                <!-- Google Tag Manager -->
            <script>(function(w,d,s,l,i){w[l]=w[l]||[];w[l].push({'gtm.start':
                  new Date().getTime(),event:'gtm.js'});var f=d.getElementsByTagName(s)[0],
                j=d.createElement(s),dl=l!=='dataLayer'?'&l='+l:'';j.async=true;j.src=
                'https://www.googletagmanager.com/gtm.js?id='+i+dl;f.parentNode.insertBefore(j,f);
              })(window,document,'script','dataLayer','GTM-TT3T7MN');</script>
            <!-- End Google Tag Manager -->
            <link rel="apple-touch-icon" sizes="180x180" href="/static/icons/apple-touch-icon.png">
            <link rel="icon" type="image/png" sizes="32x32" href="/static/icons/favicon-32x32.png">
            <link rel="icon" type="image/png" sizes="16x16" href="/static/icons/favicon-16x16.png">
            <link rel="manifest" href="/static/icons/site.webmanifest">
            <link rel="mask-icon" href="/static/icons/safari-pinned-tab.svg" color="#5bbad5">
            <link rel="shortcut icon" href="/static/icons/favicon.ico">
            <meta name="msapplication-TileColor" content="#ffc40d">
            <meta name="msapplication-config" content="/static/icons/browserconfig.xml">
        </head>
        <body>
        <div id="app">
                    
                            <div class="cmp-list header-cmp">
                
        <div class="cmp-one">
                        <div data-nn-cmp="Hallway Leaderboard 1"><div><script type="application/json" class="async-json-js">{"src":"https://hallway.netnaija.app/deliver.1.5.js"}</script><div class="hallway-ins" data-format="auto" data-site="394765639421661184" data-slot="401273800739655680"></div></div></div>
                </div>

                
        <div class="cmp-one">
                        <div data-nn-cmp="Hallway Leaderboard 2"><div><script type="application/json" class="async-json-js">{"src":"https://hallway.netnaija.app/deliver.1.5.js"}</script><div class="hallway-ins" data-format="auto" data-site="394765639421661184" data-slot="401273800739655680"></div></div></div>
                </div>

                
        <div class="cmp-one">
                        <div data-nn-cmp="Hallway Leaderboard Static 1"><div><script type="application/json" class="async-json-js">{"src":"https://hallway.netnaija.app/deliver.1.5.js"}</script><div class="hallway-ins" data-format="auto" data-site="394765639421661184" data-slot="425485528511680512"></div></div></div>
                </div>

            </div>

            <header class="app-header" id="app-header">
                <div class="app-header-main">
                    <div class="inner" id="ahi-main">
                        <div class="drawer-toggle">
                            <button
                                class="btn"
                                id="drawer-t-1"
                            >☰</button>
                        </div>
                        <div class="logo">
                                                    <a href="https://www.thenetnaija.net/">
                                <img src="https://www.thenetnaija.net/static/img/logo.silver.png" alt="Netnaija">
                                                        </a>
                                                </div>
                        <div class="navigation">
                                                        <ul class="app-header-nav nav justify-content-end">
                                                                <li class="nav-item">
                                        <a href="https://www.thenetnaija.net/posts" class="nav-link">Posts</a>
                                    </li>
                                                                <li class="nav-item">
                                        <a href="https://www.thenetnaija.net/videos" class="nav-link">Videos</a>
                                    </li>
                                                                <li class="nav-item">
                                        <a href="https://www.thenetnaija.net/music" class="nav-link">Music</a>
                                    </li>
                                                                <li class="nav-item">
                                        <a href="https://www.thenetnaija.net/docs/contact" class="nav-link">Contact</a>
                                    </li>
                                                                <li class="nav-item">
                                        <a href="https://www.thenetnaija.net/docs/advertise" class="nav-link">Advertise</a>
                                    </li>
                                                            </ul>
                                                </div>
                        <div class="search">
                            <a
                                href="/search"
                                id="search-anchor"
                                class="btn"
                            >
                                <i class="fas fa-search"></i>
                            </a>
                        </div>
                    </div>
                                    <div class="inner-alt" id="ahi-alt">
                        <div class="drawer-toggle">
                            <button
                                class="btn"
                                id="drawer-t-2"
                            >☰</button>
                        </div>
                        <div class="logo">
                            <a href="https://www.thenetnaija.net/">
                                <img src="/static/img/icon.white.png?1" alt="Netnaija">
                                                        </a>
                        </div>
                        <div class="navigation">
                            <div class="ahi-title-con">
                                <div class="ahi-title">
                                    Movies 
                                </div>
                            </div>
                        </div>
                        <div class="search">
                            <a
                                href="/search"
                                id="search-anchor-1"
                                class="btn"
                            >
                                <i class="fas fa-search"></i>
                            </a>
                        </div>
                    </div>
                                    <form
                        class="app-header-search"
                        id="ahs-form"
                        action="https://www.thenetnaija.net/search"
                        method="get"
                    >
                        <div class="input-group">
                            <input
                                type="text"
                                class="form-control rounded-0"
                                placeholder="Search..."
                                title="Search..."
                                name="t"
                                id="ahs-i-text"
                            >
                            <div class="input-group-append">
                                <button
                                    class="btn btn-secondary rounded-0"
                                    type="submit"
                                ><i class="fas fa-search"></i></button>
                            </div>
                        </div>
                    </form>
                </div>
            </header>
            <nav id="app-drawer">
                <div class="d-flex h-100">
                    <ul class="app-drawer-links">
                                                <li class="adl-entry">
                                <a
                                    href="https://www.thenetnaija.net/"
                                    class="adl-one"
                                >
                                    <span class="drawer-icon" title="Home">
                                        <i class="fas fa-home"></i>
                                    </span>
                                    <span class="drawer-title flex-grow-1">Home</span>
                                                                </a></li>
                                                <li class="adl-entry">
                                <a
                                    href="https://www.thenetnaija.net/posts"
                                    class="adl-one"
                                >
                                    <span class="drawer-icon" title="Posts">
                                        <i class="fas fa-newspaper"></i>
                                    </span>
                                    <span class="drawer-title flex-grow-1">Posts</span>
                                                                        <button class="btn adl-toggle">
                                            <i class="fas fa-angle-down"></i>
                                        </button>
                                                                </a>                                <ul>
                    <li class="">
            <a href="https://www.thenetnaija.net/posts/general">
                <span>General</span>
                            <button class="btn adl-toggle">
                    <i class="fas fa-angle-down"></i>
                </button>
                        </a><ul>
                                <li class="no-child">
            <a href="https://www.thenetnaija.net/posts/general/general">
                <span>General</span>
                        </a></li>
                                <li class="no-child">
            <a href="https://www.thenetnaija.net/posts/general/gambling">
                <span>Gambling</span>
                        </a></li>
                                <li class="no-child">
            <a href="https://www.thenetnaija.net/posts/general/religion">
                <span>Religion</span>
                        </a></li>
                                <li class="no-child">
            <a href="https://www.thenetnaija.net/posts/general/polls-debate">
                <span>Polls / Debates</span>
                        </a></li>
                                <li class="no-child">
            <a href="https://www.thenetnaija.net/posts/general/jobs">
                <span>Jobs/Vacancies</span>
                        </a></li>
                                <li class="no-child">
            <a href="https://www.thenetnaija.net/posts/general/trash">
                <span>Trash</span>
                        </a></li>
        </ul></li>
                    <li class="">
            <a href="https://www.thenetnaija.net/posts/news">
                <span>News</span>
                            <button class="btn adl-toggle">
                    <i class="fas fa-angle-down"></i>
                </button>
                        </a><ul>
                                <li class="no-child">
            <a href="https://www.thenetnaija.net/posts/news/bbnaija">
                <span>BBNaija 2022</span>
                        </a></li>
                                <li class="no-child">
            <a href="https://www.thenetnaija.net/posts/news/politics">
                <span>Politics</span>
                        </a></li>
                                <li class="no-child">
            <a href="https://www.thenetnaija.net/posts/news/celebrities">
                <span>Celebrity Gist</span>
                        </a></li>
                                <li class="no-child">
            <a href="https://www.thenetnaija.net/posts/news/sports">
                <span>Sports</span>
                        </a></li>
                                <li class="no-child">
            <a href="https://www.thenetnaija.net/posts/news/social-media">
                <span>Social Media</span>
                        </a></li>
                                <li class="no-child">
            <a href="https://www.thenetnaija.net/posts/news/world">
                <span>World</span>
                        </a></li>
                                <li class="no-child">
            <a href="https://www.thenetnaija.net/posts/news/tech">
                <span>Technology</span>
                        </a></li>
                                <li class="no-child">
            <a href="https://www.thenetnaija.net/posts/news/education">
                <span>Education News</span>
                        </a></li>
                                <li class="no-child">
            <a href="https://www.thenetnaija.net/posts/news/videos">
                <span>Videos &amp; Movies</span>
                        </a></li>
                                <li class="no-child">
            <a href="https://www.thenetnaija.net/posts/news/crime">
                <span>Crime</span>
                        </a></li>
                                <li class="no-child">
            <a href="https://www.thenetnaija.net/posts/news/happenings">
                <span>Happenings</span>
                        </a></li>
                                <li class="no-child">
            <a href="https://www.thenetnaija.net/posts/news/governance">
                <span>Governance</span>
                        </a></li>
        </ul></li>
                    <li class="">
            <a href="https://www.thenetnaija.net/posts/entertainment">
                <span>Entertainment</span>
                            <button class="btn adl-toggle">
                    <i class="fas fa-angle-down"></i>
                </button>
                        </a><ul>
                                <li class="no-child">
            <a href="https://www.thenetnaija.net/posts/entertainment/gaming">
                <span>Gaming</span>
                        </a></li>
                                <li class="no-child">
            <a href="https://www.thenetnaija.net/posts/entertainment/jokes-riddles">
                <span>Jokes - Riddles</span>
                        </a></li>
                                <li class="no-child">
            <a href="https://www.thenetnaija.net/posts/entertainment/music">
                <span>Music</span>
                        </a></li>
                                <li class="no-child">
            <a href="https://www.thenetnaija.net/posts/entertainment/lyrics">
                <span>Lyrics</span>
                        </a></li>
                                <li class="no-child">
            <a href="https://www.thenetnaija.net/posts/entertainment/events">
                <span>Events</span>
                        </a></li>
        </ul></li>
                    <li class="">
            <a href="https://www.thenetnaija.net/posts/tech">
                <span>Technology</span>
                            <button class="btn adl-toggle">
                    <i class="fas fa-angle-down"></i>
                </button>
                        </a><ul>
                                <li class="no-child">
            <a href="https://www.thenetnaija.net/posts/tech/general">
                <span>General / FBT</span>
                        </a></li>
                                <li class="no-child">
            <a href="https://www.thenetnaija.net/posts/tech/computers">
                <span>Computers</span>
                        </a></li>
                                <li class="no-child">
            <a href="https://www.thenetnaija.net/posts/tech/tv-decoders">
                <span>TV Decoders</span>
                        </a></li>
                                <li class="no-child">
            <a href="https://www.thenetnaija.net/posts/tech/mobile">
                <span>Mobile</span>
                        </a></li>
        </ul></li>
                    <li class="">
            <a href="https://www.thenetnaija.net/posts/life">
                <span>Life</span>
                            <button class="btn adl-toggle">
                    <i class="fas fa-angle-down"></i>
                </button>
                        </a><ul>
                                <li class="no-child">
            <a href="https://www.thenetnaija.net/posts/life/health">
                <span>Health</span>
                        </a></li>
                                <li class="no-child">
            <a href="https://www.thenetnaija.net/posts/life/romance">
                <span>Romance</span>
                        </a></li>
                                <li class="no-child">
            <a href="https://www.thenetnaija.net/posts/life/lifestyle">
                <span>Lifestyle</span>
                        </a></li>
                                <li class="no-child">
            <a href="https://www.thenetnaija.net/posts/life/travel">
                <span>Travel</span>
                        </a></li>
                                <li class="no-child">
            <a href="https://www.thenetnaija.net/posts/life/fashion">
                <span>Fashion</span>
                        </a></li>
                                <li class="no-child">
            <a href="https://www.thenetnaija.net/posts/life/sexuality">
                <span>Sexuality</span>
                        </a></li>
        </ul></li>
                    <li class="">
            <a href="https://www.thenetnaija.net/posts/education">
                <span>Education</span>
                            <button class="btn adl-toggle">
                    <i class="fas fa-angle-down"></i>
                </button>
                        </a><ul>
                                <li class="no-child">
            <a href="https://www.thenetnaija.net/posts/education/exams">
                <span>Examinations</span>
                        </a></li>
                                <li class="no-child">
            <a href="https://www.thenetnaija.net/posts/education/utme-ume">
                <span>UTME/Post-UTME</span>
                        </a></li>
                    <li class="">
            <a href="https://www.thenetnaija.net/posts/education/e-Learning">
                <span>e-Learning</span>
                            <button class="btn adl-toggle">
                    <i class="fas fa-angle-down"></i>
                </button>
                        </a><ul>
                                <li class="no-child">
            <a href="https://www.thenetnaija.net/posts/education/english">
                <span>English</span>
                        </a></li>
        </ul></li>
        </ul></li>
                    <li class="">
            <a href="https://www.thenetnaija.net/posts/web-dev">
                <span>Web Development</span>
                            <button class="btn adl-toggle">
                    <i class="fas fa-angle-down"></i>
                </button>
                        </a><ul>
                                <li class="no-child">
            <a href="https://www.thenetnaija.net/posts/web-dev/html">
                <span>HTML XHTML</span>
                        </a></li>
                    <li class="">
            <a href="https://www.thenetnaija.net/posts/web-dev/php">
                <span>PHP</span>
                            <button class="btn adl-toggle">
                    <i class="fas fa-angle-down"></i>
                </button>
                        </a><ul>
                                <li class="no-child">
            <a href="https://www.thenetnaija.net/posts/web-dev/phpsnippets">
                <span>Snippets</span>
                        </a></li>
        </ul></li>
                                <li class="no-child">
            <a href="https://www.thenetnaija.net/posts/web-dev/css">
                <span>CSS</span>
                        </a></li>
                                <li class="no-child">
            <a href="https://www.thenetnaija.net/posts/web-dev/graphics">
                <span>Grafix</span>
                        </a></li>
                                <li class="no-child">
            <a href="https://www.thenetnaija.net/posts/web-dev/netnews">
                <span>Net News</span>
                        </a></li>
                                <li class="no-child">
            <a href="https://www.thenetnaija.net/posts/web-dev/seo">
                <span>SEO</span>
                        </a></li>
        </ul></li>
                    <li class="">
            <a href="https://www.thenetnaija.net/posts/finance">
                <span>Finance</span>
                            <button class="btn adl-toggle">
                    <i class="fas fa-angle-down"></i>
                </button>
                        </a><ul>
                                <li class="no-child">
            <a href="https://www.thenetnaija.net/posts/finance/business">
                <span>Business News</span>
                        </a></li>
                                <li class="no-child">
            <a href="https://www.thenetnaija.net/posts/finance/loan">
                <span>Loan</span>
                        </a></li>
        </ul></li>
        </ul></li>
                                                <li class="adl-entry">
                                <a
                                    href="https://www.thenetnaija.net/music"
                                    class="adl-one"
                                >
                                    <span class="drawer-icon" title="Music">
                                        <i class="fas fa-music"></i>
                                    </span>
                                    <span class="drawer-title flex-grow-1">Music</span>
                                                                        <button class="btn adl-toggle">
                                            <i class="fas fa-angle-down"></i>
                                        </button>
                                                                </a>                                <ul>
                                <li class="no-child">
            <a href="https://www.thenetnaija.net/music/afro">
                <span>Nigerian / African Music</span>
                        </a></li>
                                <li class="no-child">
            <a href="https://www.thenetnaija.net/music/gospel">
                <span>Gospel Music</span>
                        </a></li>
                                <li class="no-child">
            <a href="https://www.thenetnaija.net/music/upcoming">
                <span>Upcoming Artists Music</span>
                        </a></li>
                                <li class="no-child">
            <a href="https://www.thenetnaija.net/music/blues">
                <span>Blues</span>
                        </a></li>
                                <li class="no-child">
            <a href="https://www.thenetnaija.net/music/hip-hop">
                <span>Foreign Music</span>
                        </a></li>
                                <li class="no-child">
            <a href="https://www.thenetnaija.net/music/old-skool">
                <span>Highlife / Old School</span>
                        </a></li>
                                <li class="no-child">
            <a href="https://www.thenetnaija.net/music/instrumentals">
                <span>Instrumentals</span>
                        </a></li>
                                <li class="no-child">
            <a href="https://www.thenetnaija.net/music/mixtapes">
                <span>DJ Mixtapes</span>
                        </a></li>
                                <li class="no-child">
            <a href="https://www.thenetnaija.net/music/sermons">
                <span>Religious Sermons</span>
                        </a></li>
                                <li class="no-child">
            <a href="https://www.thenetnaija.net/music/comedy">
                <span>Audio Comedy</span>
                        </a></li>
        </ul></li>
                                                <li class="adl-entry">
                                <a
                                    href="https://www.thenetnaija.net/videos"
                                    class="adl-one"
                                >
                                    <span class="drawer-icon" title="Videos">
                                        <i class="fas fa-video"></i>
                                    </span>
                                    <span class="drawer-title flex-grow-1">Videos</span>
                                                                        <button class="btn adl-toggle">
                                            <i class="fas fa-angle-down"></i>
                                        </button>
                                                                </a>                                <ul>
                                <li class="no-child">
            <a href="https://www.thenetnaija.net/videos/comedy">
                <span>Comedy Videos</span>
                        </a></li>
                                <li class="no-child">
            <a href="https://www.thenetnaija.net/videos/music">
                <span>Music Videos</span>
                        </a></li>
                                <li class="no-child">
            <a href="https://www.thenetnaija.net/videos/foreign-music">
                <span>Foreign Music Videos</span>
                        </a></li>
                                <li class="no-child">
            <a href="https://www.thenetnaija.net/videos/tech-gadgets">
                <span>Tech Videos</span>
                        </a></li>
                                <li class="no-child">
            <a href="https://www.thenetnaija.net/videos/gospel">
                <span>Gospel Videos</span>
                        </a></li>
                                <li class="no-child">
            <a href="https://www.thenetnaija.net/videos/movies">
                <span>Movies</span>
                        </a></li>
                                <li class="no-child">
            <a href="https://www.thenetnaija.net/videos/nollywood">
                <span>Nollywood Movies</span>
                        </a></li>
                                <li class="no-child">
            <a href="https://www.thenetnaija.net/videos/sports">
                <span>Sports Videos</span>
                        </a></li>
                                <li class="no-child">
            <a href="https://www.thenetnaija.net/videos/news">
                <span>Video News</span>
                        </a></li>
                                <li class="no-child">
            <a href="https://www.thenetnaija.net/videos/entertainment-videos">
                <span>Entertainment Videos</span>
                        </a></li>
                                <li class="no-child">
            <a href="https://www.thenetnaija.net/videos/yoruba">
                <span>Yoruba Nollywood</span>
                        </a></li>
                                <li class="no-child">
            <a href="https://www.thenetnaija.net/videos/series">
                <span>Series</span>
                        </a></li>
                                <li class="no-child">
            <a href="https://www.thenetnaija.net/videos/kdrama">
                <span>Korean Series</span>
                        </a></li>
                                <li class="no-child">
            <a href="https://www.thenetnaija.net/videos/pro-wrestling">
                <span>Pro Wrestling</span>
                        </a></li>
        </ul></li>
                                                <li class="adl-entry">
                                <a
                                    href="https://www.thenetnaija.net/trends"
                                    class="adl-one"
                                >
                                    <span class="drawer-icon" title="Trending">
                                        <i class="fas fa-chart-line"></i>
                                    </span>
                                    <span class="drawer-title flex-grow-1">Trending</span>
                                                                </a></li>
                                                <li class="adl-entry">
                                <a
                                    href="https://www.thenetnaija.net/docs/contact"
                                    class="adl-one"
                                >
                                    <span class="drawer-icon" title="Contact Us">
                                        <i class="fas fa-envelope"></i>
                                    </span>
                                    <span class="drawer-title flex-grow-1">Contact Us</span>
                                                                </a></li>
                                                <li class="adl-entry">
                                <a
                                    href="https://www.thenetnaija.net/search"
                                    class="adl-one"
                                >
                                    <span class="drawer-icon" title="Search">
                                        <i class="fas fa-search"></i>
                                    </span>
                                    <span class="drawer-title flex-grow-1">Search</span>
                                                                </a></li>
                                        </ul>
                </div>
            </nav>
                    <div id="content">            <div class="content-width">
                    <div class="content-spacer">
                            

        <div class="content-grid">
            <div class="content-main">
                            
    <nav class="breadcrumb-con">
        <ol itemscope itemtype="http://schema.org/BreadcrumbList" class="breadcrumb">
                                                                <li itemprop="itemListElement" itemscope
                    itemtype="http://schema.org/ListItem"
                    class="breadcrumb-item"
                >
                                    <a itemprop="item" href="https://www.thenetnaija.net/">
                        <span itemprop="name">Home</span></a>
                                    <meta itemprop="position" content="1" />
                </li>
                                                                <li itemprop="itemListElement" itemscope
                    itemtype="http://schema.org/ListItem"
                    class="breadcrumb-item"
                >
                                    <a itemprop="item" href="https://www.thenetnaija.net/videos">
                        <span itemprop="name">Videos</span></a>
                                    <meta itemprop="position" content="2" />
                </li>
                                                                <li itemprop="itemListElement" itemscope
                    itemtype="http://schema.org/ListItem"
                    class="breadcrumb-item active"
                >
                                        <span itemprop="name">Movies</span>
                                    <meta itemprop="position" content="3" />
                </li>
                </ol>
    </nav>

            <h1 class="page-h1">Movies</h1>
                
        <div class="goto-jump">
        <div class="inner">
          <div class="mx-2">Go to</div>
          <div class="flex-grow-1">
            <select
                id="goto-jump"
                class="form-control"
                title=""
            >
              <option
                value="https://www.thenetnaija.net/videos"
              >All Categories</option>
                        
      <option
        value="https://www.thenetnaija.net/videos/comedy"
    >
      Comedy Videos
    </option>

      <option
        value="https://www.thenetnaija.net/videos/music"
    >
      Music Videos
    </option>

      <option
        value="https://www.thenetnaija.net/videos/foreign-music"
    >
      Foreign Music Videos
    </option>

      <option
        value="https://www.thenetnaija.net/videos/tech-gadgets"
    >
      Tech Videos
    </option>

      <option
        value="https://www.thenetnaija.net/videos/gospel"
    >
      Gospel Videos
    </option>

        <option
        value="https://www.thenetnaija.net/videos/movies" selected="selected"
    >
      Movies
    </option>

      <option
        value="https://www.thenetnaija.net/videos/nollywood"
    >
      Nollywood Movies
    </option>

      <option
        value="https://www.thenetnaija.net/videos/sports"
    >
      Sports Videos
    </option>

      <option
        value="https://www.thenetnaija.net/videos/news"
    >
      Video News
    </option>

      <option
        value="https://www.thenetnaija.net/videos/entertainment-videos"
    >
      Entertainment Videos
    </option>

      <option
        value="https://www.thenetnaija.net/videos/yoruba"
    >
      Yoruba Nollywood
    </option>

      <option
        value="https://www.thenetnaija.net/videos/series"
    >
      Series
    </option>

      <option
        value="https://www.thenetnaija.net/videos/kdrama"
    >
      Korean Series
    </option>

      <option
        value="https://www.thenetnaija.net/videos/pro-wrestling"
    >
      Pro Wrestling
    </option>


            </select>
          </div>
        </div>
      </div>

                
    <div class="video-files">
                    <article class="file-one shadow">
                <div class="thumbnail">
                                        <img src="https://static.netnaija.com/i/gVpagBr57wO.jpg" alt="Diary of a Wimpy Kid: Rodrick Rules (2022)" title="Diary of a Wimpy Kid: Rodrick Rules (2022)">
                                </div>
                <div class="info">
                                    <h2>
                        <a href="https://www.thenetnaija.net/videos/movies/17633-diary-of-a-wimpy-kid-rodrick-rules-2022">Diary of a Wimpy Kid: Rodrick Rules (2022)</a>
                    </h2>
                    <div class="meta">
                        <div class="inner">
                            <span title="2022-12-02T14:32:57+01:00">
                                <i class="fas fa-calendar-alt"></i> Yesterday 2:32pm
                            </span>
                                <span>
                                <i class="fas fa-comment-alt"></i> 10
                            </span>
                        </div>
                    </div>
                </div>
            </article>
                        <article class="file-one shadow">
                <div class="thumbnail">
                                        <img src="https://static.netnaija.com/i/qvg702moNze.jpg" alt="Lady Chatterley&#039;s Lover (2022)" title="Lady Chatterley&#039;s Lover (2022)">
                                </div>
                <div class="info">
                                    <h2>
                        <a href="https://www.thenetnaija.net/videos/movies/17631-lady-chatterleys-lover-2022">Lady Chatterley&#039;s Lover (2022)</a>
                    </h2>
                    <div class="meta">
                        <div class="inner">
                            <span title="2022-12-02T11:33:47+01:00">
                                <i class="fas fa-calendar-alt"></i> Yesterday 11:33am
                            </span>
                                <span>
                                <i class="fas fa-comment-alt"></i> 14
                            </span>
                        </div>
                    </div>
                </div>
            </article>
                        <article class="file-one shadow">
                <div class="thumbnail">
                                        <img src="https://static.netnaija.com/i/ZOoanDAeKjA.jpg" alt="Scrooge: A Christmas Carol (2022)" title="Scrooge: A Christmas Carol (2022)">
                                </div>
                <div class="info">
                                    <h2>
                        <a href="https://www.thenetnaija.net/videos/movies/17630-scrooge-a-christmas-carol-2022">Scrooge: A Christmas Carol (2022)</a>
                    </h2>
                    <div class="meta">
                        <div class="inner">
                            <span title="2022-12-02T11:28:00+01:00">
                                <i class="fas fa-calendar-alt"></i> Yesterday 11:28am
                            </span>
                                <span>
                                <i class="fas fa-comment-alt"></i> 18
                            </span>
                        </div>
                    </div>
                </div>
            </article>
                                    
    <div class="feed-cmp">
      
        <div class="cmp-one">
                        <div data-nn-cmp="Galaksion In-Feed"><!-- <script data-cfasync="false" type="text/javascript" src="//lamcooppub.com/taERV9rroj0/14818"></script> -->
    <div id="first_cpm_13860"></div></div>
                </div>

    </div>

                        <article class="file-one shadow">
                <div class="thumbnail">
                                        <img src="https://static.netnaija.com/i/mr9KY8MmNq6.jpg" alt="Christmas with the Campbells (2022)" title="Christmas with the Campbells (2022)">
                                </div>
                <div class="info">
                                    <h2>
                        <a href="https://www.thenetnaija.net/videos/movies/17629-christmas-with-the-campbells-2022">Christmas with the Campbells (2022)</a>
                    </h2>
                    <div class="meta">
                        <div class="inner">
                            <span title="2022-12-02T11:22:55+01:00">
                                <i class="fas fa-calendar-alt"></i> Yesterday 11:22am
                            </span>
                                <span>
                                <i class="fas fa-comment-alt"></i> 2
                            </span>
                        </div>
                    </div>
                </div>
            </article>
                        <article class="file-one shadow">
                <div class="thumbnail">
                                        <img src="https://static.netnaija.com/i/g3M73X8La9y.jpg" alt="Warriors of Future (2022) [Chinese]" title="Warriors of Future (2022) [Chinese]">
                                </div>
                <div class="info">
                                    <h2>
                        <a href="https://www.thenetnaija.net/videos/movies/17628-warriors-of-future-2022-chinese">Warriors of Future (2022) [Chinese]</a>
                    </h2>
                    <div class="meta">
                        <div class="inner">
                            <span title="2022-12-02T11:13:42+01:00">
                                <i class="fas fa-calendar-alt"></i> Yesterday 11:13am
                            </span>
                                <span>
                                <i class="fas fa-comment-alt"></i> 47
                            </span>
                        </div>
                    </div>
                </div>
            </article>
                        <article class="file-one shadow">
                <div class="thumbnail">
                                        <img src="https://static.netnaija.com/i/DpraRrOEaAo.jpg" alt="A Wonderful Time of the Year (2022)" title="A Wonderful Time of the Year (2022)">
                                </div>
                <div class="info">
                                    <h2>
                        <a href="https://www.thenetnaija.net/videos/movies/17627-a-wonderful-time-of-the-year-2022">A Wonderful Time of the Year (2022)</a>
                    </h2>
                    <div class="meta">
                        <div class="inner">
                            <span title="2022-12-02T09:40:23+01:00">
                                <i class="fas fa-calendar-alt"></i> Yesterday 9:40am
                            </span>
                                <span>
                                <i class="fas fa-comment-alt"></i> 4
                            </span>
                        </div>
                    </div>
                </div>
            </article>
                                    
    <div class="feed-cmp">
      
        <div class="cmp-one">
                        <div data-nn-cmp="Hallway Feed-000"><div><script type="application/json" class="async-json-js">{"src":"https://hallway.netnaija.app/deliver.1.5.js"}</script><div class="hallway-ins" data-format="auto" data-site="394765639421661184" data-slot="394817743200849920"></div></div></div>
                </div>

    </div>

                        <article class="file-one shadow">
                <div class="thumbnail">
                                        <img src="https://static.netnaija.com/i/PrO7OOpb7yp.jpg" alt="Your Christmas Or Mine? (2022)" title="Your Christmas Or Mine? (2022)">
                                </div>
                <div class="info">
                                    <h2>
                        <a href="https://www.thenetnaija.net/videos/movies/17626-your-christmas-or-mine-2022">Your Christmas Or Mine? (2022)</a>
                    </h2>
                    <div class="meta">
                        <div class="inner">
                            <span title="2022-12-02T08:14:53+01:00">
                                <i class="fas fa-calendar-alt"></i> Yesterday 8:14am
                            </span>
                                <span>
                                <i class="fas fa-comment-alt"></i> 3
                            </span>
                        </div>
                    </div>
                </div>
            </article>
                        <article class="file-one shadow">
                <div class="thumbnail">
                                        <img src="https://static.netnaija.com/i/3g8NBomeKDR.jpg" alt="Darby and the Dead (2022)" title="Darby and the Dead (2022)">
                                </div>
                <div class="info">
                                    <h2>
                        <a href="https://www.thenetnaija.net/videos/movies/17625-darby-and-the-dead-2022">Darby and the Dead (2022)</a>
                    </h2>
                    <div class="meta">
                        <div class="inner">
                            <span title="2022-12-02T08:06:19+01:00">
                                <i class="fas fa-calendar-alt"></i> Yesterday 8:06am
                            </span>
                                <span>
                                <i class="fas fa-comment-alt"></i> 34
                            </span>
                        </div>
                    </div>
                </div>
            </article>
                        <article class="file-one shadow">
                <div class="thumbnail">
                                        <img src="https://static.netnaija.com/i/8yZaJEl97J0.jpg" alt="Troll (2022) [Norwegian]" title="Troll (2022) [Norwegian]">
                                </div>
                <div class="info">
                                    <h2>
                        <a href="https://www.thenetnaija.net/videos/movies/17623-troll-2022-norwegian">Troll (2022) [Norwegian]</a>
                    </h2>
                    <div class="meta">
                        <div class="inner">
                            <span title="2022-12-01T20:35:21+01:00">
                                <i class="fas fa-calendar-alt"></i> Dec 01
                            </span>
                                <span>
                                <i class="fas fa-comment-alt"></i> 38
                            </span>
                        </div>
                    </div>
                </div>
            </article>
                        <article class="file-one shadow">
                <div class="thumbnail">
                                        <img src="https://static.netnaija.com/i/no4NXP8nN9M.jpg" alt="Chup (2022) [Indian]" title="Chup (2022) [Indian]">
                                </div>
                <div class="info">
                                    <h2>
                        <a href="https://www.thenetnaija.net/videos/movies/17622-chup-2022-indian">Chup (2022) [Indian]</a>
                    </h2>
                    <div class="meta">
                        <div class="inner">
                            <span title="2022-12-01T20:28:49+01:00">
                                <i class="fas fa-calendar-alt"></i> Dec 01
                            </span>
                                <span>
                                <i class="fas fa-comment-alt"></i> 6
                            </span>
                        </div>
                    </div>
                </div>
            </article>
                        <article class="file-one shadow">
                <div class="thumbnail">
                                        <img src="https://static.netnaija.com/i/W4lNmgxY7Ro.jpg" alt="Remember Me (2022) [Korean]" title="Remember Me (2022) [Korean]">
                                </div>
                <div class="info">
                                    <h2>
                        <a href="https://www.thenetnaija.net/videos/movies/17621-remember-me-2022-korean">Remember Me (2022) [Korean]</a>
                    </h2>
                    <div class="meta">
                        <div class="inner">
                            <span title="2022-12-01T16:35:05+01:00">
                                <i class="fas fa-calendar-alt"></i> Dec 01
                            </span>
                                <span>
                                <i class="fas fa-comment-alt"></i> 13
                            </span>
                        </div>
                    </div>
                </div>
            </article>
                        <article class="file-one shadow">
                <div class="thumbnail">
                                        <img src="https://static.netnaija.com/i/AqgKGY63ar1.jpg" alt="Krishna Vrinda Vihari (2022) [Indian]" title="Krishna Vrinda Vihari (2022) [Indian]">
                                </div>
                <div class="info">
                                    <h2>
                        <a href="https://www.thenetnaija.net/videos/movies/17620-krishna-vrinda-vihari-2022-indian">Krishna Vrinda Vihari (2022) [Indian]</a>
                    </h2>
                    <div class="meta">
                        <div class="inner">
                            <span title="2022-12-01T16:22:36+01:00">
                                <i class="fas fa-calendar-alt"></i> Dec 01
                            </span>
                                <span>
                                <i class="fas fa-comment-alt"></i> 6
                            </span>
                        </div>
                    </div>
                </div>
            </article>
                        <article class="file-one shadow">
                <div class="thumbnail">
                                        <img src="https://static.netnaija.com/i/Gem7Q4r4KYx.jpg" alt="The Snowball Effect (2022)" title="The Snowball Effect (2022)">
                                </div>
                <div class="info">
                                    <h2>
                        <a href="https://www.thenetnaija.net/videos/movies/17619-the-snowball-effect-2022">The Snowball Effect (2022)</a>
                    </h2>
                    <div class="meta">
                        <div class="inner">
                            <span title="2022-12-01T16:12:55+01:00">
                                <i class="fas fa-calendar-alt"></i> Dec 01
                            </span>
                                <span>
                                <i class="fas fa-comment-alt"></i> 1
                            </span>
                        </div>
                    </div>
                </div>
            </article>
                        <article class="file-one shadow">
                <div class="thumbnail">
                                        <img src="https://static.netnaija.com/i/deYakr1k7P3.jpg" alt="Prince (2022) [Indian]" title="Prince (2022) [Indian]">
                                </div>
                <div class="info">
                                    <h2>
                        <a href="https://www.thenetnaija.net/videos/movies/17618-prince-2022-indian">Prince (2022) [Indian]</a>
                    </h2>
                    <div class="meta">
                        <div class="inner">
                            <span title="2022-12-01T16:07:49+01:00">
                                <i class="fas fa-calendar-alt"></i> Dec 01
                            </span>
                                <span>
                                <i class="fas fa-comment-alt"></i> 4
                            </span>
                        </div>
                    </div>
                </div>
            </article>
                        <article class="file-one shadow">
                <div class="thumbnail">
                                        <img src="https://static.netnaija.com/i/pJdN9rnPK4P.jpg" alt="Love Destiny: The Movie (2022) [Thai]" title="Love Destiny: The Movie (2022) [Thai]">
                                </div>
                <div class="info">
                                    <h2>
                        <a href="https://www.thenetnaija.net/videos/movies/17617-love-destiny-the-movie-2022-thai">Love Destiny: The Movie (2022) [Thai]</a>
                    </h2>
                    <div class="meta">
                        <div class="inner">
                            <span title="2022-12-01T15:55:05+01:00">
                                <i class="fas fa-calendar-alt"></i> Dec 01
                            </span>
                                <span>
                                <i class="fas fa-comment-alt"></i> 11
                            </span>
                        </div>
                    </div>
                </div>
            </article>
                        <article class="file-one shadow">
                <div class="thumbnail">
                                        <img src="https://static.netnaija.com/i/1xYNeXnPaLJ.jpg" alt="My Name Is Vendetta (2022) [Italian]" title="My Name Is Vendetta (2022) [Italian]">
                                </div>
                <div class="info">
                                    <h2>
                        <a href="https://www.thenetnaija.net/videos/movies/17616-my-name-is-vendetta-2022-italian">My Name Is Vendetta (2022) [Italian]</a>
                    </h2>
                    <div class="meta">
                        <div class="inner">
                            <span title="2022-11-30T20:08:24+01:00">
                                <i class="fas fa-calendar-alt"></i> Nov 30
                            </span>
                                <span>
                                <i class="fas fa-comment-alt"></i> 80
                            </span>
                        </div>
                    </div>
                </div>
            </article>
                        <article class="file-one shadow">
                <div class="thumbnail">
                                        <img src="https://static.netnaija.com/i/wnRaMJxmNdq.jpg" alt="A Tale of Two Christmases (2022)" title="A Tale of Two Christmases (2022)">
                                </div>
                <div class="info">
                                    <h2>
                        <a href="https://www.thenetnaija.net/videos/movies/17615-a-tale-of-two-christmases-2022">A Tale of Two Christmases (2022)</a>
                    </h2>
                    <div class="meta">
                        <div class="inner">
                            <span title="2022-11-30T19:14:53+01:00">
                                <i class="fas fa-calendar-alt"></i> Nov 30
                            </span>
                                <span>
                                <i class="fas fa-comment-alt"></i> 17
                            </span>
                        </div>
                    </div>
                </div>
            </article>
                        <article class="file-one shadow">
                <div class="thumbnail">
                                        <img src="https://static.netnaija.com/i/x5PKDRyRanM.jpg" alt="Haul out the Holly (2022)" title="Haul out the Holly (2022)">
                                </div>
                <div class="info">
                                    <h2>
                        <a href="https://www.thenetnaija.net/videos/movies/17614-haul-out-the-holly-2022">Haul out the Holly (2022)</a>
                    </h2>
                    <div class="meta">
                        <div class="inner">
                            <span title="2022-11-30T19:06:04+01:00">
                                <i class="fas fa-calendar-alt"></i> Nov 30
                            </span>
                                <span>
                                <i class="fas fa-comment-alt"></i> 1
                            </span>
                        </div>
                    </div>
                </div>
            </article>
                </div>

            
                    
        <div class="row my-3"><div class="col-md-12"><nav><ul class="pagination"><li class="active"><a href="#"><span class="page-numbers" aria-current="page">1</span></a></li><li><a class="page-numbers" href="https://www.thenetnaija.net/videos/movies/page/2">2</a></li><li><a class="page-numbers" href="https://www.thenetnaija.net/videos/movies/page/3">3</a></li><li><a class="page-numbers" href="https://www.thenetnaija.net/videos/movies/page/4">4</a></li><li><a href="#"><span class="page-numbers">...</span></a></li><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><!-- #2 --><li><a class="page-numbers" href="https://www.thenetnaija.net/videos/movies/page/213">213</a></li><li><a class="next page-numbers" href="https://www.thenetnaija.net/videos/movies/page/2">&raquo;</a></li></ul></nav></div></div>
            </div>
            <div class="content-side">
                            
          <div class="sidebar-cmp">
          
        <div class="cmp-one">
                        <div data-nn-cmp="Dochase Sidebar"><!-- <iframe src="https://ads.dochase.com/adx-dir-d/servlet/WebF_AdManager.AdDecision?aid=4840&reqin=iframe&w=300&h=250&adpos=atf&nid=13&cb=&ref=" frameborder="0" scrolling="no" style="width: 300px; height: 250px;"></iframe> -->

    <script type="text/javascript">
    var adx_adsvr_adspace_vAppRoot="https://ads.dochase.com/adx-dir-d/";
    var adx_adsvr_adspace_id="4859";
    var adx_size="300x250";
    var adx_custom="";
    var adx_nid="13";
    </script>
    <script type="text/javascript" src="https://j.dochase.com/adxads.js"></script>

    <!-- <script type="text/javascript">
    var adx_adsvr_adspace_vAppRoot="https://ads.dochase.com/adx-dir-d/";
    var adx_adsvr_adspace_id="4840";
    var adx_size="300x250";
    var adx_custom="";
    var adx_nid="13";
    </script>
    <script type="text/javascript" src="https://j.dochase.com/adxads.js"></script> --></div>
                </div>

        </div>
          <div class="sidebar-cmp">
          
        <div class="cmp-one">
                        <div data-nn-cmp="Hallway Sidebar"><div><script type="application/json" class="async-json-js">{"src":"https://hallway.netnaija.app/deliver.1.5.js"}</script><div class="hallway-ins" data-format="auto" data-site="394765639421661184" data-slot="400152434976100352"></div></div></div>
                </div>

        </div>
      
                <div class="side-trends">
                    
    <div class="trends-block">
        <div class="tb-header">
            <h2>Trending Posts</h2>    </div>
        <div class="tb-list">
                                    
    <article class="tb-leader">
        <div class="tb-thumb">
            <img src="https://static.netnaija.com/i/PrO7OOXb7yp.webp" alt="&quot;P0rn destroyed my family&quot; - Kanye West cries out" title="&quot;P0rn destroyed my family&quot; - Kanye West cries out">
        </div>
        <div class="tb-info">
            <h3>
                <a href="https://www.torizone.com/posts/2022/12/104874-p0rn-destroyed-family-kanye-west-cries">&quot;P0rn destroyed my family&quot; - Kanye West cries out</a></h3>
        </div>
    </article>

                                    
    <article class="tb-one">
        <div class="tb-rank">2</div>
        <div class="tb-info">
            <h3>
                <a href="https://www.torizone.com/posts/2022/12/104860-internet-melts-kanye-west-reveals-caught-married-basketballer-chris">Internet Melts down as Kanye West reveals he 'caught' married basketballer Chris Paul with ex wife Kim Kardashian</a></h3>
        </div>
    </article>

                                    
    <article class="tb-one">
        <div class="tb-rank">3</div>
        <div class="tb-info">
            <h3>
                <a href="https://www.torizone.com/posts/2022/12/104888-you-disappointment-satisfied-bride-tears-prays-tailor-who-made">"You will never know disappointment" - Satisfied bride in tears as she prays for tailor who made her wedding dress (Video)</a></h3>
        </div>
    </article>

                                    
    <article class="tb-one">
        <div class="tb-rank">4</div>
        <div class="tb-info">
            <h3>
                <a href="https://www.torizone.com/posts/2022/12/104887-300l-unizik-student-ends-academic-pressure">300L UNIZIK student ends it all over academic pressure</a></h3>
        </div>
    </article>

                                    
    <article class="tb-one">
        <div class="tb-rank">5</div>
        <div class="tb-info">
            <h3>
                <a href="https://www.torizone.com/posts/2022/12/104884-from-nysc-altar-god-faithful-couple-who-met-nysc-set-wed">"From NYSC to altar, God is faithful" - Couple who met during NYSC set to wed</a></h3>
        </div>
    </article>

                    <div class="tb-more">
            <a href="https://www.thenetnaija.net/trends/posts">More</a>
        </div>
        </div>
    </div>

                        
    <div class="trends-block">
        <div class="tb-header">
            <h2>Trending Music</h2>    </div>
        <div class="tb-list">
                                                    
    <article class="tb-leader">
        <div class="tb-thumb">
            <img src="https://static.netnaija.com/i/BQJNj4W37rL.jpg" alt="Alle (feat. Bayanni, Crayon, Boy Spyce, Magixx &amp; Ayra Starr)" title="Alle (feat. Bayanni, Crayon, Boy Spyce, Magixx &amp; Ayra Starr)">
        </div>
        <div class="tb-info">
            <h3>
                <a href="https://www.thenetnaija.net/music/afro/13645-mavins-alle-bayanni-crayon-boy-spyce-magixx-ayra-starr">Alle (feat. Bayanni, Crayon, Boy Spyce, Magixx &amp; Ayra Starr)</a><span class="tb-small-text">Mavins</span></h3>
        </div>
    </article>

                                                    
    <article class="tb-one">
        <div class="tb-rank">2</div>
        <div class="tb-info">
            <h3>
                <a href="https://www.thenetnaija.net/music/afro/13644-album-mavins-chapter-x">Chapter X</a><span class="tb-small-text">Mavins</span></h3>
        </div>
    </article>

                                                    
    <article class="tb-one">
        <div class="tb-rank">3</div>
        <div class="tb-info">
            <h3>
                <a href="https://www.thenetnaija.net/music/afro/13646-mavins-johnny-drille-crayon-all-im-saying-don-jazzy-bayanni-boy">All I'm Saying (feat. Don Jazzy, Bayanni, Boy Spyce & LADIPOE)</a><span class="tb-small-text">Mavins, Johnny Drille & Crayon</span></h3>
        </div>
    </article>

                                                    
    <article class="tb-one">
        <div class="tb-rank">4</div>
        <div class="tb-info">
            <h3>
                <a href="https://www.thenetnaija.net/music/afro/13647-mavins-don-jazzy-crayon-ogini-na-fio-ladipoe">Ogini Na Fio (feat. LADIPOE)</a><span class="tb-small-text">Mavins, Don Jazzy & Crayon</span></h3>
        </div>
    </article>

                                                    
    <article class="tb-one">
        <div class="tb-rank">5</div>
        <div class="tb-info">
            <h3>
                <a href="https://www.thenetnaija.net/music/afro/13648-mavins-ayra-starr-rema-amina-bayanni-crayon">Amina (feat. Bayanni & Crayon)</a><span class="tb-small-text">Mavins, Ayra Starr & Rema</span></h3>
        </div>
    </article>

                                <div class="tb-more">
                <a href="https://www.thenetnaija.net/trends/music">More</a>
            </div>
            </div>
    </div>

                        
    <div class="trends-block">
        <div class="tb-header">
            <h2>Trending Videos</h2>    </div>
        <div class="tb-list">
                                                    
    <article class="tb-leader">
        <div class="tb-thumb">
            <img src="https://static.netnaija.com/i/1xYNeXqAaLJ.jpg" alt="Japan 2  -  1 Spain (Dec-01-2022) World Cup 2022 Highlights" title="Japan 2  -  1 Spain (Dec-01-2022) World Cup 2022 Highlights">
        </div>
        <div class="tb-info">
            <h3>
                <a href="https://www.thenetnaija.net/videos/sports/17644-japan-2-1-spain-dec-01-2022-world-cup-2022-highlights">Japan 2  -  1 Spain (Dec-01-2022) World Cup 2022 Highlights</a></h3>
        </div>
    </article>

                                                    
    <article class="tb-one">
        <div class="tb-rank">2</div>
        <div class="tb-info">
            <h3>
                <a href="https://www.thenetnaija.net/videos/sports/17641-poland-0-2-argentina-nov-30-2022-world-cup-2022-highlights">Poland 0  -  2 Argentina (Nov-30-2022) World Cup 2022 Highlights</a></h3>
        </div>
    </article>

                                                    
    <article class="tb-one">
        <div class="tb-rank">3</div>
        <div class="tb-info">
            <h3>
                <a href="https://www.thenetnaija.net/videos/sports/17638-wales-0-3-england-nov-29-2022-world-cup-2022-highlights">Wales 0  -  3 England (Nov-29-2022) World Cup 2022 Highlights</a></h3>
        </div>
    </article>

                                                    
    <article class="tb-one">
        <div class="tb-rank">4</div>
        <div class="tb-info">
            <h3>
                <a href="https://www.thenetnaija.net/videos/sports/17645-canada-1-2-morocco-dec-01-2022-world-cup-2022-highlights">Canada 1  -  2 Morocco (Dec-01-2022) World Cup 2022 Highlights</a></h3>
        </div>
    </article>

                                                    
    <article class="tb-one">
        <div class="tb-rank">5</div>
        <div class="tb-info">
            <h3>
                <a href="https://www.thenetnaija.net/videos/sports/17643-costa-rica-2-4-germany-dec-01-2022-world-cup-2022-highlights">Costa Rica 2  -  4 Germany (Dec-01-2022) World Cup 2022 Highlights</a></h3>
        </div>
    </article>

                                <div class="tb-more">
                <a href="https://www.thenetnaija.net/trends/videos">More</a>
            </div>
            </div>
    </div>

                        
    <div class="trends-block">
        <div class="tb-header">
            <h2>Trending Movies</h2>    </div>
        <div class="tb-list">
                                                    
    <article class="tb-leader">
        <div class="tb-thumb">
            <img src="https://static.netnaija.com/i/WAPK4JxJa0B.jpg" alt="Wednesday" title="Wednesday">
        </div>
        <div class="tb-info">
            <h3>
                <a href="https://www.thenetnaija.net/videos/series/17533-wednesday">Wednesday</a></h3>
        </div>
    </article>

                                                    
    <article class="tb-one">
        <div class="tb-rank">2</div>
        <div class="tb-info">
            <h3>
                <a href="https://www.thenetnaija.net/videos/series/15106-the-sex-lives-of-college-girls">The Sex Lives of College Girls</a></h3>
        </div>
    </article>

                                                    
    <article class="tb-one">
        <div class="tb-rank">3</div>
        <div class="tb-info">
            <h3>
                <a href="https://www.thenetnaija.net/videos/series/17634-riches">Riches</a></h3>
        </div>
    </article>

                                                    
    <article class="tb-one">
        <div class="tb-rank">4</div>
        <div class="tb-info">
            <h3>
                <a href="https://www.thenetnaija.net/videos/movies/17628-warriors-of-future-2022-chinese">Warriors of Future (2022) [Chinese]</a></h3>
        </div>
    </article>

                                                    
    <article class="tb-one">
        <div class="tb-rank">5</div>
        <div class="tb-info">
            <h3>
                <a href="https://www.thenetnaija.net/videos/series/11744-blood-water">Blood & Water</a></h3>
        </div>
    </article>

                                <div class="tb-more">
                <a href="https://www.thenetnaija.net/trends/movies">More</a>
            </div>
            </div>
    </div>

        </div>

            </div>
        </div>
                    </div>
                </div>
            </div>
            <div class="app-subscribe">
                <div class="content-width">
                    <div class="app-subscribe-note">
                        Keep up to date with our latest articles and uploads...
                    </div>
                    <form
                        class="app-subscribe-con"
                        action="https://feedburner.google.com/fb/a/mailverify"
                        method="post"
                        target="popupwindow"
                        onsubmit="window.open('https://feedburner.google.com/fb/a/mailverify?uri=netnaija', 'popupwindow', 'scrollbars=yes,width=550,height=520');return true"
                    >
                        <div class="asc-input">
                            <input
                                type="text"
                                name="email"
                                class="form-control"
                                title=""
                                placeholder="Email address..."
                            >
                        </div>
                        <div class="asc-button">
                            <button class="btn" type="submit">
                                <i class="fas fa-envelope"></i> Subscribe
                            </button>
                        </div>
                        <input type="hidden" value="netnaija" name="uri">
                        <input type="hidden" name="loc" value="en_US">
                    </form>
                </div>
            </div>
            <div class="app-footer">
                <div class="content-width">
                    <div class="app-footer-list row">
                        <div class="afl-one site-logo col-md-10 col-lg-3">
                            <div>
                                <img src="/static/img/logo.silver.png" alt="Netnaija" title="Netnaija">
                                <div class="mt-3">
                                    <a href="https://play.google.com/store/apps/details?id=com.netnaija">
                                        <img src="/static/svg/google-play.svg" alt="Android App">
                                    </a>
                                </div>
                            </div>
                        </div>
                                            <div class="afl-one col-md-3">
                            <h3>Links</h3>
                            <div class="afl-underline"></div>
                            <ul>
                                                            <li><a href="https://www.sabishare.com/">Sabishare</a></li>
                                                            <li><a href="https://www.torizone.com/">Torizone</a></li>
                                                            <li><a href="https://pitakwa360.com/">Pitakwa360</a></li>
                                                        </ul>
                        </div>
                                            <div class="afl-one col-md-3">
                            <h3>Social Media</h3>
                            <div class="afl-underline"></div>
                            <ul>
                                                            <li><a href="https://www.facebook.com/netnaija">Facebook</a></li>
                                                            <li><a href="https://www.twitter.com/netnaijadotcom">Twitter</a></li>
                                                            <li><a href="https://www.instagram.com/netnaija">Instagram</a></li>
                                                            <li><a href="https://t.me/netnaijadotcom">Telegram</a></li>
                                                        </ul>
                        </div>
                                            <div class="afl-one col-md-3">
                            <h3>Resources</h3>
                            <div class="afl-underline"></div>
                            <ul>
                                                            <li><a href="https://dash.netnaija.com/">Login</a></li>
                                                            <li><a href="https://dash.netnaija.com/signup">Sign Up</a></li>
                                                            <li><a href="https://www.thenetnaija.net/docs/contact">Contact Us</a></li>
                                                            <li><a href="https://www.thenetnaija.net/docs/advertise">Advertise</a></li>
                                                            <li><a href="https://www.thenetnaija.net/docs/privacy">Privacy Policy</a></li>
                                                            <li><a href="https://www.thenetnaija.net/docs/dmca">DMCA Takedown</a></li>
                                                        </ul>
                        </div>
                                        </div>
                    <div class="copyright">
                        &copy; 2022 Netnaija
                    </div>
                </div>
            </div>
        </div><!-- #app -->
        <script src="/static/js/vue.min.js"></script>
            <script src="/static/js/app.min.js?v=1.0.7"></script>
            
          <script data-cfasync="false" type="text/javascript" id="clever-core">
                                        (function (document, window) {
                                            var a, c = document.createElement("script");
                                            c.id = "CleverCoreLoader52917";
                                            c.src = "//scripts.cleverwebserver.com/e93edbb359d10570adf37b2e43370b16.js";

                                            c.async = !0;
                                            c.type = "text/javascript";
                                            c.setAttribute("data-target", window.name);
                                            c.setAttribute("data-callback", "put-your-callback-macro-here");

                                            try {
                                                a = parent.document.getElementsByTagName("script")[0] || document.getElementsByTagName("script")[0];
                                            } catch (e) {
                                                a = !1;
                                            }

                                            a || (a = document.getElementsByTagName("head")[0] || document.getElementsByTagName("body")[0]);
                                            a.parentNode.insertBefore(c, a);
                                        })(document, window);
                                    </script>
          <div><script src="https://hallway.netnaija.app/deliver.1.6.js" async=""></script><div class="hallway-ins" data-format="sticky" data-site="394765639421661184" data-slot="425615794735550464"></div></div>
      
        </body>
    </html>

""".trimIndent()