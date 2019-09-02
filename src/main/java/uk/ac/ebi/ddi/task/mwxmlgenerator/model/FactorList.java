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

public class FactorList {

    private Map<String, Factor> factors = new HashMap<>();

    @JsonAnyGetter
    public Map<String, Factor> any() {
        return factors;
    }

    @JsonAnySetter
    public void set(String name, Factor value) {
        factors.put(name, value);
    }

    public boolean hasUnknowProperties() {
        return !factors.isEmpty();
    }

    public Map<String, Factor> getFactors() {
        return factors;
    }

    public void setFactors(Map<String, Factor> factors) {
        this.factors = factors;
    }
}
