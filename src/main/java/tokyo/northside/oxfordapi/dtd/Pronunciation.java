package tokyo.northside.oxfordapi.dtd;

import java.util.List;

public class Pronunciation {
    private List<String> dialects;
    private String phoneticNotation;
    private String phoneticSpelling;
    private String audioFile;
    private List<Region> regions;
    private List<Register> registers;

    public List<String> getDialects() {
        return dialects;
    }

    public void setDialects(final List<String> dialects) {
        this.dialects = dialects;
    }

    public String getPhoneticNotation() {
        return phoneticNotation;
    }

    public void setPhoneticNotation(final String phoneticNotation) {
        this.phoneticNotation = phoneticNotation;
    }

    public String getPhoneticSpelling() {
        return phoneticSpelling;
    }

    public void setPhoneticSpelling(final String phoneticSpelling) {
        this.phoneticSpelling = phoneticSpelling;
    }

    public String getAudioFile() {
        return audioFile;
    }

    public void setAudioFile(final String audioFile) {
        this.audioFile = audioFile;
    }

    public List<Region> getRegions() {
        return regions;
    }

    public void setRegions(final List<Region> regions) {
        this.regions = regions;
    }

    public List<Register> getRegisters() {
        return registers;
    }

    public void setRegisters(final List<Register> registers) {
        this.registers = registers;
    }

    @Override
    public String toString() {
        return "Pronunciation{" +
                "dialects=" + dialects +
                ", phoneticNotation='" + phoneticNotation + '\'' +
                ", phoneticSpelling='" + phoneticSpelling + '\'' +
                ", audioFile='" + audioFile + '\'' +
                ", regions=" + regions +
                ", registers=" + registers +
                '}';
    }
}
