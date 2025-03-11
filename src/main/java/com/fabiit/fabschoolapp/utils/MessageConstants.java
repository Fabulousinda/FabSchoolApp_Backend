package com.fabiit.fabschoolapp.utils;

import lombok.Getter;

@Getter
public enum MessageConstants {
	EXCEPTION_OCCURED("Check your Log File For the Error.", 1), RECORD_FOUND("Record Found.", 2),
	RECORD_NOT_FOUND("Record Not Found.", 3), ANNOUNCE_DELETED("Announcement Deleted Successfully.", 100),
	ANNOUNCE_NOT_DELETED("Announcement Not Deleted.", 101), ANNOUNCE_STATE_UPDATED("Announcement State Updated.", 102),
	ANNOUNCE_STATE_NOT_UPDATED("Announcement State Not Updated.", 103),
	ANNOUNCE_TYPE_UPDATED("Announcement Type Updated.", 104),
	ANNOUNCE_TYPE_NOT_UPDATED("Announcement Type Not Updated.", 105),
	ANNOUNCE_TYPE_LIST_FOUND("Announcement Type List Found.", 106),
	ANNOUNCE_TYPE_LIST_NULL("Announcement Type List Null.", 107),
	ANNOUNCE_TYPE_SAVED("Announcement Type Saved Successfully.", 108),
	ANNOUNCE_TYPE_NOT_SAVED("Announcement Type Not Saved.", 109),
	STANDARD_DELETED("Standard Deleted Successfully.", 110), STANDARD_NOT_DELETED("Standard Not Deleted.", 111),
	STANDARD_UPDATED("Standard Updated Successfully.", 112), STANDARD_NOT_UPDATED("Standard Not Updated.", 113),
	STANDARD_STATE_UPDATED("Standard State Updated Successfully.", 114),
	STANDARD_STATE_NOT_UPDATED("Standard State Not Updated.", 115),
	DIVISION_UPDATED("Division Updated Successfully.", 116), DIVISION_NOT_UPDATED("Division Not Updated.", 117),
	DIVISION_STATE_NOT_UPDATED("Division Not Updated.", 118),
	DIVISION_STATE_UPDATED("Division State Updated Successfully.", 119),
	DIVISION_DELETED("Division Deleted Successfully.", 120), DIVISION_NOT_DELETED("Division Not Deleted.", 121),
	GRADE_UPDATED("Grade Updated Successfully.", 122), GRADE_NOT_UPDATED("Grade Not Updated.", 123),
	GRADE_STATE_NOT_UPDATED("Grade Not Updated.", 124), GRADE_STATE_UPDATED("Grade State Updated Successfully.", 125),
	GRADE_DELETED("Grade Deleted Successfully.", 126), GRADE_NOT_DELETED("Grade Not Deleted.", 127),
	DIVISION_EMPTY("Division cannot be empty.", 128),
	DATA_SAVED("Data Saved Successfully.",129),
	DATA_NOT_SAVED("Data Not saved !! Please Try Again",130),
	ID_NOT_FOUND("Id Not Found !! Please Try Again",131),
	DATA_FOUND("Data Found",132),
	EXAM_DELETED("Exam deleted ",133),

	ROLE_UPDATED("Role Updated Successfully", 1013), ROLE_NOT_SAVED("Not Saved", 1014),
	ROLE_SAVED("Role Saved Successfully", 1015);

	private final String msg;
	private final long code;

	MessageConstants(String msg, long code) {
		this.msg = msg;
		this.code = code;
	}
}
