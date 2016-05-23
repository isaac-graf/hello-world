package com.sbna.vendorportal.dto;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.sbna.vendorportal.config.TableConfig;

@Entity
@Table(name = TableConfig.TABLE_VENDORPROFILE)
@SequenceGenerator(name = "seq_generator", sequenceName = "seq_vendorprofile", allocationSize = 1)
public class VendorProfile extends BaseModel {

	/*
	 * @Column(name = "vendor_id", nullable = false) private long vendorId;
	 */

	private static final long serialVersionUID = -8309150906560351973L;

	// bi-directional many-to-one association to Usr
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "vendor_id")
	@JsonIgnore
	private User user;

	@OneToMany(mappedBy = "vendorProfileId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<VendorDetails> vendorDetails;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<VendorDetails> getVendorDetails() {
		return vendorDetails;
	}

	public void setVendorDetails(List<VendorDetails> vendorDetails) {
		this.vendorDetails = vendorDetails;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
