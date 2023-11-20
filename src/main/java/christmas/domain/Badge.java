package christmas.domain;

public enum Badge {
    NONE("없음", 0),
    STAR("별", 5000),
    TREE("트리", 10000),
    SANTA("산타", 20000);


    private String name;
    private int cutLine;

    Badge(String name, int cutLine) {
        this.name = name;
        this.cutLine = cutLine;
    }

    public String getName() {
        return name;
    }

    public int getCutLine() {
        return cutLine;
    }
}
