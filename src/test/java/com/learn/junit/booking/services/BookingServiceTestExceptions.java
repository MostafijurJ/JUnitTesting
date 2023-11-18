package com.learn.junit.booking.services;

import com.learn.junit.booking.model.BookingDAO;
import com.learn.junit.booking.model.BookingRequest;
import com.learn.junit.booking.utils.BusinessException;
import com.learn.junit.booking.utils.MailSender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class BookingServiceTestExceptions {

	private BookingService bookingService;
	PaymentService paymentServiceMock;
	BookingDAO bookingDAOMock;
	RoomService roomServiceMock;
	MailSender mailSenderMock;

	@BeforeEach
	void setUp() {
		paymentServiceMock = mock(PaymentService.class);
		bookingDAOMock = mock(BookingDAO.class);
		roomServiceMock = mock(RoomService.class);
		mailSenderMock = mock(MailSender.class);

		bookingService = new BookingService(paymentServiceMock, roomServiceMock, bookingDAOMock, mailSenderMock);

	}

	@Test
	void should_ThroughException_When_NoRoomAvailable() {
		//given
		BookingRequest bookingRequest = new BookingRequest("1", LocalDate.of(2023, 01, 01), LocalDate.of(2023, 01, 05), 2, true);

		when(roomServiceMock.findAvailableRoomId(bookingRequest))
				.thenThrow(BusinessException.class);

		//when
		Executable executable = () -> bookingService.makeBooking(bookingRequest);

		//then
		assertThrows(BusinessException.class, executable);

	}

}