package sia.tacocloud.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import sia.tacocloud.data.Taco;

import java.net.URLConnection;

public interface TacoRepository extends PagingAndSortingRepository<Taco, Long> {
}
