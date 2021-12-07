package com.drosophila.birdwatching.models;

import com.drosophila.birdwatching.enums.Age;
import com.drosophila.birdwatching.enums.Sex;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@Document(collection = "observations")
@ToString
@Builder
@EqualsAndHashCode
public class Observations {

    @Id
    @Setter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ObjectId _id;
    @Setter @Getter
    private String species;
    @Setter @Getter
    private String weather;
    @Setter @Getter
    private String localization;
    @Setter @Getter
    private String notes;
    @Setter @Getter
    private String habitat;
    private Sex sex;
    private Age age;

    // ObjectId needs to be converted to string
    public String get_id() {
        return _id.toHexString();
    }

    public void setSex(String sex) { this.sex = Sex.fromString(sex); }

    public String getSex() { return sex.value(); }

    public void setAge(String age) { this.age = Age.fromString(age); }

    public String getAge() { return age.value(); }
}
