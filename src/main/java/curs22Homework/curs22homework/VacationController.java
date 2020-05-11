package curs22Homework.curs22homework;


import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("vacations")
public class VacationController {
    private final VacationService vacationService;

    public VacationController(final VacationService vacationService) {
        this.vacationService = vacationService;
    }
    @GetMapping("{id}")
    public Vacation getById(@PathVariable int id) {
        return vacationService.getOrThrow(id);
    }
    @GetMapping
    public List<Vacation> getAll() {
        return vacationService.getAll();
    }
    @GetMapping("location/{location}")
    public List<Vacation> getVacationsForLocation(@PathVariable String location) {
        return vacationService.getVacationsForLocation(location);
    }
    @RequestMapping(value = "filter", params = "maxPrice", method = RequestMethod.GET)
    List<Vacation> getVacationsWithLowerPrice(@RequestParam("maxPrice") int maxPrice) {
        return vacationService.getVacationsWithLowerPrice(maxPrice);
    }

    @PostMapping
    public Vacation addVacation(@RequestBody Vacation vacation) {
        return vacationService.add(vacation);
    }

    @DeleteMapping("{id}")
    public Vacation deleteVacation(@PathVariable int id) {
        return vacationService.delete(id);
    }
    @PutMapping("{id}")
    public Vacation replaceVacation(@PathVariable int id, @RequestBody Vacation vacation) {
        return vacationService.replace(id, vacation);
    }

}
