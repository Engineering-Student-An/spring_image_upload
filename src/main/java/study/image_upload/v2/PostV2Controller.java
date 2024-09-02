package study.image_upload.v2;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/v2/posts")
public class PostV2Controller {

    private final PostV2Service postV2Service;

    @GetMapping("/{postId}")
    public String home(@PathVariable("postId") Long postId, Model model) {
        model.addAttribute("post", postV2Service.findById(postId));

        return "v2_home";
    }
}