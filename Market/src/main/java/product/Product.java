package product;

import java.io.Serializable;

public class Product implements Serializable{

	private static final long serialVersionUID = 11L;

	private String productId; 		//상품코드
	private String pname; 			//상품명
	private Integer unitPrice;		//가격
	private String description;		//상품설명
	private String manufacture;		//제조사
	private String category;		//분류
	private Long unitsInStock;		//재고수
	private String condition;		//신상품or중고품
	private String productImage;	//상품 이미지
	private int quantity;
	
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public Integer getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(Integer unitPrice) {
		this.unitPrice = unitPrice;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getManufacture() {
		return manufacture;
	}
	public void setManufacture(String manufacture) {
		this.manufacture = manufacture;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Long getUnitsInStock() {
		return unitsInStock;
	}
	public void setUnitsInStock(Long unitsInStock) {
		this.unitsInStock = unitsInStock;
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public String getProductImage() {
		return productImage;
	}
	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
}
