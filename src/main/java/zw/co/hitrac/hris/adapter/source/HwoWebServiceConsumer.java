package zw.co.hitrac.hris.adapter.source;

import org.springframework.web.client.RestTemplate;
import zw.hitrac.hwo.domain.Facility;
import zw.hitrac.hwo.domain.Post;
import zw.hitrac.hwo.domain.StaffEstablishment;

/**
 *
 * @author Daniel Nkhoma
 */
public class HwoWebServiceConsumer {

    private static final RestTemplate restTemplate = new RestTemplate();

    public static Post findHwoPostByMohccPostId(String postId) {
        Post hwoPost = restTemplate.getForObject("http://localhost:2507/hwo/posts/post/get_post?post_id=" + postId, Post.class);
        return hwoPost;
    }

    public static Post saveHwoPost(Post post) {
        Post hwoPost = restTemplate.postForObject("http://localhost:2507/hwo/posts/post/save", post, Post.class);
        return hwoPost;
    }

    public static Facility findHwoFacilityByMohccFacilityId(String facilityId) {
        Facility hwoFacility = restTemplate.getForObject("http://localhost:2507/hwo/facilities/facility/get_facility?facility_id=" + facilityId, Facility.class);
        return hwoFacility;
    }

    public static Facility saveHwoFacility(Facility facility) {
        Facility hwoFacility = restTemplate.postForObject("http://localhost:2507/hwo/facilities/facility/save", facility, Facility.class);
        return hwoFacility;
    }

    public static StaffEstablishment findHwoStaffEstablishmentByMohccId(String staffEstablishmentId) {
        StaffEstablishment hwoStaffEstablishment = restTemplate.getForObject("http://localhost:2507/hwo/staffEstablishments/staffEstablishment/get_staffEstablishment?staffEstablishment_id=" + staffEstablishmentId, StaffEstablishment.class);
        return hwoStaffEstablishment;

    }

    public static StaffEstablishment saveHwoStaffEstablishment(StaffEstablishment staffEstablishment) {

        StaffEstablishment hwoStaffEstablishment = restTemplate.postForObject("http://localhost:2507/hwo/staffEstablishments/staffEstablishment/save", staffEstablishment, StaffEstablishment.class);
        return hwoStaffEstablishment;
    }

}
