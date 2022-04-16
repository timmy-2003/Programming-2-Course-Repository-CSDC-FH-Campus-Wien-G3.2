package at.ac.fhcampuswien.enums;

public enum Country {

    AE("country-ae"),
    AR("country-ar"),
    AT("country-at"),
    AU("country-au"),
    BE("country-be"),
    BG("country-bg"),
    BR("country-br"),
    CA("country-ca"),
    CH("country-ch"),
    CN("country-cn"),
    CO("country-co"),
    CU("country-cu"),
    CZ("country-cz"),
    DE("country-de"),
    EG("country-eg"),
    FR("country-fr"),
    GB("country-gb"),
    GR("country-gr"),
    HK("country-hk"),
    HU("country-hu"),
    ID("country-id"),
    IE("country-ie"),
    IL("country-il"),
    IN("country-in"),
    IT("country-it"),
    JP("country-jp"),
    KR("country-kr"),
    LT("country-lt"),
    LV("country-lv"),
    MA("country-ma"),
    MX("country-mx"),
    MY("country-my"),
    NG("country-ng"),
    NL("country-nl"),
    NO("country-no"),
    NZ("country-nz"),
    PH("country-ph"),
    PL("country-pl"),
    PT("country-pt"),
    RO("country-ro"),
    RS("country-rs"),
    RU("country-ru"),
    SA("country-sa"),
    SE("country-se"),
    SG("country-sg"),
    SI("country-si"),
    SK("country-sk"),
    TH("country-th"),
    TR("country-tr"),
    TW("country-tw"),
    UA("country-ua"),
    US("country-us"),
    VE("country-ve"),
    ZA("country-za"),
    ;

    private final String urlParameter;

    Country(String urlParameter) {
        this.urlParameter = urlParameter;
    }

    @Override
    public String toString() {
        return this.urlParameter;
    }
}
