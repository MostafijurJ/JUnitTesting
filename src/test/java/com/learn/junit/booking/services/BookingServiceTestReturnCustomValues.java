package com.learn.junit.booking.services;

import com.learn.junit.booking.model.BookingDAO;
import com.learn.junit.booking.model.Room;
import com.learn.junit.booking.utils.MailSender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BookingServiceTestReturnCustomValues {
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
	void should_CountAvailablePlaces() {

		//given
		when(roomServiceMock.getAvailableRooms()).thenReturn(Collections.singletonList(new Room("Room 1", 5)));
		int expected = 4;
		//when
		int availablePlaceCount = bookingService.getAvailablePlaceCount();

		//then
		assertEquals(expected, availablePlaceCount);
	}

	@Test
	void should_CountAvailablePlaces_When_MultipleRooms() {

		//given
		List<Room> list = Arrays.asList(new Room("Room 1", 5), new Room("Room 2", 4), new Room("Room 3", 2));
		when(roomServiceMock.getAvailableRooms()).thenReturn(list);
		int expected = 11;
		//when
		int availablePlaceCount = bookingService.getAvailablePlaceCount();

		//then
		assertEquals(expected, availablePlaceCount);
	}

}
