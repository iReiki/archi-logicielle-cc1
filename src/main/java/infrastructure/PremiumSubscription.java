package infrastructure;

import domain.Subscription;
import domain.SubscriptionName;

public final class PremiumSubscription implements Subscription {
    @Override
    public String subscriptionName() {
        return SubscriptionName.PREMIUM.name();
    }

    @Override
    public double pricePerMonth() {
        return 49.99;
    }
}
