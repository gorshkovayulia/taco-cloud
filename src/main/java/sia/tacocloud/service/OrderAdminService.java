package sia.tacocloud.service;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import sia.tacocloud.repository.OrderRepository;

@Service
public class OrderAdminService {

    private final OrderRepository orderRepo;

    public OrderAdminService(OrderRepository orderRepo) {
        this.orderRepo = orderRepo;
    }

    @PreAuthorize("hasRole('ADMIN')")
    public void deleteAllOrders() {
        orderRepo.deleteAll();
    }
}
