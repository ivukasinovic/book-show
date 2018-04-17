package bookshow.service;

import java.util.List;

import bookshow.domain.movie.Ticket;

public interface TicketService {
	List<Ticket> findAll();

	Ticket findOne(Long id);

	Ticket save(Ticket ticket);

    void delete(Long id);
    
    List<Ticket> findBySeatAuditoriumShowIdAndDiscountGreaterThanAndUserIsNull(Long id, double value);
    
    List<Ticket> findByProjectionId(Long id);
}
