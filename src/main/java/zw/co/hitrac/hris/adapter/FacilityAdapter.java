package zw.co.hitrac.hris.adapter;

import zw.co.hitrac.hris.adapter.source.HwoWebServiceConsumer;
import zw.hitrac.hwo.domain.Facility;

/**
 *
 * @author Daniel Nkhoma
 */
public class FacilityAdapter {

    private static final FacilityAdapter INSTANCE = new FacilityAdapter();

    public Facility save(org.hris.Station mohccStation) {

        Facility hwoFacility = HwoWebServiceConsumer.findHwoFacilityByMohccFacilityId(mohccStation.getStationId());

        if (hwoFacility == null) {
            hwoFacility = convert(mohccStation);
            hwoFacility = HwoWebServiceConsumer.saveHwoFacility(hwoFacility);
        }
        return hwoFacility;
    }

    private Facility convert(org.hris.Station mohccStation) {
        Facility hwoFacility = new Facility();
        hwoFacility.setMohccUuid(mohccStation.getStationId());
        hwoFacility.setName(mohccStation.getName());
        hwoFacility.setDescription(mohccStation.getDescription());
        hwoFacility.setRetired(mohccStation.getRetired());
        return hwoFacility;
    }

    public static FacilityAdapter get() {
        return INSTANCE;
    }

}
