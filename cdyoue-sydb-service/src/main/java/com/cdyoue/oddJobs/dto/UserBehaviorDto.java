package com.cdyoue.oddJobs.dto;

/**
 * Created by Administrator on 2017/4/27.
 */
public class UserBehaviorDto {
	private String id;
	private String userId;
	private String userBehaviorType;
	private String businessEntityTypeId;
	private String entityId;
	private String domainId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserBehaviorType() {
		return userBehaviorType;
	}

	public void setUserBehaviorType(String userBehaviorType) {
		this.userBehaviorType = userBehaviorType;
	}

	public String getBusinessEntityTypeId() {
		return businessEntityTypeId;
	}

	public void setBusinessEntityTypeId(String businessEntityTypeId) {
		this.businessEntityTypeId = businessEntityTypeId;
	}

	public String getEntityId() {
		return entityId;
	}

	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}

	public String getDomainId() {
		return domainId;
	}

	public void setDomainId(String domainId) {
		this.domainId = domainId;
	}
	@Override
	public String toString() {
		return "UserBehaviorDto{" +
				"id='" + id + '\'' +
				", userId='" + userId + '\'' +
				", userBehaviorType='" + userBehaviorType + '\'' +
				", businessEntityTypeId='" + businessEntityTypeId + '\'' +
				", entityId='" + entityId + '\'' +
				", domainId='" + domainId + '\'' +
				'}';
	}
}
