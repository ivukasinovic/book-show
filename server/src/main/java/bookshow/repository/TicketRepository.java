package bookshow.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import bookshow.domain.movie.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long>{

	List<Ticket> findByProjectionId(Long id);

	List<Ticket> findBySeatAuditoriumShowIdAndDiscountGreaterThanAndPurchasedIsNull(Long id, double value);

	Ticket findByPurchasedId(Long id);
}
