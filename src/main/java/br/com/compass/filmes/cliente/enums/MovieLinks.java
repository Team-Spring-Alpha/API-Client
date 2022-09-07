package br.com.compass.filmes.cliente.enums;

public enum MovieLinks {
    NETFLIX("https://www.justwatch.com/br/provedor/netflix", "Netflix"),
    AMAZON_PRIME_VIDEO("https://www.justwatch.com/br/provedor/amazon-video", "Amazon Prime Video"),
    AMAZON_PRIME("https://www.justwatch.com/br/provedor/amazon-prime-video", "Amazon Prime"),
    APPLE_ITUNES("https://www.justwatch.com/br/provedor/apple-itunes", "Apple iTunes"),
    GOOGLE_PLAY_MOVIES("https://www.justwatch.com/br/provedor/google-play-movies", "Google Play Movie"),
    SUN_NXT("https://www.justwatch.com/us/provider/sun-nxt", "Sun Nxt"),
    MUBI("https://www.justwatch.com/br/provedor/mubi", "Mubi"),
    LOOKE("https://www.justwatch.com/br/provedor/looke", "Looke"),
    CLASSIX("https://www.justwatch.com/us/provider/classix", "Classix"),
    STAR_PLUS("https://www.justwatch.com/br/provedor/star-plus", "Star Plus"),
    PARAMOUNT_PLUS("https://www.justwatch.com/br/provedor/paramount-plus", "Paramount Plus"),
    HBO_MAX("https://www.justwatch.com/br/provedor/hbo-max", "HBO Max"),
    ARGO("https://www.justwatch.com/br/provedor/argo", "Argo"),
    EVENTIVE("https://www.justwatch.com/br/provedor/eventive", "Eventive"),
    CULTPIX("https://www.justwatch.com/br/provedor/cultpix", "Cultpix"),
    CLARO_VIDEO("https://www.justwatch.com/br/provedor/claro-video", "Claro video"),
    TELECINE_PLAY("https://www.telecine.com.br/", "Telecine Play"),
    GLOBOPLAY("https://www.justwatch.com/br/provedor/globoplay", "Globoplay"),
    APPLE_TV_PLUS("https://www.justwatch.com/br/provedor/apple-tv-plus", "Apple TV Plus"),
    AMAZON_VIDEO("https://www.justwatch.com/br/provedor/amazon-prime-video", "Amazon Video"),
    FILMBOX("https://www.justwatch.com/br/provedor/filmbox", "FilmBox+"),
    VIX("https://www.justwatch.com/br/provedor/vix", "VIX"),
    CURIOSITY_STREAM("https://www.justwatch.com/br/provedor/curiosity-stream", "Curiosity Stream"),
    SPAMFLIX("https://www.justwatch.com/br/provedor/spamflix", "Spamflix"),
    FUNIMATION_NOW("https://www.justwatch.com/br/provedor/funimation-now", "Funimation Now"),
    DOCSVILLE("https://www.justwatch.com/br/provedor/docsville", "DOCSVILLE"),
    NETMOVIES("https://www.justwatch.com/br/provedor/netmovies", "NetMovies"),
    STARZ_PLAY_AMAZON_CHANNEL("https://www.justwatch.com/br/provedor/starz-play", "Starz Play Amazon Channel"),
    WOW_PRESENTS_PLUS("https://www.justwatch.com/br/provedor/wow-presents-plus", "WOW Presents Plus"),
    MAGELLAN_TV("https://www.justwatch.com/br/provedor/magellan-tv", "Magellan TV"),
    PARAMOUNT_AMAZON_CHANNEL("https://www.justwatch.com/br/provedor/paramount-plus", "Paramount+ Amazon Channel"),
    BROADWAYHD("https://www.justwatch.com/br/provedor/broadwayhd", "BroadwayHD"),
    BELAS_ARTES_A_LA_CARTE("https://www.justwatch.com/br/provedor/belas-artes-a-la-carte", "Belas Artes à La Carte"),
    FILMZIE("https://www.justwatch.com/br/provedor/filmzie", "Filmzie"),
    DEKKO("https://www.justwatch.com/br/provedor/dekkoo", "Dekkoo"),
    BELIEVE("https://www.justwatch.com/br/provedor/believe", "Believe"),
    TRUE_STORY("https://www.justwatch.com/br/provedor/true-story", "True Story"),
    HBO_GO("https://www.justwatch.com/br/provedor/hbo-go", "HBO Go"),
    DOCALLIANCE_FILMS("https://www.justwatch.com/br/provedor/docalliance-films", "DocAlliance Films"),
    HOICHOI("https://www.justwatch.com/br/provedor/hoichoi", "Hoichoi"),
    KOREAONDEMAND("https://www.justwatch.com/br/provedor/koreaondemand", "KoreaOnDemand"),
    OLDFLIX("https://www.justwatch.com/br/provedor/oldflix", "Oldflix"),
    PLUTO_TV("https://www.justwatch.com/br/provedor/pluto-tv", "Pluto TV"),
    TNTGO("https://www.justwatch.com/br/provedor/tntgo", "TNTGo"),
    GOSPEL_PLAY("https://www.justwatch.com/br/provedor/gospel-play", "GOSPEL PLAY"),
    DISNEY_PLUS("https://www.justwatch.com/br/provedor/disney-plus", "Disney Plus"),
    NOW("https://www.justwatch.com/br/provedor/now", "NOW"),
    LIBREFLIX("https://www.justwatch.com/br/provedor/libreflix", "Libreflix"),
    SUPO_MUNGAM_PLUS("https://www.justwatch.com/br/provedor/supo-mungam-plus", "Supo Mungam Plus"),
    FILME_FILME("https://www.justwatch.com/br/provedor/filme-filme", "Filme Filme"),
    STARZ("https://www.justwatch.com/br/provedor/starz", "Starz"),
    KINOPOP("https://www.justwatch.com/br/provedor/kinopop", "KinoPop"),
    OI_PLAY("https://www.justwatch.com/br/provedor/oi-play", "Oi Play"),
    MGM_AMAZON_CHANNEL("https://www.justwatch.com/br/provedor/mgm-amazon-channel", "MGM Amazon Channel"),
    MICROSOFT_STORE("https://www.justwatch.com/br/provedor/microsoft-store", "Microsoft Store"),
    LOOKE_AMAZON_CHANNEL("https://www.justwatch.com/br/provedor/looke-amazon-channel", "Looke Amazon Channel"),
    REVRY("https://www.justwatch.com/br/provedor/revry", "Revry"),
    MOVIESAINTS("https://www.justwatch.com/br/provedor/moviesaints", "MovieSaints");

    private String link;
    private String label;
    MovieLinks(String link, String label) {
        this.link = link;
        this.label = label;
    }

    public static MovieLinks valueOfLabel(String label) {
        for (MovieLinks movieLinks : values()) {
            if (movieLinks.label.equals(label)) {
                return movieLinks;
            }
        }
        return null;
    }

    public String getLink() {
        return this.link;
    }

    public String getLabel() {
        return this.label;
    }
}
