package bookshow.controller.film;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import bookshow.domain.movie.Projection;
import bookshow.service.ProjectionService;

@RestController
@RequestMapping(value="/projection")
public class ProjectionController {
	
	@Autowired
	ProjectionService projectionService;

	@RequestMapping(
			value = "/save", 
			method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Projection> saveProjection(@RequestBody Projection projection){
		System.out.println(projection.getTime());
		return new ResponseEntity<>(projectionService.save(projection), HttpStatus.OK);
	}
}
