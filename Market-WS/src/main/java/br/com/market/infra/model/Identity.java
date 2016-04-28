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
	@Column(name="cod", nullable=false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getCod() {
		return cod;
	}
	
	@Override
	public boolean equals(Object other) {
		
		if (!getClass().isInstance(other)) {
			return false;
		}

		Long cod = getCod();
		if (cod == null) {
			return super.equals(other);
		}

		Identity castOther = (Identity) other;
		Long castOtherId = castOther.getCod();

		return new EqualsBuilder().append(cod, castOtherId).isEquals();
	}
	
	@Override
	public int hashCode() {

		Long cod = getCod();

		if (cod == null) {
			return super.hashCode();
		}

		return new HashCodeBuilder().append(cod).toHashCode();
	}
	
	@Override
	public int compareTo(Identity o) {

		Long oCod = o.getCod();
		if (oCod == null) {
			return 1;
		}

		Long thisCod = getCod();
		if (thisCod == null) {
			return -1;
		}

		int compare = thisCod.compareTo(oCod);
		return compare;
	}
}