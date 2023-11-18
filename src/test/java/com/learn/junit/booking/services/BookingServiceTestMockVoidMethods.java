package com.learn.junit.booking.services;

import com.learn.junit.booking.model.BookingDAO;
import com.learn.junit.booking.model.BookingRequest;
import com.learn.junit.booking.utils.MailSender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.mockito.Mockito.*;

class BookingServiceTestMockVoidMethods {

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

	@Test
	void should_CancelBooking_When_InputOk() {
		//given
		BookingRequest bookingRequest = new BookingRequest("1", LocalDate.of(2023, 01, 01), LocalDate.of(2023, 01, 05), 2, true);

		bookingRequest.setRoomId("1");

		String bookingId = "f5f04453-a3bf-4fd8-b0d5-beb1403b83f4";


		doReturn(bookingRequest).when(bookingDAOMock).get(bookingId);

		//when
		bookingService.cancelBooking(bookingId);

		//then

	}

}