package training.springinaction_chapter4.tacos.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import training.springinaction_chapter4.tacos.Order;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {

}
