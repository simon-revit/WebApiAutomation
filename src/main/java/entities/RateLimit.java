package entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class RateLimit {

    private int coreLimit;
    private String searchLimit;

    public int getCoreLimit() {
        return coreLimit;
    }

    public String getSearchLimit() {
        return searchLimit;
    }

    // refer to https://api.github.com/rate_limit for references to the code below
    // This includes: 'resources' and 'core' etc...
    // "resources" fields tell JSON to look for resources at the TOP level
    @SuppressWarnings("unchecked") // Not necessary, but nice to have
    @JsonProperty("resources")
    private void unmarshallNested(Map<String, Object> resources) { // can use something else rather than 'resources'
        Map<String, Integer> core = (Map<String,Integer>) resources.get("core");
        coreLimit = core.get("limit");
        // Should be Interger, not a String, like the commented-out line below:
        //Map<String, String> search = (Map<String,Integer>) resources.get("search");
        Map<String, String> search = (Map<String,String>) resources.get("search");
        searchLimit = String.valueOf(search.get("limit"));

    }


}
