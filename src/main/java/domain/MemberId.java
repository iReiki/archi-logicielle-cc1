package domain;

public final class MemberId {

    private final int value;

    private MemberId(int value) {
        this.value = value;
    }

    public static MemberId of(int value) {
        return new MemberId(value);
    }

    public String getValue() {
        return String.valueOf(value);
    }

}
