package tokyo.northside.oxfordapi.dtd;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ThesaurusLink {
    @JsonProperty("entry_id")
    private String entryId;
    @JsonProperty("sense_id")
    private String senseId;

    public String getEntryId() {
        return entryId;
    }

    public void setEntryId(final String entryId) {
        this.entryId = entryId;
    }

    public String getSenseId() {
        return senseId;
    }

    public void setSenseId(final String senseId) {
        this.senseId = senseId;
    }

    @Override
    public String toString() {
        return "ThesaurusLink{" + "entry_id='" + entryId + '\'' + ", sense_id='" + senseId + '\'' + '}';
    }
}
