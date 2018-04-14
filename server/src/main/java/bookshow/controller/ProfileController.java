package bookshow.controller;


import java.util.ArrayList;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import bookshow.domain.Show;
import bookshow.domain.users.User;
import bookshow.model.ArrayListDatabaseHandler;
import bookshow.model.ChangingPasswordDTO;
import bookshow.service.ShowService;
import bookshow.service.UserService;

@RestController
public class ProfileController {
	private ArrayListDatabaseHandler handler;
	
	@Autowired
	 private UserService UserService;
	
	@Autowired
	private ShowService ShowService;
	
	@RequestMapping(value = "/getProfileInfo/{username}", method = RequestMethod.GET)
	public ResponseEntity<User> getLoggedUserData(@PathVariable("username") String username) {
		return new ResponseEntity<>(UserService.findByUsername(username),HttpStatus.OK);
	}
	
	@RequestMapping(value = "/changeCity/{username}/{newCity}", method = RequestMethod.GET)
	public ResponseEntity<User> changeCity(@PathVariable("username") String username,
											@PathVariable("newCity") String newCity) {
		User u = UserService.findByUsername(username);
		u.setCity(newCity);
		UserService.save(u);
		return new ResponseEntity<>(UserService.findByUsername(username),HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/changeEmail/{username}/{newEmail}", method = RequestMethod.GET)
	public ResponseEntity<User> changeEmail(@PathVariable("username") String username,
											@PathVariable("newEmail") String newEmail) {
		User u = UserService.findByUsername(username);
		u.setEmail(newEmail);
		UserService.save(u);
		return new ResponseEntity<>(UserService.findByUsername(username),HttpStatus.OK);
	}
	
	@RequestMapping(value = "/changeName/{username}/{newName}", method = RequestMethod.GET)
	public ResponseEntity<User> changeName(@PathVariable("username") String username,
											@PathVariable("newName") String newName) {
		User u = UserService.findByUsername(username);
		u.setName(newName);
		UserService.save(u);
		return new ResponseEntity<>(UserService.findByUsername(username),HttpStatus.OK);
	}
	
	@RequestMapping(value = "/changeNumber/{username}/{newNumber}", method = RequestMethod.GET)
	public ResponseEntity<User> changeNumber(@PathVariable("username") String username,
											@PathVariable("newNumber") String newNumber) {
		User u = UserService.findByUsername(username);
		u.setNumber(newNumber);
		UserService.save(u);
		return new ResponseEntity<>(UserService.findByUsername(username),HttpStatus.OK);
	}
	
	@RequestMapping(value = "/changeSurname/{username}/{newSurname}", method = RequestMethod.GET)
	public ResponseEntity<User> changeSurname(@PathVariable("username") String username,
											@PathVariable("newSurname") String newSurname) {
		User u = UserService.findByUsername(username);
		u.setSurname(newSurname);
		UserService.save(u);
		return new ResponseEntity<>(UserService.findByUsername(username),HttpStatus.OK);
	}
	
	@RequestMapping(value = "/changeUsername/{username}/{newUsername}", 
			method = RequestMethod.GET, consumes = "application/json")
	public ResponseEntity<User> changeUsername(@PathVariable("username") String username,
											@PathVariable("newUsername") String newUsername) {
		User u = UserService.findByUsername(username);
		u.setUsername(newUsername);
		UserService.save(u);
		return new ResponseEntity<>(UserService.findByUsername(username),HttpStatus.OK);
	}
	
	@RequestMapping(value = "/changePassword", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<User> changePassword(@RequestBody ChangingPasswordDTO cpDTO) {
		
		/*
		 * System.out.println(cpDTO.getUsername());
		 * System.out.println(cpDTO.getNewPassword());
		 */

		User u = UserService.findByUsername(cpDTO.getUsername());
		if (new BCryptPasswordEncoder().matches(cpDTO.getOldPassword(), u.getPasswordHash())) {
			//System.out.println("JEDNAKE SU SIFREEEEEEEE");
			u.setPasswordHash(new BCryptPasswordEncoder().encode(cpDTO.getNewPassword()));
			u.setLastPasswordReset(Calendar.getInstance().getTime());
			UserService.save(u);
			return new ResponseEntity<>(UserService.findByUsername(cpDTO.getUsername()), HttpStatus.OK);
		} else {
			//System.out.println("NISU JEDNAKEEEE");
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);

		}
	}
	
	@RequestMapping(value = "/addToHistory/{username}/{showId}", method = RequestMethod.GET)
	public ResponseEntity<User> addToHistory(@PathVariable("username") String username,
										@PathVariable("showId") String showId){
		User u = UserService.findByUsername(username);
		ArrayList<String> lista = new ArrayList<String>();
		handler = new ArrayListDatabaseHandler();
		lista = handler.StringToArrayList(u.getIstorijaPoseta());
	
		if(lista.contains(showId)) {
			lista.remove(showId);
			lista.add(0,showId);
		}else {
			lista.add(0,showId);
		}
		u.setIstorijaPoseta(handler.ArrayListToString(lista));
			
		/*
		if(u.getIstorijaPoseta().contains(showName)) {
			u.getIstorijaPoseta().remove(showName);
			u.getIstorijaPoseta().add(0,showName);
			
		}else {
			u.getIstorijaPoseta().add(0,showName);
		}		
		for(String s : u.getIstorijaPoseta()){
			System.out.println(s);
		}*/
		
		UserService.save(u);
		return new ResponseEntity<>(UserService.findByUsername(username),HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/removeFromHistory/{username}/{showId}", method = RequestMethod.GET)
	public ResponseEntity<ArrayList<Show>> removeFromHistory(@PathVariable("username") String username,
			@PathVariable("showId") String showId){
		User u = UserService.findByUsername(username);
		handler = new ArrayListDatabaseHandler();
		ArrayList<String> lista = new ArrayList<String>();
		lista = handler.StringToArrayList(u.getIstorijaPoseta());
		lista.remove(showId);
		u.setIstorijaPoseta(handler.ArrayListToString(lista));
		
		ArrayList<Show> retVal = new ArrayList<Show>();
		for(String s : lista) {
			retVal.add(ShowService.findOne(Long.parseLong(s)));
		}
		
		UserService.save(u);
		return new ResponseEntity<>(retVal,HttpStatus.OK);
		
	}
	@RequestMapping(value = "/getHistory/{username}", method = RequestMethod.GET)
	public ResponseEntity<ArrayList<Show>> getHistory(@PathVariable("username") String username){
		User u = UserService.findByUsername(username);
		ArrayList<String> lista = new ArrayList<String>();
		handler = new ArrayListDatabaseHandler();
		lista = handler.StringToArrayList(u.getIstorijaPoseta());
		
		ArrayList<Show> retVal = new ArrayList<Show>();
		
		for(String st : lista){
			retVal.add(ShowService.findOne(Long.parseLong(st)));
		}
		return new ResponseEntity<>(retVal,HttpStatus.OK);	
	}
	
	@RequestMapping(value = "/getFriends/{username}", method = RequestMethod.GET)
	public ResponseEntity<ArrayList<User>> getFriends(@PathVariable("username") String username){
		User u = UserService.findByUsername(username);
		ArrayList<String> lista = new ArrayList<String>();
		handler = new ArrayListDatabaseHandler();
		lista = handler.StringToArrayList(u.getFriendList());
		
		ArrayList<User> retVal = new ArrayList<User>();
		
		for(String s : lista) {
			retVal.add(UserService.findByUsername(s));
		}
		return new ResponseEntity<>(retVal,HttpStatus.OK);
	}
	
	


}
