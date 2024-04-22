package bt.edu.gcit.usermicroservice.rest;

import bt.edu.gcit.usermicroservice.entity.Country;
import bt.edu.gcit.usermicroservice.entity.State;
import bt.edu.gcit.usermicroservice.service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/states")
public class StateRestController {

    private StateService stateService;

    @Autowired
    public StateRestController(StateService theStateService) {
        stateService = theStateService;
    }

    @PostMapping("/{countryId}")
    public ResponseEntity<State> createState(@PathVariable Long countryId, @RequestBody State state) {
        stateService.save(state, countryId);
        return new ResponseEntity<>(state, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<State> getState(@PathVariable Long id) {
        State state = stateService.findById(id);
        return new ResponseEntity<>(state, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<State>> getAllStates() {
        List<State> states = stateService.findAll();
        return new ResponseEntity<>(states, HttpStatus.OK);
    }

    @GetMapping("/country/{countryId}")
    public ResponseEntity<List<State>> getStatesByCountry(@PathVariable Long countryId) {
        List<State> states = stateService.listStatesByCountry(countryId);
        return new ResponseEntity<>(states, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<State> updateState(@RequestBody State state) {
        stateService.save(state, null); // Assuming the service uses save for both create and update
        return new ResponseEntity<>(state, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteState(@PathVariable int id) {
        stateService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
