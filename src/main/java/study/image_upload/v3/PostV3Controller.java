package study.image_upload.v3;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/v3/posts")
public class PostV3Controller {

    private final PostV3Service postV3Service;

    @GetMapping("/{postId}")
    public String home(@PathVariable("postId") Long postId, Model model) {
        model.addAttribute("post", postV3Service.findById(postId));

        return "v3_home";
    }
}