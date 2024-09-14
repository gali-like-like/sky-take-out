package com.sky.dto;

public class EmployPasswordDTO {
	private Integer empId;
	private String newPassword;
	private String oldPassword;
	
	public Integer getEmpId() {
		return empId;
	}
	public void setEmpId(Integer empId) {
		this.empId = empId;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	
	@Override
	public String toString() {
		return "EmployPasswordDTO [empId=" + empId + ", newPassword=" + newPassword + ", oldPassword=" + oldPassword
				+ "]";
	}
}
