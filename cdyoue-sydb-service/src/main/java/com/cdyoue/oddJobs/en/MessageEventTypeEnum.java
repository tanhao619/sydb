package com.cdyoue.oddJobs.en;

/**
 * message 枚举事件类型
 */
public enum MessageEventTypeEnum {
	/*事件类型：1审核，2邀请，3关注，4需求，5官方, 6收藏, 7点赞, 8应聘*/
	AUDIT(1, "审核"),
	INVITATION(2, "邀请"),
	FOCUS(3, "关注"),
	REQUIREMENT(4, "需求"),
	OFFICIAL(5, "官方"),
	COLLECTION(6, "收藏"),
	THUMBUP(7, "点赞"),
	APPLYFOR(8, "应聘");


	private int value;
	private String eventTypeDes;

	private MessageEventTypeEnum(int value, String eventTypeDes) {
		this.value = value;
		this.eventTypeDes = eventTypeDes;
	}

	public int getValue() {
		return value;
	}

	public String getEventTypeDes() {
		return eventTypeDes;
	}
}
