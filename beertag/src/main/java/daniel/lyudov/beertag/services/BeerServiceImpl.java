package daniel.lyudov.beertag.services;

import daniel.lyudov.beertag.models.Beer;
import daniel.lyudov.beertag.repositories.BeerRepository;
import daniel.lyudov.beertag.services.base.BeerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BeerServiceImpl implements BeerService {
    private final BeerRepository beerRepository;

    @Autowired
    public BeerServiceImpl(BeerRepository beerRepository) {
        this.beerRepository = beerRepository;
    }


    @Override
    public Beer add(Beer beer) {
      return beerRepository.save(beer);
    }

    @Override
    public List<Beer> getAll() {
        return beerRepository.findAll();
    }

    @Override
    public void delete(Beer beer) {
        beerRepository.delete(beer);
    }
}
