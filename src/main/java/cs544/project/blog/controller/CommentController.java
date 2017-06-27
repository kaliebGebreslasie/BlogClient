package cs544.project.blog.controller;

import java.security.Principal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cs544.project.blog.domain.Comment;
import cs544.project.blog.domain.Person;
import cs544.project.blog.domain.Post;
import org.springframework.web.client.RestTemplate;
/*import cs544.project.blog.service.CommentService;
import cs544.project.blog.service.PersonService;
import cs544.project.blog.service.PostService;*/

@Controller
public class CommentController {
/*@Autowired
private CommentService commentService;
private PostService postService;*/
public static final String SERVER_URI = "http://localhost:8080/api/";

@GetMapping({ "/comment/{postid}"})
public String getPost(@PathVariable int postid, Model model) {
	

	//model.addAttribute("person", personService.getById(id));
	
	return "";
}

	@PostMapping("/addComment/{id}")
	public String addComment(@PathVariable long id, Comment comment, Principal p){
		//System.out.println("pppppp "+ id);
		RestTemplate restTemplate = new RestTemplate();

		Post post= restTemplate.getForObject(SERVER_URI+"post/" + id, Post.class);
		Comment response = restTemplate.postForObject(SERVER_URI+"comment/"+p.getName()+"/"+id, comment, Comment.class);
		return "redirect:/post/{id}";
	}

}
