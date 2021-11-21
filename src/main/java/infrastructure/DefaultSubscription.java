package infrastructure;

import domain.Subscription;
import domain.SubscriptionName;

public final class DefaultSubscription implements Subscription {

    @Override
    public String subscriptionName() {
        return SubscriptionName.DEFAULT.name();
    }

    @Override
    public double pricePerMonth() {
        return 10.0;
    }
}
