package hotelmanagementsystem.infrastructure.persistence.repositories.adapters;

import hotelmanagementsystem.domain.models.Hotel;
import hotelmanagementsystem.domain.models.PagedHotels;
import hotelmanagementsystem.infrastructure.persistence.dao.interfaces.HotelDAO;
import hotelmanagementsystem.infrastructure.persistence.entities.HotelEntity;
import hotelmanagementsystem.infrastructure.persistence.mapper.HotelMapper;
import hotelmanagementsystem.infrastructure.persistence.repositories.interfaces.HotelRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
public class HotelDatabaseAdapter implements HotelRepository {

    private final HotelDAO hotelDAO;
    private final HotelMapper hotelMapper;
    @Inject
    public HotelDatabaseAdapter(HotelDAO hotelDAO, HotelMapper hotelMapper) {

        this.hotelDAO = hotelDAO;
        this.hotelMapper = hotelMapper;

    }

    @Override
    public Hotel createHotel(Hotel hotel) {
        try {
            return hotelMapper.mapHotelEntityToDomainHotel(hotelDAO.createHotel(hotelMapper.mapDomainHotelToHotelEntity(hotel)));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error saving Hotel - DB ADAPTER", e);
        }
    }


    @Override
    public Optional<Hotel> findById(Long id) {
        return hotelDAO.findById(id)
                .map(hotelMapper::mapHotelEntityToDomainHotel);
    }

    @Override
    public PagedHotels findPagedByFilter(String city, double minRating, int pageNumber, int pageSize){
        try {
            int offset = (pageNumber - 1) * pageSize;
            long totalCount = hotelDAO.countByFilter(city, minRating);
            List<HotelEntity> entities = hotelDAO.findByFilter(city, minRating, offset, pageSize);
            List<Hotel> domainHotels = entities.stream()
                    .map(hotelMapper::mapHotelEntityToDomainHotel)
                    .collect(Collectors.toList());

            return new PagedHotels(domainHotels, (int) totalCount);
        }
        catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("Error while finding paged hotels: " + e.getMessage(), e);
        }
    }

    @Override
    public Hotel update(Hotel hotel) {
        try {
            // Aus dem Domain-Hotel ein HotelEntity bauen
            HotelEntity hotelEntity = hotelMapper.mapDomainHotelToHotelEntity(hotel);

            // Dann DAO-Ebene aufrufen
            HotelEntity updated = hotelDAO.updateHotel(hotelEntity);

            // Rückübersetzen in Domain
            return hotelMapper.mapHotelEntityToDomainHotel(updated);
            //return hotelMapper.mapHotelEntityToDomainHotel(hotelDAO.updateHotel(hotelMapper.mapDomainHotelToHotelEntity(hotel)));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error updating Hotel", e);
        }
    }


    @Override
    public void deleteById(Long id) {
        try {
            hotelDAO.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error deleting Hotel", e);
        }
    }

}
