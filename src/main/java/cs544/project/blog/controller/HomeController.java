package cs544.project.blog.controller;

import java.security.Principal;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.client.RestTemplate;

import cs544.project.blog.domain.Person;
import cs544.project.blog.domain.Post;
import java.security.Principal;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import cs544.project.blog.domain.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import cs544.project.blog.domain.Person;
import cs544.project.blog.domain.Like;
import cs544.project.blog.domain.Post;
@Controller
@SessionAttributes({"currUser","currPerson"})
public class HomeController {
	public static final String SERVER_URI = "http://localhost:8080/api/";

	@GetMapping({ "/index", "/home", "/" })
	public String homePage(Model model, Principal p) {
		RestTemplate restTemplate = new RestTemplate();
		if (p != null){
			model.addAttribute("currUser", p.getName());
			
			Person currPerson= restTemplate.getForObject(SERVER_URI+"person/user/"+p.getName(), Person.class);
			model.addAttribute("currPerson",currPerson);
		
		}
		List<LinkedHashMap> posts = restTemplate.getForObject(SERVER_URI + "post", List.class);
		// System.out.println((Post)(posts.get(0).getContent()));
		model.addAttribute("posts", posts);
		return "index";
	}

	@GetMapping("/post/{id}")
	public String readPost(@PathVariable long id, Model model,Principal p) {
		Person person;
		RestTemplate restTemplate = new RestTemplate();
		model.addAttribute("currUser", p.getName());	
		Person postPerson= restTemplate.getForObject(SERVER_URI+"person/post/"+id, Person.class);
		model.addAttribute("postPerson",postPerson);
		
		Person currPerson= restTemplate.getForObject(SERVER_URI+"person/user/"+p.getName(), Person.class);
		model.addAttribute("currPerson",currPerson);
	
        
		Post post= restTemplate.getForObject(SERVER_URI+"post/"+id, Post.class);

		for(Like like: post.getLikes()){
            person = restTemplate.getForObject(SERVER_URI + "person/like/" + like.getId(), Person.class);
            if(person != null) {
                like.setPerson(person);
            }
        }

		for (Comment comment: post.getComments()) {
            person = restTemplate.getForObject(SERVER_URI + "person/comment/" + comment.getId(), Person.class);
            if(person != null) comment.setPerson(person);
        }

        for(Like like: post.getLikes()){
            if(like.getPerson().getName().equals(currPerson.getName())) post.setLiked(true);
        }
        model.addAttribute("post", post);
		return "post";
	}
	
	@PostMapping("/addLike/{id}")
    public String addLike(@PathVariable long id, Principal p){
        //System.out.println("pppppp "+ id);
        RestTemplate restTemplate = new RestTemplate();
        Person person = restTemplate.getForObject(SERVER_URI+"person/name/" + p.getName(), Person.class);
        Post post= restTemplate.getForObject(SERVER_URI+"post/" + id, Post.class);
        Like like = new Like();
        like.setPerson(person);
        like.setPost(post);
        Like response = restTemplate.postForObject(SERVER_URI+"like/" + p.getName() + "/" + id, like, Like.class );
        return "redirect:/post/{id}";
    }

    @GetMapping("/unLike/{id}")
    public String unLike(@PathVariable long id, Principal p){
        RestTemplate restTemplate = new RestTemplate();
        Person person = restTemplate.getForObject(SERVER_URI+"person/name/" + p.getName(), Person.class);
        restTemplate.delete(SERVER_URI+"/like/"+ p.getName() + "/" + id);
        return "redirect:/post/{id}";
    }
    
	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/logout")
	public String logout() {
		return "index";
	}

	@GetMapping("/addPost")
	public String newPost() {
		System.out.println("heloooo");
		return "addPost";
	}

	// add new post
	@PostMapping({ "/savePost/" })
	public String addPost(Post post, Principal p) {
		System.out.println("pppppp " + p.getName());
		RestTemplate restTemplate = new RestTemplate();
		post.setDatecreated(new Date());

		// postService.save(post);
		Post response = restTemplate.postForObject(SERVER_URI + "post/" + p.getName(), post, Post.class);
		return "redirect:/";

	}

	@GetMapping("/editPost/{id}")
	public String editPost(@PathVariable long id, Model model) {
		RestTemplate restTemplate = new RestTemplate();

		Post post = restTemplate.getForObject(SERVER_URI + "post/" + id, Post.class);
		model.addAttribute("post", post);
		return "addPost";
	}

	// save post
	@PostMapping("/savePost/{id}")
	public String savePost(@PathVariable long id, Post post) {
		RestTemplate restTemplate = new RestTemplate();

		Post oldPost = restTemplate.getForObject(SERVER_URI + "post/" + id, Post.class);
		
		oldPost.setTitle(post.getTitle());
		oldPost.setContent(post.getContent());
		oldPost.setSummary(post.getSummary());
		oldPost.setDateupdated(new Date());
		
		restTemplate.put(SERVER_URI + "post/update/" + id, oldPost);

		return "redirect:/post/{id}";
	}

	@GetMapping("/delPost/{id}")
	public String delPost(@PathVariable long id) {
		System.out.println("iddd" + id);
		RestTemplate restTemplate = new RestTemplate();
		
		restTemplate.delete(SERVER_URI + "post/delete/" + id);
		return "redirect:/";
	}

	/*
	 * @GetMapping({"/secure"}) public String securePage() { return "secure"; }
	 * 
	 * @RequestMapping(value = "/login", method = RequestMethod.GET) public
	 * String login(Model model, Error error, String logout) {
	 * 
	 * // if (error != null) // model.addAttribute("error",
	 * "Your username and password is invalid.");
	 * 
	 * if (logout != null) model.addAttribute("message",
	 * "You have been logged out successfully.");
	 * System.out.println("hellooooooooooooooooooo"); return
	 * "redirect:/products"; }
	 * 
	 * @GetMapping({"/registration"}) public String registerPage() { return
	 * "home"; }
	 */}
