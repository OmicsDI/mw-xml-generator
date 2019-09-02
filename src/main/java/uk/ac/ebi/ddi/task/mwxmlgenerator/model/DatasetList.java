package uk.ac.ebi.ddi.task.mwxmlgenerator.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Yasset Perez-Riverol (ypriverol@gmail.com)
 * @date 18/05/2015
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class DatasetList {

    private Map<String, MWDataSetDetails> datasets = new HashMap<>();

    @JsonAnyGetter
    public Map<String, MWDataSetDetails> any() {
        return datasets;
    }

    @JsonAnySetter
    public void set(String name, MWDataSetDetails value) {
        datasets.put(name, value);
    }

    public boolean hasUnknowProperties() {
        return !datasets.isEmpty();
    }

    public Map<String, MWDataSetDetails> getDatasets() {
        if (datasets != null) {
            return datasets;
        }
        return new HashMap<>();
    }

    public void setDatasets(Map<String, MWDataSetDetails> datasets) {
        this.datasets = datasets;
    }
}
