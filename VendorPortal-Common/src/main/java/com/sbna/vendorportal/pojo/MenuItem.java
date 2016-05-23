package com.sbna.vendorportal.pojo;

public class MenuItem {
	private String menuDisplayName;
	private String menuIcon;
	private String menuUrl;
	private int menuOrder;

	public MenuItem(String menuDisplayName, String menuIcon, String menuUrl,
			int menuOrder) {
		super();
		this.menuDisplayName = menuDisplayName;
		this.menuIcon = menuIcon;
		this.menuUrl = menuUrl;
		this.menuOrder = menuOrder;
	}

	public String getMenuDisplayName() {
		return menuDisplayName;
	}

	public void setMenuDisplayName(String menuDisplayName) {
		this.menuDisplayName = menuDisplayName;
	}

	public String getMenuIcon() {
		return menuIcon;
	}

	public void setMenuIcon(String menuIcon) {
		this.menuIcon = menuIcon;
	}

	public String getMenuUrl() {
		return menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	public int getMenuOrder() {
		return menuOrder;
	}

	public void setMenuOrder(int menuOrder) {
		this.menuOrder = menuOrder;
	}

	@Override
	public String toString() {
		return "MenuItem [menuDisplayName=" + menuDisplayName + ", menuIcon="
				+ menuIcon + ", menuUrl=" + menuUrl + ", menuOrder="
				+ menuOrder + "]";
	}

}
