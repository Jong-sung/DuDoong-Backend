package band.gosrock.domain.domains.event.repository;


import band.gosrock.domain.domains.event.domain.Event;
import org.springframework.data.repository.CrudRepository;

public interface EventRepository extends CrudRepository<Event, Long> {}