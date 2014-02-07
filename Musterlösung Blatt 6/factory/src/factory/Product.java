package factory;

/**
 * This class defines a Product, having an ID
 * 
 * @author markus
 * @version 1.0
 */
public class Product {
    
    private final String productId;
    
    /**
     * Instantiates a new product.
     * 
     * @param productId
     *            the product id
     */
    public Product(String productId) {
        this.productId = productId;
    }
    
    /*
     * (non-Javadoc)
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
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        final Product other = (Product) obj;
        if (this.productId == null) {
            if (other.productId != null) {
                return false;
            }
        } else if (!this.productId.equals(other.productId)) {
            return false;
        }
        return true;
    }
    
    /**
     * Gets the id.
     * 
     * @return the id
     */
    public String getId() {
        return this.productId;
    }
    
    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = (prime * result)
                + ((this.productId == null) ? 0 : this.productId.hashCode());
        return result;
    }
    
    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return this.productId;
    }
}
