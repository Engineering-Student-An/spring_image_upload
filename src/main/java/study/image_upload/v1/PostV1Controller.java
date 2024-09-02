package study.image_upload.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/v1/posts")
public class PostV1Controller {

    private final PostV1Service postV1Service;

    @GetMapping("/{postId}")
    public String home(@PathVariable("postId") Long postId, Model model) {
        model.addAttribute("post", postV1Service.findById(postId));

        return "v1_home";
    }
}