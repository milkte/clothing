package com.model;

/**
 * Created by SRawla on 4/10/2015.
 */
public class Address {

    private int addressId;
    private String addressLine1;
    private String addressLine2="";
    private String street;
    private String state;
    private String city;
    private String country;
    private int zipcode;

    public Address() {
    }

    
    /**
	 * @param addressId
	 * @param addressLine1
	 * @param addressLine2
	 * @param street
	 * @param state
	 * @param city
	 * @param country
	 * @param zipcode
	 */
	public Address(int addressId, String addressLine1, String addressLine2,
			String street, String state, String city, String country,
			int zipcode) {
		super();
		this.addressId = addressId;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.street = street;
		this.state = state;
		this.city = city;
		this.country = country;
		this.zipcode = zipcode;
	}


	/**
     * @return the addressId
     */
    public int getAddressId() {
        return addressId;
    }

    /**
     * @param addressId the addressId to set
     */
    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    /**
     * @return the street
     */
    public String getStreet() {
        return street;
    }

    /**
     * @param Street the street to set
     */
    public void setStreet(String Street) {
        this.street = Street;
    }

    /**
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * @param State the state to set
     */
    public void setState(String State) {
        this.state = State;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param City the city to set
     */
    public void setCity(String City) {
        this.city = City;
    }

    /**
     * @return the zipcode
     */
    public int getZipcode() {
        return zipcode;
    }

    /**
     * @param Zipcode the zipcode to set
     */
    public void setZipcode(int Zipcode) {
        this.zipcode = Zipcode;
    }


	/**
	 * @return the addressLine1
	 */
	public String getAddressLine1() {
		return addressLine1;
	}


	/**
	 * @param addressLine1 the addressLine1 to set
	 */
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}


	/**
	 * @return the addressLine2
	 */
	public String getAddressLine2() {
		return addressLine2;
	}


	/**
	 * @param addressLine2 the addressLine2 to set
	 */
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}


	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}


	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + addressId;
		result = prime * result
				+ ((addressLine1 == null) ? 0 : addressLine1.hashCode());
		result = prime * result
				+ ((addressLine2 == null) ? 0 : addressLine2.hashCode());
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result + ((street == null) ? 0 : street.hashCode());
		result = prime * result + zipcode;
		return result;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Address)) {
			return false;
		}
		Address other = (Address) obj;
		if (addressId != other.addressId) {
			return false;
		}
		if (addressLine1 == null) {
			if (other.addressLine1 != null) {
				return false;
			}
		} else if (!addressLine1.equals(other.addressLine1)) {
			return false;
		}
		if (addressLine2 == null) {
			if (other.addressLine2 != null) {
				return false;
			}
		} else if (!addressLine2.equals(other.addressLine2)) {
			return false;
		}
		if (city == null) {
			if (other.city != null) {
				return false;
			}
		} else if (!city.equals(other.city)) {
			return false;
		}
		if (country == null) {
			if (other.country != null) {
				return false;
			}
		} else if (!country.equals(other.country)) {
			return false;
		}
		if (state == null) {
			if (other.state != null) {
				return false;
			}
		} else if (!state.equals(other.state)) {
			return false;
		}
		if (street == null) {
			if (other.street != null) {
				return false;
			}
		} else if (!street.equals(other.street)) {
			return false;
		}
		if (zipcode != other.zipcode) {
			return false;
		}
		return true;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Address [addressId=");
		builder.append(addressId);
		builder.append(", addressLine1=");
		builder.append(addressLine1);
		builder.append(", addressLine2=");
		builder.append(addressLine2);
		builder.append(", street=");
		builder.append(street);
		builder.append(", state=");
		builder.append(state);
		builder.append(", city=");
		builder.append(city);
		builder.append(", country=");
		builder.append(country);
		builder.append(", zipcode=");
		builder.append(zipcode);
		builder.append("]");
		return builder.toString();
	}


}
