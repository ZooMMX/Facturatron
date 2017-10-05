/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facturatron.facturacion;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author octavioruizcastillo
 */
@Entity
@Table(name = "PRODUCT", catalog = "", schema = "APP")
@NamedQueries({
    @NamedQuery(name = "Product.findAll", query = "SELECT p FROM Product p")
    , @NamedQuery(name = "Product.findByProductId", query = "SELECT p FROM Product p WHERE p.productId = :productId")
    , @NamedQuery(name = "Product.findByManufacturerId", query = "SELECT p FROM Product p WHERE p.manufacturerId = :manufacturerId")
    , @NamedQuery(name = "Product.findByProductCode", query = "SELECT p FROM Product p WHERE p.productCode = :productCode")
    , @NamedQuery(name = "Product.findByPurchaseCost", query = "SELECT p FROM Product p WHERE p.purchaseCost = :purchaseCost")
    , @NamedQuery(name = "Product.findByQuantityOnHand", query = "SELECT p FROM Product p WHERE p.quantityOnHand = :quantityOnHand")
    , @NamedQuery(name = "Product.findByMarkup", query = "SELECT p FROM Product p WHERE p.markup = :markup")
    , @NamedQuery(name = "Product.findByAvailable", query = "SELECT p FROM Product p WHERE p.available = :available")
    , @NamedQuery(name = "Product.findByDescription", query = "SELECT p FROM Product p WHERE p.description = :description")})
public class Product implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "PRODUCT_ID")
    private Integer productId;
    @Basic(optional = false)
    @Column(name = "MANUFACTURER_ID")
    private int manufacturerId;
    @Basic(optional = false)
    @Column(name = "PRODUCT_CODE")
    private String productCode;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "PURCHASE_COST")
    private BigDecimal purchaseCost;
    @Column(name = "QUANTITY_ON_HAND")
    private Integer quantityOnHand;
    @Column(name = "MARKUP")
    private BigDecimal markup;
    @Column(name = "AVAILABLE")
    private String available;
    @Column(name = "DESCRIPTION")
    private String description;

    public Product() {
    }

    public Product(Integer productId) {
        this.productId = productId;
    }

    public Product(Integer productId, int manufacturerId, String productCode) {
        this.productId = productId;
        this.manufacturerId = manufacturerId;
        this.productCode = productCode;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        Integer oldProductId = this.productId;
        this.productId = productId;
        changeSupport.firePropertyChange("productId", oldProductId, productId);
    }

    public int getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(int manufacturerId) {
        int oldManufacturerId = this.manufacturerId;
        this.manufacturerId = manufacturerId;
        changeSupport.firePropertyChange("manufacturerId", oldManufacturerId, manufacturerId);
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        String oldProductCode = this.productCode;
        this.productCode = productCode;
        changeSupport.firePropertyChange("productCode", oldProductCode, productCode);
    }

    public BigDecimal getPurchaseCost() {
        return purchaseCost;
    }

    public void setPurchaseCost(BigDecimal purchaseCost) {
        BigDecimal oldPurchaseCost = this.purchaseCost;
        this.purchaseCost = purchaseCost;
        changeSupport.firePropertyChange("purchaseCost", oldPurchaseCost, purchaseCost);
    }

    public Integer getQuantityOnHand() {
        return quantityOnHand;
    }

    public void setQuantityOnHand(Integer quantityOnHand) {
        Integer oldQuantityOnHand = this.quantityOnHand;
        this.quantityOnHand = quantityOnHand;
        changeSupport.firePropertyChange("quantityOnHand", oldQuantityOnHand, quantityOnHand);
    }

    public BigDecimal getMarkup() {
        return markup;
    }

    public void setMarkup(BigDecimal markup) {
        BigDecimal oldMarkup = this.markup;
        this.markup = markup;
        changeSupport.firePropertyChange("markup", oldMarkup, markup);
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        String oldAvailable = this.available;
        this.available = available;
        changeSupport.firePropertyChange("available", oldAvailable, available);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        String oldDescription = this.description;
        this.description = description;
        changeSupport.firePropertyChange("description", oldDescription, description);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (productId != null ? productId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Product)) {
            return false;
        }
        Product other = (Product) object;
        if ((this.productId == null && other.productId != null) || (this.productId != null && !this.productId.equals(other.productId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "facturatron.facturacion.Product[ productId=" + productId + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
