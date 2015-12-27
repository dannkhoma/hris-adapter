package zw.co.hitrac.hris.adapter.source;

import org.hris.client.soa.webservices.PostWebservice;
import org.hris.client.soa.webservices.StationEstablishmentWebService;
import org.hris.client.soa.webservices.StationWebService;
import org.jboss.resteasy.client.jaxrs.BasicAuthentication;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

/**
 *
 * @author Daniel Nkhoma
 */
public class HrisWebServiceClientFactory {

    public static PostWebservice getPostWebservice() {
        return getTarget().proxy(PostWebservice.class);
    }

    public static StationWebService getStationWebservice() {
        return getTarget().proxy(StationWebService.class);
    }

    public static StationEstablishmentWebService getStationEstablishmentWebService() {
        return getTarget().proxy(StationEstablishmentWebService.class);
    }

    private static ResteasyWebTarget getTarget() {
        ResteasyClient client = new ResteasyClientBuilder().build();
        client.register(new BasicAuthentication("hris", "hr1s"));
        ResteasyWebTarget target = client.target("http://localhost:8080/hris-webservices/rest");
        return target;
    }

}
