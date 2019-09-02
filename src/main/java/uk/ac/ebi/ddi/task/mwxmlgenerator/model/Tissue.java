package uk.ac.ebi.ddi.task.mwxmlgenerator.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Yasset Perez-Riverol (ypriverol@gmail.com)
 * @date 24/04/2016
 */
@JsonIgnoreProperties(ignoreUnknown =  true)
public class Tissue {

    @JsonProperty("Study ID")
    private String studyId;

    @JsonProperty("Sample source")
    private String tissue;

    public String getStudyId() {
        return studyId;
    }

    public void setStudyId(String studyId) {
        this.studyId = studyId;
    }

    public String getTissue() {
        return tissue;
    }

    public void setTissue(String tissue) {
        this.tissue = tissue;
    }

    @Override
    public String toString() {
        return "Tissue{" +
                "studyId='" + studyId + '\'' +
                ", tissue='" + tissue + '\'' +
                '}';
    }
}
