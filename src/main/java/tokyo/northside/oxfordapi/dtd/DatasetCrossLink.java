package tokyo.northside.oxfordapi.dtd;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DatasetCrossLink {
    private String language;
    @JsonProperty("sense_id")
    private String senseId;
    @JsonProperty("entry_id")
    private String entryId;

    public String getLanguage() {
        return language;
    }

    public void setLanguage(final String language) {
        this.language = language;
    }

    public String getSenseId() {
        return senseId;
    }

    public void setSenseId(final String senseId) {
        this.senseId = senseId;
    }

    public String getEntryId() {
        return entryId;
    }

    public void setEntryId(final String entryId) {
        this.entryId = entryId;
    }

    @Override
    public String toString() {
        return "DatasetCrossLink{" +
                "language='" + language + '\'' +
                ", senseId='" + senseId + '\'' +
                ", entryId='" + entryId + '\'' +
                '}';
    }
}
