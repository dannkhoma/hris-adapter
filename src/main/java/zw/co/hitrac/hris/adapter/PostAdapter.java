package zw.co.hitrac.hris.adapter;

import zw.co.hitrac.hris.adapter.source.HwoWebServiceConsumer;
import zw.hitrac.hwo.domain.Post;

/**
 *
 * @author Daniel Nkhoma
 */
public class PostAdapter {

    private static final PostAdapter INSTANCE = new PostAdapter();

    public Post save(org.hris.Post mohccPost) {

        Post hwoPost = HwoWebServiceConsumer.findHwoPostByMohccPostId(mohccPost.getPostId());

        if (hwoPost == null) {
            hwoPost = convert(mohccPost);
            hwoPost = HwoWebServiceConsumer.saveHwoPost(hwoPost);
        }
        return hwoPost;
    }

    private Post convert(org.hris.Post mohccPost) {
        Post hwoPost = new Post();
        hwoPost.setMohccUuid(mohccPost.getPostId());
        hwoPost.setName(mohccPost.getName());
        hwoPost.setDescription(mohccPost.getName());
        hwoPost.setRetired(mohccPost.getRetired());
        return hwoPost;
    }

    public static PostAdapter get() {
        return INSTANCE;
    }

}
