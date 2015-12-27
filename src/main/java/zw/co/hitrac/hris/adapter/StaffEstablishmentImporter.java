package zw.co.hitrac.hris.adapter;

import java.util.List;
import org.hris.StationEstablishment;
import zw.co.hitrac.hris.adapter.source.HrisWebServiceClientFactory;

/**
 *
 * @author Tonderai Ndangana
 */
public class StaffEstablishmentImporter {

    public static void newPushStaffEstablishment() {

        List<StationEstablishment> staffEstablishments = HrisWebServiceClientFactory.getStationEstablishmentWebService().getStationEstablishments();
        for (StationEstablishment staffEstablishment : staffEstablishments) {
            try {
                StaffEstablishmentAdapter.get().save(staffEstablishment);
                System.out.println("StaffEstablishment==" + staffEstablishment.getStationEstablishmentId());
            } catch (Exception ex) {
                ex.printStackTrace(System.out);
            }
        }
    }

    public static void main(String[] args) {
        newPushStaffEstablishment();
    }
}
