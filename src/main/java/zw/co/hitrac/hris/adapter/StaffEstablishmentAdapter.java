package zw.co.hitrac.hris.adapter;

import zw.co.hitrac.hris.adapter.source.HwoWebServiceConsumer;
import zw.hitrac.hwo.domain.Facility;
import zw.hitrac.hwo.domain.Post;
import zw.hitrac.hwo.domain.StaffEstablishment;

/**
 *
 * @author Tonderai ndangana
 */
public class StaffEstablishmentAdapter {

    private static final StaffEstablishmentAdapter INSTANCE = new StaffEstablishmentAdapter();
    private Post hwoPost;
    private Facility hwoFacilty;

    public StaffEstablishment save(org.hris.StationEstablishment mohccStationEstablishment) {

        StaffEstablishment hwoStaffEstablishment = HwoWebServiceConsumer.findHwoStaffEstablishmentByMohccId(mohccStationEstablishment.getStationEstablishmentId());

        if (hwoStaffEstablishment == null) {
            hwoStaffEstablishment = convert(mohccStationEstablishment);
            hwoStaffEstablishment = HwoWebServiceConsumer.saveHwoStaffEstablishment(hwoStaffEstablishment);
        }
        return hwoStaffEstablishment;
    }

    private StaffEstablishment convert(org.hris.StationEstablishment mohccStationEstablishment) {

        hwoPost = findHwoPost(mohccStationEstablishment.getPost());
        hwoFacilty = findHwoFacilty(mohccStationEstablishment.getStation());

        StaffEstablishment hwoStaffEstablishment;

        if (hwoPost != null && hwoFacilty != null) {
            hwoStaffEstablishment = setHwoStaffEstablishment(hwoPost, hwoFacilty, mohccStationEstablishment);
        } else {

            hwoPost = PostAdapter.get().save(mohccStationEstablishment.getPost());
            hwoFacilty = FacilityAdapter.get().save(mohccStationEstablishment.getStation());
            hwoStaffEstablishment = setHwoStaffEstablishment(hwoPost, hwoFacilty, mohccStationEstablishment);

        }

        return hwoStaffEstablishment;
    }

    private Post findHwoPost(org.hris.Post mohccPost) {
        hwoPost = HwoWebServiceConsumer.findHwoPostByMohccPostId(mohccPost.getPostId());
        return hwoPost;
    }

    private Facility findHwoFacilty(org.hris.Station mohccStation) {
        hwoFacilty = HwoWebServiceConsumer.findHwoFacilityByMohccFacilityId(mohccStation.getStationId());
        return hwoFacilty;
    }

    private StaffEstablishment setHwoStaffEstablishment(Post hwoPost, Facility hwoFacilty, org.hris.StationEstablishment mohccStationEstablishment) {
        StaffEstablishment hwoStaffEstablishment = new StaffEstablishment();

        hwoStaffEstablishment.setPost(hwoPost);
        hwoStaffEstablishment.setFacility(hwoFacilty);
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
