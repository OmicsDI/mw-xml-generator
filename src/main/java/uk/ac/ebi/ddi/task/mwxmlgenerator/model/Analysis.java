package uk.ac.ebi.ddi.task.mwxmlgenerator.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Yasset Perez-Riverol (ypriverol@gmail.com)
 * @date 19/05/2015
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Analysis {

    @JsonProperty("study_id")
    private String id;

    @JsonProperty("analysis_id")
    private String analysisId;

    @JsonProperty("analysis_summary")
    private String summary;

    @JsonProperty("analysis_type")
    private String type;

    @JsonProperty("instrument_name")
    private String instrumentName;

    @JsonProperty("instrument_type")
    private String instrumentType;

    @JsonProperty("ms_type")
    private String msType;

    @JsonProperty("ion_mode")
    private String ionMode;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAnalysisId() {
        return analysisId;
    }

    public void setAnalysisId(String analysisId) {
        this.analysisId = analysisId;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getInstrumentName() {
        return instrumentName;
    }

    public void setInstrumentName(String instrumentName) {
        this.instrumentName = instrumentName;
    }

    public String getInstrumentType() {
        return instrumentType;
    }

    public void setInstrumentType(String instrumentType) {
        this.instrumentType = instrumentType;
    }

    public String getMsType() {
        return msType;
    }

    public void setMsType(String msType) {
        this.msType = msType;
    }

    public String getIonMode() {
        return ionMode;
    }

    public void setIonMode(String ionMode) {
        this.ionMode = ionMode;
    }
}
