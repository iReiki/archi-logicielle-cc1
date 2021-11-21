import domain.*;
import infrastructure.*;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        MemberRepository memberRepository = new InMemoryMemberRepository();
        MembershipService membershipService = new MembershipService(memberRepository);

        Scanner sc = new Scanner(System.in);
        
        String choice = null;
        do {
            System.out.println("[MAIN MENU]");
            System.out.println("Application membership for the best support for your apps");
            System.out.println("1 - Sign your application!");
            System.out.println("2 - Display members");
            System.out.println("0 - Leave application");
            System.out.println();
            choice = sc.nextLine();
            switch (choice) {
                // Member information step
                case "1":
                    MemberId memberId = memberRepository.nextIdentity();
                    System.out.println("Let's sign your application membership!");
                    System.out.println("Enter the member's information to subscribe.");
                    System.out.println("Your lastname:");
                    String lastname = sc.nextLine();
                    System.out.println("Your firstname:");
                    String firstname = sc.nextLine();
                    System.out.println("Your email:");
                    String email = sc.nextLine();
                    System.out.println("Your password:");
                    String password = sc.nextLine();

                    // Subscription step
                    if (verifyMember(membershipService, lastname, firstname, email, password)) {
                        Subscription subscription = new DefaultSubscription();
                        displaySubscriptionMenu();
                        String choiceSubscription = sc.nextLine();
                        switch (choiceSubscription) {
                            case "2":
                                subscription = new BusinessSubscription();
                                break;
                            case "3":
                                subscription = new PremiumSubscription();
                                break;
                            default: break;
                        }
                        createMember(membershipService, memberId, lastname, firstname, email, password, subscription);
                        // Payment step
                        displayPaymentMenu(membershipService, memberId);
                        String choicePayment = null;
                        do {
                            choicePayment = sc.nextLine();
                            switch (choicePayment) {
                                case "Y":
                                    System.out.println("Successful " + subscription.subscriptionName() + " subscription for " + email + "!");
                                    System.out.println();
                                    break;
                                case "N":
                                    System.out.println("Subscription canceled");
                                    removeMember(membershipService, memberId);
                                    System.out.println();
                                    break;
                                default:
                                    System.out.println("Choose between Y or N.");
                                    break;
                            }
                        } while (!choicePayment.equals("Y") && !choicePayment.equals("N"));
                    } else {
                        displayMemberUsage();
                    }
                    break;

                // Display all members
                case "2":
                    printAllMembers(membershipService);
                    break;

                case "0":
                    System.out.println("Application exited.");
                    break;

                default:
                    System.out.println("Choose between 1, 2 or 0.");
                    break;
            }
        } while(!choice.equals("0"));

    }

    private static void displayMemberUsage() {
        System.out.println("Member's information format:");
        System.out.println("Your lastname and your firstname must be filled.");
        System.out.println("Your email must be valid.");
        System.out.println("Your password cannot be empty.");
        System.out.println("Retry again.");
        System.out.println();
    }

    private static void displaySubscriptionMenu() {
        System.out.println("Choose your subscription:");
        System.out.println("1 - Default Subscription (Support website provided)");
        System.out.println("2 - Business Subscription (Add some extra services)");
        System.out.println("3 - Premium Subscription (A full support team provided, backups, 24/24)");
        System.out.println("Your choice:");
    }

    private static void displayPaymentMenu(MembershipService membershipService, MemberId memberId) {
        Member member = membershipService.getMemberById(memberId);
        System.out.println("You subscribed to our " + member.getSubscription().subscriptionName() + " subscription using " + member.getEmail() + "!");
        System.out.println("You will now proceed to the payment.");
        System.out.println("This subscription price will be " + member.getSubscription().pricePerMonth() + "€ each month.");
        System.out.println("Are you ready for your payment?");
        System.out.println("Your choice (Y/N):");
    }

    private static void createMember(MembershipService membershipService, MemberId memberId, String lastname, String firstname, String email, String password,
                                     Subscription subscription) {
        Member member = Member.of(memberId, lastname, firstname, email, password, subscription);
        membershipService.createMember(member);
    }

    private static void removeMember(MembershipService membershipService, MemberId memberId) {
        membershipService.removeMemberById(memberId);
    }

    private static boolean verifyMember(MembershipService membershipService, String lastname, String firstname, String email, String password) {
        return membershipService.verifyMember(lastname, firstname, email, password);
    }

    private static void printAllMembers(MembershipService membershipService) {
        if (membershipService.isRepositoryEmpty()) {
            System.out.println("There is no member yet.");
        } else {
            System.out.println("Displaying all the members");
            final List<Member> members = membershipService.allMembers();
            // members.forEach(currentMember -> System.out.println(currentMember));
            members.forEach(System.out::println);
        }
        System.out.println();
    }


}
