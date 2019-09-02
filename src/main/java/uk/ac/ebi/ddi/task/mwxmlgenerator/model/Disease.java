package uk.ac.ebi.ddi.task.mwxmlgenerator.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Yasset Perez-Riverol (ypriverol@gmail.com)
 * @date 24/04/2016
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Disease {

    @JsonProperty("Study ID")
    private String studyId;

    @JsonProperty("Disease")
    private String Disease;

    public String getStudyId() {
        return studyId;
    }

    public void setStudyId(String studyId) {
        this.studyId = studyId;
    }

    public String getDisease() {
        return Disease;
    }

    public void setDisease(String disease) {
        Disease = disease;
    }

    @Override
    public String toString() {
        return "Disease{" +
                "studyId='" + studyId + '\'' +
                ", Disease='" + Disease + '\'' +
                '}';
    }
}
