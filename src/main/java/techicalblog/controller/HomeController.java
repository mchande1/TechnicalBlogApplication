package techicalblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import techicalblog.model.Post;
import techicalblog.service.PostService;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class HomeController {

    public HomeController(){
        System.out.println("** HomeController");
    }

    @Autowired
    private PostService postservice;

    @RequestMapping("/")
    public String getAllPosts(Model model){

        List<Post> posts = postservice.getAllPosts();


        model.addAttribute("posts", posts);
        return "index";
    }
}
