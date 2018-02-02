package bookshow.repository;

import bookshow.model.Bid;
import bookshow.model.props.UsedProp;
import bookshow.model.users.RegisteredUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Ivan V. on 29-Jan-18
 */
public interface BidRepository extends JpaRepository<Bid,Long> {
    List<Bid> findAll();

    List<Bid> findByUsedProp(UsedProp usedProp);
    Bid findByRegisteredUserAndUsedProp(RegisteredUser registeredUser, UsedProp usedProp);

    Bid findOne(Long id);

    Bid save(Bid bid);

    Bid findByPrice(Integer price);

    Bid findByRegisteredUser(RegisteredUser registeredUser);


    void delete(Long id);

}
