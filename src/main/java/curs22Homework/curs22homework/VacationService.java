package curs22Homework.curs22homework;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VacationService {
    private final List<Vacation> vacations = new ArrayList<>();

    public VacationService(VacationReader reader) {
        reader.read()
                .forEach(this::add);
    }
    public List<Vacation> getAll() {
        return Collections.unmodifiableList(vacations);
    }

    public Vacation add(Vacation vacation) {
        Vacation newVacation = new Vacation((vacations.size() + 1), vacation.getLocation(),
                vacation.getPrice(), vacation.getDuration());
        vacations.add(newVacation);
        return newVacation;
    }

    public Vacation replace(int id, Vacation vacation) {
        Vacation vacationToReplace = getOrThrow(id);

        vacations.remove(vacationToReplace);
        var newVacation = new Vacation(id, vacation.getLocation(), vacation.getPrice(), vacation.getDuration());
        vacations.add(id - 1, newVacation);
        return newVacation;
    }

    public Vacation getOrThrow(int id) {
        return vacations.stream()
                .filter(vacation -> vacation.getId() == id)
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("could not find vacation with id " + id));
    }

    public Vacation delete(final int id) {
        Vacation vacationToDelete = getOrThrow(id);
        vacations.remove(vacationToDelete);
        return vacationToDelete;
    }

    public List<Vacation> getVacationsForLocation(String location) {
        return vacations.stream()
                .filter(vacation -> vacation.getLocation().equalsIgnoreCase(location))
                .collect(Collectors.toList());
    }

    public List<Vacation> getVacationsWithLowerPrice(int maxPrice) {
        List<Vacation> locationsWithLowestPrice = vacations
                .stream()
                .filter(vacation -> vacation.getPrice() < maxPrice)
                .collect(Collectors.toList());
        return locationsWithLowestPrice;
    }


}
