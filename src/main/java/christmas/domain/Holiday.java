package christmas.domain;

public enum Holiday {

    FIRSTSUN(3),
    SECONDSUN(10),
    THIRDSUN(17),
    FOURTHSUN(24),
    FIFTHSUN(31),
    CHRISTMAS(25);


    private int date;

    Holiday(int date) {
        this.date = date;
    }

    public int getDate() {
        return date;
    }
}
