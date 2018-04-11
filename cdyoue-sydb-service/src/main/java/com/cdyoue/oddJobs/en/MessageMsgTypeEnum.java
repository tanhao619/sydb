package com.cdyoue.oddJobs.en;

/**
 * message 事件类型
 */
public enum MessageMsgTypeEnum {

	/**
	 * 事件类型：1审核，2邀请，3关注，4需求，5官方, 6收藏, 7点赞
	 * 1审核：1求购知识产权，2出售知识产权，3发布融资项目（前台哪里发布），4场地，5新闻，6活动，7个人认证（实名），8个人认证（大咖）,9个人认证（导师）,10企业认证（实名）
	 * 2邀请：1承接需求，2工作（兼职），3投递简历，4回答问题;
	 * 3关注：1能人，2问题(关注的问题有了新的回答),3话题,4活动;
	 * 4需求：1投递简历， 2报名兼职， 3承接项目;
	 * 5-1官方通知：内推消息;
	 * 6收藏 1文章，2回答，3专利  ;
	 * 7点赞：1回答'
	 * 8应聘：1兼职，2全职
	 */

	// 1审核lg_portal_intellectual_sale_brand
	AuditBuyIntellectualProperty(1,"审核->求购知识产权"),
	AuditSellIntellectualPropertyBrand(2,"审核->出售知识产权(商标)"),
	AuditPublishFinanceProject(3,"审核->发布融资项目"),
	AuditPlace(4,"审核->场地"),
	AuditNews(5,"审核->新闻"),
	AuditActivity(6,"审核-->活动"),
	AuditCertificationPersonRealname(7,"审核->个人认证（实名）"),
	AuditCertificationPersonDaka(8,"审核->个人认证（大咖）"),
	AuditCertificationPersonMentor(9,"审核->个人认证（导师）"),
	AuditCertificationEnterRealname(10,"审核->企业资质认证"),
	AuditSellIntellectualPropertyPatent(11, "审核->出售知识产权(专利)"),
	AuditSellIntellectualPropertyWork(12, "审核->出售知识产权(著作权)"),
	AuditRequirement(13, "审核->需求"),

	//2邀请INVITATION
	InvitationAcceptRequirement(1,"邀请->承接需求"),
	InvitationFullTimeJob(2,"邀请->应聘工作（全职）"),
	InvitationPartTimeJob(3,"邀请->应聘工作（兼职）"),
	InvitationSendResume(4,"邀请->投递简历"),
	InvitationAnswerQuestions(5,"邀请->回答问题"),

	//3关注
	FocusTalent(1,"关注->能人(发表了新的文章或提出了新的问题)"),
	FocusQuestions(2,"关注->问题(关注的问题有了新的回答)"),
	FocusTopic(3,"关注->话题"),
	FoucsActivity(4,"关注->活动"),

	// 4需求
	// 需求->投递简历 对应8-2
	@Deprecated
	RequirementSendResume(1,"需求->投递简历"),// 废弃
	// 需求->报名兼职 对应8-1
	@Deprecated
	RequirementJoinPartTimeJob(2,"需求->报名兼职"),// 废弃
	RequirementAcceptProject(3,"需求->承接项目"),

	//5官方
	OfficialNotification(1,"官方通知：内推消息"),

	//6收藏
	CollectionArticle(1,"收藏->文章"),
	CollectionAnswer(2,"收藏->回答"),
	CollectionPatent(3,"收藏->专利"),

	//7点赞
	ThumbUpAnswer(1,"点赞->回答"),

	// 8应聘
	ApplyForPartTimeJob(1, "应聘->兼职"),
	ApplyForFullTimeJob(2, "应聘->全职");


	private int msgType;
	private String eventDescribe;

	private MessageMsgTypeEnum(int msgType,String eventDescribe) {
		this.msgType = msgType;
		this.eventDescribe = eventDescribe;
	}

	public int getMsgType() {
		return msgType;
	}

	public String getEventDescribe() {
		return eventDescribe;
	}
}
