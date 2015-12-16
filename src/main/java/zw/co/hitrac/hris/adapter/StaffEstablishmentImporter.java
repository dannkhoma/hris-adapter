
package zw.co.hitrac.hris.adapter;

import java.util.List;
import org.hris.StationEstablishment;
import zw.co.hitrac.hris.adapter.source.HrisWebServiceClientFactory;

/**
 *
 * @author Tonderai Ndangana
 */
public class StaffEstablishmentImporter {
  
    public static void  newPushStaffEstablishment (){
        
        List<StationEstablishment> staffEstablishments = HrisWebServiceClientFactory.getStationEstablishmentWebService().getStationEstablishments();
        for (StationEstablishment StaffEstablishment : staffEstablishments) {
            try {
                       StaffEstablishmentAdapter.get().save(StaffEstablishment);
                System.out.println("StaffEstablishment==" +StaffEstablishment.getStationEstablishmentId());
            } catch (Exception ex) {
                ex.printStackTrace(System.out);
            }
        }
    }

    public static void main(String[] args) {
            newPushStaffEstablishment();
  }
}
