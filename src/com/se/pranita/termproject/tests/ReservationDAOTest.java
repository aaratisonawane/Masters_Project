package com.se.pranita.termproject.tests;

import com.se.pranita.termproject.model.Reservation;
import com.se.pranita.termproject.model.dao.ReservationDAO;
import org.junit.Test;

import java.sql.Date;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by Pranita on 25/4/16.
 */
public class ReservationDAOTest {
    @Test
    public void testReservationDAO() throws Exception {
        ReservationDAO reservationDAO = new ReservationDAO();

        String className = ReservationDAOTest.class.getSimpleName();

        Reservation testReservation = new Reservation();
        //this user should exist
        testReservation.setNetID("SS123456");
        //this resource should exist
        testReservation.setResourceName("Fax 1000");
        testReservation.setSlot_time_range("0-0");
        testReservation.setSlot_date(java.sql.Date.valueOf("2025-12-12"));

        reservationDAO.save(testReservation.getResourceName(), testReservation.getNetID(), testReservation.getSlot_date(), testReservation.getSlot_time_range());

        Reservation savedReservation = null;
        ArrayList<Reservation> list = reservationDAO.getById(testReservation.getNetID());
        for(int i = 0 ; i < list.size() ; i++)
            if(list.get(i).getSlot_time_range().equalsIgnoreCase(testReservation.getSlot_time_range())){
                savedReservation = list.get(i);
            }

        assertNotNull("read failed", savedReservation);

        assertEquals("save failed", savedReservation.getNetID(), testReservation.getNetID());
        assertEquals("save failed", savedReservation.getResourceName(), testReservation.getResourceName());
        assertEquals("save failed", savedReservation.getSlot_date(), testReservation.getSlot_date());
        assertEquals("save failed", savedReservation.getSlot_time_range(), testReservation.getSlot_time_range());

        reservationDAO.delete(testReservation.getResourceName(), testReservation.getNetID(), testReservation.getSlot_date(), testReservation.getSlot_time_range());

        savedReservation = null;
        list = reservationDAO.getById(testReservation.getNetID());
        for(int i = 0 ; i < list.size() ; i++)
            if(list.get(i).getSlot_time_range().equalsIgnoreCase(testReservation.getSlot_time_range())){
                savedReservation = list.get(i);
            }

        assertNull("delete failed", savedReservation);
    }

}