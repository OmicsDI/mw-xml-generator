package uk.ac.ebi.ddi.task.mwxmlgenerator.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Yasset Perez-Riverol (ypriverol@gmail.com)
 * @date 19/05/2015
 */

@JsonIgnoreProperties(ignoreUnknown = true)

public class ChebiID {

    @JsonProperty("pubchem_cid")
    private String pubchemId;

    @JsonProperty("chebi_id")
    private String chebiId;

    public String getPubchemId() {
        return pubchemId;
    }

    public void setPubchemId(String pubchemId) {
        this.pubchemId = pubchemId;
    }

    public String getChebiId() {
        return chebiId;
    }

    public void setChebiId(String chebiId) {
        this.chebiId = chebiId;
    }
}
