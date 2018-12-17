package techicalblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import techicalblog.model.Category;
import techicalblog.model.Post;
import techicalblog.model.User;
import techicalblog.service.PostService;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {

    @Autowired
    private PostService postservice;

    @RequestMapping("posts")
    public String getUserPosts(Model model){

        List<Post> posts = postservice.getAllPosts();
        model.addAttribute("posts", posts);
        return "posts";
    }

    @RequestMapping("posts/newpost")
    public String createPost(){
        return "posts/create";
    }

    @RequestMapping(value = "posts/create", method = RequestMethod.POST)
    public String createNewPost(Post newPost, HttpSession session){
        User user = (User) session.getAttribute("loggedUser");

        if(newPost.getSpringBlog() != null){
            Category springBlogCategory = new Category();
            springBlogCategory.setCategory(newPost.getSpringBlog());
            newPost.getCategories().add(springBlogCategory);
        }

        if(newPost.getJavaBlog() != null){
            Category javaBlogCategory = new Category();
            javaBlogCategory.setCategory(newPost.getJavaBlog());
            newPost.getCategories().add(javaBlogCategory);
        }

        newPost.setUser(user);
        postservice.createNewPost(newPost);
        return "redirect:/posts";
    }

    @RequestMapping(value = "/editPost", method = RequestMethod.GET)
    public String editPost(@RequestParam(name = "postId") Integer postId, Model model){
        Post post = postservice.getPost(postId);
        System.out.println("**post Id value: " + post.getId());
        model.addAttribute("post", post);
        return "posts/edit";
    }

    @RequestMapping(value = "/editPost", method = RequestMethod.PUT)
    public String editPostSubmit(@RequestParam(name = "postId") Integer postId, Post updatedPost, HttpSession session){
        System.out.println("**post id value in editPostSubmit method: " + updatedPost.getId());
        updatedPost.setId(postId);
        User user = (User)session.getAttribute("loggedUser");
        updatedPost.setUser(user);
        postservice.updatePost(updatedPost);
        return "redirect:/posts";
    }

    @RequestMapping(value = "/deletePost", method = RequestMethod.DELETE)
    public String deletePost(@RequestParam(name = "postId") Integer postId){
        postservice.deletePost(postId);
        return "redirect:/posts";
    }
}
