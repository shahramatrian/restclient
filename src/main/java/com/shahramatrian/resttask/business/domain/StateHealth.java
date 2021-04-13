package com.shahramatrian.resttask.business.domain;

public class StateHealth {
    private String region; // State
    private String regionCode; // State Code
    private String period; // Time period for the data. Only, the first four digits correspond to the year of the data is included
    private String pctHospitalsBasicEhrNotes; // percentage of hospitals that support basic EHR notes


    public String getRegion() {
        return this.region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getRegionCode() {
        return this.regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public String getPeriod() {
        return this.period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getPctHospitalsBasicEhrNotes() {
        return this.pctHospitalsBasicEhrNotes;
    }

    public void setPctHospitalsBasicEhrNotes(String pctHospitalsBasicEhrNotes) {
        this.pctHospitalsBasicEhrNotes = pctHospitalsBasicEhrNotes;
    }
    

    @Override
    public String toString() {
        return "{" +
            " State='" + getRegion() + "'" +
            ", Percent of Basic EHR Notes='" + getPctHospitalsBasicEhrNotes() + "'" +
            "}";
    }

}
