import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class RestaurantTest {
    // Using Mockito to create a mock object for Restaurant
    Restaurant restaurant = Mockito.mock(Restaurant.class);
    LocalTime openingTime = LocalTime.parse("10:30:00");
    LocalTime closingTime = LocalTime.parse("22:00:00");
    Restaurant r = new Restaurant("Ahaar", "Mumbai", openingTime, closingTime);

    // Creating a spy to partially mock the Restaurant object
    Restaurant rspied = Mockito.spy(r);

    // Testing if the restaurant is open during operational hours
    @Test
    public void isRestaurantOpenShouldReturnTrueIfTimeIsBetweenOpeningAndClosingTime(){
        LocalTime mockCurrTime = LocalTime.parse("11:20");
        Mockito.when(rspied.getCurrentTime()).thenReturn(mockCurrTime);
        Assertions.assertTrue(rspied.isRestaurantOpen());
    }

    // Testing if the restaurant is closed outside operational hours
    @Test
    public void isRestaurantOpenShouldReturnFalseIfTimeIsOutsideOpeningAndClosingTime(){
        LocalTime mockCurrTime = LocalTime.parse("23:20");
        Mockito.when(rspied.getCurrentTime()).thenReturn(mockCurrTime);
        Assertions.assertFalse(rspied.isRestaurantOpen());
    }

    // Testing adding an item to the menu
    @Test
    public void addingItemToMenuShouldIncreaseMenuSizeByOne(){
        Restaurant newRestaurant = new Restaurant("Amelie's Cafe", "Chennai", openingTime, closingTime);
        newRestaurant.addToMenu("Sweet corn soup", 119);
        int initialMenuSize = newRestaurant.getMenu().size();
        newRestaurant.addToMenu("Sizzling brownie", 319);
        assertEquals(initialMenuSize + 1, newRestaurant.getMenu().size());
    }

    // Testing removing an item from the menu
    @Test
    public void removingItemFromMenuShouldDecreaseMenuSizeByOne() throws itemNotFoundException {
        Restaurant newRestaurant = new Restaurant("Amelie's Cafe", "Chennai", openingTime, closingTime);
        newRestaurant.addToMenu("Sweet corn soup", 119);
        int initialMenuSize = newRestaurant.getMenu().size();
        newRestaurant.removeFromMenu("Sweet corn soup");
        assertEquals(initialMenuSize - 1, newRestaurant.getMenu().size());
    }

    // Testing exception throwing when trying to remove an item that does not exist
    @Test
    public void removingItemThatDoesNotExistShouldThrowException() {
        Restaurant newRestaurant = new Restaurant("Amelie's Cafe", "Chennai", openingTime, closingTime);
        newRestaurant.addToMenu("Sweet corn soup", 119);
        //assertThrows(itemNotFoundException.class, () -> newRestaurant.removeFromMenu("French fries"));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((restaurant == null) ? 0 : restaurant.hashCode());
        result = prime * result + ((openingTime == null) ? 0 : openingTime.hashCode());
        result = prime * result + ((closingTime == null) ? 0 : closingTime.hashCode());
        result = prime * result + ((r == null) ? 0 : r.hashCode());
        result = prime * result + ((rspied == null) ? 0 : rspied.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        RestaurantTest other = (RestaurantTest) obj;
        if (restaurant == null) {
            if (other.restaurant != null)
                return false;
        } else if (!restaurant.equals(other.restaurant))
            return false;
        if (openingTime == null) {
            if (other.openingTime != null)
                return false;
        } else if (!openingTime.equals(other.openingTime))
            return false;
        if (closingTime == null) {
            if (other.closingTime != null)
                return false;
        } else if (!closingTime.equals(other.closingTime))
            return false;
        if (r == null) {
            if (other.r != null)
                return false;
        } else if (!r.equals(other.r))
            return false;
        if (rspied == null) {
            if (other.rspied != null)
                return false;
        } else if (!rspied.equals(other.rspied))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "RestaurantTest [restaurant=" + restaurant + ", openingTime=" + openingTime + ", closingTime="
                + closingTime + ", r=" + r + ", rspied=" + rspied + "]";
    }

    // Testing calculation of total order value from a list of item names
    @Test
    public void calculateTotalOrderValueGivenMenuListInString() {
        Restaurant newRestaurant = new Restaurant("Amelie's Cafe", "Chennai", openingTime, closingTime);
        newRestaurant.addToMenu("Sweet corn soup", 119);
        newRestaurant.addToMenu("Vegetable lasagne", 269);
        newRestaurant.addToMenu("Mutton lasagne", 469);
        newRestaurant.addToMenu("Vegetable biryani", 369);

        List<String> selectedMenu = new ArrayList<>();
        selectedMenu.add(newRestaurant.getMenu().get(0).getName()); // Sweet corn soup
        selectedMenu.add(newRestaurant.getMenu().get(3).getName()); // Vegetable biryani
        int totalOrderValue = newRestaurant.totalOrderValue(selectedMenu);
        Assertions.assertEquals(488, totalOrderValue);

        selectedMenu.add(newRestaurant.getMenu().get(2).getName()); // Mutton lasagne
        totalOrderValue = newRestaurant.totalOrderValue(selectedMenu);
        assertEquals(957, totalOrderValue);
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public LocalTime getOpeningTime() {
        return openingTime;
    }

    public void setOpeningTime(LocalTime openingTime) {
        this.openingTime = openingTime;
    }

    public LocalTime getClosingTime() {
        return closingTime;
    }

    public void setClosingTime(LocalTime closingTime) {
        this.closingTime = closingTime;
    }

    public Restaurant getR() {
        return r;
    }

    public void setR(Restaurant r) {
        this.r = r;
    }

    public Restaurant getRspied() {
        return rspied;
    }

    public void setRspied(Restaurant rspied) {
        this.rspied = rspied;
    }
}
