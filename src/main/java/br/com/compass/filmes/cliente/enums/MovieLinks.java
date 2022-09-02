package br.com.compass.filmes.cliente.enums;

public enum MovieLinks {
    NETFLIX("https://www.justwatch.com/br/provedor/netflix"),
    AMAZON_PRIME_VIDEO("https://www.justwatch.com/br/provedor/amazon-video"),
    AMAZON_PRIME("https://www.justwatch.com/br/provedor/amazon-prime-video"),
    APPLE_ITUNES("https://www.justwatch.com/br/provedor/apple-itunes"),
    GOOGLE_PLAY_MOVIES("https://www.justwatch.com/br/provedor/google-play-movies"),
    SUN_NXT("https://www.justwatch.com/us/provider/sun-nxt"),
    MUBI("https://www.justwatch.com/br/provedor/mubi"),
    LOOKE("https://www.justwatch.com/br/provedor/looke"),
    CLASSIX("https://www.justwatch.com/us/provider/classix"),
    STAR_PLUS("https://www.justwatch.com/br/provedor/star-plus"),
    PARAMOUNT_PLUS("https://www.justwatch.com/br/provedor/paramount-plus"),
    HBO_MAX("https://www.justwatch.com/br/provedor/hbo-max"),
    ARGO("https://www.justwatch.com/br/provedor/argo"),
    EVENTIVE("https://www.justwatch.com/br/provedor/eventive"),
    CULTPIX("https://www.justwatch.com/br/provedor/cultpix"),
    CLARO_VIDEO("https://www.justwatch.com/br/provedor/claro-video"),
    TELECINE_PLAY("https://www.telecine.com.br/"),
    GLOBOPLAY("https://www.justwatch.com/br/provedor/globoplay"),
    APPLE_TV_PLUS("https://www.justwatch.com/br/provedor/apple-tv-plus"),
    AMAZON_VIDEO("https://www.justwatch.com/br/provedor/amazon-prime-video"),
    FILMBOX("https://www.justwatch.com/br/provedor/filmbox"),
    VIX("https://www.justwatch.com/br/provedor/vix"),
    CURIOSITY_STREAM("https://www.justwatch.com/br/provedor/curiosity-stream"),
    SPAMFLIX("https://www.justwatch.com/br/provedor/spamflix"),
    FUNIMATION_NOW("https://www.justwatch.com/br/provedor/funimation-now"),
    DOCSVILLE("https://www.justwatch.com/br/provedor/docsville"),
    NETMOVIES("https://www.justwatch.com/br/provedor/netmovies"),
    STARZ_PLAY_AMAZON_CHANNEL("https://www.justwatch.com/br/provedor/starz-play"),
    WOW_PRESENTS_PLUS("https://www.justwatch.com/br/provedor/wow-presents-plus"),
    MAGELLAN_TV("https://www.justwatch.com/br/provedor/magellan-tv"),
    PARAMOUNT_AMAZON_CHANNEL("https://www.justwatch.com/br/provedor/paramount-plus"),
    BROADWAYHD("https://www.justwatch.com/br/provedor/broadwayhd"),
    BELAS_ARTES_A_LA_CARTE("https://www.justwatch.com/br/provedor/belas-artes-a-la-carte"),
    FILMZIE("https://www.justwatch.com/br/provedor/filmzie"),
    DEKKO("https://www.justwatch.com/br/provedor/dekkoo"),
    BELIEVE("https://www.justwatch.com/br/provedor/believe"),
    TRUE_STORY("https://www.justwatch.com/br/provedor/true-story"),
    HBO_GO("https://www.justwatch.com/br/provedor/hbo-go"),
    DOCALLIANCE_FILMS("https://www.justwatch.com/br/provedor/docalliance-films"),
    HOICHOI("https://www.justwatch.com/br/provedor/hoichoi"),
    KOREAONDEMAND("https://www.justwatch.com/br/provedor/koreaondemand"),
    OLDFLIX("https://www.justwatch.com/br/provedor/oldflix"),
    PLUTO_TV("https://www.justwatch.com/br/provedor/pluto-tv"),
    TNTGO("https://www.justwatch.com/br/provedor/tntgo"),
    GOSPEL_PLAY("https://www.justwatch.com/br/provedor/gospel-play"),
    DISNEY_PLUS("https://www.justwatch.com/br/provedor/disney-plus"),
    NOW("https://www.justwatch.com/br/provedor/now"),
    LIBREFLIX("https://www.justwatch.com/br/provedor/libreflix"),
    SUPO_MUNGAM_PLUS("https://www.justwatch.com/br/provedor/supo-mungam-plus"),
    FILME_FILME("https://www.justwatch.com/br/provedor/filme-filme"),
    STARZ("https://www.justwatch.com/br/provedor/starz"),
    KINOPOP("https://www.justwatch.com/br/provedor/kinopop"),
    OI_PLAY("https://www.justwatch.com/br/provedor/oi-play"),
    MGM_AMAZON_CHANNEL("https://www.justwatch.com/br/provedor/mgm-amazon-channel"),
    MICROSOFT_STORE("https://www.justwatch.com/br/provedor/microsoft-store"),
    LOOKE_AMAZON_CHANNEL("https://www.justwatch.com/br/provedor/looke-amazon-channel"),
    REVRY("https://www.justwatch.com/br/provedor/revry"),
    MOVIESAINTS("https://www.justwatch.com/br/provedor/moviesaints");

    MovieLinks(String link) {
        this.link = link;
    }

    private String link;

    public String getLink() {
        return this.link;
    }
}
