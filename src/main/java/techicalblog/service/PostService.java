package techicalblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import techicalblog.model.Post;
import techicalblog.repository.PostRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository repository;

    public PostService(){
        System.out.println("** PostService");
    }

    public List<Post> getAllPosts(){

        return repository.getAllPosts();
    }

    public ArrayList<Post> getUserPosts(){

        ArrayList<Post> posts = new ArrayList<Post>();
        posts.add(repository.getLatestPost());
        return posts;
    }

    public void createNewPost(Post newPost){
        newPost.setDate(new Date());
        repository.createPost(newPost);
        System.out.println("**newPost: " + newPost);
    }

    public Post getPost(Integer postId){
        return repository.getPost(postId);
    }

    public void updatePost(Post updatedPost){
        updatedPost.setDate(new Date());
        repository.updatePost(updatedPost);
    }

    public void deletePost(Integer postId){
        repository.deletePost(postId);
    }
}
