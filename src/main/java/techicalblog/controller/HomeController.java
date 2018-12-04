package techicalblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import techicalblog.model.Post;

import java.util.ArrayList;
import java.util.Date;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String getAllPosts(Model model){

        Post post1 = new Post();
        post1.setTitle("post 1");
        post1.setBody("post 1 body");
        post1.setDate(new Date());

        Post post2 = new Post();
        post2.setTitle("post 2");
        post2.setBody("post 2 body");
        post2.setDate(new Date());

        Post post3 = new Post();
        post3.setTitle("post 3");
        post3.setBody("post 3 body");
        post3.setDate(new Date());

        Post post4 = new Post();
        post4.setTitle("post 4");
        post4.setBody("post 4 body");
        post4.setDate(new Date());

        ArrayList<Post> posts = new ArrayList<Post>();
        posts.add(post1);
        posts.add(post2);
        posts.add(post3);
        posts.add(post4);

        for(Post p: posts){
            System.out.println("**post data " + post1.getTitle());
        }

        model.addAttribute("posts", posts);
        return "index";
    }
}
