package src.bean;

public class CustomerMapper {

	
	public CustomerMapper(String customerCode) {
		super();
		this.customerCode = customerCode;
	}
	
	private String customerCode;
	private String branchCode;
	private String rmCode;
	
	public String getCustomerCode() {
		return customerCode;
	}
	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}
	public String getBranchCode() {
		return branchCode;
	}
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}
	public String getRmCode() {
		return rmCode;
	}
	public void setRmCode(String rmCode) {
		this.rmCode = rmCode;
	}
	
	
}
