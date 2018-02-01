package bookshow.service;

import bookshow.model.Show;
import bookshow.repository.ShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Ivan V. on 01-Feb-18
 */
@Service
public class ShowServiceImpl implements ShowService {
    @Autowired
    private ShowRepository showRepository;

    @Override
    public List<Show> findAll() {
        return showRepository.findAll();
    }

    @Override
    public Show findOne(Long id) {
        return showRepository.findOne(id);
    }

    @Override
    public Show save(Show show) {
        return showRepository.save(show);
    }

    @Override
    public void delete(Long id) {
        this.showRepository.delete(id);
    }
}
