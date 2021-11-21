package infrastructure;

import domain.Subscription;
import domain.SubscriptionName;

public final class BusinessSubscription implements Subscription {
    @Override
    public String subscriptionName() {
        return SubscriptionName.BUSINESS.name();
    }

    @Override
    public double pricePerMonth() {
        return 29.99;
    }
}
