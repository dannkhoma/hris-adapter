package zw.co.hitrac.hris.adapter;

import org.hris.StationEstablishment;
import zw.co.hitrac.hris.adapter.source.HwoWebServiceConsumer;
import zw.hitrac.hwo.domain.Post;
import zw.hitrac.hwo.domain.StaffEstablishment;

/**
 *
 * @author Tonderai ndangana
 */
public class StaffEstablishmentAdapter {
    
    private static final StaffEstablishmentAdapter INSTANCE = new StaffEstablishmentAdapter();
    
    public StaffEstablishment save(org.hris.StationEstablishment mohccStaffEstablishment) {
        StationEstablishment mohccStationEstablishment = null;
        
        StaffEstablishment hwoStaffEstablishment = HwoWebServiceConsumer.getHwoStaffEstablishment(mohccStationEstablishment.getStationEstablishmentId());
        
        if (hwoStaffEstablishment == null) {
            hwoStaffEstablishment = convert(mohccStationEstablishment);
            hwoStaffEstablishment = HwoWebServiceConsumer.saveHwoStaffEstablishment(hwoStaffEstablishment);
        }
        return hwoStaffEstablishment;
    }
    
    private StaffEstablishment convert(org.hris.StationEstablishment mohccStationEstablishment) {
        StaffEstablishment hwoStaffEstablishment = new StaffEstablishment();
        
        Post hwoPost = findHwoPost(mohccStationEstablishment.getPost());
        if (hwoPost != null) {
            hwoStaffEstablishment.setPost(hwoPost);
        }
        
        hwoStaffEstablishment.setFilledPost(mohccStationEstablishment.getOccupiedPosts());
        hwoStaffEstablishment.setTotalNumberOfPost(mohccStationEstablishment.getApprovedPosts());
        hwoStaffEstablishment.setVacantPost(mohccStationEstablishment.getVacantPosts());
        return hwoStaffEstablishment;
    }
    
    private Post findHwoPost(org.hris.Post mohccPost) {
        Post hwoPost = HwoWebServiceConsumer.getHwoPost(mohccPost.getPostId());
        return hwoPost;
    }
    
    public static StaffEstablishmentAdapter get() {
        return INSTANCE;
    }
    
}
