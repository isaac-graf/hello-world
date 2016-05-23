package com.sbna.vendorportal.dto;

import java.io.Serializable;
import java.util.Date;

import javax.inject.Inject;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import org.codehaus.jackson.annotate.JsonIgnore;
import com.sbna.vendorportal.util.Utils;

/**
 *
 * @author Pbatthala
 */
@MappedSuperclass
public class BaseModel implements Serializable, Comparable {

    @Inject
    @Transient
    @JsonIgnore
    private Utils utils;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_generator")
    private Long id;

    @Column(name = "row_created_by", nullable = true)
    private String rowCreatedBy;

    @Column(name = "row_updated_by", nullable = true)
    private String rowUpdatedBy;

    @Column(name = "row_created_tmst", nullable = false)
    private Date created;

    @Transient
    @Column(name = "row_updated_tmst", nullable = false)
    private Date updated;

    protected BaseModel() {
        this.created = new Date();
    }

    public boolean hasValidId() {
        return id != null && id > 0;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRowCreatedBy() {
        return rowCreatedBy;
    }

    public void setRowCreatedBy(String rowCreatedBy) {
        this.rowCreatedBy = rowCreatedBy;
    }

    public String getRowUpdatedBy() {
        return rowUpdatedBy;
    }

    public void setRowUpdatedBy(String rowUpdatedBy) {
        this.rowUpdatedBy = rowUpdatedBy;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public Utils getUtils() {
        return utils;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(this.getClass().isAssignableFrom(o.getClass()))) {
            return false;
        }

        BaseModel baseModel = (BaseModel) o;

        return id != null && id.equals(baseModel.id);
    }

    @Override
    public int hashCode() {
        if (id != null) {
            return id.hashCode();
        } else {
            return -1;
        }
    }

    @Override
    public int compareTo(Object o) {
        BaseModel baseModel = (BaseModel) o;
        return this.id.compareTo(baseModel.getId());
    }

    @Override
    public String toString() {
        return "BaseModel{" + "utils=" + utils + ", id=" + id + ", created=" + created + ", updated=" + updated + '}';
    }
    
    public String auditString(){
    	return "BaseModel{" + "id=" + id + ", created=" + created + ", updated=" + updated + '}';
    }
}
