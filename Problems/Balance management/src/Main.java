import org.w3c.dom.ls.LSOutput;

import java.util.Scanner;

public class Main {


    Direction.valueOf("NORTH"); //returns Direction.NORTH as a Direction object

    Direction.NORTH.toString(); //returns "N" as a String

    Direction.NORTH.name(); //returns "NORTH" as a String

    Direction.NORTH.getShortCode(); //returns "N" as a String

    Direction.valueOf("N"); //returns Direction.NORTH as a Direction object
}

    enum Direction {
        EAST("E"),
        WEST("W"),
        NORTH("N"),
        SOUTH("S");

        private final String shortCode;

        Direction(String code) {
            this.shortCode = code;
        }

        public String getShortCode() {
            return this.shortCode;
        }
    }



    /**
     * The method change the balance of the given account according to an operation.
     * @param account
     * @param operation
     * @return true if the balance has changed, otherwise - false.
     */
//    public static boolean changeBalance(Account account, Operation operation, Long sum) {
//        boolean isChanged = true;
//        if (operation == Operation.DEPOSIT) {
//            account.balance += sum;
//        } else if (operation == Operation.WITHDRAW) {
//            if (account.balance >= sum) {
//                account.balance -= sum;
//            } else {
//                isChanged = false;
//                System.out.println("Not enough money to withdraw.");
//            }
//        }
//        return isChanged;
//    }
//
//    /* Do not change code below */
//    enum Operation {
//        /**
//         * deposit (add) an amount into an Account
//         */
//        DEPOSIT,
//        /**
//         * withdraw (subtract) an amount from an Account
//         */
//        WITHDRAW
//    }
//
//    static class Account {
//
//        private String code;
//        private Long balance;
//
//        public String getCode() {
//            return code;
//        }
//
//        public Long getBalance() {
//            return balance;
//        }
//
//        public void setBalance(Long balance) {
//            this.balance = balance;
//        }
//    }
//
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//
//        String[] parts = scanner.nextLine().split("\\s+");
//
//        Account account = new Account();
//        account.setBalance(Long.parseLong(parts[0]));
//
//        Operation operation = Operation.valueOf(parts[1]);
//
//        Long sum = Long.parseLong(parts[2]);
//
//        if (changeBalance(account, operation, sum)) {
//            System.out.println(account.getBalance());
//        }
//    }
//}