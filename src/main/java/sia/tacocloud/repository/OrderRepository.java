package sia.tacocloud.repository;

import sia.tacocloud.data.TacoOrder;

public interface OrderRepository {

    TacoOrder save(TacoOrder order);
}
