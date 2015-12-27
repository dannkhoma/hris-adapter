package zw.co.hitrac.hris.adapter;

import zw.co.hitrac.hris.adapter.source.HwoWebServiceConsumer;
import zw.hitrac.hwo.domain.Post;
import zw.hitrac.hwo.domain.StaffEstablishment;

/**
 *
 * @author Tonderai ndangana
 */
public class StaffEstablishmentAdapter {
    
    private static final StaffEstablishmentAdapter INSTANCE = new StaffEstablishmentAdapter();
    
    public StaffEstablishment save(org.hris.StationEstablishment mohccStationEstablishment) {
        
        StaffEstablishment hwoStaffEstablishment = HwoWebServiceConsumer.findHwoStaffEstablishmentByMohccId(mohccStationEstablishment.getStationEstablishmentId());
        
        if (hwoStaffEstablishment == null) {
            hwoStaffEstablishment = convert(mohccStationEstablishment);
            hwoStaffEstablishment = HwoWebServiceConsumer.saveHwoStaffEstablishment(hwoStaffEstablishment);
        }
        return hwoStaffEstablishment;
    }
    
    private StaffEstablishment convert(org.hris.StationEstablishment mohccStationEstablishment) {
        
        Post hwoPost = findHwoPost(mohccStationEstablishment.getPost());
        StaffEstablishment hwoStaffEstablishment;
        
        if (hwoPost != null) {
            hwoStaffEstablishment = setHwoStaffEstablishment(hwoPost, mohccStationEstablishment);
        } else {
            
            hwoPost = PostAdapter.get().save(mohccStationEstablishment.getPost());
            hwoStaffEstablishment = setHwoStaffEstablishment(hwoPost, mohccStationEstablishment);
            
        }
        
        return hwoStaffEstablishment;
    }
    
    private Post findHwoPost(org.hris.Post mohccPost) {
        Post hwoPost = HwoWebServiceConsumer.findHwoPostByMohccPostId(mohccPost.getPostId());
        return hwoPost;
    }
    
    private StaffEstablishment setHwoStaffEstablishment(Post hwoPost, org.hris.StationEstablishment mohccStationEstablishment) {
        StaffEstablishment hwoStaffEstablishment = new StaffEstablishment();
        
        hwoStaffEstablishment.setPost(hwoPost);
        hwoStaffEstablishment.setMohccUuid(mohccStationEstablishment.getStationEstablishmentId());
        hwoStaffEstablishment.setFilledPost(mohccStationEstablishment.getOccupiedPosts());
        hwoStaffEstablishment.setTotalNumberOfPost(mohccStationEstablishment.getApprovedPosts());
        hwoStaffEstablishment.setVacantPost(mohccStationEstablishment.getVacantPosts());
        return hwoStaffEstablishment;
    }
    
    public static StaffEstablishmentAdapter get() {
        return INSTANCE;
    }
    
}
