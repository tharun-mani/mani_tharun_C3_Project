import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kowsh
 */
public class RestaurantService {
    private static final List<Restaurant> restaurants = new ArrayList<>();

    /**
     *
     * @param restaurantName
     * @return
     * @throws restaurantNotFoundException
     */
    public Restaurant findRestaurantByName(String restaurantName) throws restaurantNotFoundException{
    String search = restaurantName;
    int searchListLength = restaurants.size();
    for (int i = 0; i < searchListLength; i++) {
        if (restaurants.get(i).getName().contains(search)) {
            return restaurants.get(i);  // Found the restaurant, return it.
        }
    }
    throw new restaurantNotFoundException(search);  // If no restaurant is found, throw an exception.
}

    public Restaurant addRestaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        Restaurant newRestaurant = new Restaurant(name, location, openingTime, closingTime);
        restaurants.add(newRestaurant);
        return newRestaurant;
    }

    public Restaurant removeRestaurant(String restaurantName) throws restaurantNotFoundException {
        Restaurant restaurantToBeRemoved = findRestaurantByName(restaurantName);
        restaurants.remove(restaurantToBeRemoved);
        return restaurantToBeRemoved;
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

}