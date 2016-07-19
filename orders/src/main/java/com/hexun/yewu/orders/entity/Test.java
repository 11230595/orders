package com.hexun.yewu.orders.entity;

import java.io.Serializable;
import java.util.Date;

public class Test implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;

    private String userCode;

    private String userName;

    private Date createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode == null ? null : userCode.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}