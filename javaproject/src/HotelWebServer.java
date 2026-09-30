import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class HotelWebServer {

    static ArrayList<Room> rooms = new ArrayList<>();
    static ArrayList<Reservation> reservations = new ArrayList<>();

    static int nextReservationId = 1001;

    static final String FILE_NAME = "hotel_bookings.dat";

    // =========================
    // ROOM CLASS
    // =========================
    static class Room implements Serializable {

        int number;
        String category;
        double price;
        boolean available;

        Room(int number, String category, double price) {
            this.number = number;
            this.category = category;
            this.price = price;
            this.available = true;
        }
    }

    // =========================
    // RESERVATION CLASS
    // =========================
    static class Reservation implements Serializable {

        int id;
        String name;
        String phone;
        int roomNumber;
        String category;
        int nights;
        double amount;

        Reservation(int id, String name, String phone,
                    int roomNumber, String category,
                    int nights, double amount) {

            this.id = id;
            this.name = name;
            this.phone = phone;
            this.roomNumber = roomNumber;
            this.category = category;
            this.nights = nights;
            this.amount = amount;
        }
    }

    // =========================
    // MAIN
    // =========================
    public static void main(String[] args) throws IOException {

        initializeRooms();
        loadData();

        HttpServer server =
                HttpServer.create(new InetSocketAddress(8080), 0);

        server.createContext("/", HotelWebServer::home);
        server.createContext("/rooms", HotelWebServer::rooms);
        server.createContext("/book", HotelWebServer::book);
        server.createContext("/reservations", HotelWebServer::reservations);
        server.createContext("/cancel", HotelWebServer::cancel);

        server.setExecutor(null);
        server.start();

        System.out.println("--------------------------------");
        System.out.println(" HOTEL RESERVATION WEB SERVER");
        System.out.println("--------------------------------");
        System.out.println("Server started successfully!");
        System.out.println("Open: http://localhost:8080");
    }

    // =========================
    // INITIALIZE ROOMS
    // =========================
    static void initializeRooms() {

        if (!rooms.isEmpty()) {
            return;
        }

        for (int i = 101; i <= 105; i++) {
            rooms.add(new Room(i, "Standard", 1500));
        }

        for (int i = 201; i <= 205; i++) {
            rooms.add(new Room(i, "Deluxe", 2500));
        }

        for (int i = 301; i <= 303; i++) {
            rooms.add(new Room(i, "Suite", 4000));
        }
    }

    // =========================
    // HOME PAGE
    // =========================
    static void home(HttpExchange exchange) throws IOException {

        String html = page(
                "Hotel Reservation System",

                "<div class='hero'>" +
                        "<h1>🏨 Hotel Reservation System</h1>" +
                        "<p>Book your perfect room with us</p>" +
                        "</div>" +

                        "<div class='grid'>" +

                        "<div class='card'>" +
                        "<h2>🔎 Search Rooms</h2>" +
                        "<p>Check available Standard, Deluxe and Suite rooms.</p>" +
                        "<a href='/rooms' class='button'>Search Rooms</a>" +
                        "</div>" +

                        "<div class='card'>" +
                        "<h2>🏨 Book a Room</h2>" +
                        "<p>Reserve your preferred hotel room.</p>" +
                        "<a href='/book' class='button'>Book Room</a>" +
                        "</div>" +

                        "<div class='card'>" +
                        "<h2>📋 Reservations</h2>" +
                        "<p>View all current reservations.</p>" +
                        "<a href='/reservations' class='button'>View Reservations</a>" +
                        "</div>" +

                        "<div class='card'>" +
                        "<h2>❌ Cancel Booking</h2>" +
                        "<p>Cancel an existing reservation.</p>" +
                        "<a href='/cancel' class='button'>Cancel Booking</a>" +
                        "</div>" +

                        "</div>"
        );

        send(exchange, html);
    }

    // =========================
    // SEARCH ROOMS
    // =========================
    static void rooms(HttpExchange exchange) throws IOException {

        StringBuilder content = new StringBuilder();

        content.append("<h1>🔎 Available Rooms</h1>");
        content.append("<p>Select a room to make a reservation.</p>");

        boolean found = false;

        for (Room room : rooms) {

            if (room.available) {

                found = true;

                content.append(
                        "<div class='room'>" +
                                "<h2>Room " + room.number + "</h2>" +
                                "<p><b>Category:</b> " +
                                room.category + "</p>" +
                                "<p><b>Price:</b> ₹" +
                                room.price + " / night</p>" +
                                "<p class='available'>✓ Available</p>" +
                                "<a class='button' href='/book?room=" +
                                room.number +
                                "'>Book This Room</a>" +
                                "</div>"
                );
            }
        }

        if (!found) {
            content.append(
                    "<div class='card'>" +
                            "<h2>No rooms available</h2>" +
                            "</div>"
            );
        }

        content.append(
                "<br><a href='/' class='back'>← Back to Home</a>"
        );

        send(exchange, page("Available Rooms",
                content.toString()));
    }

    // =========================
    // BOOK ROOM
    // =========================
    static void book(HttpExchange exchange) throws IOException {

        Map<String, String> params =
                queryParameters(exchange);

        String selectedRoom =
                params.getOrDefault("room", "");

        StringBuilder roomOptions = new StringBuilder();

        for (Room room : rooms) {

            if (room.available) {

                String selected =
                        String.valueOf(room.number)
                                .equals(selectedRoom)
                                ? "selected"
                                : "";

                roomOptions.append(
                        "<option value='" +
                                room.number +
                                "' " +
                                selected +
                                ">" +
                                room.number +
                                " - " +
                                room.category +
                                " (₹" +
                                room.price +
                                "/night)" +
                                "</option>"
                );
            }
        }

        String content =

                "<h1>🏨 Book a Room</h1>" +

                        "<form method='POST' action='/book'>" +

                        "<label>Customer Name</label>" +
                        "<input name='name' required>" +

                        "<label>Phone Number</label>" +
                        "<input name='phone' required>" +

                        "<label>Select Room</label>" +
                        "<select name='room' required>" +
                        roomOptions +
                        "</select>" +

                        "<label>Number of Nights</label>" +
                        "<input type='number' name='nights' min='1' required>" +

                        "<button class='button' type='submit'>" +
                        "Confirm Booking" +
                        "</button>" +

                        "</form>" +

                        "<br><a href='/' class='back'>← Back to Home</a>";

        // If POST request
        if (exchange.getRequestMethod().equalsIgnoreCase("POST")) {

            String body =
                    new String(
                            exchange.getRequestBody().readAllBytes(),
                            StandardCharsets.UTF_8
                    );

            Map<String, String> form =
                    parseData(body);

            String name = form.get("name");
            String phone = form.get("phone");

            int roomNumber =
                    Integer.parseInt(form.get("room"));

            int nights =
                    Integer.parseInt(form.get("nights"));

            Room selected = findRoom(roomNumber);

            if (selected == null || !selected.available) {

                send(exchange,
                        page("Error",
                                "<h1>❌ Room Not Available</h1>" +
                                        "<a href='/rooms' class='button'>" +
                                        "Back to Rooms</a>"));

                return;
            }

            double total =
                    selected.price * nights;

            Reservation reservation =
                    new Reservation(
                            nextReservationId++,
                            name,
                            phone,
                            roomNumber,
                            selected.category,
                            nights,
                            total
                    );

            reservations.add(reservation);

            selected.available = false;

            saveData();

            String confirmation =

                    "<div class='success'>" +
                            "<h1>✅ Booking Successful!</h1>" +
                            "<h2>Reservation ID: " +
                            reservation.id +
                            "</h2>" +

                            "<p><b>Customer:</b> " +
                            name + "</p>" +

                            "<p><b>Room:</b> " +
                            roomNumber + "</p>" +

                            "<p><b>Category:</b> " +
                            selected.category + "</p>" +

                            "<p><b>Nights:</b> " +
                            nights + "</p>" +

                            "<p><b>Total Amount:</b> ₹" +
                            total + "</p>" +

                            "<p>💳 Payment Status: Successful</p>" +

                            "</div>" +

                            "<a href='/' class='button'>" +
                            "Back to Home</a>";

            send(exchange,
                    page("Booking Successful",
                            confirmation));

            return;
        }

        send(exchange, page("Book Room", content));
    }

    // =========================
    // VIEW RESERVATIONS
    // =========================
    static void reservations(HttpExchange exchange)
            throws IOException {

        StringBuilder content =
                new StringBuilder();

        content.append("<h1>📋 All Reservations</h1>");

        if (HotelWebServer.reservations.isEmpty()) {

            content.append(
                    "<div class='card'>" +
                            "<h2>No reservations found.</h2>" +
                            "</div>"
            );

        } else {

            for (Reservation r :
                    HotelWebServer.reservations) {

                content.append(
                        "<div class='reservation'>" +

                                "<h2>Reservation #" +
                                r.id +
                                "</h2>" +

                                "<p><b>Customer:</b> " +
                                r.name +
                                "</p>" +

                                "<p><b>Phone:</b> " +
                                r.phone +
                                "</p>" +

                                "<p><b>Room:</b> " +
                                r.roomNumber +
                                "</p>" +

                                "<p><b>Category:</b> " +
                                r.category +
                                "</p>" +

                                "<p><b>Nights:</b> " +
                                r.nights +
                                "</p>" +

                                "<p><b>Total:</b> ₹" +
                                r.amount +
                                "</p>" +

                                "</div>"
                );
            }
        }

        content.append(
                "<br><a href='/' class='back'>" +
                        "← Back to Home</a>"
        );

        send(exchange,
                page("Reservations",
                        content.toString()));
    }

    // =========================
    // CANCEL RESERVATION
    // =========================
    static void cancel(HttpExchange exchange)
            throws IOException {

        if (exchange.getRequestMethod()
                .equalsIgnoreCase("POST")) {

            String body =
                    new String(
                            exchange.getRequestBody().readAllBytes(),
                            StandardCharsets.UTF_8
                    );

            Map<String, String> form =
                    parseData(body);

            int id =
                    Integer.parseInt(form.get("id"));

            Reservation found = null;

            for (Reservation r :
                    reservations) {

                if (r.id == id) {
                    found = r;
                    break;
                }
            }

            if (found == null) {

                send(exchange,
                        page("Not Found",
                                "<h1>❌ Reservation Not Found</h1>" +
                                        "<a href='/cancel' class='button'>" +
                                        "Try Again</a>"));

                return;
            }

            Room room =
                    findRoom(found.roomNumber);

            if (room != null) {
                room.available = true;
            }

            reservations.remove(found);

            saveData();

            send(exchange,
                    page("Cancelled",
                            "<div class='success'>" +
                                    "<h1>✅ Reservation Cancelled</h1>" +
                                    "<p>Reservation #" +
                                    id +
                                    " has been cancelled.</p>" +
                                    "<p>Room " +
                                    found.roomNumber +
                                    " is now available.</p>" +
                                    "</div>" +

                                    "<a href='/' class='button'>" +
                                    "Back to Home</a>"));

            return;
        }

        String content =

                "<h1>❌ Cancel Reservation</h1>" +

                        "<form method='POST' action='/cancel'>" +

                        "<label>Reservation ID</label>" +

                        "<input type='number' " +
                        "name='id' required>" +

                        "<button class='button' " +
                        "type='submit'>" +
                        "Cancel Reservation" +
                        "</button>" +

                        "</form>" +

                        "<br><a href='/' class='back'>" +
                        "← Back to Home</a>";

        send(exchange,
                page("Cancel Reservation",
                        content));
    }

    // =========================
    // FIND ROOM
    // =========================
    static Room findRoom(int number) {

        for (Room room : rooms) {

            if (room.number == number) {
                return room;
            }
        }

        return null;
    }

    // =========================
    // SAVE DATA
    // =========================
    static void saveData() {

        try {

            ObjectOutputStream out =
                    new ObjectOutputStream(
                            new FileOutputStream(FILE_NAME));

            out.writeObject(reservations);

            out.close();

        } catch (IOException e) {

            System.out.println(
                    "Error saving data: " +
                            e.getMessage());
        }
    }

    // =========================
    // LOAD DATA
    // =========================
    @SuppressWarnings("unchecked")
    static void loadData() {

        File file =
                new File(FILE_NAME);

        if (!file.exists()) {
            return;
        }

        try {

            ObjectInputStream in =
                    new ObjectInputStream(
                            new FileInputStream(FILE_NAME));

            reservations =
                    (ArrayList<Reservation>)
                            in.readObject();

            in.close();

            for (Reservation r :
                    reservations) {

                Room room =
                        findRoom(r.roomNumber);

                if (room != null) {
                    room.available = false;
                }

                if (r.id >= nextReservationId) {
                    nextReservationId =
                            r.id + 1;
                }
            }

        } catch (Exception e) {

            System.out.println(
                    "Could not load previous bookings.");
        }
    }

    // =========================
    // PARSE FORM DATA
    // =========================
    static Map<String, String> parseData(String data) {

        Map<String, String> map =
                new HashMap<>();

        for (String pair :
                data.split("&")) {

            String[] parts =
                    pair.split("=", 2);

            if (parts.length == 2) {

                map.put(
                        URLDecoder.decode(
                                parts[0],
                                StandardCharsets.UTF_8
                        ),

                        URLDecoder.decode(
                                parts[1],
                                StandardCharsets.UTF_8
                        )
                );
            }
        }

        return map;
    }

    // =========================
    // QUERY PARAMETERS
    // =========================
    static Map<String, String> queryParameters(
            HttpExchange exchange) {

        String query =
                exchange.getRequestURI()
                        .getRawQuery();

        if (query == null) {
            return new HashMap<>();
        }

        return parseData(query);
    }

    // =========================
    // HTML PAGE
    // =========================
    static String page(
            String title,
            String content) {

        return """
                <!DOCTYPE html>
                <html>
                <head>
                    <meta charset="UTF-8">
                    <title>%s</title>

                    <style>

                        * {
                            box-sizing: border-box;
                        }

                        body {
                            margin: 0;
                            font-family: Arial, sans-serif;
                            background: #f2f4f7;
                            color: #333;
                        }

                        header {
                            background: #26384a;
                            color: white;
                            padding: 30px;
                            text-align: center;
                        }

                        header h1 {
                            margin: 0;
                        }

                        .container {
                            width: 90%%;
                            max-width: 900px;
                            margin: 40px auto;
                            text-align: center;
                        }

                        .hero {
                            background: #26384a;
                            color: white;
                            padding: 40px;
                            border-radius: 12px;
                            margin-bottom: 30px;
                        }

                        .hero h1 {
                            font-size: 35px;
                        }

                        .grid {
                            display: grid;
                            grid-template-columns:
                            repeat(auto-fit, minmax(250px, 1fr));
                            gap: 20px;
                        }

                        .card,
                        .room,
                        .reservation {
                            background: white;
                            padding: 25px;
                            margin: 20px 0;
                            border-radius: 12px;
                            box-shadow:
                            0 4px 12px rgba(0,0,0,0.1);
                        }

                        .button {
                            display: inline-block;
                            background: #3498db;
                            color: white;
                            text-decoration: none;
                            border: none;
                            padding: 12px 22px;
                            border-radius: 7px;
                            cursor: pointer;
                            font-size: 16px;
                            margin-top: 10px;
                        }

                        .button:hover {
                            background: #2980b9;
                        }

                        form {
                            background: white;
                            padding: 30px;
                            border-radius: 12px;
                            max-width: 500px;
                            margin: auto;
                            box-shadow:
                            0 4px 12px rgba(0,0,0,0.1);
                        }

                        label {
                            display: block;
                            text-align: left;
                            margin-top: 15px;
                            font-weight: bold;
                        }

                        input,
                        select {
                            width: 100%%;
                            padding: 12px;
                            margin-top: 7px;
                            border: 1px solid #ccc;
                            border-radius: 6px;
                            font-size: 16px;
                        }

                        .available {
                            color: green;
                            font-weight: bold;
                        }

                        .success {
                            background: white;
                            padding: 30px;
                            border-radius: 12px;
                            box-shadow:
                            0 4px 12px rgba(0,0,0,0.1);
                            margin-bottom: 25px;
                        }

                        .back {
                            color: #3498db;
                            text-decoration: none;
                        }

                    </style>
                </head>

                <body>

                    <header>
                        <h1>🏨 Hotel Reservation System</h1>
                    </header>

                    <div class="container">

                        %s

                    </div>

                </body>
                </html>
                """.formatted(title, content);
    }

    // =========================
    // SEND RESPONSE
    // =========================
    static void send(
            HttpExchange exchange,
            String response) throws IOException {

        byte[] bytes =
                response.getBytes(
                        StandardCharsets.UTF_8);

        exchange.getResponseHeaders()
                .set("Content-Type",
                        "text/html; charset=UTF-8");

        exchange.sendResponseHeaders(
                200,
                bytes.length
        );

        OutputStream output =
                exchange.getResponseBody();

        output.write(bytes);
        output.close();
    }
}