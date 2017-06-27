package cs544.project.blog.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import cs544.project.blog.domain.Person;
import cs544.project.blog.domain.Post;
import cs544.project.blog.domain.User;
import cs544.project.blog.domain.UserRole;
//import cs544.project.blog.service.PersonService;


@Controller
public class PersonController {
/*@Autowired
private PersonService personService;*/
	public static final String SERVER_URI = "http://localhost:8080/api/";
@GetMapping({"/person"})
public String homePage(Model model) {
//	model.addAttribute("posts",personService.getAll());
	RestTemplate restTemplate = new RestTemplate();
	
	
	List<LinkedHashMap> persons= restTemplate.getForObject(SERVER_URI+"person", List.class);
	model.addAttribute("persons",persons);
	return "users";
}

@GetMapping({"/register"})
public String registeration(Model model) {
//	model.addAttribute("posts",personService.getAll());
	return "addPerson";
}

@GetMapping({ "/person/{id}"})
public String getPost(@PathVariable int id, Model model) {
	

	//model.addAttribute("person", personService.getById(id));
	
	return "";
}
//new person
@PostMapping({"/addPerson/"})
public String addPerson(Person person,User user){
	RestTemplate restTemplate = new RestTemplate();
	//User user=new User("w@gmai.com", "w", true);
	user.setEnabled(true);
	person.setUser(user);
	person.getUser().addUserRoles(new UserRole("ROLE_USER"));
	//Person person=new Person("www","w@gmai.com",null,null,user);
	//personService.save(person);
	Person response = restTemplate.postForObject(SERVER_URI+"person", person, Person.class);
	return "redirect:/login";
	
}

//save person
@PostMapping({"/addPerson/{id}"})
public String savePerson(@PathVariable int id, Person person,User user){
	RestTemplate restTemplate = new RestTemplate();
	//User user=new User("w@gmai.com", "w", true);
	Person oldPerson= restTemplate.getForObject(SERVER_URI+"person/"+id, Person.class);
	user.setEnabled(true);
	oldPerson.setEmail(person.getEmail());
	oldPerson.setName(person.getName());
	oldPerson.setUser(user);
	//user.setEnabled(true);
	//person.setUser(user);
	//person.getUser().addUserRoles(new UserRole("ROLE_USER"));
	//Person person=new Person("www","w@gmai.com",null,null,user);
	//personService.save(person);
	 restTemplate.put(SERVER_URI+"person/update/"+id, oldPerson);
	return "redirect:/";
	
}

@PostMapping({ "/assignRole/{id}"})
public String assignRole(@PathVariable int id, @RequestParam String userRoles ){

	String[] roleArray=userRoles.split(",");
	
		
	
	RestTemplate restTemplate = new RestTemplate();
	Person person= restTemplate.getForObject(SERVER_URI+"person/"+id, Person.class);
//	System.out.println("title"+person.getPosts().get(0).getTitle());
	List<UserRole> roles=new ArrayList<>();
	
if(roleArray.length==2){
	UserRole role=new UserRole(roleArray[1]);
	roles.add(role);
	}
		
	roles.add(new UserRole("ROLE_USER"));
	
		person.getUser().setUserRoles(roles);
	restTemplate.put(SERVER_URI+"person/update/"+id, person);
	
	
	return "redirect:/person";
}

@GetMapping("/editPerson")
public String editPerson(Principal p,Model model){
	
	RestTemplate restTemplate = new RestTemplate();
	
	Person person= restTemplate.getForObject(SERVER_URI+"person/user/"+p.getName(), Person.class);
	model.addAttribute("person",person);
	return "addPerson";
}

@GetMapping("/delPerson/{id}")
public String delPost(@PathVariable long id){
	System.out.println("iddd"+id);
	RestTemplate restTemplate = new RestTemplate();
	restTemplate.delete(SERVER_URI+"person/delete/"+id);
	return "redirect:/person";
}

}


