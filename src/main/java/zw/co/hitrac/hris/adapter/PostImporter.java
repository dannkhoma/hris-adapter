package zw.co.hitrac.hris.adapter;

import java.util.List;
import org.hris.client.soa.webservices.domain.LeanPost;
import zw.co.hitrac.hris.adapter.source.HrisWebServiceClientFactory;

/**
 *
 * @author Daniel
 */
public class PostImporter {

    public static void newPushPosts() {
        List<LeanPost> leanPosts = HrisWebServiceClientFactory.getPostWebservice().getLeanPosts();
        for (LeanPost leanPost : leanPosts) {
            try {
                org.hris.Post mohccPost = HrisWebServiceClientFactory.getPostWebservice().getPost(leanPost.getPostId());
                PostAdapter.get().save(mohccPost);
                System.out.println("Post==" + mohccPost.getName());
            } catch (Exception ex) {
                ex.printStackTrace(System.out);
            }
        }
    }

    public static void main(String[] args) {
        newPushPosts();
    }

}
