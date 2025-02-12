package hotelmanagementsystem.domain.models;

import java.util.ArrayList;
import java.util.List;

//Wrapper klasse
public class PagedHotels {
    private final List<Hotel> hotels;
    private final int totalCount;

    public PagedHotels(List<Hotel> hotels, int totalCount){
        this.hotels = hotels;
        this.totalCount = totalCount;
    }

    public List<Hotel> getHotels() {
        return hotels;
    }

    public int getTotalCount() {
        return totalCount;
    }
}
