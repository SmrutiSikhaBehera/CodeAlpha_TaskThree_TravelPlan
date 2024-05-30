import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Travelplan {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Destination> destinations = new ArrayList<>();

        while (true) {
            System.out.println("Enter destination name (or 'done' to finish): ");
            String name = scanner.nextLine();
            if (name.equalsIgnoreCase("done")) {
                break;
            }

            System.out.println("Enter arrival date (yyyy-mm-dd): ");
            LocalDate arrivalDate = LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ISO_LOCAL_DATE);

            System.out.println("Enter departure date (yyyy-mm-dd): ");
            LocalDate departureDate = LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ISO_LOCAL_DATE);

            destinations.add(new Destination(name, arrivalDate, departureDate));
        }

        System.out.println("Do you prefer sunny weather? (yes/no): ");
        boolean prefersSunnyWeather = scanner.nextLine().equalsIgnoreCase("yes");

        System.out.println("Enter your maximum budget: ");
        double maxBudget = scanner.nextDouble();

        UserPreferences preferences = new UserPreferences(prefersSunnyWeather, maxBudget);

        WeatherService weatherService = new WeatherService();
        BudgetCalculator budgetCalculator = new BudgetCalculator();

        System.out.println("\nItinerary Details:");
        for (Destination destination : destinations) {
            WeatherInfo weatherInfo = weatherService.getWeather(destination.getName(), destination.getArrivalDate());
            System.out.println("Destination: " + destination.getName());
            System.out.println("Arrival Date: " + destination.getArrivalDate());
            System.out.println("Departure Date: " + destination.getDepartureDate());
            System.out.println("Weather on arrival: " + weatherInfo.getCondition() + ", " + weatherInfo.getTemperature() + "°C\n");
        }

        double totalBudget = budgetCalculator.calculateTotalBudget(destinations, preferences);
        System.out.println("Total Budget: $" + totalBudget);
    }

    // Model Classes
    public static class Destination {
        private String name;
        private LocalDate arrivalDate;
        private LocalDate departureDate;

        public Destination(String name, LocalDate arrivalDate, LocalDate departureDate) {
            this.name = name;
            this.arrivalDate = arrivalDate;
            this.departureDate = departureDate;
        }

        public String getName() {
            return name;
        }

        public LocalDate getArrivalDate() {
            return arrivalDate;
        }

        public LocalDate getDepartureDate() {
            return departureDate;
        }
    }

    public static class UserPreferences {
        private boolean prefersSunnyWeather;
        private double maxBudget;

        public UserPreferences(boolean prefersSunnyWeather, double maxBudget) {
            this.prefersSunnyWeather = prefersSunnyWeather;
            this.maxBudget = maxBudget;
        }

        public boolean isPrefersSunnyWeather() {
            return prefersSunnyWeather;
        }

        public double getMaxBudget() {
            return maxBudget;
        }
    }

    // Service Classes
    public static class WeatherService {
        public WeatherInfo getWeather(String location, LocalDate date) {
            // Placeholder for fetching weather info (e.g., using an API)
            return new WeatherInfo(location, date, "Sunny", 25);
        }
    }

    public static class BudgetCalculator {
        public double calculateTotalBudget(List<Destination> destinations, UserPreferences preferences) {
            // Placeholder for budget calculation
            double totalBudget = 0.0;
            for (Destination destination : destinations) {
                totalBudget += 100; // Example cost per destination
            }
            return totalBudget;
        }
    }

    // Utility Classes
    public static class DateUtil {
        public static long daysBetween(LocalDate start, LocalDate end) {
            return ChronoUnit.DAYS.between(start, end);
        }
    }

    // Weather Info Class
    public static class WeatherInfo {
        private String location;
        private LocalDate date;
        private String condition;
        private int temperature;

        public WeatherInfo(String location, LocalDate date, String condition, int temperature) {
            this.location = location;
            this.date = date;
            this.condition = condition;
            this.temperature = temperature;
        }

        public String getLocation() {
            return location;
        }

        public LocalDate getDate() {
            return date;
        }

        public String getCondition() {
            return condition;
        }

        public int getTemperature() {
            return temperature;
        }
    }
}