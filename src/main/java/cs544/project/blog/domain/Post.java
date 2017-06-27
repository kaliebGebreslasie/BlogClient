package cs544.project.blog.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;


public class Post {
	
	private long id;
	private String title;

	private String content;

	private Date datecreated;
	
	private Date dateupdated;
	private String summary;
	

	private List<Comment> comments=new ArrayList<>();
	private List<Like> likes=new ArrayList<>();
	private Person person;
	private int countlikes;
	public boolean liked;
	public Post() {
	}

	public Post(long id, String title, String content, Date datecreated, Date dateupdated,String summary,List<Comment> comments,
			Person person) {
		super();
		
		this.title = title;
		this.content = content;
		this.datecreated = datecreated;
		this.dateupdated = dateupdated;
		this.summary=summary;
		this.comments = comments;
		this.person = person;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getDatecreated() {
		return datecreated;
	}

	public void setDatecreated(Date datecreated) {
		this.datecreated = datecreated;
	}

	public Date getDateupdated() {
		return dateupdated;
	}

	public void setDateupdated(Date dateupdated) {
		this.dateupdated = dateupdated;
	}

	
	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
	
	public void addComment(Comment comment){
		this.comments.add(comment);
	}

	public List<Like> getLikes() {
		return likes;
	}


	public void setLikes(List<Like> likes) {
		this.likes = likes;
		this.countlikes = this.likes.size();
	}

	public void addLike(Like like) { this.likes.add(like); this.countlikes = this.likes.size();}

	public int getCountlikes() {
		return countlikes;
	}

	public void setCountlikes(int countlikes) {
		this.countlikes = countlikes;
	}

	public boolean isLiked() {
		return liked;
	}

	public void setLiked(boolean liked) {
		this.liked = liked;
	}
}
