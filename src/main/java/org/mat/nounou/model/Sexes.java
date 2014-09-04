package org.mat.nounou.model;

import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "sexes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sexes.findAll", query = "SELECT s FROM Sexes s"),
    @NamedQuery(name = "Sexes.findBySymbol", query = "SELECT s FROM Sexes s WHERE s.symbol = :symbol"),
    @NamedQuery(name = "Sexes.findByName", query = "SELECT s FROM Sexes s WHERE s.name = :name")})
public class Sexes {
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "symbol")
    private String symbol;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
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
