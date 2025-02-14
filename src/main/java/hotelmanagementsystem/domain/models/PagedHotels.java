package hotelmanagementsystem.domain.models;

import java.util.List;

public record PagedHotels(List<Hotel> hotels, int totalCount) {
}
