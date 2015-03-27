package org.bitxbit.model;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown =  true)
public class BeaconEvent {
    private long facebookId;
    private String firstName;
    private String lastName;
    private String email;
    private String beaconId1;
    private int beaconId2;
    private int beaconId3;
    private float range;
    private Date ts;
    private boolean visit;
    private boolean conversion;

    @JsonCreator
    public BeaconEvent(@JsonProperty("b_id_1") String beaconId1, @JsonProperty("b_id_2") int beaconId2,
                       @JsonProperty("b_id_3") int beaconId3, @JsonProperty("prospects") boolean visit,
                       @JsonProperty("purchases") boolean conversion) {
        this.beaconId1 = beaconId1;
        this.beaconId2 = beaconId2;
        this.beaconId3 = beaconId3;
        this.visit = visit;
        this.conversion = conversion;
    }

    public boolean getConversion() {
        return conversion;
    }

    @JsonProperty("conv")
    public void setConversion(boolean conversion) {
        this.conversion = conversion;
    }

    public String getBeaconId1() {
        return beaconId1;
    }

    @JsonProperty("b_id_1")
    public void setBeaconId1(String beaconId1) {
        this.beaconId1 = beaconId1;
    }

    public int getBeaconId2() {
        return beaconId2;
    }

    @JsonProperty("b_id_2")
    public void setBeaconId2(int beaconId2) {
        this.beaconId2 = beaconId2;
    }

    public int getBeaconId3() {
        return beaconId3;
    }

    @JsonProperty("b_id_3")
    public void setBeaconId3(int beaconId3) {
        this.beaconId3 = beaconId3;
    }

    public boolean getVisit() {
        return visit;
    }

    @JsonProperty("visit")
    public void setVisit(boolean visit) {
        this.visit = visit;
    }

    @Override
    public String toString() {
        return beaconId1 + ":" + beaconId2 + ":" + beaconId3 + " " + visit + " " + conversion;
    }
}
