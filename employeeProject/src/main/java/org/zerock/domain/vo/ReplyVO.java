package org.zerock.domain.vo;

import java.util.Date;

import lombok.Data;


@Data
public class ReplyVO {
	private Long r_no; // PK
	private Long b_no; // FK
	private Long emp_no;// FK
	private String reply;
	private String name;
	private Date reply_date;
	private Date update_date;
}


