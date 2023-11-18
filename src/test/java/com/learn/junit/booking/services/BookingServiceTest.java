package com.learn.junit.booking.services;

import com.learn.junit.booking.model.BookingDAO;
import com.learn.junit.booking.model.BookingRequest;
import com.learn.junit.booking.utils.MailSender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class BookingServiceTest {

	private BookingService bookingService;

	@BeforeEach
	void setUp() {
		PaymentService paymentServiceMock = mock(PaymentService.class);
		BookingDAO bookingDAOMock = mock(BookingDAO.class);
		RoomService roomServiceMock = mock(RoomService.class);
		MailSender mailSenderMock = mock(MailSender.class);

		bookingService = new BookingService(paymentServiceMock, roomServiceMock, bookingDAOMock, mailSenderMock);

	}

	@Test
	void should_CountAvailablePlaces() {

		//given
		int expected = 100;
		//when
		int availablePlaceCount = bookingService.getAvailablePlaceCount();

		//then
		assertEquals(expected, availablePlaceCount);
	}

	@Test
	void should_CalculateCorrectPrice_When_CorrectInput() {
		//given
		BookingRequest bookingRequest = new BookingRequest("1", LocalDate.of(2023, 01, 01), LocalDate.of(2023, 01, 05), 2, true);

		double expected = 4 * 2 * 50.0;

		//when
		double actual = bookingService.calculatePrice(bookingRequest);

		//then
		assertEquals(expected, actual);

	}

	@Test
	void calculatePriceEuro() {
	}

	@Test
	void makeBooking() {
	}

	@Test
	void cancelBooking() {
	}
}