package daniel.lyudov.beertag.controllers;


import daniel.lyudov.beertag.models.Beer;
import daniel.lyudov.beertag.services.BeerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/beers")
public class BeerController {
    private final BeerServiceImpl beerService;

    @Autowired
    public BeerController(BeerServiceImpl beerService) {
        this.beerService = beerService;
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/secured/all")
    public List<Beer> getAllBeer(){
        return beerService.getAll();
    }

    @ResponseBody
    @PostMapping("/addBeer")
    public Beer add(@RequestBody Beer beer){
      return beerService.add(beer);
    }

   @DeleteMapping("/delete/{beer}")
    public void deleteBeer(@PathVariable Beer beer){
        beerService.delete(beer);
    }

}
