package the.green.one.game;

public enum Emojis {
    LIKED("&#128525;"),
    LOVE("&#128149;"),
    SAD("&#128546;"),
    CRY("&#128557;"),
    WOW("&#128559;"),
    VERYLAUGH("&#128514;"),
    ANGRY("&#128545;"),
    CONFIDENT("&#128526;");
    String code;

    Emojis(String code) {
        this.code = code;
    }

    public String getEmojiCode() {
        return this.code;
    }
}
