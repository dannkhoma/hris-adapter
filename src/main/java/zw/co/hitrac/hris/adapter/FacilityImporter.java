package zw.co.hitrac.hris.adapter;

import java.util.List;
import org.hris.client.soa.webservices.domain.LeanStation;
import zw.co.hitrac.hris.adapter.source.HrisWebServiceClientFactory;

/**
 *
 * @author Daniel
 */
public class FacilityImporter {

    public static void newPushFacility() {
        List<LeanStation> leanStations = HrisWebServiceClientFactory.getStationWebservice().getLeanStations();
        for (LeanStation leanStation : leanStations) {
            try {
                org.hris.Station mohccStation = HrisWebServiceClientFactory.getStationWebservice().getStation(leanStation.getStationId());
                FacilityAdapter.get().save(mohccStation);
                System.out.println("Facility==" + mohccStation.getName());
            } catch (Exception ex) {
                ex.printStackTrace(System.out);
            }
        }
    }

    public static void main(String[] args) {
        newPushFacility();
    }

}
