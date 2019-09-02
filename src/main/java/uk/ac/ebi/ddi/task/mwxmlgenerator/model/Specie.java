package uk.ac.ebi.ddi.task.mwxmlgenerator.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Yasset Perez-Riverol (ypriverol@gmail.com)
 * @date 24/04/2016
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Specie {

    @JsonProperty("Study ID")
    private String studyId;

    @JsonProperty("Latin name")
    private String lantinName;

    @JsonProperty("Common name")
    private String name;

    public String getStudyId() {
        return studyId;
    }

    public void setStudyId(String studyId) {
        this.studyId = studyId;
    }

    public String getLantinName() {
        return lantinName;
    }

    public void setLantinName(String lantinName) {
        this.lantinName = lantinName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Specie{" +
                "studyId='" + studyId + '\'' +
                ", lantinName='" + lantinName + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
