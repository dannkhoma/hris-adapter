package zw.co.hitrac.hris.adapter.source;

import org.springframework.web.client.RestTemplate;
import zw.hitrac.hwo.domain.Post;

/**
 *
 * @author Daniel Nkhoma
 */
public class HwoWebServiceConsumer {

    private static final RestTemplate restTemplate = new RestTemplate();

    public static Post consumeHwoPost(String postId) {
        Post hwoPost = restTemplate.getForObject("http://localhost:2507/hwo/posts/post/get_post/" + postId, Post.class);
        return hwoPost;
    }

    public static Post saveHwoPost(Post post) {
        Post hwoPost = restTemplate.postForObject("http://localhost:2507/hwo/posts/post/save", post, Post.class);
        return hwoPost;
    }

}
