package hotelmanagementsystem.domain.models;

public enum HotelRatingEnum {

    ONE_STAR(1),
    TWO_STAR(2),
    THREE_STAR(3),
    FOUR_STAR(4),
    FIVE_STAR(5);

    int value;
    HotelRatingEnum(int value){
        this.value = value;
    }
}




