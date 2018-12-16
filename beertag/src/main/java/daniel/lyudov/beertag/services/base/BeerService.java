package daniel.lyudov.beertag.services.base;

import daniel.lyudov.beertag.models.Beer;

import java.util.List;

public interface BeerService {

    Beer add(Beer beer);

    List<Beer> getAll();

    void delete(Beer beer);
}
