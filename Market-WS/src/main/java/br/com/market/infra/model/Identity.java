package br.com.market.infra.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

@MappedSuperclass
public abstract class Identity extends Entity<Long> implements Comparable<Identity> {

	/** @serialField */
	private static final long serialVersionUID = -2661277236835013999L;

	@Id
	@Column(name="id", nullable=false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	
	@Override
	public boolean equals(Object other) {
		
		if (!getClass().isInstance(other)) {
			return false;
		}

		Long id = getId();
		if (id == null) {
			return super.equals(other);
		}

		Identity castOther = (Identity) other;
		Long castOtherId = castOther.getId();

		return new EqualsBuilder().append(id, castOtherId).isEquals();
	}
	
	@Override
	public int hashCode() {

		Long id = getId();

		if (id == null) {
			return super.hashCode();
		}

		return new HashCodeBuilder().append(id).toHashCode();
	}
	
	@Override
	public int compareTo(Identity o) {

		Long oId = o.getId();
		if (oId == null) {
			return 1;
		}

		Long thisId = getId();
		if (thisId == null) {
			return -1;
		}

		int compare = thisId.compareTo(oId);
		return compare;
	}
}