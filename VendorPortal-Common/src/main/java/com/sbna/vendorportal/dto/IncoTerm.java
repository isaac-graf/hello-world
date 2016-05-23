package com.sbna.vendorportal.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.sbna.vendorportal.config.TableConfig;
import java.util.List;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import org.codehaus.jackson.annotate.JsonBackReference;

@Entity
@Table(name = TableConfig.TABLE_INCOTERM)
@SequenceGenerator(name = "seq_generator", sequenceName = "seq_incotrm", allocationSize = 1)  
public class IncoTerm extends BaseModel {

    private static final long serialVersionUID = -206278159495834359L;

    @Column(name = "incotrm_val", nullable = false)
    private String incoTermVal;

    // bi-directional many-to-many association to User
    @ManyToMany(mappedBy = "incoTerms")
    @JsonBackReference
    private List<Language> languages;

    public String getIncoTermVal() {
        return incoTermVal;
    }

    public void setIncoTermVal(String incoTermVal) {
        this.incoTermVal = incoTermVal;
    }

    public List<Language> getLanguages() {
        return languages;
    }

    public void setLanguages(List<Language> languages) {
        this.languages = languages;
    }

}
