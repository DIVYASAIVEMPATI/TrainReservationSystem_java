import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


class TrainReservationSystemd1 {
    static Scanner sc = new Scanner(System.in);
    static List<Train> trains = new ArrayList<>();
    static List<User> users = new ArrayList<>();
    static int transactionCounter = 1000;

    public static void main(String[] args) {
        while (true) {
            System.out.println("\033[1;33m" + "********************WELCOME TO KSDK TRAIN TICKET RESERVATION*******************"+" \033[0;37m");
            System.out.println("\033[0;32m"+"1. Admin");
            System.out.println("2. User");
            System.out.print("\033[1;31m"+"3. Exit");
            sc.nextLine(); // Consume newline
	    System.out.println("\033[0;37m");
            System.out.print("\033[0;34mEnter your choice: \033[0m");
            int choice = sc.nextInt();
            sc.nextLine();
	     System.out.println(" \033[0;37m");
             switch (choice) {
                case 1:
                    adminLogin();
                    break;
                case 2:
                    userLogin();
                    break;
                case 3:
                    System.out.println(" \033[0;31m"+"Exiting...");
                    return;
                default:
                    System.out.println(" \033[0;31m"+"Invalid choice. Please try again.");
		    System.out.println(" \033[0;37m");
            }
        }
    }

    static void adminLogin() {
        System.out.println(" \033[0;33m" + "--------------------------------ADMIN LOGIN------------------------------------------"+" \033[0;37m");
        System.out.print(" \033[0;35m" +"Enter Admin ID: "+" \033[0;37m");
        String adminId = sc.nextLine();
        System.out.print(" \033[0;35m" +"Enter Password: "+" \033[0;37m");
        String password = sc.nextLine();

        // For demonstration, using a static admin ID and password
        Admin admin = new Admin("admin", "admin123");

        if (admin.login(adminId, password)) {
            System.out.println(" \033[0;32m"+"Admin login successful.");
            System.out.println(" \033[0;37m");
            adminMenu();
        } else {
            System.out.println(" \033[0;31m"+"Invalid Admin ID or Password.");
            System.out.println(" \033[0;37m");
        }
    }

    static void adminMenu() {
        while (true) {
            System.out.println(" \033[1;33m" + "===========================ADMIN MENU==============================================");
            System.out.println(" \033[0;37m");
            System.out.println(" \033[0;33m"+"1. Add Train");
            System.out.println("2. Display Seat Availability");
            System.out.println("3. Display Train Stops");
            System.out.println("4. Logout");
            System.out.print("\033[0;34mEnter your choice: \033[0m");
            int choice = sc.nextInt();
            System.out.println(" \033[0;37m");
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addTrain();
                    break;
                case 2:
                    displaySeatAvailability();
                    break;
                case 3:
                    displayTrainStops();
                    break;
                case 4:
                    return;
                default:
                    System.out.println(" \033[0;34m"+"Invalid choice. Please try again.");
            }
        }
    }

    static void addTrain() {
        System.out.print("Enter the number of trains available: ");
        int numTrains = sc.nextInt();
        sc.nextLine(); // Consume newline

        for (int i = 0; i < numTrains; i++) {
            System.out.print("Enter the name of train " + (i + 1) + ": ");
            String name = sc.nextLine();
            System.out.print(" \033[0;36m" +"Enter start point of train " + name + ": "+" \033[0;37m");
            String startPoint = sc.nextLine();
            System.out.print(" \033[0;36m" +"Enter end point of train " + name + ": "+" \033[0;37m");
            String endPoint = sc.nextLine();

            System.out.print(" \033[0;36m" +"Enter the number of stops for train " + name + ": "+" \033[0;37m");
            int numStops = sc.nextInt();
            sc.nextLine(); // Consume newline
            List<String> stops = new ArrayList<>();
            for (int j = 0; j < numStops; j++) {
                System.out.print(" \033[0;36m" +"Enter the name of stop " + (j + 1) + ": "+" \033[0;37m");
                stops.add(sc.nextLine());
            }

            System.out.print(" \033[0;36m" +"Enter the number of bhogies for train " + name + ": "+" \033[0;37m");
            int numBhogies = sc.nextInt();
            sc.nextLine(); // Consume newline
            List<Bhogie> bhogies = new ArrayList<>();
            int[] basePrices = new int[6];
            for (int k = 0; k < numBhogies; k++) {
                System.out.println(" \033[1;36m");
                System.out.println("Available bhogie types:");
                System.out.println("1. AC 1 Tier");
                System.out.println("2. AC 2 Tier");
                System.out.println("3. AC 3 Tier");
                System.out.println("4. Sleeper");
                System.out.println("5. Second Class");
                System.out.println("6. General");
                System.out.println(" \033[0;37m");
                System.out.print("\033[0;36m"+"Enter the type number for bhogie " + (k + 1) + ": "+" \033[0;37m");
                int type = sc.nextInt();
                System.out.print("\033[0;36m"+"Enter the number of Rows Available for " + Bhogie.getTypeName(type) + ": "+" \033[0;37m");
                int rows = sc.nextInt();
                System.out.print("\033[0;36m"+"Enter the number of Columns Available for " + Bhogie.getTypeName(type) + ": "+" \033[0;37m");
                int cols = sc.nextInt();
                sc.nextLine(); // Consume newline
                bhogies.add(new Bhogie(type, rows, cols));

                System.out.print(" \033[0;36m"+"Enter base price for " + Bhogie.getTypeName(type) + ": "+" \033[0;37m");
                basePrices[type - 1] = sc.nextInt();
                sc.nextLine(); // Consume newline
            }

            trains.add(new Train(name, startPoint, endPoint, stops, bhogies, basePrices));
            System.out.println("\033[0;32m"+"Train added successfully.");
            System.out.println("\033[0;37m");
        }
    }

    static void displaySeatAvailability() {
        System.out.print("\033[0;36m"+"Enter the train number to check seat availability: "+" \033[0;37m");
        int trainNumber = sc.nextInt();
        sc.nextLine(); // Consume newline

        if (trainNumber > 0 && trainNumber <= trains.size()) {
            Train train = trains.get(trainNumber - 1);
            train.displaySeatAvailability();
        } else {
            System.out.println("\033[0;31m"+"Invalid train number.");
            System.out.println("\033[0;37m");
        }
    }

    static void displayTrainStops() {
        System.out.print("\033[0;36m"+"Enter the train number to check stops: "+" \033[0;37m");
        int trainNumber = sc.nextInt();
        sc.nextLine(); // Consume newline

        if (trainNumber > 0 && trainNumber <= trains.size()) {
            Train train = trains.get(trainNumber - 1);
            train.displayStops();
        } else {
            System.out.println("\033[0;31m"+"Invalid train number.");
	    System.out.println("\033[0;37m");
        }
    }

    static void userLogin() {
        System.out.print("\033[0;34m"+"Signup (0) or Login (1): ");
        int option = sc.nextInt();
        sc.nextLine(); // Consume newline
	System.out.println(" \033[0;37m");
        if (option == 0) {
            signup();
        }

        System.out.print("\033[0;36m"+"ENTER UID FOR LOGIN: "+" \033[0;37m");
        String userId = sc.nextLine();
        System.out.print("\033[0;36m"+"ENTER PASSWORD FOR LOGIN: "+" \033[0;37m");
        String password = sc.nextLine();

        for (User user : users) {
            if (user.login(userId, password)) {
                System.out.println("\033[0;31m"+"SUCCESSFULLY LOGGED IN");
	        System.out.println("\033[0;37m");
                userMenu(user);
                return;
            }
        }
        System.out.println("\033[0;31m"+"Invalid User ID or Password.");
	System.out.println("\033[0;37m");
    }

    static void signup() {
        System.out.print("\033[0;36m"+"Enter the name: "+" \033[0;37m");
        String name = sc.nextLine();
        System.out.print("\033[0;36m"+"Enter the gender: "+" \033[0;37m");
        String gender = sc.nextLine();
        System.out.print("\033[0;36m"+"Enter the age: "+" \033[0;37m");
        int age = sc.nextInt();
        sc.nextLine(); // Consume newline
        System.out.print("\033[0;36m"+"Enter the email (opt): "+" \033[0;37m");
        String email = sc.nextLine();
        System.out.print("\033[0;36m"+"Enter the state: "+" \033[0;37m");
        String state = sc.nextLine();
        System.out.print("\033[0;36m"+"Enter the district: "+" \033[0;37m");
        String district = sc.nextLine();
        System.out.print("\033[0;36m"+"Enter the Pincode: "+" \033[0;37m");
        String pincode = sc.nextLine();
        System.out.print("\033[0;36m"+"Enter the Phone num (opt): "+" \033[0;37m");
        String phone = sc.nextLine();
        System.out.print("\033[0;36m"+"Enter the Pan Num (Opt): "+" \033[0;37m");
        String pan = sc.nextLine();

        System.out.println("\033[0;33m"+"------------------------------------------Create New account for USER LOGIN------------------------------------");
        System.out.print("\033[0;36m"+"Enter New USER ID: "+" \033[0;37m");
        String userId = sc.nextLine();
        System.out.print("\033[0;36m"+"Enter New Password: "+" \033[0;37m");
        String password = sc.nextLine();
        System.out.print("\033[0;36m"+"Confirm New Password: "+" \033[0;37m");
        String confirmPassword = sc.nextLine();

        if (!password.equals(confirmPassword)) {
            System.out.println("Passwords do not match.");
            return;
        }

        users.add(new User(name, gender, age, email, state, district, pincode, phone, pan, userId, password));
        System.out.println(" \033[0;32m"+"SUCCESSFULLY CREATED ACCOUNT");
	System.out.println(" \033[0;37m");
        System.out.println(" \033[0;32m"+"Congratulations! You have created an Account With Login id " + userId + " and Password as " + password);
	System.out.println(" \033[0;37m");
    }

    static void userMenu(User user) {
        while (true) {
            System.out.println("\033[0;33m"+"------------------------------User Menu------------------------------------------------------");
            System.out.println("\033[1;32m"+"1. Book Seat");
            System.out.println("2. Check Price");
            System.out.println("3. Check Train Stops");
            System.out.println("4. Check Seat Availability");
            System.out.println("5. Logout");
            System.out.println("6.check train movement");
	    System.out.println(" \033[0;37m");
            System.out.print("\033[0;34mEnter your choice: \033[0m");
	    int choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    bookSeat();
                    break;
                case 2:
                    checkPrice();
                    break;
                case 3:
                    checkTrainStops();
                    break;
                case 4:
                    checkSeatAvailability();
                    break;
                case 5:
                    return;
                case 6:
                    trainmovement();
                    break;
                default:
                    System.out.println(" \033[0;31m"+"Invalid choice. Please try again.");
		    System.out.println(" \033[0;37m");
            }
        }
    }

    static void bookSeat() {
    System.out.print("Enter the train number to book a seat: ");
    int trainNumber = sc.nextInt();
    sc.nextLine(); // Consume newline

    if (trainNumber > 0 && trainNumber <= trains.size()) {
        Train train = trains.get(trainNumber - 1);
        System.out.print(" \033[0;36m"+"Enter the Bhogie Type: "+" \033[0;37m");
        int bhogieType = sc.nextInt();
        sc.nextLine(); // Consume newline

        Bhogie bhogie = train.getBhogieByType(bhogieType);
        if (bhogie != null) {
            if (bhogie.bookSeat()) {
                System.out.println(" \033[0;32m"+"Seat booked successfully. Proceed to payment.");
		System.out.println(" \033[0;37m");
                Payment payment = new Payment();
                if (payment.processPayment()) {
                    int transactionId = ++transactionCounter;
                    System.out.println(" \033[0;32m"+"Payment successful. Transaction ID: " + transactionId);
                } else {
                    System.out.println(" \033[0;31m"+"Payment failed. Please try again.");
                }
            } else {
                System.out.println(" \033[0;32m"+"No seats available.");
            }
        } else {
            System.out.println(" \033[0;32m"+"Invalid Bhogie type.");
        }
    } else {
        System.out.println(" \033[0;32m"+"Invalid train number.");
    }
}


    static void checkPrice() {
        System.out.print(" \033[0;36m"+"Enter the train number to check price: "+" \033[0;37m");
        int trainNumber = sc.nextInt();
        sc.nextLine(); // Consume newline

        if (trainNumber > 0 && trainNumber <= trains.size()) {
            Train train = trains.get(trainNumber - 1);
            System.out.print(" \033[0;36m"+"Enter the Bhogie Type to check price: "+" \033[0;36m");
            int bhogieType = sc.nextInt();
            sc.nextLine(); // Consume newline
            System.out.println("The price for " + Bhogie.getTypeName(bhogieType) + " is: " + train.getPrice(bhogieType));
        } else {
            System.out.println(" \033[0;32m"+"Invalid train number.");
	    System.out.println(" \033[0;37m");
        }
    }

    static void checkTrainStops() {
        displayTrainStops();
    }

    static void checkSeatAvailability() {
        displaySeatAvailability();
    }

static void trainmovement() {
    System.out.println("Select Train:");
    for (int i = 1; i <= trains.size(); i++) {
        System.out.println(i + ": Train " + i);
    }
    System.out.print("Enter your choice (1-" + trains.size() + "): ");
    int trainChoice = sc.nextInt();
    sc.nextLine(); // Consume newline

    if (trainChoice < 1 || trainChoice > trains.size()) {
        System.out.println("Invalid choice. Exiting.");
        return;
    }

    int sPosition = 0;
    int ePosition = 10;

    Thread trainThread = new Thread(() -> {
        boolean isFirstPosition = true;

        for (int position = sPosition; position <= ePosition; position++) {
            clearConsole();
            generateTrain(position, ePosition + 1, trainChoice, isFirstPosition);
            isFirstPosition = false;

            try {
                if (trainChoice == 1) {
                    Thread.sleep(1000);
                } else if (trainChoice == 2) {
                    Thread.sleep(2000);
                } else {
                    Thread.sleep(1500);
                }

                if (position == 3 || position == 5) {
                    Thread.sleep(4000);
                } else if (position == 7 || position == 10) {
                    Thread.sleep(6000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Train has reached the destination.");
    });

    trainThread.start();
    try {
        trainThread.join();
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
}

public static void generateTrain(int position, int trackLength, int train, boolean isFirstPosition) {
    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    LocalDateTime now = LocalDateTime.now();
    String currentDate = now.format(dateFormatter);
    String currentTime = now.format(timeFormatter);

    for (int i = 0; i < trackLength; i++) {
        if (i == position) {
            if (train == 1) {
                System.out.print("\033[0;31mX\033[0;37m@@@ @@@ @@@|\033[1;30m0\033[0;31m!\033[1;30m0\033[0;37m| Train1");
            } else if (train == 2) {
                System.out.print("\033[0;31mX\033[0;37m@@@ @@@ @@@|\033[1;30m|\033[0;31m_\033[1;30m|\033[0;37m| Train2");
            } else {
                System.out.print("\033[0;31mX\033[0;37m@@@ @@@ @@@|\033[1;34m!\033[0;31m^ ^\033[1;34m!\033[0;37m| Train" + train);
            }
        } else {
            System.out.print("\033[0;35m_ _ _\033[0;37m");
        }
    }
    System.out.println(" " + currentDate + " " + currentTime); // Print date and time at the end of each track
}

public static void clearConsole() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
}

    // Inner classes representing different entities
    static class Admin {
        private String id;
        private String password;

        public Admin(String id, String password) {
            this.id = id;
            this.password = password;
        }

        public boolean login(String id, String password) {
            return this.id.equals(id) && this.password.equals(password);
        }
    }

    static class User {
        private String name;
        private String gender;
        private int age;
        private String email;
        private String state;
        private String district;
        private String pincode;
        private String phone;
        private String pan;
        private String userId;
        private String password;

        public User(String name, String gender, int age, String email, String state, String district, String pincode, String phone, String pan, String userId, String password) {
            this.name = name;
            this.gender = gender;
            this.age = age;
            this.email = email;
            this.state = state;
            this.district = district;
            this.pincode = pincode;
            this.phone = phone;
            this.pan = pan;
            this.userId = userId;
            this.password = password;
        }

        public boolean login(String userId, String password) {
            return this.userId.equals(userId) && this.password.equals(password);
        }
    }

    static class Train {
        private String name;
        private String startPoint;
        private String endPoint;
        private List<String> stops;
        private List<Bhogie> bhogies;
        private int[] basePrices;

        public Train(String name, String startPoint, String endPoint, List<String> stops, List<Bhogie> bhogies, int[] basePrices) {
            this.name = name;
            this.startPoint = startPoint;
            this.endPoint = endPoint;
            this.stops = stops;
            this.bhogies = bhogies;
            this.basePrices = basePrices;
        }

        public void displaySeatAvailability() {
            for (Bhogie bhogie : bhogies) {
                System.out.println("Bhogie Type: " + Bhogie.getTypeName(bhogie.getType()));
                bhogie.displaySeatAvailability();
            }
        }

        public void displayStops() {
            System.out.println("Train Stops:");
            for (String stop : stops) {
                System.out.println(stop);
            }
        }

        public Bhogie getBhogieByType(int type) {
            for (Bhogie bhogie : bhogies) {
                if (bhogie.getType() == type) {
                    return bhogie;
                }
            }
            return null;
        }

        public int getPrice(int type) {
            return basePrices[type - 1];
        }
    }

    static class Bhogie {
        private int type;
        private int rows;
        private int cols;
        private boolean[][] seats;

        public Bhogie(int type, int rows, int cols) {
            this.type = type;
            this.rows = rows;
            this.cols = cols;
            this.seats = new boolean[rows][cols];
        }

        public int getType() {
            return type;
        }

        public boolean bookSeat() {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (!seats[i][j]) {
                        seats[i][j] = true;
                        return true;
                    }
                }
            }
            return false;
        }

        public void displaySeatAvailability() {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    System.out.print(seats[i][j] ? "[X] " : "[O] ");
                }
                System.out.println();
            }
        }

        public static String getTypeName(int type) {
            switch (type) {
                case 1:
                    return "AC 1 Tier";
                case 2:
                    return "AC 2 Tier";
                case 3:
                    return "AC 3 Tier";
                case 4:
                    return "Sleeper";
                case 5:
                    return "Second Class";
                case 6:
                    return "General";
                default:
                    return "Unknown";
            }
        }
    }

    static class Payment {
        public boolean processPayment() {
            System.out.println(" \033[0;32m"+"Processing payment...");
	    System.out.println(" \033[0;37m");
            System.out.print("Enter amount: ");
            int amount = sc.nextInt();
            sc.nextLine(); // Consume newline
            System.out.print(" \033[0;34m"+"Enter payment method: "+" \033[0;37m");
            String method = sc.nextLine();

            // Here, you can implement the actual payment processing logic.
            // For now, we will assume the payment is always successful.
            return true;
        }
    }

    static class Cancel {
        public void processCancellation() {
            System.out.println(" \033[0;31m"+"Processing cancellation...");
            // Implement cancellation logic here.
        }
    }
}
