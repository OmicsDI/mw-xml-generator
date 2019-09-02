package uk.ac.ebi.ddi.task.mwxmlgenerator.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Yasset Perez-Riverol (ypriverol@gmail.com)
 * @date 20/05/2015
 */

public class AnalysisList {

    private Map<String, Analysis> analysisMap = new HashMap<>();

    @JsonAnyGetter
    public Map<String, Analysis> any() {
        return analysisMap;
    }

    @JsonAnySetter
    public void set(String name, Analysis value) {
        analysisMap.put(name, value);
    }

    public boolean hasUnknowProperties() {
        return !analysisMap.isEmpty();
    }

    public Map<String, Analysis> getAnalysisMap() {
        return analysisMap;
    }

    public void setAnalysisMap(Map<String, Analysis> analysisMap) {
        this.analysisMap = analysisMap;
    }
}
