package org.mat.nounou.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.sun.istack.NotNull;

@XmlRootElement
@Entity
@Table( name = "People")
public class People {
	  @Basic(optional = false)
	    @NotNull
	    @Column(name = "username")
	    private String username;
	    @Basic(optional = false)
	    @NotNull
	    @Column(name = "password")
	    private String password;
	    @Id
	    @Basic(optional = false)
	    @NotNull
	    @Column(name = "private_key")
	    private String privateKey;
	    @Column(name = "name")
	    private String name;
	    @Basic(optional = false)
	    @NotNull
	    @Column(name = "active")
	    private boolean active;
	    @Basic(optional = false)
	    @NotNull
	    @Column(name = "anonymous")
	    private boolean anonymous;
	    @Basic(optional = false)
	    @NotNull
	    @Column(name = "positive_ratings")
	    private long positiveRatings;
	    @Column(name = "pic_url_1")
	    private String picUrl1; 
	    @Column(name = "sexual_preference")
	    private Sexes sexualPreference;
	    @Column(name = "sex")
	    private Sexes sex;
	
	    public People() {
	    }
	
	    public People(String privateKey) {
	        this.privateKey = privateKey;
	    }
	
	    public People(String privateKey, String username, String password, boolean active, boolean anonymous, long positiveRatings) {
	        this.privateKey = privateKey;
	        this.username = username;
	        this.password = password;
	        this.active = active;
	        this.anonymous = anonymous;
	        this.positiveRatings = positiveRatings;
	    }
	
	    public String getUsername() {
	        return username;
	    }
	
	    public void setUsername(String username) {
	        this.username = username;
	    }
	
	    public String getPassword() {
	        return password;
	    }
	
	    public void setPassword(String password) {
	        this.password = password;
	    }
	
	    public String getPrivateKey() {
	        return privateKey;
	    }
	
	    public void setPrivateKey(String privateKey) {
	        this.privateKey = privateKey;
	    }
	
	    public String getName() {
	        return name;
	    }
	
	    public void setName(String name) {
	        this.name = name;
	    }
	
	    public boolean getActive() {
	        return active;
	    }
	
	    public void setActive(boolean active) {
	        this.active = active;
	    }
	
	    public boolean getAnonymous() {
	        return anonymous;
	    }
	
	    public void setAnonymous(boolean anonymous) {
	        this.anonymous = anonymous;
	    }
	
	    public long getPositiveRatings() {
	        return positiveRatings;
	    }
	
	    public void setPositiveRatings(long positiveRatings) {
	        this.positiveRatings = positiveRatings;
	    }
	
	    public String getPicUrl1() {
	        return picUrl1;
	    }
	
	    public void setPicUrl1(String picUrl1) {
	        this.picUrl1 = picUrl1;
	    }
	
	    public Sexes getSexualPreference() {
	        return sexualPreference;
	    }
	
	    public void setSexualPreference(Sexes sexualPreference) {
	        this.sexualPreference = sexualPreference;
	    }
	
	    public Sexes getSex() {
	        return sex;
	    }
	
	    public void setSex(Sexes sex) {
	        this.sex = sex;
	    }
	
	    @Override
	    public int hashCode() {
	        int hash = 0;
	        hash += (privateKey != null ? privateKey.hashCode() : 0);
	        return hash;
	    }
	
	    @Override
	    public boolean equals(Object object) {
	        // TODO: Warning - this method won't work in the case the id fields are not set
	        if (!(object instanceof People)) {
	            return false;
	        }
	        People other = (People) object;
	        if ((this.privateKey == null && other.privateKey != null) || (this.privateKey != null && !this.privateKey.equals(other.privateKey))) {
	            return false;
	        }
	        return true;
	    }
	
	    @Override
	    public String toString() {
	        return "com.yonderco.yonderbackend.People[ privateKey=" + privateKey + " ]";
	    }
	
	    public void incrementRank() {
	        this.positiveRatings = this.positiveRatings + 1;
	    }
}
