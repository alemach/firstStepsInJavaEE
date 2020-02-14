package pl.coderslab.model;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class RecipePlan {
    private Map<String, List<String[]>> planDetails;

    public Map<String, List<String[]>> getPlanDetails() {
        return planDetails;
    }

    public void setPlanDetails(Map<String, List<String[]>> planDetails) {
        this.planDetails = planDetails;
    }

    public Set<String> getKeys() {
        Set<String> keySet = planDetails.keySet();
        return keySet;
    }

    public List<String[]> getValues(String key) {
        return planDetails.get(key);
    }
}
