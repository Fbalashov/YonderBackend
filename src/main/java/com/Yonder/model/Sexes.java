package com.Yonder.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.sun.istack.NotNull;

@Entity
@Table(name = "sexes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sexes.findAll", query = "SELECT s FROM Sexes s"),
    @NamedQuery(name = "Sexes.findBySymbol", query = "SELECT s FROM Sexes s WHERE s.symbol = :symbol"),
    @NamedQuery(name = "Sexes.findByName", query = "SELECT s FROM Sexes s WHERE s.name = :name")})
public class Sexes implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "symbol")
    private String symbol;
    @Basic(optional = false)
    @NotNull
    @Column(name = "name")
    private String name;

    public Sexes() {
    }

    public Sexes(String symbol) {
        this.symbol = symbol;
    }

    public Sexes(String symbol, String name) {
        this.symbol = symbol;
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (symbol != null ? symbol.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sexes)) {
            return false;
        }
        Sexes other = (Sexes) object;
        if ((this.symbol == null && other.symbol != null) || (this.symbol != null && !this.symbol.equals(other.symbol))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.yonderco.yonderbackend.Sexes[ symbol=" + symbol + " ]";
    }
    
}
