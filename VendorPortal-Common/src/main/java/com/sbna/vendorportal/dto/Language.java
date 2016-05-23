package com.sbna.vendorportal.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.sbna.vendorportal.config.TableConfig;
import javax.persistence.SequenceGenerator;
import org.codehaus.jackson.annotate.JsonManagedReference;

@Entity
@Table(name = TableConfig.TABLE_LANG)
@SequenceGenerator(name = "seq_generator", sequenceName = "seq_lang", allocationSize = 1)
public class Language extends BaseModel {

    private static final long serialVersionUID = 621860388249122291L;

    @Column(name = "lang_key", nullable = false)
    private String langKey;

    @Column(name = "lang_name", nullable = false)
    private String langName;

    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinTable(name = "incotrm_lang", joinColumns = {
        @JoinColumn(name = "incotrm_id")}, inverseJoinColumns = {
        @JoinColumn(name = "lang_id")})
    @JsonManagedReference
    private List<IncoTerm> incoTerms = new ArrayList<IncoTerm>();

    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinTable(name = "pmttrm_lang", joinColumns = {
        @JoinColumn(name = "pmttrm_id")}, inverseJoinColumns = {
        @JoinColumn(name = "lang_id")})
    private List<PaymentTerm> paymentTerms = new ArrayList<PaymentTerm>();

    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinTable(name = "material_lang", joinColumns = {
        @JoinColumn(name = "material_id")}, inverseJoinColumns = {
        @JoinColumn(name = "lang_id")})
    private List<Material> materials = new ArrayList<Material>();

    public String getLangKey() {
        return langKey;
    }

    public void setLangKey(String langKey) {
        this.langKey = langKey;
    }

    public String getLangName() {
        return langName;
    }

    public void setLangName(String langName) {
        this.langName = langName;
    }

    public List<IncoTerm> getIncoTerms() {
        return incoTerms;
    }

    public void setIncoTerms(List<IncoTerm> incoTerms) {
        this.incoTerms = incoTerms;
    }

    public List<PaymentTerm> getPaymentTerms() {
        return paymentTerms;
    }

    public void setPaymentTerms(List<PaymentTerm> paymentTerms) {
        this.paymentTerms = paymentTerms;
    }

    public List<Material> getMaterials() {
        return materials;
    }

    public void setMaterials(List<Material> materials) {
        this.materials = materials;
    }
}
