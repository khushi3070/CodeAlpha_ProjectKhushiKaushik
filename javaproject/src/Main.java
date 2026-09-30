import java.io.*;
import java.util.*;

public class Main {

    // =========================
    // ROOM CLASS
    // =========================
    static class Room implements Serializable {
        private int roomNumber;
        private String category;
        private double price;
        private boolean available;

        public Room(int roomNumber, String category, double price) {
            this.roomNumber = roomNumber;
            this.category = category;
            this.price = price;
            this.available = true;
        }

        public int getRoomNumber() {
            return roomNumber;
        }

        public String getCategory() {
            return category;
        }

        public double getPrice() {
            return price;
        }

        public boolean isAvailable() {
            return available;
        }

        public void setAvailable(boolean available) {
            this.available = available;
        }

        @Override
        public String toString() {
            return "Room " + roomNumber +
                    " | Category: " + category +
                    " | Price: Rs." + price +
                    " per night" +
                    " | " + (available ? "Available" : "Booked");
        }
    }


    // =========================
    // RESERVATION CLASS
    // =========================
    static class Reservation implements Serializable {
        private int reservationId;
        private String customerName;
        private String phone;
        private int roomNumber;
        private String roomCategory;
        private int nights;
        private double totalAmount;

        public Reservation(int reservationId,
                           String customerName,
                           String phone,
                           int roomNumber,
                           String roomCategory,
                           int nights,
                           double totalAmount) {

            this.reservationId = reservationId;
            this.customerName = customerName;
            this.phone = phone;
            this.roomNumber = roomNumber;
            this.roomCategory = roomCategory;
            this.nights = nights;
            this.totalAmount = totalAmount;
        }

        public int getReservationId() {
            return reservationId;
        }

        public int getRoomNumber() {
            return roomNumber;
        }

        public void displayDetails() {
            System.out.println("\n========== RESERVATION DETAILS ==========");
            System.out.println("Reservation ID : " + reservationId);
            System.out.println("Customer Name  : " + customerName);
            System.out.println("Phone Number   : " + phone);
            System.out.println("Room Number    : " + roomNumber);
            System.out.println("Room Category  : " + roomCategory);
            System.out.println("Number of Nights: " + nights);
            System.out.println("Total Amount   : Rs." + totalAmount);
            System.out.println("Status         : Confirmed");
            System.out.println("==========================================");
        }

        @Override
        public String toString() {
            return "Reservation ID: " + reservationId +
                    " | Customer: " + customerName +
                    " | Room: " + roomNumber +
                    " | Category: " + roomCategory +
                    " | Nights: " + nights +
                    " | Amount: Rs." + totalAmount;
        }
    }


    // =========================
    // HOTEL CLASS
    // =========================
    static class Hotel {
        private ArrayList<Room> rooms;
        private ArrayList<Reservation> reservations;

        private final String FILE_NAME = "hotel_bookings.dat";

        public Hotel() {
            rooms = new ArrayList<>();
            reservations = new ArrayList<>();

            initializeRooms();
            loadReservations();
        }

        // Create hotel rooms
        private void initializeRooms() {

            // Standard Rooms
            for (int i = 101; i <= 105; i++) {
                rooms.add(new Room(i, "Standard", 1500));
            }

            // Deluxe Rooms
            for (int i = 201; i <= 205; i++) {
                rooms.add(new Room(i, "Deluxe", 2500));
            }

            // Suite Rooms
            for (int i = 301; i <= 303; i++) {
                rooms.add(new Room(i, "Suite", 4000));
            }

            // Mark already booked rooms
            for (Reservation reservation : reservations) {
                for (Room room : rooms) {
                    if (room.getRoomNumber() == reservation.getRoomNumber()) {
                        room.setAvailable(false);
                    }
                }
            }
        }


        // =========================
        // SEARCH ROOMS
        // =========================
        public void searchRooms() {

            Scanner scanner = new Scanner(System.in);

            System.out.println("\n========== SEARCH ROOMS ==========");
            System.out.println("1. All Rooms");
            System.out.println("2. Standard");
            System.out.println("3. Deluxe");
            System.out.println("4. Suite");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            String category = "";

            if (choice == 2) {
                category = "Standard";
            } else if (choice == 3) {
                category = "Deluxe";
            } else if (choice == 4) {
                category = "Suite";
            }

            System.out.println("\nAvailable Rooms:");

            boolean found = false;

            for (Room room : rooms) {

                if (room.isAvailable() &&
                        (category.isEmpty() ||
                                room.getCategory().equalsIgnoreCase(category))) {

                    System.out.println(room);
                    found = true;
                }
            }

            if (!found) {
                System.out.println("No rooms available.");
            }
        }


        // =========================
        // BOOK ROOM
        // =========================
        public void bookRoom() {

            Scanner scanner = new Scanner(System.in);

            System.out.println("\n========== BOOK A ROOM ==========");

            // Display available rooms
            boolean availableRoom = false;

            for (Room room : rooms) {
                if (room.isAvailable()) {
                    System.out.println(room);
                    availableRoom = true;
                }
            }

            if (!availableRoom) {
                System.out.println("Sorry! No rooms are currently available.");
                return;
            }

            System.out.print("\nEnter room number: ");
            int roomNumber = scanner.nextInt();
            scanner.nextLine();

            Room selectedRoom = null;

            for (Room room : rooms) {
                if (room.getRoomNumber() == roomNumber &&
                        room.isAvailable()) {

                    selectedRoom = room;
                    break;
                }
            }

            if (selectedRoom == null) {
                System.out.println("Invalid room number or room is already booked.");
                return;
            }

            System.out.print("Enter customer name: ");
            String name = scanner.nextLine();

            System.out.print("Enter phone number: ");
            String phone = scanner.nextLine();

            System.out.print("Enter number of nights: ");
            int nights = scanner.nextInt();

            if (nights <= 0) {
                System.out.println("Number of nights must be greater than 0.");
                return;
            }

            double totalAmount = selectedRoom.getPrice() * nights;

            System.out.println("\n========== BILL ==========");
            System.out.println("Room Category : " + selectedRoom.getCategory());
            System.out.println("Price/Night   : Rs." + selectedRoom.getPrice());
            System.out.println("Nights        : " + nights);
            System.out.println("Total Amount  : Rs." + totalAmount);
            System.out.println("==========================");

            System.out.print("Confirm booking? (Y/N): ");
            char confirm = scanner.next().charAt(0);

            if (confirm == 'Y' || confirm == 'y') {

                // Payment simulation
                System.out.println("\nProcessing payment...");
                System.out.println("Payment successful!");

                int reservationId = generateReservationId();

                Reservation reservation =
                        new Reservation(
                                reservationId,
                                name,
                                phone,
                                roomNumber,
                                selectedRoom.getCategory(),
                                nights,
                                totalAmount
                        );

                reservations.add(reservation);

                selectedRoom.setAvailable(false);

                saveReservations();

                System.out.println("\nRoom booked successfully!");
                System.out.println("Your Reservation ID is: " + reservationId);

                reservation.displayDetails();

            } else {
                System.out.println("Booking cancelled.");
            }
        }


        // =========================
        // GENERATE RESERVATION ID
        // =========================
        private int generateReservationId() {

            if (reservations.isEmpty()) {
                return 1001;
            }

            return reservations.get(reservations.size() - 1)
                    .getReservationId() + 1;
        }


        // =========================
        // CANCEL RESERVATION
        // =========================
        public void cancelReservation() {

            Scanner scanner = new Scanner(System.in);

            System.out.println("\n========== CANCEL RESERVATION ==========");

            System.out.print("Enter Reservation ID: ");
            int id = scanner.nextInt();

            Reservation foundReservation = null;

            for (Reservation reservation : reservations) {

                if (reservation.getReservationId() == id) {
                    foundReservation = reservation;
                    break;
                }
            }

            if (foundReservation == null) {
                System.out.println("Reservation not found.");
                return;
            }

            // Make room available again
            for (Room room : rooms) {

                if (room.getRoomNumber() ==
                        foundReservation.getRoomNumber()) {

                    room.setAvailable(true);
                    break;
                }
            }

            reservations.remove(foundReservation);

            saveReservations();

            System.out.println("Reservation cancelled successfully.");
            System.out.println("Room " +
                    foundReservation.getRoomNumber() +
                    " is now available.");
        }


        // =========================
        // VIEW RESERVATIONS
        // =========================
        public void viewReservations() {

            System.out.println("\n========== ALL RESERVATIONS ==========");

            if (reservations.isEmpty()) {
                System.out.println("No reservations found.");
                return;
            }

            for (Reservation reservation : reservations) {
                System.out.println(reservation);
            }
        }


        // =========================
        // VIEW RESERVATION DETAILS
        // =========================
        public void viewReservationDetails() {

            Scanner scanner = new Scanner(System.in);

            System.out.println("\n========== VIEW RESERVATION ==========");

            System.out.print("Enter Reservation ID: ");
            int id = scanner.nextInt();

            for (Reservation reservation : reservations) {

                if (reservation.getReservationId() == id) {
                    reservation.displayDetails();
                    return;
                }
            }

            System.out.println("Reservation not found.");
        }


        // =========================
        // SAVE DATA TO FILE
        // =========================
        private void saveReservations() {

            try {

                ObjectOutputStream output =
                        new ObjectOutputStream(
                                new FileOutputStream(FILE_NAME));

                output.writeObject(reservations);

                output.close();

            } catch (IOException e) {

                System.out.println(
                        "Error saving booking data: "
                                + e.getMessage());
            }
        }


        // =========================
        // LOAD DATA FROM FILE
        // =========================
        @SuppressWarnings("unchecked")
        private void loadReservations() {

            File file = new File(FILE_NAME);

            if (!file.exists()) {
                return;
            }

            try {

                ObjectInputStream input =
                        new ObjectInputStream(
                                new FileInputStream(FILE_NAME));

                reservations =
                        (ArrayList<Reservation>) input.readObject();

                input.close();

            } catch (IOException | ClassNotFoundException e) {

                System.out.println(
                        "Could not load previous booking data.");
            }
        }
    }


    // =========================
    // MAIN METHOD
    // =========================
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Hotel hotel = new Hotel();

        int choice;

        System.out.println("==========================================");
        System.out.println("       WELCOME TO JAVA HOTEL SYSTEM       ");
        System.out.println("==========================================");

        do {

            System.out.println("\n========== HOTEL RESERVATION SYSTEM ==========");
            System.out.println("1. Search Available Rooms");
            System.out.println("2. Book a Room");
            System.out.println("3. Cancel Reservation");
            System.out.println("4. View All Reservations");
            System.out.println("5. View Reservation Details");
            System.out.println("6. Exit");
            System.out.println("==============================================");

            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {

                case 1:
                    hotel.searchRooms();
                    break;

                case 2:
                    hotel.bookRoom();
                    break;

                case 3:
                    hotel.cancelReservation();
                    break;

                case 4:
                    hotel.viewReservations();
                    break;

                case 5:
                    hotel.viewReservationDetails();
                    break;

                case 6:
                    System.out.println(
                            "\nThank you for using the Hotel Reservation System!");
                    break;

                default:
                    System.out.println(
                            "Invalid choice! Please try again.");
            }

        } while (choice != 6);

        scanner.close();
    }
}