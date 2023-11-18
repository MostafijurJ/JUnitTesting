package com.learn.junit.booking.services;

import com.learn.junit.booking.model.BookingDAO;
import com.learn.junit.booking.model.BookingRequest;
import com.learn.junit.booking.utils.CurrencyConverter;
import com.learn.junit.booking.utils.MailSender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class BookingServiceTestStaticMock {

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
	void should_CalculateCorrectPrice() {

		try (MockedStatic<CurrencyConverter> mockedConverter = mockStatic(CurrencyConverter.class)) {

			//given
			BookingRequest bookingRequest = new BookingRequest("1", LocalDate.of(2023, 01, 01), LocalDate.of(2023, 01, 05), 2, true);

			double expectedPrice = 4 * 2 * 50.0;

			mockedConverter.when(() -> CurrencyConverter.toEuro(anyDouble())).thenReturn(400.0);

			//when
			double calculatePrice = bookingService.calculatePrice(bookingRequest);

			//then
			assertEquals(expectedPrice, calculatePrice);
		}

	}

}