package com.learn.junit.booking.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class BookingRequest {

	private final String userId;
	private final LocalDate dateFrom;
	private final LocalDate dateTo;
	private final int guestCount;
	private final boolean prepaid;
	private String roomId;
	
	public BookingRequest(String userId, LocalDate dateFrom, LocalDate dateTo, int guestCount, boolean prepaid) {
		super();
		this.userId = userId;
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
		this.guestCount = guestCount;
		this.prepaid = prepaid;
	}

}
