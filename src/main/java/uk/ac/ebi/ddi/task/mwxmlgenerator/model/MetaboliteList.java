package uk.ac.ebi.ddi.task.mwxmlgenerator.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Yasset Perez-Riverol (ypriverol@gmail.com)
 * @date 19/05/2015
 */

@JsonIgnoreProperties(ignoreUnknown = true)

public class MetaboliteList {

    private Map<String, Metabolite> metabolites = new HashMap<>();

    @JsonAnyGetter
    public Map<String, Metabolite> any() {
        return metabolites;
    }

    @JsonAnySetter
    public void set(String name, Metabolite value) {
        metabolites.put(name, value);
    }

    public boolean hasUnknowProperties() {
        return !metabolites.isEmpty();
    }

    public Map<String, Metabolite> getMetabolites() {
        return metabolites;
    }

    public void setMetabolites(Map<String, Metabolite> metabolites) {
        this.metabolites = metabolites;
    }

    @Override
    public String toString() {
        return "MetaboliteList{" +
                "metabolites=" + metabolites +
                '}';
    }
}
