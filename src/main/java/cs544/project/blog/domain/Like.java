package cs544.project.blog.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

public class Like {
    private long id;
    private Person person;

    private Post post;

    public Like() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

}
