package com.sbna.vendorportal.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.sbna.vendorportal.config.TableConfig;
import java.util.List;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import org.codehaus.jackson.annotate.JsonBackReference;

@Entity
@Table(name = TableConfig.TABLE_STATUS)
@SequenceGenerator(name = "seq_generator", sequenceName = "seq_status", allocationSize = 1)
public class Status extends BaseModel {

    @Column(name = "status_desc_eng")
    private String statusDescEng;

    @OneToMany(mappedBy = "status", fetch = FetchType.LAZY)
    @JsonBackReference
    private List<StatusDesc> statusDescs;

    // bi-directional many-to-many association to Module
    @ManyToMany(mappedBy = "statuses")
    private List<Module> modules;

    public String getStatusDescEng() {
        return this.statusDescEng;
    }

    public void setStatusDescEng(String statusDescEng) {
        this.statusDescEng = statusDescEng;
    }

    public List<Module> getModules() {
        return this.modules;
    }

    public void setModules(List<Module> modules) {
        this.modules = modules;
    }

    public List<StatusDesc> getStatusDescs() {
        return statusDescs;
    }

    public void setStatusDescs(List<StatusDesc> statusDescs) {
        this.statusDescs = statusDescs;
    }

	
}
