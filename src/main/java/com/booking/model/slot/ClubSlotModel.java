package com.booking.model.slot;

import java.sql.Date;
import java.sql.Timestamp;

public interface ClubSlotModel {

	Integer getSlotId();

	Integer getPrimaryBookingId();

	String getCreatedBy();

	Timestamp getCreatedDate();

	Integer getPlayerCount();

	Timestamp getSlotStartTimeStamp();

	Date getSlotDate();

	Timestamp getSlotEndTimeStmp();

	Integer getSlotDuration();

	String getSlotStatus();

	String getSecondaryBooking();

	String getClubName();

	String getTeeTime();

	String getSlotAvailable();

	String setSlotStatus();
}