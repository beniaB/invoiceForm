package pl.connectis.cschool.jcourse.restservice.dto;

import java.util.List;

public class PersonData {

    private Long personId;

    private String personName;

    private List<String> dogNames;
    private List<Long> dogIds;


    public PersonData(Long personId, String personName, List<String> dogNames, List<Long> dogIds) {
        this.personId = personId;
        this.personName = personName;
        this.dogNames = dogNames;
        this.dogIds = dogIds;
    }

    public PersonData() {
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public List<String> getDogNames() {
        return dogNames;
    }

    public void setDogNames(List<String> dogNames) {
        this.dogNames = dogNames;
    }

    public List<Long> getDogIds() {
        return dogIds;
    }

    public void setDogIds(List<Long> dogIds) {
        this.dogIds = dogIds;
    }

}
