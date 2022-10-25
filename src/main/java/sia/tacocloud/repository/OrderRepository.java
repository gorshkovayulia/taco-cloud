package sia.tacocloud.repository;

import org.springframework.data.repository.CrudRepository;
import sia.tacocloud.data.TacoOrder;

import java.util.Date;
import java.util.List;

public interface OrderRepository extends CrudRepository<TacoOrder, Long> {

    /**
     * Fetches all the orders delivered to a given ZIP code
     */
    List<TacoOrder> findByDeliveryZip(String deliveryZip);

    /**
     * Queries for all orders delivered to a given ZIP code within a given date range
     */
    List<TacoOrder> readOrdersByDeliveryZipAndPlacedAtBetween(String deliveryZip, Date startDate, Date endDate);
}
