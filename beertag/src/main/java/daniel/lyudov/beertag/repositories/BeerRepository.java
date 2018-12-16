package daniel.lyudov.beertag.repositories;

import daniel.lyudov.beertag.models.Beer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BeerRepository extends JpaRepository<Beer, Integer> {
}
