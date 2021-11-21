package domain;

import java.util.Objects;

public final class Member {

    private final MemberId memberId;
    private final String lastname;
    private final String firstname;
    private final String email;
    private String password;
    private final Subscription subscription;


    private Member(final MemberId memberId, final String lastname, final String firstname, final String email, final String password,
                   final Subscription subscription) {
        this.memberId = memberId;
        this.lastname = lastname;
        this.firstname = firstname;
        this.email = email;
        this.password = password;
        this.subscription = subscription;
    }

    public static Member of(final MemberId memberId, final String lastname, final String firstname, final String email, final String password,
                            final Subscription subscription) {
        return new Member(memberId, lastname, firstname, email, password, subscription);
    }

    public MemberId getMemberId() {
        return this.memberId;
    }

    public String getEmail() { return this.email; }

    public Subscription getSubscription() { return this.subscription; }

    public void changePassword(String newPassword) {
        this.password = Objects.requireNonNull(newPassword);
    }

    @Override
    public String toString() {
        String hiddenPassword = "*".repeat(this.password.length());
        return "Member {" +
                "memberId=" + this.memberId.getValue() +
                ", lastname='" + this.lastname + '\'' +
                ", firstname='" + this.firstname + '\'' +
                ", email='" + this.email + '\'' +
                ", password='" + hiddenPassword + '\'' +
                ", subscription=" + this.subscription.subscriptionName() +
                '}';
    }
}
