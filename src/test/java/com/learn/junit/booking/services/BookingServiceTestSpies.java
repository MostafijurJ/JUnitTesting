package com.learn.junit.booking.services;

import com.learn.junit.booking.model.BookingDAO;
import com.learn.junit.booking.model.BookingRequest;
import com.learn.junit.booking.utils.BusinessException;
import com.learn.junit.booking.utils.MailSender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.*;

class BookingServiceTestSpies {

	private BookingService bookingService;
	PaymentService paymentServiceMock;
	BookingDAO bookingDAOMock;
	RoomService roomServiceMock;
	MailSender mailSenderMock;

	@BeforeEach
	void setUp() {
		paymentServiceMock = mock(PaymentService.class);
		bookingDAOMock = spy(BookingDAO.class);
		roomServiceMock = mock(RoomService.class);
		mailSenderMock = mock(MailSender.class);

		bookingService = new BookingService(paymentServiceMock, roomServiceMock, bookingDAOMock, mailSenderMock);

	}

	/**
	 *  Mock  = dummy object with no real logic
	 *
	 *  Spy = real object with real logic that can be modified
	 *
	 */

	@Test
	void should_MakeBooking_When_InputOk() {
		//given
		BookingRequest bookingRequest = new BookingRequest("1", LocalDate.of(2023, 01, 01), LocalDate.of(2023, 01, 05), 2, true);

		//when
		String bookingId = bookingService.makeBooking(bookingRequest);

		//then
		verify(bookingDAOMock).save(bookingRequest);
		System.out.println("Booking Id: " + bookingId);


	}

}