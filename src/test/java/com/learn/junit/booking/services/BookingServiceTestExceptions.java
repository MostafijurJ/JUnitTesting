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

	@Test
	void should_NotCompleteBooking_When_forAnyPayment() {
		//given
		BookingRequest bookingRequest = new BookingRequest("1", LocalDate.of(2023, 01, 01), LocalDate.of(2023, 01, 05), 2, true);

		// payment mock returned failed for any kind of booking request by passing any() arguments for objects
		// and anyDouble() arguments for primitive types
		when(paymentServiceMock.pay(any(), anyDouble()))
				.thenThrow(BusinessException.class);

		//when
		Executable executable = () -> bookingService.makeBooking(bookingRequest);

		//then
		assertThrows(BusinessException.class, executable);

	}


	@Test
	void should_NotCompleteBooking_When_PriceTooHigh() {
		//given
		BookingRequest bookingRequest = new BookingRequest("1", LocalDate.of(2023, 01, 01), LocalDate.of(2023, 01, 05), 2, true);

		when(paymentServiceMock.pay(bookingRequest, 400.0))
				.thenThrow(BusinessException.class);

		//when
		Executable executable = () -> bookingService.makeBooking(bookingRequest);

		//then
		assertThrows(BusinessException.class, executable);

	}

	@Test
	void should_InvokedPayment_When_Prepaid() {
		//given
		BookingRequest bookingRequest = new BookingRequest("1", LocalDate.of(2023, 01, 01), LocalDate.of(2023, 01, 05), 2, true);

		//when
		bookingService.makeBooking(bookingRequest);

		//then
		verify(paymentServiceMock).pay(bookingRequest, 400.0);

	}

	@Test
	void should_NotInvokedPayment_When_NotPrepaid() {
		//given
		BookingRequest bookingRequest = new BookingRequest("1", LocalDate.of(2023, 01, 01), LocalDate.of(2023, 01, 05), 2, false);

		//when
		bookingService.makeBooking(bookingRequest);

		//then
		verify(paymentServiceMock,never()).pay(any(), anyDouble());

	}


	@Test
	void should_MakeBooking_When_InputOk() {
		//given
		BookingRequest bookingRequest = new BookingRequest("1", LocalDate.of(2023, 01, 01), LocalDate.of(2023, 01, 05), 2, true);

		//when
		String bookingId = bookingService.makeBooking(bookingRequest);

		//then
		verify(bookingDAOMock).save(bookingRequest);
		System.out.println("Booking Id: " + bookingId); // booking id will null, cause its mock


	}

}